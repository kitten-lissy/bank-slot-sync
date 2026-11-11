package com.bankslotsync;

import com.google.inject.Provides;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Item;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.ItemVariationMapping;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.banktags.BankTagsPlugin;
import net.runelite.client.plugins.banktags.BankTagsService;
import net.runelite.client.plugins.banktags.TagManager;
import net.runelite.client.plugins.banktags.tabs.Layout;
import net.runelite.client.plugins.banktags.tabs.LayoutManager;
import net.runelite.client.util.Text;

@Slf4j
@PluginDependency(BankTagsPlugin.class)
@PluginDescriptor(
	name = "Bank Slot Sync",
	description = "Keeps Bank Tag layouts and tags in step when you swap an item for a recolour, ornament kit or upgrade",
	tags = {"bank", "tags", "layout", "variants", "cosmetic", "recolor", "sync"}
)
public class BankSlotSyncPlugin extends Plugin
{
	private static final String BANK_TAGS_GROUP = "banktags";
	private static final String LAYOUT_KEY_PREFIX = BANK_TAGS_GROUP + ".layout_";
	private static final String ITEM_KEY_PREFIX = "item_";
	private static final String COMMAND = "bss";
	private static final int MAX_UNDO = 10;

	@Inject
	private Client client;

	@Inject
	private BankSlotSyncConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private BankTagsService bankTagsService;

	@Inject
	private LayoutManager layoutManager;

	@Inject
	private TagManager tagManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	private final ItemVariantMapping variantMapping = new ItemVariantMapping();

	// Bank contents at the last bank update. Null until the first bank load after login,
	// which is only recorded as a baseline and never treated as "new" items.
	private Set<Integer> previousBankItems;

	// New items waiting one tick so Bank Tags finishes its own update first
	private final Set<Integer> pendingNewItems = new LinkedHashSet<>();
	private Set<Integer> pendingBankSnapshot = Collections.emptySet();
	private boolean processPending;

	// "Ask first" mode: the batch waiting for ::bss apply
	private Set<Integer> askNewItems;
	private Set<Integer> askBankSnapshot;

	private final Deque<SyncRecord> undoStack = new ArrayDeque<>();

	/**
	 * Everything one sync changed, so it can be undone.
	 */
	private static final class SyncRecord
	{
		final Map<String, int[]> layoutsBefore = new LinkedHashMap<>();
		final List<TagOp> tagOps = new ArrayList<>();
		final List<String> lines = new ArrayList<>();

		boolean isEmpty()
		{
			return layoutsBefore.isEmpty() && tagOps.isEmpty();
		}
	}

	private static final class TagOp
	{
		final int itemId;
		final String tag;
		final boolean added;

		TagOp(int itemId, String tag, boolean added)
		{
			this.itemId = itemId;
			this.tag = tag;
			this.added = added;
		}
	}

	@Override
	protected void startUp()
	{
		log.debug("Bank Slot Sync started, tracking {} items in {} groups",
			variantMapping.getItemCount(), variantMapping.getGroupCount());
	}

	@Override
	protected void shutDown()
	{
		resetSession();
		undoStack.clear();
	}

