package drai.dev.upgradedvanilla.modules.minecraft.soil.grass;

import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Grass {
	public static TagKey<Item> GRASS_BLOCK_ITEM_TAG;
	public static Block GRASS_SLAB;
	public static Block GRASS_STAIRS;
	public static Block GRASS_WALL;
	private static void registerTags(){
		GRASS_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("grass_blocks", List.of(new ResourceLocation("minecraft","grass_block")));
	}
	public  static void register(){
		registerTags();
		GRASS_SLAB = MinecraftGrassBlocks.grassSlabBlock("grass",Blocks.GRASS_BLOCK, Blocks.DIRT, Dirt.DIRT_SLAB, GRASS_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
		GRASS_STAIRS = MinecraftGrassBlocks.grassStairsBlock("grass",Blocks.GRASS_BLOCK, Blocks.DIRT, Dirt.DIRT_STAIRS, GRASS_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
		GRASS_WALL = MinecraftGrassBlocks.grassWallBlock("grass",Blocks.GRASS_BLOCK, Blocks.DIRT, Dirt.DIRT_WALL, GRASS_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());

	}
}
