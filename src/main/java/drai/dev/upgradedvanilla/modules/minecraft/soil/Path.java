package drai.dev.upgradedvanilla.modules.minecraft.soil;

import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Path {
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
