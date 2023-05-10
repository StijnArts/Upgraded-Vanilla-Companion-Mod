package drai.dev.upgradedvanilla.modules.minecraft.brick;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class BrownBrick {
	private static final String MATERIAL_NAME = "brown_brick";
	public static TagKey<Item> BROWN_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> BROWN_BRICK_BLOCKS_ITEM_TAG;
	public static Item BROWN_BRICK_ITEM;
	public static Block BROWN_BRICKS;
	public static Block BROWN_BRICK_SLAB;
	public static Block BROWN_BRICK_STAIRS;
	public static Block BROWN_BRICK_WALL;
	public static Block BROWN_BRICK_TRAPDOOR;
	public static Block BROWN_BRICK_FENCE;
	public static Block BROWN_BRICK_FENCE_GATE;

	private static void registerTags() {
		BROWN_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "brown_brick_slabs"));
		BROWN_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "brown_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		BROWN_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.BROWN_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		BROWN_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, BROWN_BRICK_ITEM, Blocks.BRICKS, Items.BROWN_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(BROWN_BRICK_BLOCKS_ITEM_TAG));
		BROWN_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, BROWN_BRICKS, BROWN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		BROWN_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, BROWN_BRICKS, BROWN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(BROWN_BRICK_SLABS_ITEM_TAG));
		BROWN_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, BROWN_BRICKS, BROWN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		BROWN_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, BROWN_BRICKS, BROWN_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		BROWN_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, BROWN_BRICKS, BROWN_BRICK_SLABS_ITEM_TAG, BROWN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		BROWN_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, BROWN_BRICKS, BROWN_BRICK_SLABS_ITEM_TAG, BROWN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