	private void resetSession()
	{
		previousBankItems = null;
		pendingNewItems.clear();
		pendingBankSnapshot = Collections.emptySet();
		processPending = false;
		askNewItems = null;
		askBankSnapshot = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		GameState state = event.getGameState();
		if (state == GameState.LOGIN_SCREEN || state == GameState.HOPPING)
		{
			// A new account or world: start again from a fresh bank baseline
			resetSession();
			if (state == GameState.LOGIN_SCREEN)
			{
				undoStack.clear();
			}
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getContainerId() != InventoryID.BANK)
		{
			return;
		}

		ItemContainer bank = event.getItemContainer();
		if (bank == null)
		{
			return;
		}

		Set<Integer> current = bankItemIds(bank);

		if (previousBankItems == null || config.syncMode() == SyncMode.OFF)
		{
			previousBankItems = current;
			return;
		}

		for (int id : current)
		{
			if (!previousBankItems.contains(id) && isCandidate(id))
			{
				pendingNewItems.add(id);
			}
		}
		previousBankItems = current;

		if (!pendingNewItems.isEmpty())
		{
			pendingBankSnapshot = current;
			processPending = true;
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (!processPending)
		{
			return;
		}
		processPending = false;

		Set<Integer> newItems = new LinkedHashSet<>(pendingNewItems);
		Set<Integer> bank = pendingBankSnapshot;
		pendingNewItems.clear();
		pendingBankSnapshot = Collections.emptySet();

		switch (config.syncMode())
		{
			case AUTOMATIC:
				applySync(newItems, bank);
				break;
			case ASK_FIRST:
				SyncRecord preview = sync(newItems, bank, false);
				if (!preview.isEmpty())
				{
					askNewItems = newItems;
					askBankSnapshot = bank;
					sendChat(String.join("; ", preview.lines) + ". Type ::bss apply to update, or ignore this.");
				}
				break;
			default:
				break;
		}
	}

	@Subscribe
	public void onCommandExecuted(CommandExecuted event)
	{
		if (!COMMAND.equalsIgnoreCase(event.getCommand()))
		{
			return;
		}

		String arg = event.getArguments().length > 0 ? event.getArguments()[0].toLowerCase() : "";
		switch (arg)
		{
			case "undo":
				undoLastSync();
				break;
			case "apply":
				if (askNewItems == null)
				{
					sendChatAlways("nothing is waiting to be applied.");
					return;
				}
				Set<Integer> items = askNewItems;
				Set<Integer> bank = askBankSnapshot;
				askNewItems = null;
				askBankSnapshot = null;
				if (!applySync(items, bank))
				{
					sendChatAlways("nothing left to change.");
				}
				break;
			default:
				sendChatAlways("commands are ::bss undo (revert the last sync) and ::bss apply (apply a waiting sync).");
				break;
		}
	}

	/**
	 * Run a sync and keep it for undo. Returns true if anything changed.
	 */
	private boolean applySync(Set<Integer> newItems, Set<Integer> bank)
	{
		SyncRecord record = sync(newItems, bank, true);
		if (record.isEmpty())
		{
			return false;
		}

		undoStack.push(record);
		while (undoStack.size() > MAX_UNDO)
		{
			undoStack.removeLast();
		}

		if (config.showNotifications())
		{
			sendChat(String.join("; ", record.lines) + ". Type ::bss undo to revert.");
		}
		return true;
	}

	/**
	 * Work out (and, if {@code apply}, make) the layout and tag changes for a batch of
	 * newly banked items.
	 */
	private SyncRecord sync(Set<Integer> newItems, Set<Integer> bank, boolean apply)
	{
		SyncRecord record = new SyncRecord();
		Set<Integer> held = heldItemIds();
		Set<String> excluded = excludedTabs();

		String activeTag = standardize(bankTagsService.getActiveTag());
		Layout activeLayout = bankTagsService.getActiveLayout();

		// Load every layout once per batch
		Map<String, int[]> layouts = new LinkedHashMap<>();
		for (String tag : layoutTags())
		{
			if (excluded.contains(tag))
			{
				continue;
			}
			Layout layout = tag.equals(activeTag) && activeLayout != null ? activeLayout : layoutManager.loadLayout(tag);
			if (layout != null && layout.getLayout() != null)
			{
				layouts.put(tag, Arrays.copyOf(layout.getLayout(), layout.getLayout().length));
			}
		}

		Set<String> changedTags = new LinkedHashSet<>();

		for (int newId : newItems)
		{
			Set<Integer> group = variantMapping.getVariantGroup(newId);
			boolean fromChargeDetection = false;
			if (group == null)
			{
				group = chargeVariants(newId);
				fromChargeDetection = true;
			}
			if (group == null || group.size() < 2)
			{
				continue;
			}

			String newName = itemName(newId);
			Map<Integer, List<String>> replacedIn = new LinkedHashMap<>();
			List<String> addedTo = new ArrayList<>();

			// Tags
			List<String> copied = new ArrayList<>();
			int tagSource = -1;
			if (config.copyTags() && !fromChargeDetection)
			{
				for (int oldId : group)
				{
					if (oldId == newId || bank.contains(oldId) || held.contains(oldId))
					{
						continue;
					}
					List<String> toCopy = tagsToCopy(oldId, newId, excluded);
					if (!toCopy.isEmpty())
					{
						tagSource = oldId;
						copied.addAll(toCopy);
						break;
					}
				}
			}
			if (tagSource > 0)
			{
				Set<String> oldVariationTags = variationTags(tagSource);
				for (String tag : copied)
				{
					record.tagOps.add(new TagOp(newId, tag, true));
					if (apply)
					{
						tagManager.addTag(newId, tag, false);
					}
					if (config.removeOldTags() && !oldVariationTags.contains(tag))
					{
						record.tagOps.add(new TagOp(tagSource, tag, false));
						if (apply)
						{
							tagManager.removeTag(tagSource, tag);
						}
					}
					changedTags.add(tag);
				}
			}

			// Layouts
			for (Map.Entry<String, int[]> entry : layouts.entrySet())
			{
				String tag = entry.getKey();
				int[] arr = entry.getValue();

				// Already placed (on purpose or by hand): leave this layout alone
				if (LayoutEdits.count(arr, newId) > 0)
				{
					continue;
				}

				int gonePos = -1;
				int presentPos = -1;
				for (int pos = 0; pos < arr.length; pos++)
				{
					int id = arr[pos];
					if (id <= 0 || id == newId || !group.contains(id))
					{
						continue;
					}
					boolean stillHave = bank.contains(id) || held.contains(id);
					if (!stillHave && gonePos < 0)
					{
						gonePos = pos;
					}
					else if (stillHave && presentPos < 0)
					{
						presentPos = pos;
					}
				}

				int[] updated = null;
				if (gonePos >= 0)
				{
					int oldId = arr[gonePos];
					if (config.layoutMode() == LayoutMode.REPLACE)
					{
						updated = Arrays.copyOf(arr, arr.length);
						LayoutEdits.replaceAll(updated, oldId, newId);
					}
					else
					{
						updated = LayoutEdits.insertNextTo(arr, gonePos, newId);
					}
					replacedIn.computeIfAbsent(oldId, k -> new ArrayList<>()).add(tag);
				}
				else if (presentPos >= 0
					&& (config.layoutMode() == LayoutMode.ADJACENT || config.adjacentWhenOccupied()))
				{
					updated = LayoutEdits.insertNextTo(arr, presentPos, newId);
					addedTo.add(tag);
				}

				if (updated != null)
				{
					record.layoutsBefore.putIfAbsent(tag, arr);
					entry.setValue(updated);
					changedTags.add(tag);
				}
			}

			boolean replace = config.layoutMode() == LayoutMode.REPLACE;
			for (Map.Entry<Integer, List<String>> e : replacedIn.entrySet())
			{
				String verb = replace
					? (apply ? "replaced" : "can replace")
					: (apply ? "went next to" : "can go next to");
				record.lines.add(newName + " " + verb + " " + itemName(e.getKey()) + " in " + joinTabs(e.getValue()));
			}
			if (!addedTo.isEmpty())
			{
				record.lines.add(newName + (apply ? " was added" : " can be added") + " next to the old version in "
					+ joinTabs(addedTo));
			}
			if (!copied.isEmpty() && replacedIn.isEmpty() && addedTo.isEmpty())
			{
				record.lines.add(newName + (apply ? " got" : " can get") + " the tags " + joinTabs(copied)
					+ " from " + itemName(tagSource));
			}
		}

		if (apply)
		{
			boolean refreshActive = false;
			for (String tag : record.layoutsBefore.keySet())
			{
				boolean active = tag.equals(activeTag) && activeLayout != null;
				writeLayout(tag, layouts.get(tag), active ? activeLayout : null);
				refreshActive |= active;
			}
			refreshActive |= activeTag != null && changedTags.contains(activeTag);
			if (refreshActive)
			{
				reopenActiveTag();
			}
		}

		return record;
	}

	private void undoLastSync()
	{
		SyncRecord record = undoStack.poll();
		if (record == null)
		{
			sendChatAlways("there is nothing to undo.");
			return;
		}

		String activeTag = standardize(bankTagsService.getActiveTag());
		Layout activeLayout = bankTagsService.getActiveLayout();
		boolean refreshActive = false;

		for (Map.Entry<String, int[]> e : record.layoutsBefore.entrySet())
		{
			boolean active = e.getKey().equals(activeTag) && activeLayout != null;
			writeLayout(e.getKey(), e.getValue(), active ? activeLayout : null);
			refreshActive |= active;
		}

		List<TagOp> ops = new ArrayList<>(record.tagOps);
		Collections.reverse(ops);
		for (TagOp op : ops)
		{
			if (op.added)
			{
				tagManager.removeTag(op.itemId, op.tag);
			}
			else
			{
				tagManager.addTag(op.itemId, op.tag, false);
			}
			refreshActive |= op.tag.equals(activeTag);
		}

		if (refreshActive)
		{
			reopenActiveTag();
		}
		sendChatAlways("undid the last sync (" + String.join("; ", record.lines) + ").");
	}

	/**
	 * Save a layout. When it is the open tab, change Bank Tags' own copy too so it does
	 * not write the old version back.
	 */
	private void writeLayout(String tag, int[] items, Layout activeLayout)
	{
		Layout target = activeLayout != null ? activeLayout : new Layout(tag, Arrays.copyOf(items, items.length));
		if (activeLayout != null)
		{
			if (target.size() < items.length)
			{
				target.resize(items.length);
			}
			for (int pos = 0; pos < target.size(); pos++)
			{
				target.setItemAtPos(pos < items.length ? items[pos] : LayoutEdits.EMPTY, pos);
			}
		}
		layoutManager.saveLayout(target);
	}

	private void reopenActiveTag()
	{
		String activeTag = bankTagsService.getActiveTag();
		if (activeTag != null)
		{
			bankTagsService.openBankTag(activeTag, BankTagsService.OPTION_ALLOW_MODIFICATIONS);
		}
	}

	/**
	 * Tags the old item has (set on that exact item) that the new item does not have yet.
	 */
	private List<String> tagsToCopy(int oldId, int newId, Set<String> excluded)
	{
		List<String> result = new ArrayList<>();
		Collection<String> oldTags = exactTags(oldId);
		if (oldTags.isEmpty())
		{
			return result;
		}
		Set<String> newTags = new HashSet<>(exactTags(newId));
		newTags.addAll(variationTags(newId));
		for (String tag : oldTags)
		{
			if (!newTags.contains(tag) && !excluded.contains(tag))
			{
				result.add(tag);
			}
		}
		return result;
	}

	// Bank Tags config is only read here; all writes go through TagManager and LayoutManager.
	private Collection<String> exactTags(int itemId)
	{
		return readTags(itemManager.canonicalize(itemId));
	}

	private Set<String> variationTags(int itemId)
	{
		return new HashSet<>(readTags(-ItemVariationMapping.map(itemManager.canonicalize(itemId))));
	}

	private Collection<String> readTags(int key)
	{
		String value = configManager.getConfiguration(BANK_TAGS_GROUP, ITEM_KEY_PREFIX + key);
		if (value == null || value.isEmpty())
		{
			return Collections.emptyList();
		}
		return new LinkedHashSet<>(Text.fromCSV(value.toLowerCase()));
	}

	/**
	 * Names of all tags that have a layout.
	 */
	private List<String> layoutTags()
	{
		List<String> tags = new ArrayList<>();
		for (String key : configManager.getConfigurationKeys(LAYOUT_KEY_PREFIX))
		{
			String tag = key.substring(LAYOUT_KEY_PREFIX.length());
			if (!tag.isEmpty())
			{
				tags.add(tag);
			}
		}
		return tags;
	}

	private Set<String> excludedTabs()
	{
		Set<String> result = new HashSet<>();
		for (String tab : Text.fromCSV(config.excludedTabs()))
		{
			String s = standardize(tab);
			if (s != null && !s.isEmpty())
			{
				result.add(s);
			}
		}
		return result;
	}

	private static String standardize(String tag)
	{
		return tag == null ? null : Text.standardize(tag);
	}

	/**
	 * Whether a newly banked item could need syncing at all. Used to skip everything
	 * else quickly for ordinary items.
	 */
	private boolean isCandidate(int itemId)
	{
		if (variantMapping.isVariant(itemId))
		{
			return true;
		}
		return config.detectChargeVariants() && chargeVariants(itemId) != null;
	}

	/**
	 * Other charge or degradation states RuneLite treats as the same item, or null.
	 * Potions and other drinkable items are skipped.
	 */
	private Set<Integer> chargeVariants(int itemId)
	{
		if (!config.detectChargeVariants())
		{
			return null;
		}
		Collection<Integer> variations = ItemVariationMapping.getVariations(ItemVariationMapping.map(itemId));
		if (variations == null || variations.size() < 2 || isDrinkable(itemId))
		{
			return null;
		}
		return new HashSet<>(variations);
	}

	private boolean isDrinkable(int itemId)
	{
		ItemComposition comp = itemManager.getItemComposition(itemId);
		String[] actions = comp.getInventoryActions();
		if (actions == null)
		{
			return false;
		}
		for (String action : actions)
		{
			if ("Drink".equalsIgnoreCase(action))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Real (non-placeholder) items in the bank.
	 */
	private Set<Integer> bankItemIds(ItemContainer bank)
	{
		Set<Integer> ids = new HashSet<>();
		for (Item item : bank.getItems())
		{
			// Placeholders have a quantity of 0
			if (item.getId() > 0 && item.getQuantity() > 0)
			{
				ids.add(item.getId());
			}
		}
		return ids;
	}

	/**
	 * Items in the inventory or worn. These have not been swapped away.
	 */
	private Set<Integer> heldItemIds()
	{
		Set<Integer> ids = new HashSet<>();
		for (int containerId : new int[]{InventoryID.INV, InventoryID.WORN})
		{
			ItemContainer container = client.getItemContainer(containerId);
			if (container == null)
			{
				continue;
			}
			for (Item item : container.getItems())
			{
				if (item.getId() > 0)
				{
					ids.add(itemManager.canonicalize(item.getId()));
				}
			}
		}
		return ids;
	}

	private String itemName(int itemId)
	{
		return itemManager.getItemComposition(itemId).getMembersName();
	}

	private static String joinTabs(List<String> tabs)
	{
		if (tabs.size() <= 3)
		{
			return String.join(", ", tabs);
		}
		return String.join(", ", tabs.subList(0, 3)) + " (+" + (tabs.size() - 3) + " more)";
	}

	private void sendChat(String message)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}
		sendChatAlways(message);
	}

	private void sendChatAlways(String message)
	{
		String text = new ChatMessageBuilder()
			.append(ChatColorType.HIGHLIGHT)
			.append("Bank Slot Sync: ")
			.append(ChatColorType.NORMAL)
			.append(message)
			.build();

		chatMessageManager.queue(QueuedMessage.builder()
			.type(ChatMessageType.CONSOLE)
			.runeLiteFormattedMessage(text)
			.build());
	}

	@Provides
	BankSlotSyncConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankSlotSyncConfig.class);
	}
}
