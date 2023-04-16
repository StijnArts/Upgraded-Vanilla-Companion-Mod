package drai.dev.upgradedvanilla.blocks.dirt;

import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;

import java.util.function.*;
import java.util.stream.*;

public class PathBlockStairs extends StairBlock {
	protected static final VoxelShape TOP_SHAPE = Block.box(0.0, 7.0, 0.0, 16.0, 15.0, 16.0);
	protected static final VoxelShape BOTTOM_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
	protected static final VoxelShape BOTTOM_NORTH_WEST_CORNER_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 7.0, 8.0);
	protected static final VoxelShape BOTTOM_SOUTH_WEST_CORNER_SHAPE = Block.box(0.0, 0.0, 8.0, 8.0, 7.0, 16.0);
	protected static final VoxelShape TOP_NORTH_WEST_CORNER_SHAPE = Block.box(0.0, 7.0, 0.0, 8.0, 15.0, 8.0);
	protected static final VoxelShape TOP_SOUTH_WEST_CORNER_SHAPE = Block.box(0.0, 7.0, 8.0, 8.0, 15.0, 16.0);
	protected static final VoxelShape BOTTOM_NORTH_EAST_CORNER_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 7.0, 8.0);
	protected static final VoxelShape BOTTOM_SOUTH_EAST_CORNER_SHAPE = Block.box(8.0, 0.0, 8.0, 16.0, 7.0, 16.0);
	protected static final VoxelShape TOP_NORTH_EAST_CORNER_SHAPE = Block.box(8.0, 7.0, 0.0, 16.0, 15.0, 8.0);
	protected static final VoxelShape TOP_SOUTH_EAST_CORNER_SHAPE = Block.box(8.0, 7.0, 8.0, 16.0, 15.0, 16.0);
	protected static final VoxelShape[] TOP_SHAPES;
	protected static final VoxelShape[] BOTTOM_SHAPES;
	private static final int[] SHAPE_INDICES = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
	public PathBlockStairs(BlockBehaviour.Properties settings) {
		super(Blocks.DIRT.defaultBlockState(), settings);
	}

	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		if (!this.defaultBlockState().canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
			return Block.pushEntitiesUp(this.defaultBlockState(), Dirt.DIRT_STAIRS.getStateForPlacement(ctx), ctx.getLevel(), ctx.getClickedPos());
		} else {
			Direction direction = ctx.getClickedFace();
			BlockPos blockPos = ctx.getClickedPos();
			FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
			BlockState blockState = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection())).setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getClickLocation().y - (double)blockPos.getY() > 0.5)) ? Half.BOTTOM : Half.TOP)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
			return (BlockState)blockState.setValue(SHAPE, getStairShape(blockState, ctx.getLevel(), blockPos));
		}
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return (state.getValue(HALF) == Half.TOP ? TOP_SHAPES : BOTTOM_SHAPES)[SHAPE_INDICES[((StairsShape)state.getValue(SHAPE)).ordinal() * 4 + ((Direction)state.getValue(FACING)).get2DDataValue()]];
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canSurvive(world, pos)) {
			world.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		world.setBlockAndUpdate(pos, pushEntitiesUp(state, Dirt.DIRT_STAIRS.withPropertiesOf(state), world, pos));
	}

	private static VoxelShape[] composeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
		return (VoxelShape[]) IntStream.range(0, 16).mapToObj((i) -> {
			return composeShape(i, base, northWest, northEast, southWest, southEast);
		}).toArray((x$0) -> {
			return new VoxelShape[x$0];
		});
	}

	private static VoxelShape composeShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
		VoxelShape voxelShape = base;
		if ((i & 1) != 0) {
			voxelShape = Shapes.or(base, northWest);
		}

		if ((i & 2) != 0) {
			voxelShape = Shapes.or(voxelShape, northEast);
		}

		if ((i & 4) != 0) {
			voxelShape = Shapes.or(voxelShape, southWest);
		}

		if ((i & 8) != 0) {
			voxelShape = Shapes.or(voxelShape, southEast);
		}

		return voxelShape;
	}

	private static StairsShape getStairShape(BlockState state, BlockGetter world, BlockPos pos) {
		Direction direction = (Direction)state.getValue(FACING);
		BlockState blockState = world.getBlockState(pos.relative(direction));
		Direction direction2;
		if (StairBlock.isStairs(blockState) && state.getValue(HALF) == blockState.getValue(HALF) && (direction2 = (Direction)blockState.getValue(FACING)).getAxis() != ((Direction)state.getValue(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction2.getOpposite())) {
			return direction2 == direction.getCounterClockWise() ? StairsShape.OUTER_LEFT : StairsShape.OUTER_RIGHT;
		} else {
			BlockState blockState2 = world.getBlockState(pos.relative(direction.getOpposite()));
			Direction direction3;
			if (StairBlock.isStairs(blockState2) && state.getValue(HALF) == blockState2.getValue(HALF) && (direction3 = (Direction)blockState2.getValue(FACING)).getAxis() != ((Direction)state.getValue(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction3)) {
				return direction3 == direction.getCounterClockWise() ? StairsShape.INNER_LEFT : StairsShape.INNER_RIGHT;
			} else {
				return StairsShape.STRAIGHT;
			}
		}
	}

	private static boolean isDifferentOrientation(BlockState state, BlockGetter world, BlockPos pos, Direction dir) {
		BlockState blockState = world.getBlockState(pos.relative(dir));
		return !StairBlock.isStairs(blockState) || blockState.getValue(FACING) != state.getValue(FACING) || blockState.getValue(HALF) != state.getValue(HALF);
	}

	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos.above());
		return !blockState.getMaterial().isSolid() || blockState.getBlock() instanceof FenceGateBlock;
	}

	static {
		TOP_SHAPES = composeShapes(TOP_SHAPE, BOTTOM_NORTH_WEST_CORNER_SHAPE, BOTTOM_NORTH_EAST_CORNER_SHAPE, BOTTOM_SOUTH_WEST_CORNER_SHAPE, BOTTOM_SOUTH_EAST_CORNER_SHAPE);
		BOTTOM_SHAPES = composeShapes(BOTTOM_SHAPE, TOP_NORTH_WEST_CORNER_SHAPE, TOP_NORTH_EAST_CORNER_SHAPE, TOP_SOUTH_WEST_CORNER_SHAPE, TOP_SOUTH_EAST_CORNER_SHAPE);
	}
}
