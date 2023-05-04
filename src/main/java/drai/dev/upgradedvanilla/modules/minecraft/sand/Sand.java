package drai.dev.upgradedvanilla.modules.minecraft.sand;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.dirt.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Sand {
	public static TagKey<Item> SAND_BLOCK_ITEM_TAG;
	public static Block SAND_SLAB;
	public static Block SAND_STAIRS;

	private static void registerTags(){
		SAND_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"sand_blocks"), List.of(new ResourceLocation("minecraft","sand")));
	}
	public  static void register(){
		registerTags();
		SAND_SLAB = MinecraftSandBlocks.sandSlabBlock("sand",Blocks.SAND, SAND_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of(),0xDBD3A0);
		SAND_STAIRS = MinecraftSandBlocks.sandStairsBlock("sand",Blocks.SAND, SAND_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
	}
}
