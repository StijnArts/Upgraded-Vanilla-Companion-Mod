package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Black {
	public static TagKey<Item> BLACK_WOOL_BLOCKS_ITEM_TAG;
	public static Block BLACK_WOOL_SLAB;
	public static Block BLACK_WOOL_STAIRS;
	public static Block BLACK_WOOL_WALL;
	private static void registerTags(){
		BLACK_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"black_wool_blocks"), List.of(new ResourceLocation("minecraft", "black_wool")));
	}

	public  static void register(){
		registerTags();
		BLACK_WOOL_SLAB = MinecraftWoolBlocks.woolSlabBlock("black_wool",Blocks.BLACK_WOOL, BLACK_WOOL_BLOCKS_ITEM_TAG,
				List.of(BlockTags.OCCLUDES_VIBRATION_SIGNALS,BlockTags.DAMPENS_VIBRATIONS,BlockTags.WOOL, UpgradedVanillaTags.MINEABLE_KNIFE,UpgradedVanillaTags.WINDMILL_SAILS),List.of());
		BLACK_WOOL_STAIRS = MinecraftWoolBlocks.woolStairsBlock("black_wool",Blocks.BLACK_WOOL, BLACK_WOOL_BLOCKS_ITEM_TAG,
				List.of(BlockTags.OCCLUDES_VIBRATION_SIGNALS,BlockTags.DAMPENS_VIBRATIONS,BlockTags.WOOL, UpgradedVanillaTags.MINEABLE_KNIFE,UpgradedVanillaTags.WINDMILL_SAILS),List.of());
		BLACK_WOOL_WALL = MinecraftWoolBlocks.woolWallBlock("black_wool",Blocks.BLACK_WOOL,BLACK_WOOL_BLOCKS_ITEM_TAG,
				List.of(BlockTags.OCCLUDES_VIBRATION_SIGNALS,BlockTags.DAMPENS_VIBRATIONS,BlockTags.WOOL, BlockTags.WALLS, UpgradedVanillaTags.MINEABLE_KNIFE, UpgradedVanillaTags.WINDMILL_SAILS),List.of());
	}
}
