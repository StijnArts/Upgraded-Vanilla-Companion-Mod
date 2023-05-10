package drai.dev.upgradedvanilla.modules.minecraft.quartz;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class PackedIce {
	private static final String MATERIAL_NAME = "packed_ice";
	public static TagKey<Item> PACKED_ICE_SLABS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> SMOOTH_PACKED_ICE_SLABS_ITEM_TAG;
	public static TagKey<Item> SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_BRICK_BLOCKS_ITEM_TAG;
	public static TagKey<Item> CRACKED_PACKED_ICE_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_TILE_SLABS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_TILE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> CRACKED_PACKED_ICE_TILE_SLABS_ITEM_TAG;
	public static TagKey<Item> CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> CHISELED_PACKED_ICE_SLABS_ITEM_TAG;
	public static TagKey<Item> CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_PILLAR_SLABS_ITEM_TAG;
	public static TagKey<Item> PACKED_ICE_PILLAR_BLOCKS_ITEM_TAG;
	public static Block PACKED_ICE_STAIRS;
	public static Block PACKED_ICE_SLAB;
	public static Block PACKED_ICE_WALL;
	public static Block PACKED_ICE_TRAPDOOR;
	public static Block PACKED_ICE_FENCE;
	public static Block PACKED_ICE_FENCE_GATE;
	public static Block SMOOTH_PACKED_ICE;
	public static Block SMOOTH_PACKED_ICE_STAIRS;
	public static Block SMOOTH_PACKED_ICE_SLAB;
	public static Block SMOOTH_PACKED_ICE_WALL;
	public static Block SMOOTH_PACKED_ICE_TRAPDOOR;
	public static Block SMOOTH_PACKED_ICE_FENCE;
	public static Block SMOOTH_PACKED_ICE_FENCE_GATE;
	public static Block PACKED_ICE_BRICK;
	public static Block PACKED_ICE_BRICK_STAIRS;
	public static Block PACKED_ICE_BRICK_SLAB;
	public static Block PACKED_ICE_BRICK_WALL;
	public static Block PACKED_ICE_BRICK_TRAPDOOR;
	public static Block PACKED_ICE_BRICK_FENCE;
	public static Block PACKED_ICE_BRICK_FENCE_GATE;
	public static Block CRACKED_PACKED_ICE_BRICK;
	public static Block CRACKED_PACKED_ICE_BRICK_STAIRS;
	public static Block CRACKED_PACKED_ICE_BRICK_SLAB;
	public static Block CRACKED_PACKED_ICE_BRICK_WALL;
	public static Block CRACKED_PACKED_ICE_BRICK_TRAPDOOR;
	public static Block CRACKED_PACKED_ICE_BRICK_FENCE;
	public static Block CRACKED_PACKED_ICE_BRICK_FENCE_GATE;
	public static Block PACKED_ICE_TILE;
	public static Block PACKED_ICE_TILE_STAIRS;
	public static Block PACKED_ICE_TILE_SLAB;
	public static Block PACKED_ICE_TILE_WALL;
	public static Block PACKED_ICE_TILE_TRAPDOOR;
	public static Block PACKED_ICE_TILE_FENCE;
	public static Block PACKED_ICE_TILE_FENCE_GATE;
	public static Block CRACKED_PACKED_ICE_TILE;
	public static Block CRACKED_PACKED_ICE_TILE_STAIRS;
	public static Block CRACKED_PACKED_ICE_TILE_SLAB;
	public static Block CRACKED_PACKED_ICE_TILE_WALL;
	public static Block CRACKED_PACKED_ICE_TILE_TRAPDOOR;
	public static Block CRACKED_PACKED_ICE_TILE_FENCE;
	public static Block CRACKED_PACKED_ICE_TILE_FENCE_GATE;
	public static Block CHISELED_PACKED_ICE;
	public static Block CHISELED_PACKED_ICE_STAIRS;
	public static Block CHISELED_PACKED_ICE_SLAB;
	public static Block CHISELED_PACKED_ICE_WALL;
	public static Block CHISELED_PACKED_ICE_TRAPDOOR;
	public static Block PACKED_ICE_PILLAR;
	public static Block PACKED_ICE_PILLAR_STAIRS;
	public static Block PACKED_ICE_PILLAR_SLAB;
	public static Block PACKED_ICE_PILLAR_WALL;

	private static void registerTags(){
		PACKED_ICE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_slabs"));
		PACKED_ICE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_blocks"), List.of(new ResourceLocation("minecraft", "packed_ice")));
		SMOOTH_PACKED_ICE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"smooth_packed_ice_slabs"));
		SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"smooth_packed_ice_blocks"));
		PACKED_ICE_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_brick_slabs"));
		PACKED_ICE_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_brick_blocks"));
		PACKED_ICE_TILE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_tile_slabs"));
		PACKED_ICE_TILE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_tile_blocks"));
		CRACKED_PACKED_ICE_TILE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"cracked_packed_ice_tile_slabs"));
		CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"cracked_packed_ice_tile_blocks"));
		CHISELED_PACKED_ICE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"chiseled_packed_ice_slabs"));
		CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"chiseled_packed_ice_blocks"));
		CRACKED_PACKED_ICE_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"cracked_packed_ice_brick_slabs"));
		CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"cracked_packed_ice_brick_blocks"));
		PACKED_ICE_PILLAR_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_pillar_slabs"));
		PACKED_ICE_PILLAR_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"packed_ice_pillar_blocks"));

	}
	public  static void register(){
		registerTags();
		PACKED_ICE_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PACKED_ICE_SLABS_ITEM_TAG));
		PACKED_ICE_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_SLABS_ITEM_TAG, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_SLABS_ITEM_TAG, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		SMOOTH_PACKED_ICE = MinecraftQuartzBlocks.smoothQuartzBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG));
		SMOOTH_PACKED_ICE_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES));
		SMOOTH_PACKED_ICE_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		SMOOTH_PACKED_ICE_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		SMOOTH_PACKED_ICE_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		SMOOTH_PACKED_ICE_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_SLABS_ITEM_TAG, SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		SMOOTH_PACKED_ICE_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_SLABS_ITEM_TAG, SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		PACKED_ICE_BRICK = MinecraftQuartzBlocks.quartzBricksBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PACKED_ICE_BRICK_BLOCKS_ITEM_TAG));
		PACKED_ICE_BRICK_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, PACKED_ICE_BRICK, PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES, PACKED_ICE_BRICK_SLABS_ITEM_TAG));
		PACKED_ICE_BRICK_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, PACKED_ICE_BRICK, PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_BRICK_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, PACKED_ICE_BRICK, PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_BRICK_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, PACKED_ICE_BRICK, PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_BRICK_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, PACKED_ICE_BRICK, PACKED_ICE_BRICK_SLABS_ITEM_TAG, PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_BRICK_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, PACKED_ICE_BRICK, PACKED_ICE_BRICK_SLABS_ITEM_TAG, PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		CRACKED_PACKED_ICE_BRICK = MinecraftQuartzBlocks.crackedQuartzBricksBlock(MATERIAL_NAME, Blocks.PACKED_ICE,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG));
		CRACKED_PACKED_ICE_BRICK_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_BRICK, CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES, CRACKED_PACKED_ICE_BRICK_SLABS_ITEM_TAG));
		CRACKED_PACKED_ICE_BRICK_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_BRICK, CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_BRICK_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_BRICK, CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_BRICK_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_BRICK, CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_BRICK_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_BRICK, CRACKED_PACKED_ICE_BRICK_SLABS_ITEM_TAG, CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_BRICK_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_BRICK, CRACKED_PACKED_ICE_BRICK_SLABS_ITEM_TAG, CRACKED_PACKED_ICE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());


		PACKED_ICE_TILE = MinecraftQuartzBlocks.quartzTilesBlock(MATERIAL_NAME, PACKED_ICE_BRICK, SMOOTH_PACKED_ICE, SMOOTH_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PACKED_ICE_BRICK_BLOCKS_ITEM_TAG));
		PACKED_ICE_TILE_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, PACKED_ICE_TILE, PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES, PACKED_ICE_TILE_SLABS_ITEM_TAG));
		PACKED_ICE_TILE_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, PACKED_ICE_TILE, PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_TILE_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, PACKED_ICE_TILE, PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_TILE_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, PACKED_ICE_TILE, PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_TILE_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, PACKED_ICE_TILE, PACKED_ICE_TILE_SLABS_ITEM_TAG, PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_TILE_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, PACKED_ICE_TILE, PACKED_ICE_TILE_SLABS_ITEM_TAG, PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		CRACKED_PACKED_ICE_TILE = MinecraftQuartzBlocks.crackedQuartzTilesBlock(MATERIAL_NAME, PACKED_ICE_TILE,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PACKED_ICE_BRICK_BLOCKS_ITEM_TAG));
		CRACKED_PACKED_ICE_TILE_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_TILE, CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES, CRACKED_PACKED_ICE_TILE_SLABS_ITEM_TAG));
		CRACKED_PACKED_ICE_TILE_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_TILE, CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_TILE_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_TILE, CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_TILE_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_TILE, CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_TILE_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_TILE, CRACKED_PACKED_ICE_TILE_SLABS_ITEM_TAG, CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CRACKED_PACKED_ICE_TILE_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, CRACKED_PACKED_ICE_TILE, CRACKED_PACKED_ICE_TILE_SLABS_ITEM_TAG, CRACKED_PACKED_ICE_TILE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		CHISELED_PACKED_ICE = MinecraftQuartzBlocks.chiseledQuartzBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG));
		CHISELED_PACKED_ICE_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, CHISELED_PACKED_ICE, CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES, CHISELED_PACKED_ICE_SLABS_ITEM_TAG));
		CHISELED_PACKED_ICE_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, CHISELED_PACKED_ICE, CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CHISELED_PACKED_ICE_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, CHISELED_PACKED_ICE, CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CHISELED_PACKED_ICE_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, CHISELED_PACKED_ICE, CHISELED_PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		PACKED_ICE_PILLAR = MinecraftQuartzBlocks.quartzPillarBlock(MATERIAL_NAME, Blocks.PACKED_ICE, PACKED_ICE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PACKED_ICE_PILLAR_BLOCKS_ITEM_TAG));
		PACKED_ICE_PILLAR_SLAB = MinecraftQuartzBlocks.pillarSlabBlock(MATERIAL_NAME, PACKED_ICE_PILLAR, PACKED_ICE_PILLAR_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COBBLESTONES, PACKED_ICE_PILLAR_SLABS_ITEM_TAG));
		PACKED_ICE_PILLAR_STAIRS = MinecraftQuartzBlocks.pillarStairsBlock(MATERIAL_NAME, PACKED_ICE_PILLAR, PACKED_ICE_PILLAR_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PACKED_ICE_PILLAR_WALL = MinecraftQuartzBlocks.pillarWallBlock(MATERIAL_NAME, PACKED_ICE_PILLAR, PACKED_ICE_PILLAR_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
	}
}
