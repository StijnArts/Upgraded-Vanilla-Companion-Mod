package drai.dev.upgradedvanilla.mixin;

import games.twinhead.moreslabsstairsandwalls.block.culled.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;

@Mixin({StainedGlassBlock.class})
public class StainedGlassBlockMixin extends AbstractGlassBlock {

	@Final
	private DyeColor field_11558;

	private StainedGlassBlockMixin(BlockBehaviour.Properties block$Settings_1) {
		super(block$Settings_1);
	}

	public boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
		Block block = stateFrom.getBlock();
		if (block instanceof CulledStainedSlabBlock && ((CulledStainedSlabBlock)block).getColor() == this.field_11558 && this.isInvisibleToGlassSlab(stateFrom, direction)) {
			return true;
		} else {
			return block instanceof CulledStainedStairsBlock && ((CulledStainedStairsBlock)block).getColor() == this.field_11558 && this.isInvisibleToGlassStairs(stateFrom, direction) ? true : super.skipRendering(state, stateFrom, direction);
		}
	}

	private boolean isInvisibleToGlassSlab(BlockState state, Direction direction) {
		SlabType type = (SlabType)state.getValue(SlabBlock.TYPE);
		if (type == SlabType.DOUBLE) {
			return true;
		} else if (direction == Direction.UP && type != SlabType.TOP) {
			return true;
		} else {
			return direction == Direction.DOWN && type != SlabType.BOTTOM;
		}
	}

	private boolean isInvisibleToGlassStairs(BlockState state, Direction direction) {
		Half half2 = (Half)state.getValue(StairBlock.HALF);
		Direction facing2 = (Direction)state.getValue(StairBlock.FACING);
		if (direction == Direction.UP && half2 == Half.BOTTOM) {
			return true;
		} else if (direction == Direction.DOWN && half2 == Half.TOP) {
			return true;
		} else {
			return facing2 == direction.getOpposite();
		}
	}
}
