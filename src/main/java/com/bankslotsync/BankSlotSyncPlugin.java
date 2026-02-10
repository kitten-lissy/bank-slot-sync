package com.bankslotsync;

import com.google.inject.Provides;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.banktags.BankTagsPlugin;
import net.runelite.client.plugins.banktags.tabs.TabInterface;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDependency(BankTagsPlugin.class)
@PluginDescriptor(
	name = "Bank Slot Sync",
	description = "Automatically syncs item variants (recolors, ornament kits) to Bank Tag Layout slots",
	tags = {"bank", "tags", "layout", "variants", "cosmetic", "recolor", "sync"}
)
public class BankSlotSyncPlugin extends Plugin
{
	private static final String BANK_TAGS_CONFIG_GROUP = "banktags";
	private static final String BANK_TAG_LAYOUTS_CONFIG_GROUP = "banktaglayouts"; // External plugin
	private static final String LAYOUT_PREFIX = "layout_";
	private static final String TAG_TABS_KEY = "tagtabs";
	private static final String ITEM_TAG_PREFIX = "item_"; // Tags stored per item ID

	@Inject
	private Client client;

	@Inject
	private BankSlotSyncConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private TabInterface tabInterface;



	private final ItemVariantMapping variantMapping = new ItemVariantMapping();

	// Track bank item IDs to detect changes
	private Set<Integer> previousBankItems = new HashSet<>();

	// Cache item names for charge variant detection
	private final Map<Integer, String> itemNameCache = new HashMap<>();

