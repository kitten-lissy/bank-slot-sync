# Bank Slot Sync

A RuneLite plugin that automatically syncs item variants (recolors, ornament kits, charged items, degraded equipment) to their corresponding Bank Tag Layout slots.

## The Problem

When you modify an item in Old School RuneScape, it often gets a completely new item ID:
- Adding an ornament kit to your Dragon scimitar
- Recoloring your Graceful outfit
- Charging/discharging jewelry
- Barrows equipment degrading
- Imbuing rings at NMZ or Soul Wars

This breaks your carefully organized Bank Tag Layouts because the layout system doesn't recognize the "new" item as belonging in the old slot. You end up with placeholders and items appearing at the end of your tabs.

## The Solution

Bank Slot Sync automatically detects when you acquire a variant of an item already in your layouts and updates the layout accordingly. It:

1. **Detects variant items** when they appear in your bank
2. **Copies tags** from the old item to the new variant
3. **Updates layouts** to place the new item in the correct position
4. **Removes old tags** to prevent placeholder clutter

## Important Usage Tip

**For best results, deposit items when you're NOT viewing a tag tab.** The plugin works most reliably when you deposit from the main bank view rather than while browsing a specific tag. The plugin will warn you if you deposit while in a tag view.

## Configuration Options

### Main Settings

| Option | Default | Description |
|--------|---------|-------------|
| **Enable Plugin** | On | Toggle the entire plugin on/off |
| **Layout Mode** | Replace | How to position the new variant (see below) |
| **Copy Tags to Variants** | On | Copy bank tags from old item to new variant |
| **Remove Tags from Old Item** | On | Remove tags from old item to prevent placeholders |
| **Show Chat Notifications** | On | Display messages when variants are synced |

### Layout Modes Explained

| Mode | Behavior |
|------|----------|
| **Replace** | New variant takes the exact position of the old item. Best for 1:1 item swaps. |
| **Adjacent** | New variant is inserted next to the old item. Only shifts items on the same row - other rows are unaffected. |

### Advanced Settings

| Option | Default | Description |
|--------|---------|-------------|
| **Detect Charge Variants** | On | Auto-detect charged items (jewelry, barrows, tridents) as variants |
| **Debug Logging** | Off | Show detailed debug messages in chat for troubleshooting |

## How It Works

### Scenario 1: Swapping Items (Most Common)
You remove the old item and deposit the new variant:
1. Plugin detects the new variant in your bank
2. Finds the old variant's position in your tag layouts
3. Places the new item based on your Layout Mode setting
4. Optionally copies tags and removes old item's tags

### Scenario 2: Both Items in Bank
You have both the old and new variant in your bank:
- **Replace Mode**: No action (keeps your existing layout)
- **Adjacent Mode**: Inserts new variant next to the original

## Supported Item Variants

The plugin includes **300+ variant groups** covering thousands of items:

### Cosmetic Variants
- **Graceful Outfit** - All 11 recolors (Default, Arceuus, Piscarilius, Lovakengj, Shayzien, Hosidius, Kourend, Agility Arena, Hallowed, Trailblazer, Varlamore)
- **Slayer Helmets** - All colors (Black, Green, Red, Purple, Turquoise, Hydra, Twisted, Tztok, Vampyric, Tzkal) + all imbue sources
- **Void Knight Equipment** - Regular, Elite, and all ornament kit variants
- **Infinity Robes** - Regular, Dark, and Light variants
- **Ancestral Robes** - Regular and Twisted variants

### Ornament Kits
- **Godswords** - All (or) variants (AGS, BGS, SGS, ZGS, Ancient)
- **Dragon Equipment** - Scimitar, defender, boots, chainbody, platebody, full helm, platelegs, plateskirt, sq shield, kiteshield
- **Jewelry** - Fury, Torture, Anguish, Tormented bracelet, Occult necklace, Berserker necklace
- **Weapons** - Granite maul, Steam/Lava battlestaff, Odium/Malediction ward, and many more
- **Theatre of Blood** - Holy/Sanguine Scythe, Holy Sanguinesti staff, Holy Ghrazi rapier
- **Tombs of Amascut** - Osmumten's fang (or), Elidinis' ward (or), Masori (f) variants
- **GWD Armour** - Bandos, Armadyl, and all (or) variants
- **Spirit Shields** - Spectral, Arcane, Elysian + (or) variants

### Charged/Uncharged Items
- **Teleport Jewelry** - All charge levels for: Ring of dueling, Combat bracelet, Skills necklace, Games necklace, Amulet of glory, Necklace of passage, Burning amulet, Digsite pendant, Ring of returning
- **Barrows Equipment** - All degradation states (0/25/50/75/100) for all 6 brothers
- **Tridents** - Seas and Swamp variants, charged and uncharged
- **Blowpipe** - Empty, charged, and Blazing variants
- **Serpentine Helms** - Regular, Tanzanite, Magma (charged and uncharged)
- **Dragonfire Shields** - DFS, Ward, Ancient Wyvern (charged and uncharged)
- **Tomes** - Fire and Water (empty and charged)

