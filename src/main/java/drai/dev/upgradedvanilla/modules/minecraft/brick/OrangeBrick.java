package drai.dev.upgradedvanilla.modules.minecraft.brick;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import drai.dev.upgradedvanilla.modules.minecraft.quartz.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class OrangeBrick {
	private static final String MATERIAL_NAME = "orange_brick";
	public static TagKey<Item> ORANGE_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> ORANGE_BRICK_BLOCKS_ITEM_TAG;
	public static Item ORANGE_BRICK_ITEM;
	public static Block ORANGE_BRICKS;
	public static Block ORANGE_BRICK_SLAB;
	public static Block ORANGE_BRICK_STAIRS;
	public static Block ORANGE_BRICK_WALL;
	public static Block ORANGE_BRICK_TRAPDOOR;
	public static Block ORANGE_BRICK_FENCE;
	public static Block ORANGE_BRICK_FENCE_GATE;

	private static void registerTags() {
		ORANGE_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "orange_brick_slabs"));
		ORANGE_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "orange_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		ORANGE_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.ORANGE_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		ORANGE_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, ORANGE_BRICK_ITEM, Blocks.BRICKS, Items.ORANGE_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(ORANGE_BRICK_BLOCKS_ITEM_TAG));
		ORANGE_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, ORANGE_BRICKS, ORANGE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		ORANGE_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, ORANGE_BRICKS, ORANGE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(ORANGE_BRICK_SLABS_ITEM_TAG));
		ORANGE_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, ORANGE_BRICKS, ORANGE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		ORANGE_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, ORANGE_BRICKS, ORANGE_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		ORANGE_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, ORANGE_BRICKS, ORANGE_BRICK_SLABS_ITEM_TAG, ORANGE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		ORANGE_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, ORANGE_BRICKS, ORANGE_BRICK_SLABS_ITEM_TAG, ORANGE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
