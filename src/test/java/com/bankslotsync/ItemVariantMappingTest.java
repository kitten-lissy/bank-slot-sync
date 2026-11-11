package com.bankslotsync;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Data checks for {@link ItemVariantMapping}.
 *
 * <p>The mapping refers to every item through a {@code net.runelite.api.gameval.ItemID}
 * constant, so the compiler already guarantees each ID exists in the RuneLite API. This
 * test reads the source file to check the constant names: each one must contain one of
 * the keywords declared for its group (so a "Graceful legs" group can only hold
 * GRACEFUL_LEGS constants), and none may be a worn, noted, placeholder or broken copy.
 * It then checks at runtime that every ID belongs to exactly one group.
 */
public class ItemVariantMappingTest
{
	private static final Path SOURCE = Paths.get("src", "main", "java", "com", "bankslotsync", "ItemVariantMapping.java");

	private static final Pattern GROUP = Pattern.compile(
		"group\\(\\s*\"((?:[^\"\\\\]|\\\\.)*)\"\\s*,\\s*\"([^\"]*)\"\\s*,(.*?)\\);", Pattern.DOTALL);
	private static final Pattern CONSTANT = Pattern.compile("\\bItemID\\.([A-Za-z0-9_]+)");
	private static final Pattern LINE_COMMENT = Pattern.compile("//[^\\n]*");

	// Name fragments that mark copies which can never sit in a bank as themselves
	private static final String[] FORBIDDEN = {"_WORN", "CERT", "PLACEHOLDER", "DUMMY", "BROKEN", "MANGLED"};

	private static List<ParsedGroup> parsed;
	private static ItemVariantMapping mapping;

	private static final class ParsedGroup
	{
		final String label;
		final String[] keywords;
		final List<String> constants;

		ParsedGroup(String label, String[] keywords, List<String> constants)
		{
			this.label = label;
			this.keywords = keywords;
			this.constants = constants;
		}
	}

	@BeforeClass
	public static void load() throws IOException
	{
		String src = new String(Files.readAllBytes(SOURCE), StandardCharsets.UTF_8);
		int start = src.indexOf("private void initializeVariantGroups()");
		int end = src.indexOf("private void group(");
		assertTrue("could not find the group table", start >= 0 && end > start);
		String table = src.substring(start, end);

		parsed = new ArrayList<>();
		Matcher m = GROUP.matcher(table);
		while (m.find())
		{
			String body = LINE_COMMENT.matcher(m.group(3)).replaceAll("");
			List<String> constants = new ArrayList<>();
			Matcher c = CONSTANT.matcher(body);
			while (c.find())
			{
				constants.add(c.group(1));
			}
			parsed.add(new ParsedGroup(m.group(1), m.group(2).split("\\|"), constants));
		}

		mapping = new ItemVariantMapping();
	}

	@Test
	public void sourceParsesToSameGroupsAsRuntime()
	{
		assertEquals("groups parsed from source vs built at runtime", mapping.getGroupCount(), parsed.size());

		int constants = 0;
		for (ParsedGroup g : parsed)
		{
			constants += g.constants.size();
		}
		assertEquals("IDs parsed from source vs built at runtime", mapping.getItemCount(), constants);
	}

	@Test
	public void onlyPlainItemIdConstantsAreUsed()
	{
		for (ParsedGroup g : parsed)
		{
			for (String name : g.constants)
			{
				assertTrue(g.label + ": use ItemID.<NAME>, not a nested class: " + name,
					name.equals(name.toUpperCase()));
			}
		}
	}

	@Test
	public void constantNamesMatchTheirGroup()
	{
		List<String> problems = new ArrayList<>();
		for (ParsedGroup g : parsed)
		{
			assertTrue(g.label + ": a group needs keywords", g.keywords.length > 0 && !g.keywords[0].isEmpty());
			for (String name : g.constants)
			{
				boolean matches = false;
				for (String keyword : g.keywords)
				{
					if (name.contains(keyword))
					{
						matches = true;
						break;
					}
				}
				if (!matches)
				{
					problems.add(g.label + ": ItemID." + name + " does not contain any of " + String.join("|", g.keywords));
				}
				for (String bad : FORBIDDEN)
				{
					if (name.contains(bad))
					{
						problems.add(g.label + ": ItemID." + name + " is not a bankable item (" + bad + ")");
					}
				}
			}
		}
		if (!problems.isEmpty())
		{
			fail(String.join("\n", problems));
		}
	}

	@Test
	public void everyIdIsInExactlyOneGroup()
	{
		Map<Integer, Integer> seen = new HashMap<>();
		for (Set<Integer> group : mapping.getAllVariantGroups())
		{
			for (int id : group)
			{
				seen.merge(id, 1, Integer::sum);
			}
		}
		for (Map.Entry<Integer, Integer> e : seen.entrySet())
		{
			assertEquals("item " + e.getKey() + " appears in several groups", 1, (int) e.getValue());
		}

		Set<String> names = new HashSet<>();
		for (ParsedGroup g : parsed)
		{
			for (String name : g.constants)
			{
				assertTrue("ItemID." + name + " is listed twice", names.add(name));
			}
		}
	}

	@Test
	public void groupsAreSymmetricAndUseful()
	{
		for (Set<Integer> group : mapping.getAllVariantGroups())
		{
			assertTrue("a group needs at least two items: " + group, group.size() >= 2);
			for (int id : group)
			{
				assertEquals(group, mapping.getVariantGroup(id));
				assertTrue(id > 0);
			}
		}
		assertFalse(mapping.isVariant(-1));
	}

	@Test
	public void knownGroupsHoldTheRightItems()
	{
		// Purple slayer helmet used to point at a Hosidius teleport tablet (19651)
		assertTrue(mapping.areVariants(net.runelite.api.gameval.ItemID.SLAYER_HELM, net.runelite.api.gameval.ItemID.SLAYER_HELM_PURPLE));
		assertFalse(mapping.isVariant(19651));
		// Hallowed graceful top used to sit in the legs group
		assertTrue(mapping.areVariants(net.runelite.api.gameval.ItemID.GRACEFUL_TOP, net.runelite.api.gameval.ItemID.GRACEFUL_TOP_HALLOWED));
		assertFalse(mapping.areVariants(net.runelite.api.gameval.ItemID.GRACEFUL_LEGS, net.runelite.api.gameval.ItemID.GRACEFUL_TOP_HALLOWED));
		// Upgrade chain that core RuneLite does not link
		assertTrue(mapping.areVariants(net.runelite.api.gameval.ItemID.OATHPLATE_HELM, net.runelite.api.gameval.ItemID.RADIANT_OATHPLATE_HELM));
		// Pure essence (7936) used to be listed as a Ring of charos
		assertFalse(mapping.isVariant(7936));
	}
}
