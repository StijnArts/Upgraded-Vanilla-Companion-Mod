package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({SpreadingSnowyDirtBlock.class})
public abstract class SpreadableBlockMixin extends SnowyDirtBlock {
	@Shadow
	public static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
		return false;
	}

	public SpreadableBlockMixin(BlockBehaviour.Properties settings) {
		super(settings);
	}

	@Inject(
			method = {"randomTick"},
			at = @At(value = "HEAD"),
			cancellable = true,
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void onTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
		if (!canBeGrass(state, level, pos)) {
			//Tells the block to turn into a dirt block if its obstructed
			level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			ci.cancel();
		} else if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
			BlockState blockState = this.defaultBlockState();
			boolean isMycelium = level.getBlockState(pos).is(Blocks.MYCELIUM);
			boolean isGrass = level.getBlockState(pos).is(Blocks.GRASS_BLOCK);
			for(int i = 0; i < 4; ++i) {
				BlockPos blockPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (canSpread(blockState, level, blockPos)) {
					if (level.getBlockState(blockPos).is(Blocks.DIRT)) {
						if (isGrass) {
							level.setBlockAndUpdate(blockPos, (BlockState)Blocks.GRASS_BLOCK.defaultBlockState().setValue(SNOWY, level.getBlockState(blockPos.above()).is(Blocks.SNOW)));
						} else if (isMycelium) {
							level.setBlockAndUpdate(blockPos, (BlockState)Blocks.MYCELIUM.defaultBlockState().setValue(SNOWY, level.getBlockState(blockPos.above()).is(Blocks.SNOW)));
						}
					}

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

	}

	private static boolean canSpread(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		return SpreadableSlabBlock.canSurvive(state, world, pos) && !world.getFluidState(blockPos).is(FluidTags.WATER);
	}
}
