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

public class GreenBrick {
	private static final String MATERIAL_NAME = "green_brick";
	public static TagKey<Item> GREEN_BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> GREEN_BRICK_BLOCKS_ITEM_TAG;
	public static Item GREEN_BRICK_ITEM;
	public static Block GREEN_BRICKS;
	public static Block GREEN_BRICK_SLAB;
	public static Block GREEN_BRICK_STAIRS;
	public static Block GREEN_BRICK_WALL;
	public static Block GREEN_BRICK_TRAPDOOR;
	public static Block GREEN_BRICK_FENCE;
	public static Block GREEN_BRICK_FENCE_GATE;

	private static void registerTags() {
		GREEN_BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "green_brick_slabs"));
		GREEN_BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "green_brick_blocks"));
	}
	public  static void register() {
		registerTags();
		GREEN_BRICK_ITEM = MinecraftItems.coloredBrick(MATERIAL_NAME, Items.GREEN_DYE, UpgradedVanillaTags.BRICK_ITEM_TAG,List.of(UpgradedVanillaTags.BRICK_ITEM_TAG));
		GREEN_BRICKS = MinecraftBrickBlocks.brickBlock(MATERIAL_NAME, GREEN_BRICK_ITEM, Blocks.BRICKS, Items.GREEN_DYE, Brick.BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(GREEN_BRICK_BLOCKS_ITEM_TAG));
		GREEN_BRICK_STAIRS = MinecraftBrickBlocks.brickStairsBlock(MATERIAL_NAME, GREEN_BRICKS, GREEN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		GREEN_BRICK_SLAB = MinecraftBrickBlocks.brickSlabBlock(MATERIAL_NAME, GREEN_BRICKS, GREEN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(GREEN_BRICK_SLABS_ITEM_TAG));
		GREEN_BRICK_WALL = MinecraftBrickBlocks.brickWallBlock(MATERIAL_NAME, GREEN_BRICKS, GREEN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		GREEN_BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, GREEN_BRICKS, GREEN_BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		GREEN_BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, GREEN_BRICKS, GREEN_BRICK_SLABS_ITEM_TAG, GREEN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		GREEN_BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, GREEN_BRICKS, GREEN_BRICK_SLABS_ITEM_TAG, GREEN_BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
