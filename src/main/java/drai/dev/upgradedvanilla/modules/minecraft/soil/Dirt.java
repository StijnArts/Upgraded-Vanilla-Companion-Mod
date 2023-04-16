package drai.dev.upgradedvanilla.modules.minecraft.soil;

import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Dirt {
	public static TagKey<Item> DIRT_BLOCK_ITEM_TAG;
	public static Block DIRT_SLAB;
	public static Block DIRT_STAIRS;
	public static Block DIRT_WALL;

	private static void registerTags(){
		DIRT_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("dirt_blocks", List.of(new ResourceLocation("minecraft","dirt")));
		}
	public  static void register(){
		registerTags();
		DIRT_SLAB = MinecraftDirtBlocks.dirtSlabBlock("dirt",Blocks.DIRT, DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
		DIRT_STAIRS = MinecraftDirtBlocks.dirtStairsBlock("dirt",Blocks.DIRT, DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
		DIRT_WALL = MinecraftDirtBlocks.dirtWallBlock("dirt",Blocks.DIRT,DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());
	}
}
