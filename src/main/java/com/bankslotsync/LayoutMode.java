package com.bankslotsync;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LayoutMode
{
	REPLACE("Replace original slot"),
	ADJACENT("Place next to original");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
