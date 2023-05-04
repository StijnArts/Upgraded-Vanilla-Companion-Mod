package drai.dev.upgradedvanilla.modules.minecraft.soil.grass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.dirt.*;
import drai.dev.upgradedvanilla.tag.*;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

import static net.minecraft.world.level.block.SnowyDirtBlock.SNOWY;

public class Grass {
	public static TagKey<Item> GRASS_BLOCK_ITEM_TAG;
	public static Block GRASS_SLAB;
	public static Block GRASS_STAIRS;
	public static Block GRASS_WALL;
	private static void registerTags(){
		GRASS_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"grass_blocks"), List.of(new ResourceLocation("minecraft","grass_block")));
	}
	public  static void register(){
		registerTags();
		GRASS_SLAB = MinecraftGrassBlocks.grassSlabBlock("grass",Blocks.GRASS_BLOCK, Blocks.DIRT, Dirt.DIRT_SLAB, GRASS_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		GRASS_STAIRS = MinecraftGrassBlocks.grassStairsBlock("grass",Blocks.GRASS_BLOCK, Blocks.DIRT, Dirt.DIRT_STAIRS, GRASS_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		GRASS_WALL = MinecraftGrassBlocks.grassWallBlock("grass",Blocks.GRASS_BLOCK, Blocks.DIRT, Dirt.DIRT_WALL, GRASS_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());

	}

	public static void onTick(BlockState defaultBlockstate, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {

			boolean isMycelium = level.getBlockState(pos).is(Blocks.MYCELIUM);
			boolean isGrass = level.getBlockState(pos).is(Blocks.GRASS_BLOCK);
			for(int i = 0; i < 4; ++i) {
				BlockPos blockPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (canSpread(defaultBlockstate, level, blockPos)) {
					if (level.getBlockState(blockPos).is(Dirt.DIRT_SLAB)) {
						if (isGrass) {
							level.setBlockAndUpdate(blockPos, (BlockState)((BlockState)((BlockState) Grass.GRASS_SLAB.defaultBlockState().setValue(SNOWY, level.getBlockState(blockPos.above()).is(Blocks.SNOW))).setValue(SlabBlock.WATERLOGGED, (Boolean)level.getBlockState(blockPos).getValue(SlabBlock.WATERLOGGED))).setValue(SlabBlock.TYPE, (SlabType)level.getBlockState(blockPos).getValue(SlabBlock.TYPE)));
						} else if (isMycelium) {
							level.setBlockAndUpdate(blockPos, (BlockState)((BlockState)((BlockState)Mycelium.MYCELIUM_SLAB.defaultBlockState().setValue(SNOWY, level.getBlockState(blockPos.above()).is(Blocks.SNOW))).setValue(SlabBlock.WATERLOGGED, (Boolean)level.getBlockState(blockPos).getValue(SlabBlock.WATERLOGGED))).setValue(SlabBlock.TYPE, (SlabType)level.getBlockState(blockPos).getValue(SlabBlock.TYPE)));
						}
					}

					if (level.getBlockState(blockPos).is(Dirt.DIRT_STAIRS)) {
						if (isGrass) {
							level.setBlockAndUpdate(blockPos, Grass.GRASS_STAIRS.withPropertiesOf(level.getBlockState(blockPos)));
						} else if (isMycelium) {
							level.setBlockAndUpdate(blockPos, Mycelium.MYCELIUM_STAIRS.withPropertiesOf(level.getBlockState(blockPos)));
						}
					}

					if ((level.getBlockState(blockPos).getBlock() instanceof DirtWall) && !level.getBlockState(blockPos.above()).is(BlockTags.WALLS)) {
						if (isGrass) {
							level.setBlockAndUpdate(blockPos, Grass.GRASS_WALL.withPropertiesOf(level.getBlockState(blockPos)));
						} else if (isMycelium) {
							level.setBlockAndUpdate(blockPos, Mycelium.MYCELIUM_WALL.withPropertiesOf(level.getBlockState(blockPos)));
						}
					}
				}
			}


	}

	private static boolean canSpread(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		return SpreadableSlabBlock.canSurvive(state, world, pos) && !world.getFluidState(blockPos).is(FluidTags.WATER);
	}
}
