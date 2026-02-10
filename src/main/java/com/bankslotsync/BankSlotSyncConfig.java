package com.bankslotsync;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup(BankSlotSyncConfig.CONFIG_GROUP)
public interface BankSlotSyncConfig extends Config
{
	String CONFIG_GROUP = "bankslotsync";

	@ConfigSection(
		name = "How It Works",
		description = "Bank Slot Sync automatically updates your Bank Tag Layouts when you acquire " +
			"item variants (recolors, ornament kits, charged items, degraded equipment). " +
			"When a new variant appears in your bank, it will be placed in your tag layouts " +
			"based on the Layout Mode setting below.",
		position = 0,
		closedByDefault = false
	)
	String instructionsSection = "instructions";

	@ConfigItem(
		keyName = "layoutMode",
		name = "Layout Mode",
		description = "<html>Where to place the new variant in the layout:" +
			"<br><br><b>Replace:</b> New variant takes the exact position of the old item" +
			"<br><b>Adjacent:</b> New variant is inserted next to the old item (same row only)" +
			"<br><br>Example: Old item at slot 5" +
			"<br>• Replace: New item goes to slot 5" +
			"<br>• Adjacent: New item goes to slot 6, only that row shifts</html>",
		position = 1,
		section = instructionsSection
	)
	default LayoutMode layoutMode()
	{
		return LayoutMode.REPLACE;
	}

	@ConfigItem(
		keyName = "removeOldTags",
		name = "Remove Tags from Old Item",
		description = "<html>When you swap an item for its variant:" +
			"<br><br><b>ON (recommended):</b>" +
			"<br>• Old item's tags are removed" +
			"<br>• Prevents placeholder clutter in tag tabs" +
			"<br><br><b>OFF:</b>" +
			"<br>• Old item keeps its tags" +
			"<br>• Shows as a grayed-out placeholder</html>",
		position = 2,
		section = instructionsSection
	)
	default boolean removeOldTags()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showNotifications",
		name = "Show Chat Notifications",
		description = "Display a chat message when a variant is synced to a layout. " +
			"Useful for confirming the plugin is working.",
		position = 3,
		section = instructionsSection
	)
	default boolean showNotifications()
	{
		return true;
	}

	@ConfigSection(
		name = "Advanced",
		description = "Advanced detection settings",
		position = 10,
		closedByDefault = true
	)
	String advancedSection = "advanced";

	@ConfigItem(
		keyName = "adjacentWhenOccupied",
		name = "Place Adjacent When Slot Occupied",
		description = "<html>Only applies when Layout Mode is set to <b>Replace</b>." +
			"<br><br>When the original item is still in your bank (slot is not a placeholder)," +
			" place the new variant adjacent instead of doing nothing.</html>",
		position = 11,
		section = advancedSection
	)
	default boolean adjacentWhenOccupied()
	{
		return false;
	}

	@ConfigItem(
		keyName = "detectChargeVariants",
		name = "Detect Charge Variants",
		description = "<html>Automatically detect items with charges as variants of each other:" +
			"<br>• Jewelry charges: Ring of dueling(8) ↔ Ring of dueling(1)" +
			"<br>• Barrows degradation: Ahrim's robetop 100 ↔ Ahrim's robetop 0" +
			"<br>• Trident charges: Trident of the seas (full) ↔ Trident of the seas" +
			"<br><br>Disable if you want to keep charged/uncharged items in separate positions.</html>",
		position = 12,
		section = advancedSection
	)
	default boolean detectChargeVariants()
	{
		return false;
	}

}
