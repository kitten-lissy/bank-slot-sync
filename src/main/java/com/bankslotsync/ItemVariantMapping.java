package com.bankslotsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Singleton;

/**
 * Maintains mappings of item variants to their base items.
 * A variant group contains all item IDs that represent the same "logical" item
 * (e.g., all graceful top recolors, all slayer helm variants).
 *
 * Item IDs sourced from: https://oldschool.runescape.wiki
 */
@Singleton
public class ItemVariantMapping
{
	// Maps each item ID to its variant group (all IDs in the same group)
	private final Map<Integer, Set<Integer>> itemToGroup = new HashMap<>();

	// List of all variant groups for reference
	private final List<Set<Integer>> variantGroups = new ArrayList<>();

	public ItemVariantMapping()
	{
		initializeVariantGroups();
	}

	private void initializeVariantGroups()
	{
		// ==================== GRACEFUL OUTFIT ====================

		// Graceful Hood variants
		addVariantGroup(
			11850,  // Graceful hood (default)
			13579,  // Graceful hood (Arceuus)
			13591,  // Graceful hood (Piscarilius)
			13603,  // Graceful hood (Lovakengj)
			13615,  // Graceful hood (Shayzien)
			13627,  // Graceful hood (Hosidius)
			13667,  // Graceful hood (Kourend)
			21061,  // Graceful hood (Agility Arena)
			24743,  // Graceful hood (Hallowed)
			25069,  // Graceful hood (Trailblazer)
			30045   // Graceful hood (Varlamore)
		);

		// Graceful Cape variants
		addVariantGroup(
			11852,  // Graceful cape (default)
			13581,  // Graceful cape (Arceuus)
			13593,  // Graceful cape (Piscarilius)
			13605,  // Graceful cape (Lovakengj)
			13617,  // Graceful cape (Shayzien)
			13629,  // Graceful cape (Hosidius)
			13669,  // Graceful cape (Kourend)
			21063,  // Graceful cape (Agility Arena)
			24745,  // Graceful cape (Hallowed)
			25071,  // Graceful cape (Trailblazer)
			30047   // Graceful cape (Varlamore)
		);

		// Graceful Top variants
		addVariantGroup(
			11854,  // Graceful top (default)
			13583,  // Graceful top (Arceuus)
			13595,  // Graceful top (Piscarilius)
			13607,  // Graceful top (Lovakengj)
			13619,  // Graceful top (Shayzien)
			13631,  // Graceful top (Hosidius)
			13671,  // Graceful top (Kourend)
			21065,  // Graceful top (Agility Arena)
			24747,  // Graceful top (Hallowed)
			25073,  // Graceful top (Trailblazer)
			30049   // Graceful top (Varlamore)
		);

		// Graceful Legs variants
		addVariantGroup(
			11856,  // Graceful legs (default)
			13585,  // Graceful legs (Arceuus)
			13597,  // Graceful legs (Piscarilius)
			13609,  // Graceful legs (Lovakengj)
			13621,  // Graceful legs (Shayzien)
			13633,  // Graceful legs (Hosidius)
			13673,  // Graceful legs (Kourend)
			21067,  // Graceful legs (Agility Arena)
			24749,  // Graceful legs (Hallowed)
			25075,  // Graceful legs (Trailblazer)
			30051   // Graceful legs (Varlamore)
		);

		// Graceful Gloves variants
		addVariantGroup(
			11858,  // Graceful gloves (default)
			13587,  // Graceful gloves (Arceuus)
			13599,  // Graceful gloves (Piscarilius)
			13611,  // Graceful gloves (Lovakengj)
			13623,  // Graceful gloves (Shayzien)
			13635,  // Graceful gloves (Hosidius)
			13675,  // Graceful gloves (Kourend)
			21069,  // Graceful gloves (Agility Arena)
			24751,  // Graceful gloves (Hallowed)
			25077,  // Graceful gloves (Trailblazer)
			30053   // Graceful gloves (Varlamore)
		);

		// Graceful Boots variants
		addVariantGroup(
			11860,  // Graceful boots (default)
			13589,  // Graceful boots (Arceuus)
			13601,  // Graceful boots (Piscarilius)
			13613,  // Graceful boots (Lovakengj)
			13625,  // Graceful boots (Shayzien)
			13637,  // Graceful boots (Hosidius)
			13677,  // Graceful boots (Kourend)
			21071,  // Graceful boots (Agility Arena)
			24753,  // Graceful boots (Hallowed)
			25079,  // Graceful boots (Trailblazer)
			30055   // Graceful boots (Varlamore)
		);

		// ==================== SLAYER HELMETS ====================

		// Slayer Helmet (all colors and imbued variants)
		addVariantGroup(
			11864,  // Slayer helmet
			11865,  // Slayer helmet (i)
			19639,  // Black slayer helmet
			19641,  // Black slayer helmet (i)
			19643,  // Green slayer helmet
			19645,  // Green slayer helmet (i)
			19647,  // Red slayer helmet
			19649,  // Red slayer helmet (i)
			19651,  // Purple slayer helmet
			19653,  // Purple slayer helmet (i)
			21264,  // Purple slayer helmet (wrong ID in original, this is correct)
			21266,  // Purple slayer helmet (i)
			21888,  // Turquoise slayer helmet
			21890,  // Turquoise slayer helmet (i)
			23073,  // Hydra slayer helmet
			23075,  // Hydra slayer helmet (i)
			24370,  // Twisted slayer helmet
			24444,  // Twisted slayer helmet (i)
			25898,  // Tztok slayer helmet
			25900,  // Tztok slayer helmet (i)
			25904,  // Vampyric slayer helmet
			25906,  // Vampyric slayer helmet (i)
			25910,  // Tzkal slayer helmet
			25912,  // Tzkal slayer helmet (i)
			// Soul Wars imbued versions
			25177,  // Slayer helmet (i) - Soul Wars
			25179,  // Black slayer helmet (i) - Soul Wars
			25181,  // Green slayer helmet (i) - Soul Wars
			25183,  // Red slayer helmet (i) - Soul Wars
			25185,  // Purple slayer helmet (i) - Soul Wars
			25187,  // Turquoise slayer helmet (i) - Soul Wars
			25189,  // Hydra slayer helmet (i) - Soul Wars
			25191,  // Twisted slayer helmet (i) - Soul Wars
			// Emir's Arena imbued versions
			26674,  // Slayer helmet (i) - Emir's Arena
			26675,  // Black slayer helmet (i) - Emir's Arena
			26676,  // Green slayer helmet (i) - Emir's Arena
			26677,  // Red slayer helmet (i) - Emir's Arena
			26678,  // Purple slayer helmet (i) - Emir's Arena
			26679,  // Turquoise slayer helmet (i) - Emir's Arena
			26680,  // Hydra slayer helmet (i) - Emir's Arena
			26681,  // Twisted slayer helmet (i) - Emir's Arena
			26682,  // Tztok slayer helmet (i) - Emir's Arena
			26683   // Vampyric slayer helmet (i) - Emir's Arena
		);

		// ==================== GODSWORDS ====================

		// Armadyl godsword variants
		addVariantGroup(
			11802,  // Armadyl godsword
			20368   // Armadyl godsword (or)
		);

		// Bandos godsword variants
		addVariantGroup(
			11804,  // Bandos godsword
			20370   // Bandos godsword (or)
		);

		// Saradomin godsword variants
		addVariantGroup(
			11806,  // Saradomin godsword
			20372   // Saradomin godsword (or)
		);

		// Zamorak godsword variants
		addVariantGroup(
			11808,  // Zamorak godsword
			20374   // Zamorak godsword (or)
		);

		// ==================== JEWELRY ====================

		// Amulet of fury variants
		addVariantGroup(
			6585,   // Amulet of fury
			12436   // Amulet of fury (or)
		);

		// Amulet of torture variants
		addVariantGroup(
			19553,  // Amulet of torture
			20366   // Amulet of torture (or)
		);

		// Necklace of anguish variants
		addVariantGroup(
			19547,  // Necklace of anguish
			22249   // Necklace of anguish (or)
		);

		// Tormented bracelet variants
		addVariantGroup(
			19544,  // Tormented bracelet
			23444   // Tormented bracelet (or)
		);

		// Occult necklace variants
		addVariantGroup(
			12002,  // Occult necklace
			19720   // Occult necklace (or)
		);

		// Berserker necklace variants
		addVariantGroup(
			11128,  // Berserker necklace
			23240   // Berserker necklace (or)
		);

		// ==================== RINGS ====================

		// Berserker ring variants
		addVariantGroup(
			6737,   // Berserker ring
			11773,  // Berserker ring (i)
			27306   // Berserker ring (i) - Soul Wars
		);

		// Archers ring variants
		addVariantGroup(
			6733,   // Archers ring
			11771,  // Archers ring (i)
			27304   // Archers ring (i) - Soul Wars
		);

		// Seers ring variants
		addVariantGroup(
			6731,   // Seers ring
			11770,  // Seers ring (i)
			27303   // Seers ring (i) - Soul Wars
		);

		// Warrior ring variants
		addVariantGroup(
			6735,   // Warrior ring
			11772,  // Warrior ring (i)
			27305   // Warrior ring (i) - Soul Wars
		);

		// Treasonous ring variants
		addVariantGroup(
			12601,  // Treasonous ring
			12694,  // Treasonous ring (i)
			27308   // Treasonous ring (i) - Soul Wars
		);

		// Tyrannical ring variants
		addVariantGroup(
			12603,  // Tyrannical ring
			12692,  // Tyrannical ring (i)
			27307   // Tyrannical ring (i) - Soul Wars
		);

		// Ring of the gods variants
		addVariantGroup(
			12605,  // Ring of the gods
			13202,  // Ring of the gods (i)
			27309   // Ring of the gods (i) - Soul Wars
		);

		// Ring of wealth variants
		addVariantGroup(
			2572,   // Ring of wealth
			12785   // Ring of wealth (i)
		);

		// ==================== DRAGON EQUIPMENT ====================

		// Dragon scimitar variants
		addVariantGroup(
			4587,   // Dragon scimitar
			20000   // Dragon scimitar (or)
		);

		// Dragon defender variants
		addVariantGroup(
			12954,  // Dragon defender
			19722   // Dragon defender (t)
		);

		// Rune defender variants
		addVariantGroup(
			8850,   // Rune defender
			23230   // Rune defender (t)
		);

		// Dragon boots variants
		addVariantGroup(
			11840,  // Dragon boots
			22234   // Dragon boots (g)
		);

		// Dragon chainbody variants
		addVariantGroup(
			3140,   // Dragon chainbody
			12414   // Dragon chainbody (g)
		);

		// Dragon platebody variants
		addVariantGroup(
			21892,  // Dragon platebody
			22242   // Dragon platebody (g)
		);

		// Dragon full helm variants
		addVariantGroup(
			11335,  // Dragon full helm
			12417   // Dragon full helm (g)
		);

		// Dragon platelegs variants
		addVariantGroup(
			4087,   // Dragon platelegs
			12415   // Dragon platelegs (g)
		);

		// Dragon plateskirt variants
		addVariantGroup(
			4585,   // Dragon plateskirt
			12416   // Dragon plateskirt (g)
		);

		// Dragon sq shield variants
		addVariantGroup(
			1187,   // Dragon sq shield
			12418   // Dragon sq shield (g)
		);

		// Dragon kiteshield variants
		addVariantGroup(
			22002,  // Dragon kiteshield
			22244   // Dragon kiteshield (g)
		);

		// ==================== ABYSSAL WHIP ====================

		addVariantGroup(
			4151,   // Abyssal whip
			12773,  // Volcanic abyssal whip
			12774   // Frozen abyssal whip
		);

		// ==================== DARK BOW ====================

		addVariantGroup(
			11235,  // Dark bow
			12765,  // Dark bow (green)
			12766,  // Dark bow (blue)
			12767,  // Dark bow (yellow)
			12768   // Dark bow (white)
		);

		// ==================== TOOLS ====================

		// Dragon pickaxe variants
		addVariantGroup(
			11920,  // Dragon pickaxe
			12797,  // Dragon pickaxe (or)
			23677,  // Infernal pickaxe (charged)
			13243,  // Infernal pickaxe (uncharged)
			23680   // Crystal pickaxe
		);

		// Dragon axe variants
		addVariantGroup(
			6739,   // Dragon axe
			12807,  // Dragon axe (or)
			23673,  // Infernal axe (charged)
			13241,  // Infernal axe (uncharged)
			23675   // Crystal axe
		);

		// Dragon harpoon variants
		addVariantGroup(
			21028,  // Dragon harpoon
			21031,  // Dragon harpoon (or)
			23762,  // Infernal harpoon (charged)
			21033,  // Infernal harpoon (uncharged)
			23764   // Crystal harpoon
		);

		// ==================== PROSPECTOR KIT ====================

		addVariantGroup(
			12013,  // Prospector helmet
			25549   // Golden prospector helmet
		);
		addVariantGroup(
			12014,  // Prospector jacket
			25551   // Golden prospector jacket
		);
		addVariantGroup(
			12015,  // Prospector legs
			25553   // Golden prospector legs
		);
		addVariantGroup(
			12016,  // Prospector boots
			25555   // Golden prospector boots
		);

		// ==================== INFINITY ROBES ====================

		// Infinity hat variants
		addVariantGroup(
			6918,   // Infinity hat
			12457,  // Dark infinity hat
			12419   // Light infinity hat
		);

		// Infinity top variants
		addVariantGroup(
			6916,   // Infinity top
			12458,  // Dark infinity top
			12420   // Light infinity top
		);

		// Infinity bottoms variants
		addVariantGroup(
			6924,   // Infinity bottoms
			12459,  // Dark infinity bottoms
			12421   // Light infinity bottoms
		);

		// ==================== ANCESTRAL ROBES ====================

		// Ancestral hat variants
		addVariantGroup(
			21018,  // Ancestral hat
			24664   // Twisted ancestral hat
		);

		// Ancestral robe top variants
		addVariantGroup(
			21021,  // Ancestral robe top
			24666   // Twisted ancestral robe top
		);

		// Ancestral robe bottom variants
		addVariantGroup(
			21024,  // Ancestral robe bottom
			24668   // Twisted ancestral robe bottom
		);

		// ==================== VOID KNIGHT EQUIPMENT ====================

		// Void knight top variants
		addVariantGroup(
			8839,   // Void knight top
			13072   // Elite void top
		);

		// Void knight robe variants
		addVariantGroup(
			8840,   // Void knight robe
			13073   // Elite void robe
		);

		// ==================== GOD CAPES ====================

		// Saradomin cape variants
		addVariantGroup(
			2412,   // Saradomin cape
			21791   // Imbued saradomin cape
		);

		// Zamorak cape variants
		addVariantGroup(
			2413,   // Zamorak cape
			21795   // Imbued zamorak cape
		);

		// Guthix cape variants
		addVariantGroup(
			2414,   // Guthix cape
			21793   // Imbued guthix cape
		);

		// ==================== SALVE AMULET ====================

		addVariantGroup(
			4081,   // Salve amulet
			10588,  // Salve amulet (e)
			12017,  // Salve amulet(i)
			12018   // Salve amulet(ei)
		);

		// ==================== BLACK MASK ====================

		addVariantGroup(
			8901,   // Black mask (10)
			8903,   // Black mask (9)
			8905,   // Black mask (8)
			8907,   // Black mask (7)
			8909,   // Black mask (6)
			8911,   // Black mask (5)
			8913,   // Black mask (4)
			8915,   // Black mask (3)
			8917,   // Black mask (2)
			8919,   // Black mask (1)
			8921,   // Black mask
			8923,   // Black mask (i)
			// Note: Black mask charges are separate variants for banking purposes
			// but all uncharged masks map to slayer helmet creation
			11784,  // Black mask (i) - alternate
			11774   // Black mask (i) - alternate
		);

		// ==================== RUNE SCIMITAR ====================

		addVariantGroup(
			1333,   // Rune scimitar
			23330,  // Rune scimitar (guthix)
			23332,  // Rune scimitar (saradomin)
			23334   // Rune scimitar (zamorak)
		);

		// ==================== GRANITE MAUL ====================

		addVariantGroup(
			4153,   // Granite maul
			12848   // Granite maul (or)
		);

		// ==================== ODIUM/MALEDICTION WARDS ====================

		addVariantGroup(
			11926,  // Odium ward
			12807   // Odium ward (or)
		);

		addVariantGroup(
			11924,  // Malediction ward
			12806   // Malediction ward (or)
		);

		// ==================== STEAM/LAVA BATTLESTAFF ====================

		addVariantGroup(
			11787,  // Steam battlestaff
			12795   // Steam battlestaff (or)
		);

		addVariantGroup(
			11789,  // Lava battlestaff
			21198   // Lava battlestaff (or)
		);

		// ==================== MYSTIC ROBES (Shattered Relics) ====================

		addVariantGroup(
			4089,   // Mystic hat
			23047   // Mystic hat (or)
		);

		addVariantGroup(
			4091,   // Mystic robe top
			23050   // Mystic robe top (or)
		);

		addVariantGroup(
			4093,   // Mystic robe bottom
			23053   // Mystic robe bottom (or)
		);

		addVariantGroup(
			4095,   // Mystic gloves
			23056   // Mystic gloves (or)
		);

		addVariantGroup(
			4097,   // Mystic boots
			23059   // Mystic boots (or)
		);

		// ==================== CANNON (Shattered Relics) ====================

		addVariantGroup(
			6,      // Cannon base
			23028   // Cannon base (or)
		);

		addVariantGroup(
			8,      // Cannon stand
			23031   // Cannon stand (or)
		);

		addVariantGroup(
			10,     // Cannon barrels
			23034   // Cannon barrels (or)
		);

		addVariantGroup(
			12,     // Cannon furnace
			23037   // Cannon furnace (or)
		);

		// ==================== TOXIC BLOWPIPE ====================

		addVariantGroup(
			12924,  // Toxic blowpipe (empty)
			12926,  // Toxic blowpipe (charged)
			28688   // Blazing blowpipe
		);

		// ==================== DINH'S BULWARK ====================

		addVariantGroup(
			21015,  // Dinh's bulwark
			28690   // Dinh's blazing bulwark
		);

		// ==================== FIGHTER TORSO ====================

		addVariantGroup(
			10551,  // Fighter torso
			27018   // Fighter torso (or)
		);

		// ==================== AVERNIC DEFENDER ====================

		addVariantGroup(
			22322,  // Avernic defender
			28697   // Avernic defender (or) - deadman
		);

		// ==================== ADDITIONAL RINGS ====================

		// Ring of suffering variants
		addVariantGroup(
			19550,  // Ring of suffering
			19710,  // Ring of suffering (i)
			20657,  // Ring of suffering (ri) - with recoil
			27310   // Ring of suffering (i) - Soul Wars
		);

		// Granite ring variants
		addVariantGroup(
			21739,  // Granite ring
			21752   // Granite ring (i)
		);

		// ==================== MAGIC SHORTBOW ====================

		addVariantGroup(
			861,    // Magic shortbow
			12788   // Magic shortbow (i)
		);

		// ==================== SERPENTINE HELMS ====================

		addVariantGroup(
			12929,  // Serpentine helm (uncharged)
			12931,  // Serpentine helm (charged)
			13196,  // Tanzanite helm (uncharged)
			13197,  // Tanzanite helm (charged)
			13198,  // Magma helm (uncharged)
			13199   // Magma helm (charged)
		);

		// ==================== THEATRE OF BLOOD WEAPONS ====================

		// Ghrazi rapier variants
		addVariantGroup(
			22324,  // Ghrazi rapier
			25734   // Holy ghrazi rapier
		);

		// Scythe of vitur variants
		addVariantGroup(
			22325,  // Scythe of vitur (charged)
			22486,  // Scythe of vitur (uncharged)
			25736,  // Holy scythe of vitur (charged)
			25738,  // Holy scythe of vitur (uncharged)
			25740,  // Sanguine scythe of vitur (charged)
			25742   // Sanguine scythe of vitur (uncharged)
		);

		// Sanguinesti staff variants
		addVariantGroup(
			22323,  // Sanguinesti staff (charged)
			22481,  // Sanguinesti staff (uncharged)
			25731,  // Holy sanguinesti staff (charged)
			25733   // Holy sanguinesti staff (uncharged)
		);

		// ==================== CROSSBOWS ====================

		// Rune crossbow variants
		addVariantGroup(
			9185,   // Rune crossbow
			23601   // Rune crossbow (or)
		);

		// Dragon crossbow variants
		addVariantGroup(
			21902,  // Dragon crossbow
			28689   // Dragon crossbow (cr)
		);

		// Dragon hunter crossbow variants
		addVariantGroup(
			21012,  // Dragon hunter crossbow
			28700,  // Dragon hunter crossbow (t)
			28703   // Dragon hunter crossbow (b)
		);

		// ==================== HEAVY BALLISTA ====================

		addVariantGroup(
			19481,  // Heavy ballista
			28701   // Heavy ballista (or)
		);

		// ==================== TZHAAR WEAPONS ====================

		addVariantGroup(
			6528,   // Tzhaar-ket-om
			23600   // Tzhaar-ket-om (t)
		);

		// ==================== ABYSSAL TENTACLE ====================

		addVariantGroup(
			12006,  // Abyssal tentacle
			28699   // Abyssal tentacle (or)
		);

		// ==================== ELDER MAUL ====================

		addVariantGroup(
			21003,  // Elder maul
			27655   // Elder maul (or)
		);

		// ==================== OSMUMTEN'S FANG ====================

		addVariantGroup(
			26219,  // Osmumten's fang
			27246   // Osmumten's fang (or)
		);

		// ==================== NEITIZNOT HELM ====================

		addVariantGroup(
			10828,  // Helm of neitiznot
			28593   // Helm of neitiznot (or)
		);

		// ==================== ELIDINIS' WARD ====================

		addVariantGroup(
			25985,  // Elidinis' ward (f)
			27251   // Elidinis' ward (or)
		);

		// ==================== TUMEKEN'S SHADOW ====================

		addVariantGroup(
			27275,  // Tumeken's shadow (charged)
			27277   // Tumeken's shadow (uncharged)
		);

		// ==================== VENATOR BOW ====================

		addVariantGroup(
			27610,  // Venator bow (charged)
			27612,  // Venator bow (uncharged)
			29925   // Echo venator bow
		);

		// ==================== TORVA ARMOR ====================

		// Torva full helm variants
		addVariantGroup(
			26382,  // Torva full helm
			28254   // Sanguine torva full helm
		);

		// Torva platebody variants
		addVariantGroup(
			26384,  // Torva platebody
			28256   // Sanguine torva platebody
		);

		// Torva platelegs variants
		addVariantGroup(
			26386,  // Torva platelegs
			28258   // Sanguine torva platelegs
		);

		// ==================== MASORI ARMOR ====================

		// Masori mask variants
		addVariantGroup(
			27226,  // Masori mask
			27229,  // Masori mask (f)
			30033   // Masori mask (f) - fortified (if different)
		);

		// Masori body variants
		addVariantGroup(
			27232,  // Masori body
			27235,  // Masori body (f)
			30035   // Masori body (f) - fortified (if different)
		);

		// Masori chaps variants
		addVariantGroup(
			27238,  // Masori chaps
			27241,  // Masori chaps (f)
			30037   // Masori chaps (f) - fortified (if different)
		);

		// ==================== ELDER CHAOS ROBES ====================

		addVariantGroup(
			21792,  // Elder chaos hood
			29610   // Elder chaos hood (or)
		);

		addVariantGroup(
			21795,  // Elder chaos top
			29612   // Elder chaos top (or)
		);

		addVariantGroup(
			21798,  // Elder chaos robe
			29614   // Elder chaos robe (or)
		);

		// ==================== VOID ORNAMENT KIT ====================

		addVariantGroup(
			8842,   // Void knight mage helm
			27698   // Void knight mage helm (or)
		);

		addVariantGroup(
			11663,  // Void knight melee helm
			27700   // Void knight melee helm (or)
		);

		addVariantGroup(
			11664,  // Void knight ranger helm
			27702   // Void knight ranger helm (or)
		);

		// ==================== IMBUED GOD CAPES (MAX CAPE VARIANTS) ====================

		// Max cape fire/infernal variants
		addVariantGroup(
			13329,  // Fire max cape
			21285   // Infernal max cape
		);

		// Max cape assembler variants
		addVariantGroup(
			22109,  // Assembler max cape
			28785   // Masori assembler max cape
		);

		// ==================== CRYSTAL EQUIPMENT (GAUNTLET) ====================

		addVariantGroup(
			23886,  // Crystal helm (basic)
			23887,  // Crystal helm (attuned)
			23888   // Crystal helm (perfected)
		);

		addVariantGroup(
			23889,  // Crystal body (basic)
			23890,  // Crystal body (attuned)
			23891   // Crystal body (perfected)
		);

		addVariantGroup(
			23892,  // Crystal legs (basic)
			23893,  // Crystal legs (attuned)
			23894   // Crystal legs (perfected)
		);

		// ==================== BARROWS EQUIPMENT ====================
		// Note: Barrows items degrade - all versions are functionally equivalent

		// Ahrim's hood variants
		addVariantGroup(
			4708,   // Ahrim's hood 0
			4856,   // Ahrim's hood 25
			4857,   // Ahrim's hood 50
			4858,   // Ahrim's hood 75
			4859    // Ahrim's hood 100
		);

		// Ahrim's staff variants
		addVariantGroup(
			4710,   // Ahrim's staff 0
			4862,   // Ahrim's staff 25
			4863,   // Ahrim's staff 50
			4864,   // Ahrim's staff 75
			4865    // Ahrim's staff 100
		);

		// Ahrim's robetop variants
		addVariantGroup(
			4712,   // Ahrim's robetop 0
			4868,   // Ahrim's robetop 25
			4869,   // Ahrim's robetop 50
			4870,   // Ahrim's robetop 75
			4871    // Ahrim's robetop 100
		);

		// Ahrim's robeskirt variants
		addVariantGroup(
			4714,   // Ahrim's robeskirt 0
			4874,   // Ahrim's robeskirt 25
			4875,   // Ahrim's robeskirt 50
			4876,   // Ahrim's robeskirt 75
			4877    // Ahrim's robeskirt 100
		);

		// Dharok's helm variants
		addVariantGroup(
			4716,   // Dharok's helm 0
			4880,   // Dharok's helm 25
			4881,   // Dharok's helm 50
			4882,   // Dharok's helm 75
			4883    // Dharok's helm 100
		);

		// Dharok's greataxe variants
		addVariantGroup(
			4718,   // Dharok's greataxe 0
			4886,   // Dharok's greataxe 25
			4887,   // Dharok's greataxe 50
			4888,   // Dharok's greataxe 75
			4889    // Dharok's greataxe 100
		);

		// Dharok's platebody variants
		addVariantGroup(
			4720,   // Dharok's platebody 0
			4892,   // Dharok's platebody 25
			4893,   // Dharok's platebody 50
			4894,   // Dharok's platebody 75
			4895    // Dharok's platebody 100
		);

		// Dharok's platelegs variants
		addVariantGroup(
			4722,   // Dharok's platelegs 0
			4898,   // Dharok's platelegs 25
			4899,   // Dharok's platelegs 50
			4900,   // Dharok's platelegs 75
			4901    // Dharok's platelegs 100
		);

		// Guthan's helm variants
		addVariantGroup(
			4724,   // Guthan's helm 0
			4904,   // Guthan's helm 25
			4905,   // Guthan's helm 50
			4906,   // Guthan's helm 75
			4907    // Guthan's helm 100
		);

		// Guthan's warspear variants
		addVariantGroup(
			4726,   // Guthan's warspear 0
			4910,   // Guthan's warspear 25
			4911,   // Guthan's warspear 50
			4912,   // Guthan's warspear 75
			4913    // Guthan's warspear 100
		);

		// Guthan's platebody variants
		addVariantGroup(
			4728,   // Guthan's platebody 0
			4916,   // Guthan's platebody 25
			4917,   // Guthan's platebody 50
			4918,   // Guthan's platebody 75
			4919    // Guthan's platebody 100
		);

		// Guthan's chainskirt variants
		addVariantGroup(
			4730,   // Guthan's chainskirt 0
			4922,   // Guthan's chainskirt 25
			4923,   // Guthan's chainskirt 50
			4924,   // Guthan's chainskirt 75
			4925    // Guthan's chainskirt 100
		);

		// Karil's coif variants
		addVariantGroup(
			4732,   // Karil's coif 0
			4928,   // Karil's coif 25
			4929,   // Karil's coif 50
			4930,   // Karil's coif 75
			4931    // Karil's coif 100
		);

		// Karil's crossbow variants
		addVariantGroup(
			4734,   // Karil's crossbow 0
			4934,   // Karil's crossbow 25
			4935,   // Karil's crossbow 50
			4936,   // Karil's crossbow 75
			4937    // Karil's crossbow 100
		);

		// Karil's leathertop variants
		addVariantGroup(
			4736,   // Karil's leathertop 0
			4940,   // Karil's leathertop 25
			4941,   // Karil's leathertop 50
			4942,   // Karil's leathertop 75
			4943    // Karil's leathertop 100
		);

		// Karil's leatherskirt variants
		addVariantGroup(
			4738,   // Karil's leatherskirt 0
			4946,   // Karil's leatherskirt 25
			4947,   // Karil's leatherskirt 50
			4948,   // Karil's leatherskirt 75
			4949    // Karil's leatherskirt 100
		);

		// Torag's helm variants
		addVariantGroup(
			4745,   // Torag's helm 0
			4952,   // Torag's helm 25
			4953,   // Torag's helm 50
			4954,   // Torag's helm 75
			4955    // Torag's helm 100
		);

		// Torag's hammers variants
		addVariantGroup(
			4747,   // Torag's hammers 0
			4958,   // Torag's hammers 25
			4959,   // Torag's hammers 50
			4960,   // Torag's hammers 75
			4961    // Torag's hammers 100
		);

		// Torag's platebody variants
		addVariantGroup(
			4749,   // Torag's platebody 0
			4964,   // Torag's platebody 25
			4965,   // Torag's platebody 50
			4966,   // Torag's platebody 75
			4967    // Torag's platebody 100
		);

		// Torag's platelegs variants
		addVariantGroup(
			4751,   // Torag's platelegs 0
			4970,   // Torag's platelegs 25
			4971,   // Torag's platelegs 50
			4972,   // Torag's platelegs 75
			4973    // Torag's platelegs 100
		);

		// Verac's helm variants
		addVariantGroup(
			4753,   // Verac's helm 0
			4976,   // Verac's helm 25
			4977,   // Verac's helm 50
			4978,   // Verac's helm 75
			4979    // Verac's helm 100
		);

		// Verac's flail variants
		addVariantGroup(
			4755,   // Verac's flail 0
			4982,   // Verac's flail 25
			4983,   // Verac's flail 50
			4984,   // Verac's flail 75
			4985    // Verac's flail 100
		);

		// Verac's brassard variants
		addVariantGroup(
			4757,   // Verac's brassard 0
			4988,   // Verac's brassard 25
			4989,   // Verac's brassard 50
			4990,   // Verac's brassard 75
			4991    // Verac's brassard 100
		);

		// Verac's plateskirt variants
		addVariantGroup(
			4759,   // Verac's plateskirt 0
			4994,   // Verac's plateskirt 25
			4995,   // Verac's plateskirt 50
			4996,   // Verac's plateskirt 75
			4997    // Verac's plateskirt 100
		);

		// ==================== INFERNAL CAPE ====================

		addVariantGroup(
			21295,  // Infernal cape
			27016,  // Infernal cape (or)
			24224   // Infernal cape (l) - locked
		);

		// ==================== GOD BOOKS ====================

		addVariantGroup(
			3840,   // Holy book
			26496   // Holy book (or)
		);

		addVariantGroup(
			3842,   // Unholy book
			26498   // Unholy book (or)
		);

		addVariantGroup(
			3844,   // Book of balance
			26488   // Book of balance (or)
		);

		addVariantGroup(
			12608,  // Book of war
			26494   // Book of war (or)
		);

		addVariantGroup(
			12610,  // Book of law
			26492   // Book of law (or)
		);

		addVariantGroup(
			12612,  // Book of darkness
			26490   // Book of darkness (or)
		);

		// ==================== CHARGED/UNCHARGED ITEMS ====================

		// Bracelet of ethereum
		addVariantGroup(
			21816,  // Bracelet of ethereum (charged)
			21817   // Bracelet of ethereum (uncharged)
		);

		// Ring of endurance
		addVariantGroup(
			24736,  // Ring of endurance (charged)
			24844   // Ring of endurance (uncharged)
		);

		// Celestial ring
		addVariantGroup(
			25539,  // Celestial ring (uncharged)
			25541   // Celestial ring (charged)
		);

		// Pharaoh's sceptre
		addVariantGroup(
			26945,  // Pharaoh's sceptre (uncharged)
			26948,  // Pharaoh's sceptre (charged)
			26950   // Pharaoh's sceptre (charged alt)
		);

		// ==================== REVENANT WEAPONS ====================

		// Craw's bow
		addVariantGroup(
			22547,  // Craw's bow (u)
			22550   // Craw's bow (charged)
		);

		// Thammaron's sceptre
		addVariantGroup(
			22552,  // Thammaron's sceptre (u)
			22555   // Thammaron's sceptre (charged)
		);

		// Viggora's chainmace
		addVariantGroup(
			22542,  // Viggora's chainmace (u)
			22545   // Viggora's chainmace (charged)
		);

		// ==================== ARCLIGHT/DARKLIGHT ====================

		addVariantGroup(
			6746,   // Darklight
			19675,  // Arclight (charged)
			30305   // Arclight (inactive)
		);

		// ==================== LOCKED ITEMS (TROUVER PARCHMENT) ====================

		// Fire cape locked
		addVariantGroup(
			6570,   // Fire cape
			24223,  // Fire cape (l)
			27014,  // Fire cape (or)
			13329   // Fire max cape
		);

		// Void knight top locked
		addVariantGroup(
			8839,   // Void knight top
			13072,  // Elite void top
			24177   // Void knight top (l)
		);

		// ==================== MAX CAPE VARIANTS ====================

		// Base max cape and god variants
		addVariantGroup(
			13280,  // Max cape
			13331,  // Saradomin max cape
			13333,  // Zamorak max cape
			13335,  // Guthix max cape
			21776,  // Imbued saradomin max cape
			21780,  // Imbued zamorak max cape
			21784   // Imbued guthix max cape
		);

		// Accumulator/Assembler max capes
		addVariantGroup(
			10499,  // Ava's accumulator
			22109,  // Ava's assembler
			13337,  // Accumulator max cape
			22109   // Assembler max cape (uses same as assembler base?)
		);

		// Mythical max cape
		addVariantGroup(
			22114,  // Mythical cape
			24855   // Mythical max cape
		);

		// ==================== ACHIEVEMENT DIARY ITEMS ====================

		// Explorer's ring variants
		addVariantGroup(
			13125,  // Explorer's ring 1
			13126,  // Explorer's ring 2
			13127,  // Explorer's ring 3
			13128   // Explorer's ring 4
		);

		// Ardougne cloak variants
		addVariantGroup(
			13121,  // Ardougne cloak 1
			13122,  // Ardougne cloak 2
			13123,  // Ardougne cloak 3
			13124   // Ardougne cloak 4
		);

		// Desert amulet variants
		addVariantGroup(
			13133,  // Desert amulet 1
			13134,  // Desert amulet 2
			13135,  // Desert amulet 3
			13136   // Desert amulet 4
		);

		// Falador shield variants
		addVariantGroup(
			13117,  // Falador shield 1
			13118,  // Falador shield 2
			13119,  // Falador shield 3
			13120   // Falador shield 4
		);

		// Fremennik sea boots variants
		addVariantGroup(
			13129,  // Fremennik sea boots 1
			13130,  // Fremennik sea boots 2
			13131,  // Fremennik sea boots 3
			13132   // Fremennik sea boots 4
		);

		// Kandarin headgear variants
		addVariantGroup(
			13137,  // Kandarin headgear 1
			13138,  // Kandarin headgear 2
			13139,  // Kandarin headgear 3
			13140   // Kandarin headgear 4
		);

		// Karamja gloves variants
		addVariantGroup(
			11136,  // Karamja gloves 1
			11138,  // Karamja gloves 2
			11140,  // Karamja gloves 3
			13103   // Karamja gloves 4
		);

		// Morytania legs variants
		addVariantGroup(
			13112,  // Morytania legs 1
			13113,  // Morytania legs 2
			13114,  // Morytania legs 3
			13115   // Morytania legs 4
		);

		// Varrock armour variants
		addVariantGroup(
			13104,  // Varrock armour 1
			13105,  // Varrock armour 2
			13106,  // Varrock armour 3
			13107   // Varrock armour 4
		);

		// Western banner variants
		addVariantGroup(
			13141,  // Western banner 1
			13142,  // Western banner 2
			13143,  // Western banner 3
			13144   // Western banner 4
		);

		// Wilderness sword variants
		addVariantGroup(
			13108,  // Wilderness sword 1
			13109,  // Wilderness sword 2
			13110,  // Wilderness sword 3
			13111   // Wilderness sword 4
		);

		// Rada's blessing variants
		addVariantGroup(
			22941,  // Rada's blessing 1
			22943,  // Rada's blessing 2
			22945,  // Rada's blessing 3
			22947   // Rada's blessing 4
		);

		// ==================== CHOMPY BIRD HATS ====================

		addVariantGroup(
			2978,   // Ogre bowman hat
			2979,   // Bowman hat
			2980,   // Ogre yeoman hat
			2981,   // Yeoman hat
			2982,   // Ogre marksman hat
			2983,   // Marksman hat
			2984,   // Ogre woodsman hat
			2985,   // Woodsman hat
			2986,   // Ogre forester hat
			2987,   // Forester hat
			2988,   // Ogre bowmaster hat
			2989,   // Bowmaster hat
			2990,   // Ogre expert hat
			2991,   // Expert hat
			2992,   // Ogre dragon archer hat
			2993,   // Dragon archer hat
			2994,   // Expert ogre dragon archer hat
			2995    // Expert dragon archer hat
		);

		// ==================== RUNE POUCH ====================

		addVariantGroup(
			12791,  // Rune pouch
			24416   // Rune pouch (l)
		);

		// ==================== MORE LOCKED ITEMS ====================

		// Dragon defender locked
		addVariantGroup(
			12954,  // Dragon defender
			19722,  // Dragon defender (t)
			24143   // Dragon defender (l)
		);

		// Rune defender locked
		addVariantGroup(
			8850,   // Rune defender
			23230,  // Rune defender (t)
			24142   // Rune defender (l)
		);

		// Fighter torso locked
		addVariantGroup(
			10551,  // Fighter torso
			27018,  // Fighter torso (or)
			24175   // Fighter torso (l)
		);

		// Void knight robe locked
		addVariantGroup(
			8840,   // Void knight robe
			13073,  // Elite void robe
			24179,  // Void knight robe (l)
			24180   // Elite void robe (l)
		);

		// Void knight gloves locked
		addVariantGroup(
			8842,   // Void knight gloves
			24182   // Void knight gloves (l)
		);

		// Elite void top locked
		addVariantGroup(
			13072,  // Elite void top
			24178   // Elite void top (l)
		);

		// ==================== IMBUED/SATURATED HEART ====================

		addVariantGroup(
			20724,  // Imbued heart
			27641   // Saturated heart
		);

		// ==================== TRIDENTS ====================

		// Trident of the seas
		addVariantGroup(
			11905,  // Trident of the seas (full)
			11907,  // Trident of the seas (partial)
			11908   // Uncharged trident
		);

		// Trident of the swamp
		addVariantGroup(
			12899,  // Trident of the swamp (charged)
			12900   // Toxic trident (uncharged)
		);

		// ==================== STAFF OF THE DEAD VARIANTS ====================

		addVariantGroup(
			11791,  // Staff of the dead
			22296,  // Staff of light
			12902,  // Toxic staff of the dead (uncharged)
			12904   // Toxic staff of the dead (charged)
		);

		// ==================== AMULET OF THE DAMNED ====================

		addVariantGroup(
			12851,  // Amulet of the damned (full)
			12853   // Amulet of the damned (degraded)
		);

		// ==================== SLAYER RINGS ====================

		addVariantGroup(
			11866,  // Slayer ring (8)
			11867,  // Slayer ring (7)
			11868,  // Slayer ring (6)
			11869,  // Slayer ring (5)
			11870,  // Slayer ring (4)
			11871,  // Slayer ring (3)
			11872,  // Slayer ring (2)
			11873   // Slayer ring (1)
		);

		// ==================== CRYSTAL EQUIPMENT (SONG OF THE ELVES) ====================

		// Crystal bow
		addVariantGroup(
			23983,  // Crystal bow (active)
			23985,  // Crystal bow (inactive)
			24123   // Crystal bow (new)
		);

		// Crystal shield
		addVariantGroup(
			23991,  // Crystal shield (active)
			23993,  // Crystal shield (inactive)
			24127   // Crystal shield (new)
		);

		// Crystal halberd
		addVariantGroup(
			23987,  // Crystal halberd (active)
			23989,  // Crystal halberd (inactive)
			24125   // Crystal halberd (new)
		);

		// Blade of saeldor
		addVariantGroup(
			23995,  // Blade of saeldor (charged)
			23997   // Blade of saeldor (inactive)
		);

		// Bow of faerdhinen
		addVariantGroup(
			25862,  // Bow of faerdhinen (inactive)
			25865   // Bow of faerdhinen (charged)
		);

		// ==================== AMULET OF GLORY ====================

		addVariantGroup(
			1704,   // Amulet of glory (uncharged)
			1706,   // Amulet of glory (1)
			1708,   // Amulet of glory (2)
			1710,   // Amulet of glory (3)
			1712,   // Amulet of glory (4)
			11976,  // Amulet of glory (5)
			11978   // Amulet of glory (6)
		);

		// ==================== GAMES NECKLACE ====================

		addVariantGroup(
			3853,   // Games necklace (8)
			3855,   // Games necklace (7)
			3857,   // Games necklace (6)
			3859,   // Games necklace (5)
			3861,   // Games necklace (4)
			3863,   // Games necklace (3)
			3865,   // Games necklace (2)
			3867    // Games necklace (1)
		);

		// ==================== RING OF DUELING ====================

		addVariantGroup(
			2552,   // Ring of dueling (8)
			2554,   // Ring of dueling (7)
			2556,   // Ring of dueling (6)
			2558,   // Ring of dueling (5)
			2560,   // Ring of dueling (4)
			2562,   // Ring of dueling (3)
			2564,   // Ring of dueling (2)
			2566    // Ring of dueling (1)
		);

		// ==================== COMBAT BRACELET ====================

		addVariantGroup(
			11118,  // Combat bracelet (6)
			11120,  // Combat bracelet (5)
			11122,  // Combat bracelet (4)
			11124,  // Combat bracelet (3)
			11126,  // Combat bracelet (2)
			11128   // Combat bracelet (1)
		);

		// ==================== SKILLS NECKLACE ====================

		addVariantGroup(
			11105,  // Skills necklace (6)
			11107,  // Skills necklace (5)
			11109,  // Skills necklace (4)
			11111,  // Skills necklace (3)
			11113,  // Skills necklace (2)
			11115   // Skills necklace (1)
		);

		// ==================== DIGSITE PENDANT ====================

		addVariantGroup(
			11190,  // Digsite pendant (5)
			11191,  // Digsite pendant (4)
			11192,  // Digsite pendant (3)
			11193,  // Digsite pendant (2)
			11194   // Digsite pendant (1)
		);

		// ==================== NECKLACE OF PASSAGE ====================

		addVariantGroup(
			21146,  // Necklace of passage (5)
			21149,  // Necklace of passage (4)
			21151,  // Necklace of passage (3)
			21153,  // Necklace of passage (2)
			21155   // Necklace of passage (1)
		);

		// ==================== BURNING AMULET ====================

		addVariantGroup(
			21166,  // Burning amulet (5)
			21169,  // Burning amulet (4)
			21171,  // Burning amulet (3)
			21173,  // Burning amulet (2)
			21175   // Burning amulet (1)
		);

		// ==================== RING OF RETURNING ====================

		addVariantGroup(
			21129,  // Ring of returning (5)
			21132,  // Ring of returning (4)
			21134,  // Ring of returning (3)
			21136,  // Ring of returning (2)
			21138   // Ring of returning (1)
		);

		// ==================== AMULET OF GLORY (T) ====================

		addVariantGroup(
			10354,  // Amulet of glory (t) (uncharged)
			10356,  // Amulet of glory (t1)
			10358,  // Amulet of glory (t2)
			10360,  // Amulet of glory (t3)
			10362,  // Amulet of glory (t4)
			11964,  // Amulet of glory (t5)
			11966   // Amulet of glory (t6)
		);

		// ==================== AMULET OF ETERNAL GLORY ====================

		addVariantGroup(
			19707,  // Amulet of eternal glory
			1712    // Also maps from regular glory (4) - shares functionality
		);

		// ==================== TELEPORT JEWELLERY - MISC ====================

		// Enchanted lyre
		addVariantGroup(
			3690,   // Enchanted lyre
			6125,   // Enchanted lyre (1)
			6126,   // Enchanted lyre (2)
			6127,   // Enchanted lyre (3)
			6128    // Enchanted lyre (4)
		);

		// Drakan's medallion
		addVariantGroup(
			22400,  // Drakan's medallion
			22401   // Drakan's medallion (charges visible)
		);

		// Skull sceptre
		addVariantGroup(
			9013,   // Skull sceptre
			21273,  // Skull sceptre (i)
			9012,   // Skull sceptre (i) variant
			21276   // Strange skull
		);

		// ==================== BLIGHTED SUPPLIES ====================

		// Blighted super restores
		addVariantGroup(
			24620,  // Blighted super restore (4)
			24623,  // Blighted super restore (3)
			24626,  // Blighted super restore (2)
			24629   // Blighted super restore (1)
		);

		// Blighted anglerfish
		addVariantGroup(
			24640,  // Blighted anglerfish
			13441   // Regular anglerfish
		);

		// Blighted karambwan
		addVariantGroup(
			24650,  // Blighted karambwan
			3144    // Regular karambwan
		);

		// Blighted manta ray
		addVariantGroup(
			24643,  // Blighted manta ray
			391     // Regular manta ray
		);

		// ==================== PHARAOH'S SCEPTRE ====================

		addVariantGroup(
			9044,   // Pharaoh's sceptre (3)
			9046,   // Pharaoh's sceptre (2)
			9048,   // Pharaoh's sceptre (1)
			9050    // Pharaoh's sceptre (uncharged)
		);

		// ==================== ZAMORAKIAN HASTA/SPEAR ====================

		addVariantGroup(
			11824,  // Zamorakian spear
			11889   // Zamorakian hasta
		);

		// ==================== ANCIENT STAFF VARIANTS ====================

		addVariantGroup(
			4675,   // Ancient staff
			22323   // Ancient sceptre
		);

		// ==================== SEERCULL ====================

		addVariantGroup(
			6724,   // Seercull
			28783   // Seercull (or)
		);

		// ==================== MASTER WAND ====================

		addVariantGroup(
			6914,   // Master wand
			28781   // Master wand (or)
		);

		// ==================== MAGE'S BOOK ====================

		addVariantGroup(
			6889,   // Mage's book
			28779   // Mage's book (or)
		);

		// ==================== RUNE CANE ====================

		addVariantGroup(
			12373,  // Rune cane
			28777   // Rune cane (or)
		);

		// ==================== DRAGON CANE ====================

		addVariantGroup(
			12369,  // Dragon cane
			28775   // Dragon cane (or)
		);

		// ==================== TARNISHED LOCKET ====================

		addVariantGroup(
			29959,  // Tarnished locket (charged)
			29961   // Tarnished locket (uncharged)
		);

		// ==================== PHARAOH'S SCEPTRE (OR) ====================

		addVariantGroup(
			26948,  // Pharaoh's sceptre (full)
			26950,  // Pharaoh's sceptre (3)
			26952,  // Pharaoh's sceptre (2)
			26954,  // Pharaoh's sceptre (1)
			26956   // Pharaoh's sceptre (uncharged)
		);

		// ==================== BRYOPHYTA'S STAFF ====================

		addVariantGroup(
			22370,  // Bryophyta's staff (charged)
			22368   // Bryophyta's staff (uncharged)
		);

		// ==================== BLUE MOON ITEMS ====================

		addVariantGroup(
			28260,  // Blue moon helm (charged)
			28262   // Blue moon helm (uncharged)
		);

		addVariantGroup(
			28264,  // Blue moon chestplate (charged)
			28266   // Blue moon chestplate (uncharged)
		);

		addVariantGroup(
			28268,  // Blue moon tassets (charged)
			28270   // Blue moon tassets (uncharged)
		);

		addVariantGroup(
			28272,  // Blue moon spear (charged)
			28274   // Blue moon spear (uncharged)
		);

		// ==================== BLOOD MOON ITEMS ====================

		addVariantGroup(
			28276,  // Blood moon helm (charged)
			28278   // Blood moon helm (uncharged)
		);

		addVariantGroup(
			28280,  // Blood moon chestplate (charged)
			28282   // Blood moon chestplate (uncharged)
		);

		addVariantGroup(
			28284,  // Blood moon tassets (charged)
			28286   // Blood moon tassets (uncharged)
		);

		addVariantGroup(
			28288,  // Dual macuahuitl (charged)
			28290   // Dual macuahuitl (uncharged)
		);

		// ==================== ECLIPSE MOON ITEMS ====================

		addVariantGroup(
			28292,  // Eclipse moon helm (charged)
			28294   // Eclipse moon helm (uncharged)
		);

		addVariantGroup(
			28296,  // Eclipse moon chestplate (charged)
			28298   // Eclipse moon chestplate (uncharged)
		);

		addVariantGroup(
			28300,  // Eclipse moon tassets (charged)
			28302   // Eclipse moon tassets (uncharged)
		);

		addVariantGroup(
			28304,  // Eclipse atlatl (charged)
			28306   // Eclipse atlatl (uncharged)
		);

		// ==================== TONALZTICS OF RALOS ====================

		addVariantGroup(
			28316,  // Tonalztics of ralos (charged)
			28318   // Tonalztics of ralos (uncharged)
		);

		// ==================== SUNFIRE FANATIC ====================

		addVariantGroup(
			28308,  // Sunfire fanatic helm
			28310,  // Sunfire fanatic cuirass
			28312   // Sunfire fanatic chausses
		);

		// ==================== KERIS PARTISAN ====================

		addVariantGroup(
			25977,  // Keris partisan
			25981,  // Keris partisan of breaching
			25979,  // Keris partisan of corruption
			25983   // Keris partisan of the sun
		);

		// ==================== LIGHTBEARER ====================

		addVariantGroup(
			25975,  // Lightbearer
			27253   // Lightbearer (or)
		);

		// ==================== BOOK OF THE DEAD ====================

		addVariantGroup(
			25818,  // Book of the dead (empty)
			25820   // Book of the dead (charged)
		);

		// ==================== SHADOW ANCIENT SCEPTRE ====================

		addVariantGroup(
			27660,  // Ancient sceptre (shadow)
			27258   // Ancient sceptre (or)
		);

		// ==================== ANCIENT GODSWORD ====================

		addVariantGroup(
			26233,  // Ancient godsword
			27327   // Ancient godsword (or)
		);

		// ==================== ZARYTE CROSSBOW ====================

		addVariantGroup(
			26374,  // Zaryte crossbow
			27329   // Zaryte crossbow (or)
		);

		// ==================== ARMADYL CROSSBOW ====================

		addVariantGroup(
			11785,  // Armadyl crossbow
			23611   // Armadyl crossbow (or)
		);

		// ==================== BOW OF FAERDHINEN (C) ====================

		addVariantGroup(
			25867,  // Bow of faerdhinen (c)
			25884,  // Bow of faerdhinen (c) (red)
			25886,  // Bow of faerdhinen (c) (white)
			25888,  // Bow of faerdhinen (c) (green)
			25890,  // Bow of faerdhinen (c) (blue)
			25892   // Bow of faerdhinen (c) (yellow)
		);

		// ==================== BLADE OF SAELDOR (C) ====================

		addVariantGroup(
			25870,  // Blade of saeldor (c)
			25872,  // Blade of saeldor (c) (red)
			25874,  // Blade of saeldor (c) (white)
			25876,  // Blade of saeldor (c) (green)
			25878,  // Blade of saeldor (c) (blue)
			25880   // Blade of saeldor (c) (yellow)
		);

		// ==================== TWISTED BOW ====================

		addVariantGroup(
			20997,  // Twisted bow
			24670,  // Twisted bow (or)
			29939   // Echo twisted bow
		);

		// ==================== KODAI WAND ====================

		addVariantGroup(
			21006,  // Kodai wand
			29941   // Echo kodai wand
		);

		// ==================== INQUISITOR'S ARMOUR ====================

		addVariantGroup(
			24419,  // Inquisitor's great helm
			30021   // Inquisitor's great helm (or)
		);

		addVariantGroup(
			24420,  // Inquisitor's hauberk
			30023   // Inquisitor's hauberk (or)
		);

		addVariantGroup(
			24421,  // Inquisitor's plateskirt
			30025   // Inquisitor's plateskirt (or)
		);

		addVariantGroup(
			24417,  // Inquisitor's mace
			30027   // Inquisitor's mace (or)
		);

		// ==================== NIGHTMARE STAFF ====================

		addVariantGroup(
			24422,  // Nightmare staff
			24424,  // Harmonised nightmare staff
			24425,  // Volatile nightmare staff
			24423   // Eldritch nightmare staff
		);

		// ==================== BANDOS ====================

		addVariantGroup(
			11832,  // Bandos chestplate
			30007   // Bandos chestplate (or)
		);

		addVariantGroup(
			11834,  // Bandos tassets
			30009   // Bandos tassets (or)
		);

		addVariantGroup(
			11836,  // Bandos boots
			30011   // Bandos boots (or)
		);

		// ==================== ARMADYL ====================

		addVariantGroup(
			11826,  // Armadyl helmet
			30001   // Armadyl helmet (or)
		);

		addVariantGroup(
			11828,  // Armadyl chestplate
			30003   // Armadyl chestplate (or)
		);

		addVariantGroup(
			11830,  // Armadyl chainskirt
			30005   // Armadyl chainskirt (or)
		);

		// ==================== SPIRIT SHIELDS ====================

		addVariantGroup(
			12817,  // Spectral spirit shield
			30013   // Spectral spirit shield (or)
		);

		addVariantGroup(
			12821,  // Arcane spirit shield
			30015   // Arcane spirit shield (or)
		);

		addVariantGroup(
			12825,  // Elysian spirit shield
			30017   // Elysian spirit shield (or)
		);

		// ==================== DINHS BLAZING VARIANTS ====================

		addVariantGroup(
			21015,  // Dinh's bulwark
			28690,  // Dinh's blazing bulwark
			30019   // Dinh's blazing bulwark (or)
		);

		// ==================== VIRTUS ROBES ====================

		addVariantGroup(
			26241,  // Virtus mask
			30029   // Virtus mask (or)
		);

		addVariantGroup(
			26243,  // Virtus robe top
			30031   // Virtus robe top (or)
		);

		addVariantGroup(
			26245,  // Virtus robe legs
			30041   // Virtus robe legs (or)
		);

		// ==================== DRAGON HUNTER LANCE ====================

		addVariantGroup(
			22978,  // Dragon hunter lance
			30039   // Dragon hunter lance (or)
		);

		// ==================== PHARAOH'S SCEPTRE (UNCHARGED TO CHARGED) ====================

		addVariantGroup(
			26945,  // Pharaoh's sceptre (uncharged)
			26948   // Pharaoh's sceptre (charged)
		);

		// ==================== DRAGONFIRE SHIELDS ====================

		addVariantGroup(
			11283,  // Dragonfire shield
			11284   // Dragonfire shield (charged)
		);

		addVariantGroup(
			22002,  // Dragonfire ward
			22003   // Dragonfire ward (charged)
		);

		addVariantGroup(
			21633,  // Ancient wyvern shield
			21634   // Ancient wyvern shield (charged)
		);

		// ==================== TOME OF FIRE ====================

		addVariantGroup(
			20714,  // Tome of fire (empty)
			20716   // Tome of fire (charged)
		);

		// ==================== TOME OF WATER ====================

		addVariantGroup(
			25574,  // Tome of water (empty)
			25576   // Tome of water (charged)
		);

		// ==================== BONECRUSHER ====================

		addVariantGroup(
			13116,  // Bonecrusher
			24480,  // Bonecrusher necklace
			24482   // Bonecrusher necklace (charged)
		);

		// ==================== ASH SANCTIFIER ====================

		addVariantGroup(
			25566,  // Ash sanctifier
			25568   // Ash sanctifier (charged)
		);

		// ==================== SOUL BEARER ====================

		addVariantGroup(
			22333,  // Soul bearer
			22336   // Soul bearer (charged)
		);

		// ==================== COAL BAG ====================

		addVariantGroup(
			12019,  // Coal bag
			24480   // Coal bag (open)
		);

		// ==================== GEM BAG ====================

		addVariantGroup(
			12020,  // Gem bag
			24481   // Gem bag (open)
		);

		// ==================== HERB SACK ====================

		addVariantGroup(
			13226,  // Herb sack
			24478   // Herb sack (open)
		);

		// ==================== SEED BOX ====================

		addVariantGroup(
			13639,  // Seed box
			24479   // Seed box (open)
		);

		// ==================== LOG BASKET ====================

		addVariantGroup(
			28779,  // Log basket
			28780   // Log basket (open)
		);

		// ==================== FISH BARREL ====================

		addVariantGroup(
			25582,  // Fish barrel
			25584   // Fish barrel (open)
		);

		// ==================== FORESTRY EQUIPMENT ====================

		addVariantGroup(
			28189,  // Forestry hat
			28194   // Forestry hat (or)
		);

		addVariantGroup(
			28191,  // Forestry top
			28196   // Forestry top (or)
		);

		addVariantGroup(
			28193,  // Forestry legs
			28198   // Forestry legs (or)
		);

		addVariantGroup(
			28183,  // Forestry boots
			28200   // Forestry boots (or)
		);

		// ==================== IRON/STEEL/MITH/ADDY/RUNE CROSSBOW (U) ====================

		addVariantGroup(
			9174,   // Bronze crossbow (u)
			9176,   // Bronze crossbow
			9177    // Bronze crossbow (string)
		);

		addVariantGroup(
			9177,   // Iron crossbow (u)
			9179,   // Iron crossbow
			9180    // Iron crossbow (string)
		);

		addVariantGroup(
			9181,   // Steel crossbow (u)
			9183,   // Steel crossbow
			9184    // Steel crossbow (string)
		);

		addVariantGroup(
			9185,   // Mithril crossbow (u)
			9187,   // Mithril crossbow
			9188    // Mithril crossbow (string)
		);

		addVariantGroup(
			9189,   // Adamant crossbow (u)
			9191,   // Adamant crossbow
			9192    // Adamant crossbow (string)
		);

		addVariantGroup(
			9193,   // Rune crossbow (u)
			9185    // Rune crossbow
		);

		// ==================== TOXIC TRIDENT UPGRADES ====================

		addVariantGroup(
			22288,  // Trident of the seas (e)
			22290,  // Trident of the seas (e) partial
			22292   // Trident of the swamp (e)
		);

		// ==================== KRAKEN TENTACLE WHIP ====================

		addVariantGroup(
			12004,  // Kraken tentacle
			12006   // Abyssal tentacle
		);

		// ==================== BLESSED SARA SWORD ====================

		addVariantGroup(
			12804,  // Saradomin's blessed sword
			12809   // Saradomin's blessed sword (charged)
		);

		// ==================== CLUE SCROLL ORNAMENT KITS ====================

		// Dark bow ornament kits
		addVariantGroup(
			20408,  // Dark bow (ornament kit)
			12765,  // Dark bow (green)
			12766,  // Dark bow (blue)
			12767,  // Dark bow (yellow)
			12768   // Dark bow (white)
		);

		// ==================== TENTACLE POOL ITEMS ====================

		addVariantGroup(
			12771,  // Frozen abyssal whip
			12773,  // Volcanic abyssal whip
			4151    // Abyssal whip
		);

		// ==================== MISCELLANIA/ETCETERIA ====================

		addVariantGroup(
			7936,   // Ring of charos
			6465    // Ring of charos (a)
		);

		// ==================== LUNAR EQUIPMENT ====================

		addVariantGroup(
			9084,   // Lunar helm
			9085,   // Lunar torso
			9086,   // Lunar legs
			9087,   // Lunar gloves
			9088    // Lunar boots
		);

		// ==================== IMBUED RINGS (Emir's Arena) ====================

		addVariantGroup(
			26684,  // Berserker ring (i) (Emir's Arena)
			26685,  // Archers ring (i) (Emir's Arena)
			26686,  // Seers ring (i) (Emir's Arena)
			26687,  // Warrior ring (i) (Emir's Arena)
			26688,  // Tyrannical ring (i) (Emir's Arena)
			26689,  // Treasonous ring (i) (Emir's Arena)
			26690   // Ring of the gods (i) (Emir's Arena)
		);

		// ==================== GILDED PICKAXE/AXE ====================

		addVariantGroup(
			23276,  // Gilded pickaxe
			11920,  // Dragon pickaxe
			12797   // Dragon pickaxe (or)
		);

		addVariantGroup(
			23279,  // Gilded axe
			6739,   // Dragon axe
			12807   // Dragon axe (or)
		);

		// ==================== ENCHANTED SLAYER HELM ====================

		addVariantGroup(
			11864,  // Slayer helmet
			11865,  // Slayer helmet (i)
			25177   // Slayer helmet (i) (Soul Wars)
		);

		// ==================== PET MORPHS (Olmlet) ====================

		addVariantGroup(
			20851,  // Olmlet
			24486,  // Olmlet (small)
			24488,  // Olmlet (younger)
			24490   // Olmlet (cm)
		);

		// ==================== PET MORPHS (Snakeling) ====================

		addVariantGroup(
			12921,  // Snakeling
			12939,  // Snakeling (green)
			12940,  // Snakeling (red)
			13178   // Snakeling (tanzanite)
		);

		// ==================== LEAGUES - RELIC HUNTER OUTFITS ====================

		// Twisted Relic Hunter (T1, T2, T3) - Hood
		addVariantGroup(
			24413,  // Twisted relic hunter (t1) hood
			24416,  // Twisted relic hunter (t2) hood
			24419   // Twisted relic hunter (t3) hood
		);

		// Twisted Relic Hunter - Top
		addVariantGroup(
			24414,  // Twisted relic hunter (t1) top
			24417,  // Twisted relic hunter (t2) top
			24420   // Twisted relic hunter (t3) top
		);

		// Twisted Relic Hunter - Trousers
		addVariantGroup(
			24415,  // Twisted relic hunter (t1) trousers
			24418,  // Twisted relic hunter (t2) trousers
			24421   // Twisted relic hunter (t3) trousers
		);

		// Trailblazer Relic Hunter (T1, T2, T3) - Hood
		addVariantGroup(
			25044,  // Trailblazer relic hunter (t1) hood
			25047,  // Trailblazer relic hunter (t2) hood
			25050   // Trailblazer relic hunter (t3) hood
		);

		// Trailblazer Relic Hunter - Top
		addVariantGroup(
			25045,  // Trailblazer relic hunter (t1) top
			25048,  // Trailblazer relic hunter (t2) top
			25051   // Trailblazer relic hunter (t3) top
		);

		// Trailblazer Relic Hunter - Trousers
		addVariantGroup(
			25046,  // Trailblazer relic hunter (t1) trousers
			25049,  // Trailblazer relic hunter (t2) trousers
			25052   // Trailblazer relic hunter (t3) trousers
		);

		// Shattered Relic Hunter (T1, T2, T3) - Hood
		addVariantGroup(
			26851,  // Shattered relic hunter (t1) hood
			26854,  // Shattered relic hunter (t2) hood
			26857   // Shattered relic hunter (t3) hood
		);

		// Shattered Relic Hunter - Top
		addVariantGroup(
			26852,  // Shattered relic hunter (t1) top
			26855,  // Shattered relic hunter (t2) top
			26858   // Shattered relic hunter (t3) top
		);

		// Shattered Relic Hunter - Trousers
		addVariantGroup(
			26853,  // Shattered relic hunter (t1) trousers
			26856,  // Shattered relic hunter (t2) trousers
			26859   // Shattered relic hunter (t3) trousers
		);

		// Trailblazer Reloaded Relic Hunter (T1, T2, T3) - Hood
		addVariantGroup(
			28615,  // Trailblazer reloaded relic hunter (t1) hood
			28618,  // Trailblazer reloaded relic hunter (t2) hood
			28621   // Trailblazer reloaded relic hunter (t3) hood
		);

		// Trailblazer Reloaded Relic Hunter - Top
		addVariantGroup(
			28616,  // Trailblazer reloaded relic hunter (t1) top
			28619,  // Trailblazer reloaded relic hunter (t2) top
			28622   // Trailblazer reloaded relic hunter (t3) top
		);

		// Trailblazer Reloaded Relic Hunter - Trousers
		addVariantGroup(
			28617,  // Trailblazer reloaded relic hunter (t1) trousers
			28620,  // Trailblazer reloaded relic hunter (t2) trousers
			28623   // Trailblazer reloaded relic hunter (t3) trousers
		);

		// Raging Echoes Relic Hunter (T1, T2, T3) - Hood
		addVariantGroup(
			29800,  // Raging echoes relic hunter (t1) hood
			29803,  // Raging echoes relic hunter (t2) hood
			29806   // Raging echoes relic hunter (t3) hood
		);

		// Raging Echoes Relic Hunter - Top
		addVariantGroup(
			29801,  // Raging echoes relic hunter (t1) top
			29804,  // Raging echoes relic hunter (t2) top
			29807   // Raging echoes relic hunter (t3) top
		);

		// Raging Echoes Relic Hunter - Trousers
		addVariantGroup(
			29802,  // Raging echoes relic hunter (t1) trousers
			29805,  // Raging echoes relic hunter (t2) trousers
			29808   // Raging echoes relic hunter (t3) trousers
		);

		// ==================== LEAGUES - CANES AND BANNERS ====================

		// Leagues Canes
		addVariantGroup(
			24422,  // Twisted cane
			25053,  // Trailblazer cane
			26860,  // Shattered cane
			28624,  // Trailblazer reloaded torch
			29809   // Raging echoes cane
		);

		// Leagues Banners
		addVariantGroup(
			24410,  // Twisted banner
			25041,  // Trailblazer banner
			26848,  // Shattered banner
			28612,  // Trailblazer reloaded banner
			29797   // Raging echoes banner
		);

		// ==================== LEAGUES - TOOL ORNAMENT KITS ====================

		// Trailblazer tool (or) - Dragon pickaxe
		addVariantGroup(
			25063,  // Trailblazer dragon pickaxe (or)
			25064,  // Trailblazer infernal pickaxe (or)
			11920,  // Dragon pickaxe
			12797   // Dragon pickaxe (or)
		);

		// Trailblazer tool (or) - Dragon axe
		addVariantGroup(
			25059,  // Trailblazer dragon axe (or)
			25060,  // Trailblazer infernal axe (or)
			6739,   // Dragon axe
			12807   // Dragon axe (or)
		);

		// Trailblazer tool (or) - Dragon harpoon
		addVariantGroup(
			25061,  // Trailblazer dragon harpoon (or)
			25062,  // Trailblazer infernal harpoon (or)
			21028,  // Dragon harpoon
			21031   // Dragon harpoon (or)
		);

		// ==================== LEAGUES - SHATTERED RELICS VARIETY ORNAMENT ====================

		// Shattered abyssal whip (or)
		addVariantGroup(
			26865,  // Shattered abyssal whip (or)
			4151,   // Abyssal whip
			12773,  // Volcanic abyssal whip
			12774   // Frozen abyssal whip
		);

		// Shattered abyssal tentacle (or)
		addVariantGroup(
			26866,  // Shattered abyssal tentacle (or)
			12006   // Abyssal tentacle
		);

		// Shattered rune crossbow (or)
		addVariantGroup(
			26867,  // Shattered rune crossbow (or)
			9185,   // Rune crossbow
			23601   // Rune crossbow (or)
		);

		// ==================== LEAGUES - SHATTERED VOID ORNAMENT KIT ====================

		// Shattered void melee helm
		addVariantGroup(
			26876,  // Shattered void melee helm
			11663   // Void melee helm
		);

		// Shattered void ranger helm
		addVariantGroup(
			26877,  // Shattered void ranger helm
			11664   // Void ranger helm
		);

		// Shattered void mage helm
		addVariantGroup(
			26878,  // Shattered void mage helm
			8842    // Void mage helm
		);

		// Shattered void knight top
		addVariantGroup(
			26879,  // Shattered void knight top
			8839,   // Void knight top
			13072   // Elite void top
		);

		// Shattered void knight robe
		addVariantGroup(
			26880,  // Shattered void knight robe
			8840,   // Void knight robe
			13073   // Elite void robe
		);

		// Shattered void knight gloves
		addVariantGroup(
			26881,  // Shattered void knight gloves
			8842    // Void knight gloves
		);

		// ==================== LEAGUES - RAGING ECHOES ORNAMENT KITS ====================

		// Echo Ahrim's robes
		addVariantGroup(
			29810,  // Echo Ahrim's hood
			4708    // Ahrim's hood 0
		);

		addVariantGroup(
			29811,  // Echo Ahrim's robetop
			4712    // Ahrim's robetop 0
		);

		addVariantGroup(
			29812,  // Echo Ahrim's robeskirt
			4714    // Ahrim's robeskirt 0
		);

		addVariantGroup(
			29813,  // Echo Ahrim's staff
			4710    // Ahrim's staff 0
		);

		// Echo Virtus robes
		addVariantGroup(
			29814,  // Echo Virtus mask
			26241   // Virtus mask
		);

		addVariantGroup(
			29815,  // Echo Virtus robe top
			26243   // Virtus robe top
		);

		addVariantGroup(
			29816,  // Echo Virtus robe bottom
			26245   // Virtus robe bottom
		);

		// ==================== CASTLE WARS DECORATIVE ARMOUR ====================

		// Decorative helms (Red, White, Gold)
		addVariantGroup(
			4071,   // Decorative helm (red)
			4506,   // Decorative helm (white)
			4511    // Decorative helm (gold)
		);

		// Decorative full helms (Red, White, Gold)
		addVariantGroup(
			11898,  // Decorative full helm (red)
			11899,  // Decorative full helm (white)
			11900   // Decorative full helm (gold)
		);

		// Decorative bodies (Red, White, Gold)
		addVariantGroup(
			4069,   // Decorative body (red)
			4504,   // Decorative body (white)
			4509    // Decorative body (gold)
		);

		// Decorative legs (Red, White, Gold)
		addVariantGroup(
			4070,   // Decorative legs (red)
			4505,   // Decorative legs (white)
			4510    // Decorative legs (gold)
		);

		// Decorative skirts (Red, White, Gold)
		addVariantGroup(
			11896,  // Decorative skirt (red)
			11897,  // Decorative skirt (white)
			11895   // Decorative skirt (gold)
		);

		// Decorative shields (Red, White, Gold)
		addVariantGroup(
			4072,   // Decorative shield (red)
			4507,   // Decorative shield (white)
			4512    // Decorative shield (gold)
		);

		// Decorative swords (Red, White, Gold)
		addVariantGroup(
			4068,   // Decorative sword (red)
			4503,   // Decorative sword (white)
			4508    // Decorative sword (gold)
		);

		// Decorative boots (Red, White, Gold)
		addVariantGroup(
			11901,  // Decorative boots (red)
			11902,  // Decorative boots (white)
			11903   // Decorative boots (gold)
		);

		// Castle Wars magic armour
		addVariantGroup(
			4513,   // Decorative magic hat (red)
			4514,   // Decorative magic hat (white)
			4515    // Decorative magic hat (gold)
		);

		addVariantGroup(
			4516,   // Decorative magic top (red)
			4517,   // Decorative magic top (white)
			4518    // Decorative magic top (gold)
		);

		addVariantGroup(
			4519,   // Decorative magic robe (red)
			4520,   // Decorative magic robe (white)
			4521    // Decorative magic robe (gold)
		);

		// Castle Wars ranged armour
		addVariantGroup(
			4522,   // Decorative ranged top (red)
			4523,   // Decorative ranged top (white)
			4524    // Decorative ranged top (gold)
		);

		addVariantGroup(
			4525,   // Decorative ranged legs (red)
			4526,   // Decorative ranged legs (white)
			4527    // Decorative ranged legs (gold)
		);

		addVariantGroup(
			4528,   // Decorative quiver (red)
			4529,   // Decorative quiver (white)
			4530    // Decorative quiver (gold)
		);

		// ==================== SHAYZIEN ARMOUR (5 TIERS) ====================

		// Shayzien helm (tiers 1-5)
		addVariantGroup(
			13357,  // Shayzien helm (1)
			13361,  // Shayzien helm (2)
			13365,  // Shayzien helm (3)
			13369,  // Shayzien helm (4)
			13373   // Shayzien helm (5)
		);

		// Shayzien body (tiers 1-5)
		addVariantGroup(
			13358,  // Shayzien platebody (1)
			13362,  // Shayzien platebody (2)
			13366,  // Shayzien platebody (3)
			13370,  // Shayzien platebody (4)
			13374   // Shayzien body (5)
		);

		// Shayzien greaves (tiers 1-5)
		addVariantGroup(
			13359,  // Shayzien greaves (1)
			13363,  // Shayzien greaves (2)
			13367,  // Shayzien greaves (3)
			13371,  // Shayzien greaves (4)
			13375   // Shayzien greaves (5)
		);

		// Shayzien gloves (tiers 1-5)
		addVariantGroup(
			13360,  // Shayzien gloves (1)
			13364,  // Shayzien gloves (2)
			13368,  // Shayzien gloves (3)
			13372,  // Shayzien gloves (4)
			13376   // Shayzien gloves (5)
		);

		// Shayzien boots (tiers 1-5)
		addVariantGroup(
			13356,  // Shayzien boots (1)
			13355,  // Shayzien boots (2)
			13354,  // Shayzien boots (3)
			13353,  // Shayzien boots (4)
			13377   // Shayzien boots (5)
		);

		// ==================== TRIMMED ARMOUR (CLUE SCROLL) ====================

		// Bronze trimmed/gold-trimmed
		addVariantGroup(
			12211,  // Bronze full helm (t)
			12221,  // Bronze full helm (g)
			1155    // Bronze full helm
		);

		addVariantGroup(
			12215,  // Bronze platebody (t)
			12225,  // Bronze platebody (g)
			1117    // Bronze platebody
		);

		addVariantGroup(
			12217,  // Bronze platelegs (t)
			12227,  // Bronze platelegs (g)
			1075    // Bronze platelegs
		);

		addVariantGroup(
			12219,  // Bronze plateskirt (t)
			12229,  // Bronze plateskirt (g)
			1087    // Bronze plateskirt
		);

		addVariantGroup(
			12213,  // Bronze kiteshield (t)
			12223,  // Bronze kiteshield (g)
			1189    // Bronze kiteshield
		);

		// Iron trimmed/gold-trimmed
		addVariantGroup(
			12231,  // Iron full helm (t)
			12241,  // Iron full helm (g)
			1153    // Iron full helm
		);

		addVariantGroup(
			12235,  // Iron platebody (t)
			12245,  // Iron platebody (g)
			1115    // Iron platebody
		);

		addVariantGroup(
			12237,  // Iron platelegs (t)
			12247,  // Iron platelegs (g)
			1067    // Iron platelegs
		);

		addVariantGroup(
			12239,  // Iron plateskirt (t)
			12249,  // Iron plateskirt (g)
			1081    // Iron plateskirt
		);

		addVariantGroup(
			12233,  // Iron kiteshield (t)
			12243,  // Iron kiteshield (g)
			1191    // Iron kiteshield
		);

		// Steel trimmed/gold-trimmed
		addVariantGroup(
			20169,  // Steel full helm (t)
			20172,  // Steel full helm (g)
			1157    // Steel full helm
		);

		addVariantGroup(
			20178,  // Steel platebody (t)
			20181,  // Steel platebody (g)
			1119    // Steel platebody
		);

		addVariantGroup(
			20184,  // Steel platelegs (t)
			20187,  // Steel platelegs (g)
			1069    // Steel platelegs
		);

		addVariantGroup(
			20190,  // Steel plateskirt (t)
			20193,  // Steel plateskirt (g)
			1083    // Steel plateskirt
		);

		addVariantGroup(
			20175,  // Steel kiteshield (t)
			20196,  // Steel kiteshield (g)
			1193    // Steel kiteshield
		);

		// Black trimmed/gold-trimmed
		addVariantGroup(
			2587,   // Black full helm (t)
			2595,   // Black full helm (g)
			1165    // Black full helm
		);

		addVariantGroup(
			2583,   // Black platebody (t)
			2591,   // Black platebody (g)
			1125    // Black platebody
		);

		addVariantGroup(
			2585,   // Black platelegs (t)
			2593,   // Black platelegs (g)
			1077    // Black platelegs
		);

		addVariantGroup(
			3472,   // Black plateskirt (t)
			3473,   // Black plateskirt (g)
			1089    // Black plateskirt
		);

		addVariantGroup(
			2589,   // Black kiteshield (t)
			2597,   // Black kiteshield (g)
			1195    // Black kiteshield
		);

		// Mithril trimmed/gold-trimmed
		addVariantGroup(
			12283,  // Mithril full helm (t)
			12293,  // Mithril full helm (g)
			1159    // Mithril full helm
		);

		addVariantGroup(
			12287,  // Mithril platebody (t)
			12297,  // Mithril platebody (g)
			1121    // Mithril platebody
		);

		addVariantGroup(
			12289,  // Mithril platelegs (t)
			12299,  // Mithril platelegs (g)
			1071    // Mithril platelegs
		);

		addVariantGroup(
			12291,  // Mithril plateskirt (t)
			12301,  // Mithril plateskirt (g)
			1085    // Mithril plateskirt
		);

		addVariantGroup(
			12285,  // Mithril kiteshield (t)
			12295,  // Mithril kiteshield (g)
			1197    // Mithril kiteshield
		);

		// Adamant trimmed/gold-trimmed
		addVariantGroup(
			2605,   // Adamant full helm (t)
			2613,   // Adamant full helm (g)
			1161    // Adamant full helm
		);

		addVariantGroup(
			2599,   // Adamant platebody (t)
			2607,   // Adamant platebody (g)
			1123    // Adamant platebody
		);

		addVariantGroup(
			2601,   // Adamant platelegs (t)
			2609,   // Adamant platelegs (g)
			1073    // Adamant platelegs
		);

		addVariantGroup(
			3474,   // Adamant plateskirt (t)
			3475,   // Adamant plateskirt (g)
			1091    // Adamant plateskirt
		);

		addVariantGroup(
			2603,   // Adamant kiteshield (t)
			2611,   // Adamant kiteshield (g)
			1199    // Adamant kiteshield
		);

		// Rune trimmed/gold-trimmed/god
		addVariantGroup(
			2627,   // Rune full helm (t)
			2619,   // Rune full helm (g)
			1163,   // Rune full helm
			2657,   // Saradomin full helm
			2665,   // Zamorak full helm
			2673,   // Guthix full helm
			12466,  // Ancient full helm
			12468,  // Armadyl full helm
			12470   // Bandos full helm
		);

		addVariantGroup(
			2623,   // Rune platebody (t)
			2615,   // Rune platebody (g)
			1127,   // Rune platebody
			2653,   // Saradomin platebody
			2661,   // Zamorak platebody
			2669,   // Guthix platebody
			12460,  // Ancient platebody
			12462,  // Armadyl platebody
			12464   // Bandos platebody
		);

		addVariantGroup(
			2625,   // Rune platelegs (t)
			2617,   // Rune platelegs (g)
			1079,   // Rune platelegs
			2655,   // Saradomin platelegs
			2663,   // Zamorak platelegs
			2671,   // Guthix platelegs
			12472,  // Ancient platelegs
			12474,  // Armadyl platelegs
			12476   // Bandos platelegs
		);

		addVariantGroup(
			3477,   // Rune plateskirt (t)
			3476,   // Rune plateskirt (g)
			1093,   // Rune plateskirt
			2659,   // Saradomin plateskirt
			2667,   // Zamorak plateskirt
			2675,   // Guthix plateskirt
			12478,  // Ancient plateskirt
			12480,  // Armadyl plateskirt
			12482   // Bandos plateskirt
		);

		addVariantGroup(
			2629,   // Rune kiteshield (t)
			2621,   // Rune kiteshield (g)
			1201,   // Rune kiteshield
			2651,   // Saradomin kiteshield
			2659,   // Zamorak kiteshield
			2677,   // Guthix kiteshield
			12484,  // Ancient kiteshield
			12486,  // Armadyl kiteshield
			12488   // Bandos kiteshield
		);

		// ==================== GILDED ARMOUR ====================

		addVariantGroup(
			3481,   // Gilded full helm
			1163    // Rune full helm
		);

		addVariantGroup(
			3483,   // Gilded platebody
			1127    // Rune platebody
		);

		addVariantGroup(
			3485,   // Gilded platelegs
			1079    // Rune platelegs
		);

		addVariantGroup(
			3488,   // Gilded plateskirt
			1093    // Rune plateskirt
		);

		addVariantGroup(
			3486,   // Gilded kiteshield
			1201    // Rune kiteshield
		);

		addVariantGroup(
			12153,  // Gilded med helm
			1147    // Rune med helm
		);

		addVariantGroup(
			12155,  // Gilded chainbody
			1113    // Rune chainbody
		);

		addVariantGroup(
			12157,  // Gilded sq shield
			1185    // Rune sq shield
		);

		addVariantGroup(
			20146,  // Gilded 2h sword
			1319    // Rune 2h sword
		);

		addVariantGroup(
			23279,  // Gilded axe
			6739    // Dragon axe
		);

		addVariantGroup(
			23276,  // Gilded pickaxe
			11920   // Dragon pickaxe
		);

		addVariantGroup(
			23282,  // Gilded spade
			952     // Spade
		);

		// ==================== 3RD AGE EQUIPMENT ====================

		// 3rd age melee
		addVariantGroup(
			10350,  // 3rd age full helmet
			10348,  // 3rd age platebody
			10346,  // 3rd age platelegs
			10352   // 3rd age kiteshield
		);

		// 3rd age range
		addVariantGroup(
			10334,  // 3rd age range coif
			10330,  // 3rd age range top
			10332,  // 3rd age range legs
			10336   // 3rd age vambraces
		);

		// 3rd age mage
		addVariantGroup(
			10342,  // 3rd age mage hat
			10338,  // 3rd age robe top
			10340,  // 3rd age robe
			10344   // 3rd age amulet
		);

		// 3rd age weapons
		addVariantGroup(
			12426,  // 3rd age longsword
			12422,  // 3rd age wand
			12424,  // 3rd age bow
			12428   // 3rd age cloak
		);

		// 3rd age druidic
		addVariantGroup(
			23345,  // 3rd age druidic robe top
			23348,  // 3rd age druidic robe bottom
			23351,  // 3rd age druidic staff
			23354   // 3rd age druidic cloak
		);

		// 3rd age tools
		addVariantGroup(
			20011,  // 3rd age pickaxe
			20014,  // 3rd age axe
			23360   // 3rd age ring
		);

		// ==================== HERALDIC ARMOUR (H1-H5) ====================

		// Black heraldic
		addVariantGroup(
			7332,   // Black helm (h1)
			7338,   // Black helm (h2)
			7344,   // Black helm (h3)
			7350,   // Black helm (h4)
			7356    // Black helm (h5)
		);

		addVariantGroup(
			7334,   // Black body (h1)
			7340,   // Black body (h2)
			7346,   // Black body (h3)
			7352,   // Black body (h4)
			7358    // Black body (h5)
		);

		addVariantGroup(
			7336,   // Black shield (h1)
			7342,   // Black shield (h2)
			7348,   // Black shield (h3)
			7354,   // Black shield (h4)
			7360    // Black shield (h5)
		);

		// Adamant heraldic
		addVariantGroup(
			7362,   // Adamant helm (h1)
			7368,   // Adamant helm (h2)
			7374,   // Adamant helm (h3)
			7380,   // Adamant helm (h4)
			7386    // Adamant helm (h5)
		);

		addVariantGroup(
			7364,   // Adamant body (h1)
			7370,   // Adamant body (h2)
			7376,   // Adamant body (h3)
			7382,   // Adamant body (h4)
			7388    // Adamant body (h5)
		);

		addVariantGroup(
			7366,   // Adamant shield (h1)
			7372,   // Adamant shield (h2)
			7378,   // Adamant shield (h3)
			7384,   // Adamant shield (h4)
			7390    // Adamant shield (h5)
		);

		// Rune heraldic
		addVariantGroup(
			7392,   // Rune helm (h1)
			7398,   // Rune helm (h2)
			7404,   // Rune helm (h3)
			7410,   // Rune helm (h4)
			7416    // Rune helm (h5)
		);

		addVariantGroup(
			7394,   // Rune body (h1)
			7400,   // Rune body (h2)
			7406,   // Rune body (h3)
			7412,   // Rune body (h4)
			7418    // Rune body (h5)
		);

		addVariantGroup(
			7396,   // Rune shield (h1)
			7402,   // Rune shield (h2)
			7408,   // Rune shield (h3)
			7414,   // Rune shield (h4)
			7420    // Rune shield (h5)
		);

		// ==================== DRAGONHIDE TRIMMED/BLESSED ====================

		// Green d'hide trimmed
		addVariantGroup(
			7370,   // Green d'hide body (t)
			7372,   // Green d'hide body (g)
			1135    // Green d'hide body
		);

		addVariantGroup(
			7378,   // Green d'hide chaps (t)
			7380,   // Green d'hide chaps (g)
			1099    // Green d'hide chaps
		);

		// Blue d'hide trimmed
		addVariantGroup(
			7374,   // Blue d'hide body (t)
			7376,   // Blue d'hide body (g)
			2499    // Blue d'hide body
		);

		addVariantGroup(
			7382,   // Blue d'hide chaps (t)
			7384,   // Blue d'hide chaps (g)
			2493    // Blue d'hide chaps
		);

		// Red d'hide trimmed
		addVariantGroup(
			2501,   // Red d'hide body (t)
			2503,   // Red d'hide body (g)
			2501    // Red d'hide body
		);

		// Black d'hide trimmed
		addVariantGroup(
			12381,  // Black d'hide body (t)
			12383,  // Black d'hide body (g)
			2503    // Black d'hide body
		);

		addVariantGroup(
			12385,  // Black d'hide chaps (t)
			12387,  // Black d'hide chaps (g)
			2497    // Black d'hide chaps
		);

		// ==================== BLESSED DRAGONHIDE ====================

		// Saradomin blessed d'hide
		addVariantGroup(
			10386,  // Saradomin coif
			10388,  // Saradomin d'hide body
			10390,  // Saradomin chaps
			10392,  // Saradomin bracers
			23195   // Saradomin d'hide boots
		);

		// Zamorak blessed d'hide
		addVariantGroup(
			10374,  // Zamorak coif
			10376,  // Zamorak d'hide body
			10378,  // Zamorak chaps
			10380,  // Zamorak bracers
			23192   // Zamorak d'hide boots
		);

		// Guthix blessed d'hide
		addVariantGroup(
			10382,  // Guthix coif
			10370,  // Guthix d'hide body
			10372,  // Guthix chaps
			10384,  // Guthix bracers
			23198   // Guthix d'hide boots
		);

		// Armadyl blessed d'hide
		addVariantGroup(
			12512,  // Armadyl coif
			12508,  // Armadyl d'hide body
			12510,  // Armadyl chaps
			12514,  // Armadyl bracers
			23201   // Armadyl d'hide boots
		);

		// Ancient blessed d'hide
		addVariantGroup(
			12494,  // Ancient coif
			12490,  // Ancient d'hide body
			12492,  // Ancient chaps
			12496,  // Ancient bracers
			23204   // Ancient d'hide boots
		);

		// Bandos blessed d'hide
		addVariantGroup(
			12500,  // Bandos coif
			12502,  // Bandos d'hide body
			12504,  // Bandos chaps
			12506,  // Bandos bracers
			23207   // Bandos d'hide boots
		);

		// ==================== HOLIDAY EVENT ITEMS ====================

		// Partyhats
		addVariantGroup(
			1038,   // Red partyhat
			1040,   // Yellow partyhat
			1042,   // Blue partyhat
			1044,   // Green partyhat
			1046,   // Purple partyhat
			1048,   // White partyhat
			13173,  // Rainbow partyhat
			13175,  // Black partyhat
			28614   // Silver partyhat
		);

		// Halloween masks
		addVariantGroup(
			1053,   // Green h'ween mask
			1055,   // Blue h'ween mask
			1057,   // Red h'ween mask
			11847   // Black h'ween mask
		);

		// Santa hats
		addVariantGroup(
			1050,   // Santa hat
			12887,  // Inverted santa hat
			12888   // Black santa hat
		);

		// ==================== STUDDED LEATHER (TRIMMED) ====================

		addVariantGroup(
			7362,   // Studded body (t)
			7364,   // Studded body (g)
			1133    // Studded body
		);

		addVariantGroup(
			7366,   // Studded chaps (t)
			7368,   // Studded chaps (g)
			1097    // Studded chaps
		);

		// ==================== WIZARD ROBES (TRIMMED) ====================

		// Blue wizard trimmed
		addVariantGroup(
			7386,   // Blue wizard hat (t)
			7388,   // Blue wizard hat (g)
			579     // Blue wizard hat
		);

		addVariantGroup(
			7390,   // Blue wizard robe (t)
			7392,   // Blue wizard robe (g)
			577     // Blue wizard robe
		);

		// Black wizard trimmed
		addVariantGroup(
			7394,   // Black wizard hat (t)
			7396,   // Black wizard hat (g)
			1011    // Black wizard hat
		);

		addVariantGroup(
			7398,   // Black wizard robe (t)
			7400,   // Black wizard robe (g)
			1015    // Black robe
		);

		// ==================== ELEGANT CLOTHING ====================

		// Blue elegant
		addVariantGroup(
			10400,  // Blue elegant shirt
			10402,  // Blue elegant legs
			10420   // Blue elegant skirt
		);

		// Red elegant
		addVariantGroup(
			10404,  // Red elegant shirt
			10406,  // Red elegant legs
			10422   // Red elegant skirt
		);

		// Green elegant
		addVariantGroup(
			10408,  // Green elegant shirt
			10410,  // Green elegant legs
			10424   // Green elegant skirt
		);

		// Black elegant
		addVariantGroup(
			10412,  // Black elegant shirt
			10414   // Black elegant legs
		);

		// White elegant
		addVariantGroup(
			10416,  // White elegant blouse
			10418   // White elegant skirt
		);

		// Purple elegant
		addVariantGroup(
			12319,  // Purple elegant shirt
			12321,  // Purple elegant legs
			12315   // Purple elegant skirt
		);

		// Pink elegant
		addVariantGroup(
			12323,  // Pink elegant shirt
			12325,  // Pink elegant legs
			12317   // Pink elegant skirt
		);

		// Gold elegant
		addVariantGroup(
			12347,  // Gold elegant shirt
			12349,  // Gold elegant legs
			12351   // Gold elegant skirt
		);
	}

