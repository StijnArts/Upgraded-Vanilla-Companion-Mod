package drai.dev.upgradedvanilla.modules.minecraft.resourceblocks;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.wool.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Coal {
	public static TagKey<Item> COAL_BLOCKS_ITEM_TAG;
	public static Block COAL_SLAB;
	public static Block COAL_STAIRS;
	public static Block COAL_WALL;
	private static void registerTags(){
		COAL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"coal_blocks"), List.of(new ResourceLocation("minecraft", "coal")));
	}

	public  static void register(){
		registerTags();
		COAL_SLAB = MinecraftResourceBlocks.resourceSlabBlock("coal",Blocks.COAL_BLOCK, COAL_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.COAL_BLOCKS),List.of());
		COAL_STAIRS = MinecraftResourceBlocks.resourceStairsBlock("coal",Blocks.COAL_BLOCK, COAL_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.COAL_BLOCKS),List.of());
		COAL_WALL = MinecraftResourceBlocks.resourceWallBlock("coal",Blocks.COAL_BLOCK,COAL_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, UVCommonBlockTags.COAL_BLOCKS, BlockTags.WALLS),List.of());
	}
}
