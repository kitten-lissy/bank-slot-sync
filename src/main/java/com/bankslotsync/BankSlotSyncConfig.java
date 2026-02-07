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
		keyName = "enabled",
		name = "Enable Plugin",
		description = "Enable automatic variant syncing in Bank Tag Layouts. " +
			"When enabled, acquiring a new item variant will automatically update your tag layouts.",
		position = 1,
		section = instructionsSection
	)
	default boolean enabled()
	{
		return true;
	}

	@ConfigItem(
		keyName = "layoutMode",
		name = "Layout Mode",
		description = "<html>Where to place the new variant in the layout:" +
			"<br><br><b>Replace:</b> New variant takes the exact position of the old item" +
			"<br><b>Adjacent:</b> New variant is inserted next to the old item (same row only)" +
			"<br><br>Example: Old item at slot 5" +
			"<br>• Replace: New item goes to slot 5" +
			"<br>• Adjacent: New item goes to slot 6, only that row shifts</html>",
		position = 2,
		section = instructionsSection
	)
	default LayoutMode layoutMode()
	{
		return LayoutMode.REPLACE;
	}

	@ConfigItem(
		keyName = "copyTags",
		name = "Copy Tags to Variants",
		description = "When a variant is detected, copy all bank tags from the original item to the new variant. " +
			"This ensures your new item appears in all the same tag tabs as the original.",
		position = 3,
		section = instructionsSection
	)
	default boolean copyTags()
	{
		return true;
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
		position = 4,
		section = instructionsSection
	)
	default boolean removeOldTags()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showNotifications",
		name = "Show Chat Notifications",
		description = "Display a chat message when a variant is synced to a layout. " +
			"Useful for confirming the plugin is working.",
		position = 5,
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
		keyName = "detectChargeVariants",
		name = "Detect Charge Variants",
		description = "<html>Automatically detect items with charges as variants of each other:" +
			"<br>• Jewelry charges: Ring of dueling(8) ↔ Ring of dueling(1)" +
			"<br>• Barrows degradation: Ahrim's robetop 100 ↔ Ahrim's robetop 0" +
			"<br>• Trident charges: Trident of the seas (full) ↔ Trident of the seas" +
			"<br><br>Disable if you want to keep charged/uncharged items in separate positions.</html>",
		position = 11,
		section = advancedSection
	)
	default boolean detectChargeVariants()
	{
		return true;
	}

	@ConfigItem(
		keyName = "debugLogging",
		name = "Debug Logging",
		description = "Enable detailed logging in the RuneLite log file. Useful for troubleshooting.",
		position = 12,
		section = advancedSection
	)
	default boolean debugLogging()
	{
		return false;
	}
}
