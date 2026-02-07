package com.bankslotsync;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LayoutMode
{
	REPLACE("Replace original slot", "Place the variant in the exact same position as the original item"),
	ADJACENT("Place next to original", "Place the variant in the next available slot near the original item");

	private final String name;
	private final String description;

	@Override
	public String toString()
	{
		return name;
	}
}
