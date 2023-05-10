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

public class GrayBrick {
	private static final String MATERIAL_NAME = "gray_brick";
	public static TagKey<Item> GRAY_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> GRAY_BRICK_BLOCKS_ITEM_TAG;
	public static Item GRAY_BRICK_ITEM;
	public static Block GRAY_BRICKS;
	public static Block GRAY_BRICK_SLAB;
	public static Block GRAY_BRICK_STAIRS;
	public static Block GRAY_BRICK_WALL;
	public static Block GRAY_BRICK_TRAPDOOR;
	public static Block GRAY_BRICK_FENCE;
	public static Block GRAY_BRICK_FENCE_GATE;

	private static void registerTags() {
		GRAY_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "gray_brick_slabs"));
		GRAY_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "gray_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		GRAY_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.GRAY_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		GRAY_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, GRAY_BRICK_ITEM, Blocks.BRICKS, Items.GRAY_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(GRAY_BRICK_BLOCKS_ITEM_TAG));
		GRAY_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, GRAY_BRICKS, GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		GRAY_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, GRAY_BRICKS, GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(GRAY_BRICK_SLABS_ITEM_TAG));
		GRAY_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, GRAY_BRICKS, GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		GRAY_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, GRAY_BRICKS, GRAY_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		GRAY_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, GRAY_BRICKS, GRAY_BRICK_SLABS_ITEM_TAG, GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		GRAY_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, GRAY_BRICKS, GRAY_BRICK_SLABS_ITEM_TAG, GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
