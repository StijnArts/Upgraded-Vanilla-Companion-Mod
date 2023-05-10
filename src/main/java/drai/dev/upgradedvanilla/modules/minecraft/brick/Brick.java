package drai.dev.upgradedvanilla.modules.minecraft.brick;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Brick {
	private static final String MATERIAL_NAME = "brick";
	public static TagKey<Item> BRICK_SLABS_ITEM_TAG;
	public static TagKey<Item> BRICK_BLOCKS_ITEM_TAG;
	public static Block BRICK_TRAPDOOR;
	public static Block BRICK_FENCE;
	public static Block BRICK_FENCE_GATE;

	private static void registerTags() {
		BRICK_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID, "brick_slabs"), List.of(new ResourceLocation("minecraft", "brick_slab")));
		BRICK_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID, "brick_blocks"), List.of(new ResourceLocation("minecraft", "bricks")));
	}
	public  static void register() {
		registerTags();
		BRICK_TRAPDOOR = MinecraftBrickBlocks.brickTrapdoorBlock(MATERIAL_NAME, Blocks.BRICKS, BRICK_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		BRICK_FENCE = MinecraftBrickBlocks.brickFenceBlock(MATERIAL_NAME, Blocks.BRICKS, BRICK_SLABS_ITEM_TAG, BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
		BRICK_FENCE_GATE = MinecraftBrickBlocks.brickFenceGateBlock(MATERIAL_NAME, Blocks.BRICKS, BRICK_SLABS_ITEM_TAG, BRICK_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE), List.of());
	}
}
