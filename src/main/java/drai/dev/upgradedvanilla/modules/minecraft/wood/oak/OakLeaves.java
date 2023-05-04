package drai.dev.upgradedvanilla.modules.minecraft.wood.oak;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.wood.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.function.*;

public class OakLeaves {
	public static TagKey<Item> LEAF_BLOCK_ITEM_TAG;
	public static Block OAK_LEAF_SLAB;
	public static Block OAK_LEAF_STAIRS;
	public static Block OAK_LEAF_WALL;
	public static BiFunction<BlockAndTintGetter, BlockPos, Integer> oakTintBifunction = (world, pos) -> {
		return world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();
	};
	private static void registerTags(){
		LEAF_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"oak_leaf_blocks"), List.of(new ResourceLocation("minecraft","oak_leaves")));
	}
	public  static void register(){
		registerTags();
		OAK_LEAF_SLAB = MinecraftLeafBlocks.leafSlabBlock("oak",Blocks.OAK_LEAVES, Blocks.OAK_SAPLING, LEAF_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of(), oakTintBifunction, FoliageColor.getDefaultColor());
		OAK_LEAF_STAIRS = MinecraftLeafBlocks.leafStairsBlock("oak",Blocks.OAK_LEAVES, Blocks.OAK_SAPLING, LEAF_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of(), oakTintBifunction, FoliageColor.getDefaultColor());
		OAK_LEAF_WALL = MinecraftLeafBlocks.leafWallBlock("oak",Blocks.OAK_LEAVES, Blocks.OAK_SAPLING, LEAF_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of(), oakTintBifunction, FoliageColor.getDefaultColor());
	}
}
