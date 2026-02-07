package com.bankslotsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for parsing and manipulating Bank Tag Layout strings.
 *
 * Supports two formats:
 * 1. Built-in Bank Tags: "itemId,itemId,itemId,..." (array index = position)
 * 2. External Bank Tag Layouts plugin: "position:itemId,position:itemId,..." (explicit positions)
 */
@Slf4j
public class LayoutParser
{
	private static final int EMPTY_SLOT = -1;

	/**
	 * Represents a parsed layout with position -> itemId mapping.
	 */
	public static class Layout
	{
		private final Map<Integer, Integer> positionToItem = new HashMap<>();
		private final boolean isExternalFormat;

		public Layout(boolean isExternalFormat)
		{
			this.isExternalFormat = isExternalFormat;
		}

		public void setItem(int position, int itemId)
		{
			if (itemId > 0)
			{
				positionToItem.put(position, itemId);
			}
			else
			{
				positionToItem.remove(position);
			}
		}

		public int getItem(int position)
		{
			return positionToItem.getOrDefault(position, EMPTY_SLOT);
		}

		public boolean containsItem(int itemId)
		{
			return positionToItem.containsValue(itemId);
		}

		public int findItemPosition(int itemId)
		{
			for (Map.Entry<Integer, Integer> entry : positionToItem.entrySet())
			{
				if (entry.getValue() == itemId)
				{
					return entry.getKey();
				}
			}
			return -1;
		}

		/**
		 * Insert an item at a position, shifting all items at and after that position down by 1.
		 */
		public void insertAt(int position, int itemId)
		{
			// Find the max position to know how far to shift
			int maxPos = positionToItem.keySet().stream().max(Integer::compare).orElse(-1);

			log.debug("insertAt called: position={}, itemId={}, maxPos={}, before={}",
				position, itemId, maxPos, positionToItem);

			// Shift all items from maxPos down to position, moving each one position higher
			// We iterate from high to low so we don't overwrite values we need to read
			for (int i = maxPos; i >= position; i--)
			{
				int item = positionToItem.getOrDefault(i, EMPTY_SLOT);
				if (item > 0)
				{
					positionToItem.put(i + 1, item);
					// Only remove from old position if it's not the target position
					// (target position will be overwritten by the new item)
					if (i != position)
					{
						positionToItem.remove(i);
					}
				}
			}

			// Now insert the new item at the target position
			positionToItem.put(position, itemId);

			log.debug("insertAt result: after={}", positionToItem);
		}

		public int findVariantPosition(Set<Integer> variantGroup)
		{
			for (Map.Entry<Integer, Integer> entry : positionToItem.entrySet())
			{
				if (variantGroup.contains(entry.getValue()))
				{
					return entry.getKey();
				}
			}
			return -1;
		}

		public List<Integer> findAllVariantPositions(Set<Integer> variantGroup)
		{
			List<Integer> positions = new ArrayList<>();
			for (Map.Entry<Integer, Integer> entry : positionToItem.entrySet())
			{
				if (variantGroup.contains(entry.getValue()))
				{
					positions.add(entry.getKey());
				}
			}
			return positions;
		}

		public boolean isExternalFormat()
		{
			return isExternalFormat;
		}

		public Map<Integer, Integer> getPositionToItem()
		{
			return positionToItem;
		}

		public int size()
		{
			return positionToItem.size();
		}

		/**
		 * Insert an item at a position, shifting only items on the SAME ROW.
		 * Bank rows are 8 items wide (0-7, 8-15, 16-23, etc.).
		 * If an item gets pushed off the end of the row, it goes to the end of the layout.
		 */
		public void insertAtSameRow(int position, int itemId)
		{
			final int ROW_WIDTH = 8;
			int row = position / ROW_WIDTH;
			int rowStart = row * ROW_WIDTH;
			int rowEnd = rowStart + ROW_WIDTH - 1;

			log.debug("insertAtSameRow: position={}, row={}, rowStart={}, rowEnd={}",
				position, row, rowStart, rowEnd);

			// Check if position+1 is empty - if so, just place there
			if (!positionToItem.containsKey(position) || positionToItem.get(position) <= 0)
			{
				positionToItem.put(position, itemId);
				log.debug("Position {} was empty, placed item directly", position);
				return;
			}

			// Need to shift items on this row only
			// First, check if there's an item at the end of the row that will be displaced
			int displacedItem = -1;
			if (positionToItem.containsKey(rowEnd) && positionToItem.get(rowEnd) > 0)
			{
				displacedItem = positionToItem.get(rowEnd);
				log.debug("Item {} at row end (pos {}) will be displaced", displacedItem, rowEnd);
			}

			// Shift items from rowEnd down to position (within the same row only)
			for (int i = rowEnd; i > position; i--)
			{
				int prevPos = i - 1;
				if (prevPos >= position)
				{
					int item = positionToItem.getOrDefault(prevPos, EMPTY_SLOT);
					if (item > 0)
					{
						positionToItem.put(i, item);
						positionToItem.remove(prevPos);
					}
					else
					{
						positionToItem.remove(i);
					}
				}
			}

			// Place the new item at the target position
			positionToItem.put(position, itemId);

			// If an item was displaced from the end of the row, append it to the layout
			if (displacedItem > 0)
			{
				int maxPos = positionToItem.keySet().stream().max(Integer::compare).orElse(0);
				positionToItem.put(maxPos + 1, displacedItem);
				log.debug("Displaced item {} moved to position {}", displacedItem, maxPos + 1);
			}

			log.debug("insertAtSameRow result: {}", positionToItem);
		}

