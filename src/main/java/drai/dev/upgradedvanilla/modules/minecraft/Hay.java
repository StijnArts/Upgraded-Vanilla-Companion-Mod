package drai.dev.upgradedvanilla.modules.minecraft;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Hay {
	public static TagKey<Item> HAY_BLOCK_ITEM_TAG;
	public static Block HAY_SLAB;
	public static Block HAY_STAIRS;
	public static Block HAY_WALL;

	private static void registerTags(){
		HAY_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"hay_blocks"), List.of(new ResourceLocation("minecraft","hay_block")));
	}
	public  static void register(){
		registerTags();
		HAY_SLAB = MinecraftThatchBlocks.thatchSlabBlock("hay",Blocks.HAY_BLOCK, HAY_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_HOE),List.of());
		HAY_STAIRS = MinecraftThatchBlocks.thatchStairsBlock("hay",Blocks.HAY_BLOCK, HAY_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_HOE),List.of());
		HAY_WALL = MinecraftThatchBlocks.thatchWallBlock("hay",Blocks.HAY_BLOCK,HAY_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_HOE, BlockTags.WALLS),List.of());
	}
}
