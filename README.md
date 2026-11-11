# Bank Slot Sync

A RuneLite plugin that keeps your Bank Tag layouts and tags in step when you swap an item for another version of it: a recolour, an ornament kit, an imbue, a locked (l) copy, or an upgrade such as Oathplate to Radiant oathplate.

## What it does

Many items get a new item ID when you change them. RuneLite's Bank Tags already draws a lot of recolours in the slot of the original item, but the saved layout and the item's tags still point at the old ID. Bank Slot Sync fixes that when the new version reaches your bank:

1. It notices a new version of an item it knows about.
2. If the old version is gone (not in your bank, inventory or equipment), it gives the new version the old one's bank tags and puts it in the old one's layout slot.
3. It posts one chat message saying what changed, for example: `Bank Slot Sync: Hallowed graceful top replaced Graceful top in agility, skilling. Type ::bss undo to revert.`

It leaves a layout alone when:

- the new item is already somewhere in that layout (for example a tab that holds every slayer helmet colour on purpose),
- the tab is on your "Never Touch These Tabs" list,
- you still have the old version in your bank, inventory or equipment (unless you ask it to insert next to it).

The first time you open your bank after logging in or hopping, the plugin only records what is there. It never treats your whole bank as new.

## Chat commands

| Command | What it does |
|--------|--------------|
| `::bss undo` | Reverts the last sync (layouts and tags). Repeat to go further back, up to 10 syncs. |
| `::bss apply` | In "Ask first" mode, applies the change the plugin offered. |

These commands stay in your client and are never sent to the game.

## Settings

### General

| Option | Default | What it does |
|--------|---------|-------------|
| **Sync Mode** | Automatic | Automatic, Ask first (post a chat message and wait for `::bss apply`), or Off |
| **Layout Mode** | Replace original slot | Replace original slot, or Place next to original (shifts that row only up to the first gap) |
| **Copy Tags to New Version** | On | Gives the new version the tags the old one had |
| **Remove Tags from Old Item** | Off | Takes the copied tags off the old version. Other tags stay. |
| **Never Touch These Tabs** | (empty) | Comma-separated tab names the plugin must not change |
| **Show Chat Notifications** | On | One chat message per sync |

### Advanced

| Option | Default | What it does |
|--------|---------|-------------|
| **Insert When You Keep Both** | Off | With Replace, still insert the new version next to the old one when you have both |
| **Detect Charge Variants** | Off | Also treat items RuneLite counts as the same item with different charges or degradation (jewellery charges, Barrows degradation) as versions of each other. Potions are always ignored. |

## Supported items

Around 380 groups and 1,460 item IDs. Every ID is written as a RuneLite `ItemID` constant and checked by a unit test, so a group can only hold the item it is named after. Highlights:

- **Graceful**: all 12 versions of each piece (default, the five Kourend houses, Kourend, Agility Arena, Hallowed, Trailblazer, Adventurer, Varlamore)
- **Slayer helmets**: every colour, including Araxyte, Hooded, Oathplate and Radiant, with all imbued copies (Nightmare Zone, Soul Wars, Emir's Arena)
- **Upgrade chains**: Oathplate to Radiant oathplate, Torva to Sanguine torva, Amulet of fury to Amulet of blood fury, Void to Elite void, Ava's assembler to Masori assembler, Dizana's quiver to Blessed Dizana's quiver, Fire cape to Infernal cape and their max capes, Rune pouch to Divine rune pouch
- **Ornament kits**: godswords, dragon armour (g), zenyte jewellery (or), Bandos and Armadyl (or), Elder chaos (or), Soulreaper axe (o), Iban's staff (o), tridents (o), Abyssal whip and tentacle (or), and more
- **Charges and degradation**: teleport jewellery, Barrows, tridents, blowpipes (Toxic, Blazing, Drygore, Ironwood, Rosewood, Camphor), Serpentine helms, Scythe of vitur, Sanguinesti staff
- **Locked (l) copies** of Void, Fighter torso, defenders, capes and more
- **Crystal armour** with all elven clan recolours
- **Treasure Trails**: trimmed and gold-trimmed armour, god and gilded rune armour, heraldic helms and shields, trimmed dragonhide and wizard robes
- **Leagues**: relic hunter outfits for each league (one group per piece, tiers 1 to 3), league canes and banners
- **Castle Wars** decorative armour, **Shayzien** armour tiers, achievement diary items, skill capes and their trimmed versions

Groups only hold versions that go in the same slot. A Saradomin d'hide body and Saradomin chaps are never treated as versions of each other.

## Compatibility

Works with RuneLite's built-in **Bank Tags** plugin, using only the services it shares with other plugins. Layout and tag changes go through Bank Tags itself, so they also work while a tag tab is open.

## Troubleshooting

**A swap was not picked up.**
Make sure the old version was not still in your inventory or equipment when you deposited the new one. If the item is missing from the list, please open an issue with both item names.

**Something moved that should not have.**
Type `::bss undo`. Add the tab to "Never Touch These Tabs" if you want the plugin to leave it alone.

**Nothing changes at all.**
Check that Sync Mode is not Off and that the tab has a layout. The plugin only edits existing layouts; it never creates one.

## Building from source

```bash
./gradlew build
```

## License

BSD 2-Clause License, see the LICENSE file.

## Credits

- Item names checked against the [Old School RuneScape Wiki](https://oldschool.runescape.wiki)
- Built for [RuneLite](https://runelite.net)
