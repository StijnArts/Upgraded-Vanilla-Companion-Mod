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

public class PurpleBrick {
	private static final String MATERIAL_NAME = "purple_brick";
	public static TagKey<Item> PURPLE_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> PURPLE_BRICK_BLOCKS_ITEM_TAG;
	public static Item PURPLE_BRICK_ITEM;
	public static Block PURPLE_BRICKS;
	public static Block PURPLE_BRICK_SLAB;
	public static Block PURPLE_BRICK_STAIRS;
	public static Block PURPLE_BRICK_WALL;
	public static Block PURPLE_BRICK_TRAPDOOR;
	public static Block PURPLE_BRICK_FENCE;
	public static Block PURPLE_BRICK_FENCE_GATE;

	private static void registerTags() {
		PURPLE_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "purple_brick_slabs"));
		PURPLE_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "purple_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		PURPLE_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.PURPLE_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		PURPLE_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, PURPLE_BRICK_ITEM, Blocks.BRICKS, Items.PURPLE_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PURPLE_BRICK_BLOCKS_ITEM_TAG));
		PURPLE_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, PURPLE_BRICKS, PURPLE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PURPLE_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, PURPLE_BRICKS, PURPLE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(PURPLE_BRICK_SLABS_ITEM_TAG));
		PURPLE_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, PURPLE_BRICKS, PURPLE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		PURPLE_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, PURPLE_BRICKS, PURPLE_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		PURPLE_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, PURPLE_BRICKS, PURPLE_BRICK_SLABS_ITEM_TAG, PURPLE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		PURPLE_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, PURPLE_BRICKS, PURPLE_BRICK_SLABS_ITEM_TAG, PURPLE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
