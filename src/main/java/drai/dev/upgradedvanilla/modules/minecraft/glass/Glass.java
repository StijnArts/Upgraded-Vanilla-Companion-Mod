package drai.dev.upgradedvanilla.modules.minecraft.glass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.resourceblocks.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Glass {
	public static TagKey<Item> GLASS_BLOCKS_ITEM_TAG;
	public static Block GLASS_SLAB;
	public static Block GLASS_STAIRS;
	private static void registerTags(){
		GLASS_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"glass_blocks"), List.of(new ResourceLocation("minecraft", "glass")));
	}

	public  static void register(){
		registerTags();
		GLASS_SLAB = MinecraftGlassBlocks.glassSlabBlock("glass",Blocks.GLASS, GLASS_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.GLASS_BLOCKS),List.of());
		GLASS_STAIRS = MinecraftGlassBlocks.glassStairsBlock("glass",Blocks.GLASS, GLASS_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.GLASS_BLOCKS),List.of());
	}
}
