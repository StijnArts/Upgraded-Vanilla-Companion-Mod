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

public class PathBlockSlab extends SlabBlock {
	public static final VoxelShape BOTTOM_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
	public static final VoxelShape TOP_SHAPE = Block.box(0.0, 7.0, 0.0, 16.0, 15.0, 16.0);
	public static final VoxelShape FULL_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

	public PathBlockSlab(BlockBehaviour.Properties settings) {
		super(settings);
	}
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		if (!this.defaultBlockState().canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
			return Block.pushEntitiesUp(this.defaultBlockState(), Dirt.DIRT_SLAB.getStateForPlacement(ctx), ctx.getLevel(), ctx.getClickedPos());
		} else {
			BlockPos blockPos = ctx.getClickedPos();
			BlockState blockState = ctx.getLevel().getBlockState(blockPos);
			if (blockState.is(this)) {
				return (BlockState)((BlockState)blockState.setValue(TYPE, SlabType.DOUBLE)).setValue(WATERLOGGED, false);
			} else {
				FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
				BlockState blockState2 = (BlockState)((BlockState)this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
				Direction direction = ctx.getClickedFace();
				return direction == Direction.DOWN || direction != Direction.UP && ctx.getClickLocation().y - (double)blockPos.getY() > 0.5 ? (BlockState)blockState2.setValue(TYPE, SlabType.TOP) : blockState2;
			}
		}
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		SlabType slabType = (SlabType)state.getValue(TYPE);
		VoxelShape var10000;
		switch (slabType) {
			case DOUBLE:
				var10000 = FULL_SHAPE;
				break;
			case TOP:
				var10000 = TOP_SHAPE;
				break;
			default:
				var10000 = BOTTOM_SHAPE;
		}

		return var10000;
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canSurvive(world, pos)) {
			world.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		world.setBlockAndUpdate(pos, pushEntitiesUp(state, Dirt.DIRT_SLAB.defaultBlockState().setValue(TYPE, (SlabType)world.getBlockState(pos).getValue(TYPE)), world, pos));
	}

	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos.above());
		return !blockState.getMaterial().isSolid() || blockState.getBlock() instanceof FenceGateBlock;
	}
}
