package com.bankslotsync;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SyncMode
{
	AUTOMATIC("Automatic"),
	ASK_FIRST("Ask first"),
	OFF("Off");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