	// Pending items to process (1-tick delay to let Bank Tags finish first)
	private Set<Integer> pendingNewItems = new HashSet<>();
	private Set<Integer> pendingAllBankItems = new HashSet<>();
	private boolean processPending = false;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Bank Slot Sync started! Tracking {} items across {} variant groups.",
			variantMapping.getItemCount(), variantMapping.getGroupCount());
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Bank Slot Sync stopped!");
		previousBankItems.clear();
		itemNameCache.clear();
		pendingNewItems.clear();
		pendingAllBankItems.clear();
		processPending = false;
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getContainerId() != InventoryID.BANK.getId())
		{
			return;
		}

		ItemContainer bankContainer = event.getItemContainer();
		if (bankContainer == null)
		{
			return;
		}

		// Get current bank item IDs
		Set<Integer> currentBankItems = new HashSet<>();
		for (Item item : bankContainer.getItems())
		{
			if (item.getId() > 0)
			{
				currentBankItems.add(item.getId());
			}
		}

		// Find newly added items (items in current but not in previous)
		Set<Integer> newItems = new HashSet<>(currentBankItems);
		newItems.removeAll(previousBankItems);

		if (!newItems.isEmpty())
		{
			debugLog("New items detected: {}", newItems);
			pendingNewItems.addAll(newItems);
			pendingAllBankItems = new HashSet<>(currentBankItems);
			processPending = true;
		}

		// Update previous state for next comparison
		previousBankItems = currentBankItems;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (!processPending)
		{
			return;
		}
		processPending = false;

		debugLog("Processing {} pending items", pendingNewItems.size());
		processNewItems(new HashSet<>(pendingNewItems), pendingAllBankItems);
		pendingNewItems.clear();
		pendingAllBankItems.clear();

		// If a bank tag is currently open, refresh the display
		if (tabInterface.getActiveTag() != null)
		{
			tabInterface.reloadActiveTab();
		}
	}

	/**
	 * Process newly deposited items and sync variants to layouts.
	 */
	private void processNewItems(Set<Integer> newItems, Set<Integer> allBankItems)
	{
		// Get all tag tabs
		String[] tagTabs = getTagTabs();
		if (tagTabs == null || tagTabs.length == 0)
		{
			debugLog("No tag tabs found");
			return;
		}
		debugLog("Found {} tag tabs", tagTabs.length);

		for (int newItemId : newItems)
		{
			// Copy tags from old variant and remove old variant's tags
			Set<Integer> variantGroup = variantMapping.getVariantGroup(newItemId);
			if (variantGroup != null)
			{
				handleVariantTags(newItemId, variantGroup, allBankItems);
			}

			// Check each tag tab's layout
			for (String tagName : tagTabs)
			{
				if (tagName == null || tagName.isEmpty())
				{
					continue;
				}

				String layoutStr = getLayoutConfig(tagName);
				if (layoutStr == null || layoutStr.isEmpty())
				{
					continue;
				}

				LayoutParser.Layout layout = LayoutParser.parseLayout(layoutStr);
				debugLog("Tag '{}' has layout with {} items", tagName, layout.size());

				int variantPosition = -1;
				int existingVariantId = -1;
				boolean isChargeVariant = false;

				// First, check hardcoded variant groups (variantGroup already fetched above)
				if (variantGroup != null)
				{
					variantPosition = layout.findVariantPosition(variantGroup);
					if (variantPosition >= 0)
					{
						existingVariantId = layout.getItem(variantPosition);
						debugLog("New item {} matches hardcoded variant group at pos {}", newItemId, variantPosition);
					}
				}

				// If no hardcoded match, try charge-based detection
				if (variantPosition < 0 && config.detectChargeVariants())
				{
					variantPosition = findChargeVariantPositionInLayout(layout, newItemId);
					if (variantPosition >= 0)
					{
						existingVariantId = layout.getItem(variantPosition);
						isChargeVariant = true;
						debugLog("New item {} matches charge variant pattern at pos {}", newItemId, variantPosition);
					}
				}

				// No variant match found in this layout
				if (variantPosition < 0)
				{
					continue;
				}

				// Check if the new item is already at the correct position
				if (existingVariantId == newItemId)
				{
					debugLog("Item {} already at correct position {} in tag '{}'", newItemId, variantPosition, tagName);
					continue;
				}

				// Check if the existing variant is still in the bank
				// If it's gone, the user probably swapped it for the new variant
				if (allBankItems.contains(existingVariantId))
				{
					debugLog("Existing variant {} still in bank", existingVariantId);
					if (config.layoutMode() == LayoutMode.ADJACENT
						|| (config.layoutMode() == LayoutMode.REPLACE && config.adjacentWhenOccupied()))
					{
						addVariantAdjacent(tagName, layout, variantPosition, newItemId);
					}
					continue;
				}

				// The old variant is gone - replace it with the new one
				debugLog("Old variant {} is GONE, syncing {} to position {}", existingVariantId, newItemId, variantPosition);
				debugLog("Layout BEFORE: {}", layout.serialize());
				syncVariantToLayout(tagName, layout, variantPosition, newItemId, isChargeVariant);
				debugLog("Layout AFTER: {}", getLayoutConfig(tagName));
			}
		}
	}

	/**
	 * Sync a variant item to a layout position based on the configured Layout Mode.
	 * This is called when the OLD variant is no longer in the bank (user swapped it).
	 */
	private void syncVariantToLayout(String tagName, LayoutParser.Layout layout, int variantPosition, int newItemId, boolean isChargeVariant)
	{
		String itemName = getItemName(newItemId);
		debugLog("syncVariantToLayout called: tag={}, pos={}, newId={}, layoutMode={}",
			tagName, variantPosition, newItemId, config.layoutMode());

		// First, remove the new item from any other position in the layout
		// (in case Bank Tags already added it at the end)
		int existingPos = layout.findItemPosition(newItemId);
		debugLog("New item {} current position in layout: {}", newItemId, existingPos);
		if (existingPos >= 0 && existingPos != variantPosition)
		{
			layout.setItem(existingPos, -1); // Remove from old position
			debugLog("Removed item {} from position {}", newItemId, existingPos);
		}

		// Use Layout Mode setting to determine placement
		LayoutMode mode = config.layoutMode();
		debugLog("Current layoutMode setting: {}", mode);

		switch (mode)
		{
			case REPLACE:
				// Replace: new item takes old item's exact position
				debugLog("REPLACE mode: setting position {} to item {}", variantPosition, newItemId);
				layout.setItem(variantPosition, newItemId);
				log.info("Replaced variant at position {} with {} in tag '{}' layout", variantPosition, itemName, tagName);
				sendChatNotification("Synced " + itemName + " to '" + tagName + "' (slot " + variantPosition + ")");
				break;

			case ADJACENT:
				// Insert next to the variant, shifting only items on the same row
				int insertPos = variantPosition + 1;
				debugLog("ADJACENT mode: variantPosition={}, insertPos={}", variantPosition, insertPos);
				debugLog("Layout BEFORE: {}", layout.getPositionToItem());
				layout.insertAtSameRow(insertPos, newItemId);
				debugLog("Layout AFTER: {}", layout.getPositionToItem());
				log.info("Inserted {} at position {} in tag '{}' layout", itemName, insertPos, tagName);
				sendChatNotification("Synced " + itemName + " to '" + tagName + "' (slot " + insertPos + ")");
				break;
		}

		String serialized = layout.serialize();
		debugLog("Serialized layout to save: {}", serialized);
		saveLayoutConfig(tagName, serialized);
	}

	/**
	 * Add a new variant next to an existing variant (ADJACENT mode when both items are in bank).
	 */
	private void addVariantAdjacent(String tagName, LayoutParser.Layout layout, int existingPosition, int newItemId)
	{
		String itemName = getItemName(newItemId);

		// First, remove the new item from any other position in the layout
		int existingPos = layout.findItemPosition(newItemId);
		if (existingPos >= 0)
		{
			layout.setItem(existingPos, -1);
			debugLog("Removed item {} from position {} before placing adjacent", newItemId, existingPos);
		}

		// Insert next to the existing item, shifting only items on the same row
		int insertPos = existingPosition + 1;
		layout.insertAtSameRow(insertPos, newItemId);

		log.info("Inserted {} at position {} (adjacent) in tag '{}' layout", itemName, insertPos, tagName);
		sendChatNotification("Added " + itemName + " to '" + tagName + "' (slot " + insertPos + ")");

		saveLayoutConfig(tagName, layout.serialize());
	}

	/**
	 * Get the name of an item, using cache when possible.
	 */
	private String getItemName(int itemId)
	{
		return itemNameCache.computeIfAbsent(itemId, id -> {
			ItemComposition comp = itemManager.getItemComposition(id);
			return comp != null ? comp.getName() : null;
		});
	}

	/**
	 * Find a charge variant in the layout by matching item names.
	 * Returns the position of a matching charge variant, or -1 if none found.
	 */
	private int findChargeVariantPositionInLayout(LayoutParser.Layout layout, int newItemId)
	{
		String newItemName = getItemName(newItemId);
		if (newItemName == null || !ChargeVariantDetector.isChargedItem(newItemName))
		{
			return -1;
		}

		String newBaseName = ChargeVariantDetector.getNormalizedBaseName(newItemName);

		for (Map.Entry<Integer, Integer> entry : layout.getPositionToItem().entrySet())
		{
			int layoutItemId = entry.getValue();
			if (layoutItemId <= 0 || layoutItemId == newItemId)
			{
				continue;
			}

			String layoutItemName = getItemName(layoutItemId);
			if (layoutItemName == null)
			{
				continue;
			}

			String layoutBaseName = ChargeVariantDetector.getNormalizedBaseName(layoutItemName);
			if (newBaseName.equals(layoutBaseName))
			{
				log.debug("Found charge variant match: {} ({}) -> {} ({})",
					newItemName, newItemId, layoutItemName, layoutItemId);
				return entry.getKey();
			}
		}

		return -1;
	}

	/**
	 * Send a notification to the game chat.
	 */
	private void sendChatNotification(String message)
	{
		if (config.showNotifications() && client.getGameState().getState() >= 30)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "[Bank Slot Sync] " + message, null);
		}
	}

	/**
	 * Get all tag tab names.
	 */
	private String[] getTagTabs()
	{
		String tagTabsStr = configManager.getConfiguration(BANK_TAGS_CONFIG_GROUP, TAG_TABS_KEY);
		if (tagTabsStr == null || tagTabsStr.isEmpty())
		{
			return new String[0];
		}
		return tagTabsStr.split(",");
	}

	/**
	 * Get the tags assigned to an item ID.
	 */
	private String getItemTags(int itemId)
	{
		return configManager.getConfiguration(BANK_TAGS_CONFIG_GROUP, ITEM_TAG_PREFIX + itemId);
	}

	/**
	 * Set the tags for an item ID.
	 */
	private void setItemTags(int itemId, String tags)
	{
		if (tags != null && !tags.isEmpty())
		{
			configManager.setConfiguration(BANK_TAGS_CONFIG_GROUP, ITEM_TAG_PREFIX + itemId, tags);
		}
	}

	/**
	 * Remove all tags from an item ID.
	 */
	private void removeItemTags(int itemId)
	{
		configManager.unsetConfiguration(BANK_TAGS_CONFIG_GROUP, ITEM_TAG_PREFIX + itemId);
		log.info("Removed tags from item {}", itemId);
	}

	/**
	 * Handle tag operations for a variant: copy tags to new item and remove from old.
	 */
	private void handleVariantTags(int newItemId, Set<Integer> variantGroup, Set<Integer> allBankItems)
	{
		// Find a variant that has tags and is no longer in the bank (was replaced)
		for (int variantId : variantGroup)
		{
			if (variantId == newItemId)
			{
				continue;
			}

			String oldTags = getItemTags(variantId);
			if (oldTags != null && !oldTags.isEmpty())
			{
				// Check if this variant is gone from bank (user swapped it)
				if (!allBankItems.contains(variantId))
				{
					// Copy tags to new item
					String existingNewTags = getItemTags(newItemId);
					if (existingNewTags == null || existingNewTags.isEmpty())
					{
						setItemTags(newItemId, oldTags);
						log.info("Copied tags '{}' from item {} to item {}", oldTags, variantId, newItemId);
					}

					// Remove tags from old item (if enabled)
					if (config.removeOldTags())
					{
						removeItemTags(variantId);
						log.info("Removed tags from old variant: {}", variantId);
					}

					return; // Only process one old variant
				}
			}
		}
	}

	/**
	 * Get the layout config for a specific tag.
	 * Checks both built-in Bank Tags and external Bank Tag Layouts plugin.
	 */
	private String getLayoutConfig(String tagName)
	{
		// Standardize the tag name to match Bank Tags format (lowercase, trimmed)
		String standardizedTag = Text.standardize(tagName);

		// First try the external Bank Tag Layouts plugin
		String layout = configManager.getConfiguration(BANK_TAG_LAYOUTS_CONFIG_GROUP, LAYOUT_PREFIX + standardizedTag);
		if (layout != null && !layout.isEmpty())
		{
			debugLog("Layout for '{}' (external plugin): {} chars", tagName, layout.length());
			return layout;
		}

		// Fall back to built-in Bank Tags
		layout = configManager.getConfiguration(BANK_TAGS_CONFIG_GROUP, LAYOUT_PREFIX + standardizedTag);
		debugLog("Layout for '{}' (built-in): {} chars", tagName, layout != null ? layout.length() : 0);
		return layout;
	}

	/**
	 * Save the layout config for a specific tag.
	 * Saves to whichever config group has the existing layout.
	 */
	private void saveLayoutConfig(String tagName, String layoutStr)
	{
		// Standardize the tag name to match Bank Tags format
		String standardizedTag = Text.standardize(tagName);
		debugLog("saveLayoutConfig: tag='{}' standardized='{}' length={}", tagName, standardizedTag, layoutStr.length());

		// Check which config group has the layout
		String externalLayout = configManager.getConfiguration(BANK_TAG_LAYOUTS_CONFIG_GROUP, LAYOUT_PREFIX + standardizedTag);
		if (externalLayout != null && !externalLayout.isEmpty())
		{
			configManager.setConfiguration(BANK_TAG_LAYOUTS_CONFIG_GROUP, LAYOUT_PREFIX + standardizedTag, layoutStr);
			debugLog("SAVED to external: {}", LAYOUT_PREFIX + standardizedTag);
		}
		else
		{
			configManager.setConfiguration(BANK_TAGS_CONFIG_GROUP, LAYOUT_PREFIX + standardizedTag, layoutStr);
			debugLog("SAVED to built-in: {}", LAYOUT_PREFIX + standardizedTag);
		}
	}

	private void debugLog(String message, Object... args)
	{
		log.debug(message, args);
	}

	@Provides
	BankSlotSyncConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankSlotSyncConfig.class);
	}
}
