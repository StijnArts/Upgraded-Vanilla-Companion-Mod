package drai.dev.upgradedvanilla.modules.minecraft.brick;

import java.util.*;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import drai.dev.upgradedvanilla.modules.minecraft.brick.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public class LimeBrick {
	private static final String MATERIAL_NAME = "lime_brick";
	public static TagKey<Item> LIME_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> LIME_BRICK_BLOCKS_ITEM_TAG;
	public static Item LIME_BRICK_ITEM;
	public static Block LIME_BRICKS;
	public static Block LIME_BRICK_SLAB;
	public static Block LIME_BRICK_STAIRS;
	public static Block LIME_BRICK_WALL;
	public static Block LIME_BRICK_TRAPDOOR;
	public static Block LIME_BRICK_FENCE;
	public static Block LIME_BRICK_FENCE_GATE;

	private static void registerTags() {
		LIME_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "lime_brick_slabs"));
		LIME_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "lime_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		LIME_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.LIME_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		LIME_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, LIME_BRICK_ITEM, Blocks.BRICKS, Items.LIME_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(LIME_BRICK_BLOCKS_ITEM_TAG));
		LIME_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, LIME_BRICKS, LIME_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		LIME_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, LIME_BRICKS, LIME_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(LIME_BRICK_SLABS_ITEM_TAG));
		LIME_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, LIME_BRICKS, LIME_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		LIME_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, LIME_BRICKS, LIME_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		LIME_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, LIME_BRICKS, LIME_BRICK_SLABS_ITEM_TAG, LIME_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		LIME_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, LIME_BRICKS, LIME_BRICK_SLABS_ITEM_TAG, LIME_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
