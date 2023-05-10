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

public class PinkBrick {
	private static final String MATERIAL_NAME = "pink_brick";
	public static TagKey<Item> PINK_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> PINK_BRICK_BLOCKS_ITEM_TAG;
	public static Item PINK_BRICK_ITEM;
	public static Block PINK_BRICKS;
	public static Block PINK_BRICK_SLAB;
	public static Block PINK_BRICK_STAIRS;
	public static Block PINK_BRICK_WALL;
	public static Block PINK_BRICK_TRAPDOOR;
	public static Block PINK_BRICK_FENCE;
	public static Block PINK_BRICK_FENCE_GATE;

	private static void registerTags() {
		PINK_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "pink_brick_slabs"));
		PINK_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "pink_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		PINK_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.PINK_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		PINK_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, PINK_BRICK_ITEM, Blocks.BRICKS, Items.PINK_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PINK_BRICK_BLOCKS_ITEM_TAG));
		PINK_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, PINK_BRICKS, PINK_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PINK_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, PINK_BRICKS, PINK_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PINK_BRICK_SLABS_ITEM_TAG));
		PINK_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, PINK_BRICKS, PINK_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PINK_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, PINK_BRICKS, PINK_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		PINK_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, PINK_BRICKS, PINK_BRICK_SLABS_ITEM_TAG, PINK_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		PINK_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, PINK_BRICKS, PINK_BRICK_SLABS_ITEM_TAG, PINK_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