	/**
	 * Add a group of item IDs that should be treated as equivalent.
	 */
	public void addVariantGroup(int... itemIds)
	{
		Set<Integer> group = new HashSet<>();
		for (int id : itemIds)
		{
			group.add(id);
		}

		// Map each item to this group
		for (int id : itemIds)
		{
			itemToGroup.put(id, group);
		}

		variantGroups.add(group);
	}

	/**
	 * Check if an item ID belongs to a variant group.
	 */
	public boolean isVariant(int itemId)
	{
		return itemToGroup.containsKey(itemId);
	}

	/**
	 * Get all item IDs in the same variant group as the given item.
	 * Returns null if the item is not part of any variant group.
	 */
	public Set<Integer> getVariantGroup(int itemId)
	{
		return itemToGroup.get(itemId);
	}

	/**
	 * Check if two items are variants of each other.
	 */
	public boolean areVariants(int itemId1, int itemId2)
	{
		Set<Integer> group = itemToGroup.get(itemId1);
		return group != null && group.contains(itemId2);
	}

	/**
	 * Get all variant groups.
	 */
	public List<Set<Integer>> getAllVariantGroups()
	{
		return variantGroups;
	}

	/**
	 * Get the total number of variant groups.
	 */
	public int getGroupCount()
	{
		return variantGroups.size();
	}

	/**
	 * Get the total number of items tracked.
	 */
	public int getItemCount()
	{
		return itemToGroup.size();
	}
}
