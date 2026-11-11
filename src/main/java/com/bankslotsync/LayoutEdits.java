package com.bankslotsync;

import java.util.Arrays;

/**
 * Pure helpers for editing a bank tag layout, kept as plain int arrays so they can be
 * unit tested. A layout is a list of item IDs where the index is the slot and -1 means
 * an empty slot. Rows are {@link #ROW_WIDTH} slots wide.
 */
final class LayoutEdits
{
	static final int ROW_WIDTH = 8;
	static final int EMPTY = -1;

	private LayoutEdits()
	{
	}

	/**
	 * Put {@code itemId} in the slot to the right of {@code pos}.
	 *
	 * <p>Items between that slot and the first gap on the same row move one slot to the
	 * right. Nothing on other rows moves. If the row has no gap after {@code pos}, the item
	 * goes in the first empty slot after {@code pos}, or at the end of the layout.
	 *
	 * @return the new layout (a new array) and never null
	 */
	static int[] insertNextTo(int[] layout, int pos, int itemId)
	{
		int target = pos + 1;
		int rowEnd = (pos / ROW_WIDTH) * ROW_WIDTH + ROW_WIDTH - 1;

		int gap = -1;
		for (int i = target; i <= rowEnd; i++)
		{
			if (i >= layout.length || layout[i] == EMPTY)
			{
				gap = i;
				break;
			}
		}

		if (gap >= 0)
		{
			int[] out = Arrays.copyOf(layout, Math.max(layout.length, gap + 1));
			for (int i = layout.length; i < out.length; i++)
			{
				out[i] = EMPTY;
			}
			for (int i = gap; i > target; i--)
			{
				out[i] = out[i - 1];
			}
			out[target] = itemId;
			return out;
		}

		// Row is full: use the first empty slot after pos, or append
		for (int i = target; i < layout.length; i++)
		{
			if (layout[i] == EMPTY)
			{
				int[] out = Arrays.copyOf(layout, layout.length);
				out[i] = itemId;
				return out;
			}
		}
		int[] out = Arrays.copyOf(layout, layout.length + 1);
		out[layout.length] = itemId;
		return out;
	}

	/**
	 * Replace every slot holding {@code oldId} with {@code newId}.
	 *
	 * @return the number of slots changed
	 */
	static int replaceAll(int[] layout, int oldId, int newId)
	{
		int changed = 0;
		for (int i = 0; i < layout.length; i++)
		{
			if (layout[i] == oldId)
			{
				layout[i] = newId;
				changed++;
			}
		}
		return changed;
	}

	static int count(int[] layout, int itemId)
	{
		int n = 0;
		for (int id : layout)
		{
			if (id == itemId)
			{
				n++;
			}
		}
		return n;
	}
}