### Tools
- **Dragon Pickaxe** - Regular, (or), Infernal, Crystal, Gilded, Trailblazer variants
- **Dragon Axe** - Regular, (or), Infernal, Crystal, Gilded, Trailblazer variants
- **Dragon Harpoon** - Regular, (or), Infernal, Crystal, Trailblazer variants

### Rings
- **Fremennik Rings** - Berserker, Archers, Seers, Warrior + all imbue sources
- **Wilderness Rings** - Treasonous, Tyrannical, Ring of the gods + imbued
- **Ring of Suffering** - Regular, imbued, recoil variants
- **Slayer Rings** - All 8 charge levels

### Achievement Diary Items
- All 12 regions with tiers 1-4: Explorer's ring, Ardougne cloak, Desert amulet, Falador shield, Fremennik sea boots, Kandarin headgear, Karamja gloves, Morytania legs, Varrock armour, Western banner, Wilderness sword, Rada's blessing

### Leagues Rewards
- **Relic Hunter Outfits** - All 5 leagues (Twisted, Trailblazer, Shattered, Reloaded, Raging Echoes) with T1/T2/T3 variants
- **Canes & Banners** - All league variants
- **Tool Ornament Kits** - Trailblazer variants
- **Shattered Relics** - Void, Mystic, Cannon ornament kits
- **Raging Echoes** - Echo Ahrim's, Echo Virtus, Echo Venator bow

### Castle Wars
- **Decorative Armour** - Red, White, and Gold tiers for all pieces (melee, magic, ranged)

### Shayzien Armour
- All 5 tiers for helm, body, greaves, gloves, boots

### Treasure Trails
- **Trimmed Armour** - Bronze through Rune (t) and (g) variants
- **God Armour** - All 6 gods (Saradomin, Zamorak, Guthix, Ancient, Armadyl, Bandos)
- **Gilded Armour** - Full set including weapons and tools
- **3rd Age** - Melee, Range, Mage, Druidic sets + weapons and tools
- **Blessed D'hide** - All 6 god variants
- **Heraldic Armour** - H1-H5 for Black, Adamant, Rune

### Misc
- **Max Capes** - All god variants, Assembler, Infernal, Mythical
- **God Capes** - Regular and Imbued (Sara, Zammy, Guthix)
- **Black Masks** - All charge levels + imbued variants
- **Crystal Equipment** - Bow, Shield, Halberd (active/inactive/new)
- **Blade of Saeldor / Bow of Faerdhinen** - All color variants
- **Holiday Items** - Partyhats, H'ween masks, Santa hats

### Auto-Detected Charge Variants

The plugin can also automatically detect charge variants by pattern matching item names, including:
- Items with `(1)` through `(8)` charges
- Items with `(full)`, `(empty)`, `(charged)`, `(uncharged)`
- Barrows degradation percentages

## Installation

### Plugin Hub (Recommended)
*Coming soon* - Submit to RuneLite Plugin Hub

### Manual Installation
1. Clone or download this repository
2. Build with `./gradlew build`
3. Copy the JAR from `build/libs/` to your RuneLite plugins folder

## Building from Source

```bash
# Build the plugin
./gradlew build

# Run RuneLite with the plugin (development)
./gradlew run
```

## Compatibility

- Works with the built-in **Bank Tags** plugin
- Works with the external **Bank Tag Layouts** plugin
- Automatically detects which layout format is being used

## Troubleshooting

**Variants not being detected?**
- Enable "Debug Logging" in the Advanced settings
- Check the chat for debug messages
- Make sure the item is in our variant mapping (open an issue if missing)

**Layout not updating correctly?**
- Deposit items from the main bank view, not while viewing a tag tab
- The plugin waits briefly before updating to avoid conflicts with Bank Tags

**Old item showing as placeholder?**
- Make sure "Remove Tags from Old Item" is enabled
- This removes the old item's tags so it won't appear in tag tabs

**Layout not updating at all?**
- Make sure you have a layout enabled for the tag tab
- The plugin only updates existing layouts, it doesn't create new ones

## Contributing

Found an item variant that's not supported? Please open an issue with:
- The item names (old and new)
- The item IDs if known (can find on OSRS Wiki)

## License

BSD 2-Clause License - see LICENSE file for details.

## Credits

- Item IDs sourced from the [Old School RuneScape Wiki](https://oldschool.runescape.wiki)
- Built for [RuneLite](https://runelite.net)
