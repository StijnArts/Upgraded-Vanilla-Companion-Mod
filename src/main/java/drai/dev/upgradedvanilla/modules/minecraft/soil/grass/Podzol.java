package drai.dev.upgradedvanilla.modules.minecraft.soil.grass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.dirt.*;
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
		PODZOL_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"podzol_blocks"), List.of(new ResourceLocation("minecraft","podzol")));
	}
	public  static void register(){
		registerTags();
		PODZOL_STAIRS = MinecraftGrassBlocks.podzolStairsBlock("podzol",Blocks.PODZOL, Blocks.DIRT, Dirt.DIRT_SLAB, PODZOL_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
		PODZOL_SLAB = MinecraftGrassBlocks.podzolSlabBlock("podzol",Blocks.PODZOL, Blocks.DIRT, Dirt.DIRT_STAIRS, PODZOL_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
		PODZOL_WALL = MinecraftGrassBlocks.podzolWallBlock("podzol",Blocks.PODZOL, Blocks.DIRT, Dirt.DIRT_WALL, PODZOL_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());

	}
}
