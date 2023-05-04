package drai.dev.upgradedvanilla.modules.minecraft.soil.dirt;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class CoarseDirt {
	public static TagKey<Item> COARSE_DIRT_BLOCK_ITEM_TAG;
	public static Block COARSE_DIRT_SLAB;
	public static Block COARSE_DIRT_STAIRS;
	public static Block COARSE_DIRT_WALL;

	private static void registerTags(){
		COARSE_DIRT_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"coarse_dirt_blocks"), List.of(new ResourceLocation("minecraft","coarse_dirt")));
	}
	public  static void register(){
		registerTags();
		COARSE_DIRT_SLAB = MinecraftDirtBlocks.coarseDirtSlabBlock("coarse_dirt",Blocks.COARSE_DIRT, COARSE_DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		COARSE_DIRT_STAIRS = MinecraftDirtBlocks.coarseDirtStairsBlock("coarse_dirt",Blocks.COARSE_DIRT, COARSE_DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		COARSE_DIRT_WALL = MinecraftDirtBlocks.coarseDirtWallBlock("coarse_dirt",Blocks.COARSE_DIRT,COARSE_DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());
	}
}
