package com.bankslotsync;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LayoutEditsTest
{
	private static final int E = LayoutEdits.EMPTY;

	@Test
	public void insertIntoEmptyNeighbourMovesNothing()
	{
		int[] layout = {1, E, 3, 4};
		assertArrayEquals(new int[]{1, 9, 3, 4}, LayoutEdits.insertNextTo(layout, 0, 9));
	}

	@Test
	public void insertShiftsOnlyUpToFirstGap()
	{
		int[] layout = {1, 2, 3, E, 5, 6, 7, 8, 10};
		// 2 and 3 move right into the gap; 5..8 and the next row stay put
		assertArrayEquals(new int[]{1, 9, 2, 3, 5, 6, 7, 8, 10}, LayoutEdits.insertNextTo(layout, 0, 9));
	}

	@Test
	public void insertPastEndOfShortLayoutGrowsIt()
	{
		int[] layout = {1, 2};
		assertArrayEquals(new int[]{1, 2, 9}, LayoutEdits.insertNextTo(layout, 1, 9));
	}

	@Test
	public void fullRowUsesNextFreeSlotInsteadOfPushingItemsAway()
	{
		int[] layout = {1, 2, 3, 4, 5, 6, 7, 8, 11, E, 13};
		assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 11, 9, 13}, LayoutEdits.insertNextTo(layout, 2, 9));
	}

	@Test
	public void fullRowAndNoGapAppends()
	{
		int[] layout = {1, 2, 3, 4, 5, 6, 7, 8};
		assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, LayoutEdits.insertNextTo(layout, 3, 9));
	}

	@Test
	public void lastColumnDoesNotSpillIntoNextRow()
	{
		int[] layout = {1, 2, 3, 4, 5, 6, 7, 8, 10, E};
		// pos 7 is the end of the row; the item goes to the first free slot after it
		assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10, 9}, LayoutEdits.insertNextTo(layout, 7, 9));
	}

	@Test
	public void replaceAllUpdatesDuplicateSlots()
	{
		int[] layout = {5, 1, 5, E};
		assertEquals(2, LayoutEdits.replaceAll(layout, 5, 7));
		assertArrayEquals(new int[]{7, 1, 7, E}, layout);
		assertEquals(2, LayoutEdits.count(layout, 7));
	}
}
