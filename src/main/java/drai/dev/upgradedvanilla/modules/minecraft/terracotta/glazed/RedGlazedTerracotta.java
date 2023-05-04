package drai.dev.upgradedvanilla.modules.minecraft.terracotta.glazed;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class RedGlazedTerracotta {
	public static TagKey<Item> RED_GLAZED_TERRACOTTA_BLOCK_ITEM_TAG;
	public static Block GLAZED_TERRACOTTA_SLAB;
	public static Block GLAZED_TERRACOTTA_STAIRS;
	public static Block GLAZED_TERRACOTTA_WALL;

	private static void registerTags(){
		RED_GLAZED_TERRACOTTA_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"red_glazed_terracotta_blocks"),
				List.of(new ResourceLocation("minecraft","red_glazed_terracotta")));
	}
	public  static void register(){
		registerTags();
		GLAZED_TERRACOTTA_SLAB = MinecraftGlazedTerracottaBlocks.glazedSlabBlock("red_glazed_terracotta",Blocks.RED_GLAZED_TERRACOTTA, RED_GLAZED_TERRACOTTA_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_HOE),List.of());
		GLAZED_TERRACOTTA_STAIRS = MinecraftGlazedTerracottaBlocks.glazedStairsBlock("red_glazed_terracotta",Blocks.RED_GLAZED_TERRACOTTA, RED_GLAZED_TERRACOTTA_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_HOE),List.of());
		GLAZED_TERRACOTTA_WALL = MinecraftGlazedTerracottaBlocks.glazedWallBlock("red_glazed_terracotta",Blocks.RED_GLAZED_TERRACOTTA,RED_GLAZED_TERRACOTTA_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_HOE, BlockTags.WALLS),List.of());
	}
}
