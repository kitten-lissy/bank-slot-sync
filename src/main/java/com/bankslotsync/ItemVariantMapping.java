package com.bankslotsync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.runelite.api.gameval.ItemID;

/**
 * Groups of item IDs that are versions of the same item (recolours, ornament kits,
 * charges, imbues, locked versions and a few upgrade chains such as Oathplate to
 * Radiant oathplate).
 *
 * <p>Every ID is written as a {@link ItemID} constant so the compiler checks that it
 * exists. Each group carries a label and a list of keywords ("A|B") that every constant
 * name in the group must contain one of. {@code ItemVariantMappingTest} reads this file
 * and enforces that, and also that no ID sits in more than one group.
 *
 * <p>Only bankable versions are listed: worn-only, noted, placeholder, broken and
 * Last Man Standing copies are left out.
 */
public class ItemVariantMapping
{
	// Maps each item ID to its variant group (all IDs in the same group)
	private final Map<Integer, Set<Integer>> itemToGroup = new HashMap<>();

	// Group label for each item ID, used in chat messages
	private final Map<Integer, String> itemToLabel = new HashMap<>();

	// List of all variant groups for reference
	private final List<Set<Integer>> variantGroups = new ArrayList<>();

	public ItemVariantMapping()
	{
		initializeVariantGroups();
	}

