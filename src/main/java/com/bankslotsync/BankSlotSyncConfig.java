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
		name = "General",
		description = "When you put a new version of an item in your bank (a recolour, ornament kit, imbue or upgrade)" +
			" and the old version is gone, Bank Slot Sync puts the new one in the old one's tag layout slot and copies" +
			" its bank tags. RuneLite already draws many recolours in the right slot on its own. This plugin keeps the" +
			" saved layouts and tags in step. Type ::bss undo in chat to revert the last change.",
		position = 0,
		closedByDefault = false
	)
	String generalSection = "instructions";

	@ConfigItem(
		keyName = "syncMode",
		name = "Sync Mode",
		description = "<html><b>Automatic:</b> update layouts and tags as soon as a new version reaches the bank" +
			"<br><b>Ask first:</b> post a chat message and wait for ::bss apply" +
			"<br><b>Off:</b> do nothing</html>",
		position = 1,
		section = generalSection
	)
	default SyncMode syncMode()
	{
		return SyncMode.AUTOMATIC;
	}

	@ConfigItem(
		keyName = "layoutMode",
		name = "Layout Mode",
		description = "<html>Where the new version goes in a tag layout when the old version is gone:" +
			"<br><br><b>Replace original slot:</b> it takes the old item's slot" +
			"<br><b>Place next to original:</b> it goes in the slot to the right of the old item," +
			" shifting that row only up to the first gap</html>",
		position = 2,
		section = generalSection
	)
	default LayoutMode layoutMode()
	{
		return LayoutMode.REPLACE;
	}

	@ConfigItem(
		keyName = "copyTags",
		name = "Copy Tags to New Version",
		description = "Give the new version the bank tags the old version had, so it shows up in the same tag tabs.",
		position = 3,
		section = generalSection
	)
	default boolean copyTags()
	{
		return true;
	}

	@ConfigItem(
		keyName = "removeOldTags",
		name = "Remove Tags from Old Item",
		description = "<html>Off by default." +
			"<br><br><b>On:</b> after copying, the tags that were copied are taken off the old version," +
			" so it no longer shows as a placeholder in those tabs. Other tags stay." +
			"<br><b>Off:</b> the old version keeps its tags.</html>",
		position = 4,
		section = generalSection
	)
	default boolean removeOldTags()
	{
		return false;
	}

	@ConfigItem(
		keyName = "excludedTabs",
		name = "Never Touch These Tabs",
		description = "Comma-separated tag tab names that Bank Slot Sync must never change, for example: fashion, collection",
		position = 5,
		section = generalSection
	)
	default String excludedTabs()
	{
		return "";
	}

	@ConfigItem(
		keyName = "showNotifications",
		name = "Show Chat Notifications",
		description = "Post one chat message per sync naming the items and tabs that changed.",
		position = 6,
		section = generalSection
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
		name = "Insert When You Keep Both",
		description = "<html>Only used when Layout Mode is <b>Replace original slot</b>." +
			"<br><br>When the old version is still in your bank, inventory or equipment," +
			" insert the new version next to it. When off, the layout is left alone in that case.</html>",
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
		description = "<html>Also treat items that RuneLite counts as the same item with a different charge" +
			" or degradation state as versions of each other, for example" +
			"<br>Ring of dueling(8) and Ring of dueling(1), or Ahrim's robetop 100 and Ahrim's robetop 0." +
			"<br><br>Potions and other drinkable items are always ignored.</html>",
		position = 12,
		section = advancedSection
	)
	default boolean detectChargeVariants()
	{
		return false;
	}
}