		/**
		 * Serialize the layout back to a string.
		 */
		public String serialize()
		{
			if (positionToItem.isEmpty())
			{
				return "";
			}

			StringBuilder sb = new StringBuilder();

			if (isExternalFormat)
			{
				// External format: "position:itemId,position:itemId,..."
				boolean first = true;
				for (Map.Entry<Integer, Integer> entry : positionToItem.entrySet())
				{
					if (!first)
					{
						sb.append(',');
					}
					// Format is itemId:position (based on the wiki source showing "integerIntegerEntry.getValue() + ":" + integerIntegerEntry.getKey()")
					sb.append(entry.getValue()).append(':').append(entry.getKey());
					first = false;
				}
			}
			else
			{
				// Built-in format: "itemId,itemId,itemId,..."
				int maxPos = positionToItem.keySet().stream().max(Integer::compare).orElse(-1);
				for (int i = 0; i <= maxPos; i++)
				{
					if (i > 0)
					{
						sb.append(',');
					}
					sb.append(positionToItem.getOrDefault(i, EMPTY_SLOT));
				}
			}

			return sb.toString();
		}
	}

	/**
	 * Detect if a layout string is in external format (contains colons).
	 */
	public static boolean isExternalFormat(String layoutStr)
	{
		return layoutStr != null && layoutStr.contains(":");
	}

	/**
	 * Parse a layout string into a Layout object.
	 */
	public static Layout parseLayout(String layoutStr)
	{
		if (layoutStr == null || layoutStr.isEmpty())
		{
			return new Layout(false);
		}

		boolean external = isExternalFormat(layoutStr);
		Layout layout = new Layout(external);

		String[] parts = layoutStr.split(",");

		for (int i = 0; i < parts.length; i++)
		{
			String part = parts[i].trim();
			if (part.isEmpty())
			{
				continue;
			}

			try
			{
				if (external)
				{
					// External format: "itemId:position"
					String[] pair = part.split(":");
					if (pair.length == 2)
					{
						int itemId = Integer.parseInt(pair[0].trim());
						int position = Integer.parseInt(pair[1].trim());
						if (position >= 0 && itemId > 0)
						{
							layout.setItem(position, itemId);
						}
					}
				}
				else
				{
					// Built-in format: array index is position
					int itemId = Integer.parseInt(part);
					if (itemId > 0)
					{
						layout.setItem(i, itemId);
					}
				}
			}
			catch (NumberFormatException e)
			{
				log.warn("Invalid layout entry: {}", part);
			}
		}

		log.debug("Parsed layout ({} format): {} items", external ? "external" : "built-in", layout.size());
		return layout;
	}

	// Legacy methods for compatibility - convert to/from int[] for existing code

	/**
	 * Parse a layout string into an array of item IDs (legacy method).
	 */
	public static int[] parseLayoutArray(String layoutStr)
	{
		Layout layout = parseLayout(layoutStr);
		if (layout.size() == 0)
		{
			return new int[0];
		}

		int maxPos = layout.getPositionToItem().keySet().stream().max(Integer::compare).orElse(-1);
		int[] arr = new int[maxPos + 1];
		for (int i = 0; i <= maxPos; i++)
		{
			arr[i] = layout.getItem(i);
		}
		return arr;
	}

	/**
	 * Find the position of any item from the variant group in the layout.
	 * Returns -1 if no variant is found in the layout.
	 */
	public static int findVariantPosition(int[] layout, Set<Integer> variantGroup)
	{
		if (layout == null || variantGroup == null)
		{
			return -1;
		}

		for (int i = 0; i < layout.length; i++)
		{
			if (variantGroup.contains(layout[i]))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Check if a specific item ID exists in the layout.
	 */
	public static boolean containsItem(int[] layout, int itemId)
	{
		if (layout == null)
		{
			return false;
		}

		for (int id : layout)
		{
			if (id == itemId)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Replace the item at a specific position with a new item ID.
	 * Returns a new layout array.
	 */
	public static int[] replaceAtPosition(int[] layout, int position, int newItemId)
	{
		if (layout == null || position < 0 || position >= layout.length)
		{
			return layout;
		}

		int[] newLayout = new int[layout.length];
		System.arraycopy(layout, 0, newLayout, 0, layout.length);
		newLayout[position] = newItemId;
		return newLayout;
	}

	/**
	 * Find the first empty slot near a given position (for ADJACENT mode).
	 */
	public static int findNearestEmptySlot(int[] layout, int referencePosition)
	{
		if (layout == null || referencePosition < 0)
		{
			return -1;
		}

		int maxDistance = Math.max(referencePosition, layout.length - referencePosition);

		for (int distance = 1; distance <= maxDistance; distance++)
		{
			int posAfter = referencePosition + distance;
			if (posAfter < layout.length && layout[posAfter] == EMPTY_SLOT)
			{
				return posAfter;
			}

			int posBefore = referencePosition - distance;
			if (posBefore >= 0 && layout[posBefore] == EMPTY_SLOT)
			{
				return posBefore;
			}
		}

		return -1;
	}

	/**
	 * Append an item to the layout.
	 */
	public static int[] appendItem(int[] layout, int itemId)
	{
		if (layout == null)
		{
			return new int[] { itemId };
		}

		// First try to find an empty slot
		for (int i = 0; i < layout.length; i++)
		{
			if (layout[i] == EMPTY_SLOT)
			{
				int[] newLayout = new int[layout.length];
				System.arraycopy(layout, 0, newLayout, 0, layout.length);
				newLayout[i] = itemId;
				return newLayout;
			}
		}

		// No empty slots, append to end
		int[] newLayout = new int[layout.length + 1];
		System.arraycopy(layout, 0, newLayout, 0, layout.length);
		newLayout[layout.length] = itemId;
		return newLayout;
	}
}