	private void initializeVariantGroups()
	{
		// ==================== ABYSSAL WHIP ====================

		group("Abyssal whip", "WHIP",
			ItemID.ABYSSAL_WHIP,                                     // Abyssal whip
			ItemID.ABYSSAL_WHIP_LAVA,                                // Volcanic abyssal whip
			ItemID.ABYSSAL_WHIP_ICE,                                 // Frozen abyssal whip
			ItemID.LEAGUE_3_WHIP                                     // Abyssal whip (or)
		);

		// ==================== ABYSSAL TENTACLE ====================

		group("Abyssal tentacle", "TENTACLE",
			ItemID.ABYSSAL_TENTACLE,                                 // Abyssal tentacle
			ItemID.LEAGUE_3_WHIP_TENTACLE,                           // Abyssal tentacle (or)
			ItemID.KRAKEN_TENTACLE                                   // Kraken tentacle
		);

		// ==================== DARK BOW ====================

		group("Dark bow", "DARKBOW",
			ItemID.DARKBOW,                                          // Dark bow
			ItemID.DARKBOW_GREEN,                                    // Dark bow
			ItemID.DARKBOW_BLUE,                                     // Dark bow
			ItemID.DARKBOW_YELLOW,                                   // Dark bow
			ItemID.DARKBOW_WHITE                                     // Dark bow
		);

		// ==================== TOOLS ====================

		group("Dragon pickaxe, Infernal pickaxe and Crystal pickaxe", "PICKAXE",
			ItemID.DRAGON_PICKAXE,                                   // Dragon pickaxe
			ItemID.DRAGON_PICKAXE_PRETTY,                            // Dragon pickaxe
			ItemID.ZALCANO_PICKAXE,                                  // Dragon pickaxe (or)
			ItemID.INFERNAL_PICKAXE,                                 // Infernal pickaxe
			ItemID.CRYSTAL_PICKAXE,                                  // Crystal pickaxe
			ItemID.TRAILBLAZER_PICKAXE,                              // Infernal pickaxe (or)
			ItemID.INFERNAL_PICKAXE_EMPTY,                           // Infernal pickaxe (uncharged)
			ItemID.CRYSTAL_PICKAXE_INACTIVE,                         // Crystal pickaxe (inactive)
			ItemID.TRAILBLAZER_PICKAXE_EMPTY,                        // Infernal pickaxe (uncharged)
			ItemID.TRAILBLAZER_PICKAXE_NO_INFERNAL,                  // Dragon pickaxe (or)
			ItemID.TRAILBLAZER_RELOADED_PICKAXE,                     // Infernal pickaxe (or)
			ItemID.TRAILBLAZER_RELOADED_PICKAXE_EMPTY,               // Infernal pickaxe (uncharged)
			ItemID.TRAILBLAZER_RELOADED_PICKAXE_NO_INFERNAL          // Dragon pickaxe (or)
		);

		group("Dragon axe, Infernal axe and Crystal axe", "AXE",
			ItemID.DRAGON_AXE,                                       // Dragon axe
			ItemID.TRAILBLAZER_AXE_NO_INFERNAL,                      // Dragon axe (or)
			ItemID.TRAILBLAZER_RELOADED_AXE_NO_INFERNAL,             // Dragon axe (or)
			ItemID.INFERNAL_AXE,                                     // Infernal axe
			ItemID.INFERNAL_AXE_EMPTY,                               // Infernal axe (uncharged)
			ItemID.TRAILBLAZER_AXE_EMPTY,                            // Infernal axe (uncharged)
			ItemID.TRAILBLAZER_RELOADED_AXE_EMPTY,                   // Infernal axe (uncharged)
			ItemID.CRYSTAL_AXE,                                      // Crystal axe
			ItemID.GAUNTLET_AXE,                                     // Crystal axe
			ItemID.CRYSTAL_AXE_INACTIVE,                             // Crystal axe (inactive)
			ItemID.TRAILBLAZER_AXE,                                  // Infernal axe (or)
			ItemID.TRAILBLAZER_RELOADED_AXE                          // Infernal axe (or)
		);

		group("Dragon harpoon, Infernal harpoon and Crystal harpoon", "HARPOON",
			ItemID.DRAGON_HARPOON,                                   // Dragon harpoon
			ItemID.INFERNAL_HARPOON,                                 // Infernal harpoon
			ItemID.CRYSTAL_HARPOON,                                  // Crystal harpoon
			ItemID.INFERNAL_HARPOON_EMPTY,                           // Infernal harpoon (uncharged)
			ItemID.CRYSTAL_HARPOON_INACTIVE,                         // Crystal harpoon (inactive)
			ItemID.TRAILBLAZER_HARPOON,                              // Infernal harpoon (or)
			ItemID.TRAILBLAZER_HARPOON_EMPTY,                        // Infernal harpoon (uncharged)
			ItemID.TRAILBLAZER_HARPOON_NO_INFERNAL,                  // Dragon harpoon (or)
			ItemID.TRAILBLAZER_RELOADED_HARPOON,                     // Infernal harpoon (or)
			ItemID.TRAILBLAZER_RELOADED_HARPOON_EMPTY,               // Infernal harpoon (uncharged)
			ItemID.TRAILBLAZER_RELOADED_HARPOON_NO_INFERNAL          // Dragon harpoon (or)
		);

		// ==================== DRAGON EQUIPMENT ====================

		group("Dragon defender", "DRAGON_PARRYINGDAGGER",
			ItemID.DRAGON_PARRYINGDAGGER,                            // Dragon defender
			ItemID.DRAGON_PARRYINGDAGGER_T,                          // Dragon defender (t)
			ItemID.DRAGON_PARRYINGDAGGER_TROUVER                     // Dragon defender (l)
		);

		group("Rune defender", "RUNE_PARRYINGDAGGER",
			ItemID.RUNE_PARRYINGDAGGER,                              // Rune defender
			ItemID.RUNE_PARRYINGDAGGER_T,                            // Rune defender (t)
			ItemID.RUNE_PARRYINGDAGGER_TROUVER                       // Rune defender (l)
		);

		group("Dragon scimitar", "DRAGON_SCIMITAR",
			ItemID.DRAGON_SCIMITAR,                                  // Dragon scimitar
			ItemID.DRAGON_SCIMITAR_ORNAMENT                          // Dragon scimitar (or)
		);

		group("Dragon boots", "DRAGON_BOOTS",
			ItemID.DRAGON_BOOTS,                                     // Dragon boots
			ItemID.DRAGON_BOOTS_GOLD                                 // Dragon boots (g)
		);

		group("Dragon chainbody", "DRAGON_CHAINBODY",
			ItemID.DRAGON_CHAINBODY,                                 // Dragon chainbody
			ItemID.DRAGON_CHAINBODY_GOLD                             // Dragon chainbody (g)
		);

		group("Dragon platebody", "DRAGON_PLATEBODY",
			ItemID.DRAGON_PLATEBODY,                                 // Dragon platebody
			ItemID.DRAGON_PLATEBODY_GOLD                             // Dragon platebody (g)
		);

		group("Dragon full helm", "DRAGON_FULL_HELM",
			ItemID.BRUT_DRAGON_FULL_HELM,                            // Dragon full helm
			ItemID.DRAGON_FULL_HELM_GOLD                             // Dragon full helm (g)
		);

		group("Dragon platelegs", "DRAGON_PLATELEGS",
			ItemID.DRAGON_PLATELEGS,                                 // Dragon platelegs
			ItemID.DRAGON_PLATELEGS_GOLD                             // Dragon platelegs (g)
		);

		group("Dragon plateskirt", "DRAGON_PLATESKIRT",
			ItemID.DRAGON_PLATESKIRT,                                // Dragon plateskirt
			ItemID.DRAGON_PLATESKIRT_GOLD                            // Dragon plateskirt (g)
		);

		group("Dragon sq shield", "DRAGON_SQ_SHIELD",
			ItemID.DRAGON_SQ_SHIELD,                                 // Dragon sq shield
			ItemID.DRAGON_SQ_SHIELD_GOLD                             // Dragon sq shield (g)
		);

		group("Dragon kiteshield", "DRAGON_KITESHIELD",
			ItemID.DRAGON_KITESHIELD,                                // Dragon kiteshield
			ItemID.DRAGON_KITESHIELD_GOLD                            // Dragon kiteshield (g)
		);

		// ==================== FIGHTER TORSO ====================

		group("Fighter torso", "FIGHTER_TORSO",
			ItemID.BARBASSAULT_PENANCE_FIGHTER_TORSO,                // Fighter torso
			ItemID.BH_BARBASSAULT_PENANCE_FIGHTER_TORSO_CORRUPTED,   // Fighter torso (or)
			ItemID.BARBASSAULT_PENANCE_FIGHTER_TORSO_TROUVER         // Fighter torso (l)
		);

		// ==================== DINH'S BULWARK ====================

		group("Dinh's bulwark", "DINHS_BULWARK",
			ItemID.DINHS_BULWARK,                                    // Dinh's bulwark
			ItemID.DINHS_BULWARK_ORNAMENT,                           // Dinh's blazing bulwark
			ItemID.ROTTEN_DINHS_BULWARK                              // Dinh's blazing bulwark
		);

		// ==================== BARROWS EQUIPMENT ====================

		group("Ahrim's hood", "AHRIM_HEAD",
			ItemID.BARROWS_AHRIM_HEAD,                               // Ahrim's hood
			ItemID.BARROWS_AHRIM_HEAD_100,                           // Ahrim's hood 100
			ItemID.BARROWS_AHRIM_HEAD_75,                            // Ahrim's hood 75
			ItemID.BARROWS_AHRIM_HEAD_50,                            // Ahrim's hood 50
			ItemID.BARROWS_AHRIM_HEAD_25,                            // Ahrim's hood 25
			ItemID.BARROWS_AHRIM_HEAD_ORNAMENT                       // Echo ahrim's hood
		);

		group("Ahrim's staff", "AHRIM_WEAPON",
			ItemID.BARROWS_AHRIM_WEAPON,                             // Ahrim's staff
			ItemID.BARROWS_AHRIM_WEAPON_100,                         // Ahrim's staff 100
			ItemID.BARROWS_AHRIM_WEAPON_75,                          // Ahrim's staff 75
			ItemID.BARROWS_AHRIM_WEAPON_50,                          // Ahrim's staff 50
			ItemID.BARROWS_AHRIM_WEAPON_25,                          // Ahrim's staff 25
			ItemID.BARROWS_AHRIM_WEAPON_ORNAMENT                     // Echo ahrim's staff
		);

		group("Ahrim's robetop", "AHRIM_BODY",
			ItemID.BARROWS_AHRIM_BODY,                               // Ahrim's robetop
			ItemID.BARROWS_AHRIM_BODY_100,                           // Ahrim's robetop 100
			ItemID.BARROWS_AHRIM_BODY_75,                            // Ahrim's robetop 75
			ItemID.BARROWS_AHRIM_BODY_50,                            // Ahrim's robetop 50
			ItemID.BARROWS_AHRIM_BODY_25,                            // Ahrim's robetop 25
			ItemID.BARROWS_AHRIM_BODY_ORNAMENT                       // Echo ahrim's robetop
		);

		group("Ahrim's robeskirt", "AHRIM_LEGS",
			ItemID.BARROWS_AHRIM_LEGS,                               // Ahrim's robeskirt
			ItemID.BARROWS_AHRIM_LEGS_100,                           // Ahrim's robeskirt 100
			ItemID.BARROWS_AHRIM_LEGS_75,                            // Ahrim's robeskirt 75
			ItemID.BARROWS_AHRIM_LEGS_50,                            // Ahrim's robeskirt 50
			ItemID.BARROWS_AHRIM_LEGS_25,                            // Ahrim's robeskirt 25
			ItemID.BARROWS_AHRIM_LEGS_ORNAMENT                       // Echo ahrim's robeskirt
		);

		group("Dharok's helm", "BARROWS_DHAROK_HEAD",
			ItemID.BARROWS_DHAROK_HEAD,                              // Dharok's helm
			ItemID.BARROWS_DHAROK_HEAD_100,                          // Dharok's helm 100
			ItemID.BARROWS_DHAROK_HEAD_75,                           // Dharok's helm 75
			ItemID.BARROWS_DHAROK_HEAD_50,                           // Dharok's helm 50
			ItemID.BARROWS_DHAROK_HEAD_25                            // Dharok's helm 25
		);

		group("Dharok's greataxe", "BARROWS_DHAROK_WEAPON",
			ItemID.BARROWS_DHAROK_WEAPON,                            // Dharok's greataxe
			ItemID.BARROWS_DHAROK_WEAPON_100,                        // Dharok's greataxe 100
			ItemID.BARROWS_DHAROK_WEAPON_75,                         // Dharok's greataxe 75
			ItemID.BARROWS_DHAROK_WEAPON_50,                         // Dharok's greataxe 50
			ItemID.BARROWS_DHAROK_WEAPON_25                          // Dharok's greataxe 25
		);

		group("Dharok's platebody", "BARROWS_DHAROK_BODY",
			ItemID.BARROWS_DHAROK_BODY,                              // Dharok's platebody
			ItemID.BARROWS_DHAROK_BODY_100,                          // Dharok's platebody 100
			ItemID.BARROWS_DHAROK_BODY_75,                           // Dharok's platebody 75
			ItemID.BARROWS_DHAROK_BODY_50,                           // Dharok's platebody 50
			ItemID.BARROWS_DHAROK_BODY_25                            // Dharok's platebody 25
		);

		group("Dharok's platelegs", "BARROWS_DHAROK_LEGS",
			ItemID.BARROWS_DHAROK_LEGS,                              // Dharok's platelegs
			ItemID.BARROWS_DHAROK_LEGS_100,                          // Dharok's platelegs 100
			ItemID.BARROWS_DHAROK_LEGS_75,                           // Dharok's platelegs 75
			ItemID.BARROWS_DHAROK_LEGS_50,                           // Dharok's platelegs 50
			ItemID.BARROWS_DHAROK_LEGS_25                            // Dharok's platelegs 25
		);

		group("Guthan's helm", "BARROWS_GUTHAN_HEAD",
			ItemID.BARROWS_GUTHAN_HEAD,                              // Guthan's helm
			ItemID.BARROWS_GUTHAN_HEAD_100,                          // Guthan's helm 100
			ItemID.BARROWS_GUTHAN_HEAD_75,                           // Guthan's helm 75
			ItemID.BARROWS_GUTHAN_HEAD_50,                           // Guthan's helm 50
			ItemID.BARROWS_GUTHAN_HEAD_25                            // Guthan's helm 25
		);

		group("Guthan's warspear", "BARROWS_GUTHAN_WEAPON",
			ItemID.BARROWS_GUTHAN_WEAPON,                            // Guthan's warspear
			ItemID.BARROWS_GUTHAN_WEAPON_100,                        // Guthan's warspear 100
			ItemID.BARROWS_GUTHAN_WEAPON_75,                         // Guthan's warspear 75
			ItemID.BARROWS_GUTHAN_WEAPON_50,                         // Guthan's warspear 50
			ItemID.BARROWS_GUTHAN_WEAPON_25                          // Guthan's warspear 25
		);

		group("Guthan's platebody", "BARROWS_GUTHAN_BODY",
			ItemID.BARROWS_GUTHAN_BODY,                              // Guthan's platebody
			ItemID.BARROWS_GUTHAN_BODY_100,                          // Guthan's platebody 100
			ItemID.BARROWS_GUTHAN_BODY_75,                           // Guthan's platebody 75
			ItemID.BARROWS_GUTHAN_BODY_50,                           // Guthan's platebody 50
			ItemID.BARROWS_GUTHAN_BODY_25                            // Guthan's platebody 25
		);

		group("Guthan's chainskirt", "BARROWS_GUTHAN_LEGS",
			ItemID.BARROWS_GUTHAN_LEGS,                              // Guthan's chainskirt
			ItemID.BARROWS_GUTHAN_LEGS_100,                          // Guthan's chainskirt 100
			ItemID.BARROWS_GUTHAN_LEGS_75,                           // Guthan's chainskirt 75
			ItemID.BARROWS_GUTHAN_LEGS_50,                           // Guthan's chainskirt 50
			ItemID.BARROWS_GUTHAN_LEGS_25                            // Guthan's chainskirt 25
		);

		group("Karil's coif", "BARROWS_KARIL_HEAD",
			ItemID.BARROWS_KARIL_HEAD,                               // Karil's coif
			ItemID.BARROWS_KARIL_HEAD_100,                           // Karil's coif 100
			ItemID.BARROWS_KARIL_HEAD_75,                            // Karil's coif 75
			ItemID.BARROWS_KARIL_HEAD_50,                            // Karil's coif 50
			ItemID.BARROWS_KARIL_HEAD_25                             // Karil's coif 25
		);

		group("Karil's crossbow", "BARROWS_KARIL_WEAPON",
			ItemID.BARROWS_KARIL_WEAPON,                             // Karil's crossbow
			ItemID.BARROWS_KARIL_WEAPON_100,                         // Karil's crossbow 100
			ItemID.BARROWS_KARIL_WEAPON_75,                          // Karil's crossbow 75
			ItemID.BARROWS_KARIL_WEAPON_50,                          // Karil's crossbow 50
			ItemID.BARROWS_KARIL_WEAPON_25                           // Karil's crossbow 25
		);

		group("Karil's leathertop", "BARROWS_KARIL_BODY",
			ItemID.BARROWS_KARIL_BODY,                               // Karil's leathertop
			ItemID.BARROWS_KARIL_BODY_100,                           // Karil's leathertop 100
			ItemID.BARROWS_KARIL_BODY_75,                            // Karil's leathertop 75
			ItemID.BARROWS_KARIL_BODY_50,                            // Karil's leathertop 50
			ItemID.BARROWS_KARIL_BODY_25                             // Karil's leathertop 25
		);

		group("Karil's leatherskirt", "BARROWS_KARIL_LEGS",
			ItemID.BARROWS_KARIL_LEGS,                               // Karil's leatherskirt
			ItemID.BARROWS_KARIL_LEGS_100,                           // Karil's leatherskirt 100
			ItemID.BARROWS_KARIL_LEGS_75,                            // Karil's leatherskirt 75
			ItemID.BARROWS_KARIL_LEGS_50,                            // Karil's leatherskirt 50
			ItemID.BARROWS_KARIL_LEGS_25                             // Karil's leatherskirt 25
		);

		group("Torag's helm", "BARROWS_TORAG_HEAD",
			ItemID.BARROWS_TORAG_HEAD,                               // Torag's helm
			ItemID.BARROWS_TORAG_HEAD_100,                           // Torag's helm 100
			ItemID.BARROWS_TORAG_HEAD_75,                            // Torag's helm 75
			ItemID.BARROWS_TORAG_HEAD_50,                            // Torag's helm 50
			ItemID.BARROWS_TORAG_HEAD_25                             // Torag's helm 25
		);

		group("Torag's hammers", "BARROWS_TORAG_WEAPON",
			ItemID.BARROWS_TORAG_WEAPON,                             // Torag's hammers
			ItemID.BARROWS_TORAG_WEAPON_100,                         // Torag's hammers 100
			ItemID.BARROWS_TORAG_WEAPON_75,                          // Torag's hammers 75
			ItemID.BARROWS_TORAG_WEAPON_50,                          // Torag's hammers 50
			ItemID.BARROWS_TORAG_WEAPON_25                           // Torag's hammers 25
		);

		group("Torag's platebody", "BARROWS_TORAG_BODY",
			ItemID.BARROWS_TORAG_BODY,                               // Torag's platebody
			ItemID.BARROWS_TORAG_BODY_100,                           // Torag's platebody 100
			ItemID.BARROWS_TORAG_BODY_75,                            // Torag's platebody 75
			ItemID.BARROWS_TORAG_BODY_50,                            // Torag's platebody 50
			ItemID.BARROWS_TORAG_BODY_25                             // Torag's platebody 25
		);

		group("Torag's platelegs", "BARROWS_TORAG_LEGS",
			ItemID.BARROWS_TORAG_LEGS,                               // Torag's platelegs
			ItemID.BARROWS_TORAG_LEGS_100,                           // Torag's platelegs 100
			ItemID.BARROWS_TORAG_LEGS_75,                            // Torag's platelegs 75
			ItemID.BARROWS_TORAG_LEGS_50,                            // Torag's platelegs 50
			ItemID.BARROWS_TORAG_LEGS_25                             // Torag's platelegs 25
		);

		group("Verac's helm", "BARROWS_VERAC_HEAD",
			ItemID.BARROWS_VERAC_HEAD,                               // Verac's helm
			ItemID.BARROWS_VERAC_HEAD_100,                           // Verac's helm 100
			ItemID.BARROWS_VERAC_HEAD_75,                            // Verac's helm 75
			ItemID.BARROWS_VERAC_HEAD_50,                            // Verac's helm 50
			ItemID.BARROWS_VERAC_HEAD_25                             // Verac's helm 25
		);

		group("Verac's flail", "BARROWS_VERAC_WEAPON",
			ItemID.BARROWS_VERAC_WEAPON,                             // Verac's flail
			ItemID.BARROWS_VERAC_WEAPON_100,                         // Verac's flail 100
			ItemID.BARROWS_VERAC_WEAPON_75,                          // Verac's flail 75
			ItemID.BARROWS_VERAC_WEAPON_50,                          // Verac's flail 50
			ItemID.BARROWS_VERAC_WEAPON_25                           // Verac's flail 25
		);

		group("Verac's brassard", "BARROWS_VERAC_BODY",
			ItemID.BARROWS_VERAC_BODY,                               // Verac's brassard
			ItemID.BARROWS_VERAC_BODY_100,                           // Verac's brassard 100
			ItemID.BARROWS_VERAC_BODY_75,                            // Verac's brassard 75
			ItemID.BARROWS_VERAC_BODY_50,                            // Verac's brassard 50
			ItemID.BARROWS_VERAC_BODY_25                             // Verac's brassard 25
		);

		group("Verac's plateskirt", "BARROWS_VERAC_LEGS",
			ItemID.BARROWS_VERAC_LEGS,                               // Verac's plateskirt
			ItemID.BARROWS_VERAC_LEGS_100,                           // Verac's plateskirt 100
			ItemID.BARROWS_VERAC_LEGS_75,                            // Verac's plateskirt 75
			ItemID.BARROWS_VERAC_LEGS_50,                            // Verac's plateskirt 50
			ItemID.BARROWS_VERAC_LEGS_25                             // Verac's plateskirt 25
		);

		// ==================== AMULET OF GLORY ====================

		group("Amulet of glory", "AMULET_OF_GLORY",
			ItemID.AMULET_OF_GLORY,                                  // Amulet of glory
			ItemID.AMULET_OF_GLORY_1,                                // Amulet of glory(1)
			ItemID.AMULET_OF_GLORY_2,                                // Amulet of glory(2)
			ItemID.AMULET_OF_GLORY_3,                                // Amulet of glory(3)
			ItemID.AMULET_OF_GLORY_4,                                // Amulet of glory(4)
			ItemID.AMULET_OF_GLORY_5,                                // Amulet of glory(5)
			ItemID.AMULET_OF_GLORY_6,                                // Amulet of glory(6)
			ItemID.AMULET_OF_GLORY_INF                               // Amulet of eternal glory
		);

		// ==================== CROSSBOWS ====================

		group("Rune crossbow", "CROSSBOW_RUNITE|CROSSBOW_UNSTRUNG_RUNITE|RUNE_XBOW",
			ItemID.XBOWS_CROSSBOW_RUNITE,                            // Rune crossbow
			ItemID.XBOWS_CROSSBOW_UNSTRUNG_RUNITE,                   // Runite crossbow (u)
			ItemID.LEAGUE_3_RUNE_XBOW                                // Rune crossbow (or)
		);

		group("Dragon crossbow", "XBOWS_CROSSBOW_DRAGON",
			ItemID.XBOWS_CROSSBOW_DRAGON,                            // Dragon crossbow
			ItemID.BH_XBOWS_CROSSBOW_DRAGON_CORRUPTED                // Dragon crossbow (cr)
		);

		group("Dragon hunter crossbow", "DRAGONHUNTER_XBOW",
			ItemID.DRAGONHUNTER_XBOW,                                // Dragon hunter crossbow
			ItemID.DRAGONHUNTER_XBOW_VORKATH,                        // Dragon hunter crossbow (t)
			ItemID.DRAGONHUNTER_XBOW_KBD                             // Dragon hunter crossbow (b)
		);

		// ==================== TRIMMED ARMOUR (CLUE SCROLL) ====================

		group("Rune full helm (trimmed, god and gilded)", "FULL_HELM",
			ItemID.RUNE_FULL_HELM_TRIM,                              // Rune full helm (t)
			ItemID.RUNE_FULL_HELM_GOLD,                              // Rune full helm (g)
			ItemID.RUNE_FULL_HELM,                                   // Rune full helm
			ItemID.RUNE_FULL_HELM_ZAMORAK,                           // Zamorak full helm
			ItemID.RUNE_FULL_HELM_SARADOMIN,                         // Saradomin full helm
			ItemID.RUNE_FULL_HELM_GUTHIX,                            // Guthix full helm
			ItemID.RUNE_FULL_HELM_ANCIENT,                           // Ancient full helm
			ItemID.RUNE_FULL_HELM_ARMADYL,                           // Armadyl full helm
			ItemID.RUNE_FULL_HELM_BANDOS,                            // Bandos full helm
			ItemID.RUNE_FULL_HELM_GOLDPLATE                          // Gilded full helm
		);

		group("Rune platebody (trimmed, god and gilded)", "PLATEBODY",
			ItemID.RUNE_PLATEBODY_TRIM,                              // Rune platebody (t)
			ItemID.RUNE_PLATEBODY_GOLD,                              // Rune platebody (g)
			ItemID.RUNE_PLATEBODY,                                   // Rune platebody
			ItemID.RUNE_PLATEBODY_ZAMORAK,                           // Zamorak platebody
			ItemID.RUNE_PLATEBODY_SARADOMIN,                         // Saradomin platebody
			ItemID.RUNE_PLATEBODY_GUTHIX,                            // Guthix platebody
			ItemID.RUNE_PLATEBODY_ANCIENT,                           // Ancient platebody
			ItemID.RUNE_PLATEBODY_ARMADYL,                           // Armadyl platebody
			ItemID.RUNE_PLATEBODY_BANDOS,                            // Bandos platebody
			ItemID.RUNE_PLATEBODY_GOLDPLATE                          // Gilded platebody
		);

		group("Rune platelegs (trimmed, god and gilded)", "PLATELEGS",
			ItemID.RUNE_PLATELEGS_TRIM,                              // Rune platelegs (t)
			ItemID.RUNE_PLATELEGS_GOLD,                              // Rune platelegs (g)
			ItemID.RUNE_PLATELEGS,                                   // Rune platelegs
			ItemID.RUNE_PLATELEGS_ZAMORAK,                           // Zamorak platelegs
			ItemID.RUNE_PLATELEGS_SARADOMIN,                         // Saradomin platelegs
			ItemID.RUNE_PLATELEGS_GUTHIX,                            // Guthix platelegs
			ItemID.RUNE_PLATELEGS_ARMADYL,                           // Armadyl platelegs
			ItemID.RUNE_PLATELEGS_BANDOS,                            // Bandos platelegs
			ItemID.RUNE_PLATELEGS_GOLDPLATE                          // Gilded platelegs
		);

		group("Rune plateskirt (trimmed, god and gilded)", "PLATESKIRT",
			ItemID.RUNE_PLATESKIRT_TRIM,                             // Rune plateskirt (t)
			ItemID.RUNE_PLATESKIRT_GOLD,                             // Rune plateskirt (g)
			ItemID.RUNE_PLATESKIRT,                                  // Rune plateskirt
			ItemID.RUNE_PLATESKIRT_SARADOMIN,                        // Saradomin plateskirt
			ItemID.RUNE_PLATESKIRT_ZAMORAK,                          // Zamorak plateskirt
			ItemID.RUNE_PLATESKIRT_GUTHIX,                           // Guthix plateskirt
			ItemID.RUNE_PLATESKIRT_ANCIENT,                          // Ancient plateskirt
			ItemID.RUNE_PLATESKIRT_ARMADYL,                          // Armadyl plateskirt
			ItemID.RUNE_PLATESKIRT_BANDOS,                           // Bandos plateskirt
			ItemID.RUNE_PLATESKIRT_GOLDPLATE                         // Gilded plateskirt
		);

		group("Rune kiteshield (trimmed, god and gilded)", "KITESHIELD",
			ItemID.RUNE_KITESHIELD_TRIM,                             // Rune kiteshield (t)
			ItemID.RUNE_KITESHIELD_GOLD,                             // Rune kiteshield (g)
			ItemID.RUNE_KITESHIELD,                                  // Rune kiteshield
			ItemID.RUNE_KITESHIELD_SARADOMIN,                        // Saradomin kiteshield
			ItemID.RUNE_KITESHIELD_ZAMORAK,                          // Zamorak kiteshield
			ItemID.RUNE_KITESHIELD_GUTHIX,                           // Guthix kiteshield
			ItemID.RUNE_KITESHIELD_ANCIENT,                          // Ancient kiteshield
			ItemID.RUNE_KITESHIELD_ARMADYL,                          // Armadyl kiteshield
			ItemID.RUNE_KITESHIELD_BANDOS,                           // Bandos kiteshield
			ItemID.RUNE_KITESHIELD_GOLDPLATE                         // Gilded kiteshield
		);

		group("Bronze trimmed/gold-trimmed", "BRONZE_FULL_HELM",
			ItemID.BRONZE_FULL_HELM_GOLD,                            // Bronze full helm (g)
			ItemID.BRONZE_FULL_HELM_TRIM,                            // Bronze full helm (t)
			ItemID.BRONZE_FULL_HELM                                  // Bronze full helm
		);

		group("Bronze platebody", "BRONZE_PLATEBODY",
			ItemID.BRONZE_PLATEBODY_TRIM,                            // Bronze platebody (t)
			ItemID.BRONZE_PLATEBODY_GOLD,                            // Bronze platebody (g)
			ItemID.BRONZE_PLATEBODY                                  // Bronze platebody
		);

		group("Bronze platelegs", "BRONZE_PLATELEGS",
			ItemID.BRONZE_PLATELEGS_TRIM,                            // Bronze platelegs (t)
			ItemID.BRONZE_PLATELEGS_GOLD,                            // Bronze platelegs (g)
			ItemID.BRONZE_PLATELEGS                                  // Bronze platelegs
		);

		group("Bronze plateskirt", "BRONZE_PLATESKIRT",
			ItemID.BRONZE_PLATESKIRT_TRIM,                           // Bronze plateskirt (t)
			ItemID.BRONZE_PLATESKIRT_GOLD,                           // Bronze plateskirt (g)
			ItemID.BRONZE_PLATESKIRT                                 // Bronze plateskirt
		);

		group("Bronze kiteshield", "BRONZE_KITESHIELD",
			ItemID.BRONZE_KITESHIELD_GOLD,                           // Bronze kiteshield (g)
			ItemID.BRONZE_KITESHIELD_TRIM,                           // Bronze kiteshield (t)
			ItemID.BRONZE_KITESHIELD                                 // Bronze kiteshield
		);

		group("Iron trimmed/gold-trimmed", "IRON_FULL_HELM",
			ItemID.IRON_FULL_HELM_TRIM,                              // Iron full helm (t)
			ItemID.IRON_FULL_HELM_GOLD,                              // Iron full helm (g)
			ItemID.IRON_FULL_HELM                                    // Iron full helm
		);

		group("Iron platebody", "IRON_PLATEBODY",
			ItemID.IRON_PLATEBODY_GOLD,                              // Iron platebody (g)
			ItemID.IRON_PLATEBODY                                    // Iron platebody
		);

		group("Iron platelegs", "IRON_PLATELEGS",
			ItemID.IRON_PLATELEGS_GOLD,                              // Iron platelegs (g)
			ItemID.IRON_PLATELEGS                                    // Iron platelegs
		);

		group("Iron plateskirt", "IRON_PLATESKIRT",
			ItemID.IRON_PLATESKIRT_GOLD,                             // Iron plateskirt (g)
			ItemID.IRON_PLATESKIRT                                   // Iron plateskirt
		);

		group("Iron kiteshield", "IRON_KITESHIELD",
			ItemID.IRON_KITESHIELD_TRIM,                             // Iron kiteshield (t)
			ItemID.IRON_KITESHIELD_GOLD,                             // Iron kiteshield (g)
			ItemID.IRON_KITESHIELD                                   // Iron kiteshield
		);

		group("Steel trimmed/gold-trimmed", "STEEL_FULL_HELM",
			ItemID.STEEL_FULL_HELM_TRIM,                             // Steel full helm (t)
			ItemID.STEEL_FULL_HELM_GOLD,                             // Steel full helm (g)
			ItemID.STEEL_FULL_HELM                                   // Steel full helm
		);

		group("Steel platebody", "STEEL_PLATEBODY",
			ItemID.STEEL_PLATEBODY_TRIM,                             // Steel platebody (t)
			ItemID.STEEL_PLATEBODY_GOLD,                             // Steel platebody (g)
			ItemID.STEEL_PLATEBODY                                   // Steel platebody
		);

		group("Steel platelegs", "STEEL_PLATELEGS",
			ItemID.STEEL_PLATELEGS_TRIM,                             // Steel platelegs (t)
			ItemID.STEEL_PLATELEGS                                   // Steel platelegs
		);

		group("Steel plateskirt", "STEEL_PLATESKIRT",
			ItemID.STEEL_PLATESKIRT_TRIM,                            // Steel plateskirt (t)
			ItemID.STEEL_PLATESKIRT_GOLD,                            // Steel plateskirt (g)
			ItemID.STEEL_PLATESKIRT                                  // Steel plateskirt
		);

		group("Steel kiteshield", "STEEL_KITESHIELD",
			ItemID.STEEL_KITESHIELD_TRIM,                            // Steel kiteshield (t)
			ItemID.STEEL_KITESHIELD                                  // Steel kiteshield
		);

		group("Black trimmed/gold-trimmed", "BLACK_FULL_HELM",
			ItemID.BLACK_FULL_HELM_TRIM,                             // Black full helm (t)
			ItemID.BLACK_FULL_HELM_GOLD,                             // Black full helm (g)
			ItemID.BLACK_FULL_HELM                                   // Black full helm
		);

		group("Black platebody", "BLACK_PLATEBODY",
			ItemID.BLACK_PLATEBODY_TRIM,                             // Black platebody (t)
			ItemID.BLACK_PLATEBODY_GOLD,                             // Black platebody (g)
			ItemID.BLACK_PLATEBODY                                   // Black platebody
		);

		group("Black platelegs", "BLACK_PLATELEGS",
			ItemID.BLACK_PLATELEGS_TRIM,                             // Black platelegs (t)
			ItemID.BLACK_PLATELEGS_GOLD,                             // Black platelegs (g)
			ItemID.BLACK_PLATELEGS                                   // Black platelegs
		);

		group("Black plateskirt", "BLACK_PLATESKIRT",
			ItemID.BLACK_PLATESKIRT_TRIM,                            // Black plateskirt (t)
			ItemID.BLACK_PLATESKIRT_GOLD,                            // Black plateskirt (g)
			ItemID.BLACK_PLATESKIRT                                  // Black plateskirt
		);

		group("Black kiteshield", "BLACK_KITESHIELD",
			ItemID.BLACK_KITESHIELD_TRIM,                            // Black kiteshield (t)
			ItemID.BLACK_KITESHIELD_GOLD,                            // Black kiteshield (g)
			ItemID.BLACK_KITESHIELD                                  // Black kiteshield
		);

		group("Mithril trimmed/gold-trimmed", "MITHRIL_FULL_HELM",
			ItemID.MITHRIL_FULL_HELM_GOLD,                           // Mithril full helm (g)
			ItemID.MITHRIL_FULL_HELM_TRIM,                           // Mithril full helm (t)
			ItemID.MITHRIL_FULL_HELM                                 // Mithril full helm
		);

		group("Mithril platebody", "MITHRIL_PLATEBODY",
			ItemID.MITHRIL_PLATEBODY_TRIM,                           // Mithril platebody (t)
			ItemID.MITHRIL_PLATEBODY_GOLD,                           // Mithril platebody (g)
			ItemID.MITHRIL_PLATEBODY                                 // Mithril platebody
		);

		group("Mithril platelegs", "MITHRIL_PLATELEGS",
			ItemID.MITHRIL_PLATELEGS_TRIM,                           // Mithril platelegs (t)
			ItemID.MITHRIL_PLATELEGS_GOLD,                           // Mithril platelegs (g)
			ItemID.MITHRIL_PLATELEGS                                 // Mithril platelegs
		);

		group("Mithril plateskirt", "MITHRIL_PLATESKIRT",
			ItemID.MITHRIL_PLATESKIRT_TRIM,                          // Mithril plateskirt (t)
			ItemID.MITHRIL_PLATESKIRT_GOLD,                          // Mithril plateskirt (g)
			ItemID.MITHRIL_PLATESKIRT                                // Mithril plateskirt
		);

		group("Mithril kiteshield", "MITHRIL_KITESHIELD",
			ItemID.MITHRIL_KITESHIELD_TRIM,                          // Mithril kiteshield (t)
			ItemID.MITHRIL_KITESHIELD_GOLD,                          // Mithril kiteshield (g)
			ItemID.MITHRIL_KITESHIELD                                // Mithril kiteshield
		);

		group("Adamant trimmed/gold-trimmed", "ADAMANT_FULL_HELM",
			ItemID.ADAMANT_FULL_HELM_TRIM,                           // Adamant full helm (t)
			ItemID.ADAMANT_FULL_HELM_GOLD,                           // Adamant full helm (g)
			ItemID.ADAMANT_FULL_HELM                                 // Adamant full helm
		);

		group("Adamant platebody", "ADAMANT_PLATEBODY",
			ItemID.ADAMANT_PLATEBODY_TRIM,                           // Adamant platebody (t)
			ItemID.ADAMANT_PLATEBODY_GOLD,                           // Adamant platebody (g)
			ItemID.ADAMANT_PLATEBODY                                 // Adamant platebody
		);

		group("Adamant platelegs", "ADAMANT_PLATELEGS",
			ItemID.ADAMANT_PLATELEGS_TRIM,                           // Adamant platelegs (t)
			ItemID.ADAMANT_PLATELEGS_GOLD,                           // Adamant platelegs (g)
			ItemID.ADAMANT_PLATELEGS                                 // Adamant platelegs
		);

		group("Adamant plateskirt", "ADAMANT_PLATESKIRT",
			ItemID.ADAMANT_PLATESKIRT_TRIM,                          // Adamant plateskirt (t)
			ItemID.ADAMANT_PLATESKIRT_GOLD,                          // Adamant plateskirt (g)
			ItemID.ADAMANT_PLATESKIRT                                // Adamant plateskirt
		);

		group("Adamant kiteshield", "ADAMANT_KITESHIELD",
			ItemID.ADAMANT_KITESHIELD_TRIM,                          // Adamant kiteshield (t)
			ItemID.ADAMANT_KITESHIELD_GOLD,                          // Adamant kiteshield (g)
			ItemID.ADAMANT_KITESHIELD                                // Adamant kiteshield
		);

		// ==================== IRON/STEEL/MITH/ADDY/RUNE CROSSBOW (U) ====================

		group("Bronze crossbow", "XBOWS_CROSSBOW",
			ItemID.XBOWS_CROSSBOW_BRONZE,                            // Bronze crossbow
			ItemID.XBOWS_CROSSBOW_UNSTRUNG_BRONZE                    // Bronze crossbow (u)
		);

		group("Iron crossbow", "XBOWS_CROSSBOW",
			ItemID.XBOWS_CROSSBOW_IRON,                              // Iron crossbow
			ItemID.XBOWS_CROSSBOW_UNSTRUNG_IRON                      // Iron crossbow (u)
		);

		group("Steel crossbow (u)", "XBOWS_CROSSBOW",
			ItemID.XBOWS_CROSSBOW_UNSTRUNG_STEEL,                    // Steel crossbow (u)
			ItemID.XBOWS_CROSSBOW_STEEL                              // Steel crossbow
		);

		group("Mithril crossbow (u)", "XBOWS_CROSSBOW",
			ItemID.XBOWS_CROSSBOW_UNSTRUNG_MITHRIL,                  // Mithril crossbow (u)
			ItemID.XBOWS_CROSSBOW_MITHRIL                            // Mithril crossbow
		);

		group("Adamant crossbow (u)", "XBOWS_CROSSBOW",
			ItemID.XBOWS_CROSSBOW_UNSTRUNG_ADAMANTITE,               // Adamant crossbow (u)
			ItemID.XBOWS_CROSSBOW_ADAMANTITE                         // Adamant crossbow
		);

		// ==================== ANCIENT STAFF VARIANTS ====================

		group("Ancient staff and Ancient sceptre", "STAFF_OF_ZAROS|ANCIENT_SCEPTRE",
			ItemID.STAFF_OF_ZAROS,                                   // Ancient staff
			ItemID.ANCIENT_SCEPTRE,                                  // Ancient sceptre
			ItemID.ANCIENT_SCEPTRE_TROUVER                           // Ancient sceptre (l)
		);

		// ==================== GODSWORDS ====================

		group("Armadyl godsword", "AGS",
			ItemID.AGS,                                              // Armadyl godsword
			ItemID.AGSG                                              // Armadyl godsword (or)
		);

		group("Bandos godsword", "BGS",
			ItemID.BGS,                                              // Bandos godsword
			ItemID.BGSG                                              // Bandos godsword (or)
		);

		group("Saradomin godsword", "SGS",
			ItemID.SGS,                                              // Saradomin godsword
			ItemID.SGSG                                              // Saradomin godsword (or)
		);

		group("Zamorak godsword", "ZGS",
			ItemID.ZGS,                                              // Zamorak godsword
			ItemID.ZGSG                                              // Zamorak godsword (or)
		);

		// ==================== JEWELRY ====================

		group("Amulet of fury and Amulet of blood fury", "ONYX_AMULET|FURY|BLOOD_AMULET",
			ItemID.ENCHANTED_ONYX_AMULET,                            // Amulet of fury
			ItemID.FURY_ORNAMENT,                                    // Amulet of fury (or)
			ItemID.BLOOD_AMULET                                      // Amulet of blood fury
		);

		group("Amulet of torture", "ZENYTE_AMULET",
			ItemID.ZENYTE_AMULET_ENCHANTED,                          // Amulet of torture
			ItemID.ZENYTE_AMULET_ORNAMENT                            // Amulet of torture (or)
		);

		group("Necklace of anguish", "ZENYTE_NECKLACE",
			ItemID.ZENYTE_NECKLACE_ENCHANTED,                        // Necklace of anguish
			ItemID.ZENYTE_NECKLACE_ORNAMENT                          // Necklace of anguish (or)
		);

		group("Tormented bracelet", "ZENYTE_BRACELET_ENCHANTED",
			ItemID.ZENYTE_BRACELET_ENCHANTED,                        // Tormented bracelet
			ItemID.ZENYTE_BRACELET_ENCHANTED_ORNAMENT                // Tormented bracelet (or)
		);

		group("Occult necklace", "OCCULT_NECKLACE",
			ItemID.OCCULT_NECKLACE,                                  // Occult necklace
			ItemID.OCCULT_NECKLACE_ORNAMENT                          // Occult necklace (or)
		);

		group("Berserker necklace", "JEWL_BESERKER_NECKLACE",
			ItemID.JEWL_BESERKER_NECKLACE,                           // Berserker necklace
			ItemID.JEWL_BESERKER_NECKLACE_ORNAMENT                   // Berserker necklace (or)
		);

		// ==================== RINGS ====================

		group("Berserker ring", "BERZERKER_RING",
			ItemID.BERZERKER_RING,                                   // Berserker ring
			ItemID.NZONE_BERZERKER_RING,                             // Berserker ring (i)
			ItemID.SW_BERZERKER_RING,                                // Berserker ring (i)
			ItemID.PVPA_BERZERKER_RING                               // Berserker ring (i)
		);

		group("Archers ring", "RANGER_RING",
			ItemID.RANGER_RING,                                      // Archers ring
			ItemID.NZONE_RANGER_RING,                                // Archers ring (i)
			ItemID.SW_RANGER_RING,                                   // Archers ring (i)
			ItemID.PVPA_RANGER_RING                                  // Archers ring (i)
		);

		group("Seers ring", "SEER_RING",
			ItemID.SEER_RING,                                        // Seers ring
			ItemID.NZONE_SEER_RING,                                  // Seers ring (i)
			ItemID.SW_SEER_RING,                                     // Seers ring (i)
			ItemID.PVPA_SEER_RING                                    // Seers ring (i)
		);

		group("Warrior ring", "WARRIOR_RING",
			ItemID.WARRIOR_RING,                                     // Warrior ring
			ItemID.NZONE_WARRIOR_RING,                               // Warrior ring (i)
			ItemID.SW_WARRIOR_RING,                                  // Warrior ring (i)
			ItemID.PVPA_WARRIOR_RING                                 // Warrior ring (i)
		);

		group("Treasonous ring", "SHARP_RING",
			ItemID.SHARP_RING,                                       // Treasonous ring
			ItemID.NZONE_SHARP_RING,                                 // Treasonous ring (i)
			ItemID.SW_SHARP_RING,                                    // Treasonous ring (i)
			ItemID.PVPA_SHARP_RING                                   // Treasonous ring (i)
		);

		group("Tyrannical ring", "HEAVY_RING",
			ItemID.HEAVY_RING,                                       // Tyrannical ring
			ItemID.NZONE_HEAVY_RING,                                 // Tyrannical ring (i)
			ItemID.SW_HEAVY_RING,                                    // Tyrannical ring (i)
			ItemID.PVPA_HEAVY_RING                                   // Tyrannical ring (i)
		);

		group("Ring of the gods", "ROTG",
			ItemID.ROTG,                                             // Ring of the gods
			ItemID.NZONE_ROTG,                                       // Ring of the gods (i)
			ItemID.SW_ROTG,                                          // Ring of the gods (i)
			ItemID.PVPA_ROTG                                         // Ring of the gods (i)
		);

		group("Ring of wealth", "RING_OF_WEALTH",
			ItemID.RING_OF_WEALTH,                                   // Ring of wealth
			ItemID.RING_OF_WEALTH_I                                  // Ring of wealth (i)
		);

		// ==================== PROSPECTOR KIT ====================

		group("Prospector helmet", "MOTHERLODE_REWARD_HAT",
			ItemID.MOTHERLODE_REWARD_HAT,                            // Prospector helmet
			ItemID.MOTHERLODE_REWARD_HAT_GOLD                        // Golden prospector helmet
		);

		group("Prospector jacket", "MOTHERLODE_REWARD_TOP",
			ItemID.MOTHERLODE_REWARD_TOP,                            // Prospector jacket
			ItemID.MOTHERLODE_REWARD_TOP_GOLD                        // Golden prospector jacket
		);

		group("Prospector legs", "MOTHERLODE_REWARD_LEGS",
			ItemID.MOTHERLODE_REWARD_LEGS,                           // Prospector legs
			ItemID.MOTHERLODE_REWARD_LEGS_GOLD                       // Golden prospector legs
		);

		group("Prospector boots", "MOTHERLODE_REWARD_BOOTS",
			ItemID.MOTHERLODE_REWARD_BOOTS,                          // Prospector boots
			ItemID.MOTHERLODE_REWARD_BOOTS_GOLD                      // Golden prospector boots
		);

		// ==================== INFINITY ROBES ====================

		group("Infinity hat", "INFINITY",
			ItemID.MAGICTRAINING_INFINITYHAT,                        // Infinity hat
			ItemID.INFINITY_HAT_TRIM,                                // Dark infinity hat
			ItemID.INFINITY_HAT_GOLD                                 // Light infinity hat
		);

		group("Infinity top", "INFINITY",
			ItemID.MAGICTRAINING_INFINITYTOP,                        // Infinity top
			ItemID.INFINITY_TOP_TRIM,                                // Dark infinity top
			ItemID.INFINITY_TOP_GOLD                                 // Light infinity top
		);

		group("Infinity bottoms", "INFINITY",
			ItemID.MAGICTRAINING_INFINITYBOTTOM,                     // Infinity bottoms
			ItemID.INFINITY_BOTTOM_TRIM,                             // Dark infinity bottoms
			ItemID.INFINITY_BOTTOM_GOLD                              // Light infinity bottoms
		);

		// ==================== ANCESTRAL ROBES ====================

		group("Ancestral hat", "ANCESTRAL_HAT",
			ItemID.ANCESTRAL_HAT,                                    // Ancestral hat
			ItemID.ANCESTRAL_HAT_TWISTED                             // Twisted ancestral hat
		);

		group("Ancestral robe top", "ANCESTRAL_ROBE_TOP",
			ItemID.ANCESTRAL_ROBE_TOP,                               // Ancestral robe top
			ItemID.ANCESTRAL_ROBE_TOP_TWISTED                        // Twisted ancestral robe top
		);

		group("Ancestral robe bottom", "ANCESTRAL_ROBE_BOTTOM",
			ItemID.ANCESTRAL_ROBE_BOTTOM,                            // Ancestral robe bottom
			ItemID.ANCESTRAL_ROBE_BOTTOM_TWISTED                     // Twisted ancestral robe bottom
		);

		// ==================== GOD CAPES ====================

		group("Saradomin cape", "SARADOMIN_CAPE",
			ItemID.SARADOMIN_CAPE,                                   // Saradomin cape
			ItemID.MA2_SARADOMIN_CAPE                                // Imbued Saradomin cape
		);

		group("Zamorak cape", "ZAMORAK_CAPE",
			ItemID.ZAMORAK_CAPE,                                     // Zamorak cape
			ItemID.MA2_ZAMORAK_CAPE                                  // Imbued Zamorak cape
		);

		group("Guthix cape", "GUTHIX_CAPE",
			ItemID.GUTHIX_CAPE,                                      // Guthix cape
			ItemID.MA2_GUTHIX_CAPE                                   // Imbued Guthix cape
		);

		// ==================== SALVE AMULET ====================

		group("Salve amulet", "SALVE|CRYSTALSHARD_NECKLACE",
			ItemID.CRYSTALSHARD_NECKLACE,                            // Salve amulet
			ItemID.LOTR_CRYSTALSHARD_NECKLACE_UPGRADE,               // Salve amulet (e)
			ItemID.NZONE_SALVE_AMULET,                               // Salve amulet(i)
			ItemID.NZONE_SALVE_AMULET_E                              // Salve amulet(ei)
		);

		// ==================== BLACK MASK ====================

		group("Black mask", "BLACK_MASK",
			ItemID.HARMLESS_BLACK_MASK_10,                           // Black mask (10)
			ItemID.HARMLESS_BLACK_MASK_9,                            // Black mask (9)
			ItemID.HARMLESS_BLACK_MASK_8,                            // Black mask (8)
			ItemID.HARMLESS_BLACK_MASK_7,                            // Black mask (7)
			ItemID.HARMLESS_BLACK_MASK_6,                            // Black mask (6)
			ItemID.HARMLESS_BLACK_MASK_5,                            // Black mask (5)
			ItemID.HARMLESS_BLACK_MASK_4,                            // Black mask (4)
			ItemID.HARMLESS_BLACK_MASK_3,                            // Black mask (3)
			ItemID.HARMLESS_BLACK_MASK_2,                            // Black mask (2)
			ItemID.HARMLESS_BLACK_MASK_1,                            // Black mask (1)
			ItemID.HARMLESS_BLACK_MASK,                              // Black mask
			ItemID.NZONE_BLACK_MASK,                                 // Black mask (i)
			ItemID.SW_BLACK_MASK,                                    // Black mask (i)
			ItemID.PVPA_BLACK_MASK,                                  // Black mask (i)
			ItemID.NZONE_BLACK_MASK_10                               // Black mask (10) (i)
		);

		// ==================== RUNE SCIMITAR ====================

		group("Rune scimitar", "RUNE_SCIMITAR",
			ItemID.RUNE_SCIMITAR,                                    // Rune scimitar
			ItemID.RUNE_SCIMITAR_GUTHIX,                             // Rune scimitar
			ItemID.RUNE_SCIMITAR_SARADOMIN,                          // Rune scimitar
			ItemID.RUNE_SCIMITAR_ZAMORAK                             // Rune scimitar
		);

		// ==================== GRANITE MAUL ====================

		group("Granite maul", "GRANITE_MAUL",
			ItemID.GRANITE_MAUL,                                     // Granite maul
			ItemID.GRANITE_MAUL_PRETTY,                              // Granite maul
			ItemID.GRANITE_MAUL_PLUS                                 // Granite maul
		);

		// ==================== ODIUM/MALEDICTION WARDS ====================

		group("Odium ward", "ODIUM_WARD",
			ItemID.ODIUM_WARD,                                       // Odium ward
			ItemID.ODIUM_WARD_PRETTY                                 // Odium ward
		);

		group("Malediction ward", "MALEDICTION_WARD",
			ItemID.MALEDICTION_WARD,                                 // Malediction ward
			ItemID.MALEDICTION_WARD_PRETTY                           // Malediction ward
		);

		// ==================== STEAM/LAVA BATTLESTAFF ====================

		group("Steam battlestaff", "STEAM_BATTLESTAFF",
			ItemID.STEAM_BATTLESTAFF,                                // Steam battlestaff
			ItemID.STEAM_BATTLESTAFF_PRETTY                          // Steam battlestaff
		);

		group("Lava battlestaff", "LAVA_BATTLESTAFF",
			ItemID.LAVA_BATTLESTAFF,                                 // Lava battlestaff
			ItemID.LAVA_BATTLESTAFF_PRETTY                           // Lava battlestaff
		);

		// ==================== MYSTIC ROBES (All Color Variants) ====================

		group("Mystic hat", "MYSTIC_HAT",
			ItemID.MYSTIC_HAT,                                       // Mystic hat
			ItemID.MYSTIC_HAT_DARK,                                  // Mystic hat (dark)
			ItemID.MYSTIC_HAT_LIGHT,                                 // Mystic hat (light)
			ItemID.MYSTIC_HAT_DUSK                                   // Mystic hat (dusk)
		);

		group("Mystic robe top", "MYSTIC_ROBE_TOP",
			ItemID.MYSTIC_ROBE_TOP,                                  // Mystic robe top
			ItemID.MYSTIC_ROBE_TOP_DARK,                             // Mystic robe top (dark)
			ItemID.MYSTIC_ROBE_TOP_LIGHT,                            // Mystic robe top (light)
			ItemID.MYSTIC_ROBE_TOP_DUSK                              // Mystic robe top (dusk)
		);

		group("Mystic robe bottom", "MYSTIC_ROBE_BOTTOM",
			ItemID.MYSTIC_ROBE_BOTTOM,                               // Mystic robe bottom
			ItemID.MYSTIC_ROBE_BOTTOM_DARK,                          // Mystic robe bottom (dark)
			ItemID.MYSTIC_ROBE_BOTTOM_LIGHT,                         // Mystic robe bottom (light)
			ItemID.MYSTIC_ROBE_BOTTOM_DUSK                           // Mystic robe bottom (dusk)
		);

		group("Mystic gloves", "MYSTIC_GLOVES",
			ItemID.MYSTIC_GLOVES,                                    // Mystic gloves
			ItemID.MYSTIC_GLOVES_DARK,                               // Mystic gloves (dark)
			ItemID.MYSTIC_GLOVES_LIGHT,                              // Mystic gloves (light)
			ItemID.MYSTIC_GLOVES_DUSK                                // Mystic gloves (dusk)
		);

		group("Mystic boots", "MYSTIC_BOOTS",
			ItemID.MYSTIC_BOOTS,                                     // Mystic boots
			ItemID.MYSTIC_BOOTS_DARK,                                // Mystic boots (dark)
			ItemID.MYSTIC_BOOTS_LIGHT,                               // Mystic boots (light)
			ItemID.MYSTIC_BOOTS_DUSK                                 // Mystic boots (dusk)
		);

		// ==================== CANNON (Shattered Relics) ====================

		group("Cannon base", "TWPART1|MULTICANNON_BASE",
			ItemID.TWPART1,                                          // Cannon base
			ItemID.LEAGUE_3_MULTICANNON_BASE                         // Cannon base (or)
		);

		group("Cannon stand", "TWPART2|MULTICANNON_STAND",
			ItemID.TWPART2,                                          // Cannon stand
			ItemID.LEAGUE_3_MULTICANNON_STAND                        // Cannon stand (or)
		);

		group("Cannon barrels", "TWPART3|MULTICANNON_BARRELS",
			ItemID.TWPART3,                                          // Cannon barrels
			ItemID.LEAGUE_3_MULTICANNON_BARRELS                      // Cannon barrels (or)
		);

		group("Cannon furnace", "TWPART4|MULTICANNON_FURNACE",
			ItemID.TWPART4,                                          // Cannon furnace
			ItemID.LEAGUE_3_MULTICANNON_FURNACE                      // Cannon furnace (or)
		);

		// ==================== ADDITIONAL RINGS ====================

		group("Ring of suffering", "ZENYTE_RING_ENCHANTED",
			ItemID.ZENYTE_RING_ENCHANTED,                            // Ring of suffering
			ItemID.NZONE_ZENYTE_RING_ENCHANTED,                      // Ring of suffering (i)
			ItemID.NZONE_ZENYTE_RING_ENCHANTED_RECOIL,               // Ring of suffering (ri)
			ItemID.SW_ZENYTE_RING_ENCHANTED,                         // Ring of suffering (i)
			ItemID.PVPA_ZENYTE_RING_ENCHANTED                        // Ring of suffering (i)
		);

		group("Granite ring", "GRANITE_RING",
			ItemID.GRANITE_RING,                                     // Granite ring
			ItemID.NZONE_GRANITE_RING                                // Granite ring (i)
		);

		// ==================== MAGIC SHORTBOW ====================

		group("Magic shortbow", "MAGIC_SHORTBOW",
			ItemID.MAGIC_SHORTBOW,                                   // Magic shortbow
			ItemID.MAGIC_SHORTBOW_I                                  // Magic shortbow (i)
		);

		// ==================== SERPENTINE HELMS ====================

		group("Serpentine helm", "SERPENTINE_HELM",
			ItemID.SERPENTINE_HELM,                                  // Serpentine helm (uncharged)
			ItemID.SERPENTINE_HELM_CHARGED,                          // Serpentine helm
			ItemID.SERPENTINE_HELM_CYAN,                             // Tanzanite helm (uncharged)
			ItemID.SERPENTINE_HELM_CHARGED_CYAN,                     // Tanzanite helm
			ItemID.SERPENTINE_HELM_RED,                              // Magma helm (uncharged)
			ItemID.SERPENTINE_HELM_CHARGED_RED                       // Magma helm
		);

		// ==================== THEATRE OF BLOOD WEAPONS ====================

		group("Ghrazi rapier", "GHRAZI_RAPIER",
			ItemID.GHRAZI_RAPIER,                                    // Ghrazi rapier
			ItemID.GHRAZI_RAPIER_OR                                  // Holy ghrazi rapier
		);

		group("Scythe of vitur", "SCYTHE_OF_VITUR",
			ItemID.SCYTHE_OF_VITUR,                                  // Scythe of Vitur
			ItemID.SCYTHE_OF_VITUR_UNCHARGED,                        // Scythe of Vitur (uncharged)
			ItemID.SCYTHE_OF_VITUR_OR,                               // Holy Scythe of Vitur
			ItemID.SCYTHE_OF_VITUR_UNCHARGED_OR,                     // Holy Scythe of Vitur (uncharged)
			ItemID.SCYTHE_OF_VITUR_BL,                               // Sanguine Scythe of Vitur
			ItemID.SCYTHE_OF_VITUR_UNCHARGED_BL                      // Sanguine Scythe of Vitur (uncharged)
		);

		group("Sanguinesti staff", "SANGUINESTI_STAFF",
			ItemID.SANGUINESTI_STAFF,                                // Sanguinesti staff
			ItemID.SANGUINESTI_STAFF_UNCHARGED,                      // Sanguinesti staff (uncharged)
			ItemID.SANGUINESTI_STAFF_OR,                             // Holy sanguinesti staff
			ItemID.SANGUINESTI_STAFF_UNCHARGED_OR                    // Holy sanguinesti staff (uncharged)
		);

		// ==================== HEAVY BALLISTA ====================

		group("Heavy ballista", "HEAVY_BALLISTA",
			ItemID.HEAVY_BALLISTA,                                   // Heavy ballista
			ItemID.HEAVY_BALLISTA_ORNAMENT                           // Heavy ballista (or)
		);

		// ==================== TZHAAR WEAPONS ====================

		group("Tzhaar-ket-om", "TZHAAR_MAUL",
			ItemID.TZHAAR_MAUL,                                      // Tzhaar-ket-om
			ItemID.TZHAAR_MAUL_T                                     // Tzhaar-ket-om (t)
		);

		// ==================== ELDER MAUL ====================

		group("Elder maul", "ELDER_MAUL",
			ItemID.ELDER_MAUL,                                       // Elder maul
			ItemID.ELDER_MAUL_ORNAMENT                               // Elder maul (or)
		);

		// ==================== OSMUMTEN'S FANG ====================

		group("Osmumten's fang", "OSMUMTENS_FANG",
			ItemID.OSMUMTENS_FANG,                                   // Osmumten's fang
			ItemID.OSMUMTENS_FANG_ORNAMENT                           // Osmumten's fang (or)
		);

		// ==================== NEITIZNOT HELM ====================

		group("Helm of Neitiznot", "FRIS_KINGLY_HELM",
			ItemID.FRIS_KINGLY_HELM,                                 // Helm of Neitiznot
			ItemID.BH_FRIS_KINGLY_HELM_CORRUPTED                     // Helm of Neitiznot (or)
		);

		// ==================== ELIDINIS' WARD ====================

		group("Elidinis' ward", "ELIDINIS_WARD",
			ItemID.ELIDINIS_WARD,                                    // Elidinis' ward
			ItemID.ELIDINIS_WARD_FORTIFIED                           // Elidinis' ward (f)
		);

		// ==================== TUMEKEN'S SHADOW ====================

		group("Tumeken's shadow", "TUMEKENS_SHADOW",
			ItemID.TUMEKENS_SHADOW,                                  // Tumeken's shadow
			ItemID.TUMEKENS_SHADOW_UNCHARGED                         // Tumeken's shadow (uncharged)
		);

		// ==================== VENATOR BOW ====================

		group("Venator bow", "VENATOR_BOW",
			ItemID.VENATOR_BOW,                                      // Venator bow
			ItemID.VENATOR_BOW_UNCHARGED,                            // Venator bow (uncharged)
			ItemID.VENATOR_BOW_ORNAMENT                              // Echo venator bow
		);

		// ==================== TORVA ARMOR ====================

		group("Torva full helm", "TORVA_HELM",
			ItemID.TORVA_HELM,                                       // Torva full helm
			ItemID.TORVA_HELM_SANGUINE                               // Sanguine torva full helm
		);

		group("Torva platebody", "TORVA_CHEST",
			ItemID.TORVA_CHEST,                                      // Torva platebody
			ItemID.TORVA_CHEST_SANGUINE                              // Sanguine torva platebody
		);

		group("Torva platelegs", "TORVA_LEGS",
			ItemID.TORVA_LEGS,                                       // Torva platelegs
			ItemID.TORVA_LEGS_SANGUINE                               // Sanguine torva platelegs
		);

		// ==================== MASORI ARMOR ====================

		group("Masori mask", "MASORI_MASK",
			ItemID.MASORI_MASK,                                      // Masori mask
			ItemID.MASORI_MASK_FORTIFIED                             // Masori mask (f)
		);

		group("Masori body", "MASORI_BODY",
			ItemID.MASORI_BODY,                                      // Masori body
			ItemID.MASORI_BODY_FORTIFIED                             // Masori body (f)
		);

		group("Masori chaps", "MASORI_CHAPS",
			ItemID.MASORI_CHAPS,                                     // Masori chaps
			ItemID.MASORI_CHAPS_FORTIFIED                            // Masori chaps (f)
		);

		// ==================== ELDER CHAOS ROBES ====================

		group("Elder chaos hood", "ELDERCHAOS_HOOD",
			ItemID.ELDERCHAOS_HOOD,                                  // Elder chaos hood
			ItemID.ELDERCHAOS_HOOD_PRETTY                            // Elder chaos hood (or)
		);

		group("Elder chaos top", "ELDERCHAOS_TOP",
			ItemID.ELDERCHAOS_TOP,                                   // Elder chaos top
			ItemID.ELDERCHAOS_TOP_PRETTY                             // Elder chaos top (or)
		);

		group("Elder chaos robe", "ELDERCHAOS_BOTTOM",
			ItemID.ELDERCHAOS_BOTTOM,                                // Elder chaos robe
			ItemID.ELDERCHAOS_BOTTOM_PRETTY                          // Elder chaos robe (or)
		);

		// ==================== GOD BOOKS ====================

		group("Holy book", "SARADOMIN",
			ItemID.SARADOMINBOOK_COMPLETE,                           // Holy book
			ItemID.LEAGUE_3_BOOK_SARADOMIN                           // Holy book (or)
		);

		group("Unholy book", "ZAMORAK",
			ItemID.ZAMORAKBOOK_COMPLETE,                             // Unholy book
			ItemID.LEAGUE_3_BOOK_ZAMORAK                             // Unholy book (or)
		);

		group("Book of Balance", "GUTHIX",
			ItemID.GUTHIXBOOK_COMPLETE,                              // Book of Balance
			ItemID.LEAGUE_3_BOOK_GUTHIX                              // Book of Balance (or)
		);

		group("Book of War", "BANDOS",
			ItemID.BANDOSBOOK_COMPLETE,                              // Book of War
			ItemID.LEAGUE_3_BOOK_BANDOS                              // Book of War (or)
		);

		group("Book of Law", "ARMADYL",
			ItemID.ARMADYLBOOK_COMPLETE,                             // Book of Law
			ItemID.LEAGUE_3_BOOK_ARMADYL                             // Book of Law (or)
		);

		group("Book of Darkness", "ZAROS",
			ItemID.ZAROSBOOK_COMPLETE,                               // Book of Darkness
			ItemID.LEAGUE_3_BOOK_ZAROS                               // Book of Darkness (or)
		);

		// ==================== CHARGED/UNCHARGED ITEMS ====================

		group("Bracelet of ethereum", "WILD_CAVE_BRACELET",
			ItemID.WILD_CAVE_BRACELET_CHARGED,                       // Bracelet of ethereum
			ItemID.WILD_CAVE_BRACELET_UNCHARGED                      // Bracelet of ethereum (uncharged)
		);

		group("Ring of endurance", "RING_OF_ENDURANCE",
			ItemID.RING_OF_ENDURANCE,                                // Ring of endurance
			ItemID.RING_OF_ENDURANCE_NOCHARGES                       // Ring of endurance (uncharged)
		);

		group("Celestial ring", "CELESTIAL_RING",
			ItemID.CELESTIAL_RING,                                   // Celestial ring (uncharged)
			ItemID.CELESTIAL_RING_CHARGED                            // Celestial ring
		);

		group("Pharaoh's sceptre (all consolidated)", "SCEPTRE",
			ItemID.NTK_JEWELLED_SCEPTRE_3,                           // Pharaoh's sceptre
			ItemID.NTK_JEWELLED_SCEPTRE_2,                           // Pharaoh's sceptre
			ItemID.NTK_JEWELLED_SCEPTRE_1,                           // Pharaoh's sceptre
			ItemID.NTK_JEWELLED_SCEPTRE_0,                           // Pharaoh's sceptre
			ItemID.PHARAOHS_SCEPTRE,                                 // Pharaoh's sceptre (uncharged)
			ItemID.PHARAOHS_SCEPTRE_CHARGED,                         // Pharaoh's sceptre
			ItemID.PHARAOHS_SCEPTRE_CHARGED_INITIAL                  // Pharaoh's sceptre
		);

		group("Trident of the seas", "TOTS",
			ItemID.TOTS,                                             // Trident of the Seas (full)
			ItemID.TOTS_CHARGED,                                     // Trident of the Seas
			ItemID.TOTS_UNCHARGED,                                   // Uncharged trident
			ItemID.TOTS_CHARGED_ORN,                                 // Trident of the Seas (o)
			ItemID.TOTS_ORN,                                         // Trident of the Seas (full) (o)
			ItemID.TOTS_UNCHARGED_ORN                                // Uncharged trident (o)
		);

		group("Trident of the swamp", "TOXIC_TOTS",
			ItemID.TOXIC_TOTS_CHARGED,                               // Trident of the Swamp
			ItemID.TOXIC_TOTS_UNCHARGED,                             // Uncharged toxic trident
			ItemID.TOXIC_TOTS_CHARGED_ORN,                           // Trident of the Swamp (o)
			ItemID.TOXIC_TOTS_UNCHARGED_ORN                          // Uncharged toxic trident (o)
		);

		group("Trident of the seas (e)", "TOTS_I",
			ItemID.TOTS_I_CHARGED,                                   // Trident of the Seas (e)
			ItemID.TOTS_I_UNCHARGED,                                 // Uncharged trident (e)
			ItemID.TOTS_I_CHARGED_ORN,                               // Trident of the Seas (e) (o)
			ItemID.TOTS_I_UNCHARGED_ORN                              // Uncharged trident (e) (o)
		);

		group("Trident of the swamp (e)", "TOXIC_TOTS_I",
			ItemID.TOXIC_TOTS_I_CHARGED,                             // Trident of the Swamp (e)
			ItemID.TOXIC_TOTS_I_UNCHARGED,                           // Uncharged toxic trident (e)
			ItemID.TOXIC_TOTS_I_CHARGED_ORN,                         // Trident of the Swamp (e) (o)
			ItemID.TOXIC_TOTS_I_UNCHARGED_ORN                        // Uncharged toxic trident (e) (o)
		);

		// ==================== REVENANT / WILDERNESS WEAPONS ====================

		group("Craw's bow / Webweaver bow", "WILD_CAVE",
			ItemID.WILD_CAVE_BOW_UNCHARGED,                          // Craw's bow (u)
			ItemID.WILD_CAVE_BOW_CHARGED,                            // Craw's bow
			ItemID.WILD_CAVE_WEBWEAVER_CHARGED                       // Webweaver bow
		);

		group("Thammaron's sceptre / Accursed sceptre", "WILD_CAVE",
			ItemID.WILD_CAVE_SCEPTRE_UNCHARGED,                      // Thammaron's sceptre (u)
			ItemID.WILD_CAVE_SCEPTRE_CHARGED,                        // Thammaron's sceptre
			ItemID.WILD_CAVE_ACCURSED_UNCHARGED,                     // Accursed sceptre (u)
			ItemID.WILD_CAVE_ACCURSED_CHARGED                        // Accursed sceptre
		);

		group("Viggora's chainmace / Ursine chainmace", "WILD_CAVE",
			ItemID.WILD_CAVE_CHAINMACE_UNCHARGED,                    // Viggora's chainmace (u)
			ItemID.WILD_CAVE_CHAINMACE_CHARGED,                      // Viggora's chainmace
			ItemID.WILD_CAVE_URSINE_UNCHARGED,                       // Ursine chainmace (u)
			ItemID.WILD_CAVE_URSINE_CHARGED                          // Ursine chainmace
		);

		// ==================== ARCLIGHT/DARKLIGHT ====================

		group("Darklight", "LIGHT",
			ItemID.DARKLIGHT,                                        // Darklight
			ItemID.ARCLIGHT,                                         // Arclight
			ItemID.ARCLIGHT_INACTIVE                                 // Arclight (inactive)
		);

		// ==================== MAX CAPE VARIANTS ====================

		group("Mythical max cape", "MYTHICAL",
			ItemID.MYTHICAL_CAPE,                                    // Mythical cape
			ItemID.SKILLCAPE_MAX_MYTHICAL                            // Mythical max cape
		);

		// ==================== ACHIEVEMENT DIARY ITEMS ====================

		group("Explorer's ring", "LUMBRIDGE_RING",
			ItemID.LUMBRIDGE_RING_EASY,                              // Explorer's ring 1
			ItemID.LUMBRIDGE_RING_MEDIUM,                            // Explorer's ring 2
			ItemID.LUMBRIDGE_RING_HARD,                              // Explorer's ring 3
			ItemID.LUMBRIDGE_RING_ELITE                              // Explorer's ring 4
		);

		group("Ardougne cloak", "ARDY_CAPE",
			ItemID.ARDY_CAPE_EASY,                                   // Ardougne cloak 1
			ItemID.ARDY_CAPE_MEDIUM,                                 // Ardougne cloak 2
			ItemID.ARDY_CAPE_HARD,                                   // Ardougne cloak 3
			ItemID.ARDY_CAPE_ELITE                                   // Ardougne cloak 4
		);

		group("Desert amulet", "DESERT_AMULET",
			ItemID.DESERT_AMULET_EASY,                               // Desert amulet 1
			ItemID.DESERT_AMULET_MEDIUM,                             // Desert amulet 2
			ItemID.DESERT_AMULET_HARD,                               // Desert amulet 3
			ItemID.DESERT_AMULET_ELITE                               // Desert amulet 4
		);

		group("Falador shield", "FALADOR_SHIELD",
			ItemID.FALADOR_SHIELD_EASY,                              // Falador shield 1
			ItemID.FALADOR_SHIELD_MEDIUM,                            // Falador shield 2
			ItemID.FALADOR_SHIELD_HARD,                              // Falador shield 3
			ItemID.FALADOR_SHIELD_ELITE                              // Falador shield 4
		);

		group("Fremennik sea boots", "FREMENNIK_BOOTS",
			ItemID.FREMENNIK_BOOTS_EASY,                             // Fremennik sea boots 1
			ItemID.FREMENNIK_BOOTS_MEDIUM,                           // Fremennik sea boots 2
			ItemID.FREMENNIK_BOOTS_HARD,                             // Fremennik sea boots 3
			ItemID.FREMENNIK_BOOTS_ELITE                             // Fremennik sea boots 4
		);

		group("Kandarin headgear", "SEERS_HEADBAND",
			ItemID.SEERS_HEADBAND_EASY,                              // Kandarin headgear 1
			ItemID.SEERS_HEADBAND_MEDIUM,                            // Kandarin headgear 2
			ItemID.SEERS_HEADBAND_HARD,                              // Kandarin headgear 3
			ItemID.SEERS_HEADBAND_ELITE                              // Kandarin headgear 4
		);

		group("Karamja gloves", "ATJUN_GLOVES",
			ItemID.ATJUN_GLOVES_EASY,                                // Karamja gloves 1
			ItemID.ATJUN_GLOVES_MED,                                 // Karamja gloves 2
			ItemID.ATJUN_GLOVES_HARD,                                // Karamja gloves 3
			ItemID.ATJUN_GLOVES_ELITE                                // Karamja gloves 4
		);

		group("Morytania legs", "MORYTANIA_LEGS",
			ItemID.MORYTANIA_LEGS_EASY,                              // Morytania legs 1
			ItemID.MORYTANIA_LEGS_MEDIUM,                            // Morytania legs 2
			ItemID.MORYTANIA_LEGS_HARD,                              // Morytania legs 3
			ItemID.MORYTANIA_LEGS_ELITE                              // Morytania legs 4
		);

		group("Varrock armour", "VARROCK_ARMOUR",
			ItemID.VARROCK_ARMOUR_EASY,                              // Varrock armour 1
			ItemID.VARROCK_ARMOUR_MEDIUM,                            // Varrock armour 2
			ItemID.VARROCK_ARMOUR_HARD,                              // Varrock armour 3
			ItemID.VARROCK_ARMOUR_ELITE                              // Varrock armour 4
		);

		group("Western banner", "WESTERN_BANNER",
			ItemID.WESTERN_BANNER_EASY,                              // Western banner 1
			ItemID.WESTERN_BANNER_MEDIUM,                            // Western banner 2
			ItemID.WESTERN_BANNER_HARD,                              // Western banner 3
			ItemID.WESTERN_BANNER_ELITE                              // Western banner 4
		);

		group("Wilderness sword", "WILDERNESS_SWORD",
			ItemID.WILDERNESS_SWORD_EASY,                            // Wilderness sword 1
			ItemID.WILDERNESS_SWORD_MEDIUM,                          // Wilderness sword 2
			ItemID.WILDERNESS_SWORD_HARD,                            // Wilderness sword 3
			ItemID.WILDERNESS_SWORD_ELITE                            // Wilderness sword 4
		);

		group("Rada's blessing", "ZEAH_BLESSING",
			ItemID.ZEAH_BLESSING_EASY,                               // Rada's blessing 1
			ItemID.ZEAH_BLESSING_MEDIUM,                             // Rada's blessing 2
			ItemID.ZEAH_BLESSING_HARD,                               // Rada's blessing 3
			ItemID.ZEAH_BLESSING_ELITE                               // Rada's blessing 4
		);

		// ==================== CHOMPY BIRD HATS ====================

		group("Chompy bird hat", "CBHAT",
			ItemID.CBHAT1,                                           // Chompy bird hat
			ItemID.CBHAT2,                                           // Chompy bird hat
			ItemID.CBHAT3,                                           // Chompy bird hat
			ItemID.CBHAT4,                                           // Chompy bird hat
			ItemID.CBHAT5,                                           // Chompy bird hat
			ItemID.CBHAT6,                                           // Chompy bird hat
			ItemID.CBHAT7,                                           // Chompy bird hat
			ItemID.CBHAT8,                                           // Chompy bird hat
			ItemID.CBHAT9,                                           // Chompy bird hat
			ItemID.CBHAT10,                                          // Chompy bird hat
			ItemID.CBHAT11,                                          // Chompy bird hat
			ItemID.CBHAT12,                                          // Chompy bird hat
			ItemID.CBHAT13,                                          // Chompy bird hat
			ItemID.CBHAT14,                                          // Chompy bird hat
			ItemID.CBHAT15,                                          // Chompy bird hat
			ItemID.CBHAT16,                                          // Chompy bird hat
			ItemID.CBHAT17,                                          // Chompy bird hat
			ItemID.CBHAT18                                           // Chompy bird hat
		);

		// ==================== IMBUED/SATURATED HEART ====================

		group("Imbued heart", "ED_HEART",
			ItemID.IMBUED_HEART,                                     // Imbued heart
			ItemID.SATURATED_HEART                                   // Saturated heart
		);

		// ==================== STAFF OF THE DEAD VARIANTS ====================

		group("Staff of the dead, Staff of light and Toxic staff of the dead", "SOTD|STAFF_OF_LIGHT",
			ItemID.SOTD,                                             // Staff of the Dead
			ItemID.STAFF_OF_LIGHT,                                   // Staff of Light
			ItemID.TOXIC_SOTD_CHARGED                                // Toxic Staff of the Dead
		);

		// ==================== AMULET OF THE DAMNED ====================

		group("Amulet of the Damned", "DAMNED_AMULET",
			ItemID.DAMNED_AMULET,                                    // Amulet of the Damned (full)
			ItemID.DAMNED_AMULET_DEGRADED                            // Amulet of the Damned
		);

		// ==================== SLAYER RINGS ====================

		group("Slayer ring", "SLAYER_RING",
			ItemID.SLAYER_RING_8,                                    // Slayer ring (8)
			ItemID.SLAYER_RING_7,                                    // Slayer ring (7)
			ItemID.SLAYER_RING_6,                                    // Slayer ring (6)
			ItemID.SLAYER_RING_5,                                    // Slayer ring (5)
			ItemID.SLAYER_RING_4,                                    // Slayer ring (4)
			ItemID.SLAYER_RING_3,                                    // Slayer ring (3)
			ItemID.SLAYER_RING_2,                                    // Slayer ring (2)
			ItemID.SLAYER_RING_1                                     // Slayer ring (1)
		);

		// ==================== CRYSTAL EQUIPMENT (SONG OF THE ELVES) ====================

		group("Crystal bow", "CRYSTAL_BOW",
			ItemID.CRYSTAL_BOW,                                      // Crystal bow
			ItemID.CRYSTAL_BOW_INACTIVE,                             // Crystal bow (inactive)
			ItemID.CRYSTAL_BOW_2500                                  // Crystal bow
		);

		group("Crystal shield", "CRYSTAL_SHIELD",
			ItemID.CRYSTAL_SHIELD,                                   // Crystal shield
			ItemID.CRYSTAL_SHIELD_INACTIVE,                          // Crystal shield (inactive)
			ItemID.CRYSTAL_SHIELD_2500                               // Crystal shield
		);

		group("Crystal halberd", "CRYSTAL_HALBERD",
			ItemID.CRYSTAL_HALBERD,                                  // Crystal halberd
			ItemID.CRYSTAL_HALBERD_INACTIVE,                         // Crystal halberd (inactive)
			ItemID.CRYSTAL_HALBERD_2500                              // Crystal halberd
		);

		group("Blade of saeldor", "BLADE_OF_SAELDOR",
			ItemID.BLADE_OF_SAELDOR,                                 // Blade of Saeldor
			ItemID.BLADE_OF_SAELDOR_INACTIVE                         // Blade of Saeldor (inactive)
		);

		group("Bow of faerdhinen", "BOW_OF_FAERDHINEN",
			ItemID.BOW_OF_FAERDHINEN_INACTIVE,                       // Bow of Faerdhinen (inactive)
			ItemID.BOW_OF_FAERDHINEN                                 // Bow of Faerdhinen
		);

		// ==================== GAMES NECKLACE ====================

		group("Games necklace", "NECKLACE_OF_MINIGAMES",
			ItemID.NECKLACE_OF_MINIGAMES_8,                          // Games necklace(8)
			ItemID.NECKLACE_OF_MINIGAMES_7,                          // Games necklace(7)
			ItemID.NECKLACE_OF_MINIGAMES_6,                          // Games necklace(6)
			ItemID.NECKLACE_OF_MINIGAMES_5,                          // Games necklace(5)
			ItemID.NECKLACE_OF_MINIGAMES_4,                          // Games necklace(4)
			ItemID.NECKLACE_OF_MINIGAMES_3,                          // Games necklace(3)
			ItemID.NECKLACE_OF_MINIGAMES_2,                          // Games necklace(2)
			ItemID.NECKLACE_OF_MINIGAMES_1                           // Games necklace(1)
		);

		// ==================== RING OF DUELING ====================

		group("Ring of dueling", "RING_OF_DUELING",
			ItemID.RING_OF_DUELING_8,                                // Ring of dueling(8)
			ItemID.RING_OF_DUELING_7,                                // Ring of dueling(7)
			ItemID.RING_OF_DUELING_6,                                // Ring of dueling(6)
			ItemID.RING_OF_DUELING_5,                                // Ring of dueling(5)
			ItemID.RING_OF_DUELING_4,                                // Ring of dueling(4)
			ItemID.RING_OF_DUELING_3,                                // Ring of dueling(3)
			ItemID.RING_OF_DUELING_2,                                // Ring of dueling(2)
			ItemID.RING_OF_DUELING_1                                 // Ring of dueling(1)
		);

		// ==================== COMBAT BRACELET ====================

		group("Combat bracelet", "JEWL_BRACELET_OF_COMBAT",
			ItemID.JEWL_BRACELET_OF_COMBAT_4,                        // Combat bracelet(4)
			ItemID.JEWL_BRACELET_OF_COMBAT_3,                        // Combat bracelet(3)
			ItemID.JEWL_BRACELET_OF_COMBAT_2,                        // Combat bracelet(2)
			ItemID.JEWL_BRACELET_OF_COMBAT_1,                        // Combat bracelet(1)
			ItemID.JEWL_BRACELET_OF_COMBAT                           // Combat bracelet
		);

		// ==================== SKILLS NECKLACE ====================

		group("Skills necklace", "JEWL_NECKLACE_OF_SKILLS",
			ItemID.JEWL_NECKLACE_OF_SKILLS_4,                        // Skills necklace(4)
			ItemID.JEWL_NECKLACE_OF_SKILLS_3,                        // Skills necklace(3)
			ItemID.JEWL_NECKLACE_OF_SKILLS_2,                        // Skills necklace(2)
			ItemID.JEWL_NECKLACE_OF_SKILLS_1,                        // Skills necklace(1)
			ItemID.JEWL_NECKLACE_OF_SKILLS                           // Skills necklace
		);

		// ==================== DIGSITE PENDANT ====================

		group("Digsite pendant", "NECKLACE_OF_DIGSITE",
			ItemID.NECKLACE_OF_DIGSITE_1,                            // Digsite pendant (1)
			ItemID.NECKLACE_OF_DIGSITE_2,                            // Digsite pendant (2)
			ItemID.NECKLACE_OF_DIGSITE_3,                            // Digsite pendant (3)
			ItemID.NECKLACE_OF_DIGSITE_4,                            // Digsite pendant (4)
			ItemID.NECKLACE_OF_DIGSITE_5                             // Digsite pendant (5)
		);

		// ==================== NECKLACE OF PASSAGE ====================

		group("Necklace of passage", "NECKLACE_OF_PASSAGE",
			ItemID.NECKLACE_OF_PASSAGE_5,                            // Necklace of passage(5)
			ItemID.NECKLACE_OF_PASSAGE_4,                            // Necklace of passage(4)
			ItemID.NECKLACE_OF_PASSAGE_3,                            // Necklace of passage(3)
			ItemID.NECKLACE_OF_PASSAGE_2,                            // Necklace of passage(2)
			ItemID.NECKLACE_OF_PASSAGE_1                             // Necklace of passage(1)
		);

		// ==================== BURNING AMULET ====================

		group("Burning amulet", "BURNING_AMULET",
			ItemID.BURNING_AMULET_5,                                 // Burning amulet(5)
			ItemID.BURNING_AMULET_4,                                 // Burning amulet(4)
			ItemID.BURNING_AMULET_3,                                 // Burning amulet(3)
			ItemID.BURNING_AMULET_2,                                 // Burning amulet(2)
			ItemID.BURNING_AMULET_1                                  // Burning amulet(1)
		);

		// ==================== RING OF RETURNING ====================

		group("Ring of returning", "RING_OF_RETURNING",
			ItemID.RING_OF_RETURNING_5,                              // Ring of returning(5)
			ItemID.RING_OF_RETURNING_4,                              // Ring of returning(4)
			ItemID.RING_OF_RETURNING_3,                              // Ring of returning(3)
			ItemID.RING_OF_RETURNING_2,                              // Ring of returning(2)
			ItemID.RING_OF_RETURNING_1                               // Ring of returning(1)
		);

		// ==================== AMULET OF GLORY (T) ====================

		group("Amulet of glory", "TRAIL_AMULET_OF_GLORY",
			ItemID.TRAIL_AMULET_OF_GLORY_4,                          // Amulet of glory (t4)
			ItemID.TRAIL_AMULET_OF_GLORY_3,                          // Amulet of glory (t3)
			ItemID.TRAIL_AMULET_OF_GLORY_2,                          // Amulet of glory (t2)
			ItemID.TRAIL_AMULET_OF_GLORY_1,                          // Amulet of glory (t1)
			ItemID.TRAIL_AMULET_OF_GLORY,                            // Amulet of glory (t)
			ItemID.TRAIL_AMULET_OF_GLORY_6,                          // Amulet of glory (t6)
			ItemID.TRAIL_AMULET_OF_GLORY_5                           // Amulet of glory (t5)
		);

		// ==================== TELEPORT JEWELLERY - MISC ====================

		group("Enchanted lyre", "STRUNG_LYRE",
			ItemID.VIKING_ENCHANTED_STRUNG_LYRE,                     // Enchanted lyre
			ItemID.MAGIC_STRUNG_LYRE_2,                              // Enchanted lyre(2)
			ItemID.MAGIC_STRUNG_LYRE_3,                              // Enchanted lyre(3)
			ItemID.MAGIC_STRUNG_LYRE_4                               // Enchanted lyre(4)
		);

		group("Skull sceptre", "SOS_SKULL_SCEPTRE",
			ItemID.SOS_SKULL_SCEPTRE,                                // Skull sceptre
			ItemID.SOS_SKULL_SCEPTRE_IMBUED                          // Skull sceptre (i)
		);

		// ==================== BLIGHTED SUPPLIES ====================

		group("Blighted anglerfish", "ANGLERFISH",
			ItemID.BLIGHTED_ANGLERFISH,                              // Blighted anglerfish
			ItemID.ANGLERFISH                                        // Anglerfish
		);

		group("Blighted karambwan", "ED_KARAMBWAN",
			ItemID.BLIGHTED_KARAMBWAN,                               // Blighted karambwan
			ItemID.TBWT_COOKED_KARAMBWAN                             // Cooked karambwan
		);

		group("Blighted manta ray", "MANTARAY",
			ItemID.BLIGHTED_MANTARAY,                                // Blighted manta ray
			ItemID.MANTARAY                                          // Manta ray
		);

		// ==================== ZAMORAKIAN HASTA/SPEAR ====================

		group("Zamorakian spear", "ZAMORAK",
			ItemID.ZAMORAK_SPEAR,                                    // Zamorakian spear
			ItemID.ZAMORAK_HASTA                                     // Zamorakian hasta
		);

		// ==================== BRYOPHYTA'S STAFF ====================

		group("Bryophyta's staff", "NATURE_STAFF",
			ItemID.NATURE_STAFF_CHARGED,                             // Bryophyta's staff
			ItemID.NATURE_STAFF_UNCHARGED                            // Bryophyta's staff (uncharged)
		);

		// ==================== BLUE MOON ITEMS ====================

		group("Blue Moon helm", "FROST_MOON_HELM",
			ItemID.FROST_MOON_HELM,                                  // Blue Moon helm
			ItemID.FROST_MOON_HELM_DEGRADED                          // Blue Moon helm
		);

		group("Blue Moon chestplate", "FROST_MOON_CHESTPLATE",
			ItemID.FROST_MOON_CHESTPLATE,                            // Blue Moon chestplate
			ItemID.FROST_MOON_CHESTPLATE_DEGRADED                    // Blue Moon chestplate
		);

		group("Blue Moon tassets", "FROST_MOON_TASSETS",
			ItemID.FROST_MOON_TASSETS,                               // Blue Moon tassets
			ItemID.FROST_MOON_TASSETS_DEGRADED                       // Blue Moon tassets
		);

		// ==================== BLOOD MOON ITEMS ====================

		group("Blood Moon helm", "BLOOD_MOON_HELM",
			ItemID.BLOOD_MOON_HELM,                                  // Blood Moon helm
			ItemID.BLOOD_MOON_HELM_DEGRADED                          // Blood Moon helm
		);

		group("Blood Moon chestplate", "BLOOD_MOON_CHESTPLATE",
			ItemID.BLOOD_MOON_CHESTPLATE,                            // Blood Moon chestplate
			ItemID.BLOOD_MOON_CHESTPLATE_DEGRADED                    // Blood Moon chestplate
		);

		group("Blood Moon tassets", "BLOOD_MOON_TASSETS",
			ItemID.BLOOD_MOON_TASSETS,                               // Blood Moon tassets
			ItemID.BLOOD_MOON_TASSETS_DEGRADED                       // Blood Moon tassets
		);

		// ==================== ECLIPSE MOON ITEMS ====================

		group("Eclipse Moon helm", "ECLIPSE_MOON_HELM",
			ItemID.ECLIPSE_MOON_HELM,                                // Eclipse Moon helm
			ItemID.ECLIPSE_MOON_HELM_DEGRADED                        // Eclipse Moon helm
		);

		group("Eclipse Moon chestplate", "ECLIPSE_MOON_CHESTPLATE",
			ItemID.ECLIPSE_MOON_CHESTPLATE,                          // Eclipse Moon chestplate
			ItemID.ECLIPSE_MOON_CHESTPLATE_DEGRADED                  // Eclipse Moon chestplate
		);

		group("Eclipse Moon tassets", "ECLIPSE_MOON_TASSETS",
			ItemID.ECLIPSE_MOON_TASSETS,                             // Eclipse Moon tassets
			ItemID.ECLIPSE_MOON_TASSETS_DEGRADED                     // Eclipse Moon tassets
		);

		// ==================== TONALZTICS OF RALOS ====================

		group("Tonalztics of Ralos", "TONALZTICS_OF_RALOS",
			ItemID.TONALZTICS_OF_RALOS_CHARGED,                      // Tonalztics of Ralos
			ItemID.TONALZTICS_OF_RALOS_UNCHARGED                     // Tonalztics of Ralos (uncharged)
		);

		// ==================== KERIS PARTISAN ====================

		group("Keris partisan", "KERIS_PARTISAN",
			ItemID.KERIS_PARTISAN,                                   // Keris partisan
			ItemID.KERIS_PARTISAN_BREACH,                            // Keris partisan of breaching
			ItemID.KERIS_PARTISAN_SUN                                // Keris partisan of the sun
		);

		// ==================== BOW OF FAERDHINEN (C) ====================

		group("Bow of Faerdhinen (c)", "BOW_OF_FAERDHINEN_INFINITE",
			ItemID.BOW_OF_FAERDHINEN_INFINITE,                       // Bow of Faerdhinen (c)
			ItemID.BOW_OF_FAERDHINEN_INFINITE_ITHELL,                // Bow of Faerdhinen (c)
			ItemID.BOW_OF_FAERDHINEN_INFINITE_IORWERTH,              // Bow of Faerdhinen (c)
			ItemID.BOW_OF_FAERDHINEN_INFINITE_TRAHAEARN,             // Bow of Faerdhinen (c)
			ItemID.BOW_OF_FAERDHINEN_INFINITE_CADARN,                // Bow of Faerdhinen (c)
			ItemID.BOW_OF_FAERDHINEN_INFINITE_CRWYS                  // Bow of Faerdhinen (c)
		);

		// ==================== BLADE OF SAELDOR (C) ====================

		group("Blade of Saeldor (c)", "BLADE_OF_SAELDOR_INFINITE",
			ItemID.BLADE_OF_SAELDOR_INFINITE_ITHELL,                 // Blade of Saeldor (c)
			ItemID.BLADE_OF_SAELDOR_INFINITE_IORWERTH,               // Blade of Saeldor (c)
			ItemID.BLADE_OF_SAELDOR_INFINITE_TRAHAEARN,              // Blade of Saeldor (c)
			ItemID.BLADE_OF_SAELDOR_INFINITE_CADARN,                 // Blade of Saeldor (c)
			ItemID.BLADE_OF_SAELDOR_INFINITE_CRWYS,                  // Blade of Saeldor (c)
			ItemID.BLADE_OF_SAELDOR_INFINITE_MEILYR                  // Blade of Saeldor (c)
		);

		// ==================== NIGHTMARE STAFF ====================

		group("Nightmare staff", "NIGHTMARE_STAFF",
			ItemID.NIGHTMARE_STAFF,                                  // Nightmare staff
			ItemID.NIGHTMARE_STAFF_VOLATILE,                         // Volatile Nightmare staff
			ItemID.NIGHTMARE_STAFF_ELDRITCH,                         // Eldritch Nightmare staff
			ItemID.NIGHTMARE_STAFF_HARMONISED                        // Harmonised Nightmare staff
		);

		// ==================== BANDOS ====================

		group("Bandos chestplate", "BANDOS_CHESTPLATE",
			ItemID.BANDOS_CHESTPLATE,                                // Bandos chestplate
			ItemID.BANDOS_CHESTPLATE_ORNAMENT                        // Bandos chestplate (or)
		);

		group("Bandos tassets", "BANDOS_SKIRT",
			ItemID.BANDOS_SKIRT,                                     // Bandos tassets
			ItemID.BANDOS_SKIRT_ORNAMENT                             // Bandos tassets (or)
		);

		group("Bandos boots", "BANDOS_BOOTS",
			ItemID.BANDOS_BOOTS,                                     // Bandos boots
			ItemID.BANDOS_BOOTS_ORNAMENT                             // Bandos boots (or)
		);

		// ==================== ARMADYL ====================

		group("Armadyl helmet", "ARMADYL_HELMET",
			ItemID.ARMADYL_HELMET,                                   // Armadyl helmet
			ItemID.ARMADYL_HELMET_ORNAMENT                           // Armadyl helmet (or)
		);

		group("Armadyl chestplate", "ARMADYL_CHESTPLATE",
			ItemID.ARMADYL_CHESTPLATE,                               // Armadyl chestplate
			ItemID.ARMADYL_CHESTPLATE_ORNAMENT                       // Armadyl chestplate (or)
		);

		group("Armadyl chainskirt", "ARMADYL_SKIRT",
			ItemID.ARMADYL_SKIRT,                                    // Armadyl chainskirt
			ItemID.ARMADYL_SKIRT_ORNAMENT                            // Armadyl chainskirt (or)
		);

		// ==================== SPIRIT SHIELDS ====================

		group("Elysian spirit shield", "ELYSIAN",
			ItemID.ELYSIAN,                                          // Elysian spirit shield
			ItemID.MM2_CRUSHED_ELYSIAN                               // Elysian spirit shield
		);

		// ==================== VIRTUS ROBES ====================

		group("Virtus mask", "VIRTUS_MASK",
			ItemID.VIRTUS_MASK,                                      // Virtus mask
			ItemID.VIRTUS_MASK_ORNAMENT                              // Echo virtus mask
		);

		group("Virtus robe top", "VIRTUS_TOP",
			ItemID.VIRTUS_TOP,                                       // Virtus robe top
			ItemID.VIRTUS_TOP_ORNAMENT                               // Echo virtus robe top
		);

		group("Virtus robe bottom", "VIRTUS_LEGS",
			ItemID.VIRTUS_LEGS,                                      // Virtus robe bottom
			ItemID.VIRTUS_LEGS_ORNAMENT                              // Echo virtus robe bottom
		);

		// ==================== DRAGONFIRE SHIELDS ====================

		group("Dragonfire shield", "DRAGONFIRE_SHIELD",
			ItemID.DRAGONFIRE_SHIELD,                                // Dragonfire shield
			ItemID.DRAGONFIRE_SHIELD_UNCHARGED                       // Dragonfire shield
		);

		group("Dragonfire ward", "DRAGONFIRE_WARD",
			ItemID.DRAGONFIRE_WARD,                                  // Dragonfire ward
			ItemID.DRAGONFIRE_WARD_UNCHARGED                         // Dragonfire ward
		);

		group("Ancient wyvern shield", "WYVERN_SHIELD",
			ItemID.WYVERN_SHIELD,                                    // Ancient wyvern shield
			ItemID.WYVERN_SHIELD_UNCHARGED                           // Ancient wyvern shield
		);

		// ==================== TOME OF FIRE ====================

		group("Tome of Fire", "TOME_OF_FIRE",
			ItemID.TOME_OF_FIRE,                                     // Tome of Fire
			ItemID.TOME_OF_FIRE_UNCHARGED                            // Tome of Fire (empty)
		);

		// ==================== TOME OF WATER ====================

		group("Tome of Water", "TOME_OF_WATER",
			ItemID.TOME_OF_WATER,                                    // Tome of Water
			ItemID.TOME_OF_WATER_UNCHARGED                           // Tome of Water (empty)
		);

		// ==================== BONECRUSHER ====================

		group("Bonecrusher", "BONECRUSHER",
			ItemID.BONECRUSHER,                                      // Bonecrusher
			ItemID.BONECRUSHER_NECKLACE                              // Bonecrusher necklace
		);

		// ==================== COAL BAG ====================

		group("Coal bag", "COAL_BAG",
			ItemID.COAL_BAG,                                         // Coal bag
			ItemID.COAL_BAG_OPEN                                     // Open coal bag
		);

		// ==================== GEM BAG ====================

		group("Gem bag", "GEM_BAG",
			ItemID.GEM_BAG,                                          // Gem bag
			ItemID.GEM_BAG_OPEN                                      // Open gem bag
		);

		// ==================== HERB SACK ====================

		group("Herb sack", "SLAYER_HERB_SACK",
			ItemID.SLAYER_HERB_SACK,                                 // Herb sack
			ItemID.SLAYER_HERB_SACK_OPEN                             // Open herb sack
		);

		// ==================== FISH BARREL ====================

		group("Fish barrel", "FISH_BARREL",
			ItemID.FISH_BARREL_CLOSED,                               // Fish barrel
			ItemID.FISH_BARREL_OPEN                                  // Open fish barrel
		);

		// ==================== MISCELLANIA/ETCETERIA ====================

		group("Ring of Charos", "RING_OF_CHAROS",
			ItemID.RING_OF_CHAROS,                                   // Ring of Charos
			ItemID.RING_OF_CHAROS_UNLOCKED                           // Ring of Charos(a)
		);

		// ==================== PET MORPHS (Snakeling) ====================

		group("Pet Snakeling", "SNAKEPET",
			ItemID.SNAKEPET,                                         // Pet Snakeling
			ItemID.SNAKEPET_ORANGE,                                  // Pet Snakeling
			ItemID.SNAKEPET_BLUE                                     // Pet Snakeling
		);

		// ==================== LEAGUES - CANES AND BANNERS ====================

		group("Leagues Banners", "BANNER",
			ItemID.TWISTED_BANNER,                                   // Twisted banner
			ItemID.TRAILBLAZER_BANNER,                               // Trailblazer banner
			ItemID.LEAGUE_3_BANNER,                                  // Shattered banner
			ItemID.LEAGUE_4_BANNER,                                  // Trailblazer reloaded banner
			ItemID.LEAGUE_5_BANNER                                   // Raging echoes banner
		);

		// ==================== GILDED ARMOUR ====================

		group("Gilded med helm", "RUNE_MED_HELM",
			ItemID.RUNE_MED_HELM_GOLD,                               // Gilded med helm
			ItemID.RUNE_MED_HELM                                     // Rune med helm
		);

		group("Gilded chainbody", "RUNE_CHAINBODY",
			ItemID.RUNE_CHAINBODY_GOLD,                              // Gilded chainbody
			ItemID.RUNE_CHAINBODY                                    // Rune chainbody
		);

		group("Gilded sq shield", "RUNE_SQ_SHIELD",
			ItemID.RUNE_SQ_SHIELD_GOLD,                              // Gilded sq shield
			ItemID.RUNE_SQ_SHIELD                                    // Rune sq shield
		);

		group("Gilded 2h sword", "RUNE_2H_SWORD",
			ItemID.RUNE_2H_SWORD_GOLD,                               // Gilded 2h sword
			ItemID.RUNE_2H_SWORD                                     // Rune 2h sword
		);

		group("Gilded spade", "SPADE",
			ItemID.TRAIL_GILDED_SPADE,                               // Gilded spade
			ItemID.SPADE                                             // Spade
		);

		// ==================== HERALDIC ARMOUR (H1-H5) ====================

		group("Black heraldic", "TRAIL_HERALDIC_HELM",
			ItemID.TRAIL_HERALDIC_HELM_1_BLACK,                      // Black helm (h1)
			ItemID.TRAIL_HERALDIC_HELM_2_BLACK,                      // Black helm (h2)
			ItemID.TRAIL_HERALDIC_HELM_3_BLACK,                      // Black helm (h3)
			ItemID.TRAIL_HERALDIC_HELM_4_BLACK,                      // Black helm (h4)
			ItemID.TRAIL_HERALDIC_HELM_5_BLACK                       // Black helm (h5)
		);

		group("Black shield", "BLACK_HERALDIC_KITESHIELD",
			ItemID.BLACK_HERALDIC_KITESHIELD1,                       // Black shield (h1)
			ItemID.BLACK_HERALDIC_KITESHIELD2,                       // Black shield (h2)
			ItemID.BLACK_HERALDIC_KITESHIELD3,                       // Black shield (h3)
			ItemID.BLACK_HERALDIC_KITESHIELD4,                       // Black shield (h4)
			ItemID.BLACK_HERALDIC_KITESHIELD5                        // Black shield (h5)
		);

		group("Adamant heraldic", "TRAIL_HERALDIC_HELM",
			ItemID.TRAIL_HERALDIC_HELM_1_ADAMANT,                    // Adamant helm (h1)
			ItemID.TRAIL_HERALDIC_HELM_2_ADAMANT,                    // Adamant helm (h2)
			ItemID.TRAIL_HERALDIC_HELM_3_ADAMANT,                    // Adamant helm (h3)
			ItemID.TRAIL_HERALDIC_HELM_4_ADAMANT,                    // Adamant helm (h4)
			ItemID.TRAIL_HERALDIC_HELM_5_ADAMANT                     // Adamant helm (h5)
		);

		group("Adamant shield", "ADAMANT_HERALDIC_KITESHIELD",
			ItemID.ADAMANT_HERALDIC_KITESHIELD1,                     // Adamant shield (h1)
			ItemID.ADAMANT_HERALDIC_KITESHIELD2,                     // Adamant shield (h2)
			ItemID.ADAMANT_HERALDIC_KITESHIELD3,                     // Adamant shield (h3)
			ItemID.ADAMANT_HERALDIC_KITESHIELD4,                     // Adamant shield (h4)
			ItemID.ADAMANT_HERALDIC_KITESHIELD5                      // Adamant shield (h5)
		);

		group("Rune heraldic", "TRAIL_HERALDIC_HELM",
			ItemID.TRAIL_HERALDIC_HELM_1_RUNE,                       // Rune helm (h1)
			ItemID.TRAIL_HERALDIC_HELM_2_RUNE,                       // Rune helm (h2)
			ItemID.TRAIL_HERALDIC_HELM_3_RUNE,                       // Rune helm (h3)
			ItemID.TRAIL_HERALDIC_HELM_4_RUNE,                       // Rune helm (h4)
			ItemID.TRAIL_HERALDIC_HELM_5_RUNE                        // Rune helm (h5)
		);

		group("Rune shield", "RUNE_HERALDIC_KITESHIELD",
			ItemID.RUNE_HERALDIC_KITESHIELD1,                        // Rune shield (h1)
			ItemID.RUNE_HERALDIC_KITESHIELD2,                        // Rune shield (h2)
			ItemID.RUNE_HERALDIC_KITESHIELD3,                        // Rune shield (h3)
			ItemID.RUNE_HERALDIC_KITESHIELD4,                        // Rune shield (h4)
			ItemID.RUNE_HERALDIC_KITESHIELD5                         // Rune shield (h5)
		);

		// ==================== DRAGONHIDE TRIMMED/BLESSED ====================

		group("Green d'hide trimmed", "DRAGONHIDE_BODY",
			ItemID.DRAGONHIDE_BODY_TRIM_GOLD,                        // Green d'hide body (g)
			ItemID.DRAGONHIDE_BODY_TRIM,                             // Green d'hide body (t)
			ItemID.DRAGONHIDE_BODY                                   // Green d'hide body
		);

		group("Green d'hide chaps", "DRAGONHIDE_CHAPS",
			ItemID.DRAGONHIDE_CHAPS_TRIM_GOLD,                       // Green d'hide chaps (g)
			ItemID.DRAGONHIDE_CHAPS_TRIM,                            // Green d'hide chaps (t)
			ItemID.DRAGONHIDE_CHAPS                                  // Green d'hide chaps
		);

		group("Blue d'hide trimmed", "BLUE_DRAGONHIDE_BODY",
			ItemID.BLUE_DRAGONHIDE_BODY_TRIM_GOLD,                   // Blue d'hide body (g)
			ItemID.BLUE_DRAGONHIDE_BODY_TRIM,                        // Blue d'hide body (t)
			ItemID.BLUE_DRAGONHIDE_BODY                              // Blue d'hide body
		);

		group("Blue d'hide chaps", "BLUE_DRAGONHIDE_CHAPS",
			ItemID.BLUE_DRAGONHIDE_CHAPS_TRIM_GOLD,                  // Blue d'hide chaps (g)
			ItemID.BLUE_DRAGONHIDE_CHAPS_TRIM,                       // Blue d'hide chaps (t)
			ItemID.BLUE_DRAGONHIDE_CHAPS                             // Blue d'hide chaps
		);

		group("Red d'hide trimmed", "RED_DRAGONHIDE_BODY",
			ItemID.RED_DRAGONHIDE_BODY,                              // Red d'hide body
			ItemID.RED_DRAGONHIDE_BODY_GOLD                          // Red d'hide body (g)
		);

		group("Black d'hide trimmed", "BLACK_DRAGONHIDE_BODY",
			ItemID.BLACK_DRAGONHIDE_BODY_GOLD,                       // Black d'hide body (g)
			ItemID.BLACK_DRAGONHIDE_BODY                             // Black d'hide body
		);

		group("Black d'hide chaps", "BLACK_DRAGONHIDE_CHAPS",
			ItemID.BLACK_DRAGONHIDE_CHAPS_TRIM,                      // Black d'hide chaps (t)
			ItemID.BLACK_DRAGONHIDE_CHAPS                            // Black d'hide chaps
		);

		// ==================== HOLIDAY EVENT ITEMS ====================

		group("Partyhats", "PARTYHAT",
			ItemID.RED_PARTYHAT,                                     // Red partyhat
			ItemID.YELLOW_PARTYHAT,                                  // Yellow partyhat
			ItemID.BLUE_PARTYHAT,                                    // Blue partyhat
			ItemID.GREEN_PARTYHAT,                                   // Green partyhat
			ItemID.PURPLE_PARTYHAT,                                  // Purple partyhat
			ItemID.WHITE_PARTYHAT,                                   // White partyhat
			ItemID.BLACK_PARTYHAT,                                   // Black partyhat
			ItemID.OSB10_SILVER_PARTYHAT                             // Silver partyhat
		);

		group("Santa hats", "SANTA_HAT",
			ItemID.SANTA_HAT,                                        // Santa hat
			ItemID.INVERTED_SANTA_HAT,                               // Inverted santa hat
			ItemID.BLACK_SANTA_HAT                                   // Black santa hat
		);

		// ==================== STUDDED LEATHER (TRIMMED) ====================

		group("Studded body", "STUDDED_BODY",
			ItemID.STUDDED_BODY_TRIM_GOLD,                           // Studded body (g)
			ItemID.STUDDED_BODY_TRIM_FUR,                            // Studded body (t)
			ItemID.STUDDED_BODY                                      // Studded body
		);

		group("Studded chaps", "STUDDED_CHAPS",
			ItemID.STUDDED_CHAPS_TRIM_GOLD,                          // Studded chaps (g)
			ItemID.STUDDED_CHAPS_TRIM_FUR,                           // Studded chaps (t)
			ItemID.STUDDED_CHAPS                                     // Studded chaps
		);

		// ==================== WIZARD ROBES (TRIMMED) ====================

		group("Blue wizard trimmed", "BLUEWIZHAT",
			ItemID.BLUEWIZHAT_TRIM,                                  // Blue wizard hat (t)
			ItemID.BLUEWIZHAT_TRIM_GOLD,                             // Blue wizard hat (g)
			ItemID.BLUEWIZHAT                                        // Blue wizard hat
		);

		group("Blue wizard robe", "WIZARDS_ROBE",
			ItemID.WIZARDS_ROBE_TRIM_GOLD,                           // Blue wizard robe (g)
			ItemID.WIZARDS_ROBE_TRIM,                                // Blue wizard robe (t)
			ItemID.WIZARDS_ROBE                                      // Blue wizard robe
		);

		group("Black wizard hat", "BLACKWIZHAT",
			ItemID.BLACKWIZHAT,                                      // Wizard hat
			ItemID.BLACKWIZHAT_TRIM,                                 // Black wizard hat (t)
			ItemID.BLACKWIZHAT_GOLD                                  // Black wizard hat (g)
		);

		group("Black wizard robe", "BLACK_WIZARDS_ROBE",
			ItemID.BLACK_WIZARDS_ROBE_TRIM,                          // Black wizard robe (t)
			ItemID.BLACK_WIZARDS_ROBE_GOLD                           // Black wizard robe (g)
		);

		// ==================== SPIRIT ANGLER OUTFIT ====================

		group("Angler hat and Spirit angler headband", "ANGLER_HAT|TRAWLER_REWARD_HAT",
			ItemID.TRAWLER_REWARD_HAT,                               // Angler hat
			ItemID.SPIRIT_ANGLER_HAT                                 // Spirit angler headband
		);

		group("Angler top and Spirit angler top", "ANGLER_TOP|TRAWLER_REWARD_TOP",
			ItemID.TRAWLER_REWARD_TOP,                               // Angler top
			ItemID.SPIRIT_ANGLER_TOP                                 // Spirit angler top
		);

		group("Angler waders and Spirit angler waders", "ANGLER_LEGS|TRAWLER_REWARD_LEGS",
			ItemID.TRAWLER_REWARD_LEGS,                              // Angler waders
			ItemID.SPIRIT_ANGLER_LEGS                                // Spirit angler waders
		);

		group("Angler boots and Spirit angler boots", "ANGLER_BOOTS|TRAWLER_REWARD_BOOTS",
			ItemID.TRAWLER_REWARD_BOOTS,                             // Angler boots
			ItemID.SPIRIT_ANGLER_BOOTS                               // Spirit angler boots
		);

		// ==================== DRAGON WARHAMMER ====================

		group("Dragon warhammer", "DRAGON_WARHAMMER",
			ItemID.DRAGON_WARHAMMER,                                 // Dragon warhammer
			ItemID.BH_DRAGON_WARHAMMER_CORRUPTED                     // Dragon warhammer (cr)
		);

		// ==================== DRAGON CLAWS ====================

		group("Dragon claws", "DRAGON_CLAWS",
			ItemID.DRAGON_CLAWS,                                     // Dragon claws
			ItemID.BH_DRAGON_CLAWS_CORRUPTED                         // Dragon claws (cr)
		);

		// ==================== GHOMMAL'S HILT (COMBAT ACHIEVEMENTS) ====================

		group("Ghommal's hilt 1", "CA_OFFHAND",
			ItemID.CA_OFFHAND_EASY,                                  // Ghommal's hilt 1
			ItemID.CA_OFFHAND_MEDIUM,                                // Ghommal's hilt 2
			ItemID.CA_OFFHAND_HARD,                                  // Ghommal's hilt 3
			ItemID.CA_OFFHAND_ELITE,                                 // Ghommal's hilt 4
			ItemID.CA_OFFHAND_MASTER,                                // Ghommal's hilt 5
			ItemID.CA_OFFHAND_GRANDMASTER                            // Ghommal's hilt 6
		);

		// ==================== QUEST POINT CAPE ====================

		group("Quest point cape", "SKILLCAPE_QP",
			ItemID.SKILLCAPE_QP,                                     // Quest point cape
			ItemID.SKILLCAPE_QP_TRIMMED                              // Quest point cape (t)
		);

		// ==================== MUSIC CAPE ====================

		group("Music cape", "MUSIC_CAPE",
			ItemID.MUSIC_CAPE,                                       // Music cape
			ItemID.MUSIC_CAPE_TRIMMED                                // Music cape(t)
		);

		// ==================== ABYSSAL LANTERN ====================

		group("Abyssal lantern", "ABYSSAL_LANTERN",
			ItemID.ABYSSAL_LANTERN,                                  // Abyssal lantern
			ItemID.ABYSSAL_LANTERN_NORMAL,                           // Abyssal lantern (normal logs)
			ItemID.ABYSSAL_LANTERN_NORMAL_BLUE,                      // Abyssal lantern (blue logs)
			ItemID.ABYSSAL_LANTERN_NORMAL_RED,                       // Abyssal lantern (red logs)
			ItemID.ABYSSAL_LANTERN_NORMAL_WHITE,                     // Abyssal lantern (white logs)
			ItemID.ABYSSAL_LANTERN_NORMAL_PURPLE,                    // Abyssal lantern (purple logs)
			ItemID.ABYSSAL_LANTERN_NORMAL_GREEN,                     // Abyssal lantern (green logs)
			ItemID.ABYSSAL_LANTERN_OAK,                              // Abyssal lantern (oak logs)
			ItemID.ABYSSAL_LANTERN_WILLOW,                           // Abyssal lantern (willow logs)
			ItemID.ABYSSAL_LANTERN_MAPLE,                            // Abyssal lantern (maple logs)
			ItemID.ABYSSAL_LANTERN_YEW,                              // Abyssal lantern (yew logs)
			ItemID.ABYSSAL_LANTERN_BLISTERWOOD,                      // Abyssal lantern (blisterwood logs)
			ItemID.ABYSSAL_LANTERN_MAGIC,                            // Abyssal lantern (magic logs)
			ItemID.ABYSSAL_LANTERN_REDWOOD                           // Abyssal lantern (redwood logs)
		);

		// ==================== HAT OF THE EYE ====================

		group("Hat of the Eye", "HAT_OF_THE_EYE",
			ItemID.HAT_OF_THE_EYE,                                   // Hat of the Eye
			ItemID.HAT_OF_THE_EYE_RED,                               // Hat of the Eye (red)
			ItemID.HAT_OF_THE_EYE_GREEN,                             // Hat of the Eye (green)
			ItemID.HAT_OF_THE_EYE_BLUE                               // Hat of the Eye (blue)
		);

		// ==================== SKILL CAPES (UNTRIMMED/TRIMMED) ====================

		group("Attack cape", "SKILLCAPE_ATTACK",
			ItemID.SKILLCAPE_ATTACK,                                 // Attack cape
			ItemID.SKILLCAPE_ATTACK_TRIMMED                          // Attack cape(t)
		);

		group("Strength cape", "SKILLCAPE_STRENGTH",
			ItemID.SKILLCAPE_STRENGTH,                               // Strength cape
			ItemID.SKILLCAPE_STRENGTH_TRIMMED                        // Strength cape(t)
		);

		group("Defence cape", "SKILLCAPE_DEFENCE",
			ItemID.SKILLCAPE_DEFENCE,                                // Defence cape
			ItemID.SKILLCAPE_DEFENCE_TRIMMED                         // Defence cape(t)
		);

		group("Prayer cape", "SKILLCAPE_PRAYER",
			ItemID.SKILLCAPE_PRAYER,                                 // Prayer cape
			ItemID.SKILLCAPE_PRAYER_TRIMMED                          // Prayer cape(t)
		);

		group("Magic cape", "SKILLCAPE_MAGIC",
			ItemID.SKILLCAPE_MAGIC,                                  // Magic cape
			ItemID.SKILLCAPE_MAGIC_TRIMMED                           // Magic cape(t)
		);

		group("Runecraft cape", "SKILLCAPE_RUNECRAFTING",
			ItemID.SKILLCAPE_RUNECRAFTING,                           // Runecraft cape
			ItemID.SKILLCAPE_RUNECRAFTING_TRIMMED                    // Runecraft cape(t)
		);

		group("Hitpoints cape", "SKILLCAPE_HITPOINTS",
			ItemID.SKILLCAPE_HITPOINTS,                              // Hitpoints cape
			ItemID.SKILLCAPE_HITPOINTS_TRIMMED                       // Hitpoints cape(t)
		);

		group("Agility cape", "SKILLCAPE_AGILITY",
			ItemID.SKILLCAPE_AGILITY,                                // Agility cape
			ItemID.SKILLCAPE_AGILITY_TRIMMED                         // Agility cape(t)
		);

		group("Herblore cape", "SKILLCAPE_HERBLORE",
			ItemID.SKILLCAPE_HERBLORE,                               // Herblore cape
			ItemID.SKILLCAPE_HERBLORE_TRIMMED                        // Herblore cape(t)
		);

		group("Thieving cape", "SKILLCAPE_THIEVING",
			ItemID.SKILLCAPE_THIEVING,                               // Thieving cape
			ItemID.SKILLCAPE_THIEVING_TRIMMED                        // Thieving cape(t)
		);

		group("Crafting cape", "SKILLCAPE_CRAFTING",
			ItemID.SKILLCAPE_CRAFTING,                               // Crafting cape
			ItemID.SKILLCAPE_CRAFTING_TRIMMED                        // Crafting cape(t)
		);

		group("Fletching cape", "SKILLCAPE_FLETCHING",
			ItemID.SKILLCAPE_FLETCHING,                              // Fletching cape
			ItemID.SKILLCAPE_FLETCHING_TRIMMED                       // Fletching cape(t)
		);

		group("Slayer cape", "SKILLCAPE_SLAYER",
			ItemID.SKILLCAPE_SLAYER,                                 // Slayer cape
			ItemID.SKILLCAPE_SLAYER_TRIMMED                          // Slayer cape(t)
		);

		group("Mining cape", "SKILLCAPE_MINING",
			ItemID.SKILLCAPE_MINING,                                 // Mining cape
			ItemID.SKILLCAPE_MINING_TRIMMED                          // Mining cape(t)
		);

		group("Smithing cape", "SKILLCAPE_SMITHING",
			ItemID.SKILLCAPE_SMITHING,                               // Smithing cape
			ItemID.SKILLCAPE_SMITHING_TRIMMED                        // Smithing cape(t)
		);

		group("Fishing cape", "SKILLCAPE_FISHING",
			ItemID.SKILLCAPE_FISHING,                                // Fishing cape
			ItemID.SKILLCAPE_FISHING_TRIMMED                         // Fishing cape(t)
		);

		group("Cooking cape", "SKILLCAPE_COOKING",
			ItemID.SKILLCAPE_COOKING,                                // Cooking cape
			ItemID.SKILLCAPE_COOKING_TRIMMED                         // Cooking cape(t)
		);

		group("Firemaking cape", "SKILLCAPE_FIREMAKING",
			ItemID.SKILLCAPE_FIREMAKING,                             // Firemaking cape
			ItemID.SKILLCAPE_FIREMAKING_TRIMMED                      // Firemaking cape(t)
		);

		group("Farming cape", "SKILLCAPE_FARMING",
			ItemID.SKILLCAPE_FARMING,                                // Farming cape
			ItemID.SKILLCAPE_FARMING_TRIMMED                         // Farming cape(t)
		);

		group("Hunter cape", "SKILLCAPE_HUNTING",
			ItemID.SKILLCAPE_HUNTING,                                // Hunter cape
			ItemID.SKILLCAPE_HUNTING_TRIMMED                         // Hunter cape(t)
		);

		// ==================== GRACEFUL OUTFIT ====================

		group("Graceful hood", "GRACEFUL_HOOD",
			ItemID.GRACEFUL_HOOD,                                    // Graceful hood
			ItemID.ZEAH_GRACEFUL_HOOD_ARCEUUS,                       // Graceful hood
			ItemID.ZEAH_GRACEFUL_HOOD_PISCARILIUS,                   // Graceful hood
			ItemID.ZEAH_GRACEFUL_HOOD_LOVAKENGJ,                     // Graceful hood
			ItemID.ZEAH_GRACEFUL_HOOD_SHAYZIEN,                      // Graceful hood
			ItemID.ZEAH_GRACEFUL_HOOD_HOSIDIUS,                      // Graceful hood
			ItemID.ZEAH_GRACEFUL_HOOD_KOUREND,                       // Graceful hood
			ItemID.GRACEFUL_HOOD_SKILLCAPECOLOUR,                    // Graceful hood
			ItemID.GRACEFUL_HOOD_HALLOWED,                           // Graceful hood
			ItemID.GRACEFUL_HOOD_TRAILBLAZER,                        // Graceful hood
			ItemID.GRACEFUL_HOOD_ADVENTURER,                         // Graceful hood
			ItemID.GRACEFUL_HOOD_WYRM                                // Graceful hood
		);

		group("Graceful cape", "GRACEFUL_CAPE",
			ItemID.GRACEFUL_CAPE,                                    // Graceful cape
			ItemID.ZEAH_GRACEFUL_CAPE_ARCEUUS,                       // Graceful cape
			ItemID.ZEAH_GRACEFUL_CAPE_PISCARILIUS,                   // Graceful cape
			ItemID.ZEAH_GRACEFUL_CAPE_LOVAKENGJ,                     // Graceful cape
			ItemID.ZEAH_GRACEFUL_CAPE_SHAYZIEN,                      // Graceful cape
			ItemID.ZEAH_GRACEFUL_CAPE_HOSIDIUS,                      // Graceful cape
			ItemID.ZEAH_GRACEFUL_CAPE_KOUREND,                       // Graceful cape
			ItemID.GRACEFUL_CAPE_SKILLCAPECOLOUR,                    // Graceful cape
			ItemID.GRACEFUL_CAPE_HALLOWED,                           // Graceful cape
			ItemID.GRACEFUL_CAPE_TRAILBLAZER,                        // Graceful cape
			ItemID.GRACEFUL_CAPE_ADVENTURER,                         // Graceful cape
			ItemID.GRACEFUL_CAPE_WYRM                                // Graceful cape
		);

		group("Graceful top", "GRACEFUL_TOP",
			ItemID.GRACEFUL_TOP,                                     // Graceful top
			ItemID.ZEAH_GRACEFUL_TOP_ARCEUUS,                        // Graceful top
			ItemID.ZEAH_GRACEFUL_TOP_PISCARILIUS,                    // Graceful top
			ItemID.ZEAH_GRACEFUL_TOP_LOVAKENGJ,                      // Graceful top
			ItemID.ZEAH_GRACEFUL_TOP_SHAYZIEN,                       // Graceful top
			ItemID.ZEAH_GRACEFUL_TOP_HOSIDIUS,                       // Graceful top
			ItemID.ZEAH_GRACEFUL_TOP_KOUREND,                        // Graceful top
			ItemID.GRACEFUL_TOP_SKILLCAPECOLOUR,                     // Graceful top
			ItemID.GRACEFUL_TOP_HALLOWED,                            // Graceful top
			ItemID.GRACEFUL_TOP_TRAILBLAZER,                         // Graceful top
			ItemID.GRACEFUL_TOP_ADVENTURER,                          // Graceful top
			ItemID.GRACEFUL_TOP_WYRM                                 // Graceful top
		);

		group("Graceful legs", "GRACEFUL_LEGS",
			ItemID.GRACEFUL_LEGS,                                    // Graceful legs
			ItemID.ZEAH_GRACEFUL_LEGS_ARCEUUS,                       // Graceful legs
			ItemID.ZEAH_GRACEFUL_LEGS_PISCARILIUS,                   // Graceful legs
			ItemID.ZEAH_GRACEFUL_LEGS_LOVAKENGJ,                     // Graceful legs
			ItemID.ZEAH_GRACEFUL_LEGS_SHAYZIEN,                      // Graceful legs
			ItemID.ZEAH_GRACEFUL_LEGS_HOSIDIUS,                      // Graceful legs
			ItemID.ZEAH_GRACEFUL_LEGS_KOUREND,                       // Graceful legs
			ItemID.GRACEFUL_LEGS_SKILLCAPECOLOUR,                    // Graceful legs
			ItemID.GRACEFUL_LEGS_HALLOWED,                           // Graceful legs
			ItemID.GRACEFUL_LEGS_TRAILBLAZER,                        // Graceful legs
			ItemID.GRACEFUL_LEGS_ADVENTURER,                         // Graceful legs
			ItemID.GRACEFUL_LEGS_WYRM                                // Graceful legs
		);

		group("Graceful gloves", "GRACEFUL_GLOVES",
			ItemID.GRACEFUL_GLOVES,                                  // Graceful gloves
			ItemID.ZEAH_GRACEFUL_GLOVES_ARCEUUS,                     // Graceful gloves
			ItemID.ZEAH_GRACEFUL_GLOVES_PISCARILIUS,                 // Graceful gloves
			ItemID.ZEAH_GRACEFUL_GLOVES_LOVAKENGJ,                   // Graceful gloves
			ItemID.ZEAH_GRACEFUL_GLOVES_SHAYZIEN,                    // Graceful gloves
			ItemID.ZEAH_GRACEFUL_GLOVES_HOSIDIUS,                    // Graceful gloves
			ItemID.ZEAH_GRACEFUL_GLOVES_KOUREND,                     // Graceful gloves
			ItemID.GRACEFUL_GLOVES_SKILLCAPECOLOUR,                  // Graceful gloves
			ItemID.GRACEFUL_GLOVES_HALLOWED,                         // Graceful gloves
			ItemID.GRACEFUL_GLOVES_TRAILBLAZER,                      // Graceful gloves
			ItemID.GRACEFUL_GLOVES_ADVENTURER,                       // Graceful gloves
			ItemID.GRACEFUL_GLOVES_WYRM                              // Graceful gloves
		);

		group("Graceful boots", "GRACEFUL_BOOTS",
			ItemID.GRACEFUL_BOOTS,                                   // Graceful boots
			ItemID.ZEAH_GRACEFUL_BOOTS_ARCEUUS,                      // Graceful boots
			ItemID.ZEAH_GRACEFUL_BOOTS_PISCARILIUS,                  // Graceful boots
			ItemID.ZEAH_GRACEFUL_BOOTS_LOVAKENGJ,                    // Graceful boots
			ItemID.ZEAH_GRACEFUL_BOOTS_SHAYZIEN,                     // Graceful boots
			ItemID.ZEAH_GRACEFUL_BOOTS_HOSIDIUS,                     // Graceful boots
			ItemID.ZEAH_GRACEFUL_BOOTS_KOUREND,                      // Graceful boots
			ItemID.GRACEFUL_BOOTS_SKILLCAPECOLOUR,                   // Graceful boots
			ItemID.GRACEFUL_BOOTS_HALLOWED,                          // Graceful boots
			ItemID.GRACEFUL_BOOTS_TRAILBLAZER,                       // Graceful boots
			ItemID.GRACEFUL_BOOTS_ADVENTURER,                        // Graceful boots
			ItemID.GRACEFUL_BOOTS_WYRM                               // Graceful boots
		);

		// ==================== SLAYER HELMETS ====================

		group("Slayer helmet (all colours and imbued versions)", "SLAYER_HELM",
			ItemID.SLAYER_HELM,                                      // Slayer helmet
			ItemID.SLAYER_HELM_I,                                    // Slayer helmet (i)
			ItemID.SLAYER_HELM_BLACK,                                // Black slayer helmet
			ItemID.SLAYER_HELM_I_BLACK,                              // Black slayer helmet (i)
			ItemID.SLAYER_HELM_GREEN,                                // Green slayer helmet
			ItemID.SLAYER_HELM_I_GREEN,                              // Green slayer helmet (i)
			ItemID.SLAYER_HELM_RED,                                  // Red slayer helmet
			ItemID.SLAYER_HELM_I_RED,                                // Red slayer helmet (i)
			ItemID.SLAYER_HELM_PURPLE,                               // Purple slayer helmet
			ItemID.SLAYER_HELM_I_PURPLE,                             // Purple slayer helmet (i)
			ItemID.SLAYER_HELM_TURQUOISE,                            // Turquoise slayer helmet
			ItemID.SLAYER_HELM_I_TURQUOISE,                          // Turquoise slayer helmet (i)
			ItemID.SLAYER_HELM_HYDRA,                                // Hydra slayer helmet
			ItemID.SLAYER_HELM_I_HYDRA,                              // Hydra slayer helmet (i)
			ItemID.SLAYER_HELM_TWISTED,                              // Twisted slayer helmet
			ItemID.SLAYER_HELM_I_TWISTED,                            // Twisted slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I,                                 // Slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_BLACK,                           // Black slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_GREEN,                           // Green slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_RED,                             // Red slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_PURPLE,                          // Purple slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_TURQUOISE,                       // Turquoise slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_HYDRA,                           // Hydra slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_TWISTED,                         // Twisted slayer helmet (i)
			ItemID.SLAYER_HELM_JAD,                                  // Tztok slayer helmet
			ItemID.SLAYER_HELM_I_JAD,                                // Tztok slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_JAD,                             // Tztok slayer helmet (i)
			ItemID.SLAYER_HELM_VERZIK,                               // Vampyric slayer helmet
			ItemID.SLAYER_HELM_I_VERZIK,                             // Vampyric slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_VERZIK,                          // Vampyric slayer helmet (i)
			ItemID.SLAYER_HELM_ZUK,                                  // Tzkal slayer helmet
			ItemID.SLAYER_HELM_I_ZUK,                                // Tzkal slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_ZUK,                             // Tzkal slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I,                               // Slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_BLACK,                         // Black slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_GREEN,                         // Green slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_RED,                           // Red slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_PURPLE,                        // Purple slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_TURQUOISE,                     // Turquoise slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_HYDRA,                         // Hydra slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_TWISTED,                       // Twisted slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_JAD,                           // Tztok slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_VERZIK,                        // Vampyric slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_ZUK,                           // Tzkal slayer helmet (i)
			ItemID.SLAYER_HELM_ARAXYTE,                              // Araxyte slayer helmet
			ItemID.SLAYER_HELM_I_ARAXYTE,                            // Araxyte slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_ARAXYTE,                         // Araxyte slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_ARAXYTE,                       // Araxyte slayer helmet (i)
			ItemID.SLAYER_HELM_HOODED,                               // Hooded slayer helmet
			ItemID.SLAYER_HELM_I_HOODED,                             // Hooded slayer helmet (i)
			ItemID.SW_SLAYER_HELM_I_HOODED,                          // Hooded slayer helmet (i)
			ItemID.PVPA_SLAYER_HELM_I_HOODED,                        // Hooded slayer helmet (i)
			ItemID.LEAGUE_6_SLAYER_HELM1,                            // Oathplate slayer helmet
			ItemID.LEAGUE_6_SLAYER_HELM2,                            // Radiant slayer helmet
			ItemID.LEAGUE_6_SLAYER_HELM1_I,                          // Oathplate slayer helmet (i)
			ItemID.SW_LEAGUE_6_SLAYER_HELM1_I,                       // Oathplate slayer helmet (i)
			ItemID.PVPA_LEAGUE_6_SLAYER_HELM1_I,                     // Oathplate slayer helmet (i)
			ItemID.LEAGUE_6_SLAYER_HELM2_I,                          // Radiant slayer helmet (i)
			ItemID.SW_LEAGUE_6_SLAYER_HELM2_I,                       // Radiant slayer helmet (i)
			ItemID.PVPA_LEAGUE_6_SLAYER_HELM2_I                      // Radiant slayer helmet (i)
		);

		// ==================== VOID KNIGHT EQUIPMENT ====================

		group("Void knight top", "VOID_KNIGHT_TOP",
			ItemID.PEST_VOID_KNIGHT_TOP,                             // Void knight top
			ItemID.ELITE_VOID_KNIGHT_TOP,                            // Elite void top
			ItemID.PEST_VOID_KNIGHT_TOP_TROUVER,                     // Void knight top (l)
			ItemID.ELITE_VOID_KNIGHT_TOP_TROUVER,                    // Elite void top (l)
			ItemID.LEAGUE_3_VOID_KNIGHT_TOP,                         // Void knight top (or)
			ItemID.LEAGUE_3_VOID_KNIGHT_TOP_ELITE,                   // Elite void top (or)
			ItemID.LEAGUE_3_VOID_KNIGHT_TOP_TROUVER,                 // Void knight top (l)(or)
			ItemID.LEAGUE_3_VOID_KNIGHT_TOP_ELITE_TROUVER            // Elite void top (l)(or)
		);

		group("Void knight robe", "VOID_KNIGHT_ROBES",
			ItemID.PEST_VOID_KNIGHT_ROBES,                           // Void knight robe
			ItemID.ELITE_VOID_KNIGHT_ROBES,                          // Elite void robe
			ItemID.PEST_VOID_KNIGHT_ROBES_TROUVER,                   // Void knight robe (l)
			ItemID.ELITE_VOID_KNIGHT_ROBES_TROUVER,                  // Elite void robe (l)
			ItemID.LEAGUE_3_VOID_KNIGHT_ROBES,                       // Void knight robe (or)
			ItemID.LEAGUE_3_VOID_KNIGHT_ROBES_ELITE,                 // Elite void robe (or)
			ItemID.LEAGUE_3_VOID_KNIGHT_ROBES_TROUVER,               // Void knight robe (l)(or)
			ItemID.LEAGUE_3_VOID_KNIGHT_ROBES_ELITE_TROUVER          // Elite void robe (l)(or)
		);

		group("Void knight gloves", "VOID_KNIGHT_GLOVES",
			ItemID.PEST_VOID_KNIGHT_GLOVES,                          // Void knight gloves
			ItemID.PEST_VOID_KNIGHT_GLOVES_TROUVER,                  // Void knight gloves (l)
			ItemID.LEAGUE_3_VOID_KNIGHT_GLOVES,                      // Void knight gloves (or)
			ItemID.LEAGUE_3_VOID_KNIGHT_GLOVES_TROUVER               // Void knight gloves (l)(or)
		);

		group("Void mage helm", "MAGE_HELM",
			ItemID.GAME_PEST_MAGE_HELM,                              // Void mage helm
			ItemID.GAME_PEST_MAGE_HELM_TROUVER,                      // Void mage helm (l)
			ItemID.LEAGUE_3_VOID_MAGE_HELM,                          // Void mage helm (or)
			ItemID.LEAGUE_3_VOID_MAGE_HELM_TROUVER                   // Void mage helm (l)(or)
		);

		group("Void ranger helm", "ARCHER_HELM|RANGE_HELM",
			ItemID.GAME_PEST_ARCHER_HELM,                            // Void ranger helm
			ItemID.GAME_PEST_ARCHER_HELM_TROUVER,                    // Void ranger helm (l)
			ItemID.LEAGUE_3_VOID_RANGE_HELM,                         // Void ranger helm (or)
			ItemID.LEAGUE_3_VOID_RANGE_HELM_TROUVER                  // Void ranger helm (l)(or)
		);

		group("Void melee helm", "MELEE_HELM",
			ItemID.GAME_PEST_MELEE_HELM,                             // Void melee helm
			ItemID.GAME_PEST_MELEE_HELM_TROUVER,                     // Void melee helm (l)
			ItemID.LEAGUE_3_VOID_MELEE_HELM,                         // Void melee helm (or)
			ItemID.LEAGUE_3_VOID_MELEE_HELM_TROUVER                  // Void melee helm (l)(or)
		);

		// ==================== LEAGUES REWARDS ====================

		group("Twisted relic hunter hat (t1-t3)", "RELIC_HUNTER",
			ItemID.TWISTED_RELIC_HUNTER_HAT_T3,                      // Twisted hat (t3)
			ItemID.TWISTED_RELIC_HUNTER_HAT_T2,                      // Twisted hat (t2)
			ItemID.TWISTED_RELIC_HUNTER_HAT_T1                       // Twisted hat (t1)
		);

		group("Twisted relic hunter top (t1-t3)", "RELIC_HUNTER",
			ItemID.TWISTED_RELIC_HUNTER_TOP_T3,                      // Twisted coat (t3)
			ItemID.TWISTED_RELIC_HUNTER_TOP_T2,                      // Twisted coat (t2)
			ItemID.TWISTED_RELIC_HUNTER_TOP_T1                       // Twisted coat (t1)
		);

		group("Twisted relic hunter legs (t1-t3)", "RELIC_HUNTER",
			ItemID.TWISTED_RELIC_HUNTER_LEGS_T3,                     // Twisted trousers (t3)
			ItemID.TWISTED_RELIC_HUNTER_LEGS_T2,                     // Twisted trousers (t2)
			ItemID.TWISTED_RELIC_HUNTER_LEGS_T1                      // Twisted trousers (t1)
		);

		group("Twisted relic hunter boots (t1-t3)", "RELIC_HUNTER",
			ItemID.TWISTED_RELIC_HUNTER_BOOTS_T3,                    // Twisted boots (t3)
			ItemID.TWISTED_RELIC_HUNTER_BOOTS_T2,                    // Twisted boots (t2)
			ItemID.TWISTED_RELIC_HUNTER_BOOTS_T1                     // Twisted boots (t1)
		);

		group("Trailblazer relic hunter hat (t1-t3)", "RELIC_HUNTER",
			ItemID.TRAILBLAZER_RELIC_HUNTER_HOOD_T3,                 // Trailblazer hood (t3)
			ItemID.TRAILBLAZER_RELIC_HUNTER_HOOD_T2,                 // Trailblazer hood (t2)
			ItemID.TRAILBLAZER_RELIC_HUNTER_HOOD_T1                  // Trailblazer hood (t1)
		);

		group("Trailblazer relic hunter top (t1-t3)", "RELIC_HUNTER",
			ItemID.TRAILBLAZER_RELIC_HUNTER_TOP_T3,                  // Trailblazer top (t3)
			ItemID.TRAILBLAZER_RELIC_HUNTER_TOP_T2,                  // Trailblazer top (t2)
			ItemID.TRAILBLAZER_RELIC_HUNTER_TOP_T1                   // Trailblazer top (t1)
		);

		group("Trailblazer relic hunter legs (t1-t3)", "RELIC_HUNTER",
			ItemID.TRAILBLAZER_RELIC_HUNTER_LEGS_T3,                 // Trailblazer trousers (t3)
			ItemID.TRAILBLAZER_RELIC_HUNTER_LEGS_T2,                 // Trailblazer trousers (t2)
			ItemID.TRAILBLAZER_RELIC_HUNTER_LEGS_T1                  // Trailblazer trousers (t1)
		);

		group("Trailblazer relic hunter boots (t1-t3)", "RELIC_HUNTER",
			ItemID.TRAILBLAZER_RELIC_HUNTER_BOOTS_T3,                // Trailblazer boots (t3)
			ItemID.TRAILBLAZER_RELIC_HUNTER_BOOTS_T2,                // Trailblazer boots (t2)
			ItemID.TRAILBLAZER_RELIC_HUNTER_BOOTS_T1                 // Trailblazer boots (t1)
		);

		group("Shattered relic hunter hat (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_3_RELIC_HUNTER_HOOD_T1,                    // Shattered hood (t1)
			ItemID.LEAGUE_3_RELIC_HUNTER_HOOD_T2,                    // Shattered hood (t2)
			ItemID.LEAGUE_3_RELIC_HUNTER_HOOD_T3                     // Shattered hood (t3)
		);

		group("Shattered relic hunter top (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_3_RELIC_HUNTER_TOP_T1,                     // Shattered top (t1)
			ItemID.LEAGUE_3_RELIC_HUNTER_TOP_T2,                     // Shattered top (t2)
			ItemID.LEAGUE_3_RELIC_HUNTER_TOP_T3                      // Shattered top (t3)
		);

		group("Shattered relic hunter legs (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_3_RELIC_HUNTER_LEGS_T1,                    // Shattered trousers (t1)
			ItemID.LEAGUE_3_RELIC_HUNTER_LEGS_T2,                    // Shattered trousers (t2)
			ItemID.LEAGUE_3_RELIC_HUNTER_LEGS_T3                     // Shattered trousers (t3)
		);

		group("Shattered relic hunter boots (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_3_RELIC_HUNTER_BOOTS_T1,                   // Shattered boots (t1)
			ItemID.LEAGUE_3_RELIC_HUNTER_BOOTS_T2,                   // Shattered boots (t2)
			ItemID.LEAGUE_3_RELIC_HUNTER_BOOTS_T3                    // Shattered boots (t3)
		);

		group("Trailblazer reloaded relic hunter hat (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_4_RELIC_HUNTER_HAT_T1,                     // Trailblazer reloaded headband (t1)
			ItemID.LEAGUE_4_RELIC_HUNTER_HAT_T2,                     // Trailblazer reloaded headband (t2)
			ItemID.LEAGUE_4_RELIC_HUNTER_HAT_T3                      // Trailblazer reloaded headband (t3)
		);

		group("Trailblazer reloaded relic hunter top (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_4_RELIC_HUNTER_TOP_T1,                     // Trailblazer reloaded top (t1)
			ItemID.LEAGUE_4_RELIC_HUNTER_TOP_T2,                     // Trailblazer reloaded top (t2)
			ItemID.LEAGUE_4_RELIC_HUNTER_TOP_T3                      // Trailblazer reloaded top (t3)
		);

		group("Trailblazer reloaded relic hunter legs (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_4_RELIC_HUNTER_LEGS_T1,                    // Trailblazer reloaded trousers (t1)
			ItemID.LEAGUE_4_RELIC_HUNTER_LEGS_T2,                    // Trailblazer reloaded trousers (t2)
			ItemID.LEAGUE_4_RELIC_HUNTER_LEGS_T3                     // Trailblazer reloaded trousers (t3)
		);

		group("Trailblazer reloaded relic hunter boots (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_4_RELIC_HUNTER_BOOTS_T1,                   // Trailblazer reloaded boots (t1)
			ItemID.LEAGUE_4_RELIC_HUNTER_BOOTS_T2,                   // Trailblazer reloaded boots (t2)
			ItemID.LEAGUE_4_RELIC_HUNTER_BOOTS_T3                    // Trailblazer reloaded boots (t3)
		);

		group("Raging echoes relic hunter hat (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE5_RELIC_HUNTER_HAT_T1,                      // Raging echoes hat (t1)
			ItemID.LEAGUE5_RELIC_HUNTER_HAT_T2,                      // Raging echoes hat (t2)
			ItemID.LEAGUE5_RELIC_HUNTER_HAT_T3                       // Raging echoes hat (t3)
		);

		group("Raging echoes relic hunter top (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE5_RELIC_HUNTER_TOP_T1,                      // Raging echoes top (t1)
			ItemID.LEAGUE5_RELIC_HUNTER_TOP_T2,                      // Raging echoes top (t2)
			ItemID.LEAGUE5_RELIC_HUNTER_TOP_T3                       // Raging echoes top (t3)
		);

		group("Raging echoes relic hunter legs (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE5_RELIC_HUNTER_LEGS_T1,                     // Raging echoes robeskirt (t1)
			ItemID.LEAGUE5_RELIC_HUNTER_LEGS_T2,                     // Raging echoes robeskirt (t2)
			ItemID.LEAGUE5_RELIC_HUNTER_LEGS_T3                      // Raging echoes robeskirt (t3)
		);

		group("Raging echoes relic hunter boots (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE5_RELIC_HUNTER_BOOTS_T1,                    // Raging echoes boots (t1)
			ItemID.LEAGUE5_RELIC_HUNTER_BOOTS_T2,                    // Raging echoes boots (t2)
			ItemID.LEAGUE5_RELIC_HUNTER_BOOTS_T3                     // Raging echoes boots (t3)
		);

		group("Demonic relic hunter hat (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_6_RELIC_HUNTER_HOOD_T1,                    // Demonic hood (t1)
			ItemID.LEAGUE_6_RELIC_HUNTER_HOOD_T2,                    // Demonic hood (t2)
			ItemID.LEAGUE_6_RELIC_HUNTER_HOOD_T3                     // Demonic hood (t3)
		);

		group("Demonic relic hunter top (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_6_RELIC_HUNTER_TOP_T1,                     // Demonic robe top (t1)
			ItemID.LEAGUE_6_RELIC_HUNTER_TOP_T2,                     // Demonic robe top (t2)
			ItemID.LEAGUE_6_RELIC_HUNTER_TOP_T3                      // Demonic robe top (t3)
		);

		group("Demonic relic hunter legs (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_6_RELIC_HUNTER_LEGS_T1,                    // Demonic robe bottom (t1)
			ItemID.LEAGUE_6_RELIC_HUNTER_LEGS_T2,                    // Demonic robe bottom (t2)
			ItemID.LEAGUE_6_RELIC_HUNTER_LEGS_T3                     // Demonic robe bottom (t3)
		);

		group("Demonic relic hunter boots (t1-t3)", "RELIC_HUNTER",
			ItemID.LEAGUE_6_RELIC_HUNTER_BOOTS_T1,                   // Demonic boots (t1)
			ItemID.LEAGUE_6_RELIC_HUNTER_BOOTS_T2,                   // Demonic boots (t2)
			ItemID.LEAGUE_6_RELIC_HUNTER_BOOTS_T3                    // Demonic boots (t3)
		);

		group("League canes", "CANE",
			ItemID.TWISTED_CANE,                                     // Twisted cane
			ItemID.TRAILBLAZER_CANE,                                 // Trailblazer cane
			ItemID.LEAGUE_3_CANE,                                    // Shattered cane
			ItemID.LEAGUE5_RELIC_HUNTER_CANE                         // Raging echoes cane
		);

		// ==================== CASTLE WARS DECORATIVE ====================

		group("Decorative sword", "CASTLEWARS_SWORD",
			ItemID.CASTLEWARS_SWORD,                                 // Decorative sword
			ItemID.CASTLEWARS_SWORD_2,                               // Decorative sword
			ItemID.CASTLEWARS_SWORD_3,                               // Decorative sword
			ItemID.CASTLEWARS_SWORD_3_TROUVER                        // Decorative sword (l)
		);

		group("Decorative armour body", "CASTLEWARS_ARMOUR_BODY",
			ItemID.CASTLEWARS_ARMOUR_BODY,                           // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_BODY_2,                         // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_BODY_3,                         // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_BODY_3_TROUVER                  // Decorative armour (l)
		);

		group("Decorative armour legs", "CASTLEWARS_ARMOUR_LEGS",
			ItemID.CASTLEWARS_ARMOUR_LEGS,                           // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_LEGS_2,                         // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_LEGS_3,                         // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_LEGS_3_TROUVER                  // Decorative armour (l)
		);

		group("Decorative armour skirt", "CASTLEWARS_ARMOUR_SKIRT",
			ItemID.CASTLEWARS_ARMOUR_SKIRT,                          // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_SKIRT_2,                        // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_SKIRT_3,                        // Decorative armour
			ItemID.CASTLEWARS_ARMOUR_SKIRT_3_TROUVER                 // Decorative armour (l)
		);

		group("Decorative med helm", "CASTLEWARS_MED_HELM",
			ItemID.CASTLEWARS_MED_HELM,                              // Decorative helm
			ItemID.CASTLEWARS_MED_HELM_2,                            // Decorative helm
			ItemID.CASTLEWARS_MED_HELM_3,                            // Decorative helm
			ItemID.CASTLEWARS_MED_HELM_3_TROUVER                     // Decorative helm (l)
		);

		group("Decorative shield", "CASTLEWARS_SHIELD",
			ItemID.CASTLEWARS_SHIELD,                                // Decorative shield
			ItemID.CASTLEWARS_SHIELD_2,                              // Decorative shield
			ItemID.CASTLEWARS_SHIELD_3,                              // Decorative shield
			ItemID.CASTLEWARS_SHIELD_3_TROUVER                       // Decorative shield (l)
		);

		group("Decorative boots", "CASTLEWARS_BOOTS",
			ItemID.CASTLEWARS_BOOTS,                                 // Decorative boots
			ItemID.CASTLEWARS_BOOTS_2,                               // Decorative boots
			ItemID.CASTLEWARS_BOOTS_3,                               // Decorative boots
			ItemID.CASTLEWARS_BOOTS_3_TROUVER                        // Decorative boots (l)
		);

		group("Decorative full helm", "CASTLEWARS_FULL_HELM",
			ItemID.CASTLEWARS_FULL_HELM,                             // Decorative full helm
			ItemID.CASTLEWARS_FULL_HELM_2,                           // Decorative full helm
			ItemID.CASTLEWARS_FULL_HELM_3,                           // Decorative full helm
			ItemID.CASTLEWARS_FULL_HELM_3_TROUVER                    // Decorative full helm (l)
		);

		// ==================== SHAYZIEN ARMOUR ====================

		group("Shayzien helm (1-5)", "SHAYZIEN_HELM",
			ItemID.SHAYZIEN_HELM_1,                                  // Shayzien helm (1)
			ItemID.SHAYZIEN_HELM_2,                                  // Shayzien helm (2)
			ItemID.SHAYZIEN_HELM_3,                                  // Shayzien helm (3)
			ItemID.SHAYZIEN_HELM_4,                                  // Shayzien helm (4)
			ItemID.SHAYZIEN_HELM_5                                   // Shayzien helm (5)
		);

		group("Shayzien body (1-5)", "SHAYZIEN_BODY",
			ItemID.SHAYZIEN_BODY_1,                                  // Shayzien platebody (1)
			ItemID.SHAYZIEN_BODY_2,                                  // Shayzien platebody (2)
			ItemID.SHAYZIEN_BODY_3,                                  // Shayzien platebody (3)
			ItemID.SHAYZIEN_BODY_4,                                  // Shayzien platebody (4)
			ItemID.SHAYZIEN_BODY_5                                   // Shayzien body (5)
		);

		group("Shayzien legs (1-5)", "SHAYZIEN_LEGS",
			ItemID.SHAYZIEN_LEGS_1,                                  // Shayzien greaves (1)
			ItemID.SHAYZIEN_LEGS_2,                                  // Shayzien greaves (2)
			ItemID.SHAYZIEN_LEGS_3,                                  // Shayzien greaves (3)
			ItemID.SHAYZIEN_LEGS_4,                                  // Shayzien greaves (4)
			ItemID.SHAYZIEN_LEGS_5                                   // Shayzien greaves (5)
		);

		group("Shayzien gloves (1-5)", "SHAYZIEN_GLOVES",
			ItemID.SHAYZIEN_GLOVES_1,                                // Shayzien gloves (1)
			ItemID.SHAYZIEN_GLOVES_2,                                // Shayzien gloves (2)
			ItemID.SHAYZIEN_GLOVES_3,                                // Shayzien gloves (3)
			ItemID.SHAYZIEN_GLOVES_4,                                // Shayzien gloves (4)
			ItemID.SHAYZIEN_GLOVES_5                                 // Shayzien gloves (5)
		);

		group("Shayzien boots (1-5)", "SHAYZIEN_BOOTS",
			ItemID.SHAYZIEN_BOOTS_1,                                 // Shayzien boots (1)
			ItemID.SHAYZIEN_BOOTS_2,                                 // Shayzien boots (2)
			ItemID.SHAYZIEN_BOOTS_3,                                 // Shayzien boots (3)
			ItemID.SHAYZIEN_BOOTS_4,                                 // Shayzien boots (4)
			ItemID.SHAYZIEN_BOOTS_5                                  // Shayzien boots (5)
		);

		// ==================== POTIONS ====================

		group("Blighted super restore", "2RESTORE",
			ItemID.BLIGHTED_4DOSE2RESTORE,                           // Blighted super restore(4)
			ItemID.BLIGHTED_3DOSE2RESTORE,                           // Blighted super restore(3)
			ItemID.BLIGHTED_2DOSE2RESTORE,                           // Blighted super restore(2)
			ItemID.BLIGHTED_1DOSE2RESTORE                            // Blighted super restore(1)
		);

		// ==================== HOLIDAY ITEMS ====================

		group("H'ween masks", "HALLOWEENMASK",
			ItemID.HALLOWEENMASK_GREEN,                              // Green halloween mask
			ItemID.HALLOWEENMASK_BLUE,                               // Blue halloween mask
			ItemID.HALLOWEENMASK_RED,                                // Red halloween mask
			ItemID.HALLOWEENMASK_BLACK                               // Black h'ween mask
		);

		// ==================== SKILL CAPES ====================

		group("Ranging cape", "SKILLCAPE_RANGING",
			ItemID.SKILLCAPE_RANGING,                                // Ranging cape
			ItemID.SKILLCAPE_RANGING_TRIMMED                         // Ranging cape(t)
		);

		group("Construction cape", "SKILLCAPE_CONSTRUCTION",
			ItemID.SKILLCAPE_CONSTRUCTION,                           // Construct. cape
			ItemID.SKILLCAPE_CONSTRUCTION_TRIMMED                    // Construct. cape(t)
		);

		group("Woodcutting cape", "SKILLCAPE_WOODCUTTING",
			ItemID.SKILLCAPE_WOODCUTTING,                            // Woodcutting cape
			ItemID.SKILLCAPE_WOODCUTTING_TRIMMED                     // Woodcut. cape(t)
		);

		// ==================== CAPES ====================

		group("Fire cape, Infernal cape and their max capes", "CAPE_FIRE|FIRECAPE|INFERNAL",
			ItemID.TZHAAR_CAPE_FIRE,                                 // Fire cape
			ItemID.TZHAAR_CAPE_FIRE_TROUVER,                         // Fire cape (l)
			ItemID.SKILLCAPE_MAX_FIRECAPE,                           // Fire max cape
			ItemID.SKILLCAPE_MAX_INFERNALCAPE,                       // Infernal max cape
			ItemID.INFERNAL_CAPE,                                    // Infernal cape
			ItemID.SKILLCAPE_MAX_INFERNALCAPE_TROUVER,               // Infernal max cape (l)
			ItemID.SKILLCAPE_MAX_FIRECAPE_TROUVER,                   // Fire max cape (l)
			ItemID.INFERNAL_CAPE_TROUVER                             // Infernal cape (l)
		);

		group("Max cape and god max capes", "SKILLCAPE_MAX",
			ItemID.SKILLCAPE_MAX,                                    // Max cape
			ItemID.SKILLCAPE_MAX_SARADOMIN,                          // Saradomin max cape
			ItemID.SKILLCAPE_MAX_ZAMORAK,                            // Zamorak max cape
			ItemID.SKILLCAPE_MAX_GUTHIX,                             // Guthix max cape
			ItemID.SKILLCAPE_MAX_SARADOMIN2,                         // Imbued Saradomin max cape
			ItemID.SKILLCAPE_MAX_ZAMORAK2,                           // Imbued Zamorak max cape
			ItemID.SKILLCAPE_MAX_GUTHIX2,                            // Imbued Guthix max cape
			ItemID.SKILLCAPE_MAX_SARADOMIN2_TROUVER,                 // Imbued Saradomin max cape (l)
			ItemID.SKILLCAPE_MAX_ZAMORAK2_TROUVER,                   // Imbued Zamorak max cape (l)
			ItemID.SKILLCAPE_MAX_GUTHIX2_TROUVER                     // Imbued Guthix max cape (l)
		);

		group("Ava's accumulator, assembler and Masori assembler", "AVAS_|ANMA_50",
			ItemID.ANMA_50_REWARD,                                   // Ava's accumulator
			ItemID.AVAS_ASSEMBLER,                                   // Ava's assembler
			ItemID.AVAS_ASSEMBLER_TROUVER,                           // Ava's assembler (l)
			ItemID.AVAS_ASSEMBLER_MASORI,                            // Masori assembler
			ItemID.AVAS_ASSEMBLER_MASORI_TROUVER                     // Masori assembler (l)
		);

		group("Accumulator and assembler max capes", "SKILLCAPE_MAX_A",
			ItemID.SKILLCAPE_MAX_ANMA,                               // Accumulator max cape
			ItemID.SKILLCAPE_MAX_ASSEMBLER,                          // Assembler max cape
			ItemID.SKILLCAPE_MAX_ASSEMBLER_TROUVER,                  // Assembler max cape (l)
			ItemID.SKILLCAPE_MAX_ASSEMBLER_MASORI,                   // Masori assembler max cape
			ItemID.SKILLCAPE_MAX_ASSEMBLER_MASORI_TROUVER            // Masori assembler max cape (l)
		);

		group("Dizana's quiver and Blessed Dizana's quiver", "DIZANAS_QUIVER",
			ItemID.DIZANAS_QUIVER_UNCHARGED,                         // Dizana's quiver (uncharged)
			ItemID.DIZANAS_QUIVER_UNCHARGED_TROUVER,                 // Dizana's quiver (l) (uncharged)
			ItemID.DIZANAS_QUIVER_CHARGED,                           // Dizana's quiver
			ItemID.DIZANAS_QUIVER_CHARGED_TROUVER,                   // Dizana's quiver (l)
			ItemID.DIZANAS_QUIVER_INFINITE,                          // Blessed Dizana's quiver
			ItemID.DIZANAS_QUIVER_INFINITE_TROUVER                   // Blessed Dizana's quiver (l)
		);

		// ==================== MISC ====================

		group("Rune pouch and Divine rune pouch", "RUNE_POUCH",
			ItemID.BH_RUNE_POUCH,                                    // Rune pouch
			ItemID.BH_RUNE_POUCH_TROUVER,                            // Rune pouch (l)
			ItemID.DIVINE_RUNE_POUCH,                                // Divine rune pouch
			ItemID.DIVINE_RUNE_POUCH_TROUVER                         // Divine rune pouch (l)
		);

		// ==================== CRYSTAL EQUIPMENT ====================

		group("Crystal helm", "CRYSTAL_HELMET",
			ItemID.CRYSTAL_HELMET,                                   // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE,                          // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_HEFIN,                             // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_HEFIN,                    // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_ITHELL,                            // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_ITHELL,                   // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_IORWERTH,                          // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_IORWERTH,                 // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_TRAHAEARN,                         // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_TRAHAEARN,                // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_CADARN,                            // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_CADARN,                   // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_CRWYS,                             // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_CRWYS,                    // Crystal helm (inactive)
			ItemID.CRYSTAL_HELMET_AMLODD,                            // Crystal helm
			ItemID.CRYSTAL_HELMET_INACTIVE_AMLODD                    // Crystal helm (inactive)
		);

		group("Crystal body", "CRYSTAL_CHESTPLATE",
			ItemID.CRYSTAL_CHESTPLATE,                               // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE,                      // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_HEFIN,                         // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_HEFIN,                // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_ITHELL,                        // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_ITHELL,               // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_IORWERTH,                      // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_IORWERTH,             // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_TRAHAEARN,                     // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_TRAHAEARN,            // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_CADARN,                        // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_CADARN,               // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_CRWYS,                         // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_CRWYS,                // Crystal body (inactive)
			ItemID.CRYSTAL_CHESTPLATE_AMLODD,                        // Crystal body
			ItemID.CRYSTAL_CHESTPLATE_INACTIVE_AMLODD                // Crystal body (inactive)
		);

		group("Crystal legs", "CRYSTAL_PLATELEGS",
			ItemID.CRYSTAL_PLATELEGS,                                // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE,                       // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_HEFIN,                          // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_HEFIN,                 // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_ITHELL,                         // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_ITHELL,                // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_IORWERTH,                       // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_IORWERTH,              // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_TRAHAEARN,                      // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_TRAHAEARN,             // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_CADARN,                         // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_CADARN,                // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_CRWYS,                          // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_CRWYS,                 // Crystal legs (inactive)
			ItemID.CRYSTAL_PLATELEGS_AMLODD,                         // Crystal legs
			ItemID.CRYSTAL_PLATELEGS_INACTIVE_AMLODD                 // Crystal legs (inactive)
		);

		// ==================== OATHPLATE ====================

		group("Oathplate helm and Radiant oathplate helm", "OATHPLATE_HELM",
			ItemID.OATHPLATE_HELM,                                   // Oathplate helm
			ItemID.RADIANT_OATHPLATE_HELM                            // Radiant oathplate helm
		);

		group("Oathplate chest and Radiant oathplate chest", "OATHPLATE_CHEST",
			ItemID.OATHPLATE_CHEST,                                  // Oathplate chest
			ItemID.RADIANT_OATHPLATE_CHEST                           // Radiant oathplate chest
		);

		group("Oathplate legs and Radiant oathplate legs", "OATHPLATE_LEGS",
			ItemID.OATHPLATE_LEGS,                                   // Oathplate legs
			ItemID.RADIANT_OATHPLATE_LEGS                            // Radiant oathplate legs
		);

		// ==================== ORNAMENT KITS ====================

		group("Soulreaper axe", "SOULREAPER",
			ItemID.SOULREAPER,                                       // Soulreaper axe
			ItemID.SOULREAPER_AXE_ORN                                // Soulreaper axe (o)
		);

		group("Iban's staff", "IBAN",
			ItemID.IBANSTAFF,                                        // Iban's staff
			ItemID.IBANSTAFF_UPGRADED,                               // Iban's staff (u)
			ItemID.IBAN_STAFF_ORN,                                   // Iban's staff (o)
			ItemID.IBAN_STAFF_UPGRADED_ORN                           // Iban's staff (u) (o)
		);

		// ==================== BLOWPIPES ====================

		group("Blowpipes", "BLOWPIPE",
			ItemID.TOXIC_BLOWPIPE,                                   // Toxic blowpipe (empty)
			ItemID.TOXIC_BLOWPIPE_LOADED,                            // Toxic blowpipe
			ItemID.TOXIC_BLOWPIPE_LOADED_ORNAMENT,                   // Blazing blowpipe
			ItemID.DRYGORE_BLOWPIPE_LOADED,                          // Drygore blowpipe
			ItemID.CAMPHOR_BLOWPIPE,                                 // Camphor blowpipe
			ItemID.IRONWOOD_BLOWPIPE,                                // Ironwood blowpipe
			ItemID.ROSEWOOD_BLOWPIPE                                 // Rosewood blowpipe
		);

		// ==================== BOSS DROPS ====================

		group("Avernic defender", "INFERNAL_DEFENDER",
			ItemID.INFERNAL_DEFENDER,                                // Avernic defender
			ItemID.INFERNAL_DEFENDER_TROUVER                         // Avernic defender (l)
		);
	}

	/**
	 * Add a group of item IDs that should be treated as versions of one item.
	 *
	 * @param label    readable name of the group
	 * @param keywords "|"-separated fragments; every constant name in the group must contain one (checked by tests)
	 * @param itemIds  the item IDs
	 */
	private void group(String label, String keywords, int... itemIds)
	{
		Set<Integer> group = new LinkedHashSet<>();
		for (int id : itemIds)
		{
			group.add(id);
		}
		Set<Integer> readOnly = Collections.unmodifiableSet(group);

		for (int id : itemIds)
		{
			if (itemToGroup.containsKey(id))
			{
				throw new IllegalStateException("Item " + id + " is in more than one variant group (" + label + ")");
			}
			itemToGroup.put(id, readOnly);
			itemToLabel.put(id, label);
		}

		variantGroups.add(readOnly);
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
	 * Get the readable label of the group an item belongs to, or null.
	 */
	public String getGroupLabel(int itemId)
	{
		return itemToLabel.get(itemId);
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
		return Collections.unmodifiableList(variantGroups);
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
