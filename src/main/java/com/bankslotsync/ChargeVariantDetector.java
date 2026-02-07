package com.bankslotsync;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;

/**
 * Detects charge-based item variants by pattern matching item names.
 * Handles items like:
 * - Games necklace(8), Games necklace(1), etc.
 * - Amulet of glory(6), Amulet of glory(1), etc.
 * - Ring of dueling(8), Ring of dueling(1), etc.
 * - Crystal bow (new), Crystal bow, Crystal bow (i), etc.
 * - Barrows items: Ahrim's robetop 100, Ahrim's robetop 75, etc.
 */
@Slf4j
public class ChargeVariantDetector
{
	// Pattern for items with charges in parentheses: "Item name(X)" where X is a number
	// Also matches items with (i) for imbued, which should be kept as part of base name
	private static final Pattern CHARGE_PATTERN = Pattern.compile("^(.+?)\\((\\d+)\\)$");

	// Pattern for Barrows degradation: "Item name X" where X is 0, 25, 50, 75, or 100
	private static final Pattern BARROWS_PATTERN = Pattern.compile("^(.+?)\\s+(0|25|50|75|100)$");

	// Pattern for crystal equipment states: "Crystal X (new)" or "Crystal X (i)"
	private static final Pattern CRYSTAL_STATE_PATTERN = Pattern.compile("^(.+?)\\s+\\((new|inactive)\\)$");

	// Pattern for items ending with a number charge without parentheses (some special cases)
	private static final Pattern SUFFIX_NUMBER_PATTERN = Pattern.compile("^(.+?)\\s+(\\d+)$");

	/**
	 * Extract the base name from an item, removing charge/degradation indicators.
	 * Returns null if the item doesn't appear to be a charged variant.
	 *
	 * @param itemName The full item name
	 * @return The base name without charge info, or null if not a charged item
	 */
	@Nullable
	public static String extractBaseName(String itemName)
	{
		if (itemName == null || itemName.isEmpty())
		{
			return null;
		}

		// Try charge pattern first: "Item name(X)"
		Matcher chargeMatcher = CHARGE_PATTERN.matcher(itemName);
		if (chargeMatcher.matches())
		{
			return chargeMatcher.group(1).trim();
		}

		// Try Barrows pattern: "Item name X" where X is degradation percentage
		Matcher barrowsMatcher = BARROWS_PATTERN.matcher(itemName);
		if (barrowsMatcher.matches())
		{
			return barrowsMatcher.group(1).trim();
		}

		// Try crystal state pattern: "Item (new)" or "Item (inactive)"
		Matcher crystalMatcher = CRYSTAL_STATE_PATTERN.matcher(itemName);
		if (crystalMatcher.matches())
		{
			return crystalMatcher.group(1).trim();
		}

		return null;
	}

	/**
	 * Check if two item names are charge variants of each other.
	 *
	 * @param name1 First item name
	 * @param name2 Second item name
	 * @return true if they share the same base name (different charge states)
	 */
	public static boolean areChargeVariants(String name1, String name2)
	{
		if (name1 == null || name2 == null)
		{
			return false;
		}

		// If names are identical, they're the same item
		if (name1.equals(name2))
		{
			return false;
		}

		String base1 = extractBaseName(name1);
		String base2 = extractBaseName(name2);

		// If neither has a charge pattern, check if one is the base of the other
		if (base1 == null && base2 == null)
		{
			return false;
		}

		// If one has a base name and it matches the other's full name or base name
		if (base1 != null && base2 != null)
		{
			return base1.equalsIgnoreCase(base2);
		}

		// One is charged, one might be the uncharged base
		if (base1 != null)
		{
			return base1.equalsIgnoreCase(name2) || base1.equalsIgnoreCase(extractBaseOrSelf(name2));
		}
		if (base2 != null)
		{
			return base2.equalsIgnoreCase(name1) || base2.equalsIgnoreCase(extractBaseOrSelf(name1));
		}

		return false;
	}

	/**
	 * Extract base name or return the original name if no pattern matches.
	 */
	private static String extractBaseOrSelf(String itemName)
	{
		String base = extractBaseName(itemName);
		return base != null ? base : itemName;
	}

	/**
	 * Get a normalized base name for grouping purposes.
	 * This returns a consistent key that can be used to group charge variants.
	 *
	 * @param itemName The item name
	 * @return A normalized base name for grouping
	 */
	public static String getNormalizedBaseName(String itemName)
	{
		if (itemName == null)
		{
			return null;
		}

		String base = extractBaseName(itemName);
		if (base != null)
		{
			return base.toLowerCase();
		}

		// Return the original name lowercased for non-charged items
		return itemName.toLowerCase();
	}

	/**
	 * Check if an item name appears to be a charged/degradable item.
	 *
	 * @param itemName The item name to check
	 * @return true if the item appears to have charges or degradation state
	 */
	public static boolean isChargedItem(String itemName)
	{
		return extractBaseName(itemName) != null;
	}
}
