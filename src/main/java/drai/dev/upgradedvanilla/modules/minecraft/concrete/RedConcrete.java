package drai.dev.upgradedvanilla.modules.minecraft.concrete;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class RedConcrete {
	private static final String MATERIAL_NAME = "red_concrete";
	public static TagKey<Item> RED_CONCRETE_SLABS_ITEM_TAG;
	public static TagKey<Item> RED_CONCRETE_BLOCKS_ITEM_TAG;
	public static Block RED_CONCRETE_STAIRS;
	public static Block RED_CONCRETE_SLAB;
	public static Block RED_CONCRETE_WALL;
	public static Block RED_CONCRETE_TRAPDOOR;
	public static Block RED_CONCRETE_FENCE;
	public static Block RED_CONCRETE_FENCE_GATE;

	private static void registerTags(){
		RED_CONCRETE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"red_concrete_slabs"));
		RED_CONCRETE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"red_concrete_blocks"), List.of(new ResourceLocation("minecraft", "red_concrete")));
	}
	public  static void register(){
		registerTags();
		RED_CONCRETE_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, Blocks.RED_CONCRETE, RED_CONCRETE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		RED_CONCRETE_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, Blocks.RED_CONCRETE, RED_CONCRETE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(RED_CONCRETE_SLABS_ITEM_TAG));
		RED_CONCRETE_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, Blocks.RED_CONCRETE, RED_CONCRETE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		RED_CONCRETE_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, Blocks.RED_CONCRETE, RED_CONCRETE_SLABS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		RED_CONCRETE_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, Blocks.RED_CONCRETE, RED_CONCRETE_SLABS_ITEM_TAG, RED_CONCRETE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		RED_CONCRETE_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, Blocks.RED_CONCRETE, RED_CONCRETE_SLABS_ITEM_TAG, RED_CONCRETE_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
	}
}
