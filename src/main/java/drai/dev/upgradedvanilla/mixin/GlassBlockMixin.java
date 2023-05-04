package drai.dev.upgradedvanilla.mixin;

import games.twinhead.moreslabsstairsandwalls.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;

@Mixin({GlassBlock.class})
public class GlassBlockMixin extends AbstractGlassBlock {
	protected GlassBlockMixin(BlockBehaviour.Properties settings) {
		super(settings);
	}

	public boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
		if (stateFrom.getBlock() == ModBlocks.GLASS.getSlabBlock() && this.isInvisibleToGlassSlab(stateFrom, direction)) {
			return true;
		} else {
			return stateFrom.getBlock() == ModBlocks.GLASS.getStairsBlock() && this.isInvisibleToGlassStairs(stateFrom, direction) ? true : super.skipRendering(state, stateFrom, direction);
		}
	}

	private boolean isInvisibleToGlassSlab(BlockState slabState, Direction direction_1) {
		SlabType type2 = (SlabType)slabState.getValue(SlabBlock.TYPE);
		if (type2 == SlabType.DOUBLE) {
			return true;
		} else if (direction_1 == Direction.UP && type2 != SlabType.TOP) {
			return true;
		} else if (direction_1 == Direction.DOWN) {
			return type2 != SlabType.BOTTOM;
		} else {
			return false;
		}
	}

	private boolean isInvisibleToGlassStairs(BlockState stairState, Direction direction_1) {
		Half half2 = (Half)stairState.getValue(StairBlock.HALF);
		Direction facing2 = (Direction)stairState.getValue(StairBlock.FACING);
		if (direction_1 == Direction.UP && half2 == Half.BOTTOM) {
			return true;
		} else if (direction_1 == Direction.DOWN && half2 == Half.TOP) {
			return true;
		} else {
			return facing2 == direction_1.getOpposite();
		}
	}
}
