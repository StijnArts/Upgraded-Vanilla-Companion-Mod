package drai.dev.upgradedvanilla.modules.minecraft.red_stained_glass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.glass.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class RedStainedGlass {

	public static TagKey<Item> RED_STAINED_GLASS_BLOCKS_ITEM_TAG;
	public static Block RED_STAINED_GLASS_SLAB;
	public static Block RED_STAINED_GLASS_STAIRS;
	private static void registerTags(){
		RED_STAINED_GLASS_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"red_stained_glass_blocks"),
				List.of(new ResourceLocation("minecraft", "red_stained_glass")));
	}

	public  static void register(){
		registerTags();
		RED_STAINED_GLASS_SLAB = MinecraftGlassBlocks.stainedGlassSlabBlock("red_stained_glass",Blocks.RED_STAINED_GLASS, RED_STAINED_GLASS_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.STAINED_GLASS),List.of(), DyeColor.RED);
		RED_STAINED_GLASS_STAIRS = MinecraftGlassBlocks.stainedGlassStairsBlock("red_stained_glass",Blocks.RED_STAINED_GLASS, RED_STAINED_GLASS_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.STAINED_GLASS),List.of(), DyeColor.RED);
	}
}

