package drai.dev.upgradedvanilla.modules.betterend.amaranita;

import drai.dev.upgradedvanilla.modules.minecraft.stone.stone.*;
import drai.dev.upgradedvanilla.modules.minecraft.wood.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;

import org.betterx.betterend.registry.*;

import java.util.*;

import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.AMARANITA_PLANKS;
import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.AMARANITA_PLANKS_ITEM_TAG;
import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.AMARANITA_SLAB_ITEM_TAG;
import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.AMARANITA_STEMS_ITEM_TAG;
import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.AMARANITA_STICK;
import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.MATERIAL_NAME;
import static drai.dev.upgradedvanilla.modules.betterend.amaranita.AmaranitaBlocks.amaranitaPalette;

public class AmaranitaCombinationBlocks {
	public static Block AMARANITA_LEVER;
	public static Block AMARANITA_GRINDSTONE;
	public static Block AMARANITA_SMOKER;
	public static Block AMARANITA_STONECUTTER;
	public static Block AMARANITA_TRIPWIRE_HOOK;
	public static Block AMARANITA_SMITHING_TABLE;
	public static Block AMARANITA_BED;

	public static void registerTags(){

	}
	public static void registerCombinationBlocks() {
		registerTags();
		//Minecraft is Initialized
		AMARANITA_BED = MinecraftWoodBlocks.bedBlockSet(MATERIAL_NAME, AmaranitaBlocks.AMARANITA_PLANKS_ITEM_TAG,
				List.of(BlockTags.BEDS),List.of(ItemTags.BEDS), amaranitaPalette);
		AMARANITA_LEVER = MinecraftWoodBlocks.leverBlockSet(MATERIAL_NAME, AMARANITA_STICK,
				List.of(UpgradedVanillaTags.LEVERS_BLOCK_TAG), List.of(UpgradedVanillaTags.LEVERS_ITEM_TAG), amaranitaPalette);
		AMARANITA_GRINDSTONE = MinecraftWoodBlocks.grindstoneSet(MATERIAL_NAME, AMARANITA_STICK, EndBlocks.AMARANITA_HYPHAE,AMARANITA_PLANKS_ITEM_TAG,
				List.of(PoiTags.WEAPONSMITH_WORKSTATION, BlockTags.MINEABLE_WITH_PICKAXE),List.of(), amaranitaPalette);
		AMARANITA_SMOKER = MinecraftWoodBlocks.smokerSet(MATERIAL_NAME, AMARANITA_STEMS_ITEM_TAG,
				List.of(PoiTags.BUTCHER_WORKSTATION, BlockTags.MINEABLE_WITH_PICKAXE),List.of(), amaranitaPalette);
		AMARANITA_TRIPWIRE_HOOK = MinecraftWoodBlocks.tripwireHookSet(MATERIAL_NAME, AMARANITA_STICK,
				AMARANITA_PLANKS_ITEM_TAG, List.of(),List.of(UpgradedVanillaTags.TRIPWIRE_HOOK_ITEMS), amaranitaPalette);
		AMARANITA_STONECUTTER = MinecraftWoodBlocks.stonecutterSet(MATERIAL_NAME, AMARANITA_STEMS_ITEM_TAG,
				List.of(PoiTags.MASON_WORKSTATION, BlockTags.MINEABLE_WITH_PICKAXE),List.of(), amaranitaPalette);
		AMARANITA_SMITHING_TABLE = MinecraftWoodBlocks.smithingTableSet(MATERIAL_NAME, AMARANITA_PLANKS_ITEM_TAG,
				List.of(PoiTags.ARMORER_WORKSTATION, BlockTags.MINEABLE_WITH_AXE),List.of(), amaranitaPalette);
	}
}
