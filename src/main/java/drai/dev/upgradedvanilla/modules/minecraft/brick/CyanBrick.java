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

public class CyanBrick {
	private static final String MATERIAL_NAME = "cyan_brick";
	public static TagKey<Item> CYAN_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> CYAN_BRICK_BLOCKS_ITEM_TAG;
	public static Item CYAN_BRICK_ITEM;
	public static Block CYAN_BRICKS;
	public static Block CYAN_BRICK_SLAB;
	public static Block CYAN_BRICK_STAIRS;
	public static Block CYAN_BRICK_WALL;
	public static Block CYAN_BRICK_TRAPDOOR;
	public static Block CYAN_BRICK_FENCE;
	public static Block CYAN_BRICK_FENCE_GATE;

	private static void registerTags() {
		CYAN_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "cyan_brick_slabs"));
		CYAN_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "cyan_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		CYAN_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.CYAN_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		CYAN_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, CYAN_BRICK_ITEM, Blocks.BRICKS, Items.CYAN_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(CYAN_BRICK_BLOCKS_ITEM_TAG));
		CYAN_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, CYAN_BRICKS, CYAN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CYAN_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, CYAN_BRICKS, CYAN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(CYAN_BRICK_SLABS_ITEM_TAG));
		CYAN_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, CYAN_BRICKS, CYAN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CYAN_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, CYAN_BRICKS, CYAN_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		CYAN_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, CYAN_BRICKS, CYAN_BRICK_SLABS_ITEM_TAG, CYAN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		CYAN_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, CYAN_BRICKS, CYAN_BRICK_SLABS_ITEM_TAG, CYAN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
