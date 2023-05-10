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

public class MagentaBrick {
	private static final String MATERIAL_NAME = "magenta_brick";
	public static TagKey<Item> MAGENTA_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> MAGENTA_BRICK_BLOCKS_ITEM_TAG;
	public static Item MAGENTA_BRICK_ITEM;
	public static Block MAGENTA_BRICKS;
	public static Block MAGENTA_BRICK_SLAB;
	public static Block MAGENTA_BRICK_STAIRS;
	public static Block MAGENTA_BRICK_WALL;
	public static Block MAGENTA_BRICK_TRAPDOOR;
	public static Block MAGENTA_BRICK_FENCE;
	public static Block MAGENTA_BRICK_FENCE_GATE;

	private static void registerTags() {
		MAGENTA_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "magenta_brick_slabs"));
		MAGENTA_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "magenta_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		MAGENTA_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.MAGENTA_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		MAGENTA_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, MAGENTA_BRICK_ITEM, Blocks.BRICKS, Items.MAGENTA_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(MAGENTA_BRICK_BLOCKS_ITEM_TAG));
		MAGENTA_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, MAGENTA_BRICKS, MAGENTA_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		MAGENTA_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, MAGENTA_BRICKS, MAGENTA_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(MAGENTA_BRICK_SLABS_ITEM_TAG));
		MAGENTA_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, MAGENTA_BRICKS, MAGENTA_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		MAGENTA_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, MAGENTA_BRICKS, MAGENTA_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		MAGENTA_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, MAGENTA_BRICKS, MAGENTA_BRICK_SLABS_ITEM_TAG, MAGENTA_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		MAGENTA_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, MAGENTA_BRICKS, MAGENTA_BRICK_SLABS_ITEM_TAG, MAGENTA_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
