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

public class LightGrayBrick {
	private static final String MATERIAL_NAME = "light_gray_brick";
	public static TagKey<Item> LIGHT_GRAY_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG;
	public static Item LIGHT_GRAY_BRICK_ITEM;
	public static Block LIGHT_GRAY_BRICKS;
	public static Block LIGHT_GRAY_BRICK_SLAB;
	public static Block LIGHT_GRAY_BRICK_STAIRS;
	public static Block LIGHT_GRAY_BRICK_WALL;
	public static Block LIGHT_GRAY_BRICK_TRAPDOOR;
	public static Block LIGHT_GRAY_BRICK_FENCE;
	public static Block LIGHT_GRAY_BRICK_FENCE_GATE;

	private static void registerTags() {
		LIGHT_GRAY_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "light_gray_brick_slabs"));
		LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "light_gray_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		LIGHT_GRAY_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.LIGHT_GRAY_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		LIGHT_GRAY_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, LIGHT_GRAY_BRICK_ITEM, Blocks.BRICKS, Items.LIGHT_GRAY_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG));
		LIGHT_GRAY_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, LIGHT_GRAY_BRICKS, LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		LIGHT_GRAY_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, LIGHT_GRAY_BRICKS, LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(LIGHT_GRAY_BRICK_SLABS_ITEM_TAG));
		LIGHT_GRAY_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, LIGHT_GRAY_BRICKS, LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		LIGHT_GRAY_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, LIGHT_GRAY_BRICKS, LIGHT_GRAY_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		LIGHT_GRAY_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, LIGHT_GRAY_BRICKS, LIGHT_GRAY_BRICK_SLABS_ITEM_TAG, LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		LIGHT_GRAY_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, LIGHT_GRAY_BRICKS, LIGHT_GRAY_BRICK_SLABS_ITEM_TAG, LIGHT_GRAY_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
