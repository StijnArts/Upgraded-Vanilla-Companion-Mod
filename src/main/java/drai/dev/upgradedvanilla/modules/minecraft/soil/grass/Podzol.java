package drai.dev.upgradedvanilla.modules.minecraft.soil.grass;

import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Podzol {
	public static TagKey<Item> PODZOL_BLOCK_ITEM_TAG;
	public static Block PODZOL_SLAB;
	public static Block PODZOL_STAIRS;
	public static Block PODZOL_WALL;
	private static void registerTags(){
		PODZOL_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("podzol_blocks", List.of(new ResourceLocation("minecraft","podzol")));
	}
	public  static void register(){
		registerTags();
		PODZOL_SLAB = MinecraftGrassBlocks.podzolStairsBlock("podzol",Blocks.PODZOL, Blocks.DIRT, Dirt.DIRT_SLAB, PODZOL_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.DIRT),List.of());
		PODZOL_STAIRS = MinecraftGrassBlocks.podzolSlabBlock("podzol",Blocks.PODZOL, Blocks.DIRT, Dirt.DIRT_STAIRS, PODZOL_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.DIRT),List.of());
		PODZOL_WALL = MinecraftGrassBlocks.podzolWallBlock("podzol",Blocks.PODZOL, Blocks.DIRT, Dirt.DIRT_WALL, PODZOL_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS, BlockTags.DIRT),List.of());

	}
}
