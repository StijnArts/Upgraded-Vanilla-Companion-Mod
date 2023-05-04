package drai.dev.upgradedvanilla.blocks.dirt;

import drai.dev.upgradedvanilla.modules.minecraft.soil.dirt.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

public class PathBlockWall extends WallBlock {
	public PathBlockWall(BlockBehaviour.Properties settings) {
		super(settings);
	}

	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return !this.defaultBlockState().canSurvive(ctx.getLevel(), ctx.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(),
				Dirt.DIRT_WALL.getStateForPlacement(ctx), ctx.getLevel(), ctx.getClickedPos()) : super.getStateForPlacement(ctx);
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canSurvive(world, pos)) {
			world.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		world.setBlockAndUpdate(pos, pushEntitiesUp(state, Dirt.DIRT_WALL.withPropertiesOf(state), world, pos));
	}

	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos.above());
		return !blockState.getMaterial().isSolid() || blockState.getBlock() instanceof FenceGateBlock;
	}
}
