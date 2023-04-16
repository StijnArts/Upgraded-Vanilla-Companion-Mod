package drai.dev.upgradedvanilla.blocks.grass;

import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.blocks.dirt.DirtWall;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.lighting.*;
import net.minecraft.world.level.material.*;

public class SpreadableStairsBlock extends DirtStairs implements SimpleWaterloggedBlock {
	public static final BooleanProperty SNOWY;
	private BaseDirtStairs baseDirtStair;
	private SpreadingSnowyDirtBlock originBlock;

	public SpreadableStairsBlock(SpreadingSnowyDirtBlock originBlock, BaseDirtStairs baseDirtStair) {
		super(originBlock.defaultBlockState(),FabricBlockSettings.copy(originBlock));
		this.baseDirtStair = baseDirtStair;
		this.originBlock = originBlock;
	}

	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		BlockState blockState = world.getBlockState(blockPos);
		if (!blockState.isFaceSturdy(world, pos, Direction.DOWN, SupportType.FULL)) {
			return true;
		} else if (blockState.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockState, blockPos, Direction.UP, blockState.getLightBlock(world, blockPos));
			return i < world.getMaxLightLevel();
		}
	}

	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!canSurvive(state, level, pos)) {
			if(state.getValue(StairBlock.HALF)==Half.TOP){
				if (baseDirtStair !=null) {
					level.setBlock(pos, baseDirtStair.withPropertiesOf(level.getBlockState(pos)), 2);
				} else {
					level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
				}
			}
		} else if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
			BlockState blockState = this.defaultBlockState();
			boolean isMycelium = originBlock.defaultBlockState().is(Blocks.MYCELIUM);
			boolean isGrass = originBlock.defaultBlockState().is(Blocks.GRASS_BLOCK);
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

	private boolean canSpread(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		return canSurvive(state, world, pos) && !world.getFluidState(blockPos).is(FluidTags.WATER);
	}

	public FluidState getFluidState(BlockState state) {
		return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	static {
		SNOWY = BlockStateProperties.SNOWY;
	}
}
