package drai.dev.upgradedvanilla.blocks.grass;

import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.dirt.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.lighting.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;

import org.jetbrains.annotations.*;

public class SpreadableSlabBlock extends DirtSlab implements SimpleWaterloggedBlock, BonemealableBlock {
	public static final BooleanProperty SNOWY;
	public static final EnumProperty<SlabType> TYPE;
	public static final BooleanProperty WATERLOGGED;
	protected static final VoxelShape BOTTOM_SHAPE;
	protected static final VoxelShape TOP_SHAPE;
	private BaseDirtSlab baseDirtSlab;
	private SpreadingSnowyDirtBlock originBlock;
	public SpreadableSlabBlock(SpreadingSnowyDirtBlock originBlock, BaseDirtSlab baseDirtSlab) {
		super(FabricBlockSettings.copy(originBlock));
		this.baseDirtSlab = baseDirtSlab;
		this.originBlock = originBlock;
	}

	public boolean canSurvive(@NotNull BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.is(Blocks.SNOW) && (Integer)blockState.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		} else if (!blockState.isSolidRender(world, pos) && blockState.getBlock() instanceof SpreadableSlabBlock && blockState.getValue(SlabBlock.TYPE) == SlabType.TOP || blockState.getBlock() instanceof SpreadableStairsBlock && blockState.getValue(StairBlock.HALF) == Half.TOP) {
			return true;
		} else if (state.getBlock() instanceof SpreadableSlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) {
			return true;
		} else if (state.getBlock() instanceof SpreadableSlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.TOP && !blockState.isFaceSturdy(world, pos, Direction.DOWN, SupportType.FULL)) {
			return true;
		} else if (blockState.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockState, blockPos, Direction.UP, blockState.getLightBlock(world, blockPos));
			return i < world.getMaxLightLevel();
		}
	}

	public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM ? false : world.getBlockState(pos.above()).isAir();
	}

	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
		BlockPos blockPos = pos.above();
		ChunkGenerator chunkGenerator = world.getChunkSource().getGenerator();
		if (originBlock.defaultBlockState().is(Blocks.CRIMSON_NYLIUM)) {
			((ConfiguredFeature) NetherFeatures.CRIMSON_FOREST_VEGETATION_BONEMEAL.value()).place(world, chunkGenerator, random, blockPos);
		} else if (originBlock.defaultBlockState().is(Blocks.WARPED_NYLIUM)) {
			((ConfiguredFeature)NetherFeatures.WARPED_FOREST_VEGETATION_BONEMEAL.value()).place(world, chunkGenerator, random, blockPos);
			((ConfiguredFeature)NetherFeatures.NETHER_SPROUTS_BONEMEAL.value()).place(world, chunkGenerator, random, blockPos);
			if (random.nextInt(8) == 0) {
				((ConfiguredFeature)NetherFeatures.TWISTING_VINES_BONEMEAL.value()).place(world, chunkGenerator, random, blockPos);
			}
		} else if (originBlock.defaultBlockState().is(Blocks.GRASS_BLOCK)) {
			((GrassBlock)originBlock.defaultBlockState().getBlock()).performBonemeal(world, random, pos, state);
		}
	}

	private boolean canSpread(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		return canSurvive(state, world, pos) && !world.getFluidState(blockPos).is(FluidTags.WATER);
	}

	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!canSurvive(state, level, pos)) {
			if (baseDirtSlab !=null) {
				level.setBlock(pos, baseDirtSlab.withPropertiesOf(level.getBlockState(pos)), 2);
			} else {
				level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
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
							level.setBlockAndUpdate(blockPos, (BlockState)((BlockState)((BlockState)Grass.GRASS_SLAB.defaultBlockState().setValue(SNOWY, level.getBlockState(blockPos.above()).is(Blocks.SNOW))).setValue(SlabBlock.WATERLOGGED, (Boolean)level.getBlockState(blockPos).getValue(SlabBlock.WATERLOGGED))).setValue(SlabBlock.TYPE, (SlabType)level.getBlockState(blockPos).getValue(SlabBlock.TYPE)));
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

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED, SNOWY);
	}

	public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockPos blockPos = ctx.getClickedPos();
		BlockState blockState = ctx.getLevel().getBlockState(blockPos);
		if (blockState.is(this)) {
			return (BlockState)((BlockState)((BlockState)blockState.setValue(TYPE, SlabType.DOUBLE)).setValue(WATERLOGGED, false)).setValue(SNOWY, ctx.getLevel().getBlockState(ctx.getClickedPos().above()).is(BlockTags.SNOW));
		} else {
			FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
			BlockState blockState2 = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)).setValue(SNOWY, ctx.getLevel().getBlockState(ctx.getClickedPos().above()).is(BlockTags.SNOW));
			Direction direction = ctx.getClickedFace();
			return direction == Direction.DOWN || direction != Direction.UP && ctx.getClickLocation().y - (double)blockPos.getY() > 0.5 ? (BlockState)blockState2.setValue(TYPE, SlabType.TOP) : blockState2;
		}
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		state = (BlockState)state.setValue(SNOWY, world.getBlockState(pos.above()).is(BlockTags.SNOW));
		return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	static {
		TYPE = BlockStateProperties.SLAB_TYPE;
		WATERLOGGED = BlockStateProperties.WATERLOGGED;
		BOTTOM_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
		TOP_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
		SNOWY = BlockStateProperties.SNOWY;
	}
}
