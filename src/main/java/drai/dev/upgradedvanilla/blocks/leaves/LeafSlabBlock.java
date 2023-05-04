package drai.dev.upgradedvanilla.blocks.leaves;

import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;

import org.jetbrains.annotations.*;

public class LeafSlabBlock extends SlabBlock {
	public static final int MAX_DISTANCE = 7;
	public static final IntegerProperty DISTANCE;
	public static final BooleanProperty PERSISTENT;

	public LeafSlabBlock(BlockBehaviour.Properties settings) {
		super(settings);
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return super.getShape(state, world, pos, context);
	}

	public boolean isRandomlyTicking(BlockState state) {
		return (Integer)state.getValue(DISTANCE) == 7 && !(Boolean)state.getValue(PERSISTENT);
	}

	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		if (this.shouldDecay(state)) {
			dropResources(state, world, pos);
			world.removeBlock(pos, false);
		}

	}

	public FluidState getFluidState(BlockState state) {
		return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
		if (!(Boolean)state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
			if (!world.isClientSide()) {
				world.setBlock(pos, (BlockState)state.setValue(BlockStateProperties.WATERLOGGED, true), 3);
				world.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(world));
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean canPlaceLiquid(BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
		return fluid == Fluids.WATER;
	}

	public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockPos blockPos = ctx.getClickedPos();
		BlockState blockState = ctx.getLevel().getBlockState(blockPos);
		FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
		if (blockState.is(this)) {
			return (BlockState)((BlockState)blockState.setValue(TYPE, SlabType.DOUBLE)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
		} else {
			BlockState blockState2 = (BlockState)((BlockState)this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
			Direction direction = ctx.getClickedFace();
			return direction == Direction.DOWN || direction != Direction.UP && ctx.getClickLocation().y - (double)blockPos.getY() > 0.5 ? (BlockState)blockState2.setValue(TYPE, SlabType.TOP) : blockState2;
		}
	}

	protected boolean shouldDecay(BlockState state) {
		return !(Boolean)state.getValue(PERSISTENT) && (Integer)state.getValue(DISTANCE) == 7;
	}

	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		world.setBlock(pos, updateDistanceFromLogs(state, world, pos), 3);
	}

	public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
		return 1;
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		int i = getDistanceFromLog(neighborState) + 1;
		if (i != 1 || (Integer)state.getValue(DISTANCE) != i) {
			world.scheduleTick(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistanceFromLogs(BlockState state, LevelAccessor world, BlockPos pos) {
		int i = 7;
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		Direction[] var5 = Direction.values();
		int var6 = var5.length;

		for(int var7 = 0; var7 < var6; ++var7) {
			Direction direction = var5[var7];
			mutable.setWithOffset(pos, direction);
			i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
			if (i == 1) {
				break;
			}
		}

		return (BlockState)state.setValue(DISTANCE, i);
	}

	private static int getDistanceFromLog(BlockState state) {
		if (state.is(BlockTags.LOGS)) {
			return 0;
		} else {
			return state.getBlock() instanceof LeavesBlock ? (Integer)state.getValue(DISTANCE) : 7;
		}
	}

	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
		if (world.isRainingAt(pos.above()) && random.nextInt(15) == 1) {
			BlockPos blockPos = pos.below();
			BlockState blockState = world.getBlockState(blockPos);
			if (!blockState.canOcclude() || !blockState.isFaceSturdy(world, blockPos, Direction.UP)) {
				double d = (double)pos.getX() + random.nextDouble();
				double e = (double)pos.getY() - 0.05;
				double f = (double)pos.getZ() + random.nextDouble();
				world.addParticle(ParticleTypes.DRIPPING_WATER, d, e, f, 0.0, 0.0, 0.0);
			}
		}

	}

	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(new Property[]{DISTANCE, PERSISTENT});
		super.createBlockStateDefinition(builder);
	}

	static {
		DISTANCE = BlockStateProperties.DISTANCE;
		PERSISTENT = BlockStateProperties.PERSISTENT;
	}
}
