package drai.dev.upgradedvanilla.modules.minecraft.soil.grass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.dirt.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Mycelium {
	public static Block MYCELIUM_SLAB;
	public static Block MYCELIUM_STAIRS;
	public static Block MYCELIUM_WALL;
	public static TagKey<Item> MYCELIUM_BLOCK_ITEM_TAG;
	private static void registerTags(){
		MYCELIUM_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"mycelium_blocks"), List.of(new ResourceLocation("minecraft","mycelium")));
	}
	public  static void register(){
		registerTags();
		MYCELIUM_SLAB = MinecraftGrassBlocks.myceliumSlabBlock("mycelium",Blocks.MYCELIUM, Blocks.DIRT, Dirt.DIRT_SLAB, MYCELIUM_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		MYCELIUM_STAIRS = MinecraftGrassBlocks.myceliumStairsBlock("mycelium",Blocks.MYCELIUM, Blocks.DIRT, Dirt.DIRT_STAIRS, MYCELIUM_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		MYCELIUM_WALL = MinecraftGrassBlocks.myceliumWallBlock("mycelium",Blocks.MYCELIUM, Blocks.DIRT, Dirt.DIRT_WALL, MYCELIUM_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());

	}

	public static class Path {
		public static Block PATH_SLAB;
		public static Block PATH_STAIRS;
		public static Block PATH_WALL;

		private static void registerTags(){

		}

		public static void register(){
			registerTags();
			PATH_SLAB = MinecraftGrassBlocks.pathSlabBlock(Blocks.DIRT_PATH, Blocks.DIRT, Dirt.DIRT_SLAB,
					List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
			PATH_STAIRS = MinecraftGrassBlocks.pathStairsBlock(Blocks.DIRT_PATH, Blocks.DIRT, Dirt.DIRT_STAIRS,
					List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
			PATH_WALL = MinecraftGrassBlocks.pathWallBlock(Blocks.DIRT_PATH, Blocks.DIRT, Dirt.DIRT_WALL,
					List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());
		}
	}
}
