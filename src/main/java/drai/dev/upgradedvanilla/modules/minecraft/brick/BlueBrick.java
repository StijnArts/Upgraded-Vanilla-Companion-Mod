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

public class BlueBrick {
	private static final String MATERIAL_NAME = "blue_brick";
	public static TagKey<Item> BLUE_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> BLUE_BRICK_BLOCKS_ITEM_TAG;
	public static Item BLUE_BRICK_ITEM;
	public static Block BLUE_BRICKS;
	public static Block BLUE_BRICK_SLAB;
	public static Block BLUE_BRICK_STAIRS;
	public static Block BLUE_BRICK_WALL;
	public static Block BLUE_BRICK_TRAPDOOR;
	public static Block BLUE_BRICK_FENCE;
	public static Block BLUE_BRICK_FENCE_GATE;

	private static void registerTags() {
		BLUE_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "blue_brick_slabs"));
		BLUE_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "blue_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		BLUE_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.BLUE_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		BLUE_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, BLUE_BRICK_ITEM, Blocks.BRICKS, Items.BLUE_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(BLUE_BRICK_BLOCKS_ITEM_TAG));
		BLUE_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, BLUE_BRICKS, BLUE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		BLUE_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, BLUE_BRICKS, BLUE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(BLUE_BRICK_SLABS_ITEM_TAG));
		BLUE_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, BLUE_BRICKS, BLUE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		BLUE_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, BLUE_BRICKS, BLUE_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		BLUE_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, BLUE_BRICKS, BLUE_BRICK_SLABS_ITEM_TAG, BLUE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		BLUE_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, BLUE_BRICKS, BLUE_BRICK_SLABS_ITEM_TAG, BLUE_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
