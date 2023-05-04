package drai.dev.upgradedvanilla.blocks.culled;

import net.fabricmc.api.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

public class CulledSlabBlock extends SlabBlock {
	public CulledSlabBlock(BlockBehaviour.Properties settings) {
		super(settings);
	}

	@Environment(EnvType.CLIENT)
	public boolean skipRendering(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
		if (blockState_2.getBlock() instanceof GlassBlock) {
			return true;
		} else if (blockState_2.getBlock() == this && this.isInvisibleToGlassSlab(blockState_1, blockState_2, direction_1)) {
			return true;
		} else {
			return (blockState_2.getBlock() instanceof CulledStairBlock) && this.isInvisibleToGlassStairs(blockState_1, blockState_2, direction_1) ? true : super.skipRendering(blockState_1, blockState_2, direction_1);
		}
	}

	private boolean isInvisibleToGlassSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
		SlabType type1 = (SlabType)blockState_1.getValue(SlabBlock.TYPE);
		SlabType type2 = (SlabType)blockState_2.getValue(SlabBlock.TYPE);
		if (type2 == SlabType.DOUBLE) {
			return true;
		} else {
			switch (direction_1) {
				case UP:
				case DOWN:
					if (type1 != type2) {
						return true;
					}
					break;
				case NORTH:
				case EAST:
				case SOUTH:
				case WEST:
					if (type1 == type2) {
						return true;
					}
			}

			return false;
		}
	}

	private boolean isInvisibleToGlassStairs(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
		SlabType type1 = (SlabType)blockState_1.getValue(SlabBlock.TYPE);
		Half half2 = (Half)blockState_2.getValue(StairBlock.HALF);
		Direction facing2 = (Direction)blockState_2.getValue(StairBlock.FACING);
		if (direction_1 == Direction.UP && half2 == Half.BOTTOM) {
			return true;
		} else if (direction_1 == Direction.DOWN && half2 == Half.TOP) {
			return true;
		} else if (facing2 == direction_1.getOpposite()) {
			return true;
		} else if (direction_1.get2DDataValue() != -1) {
			if (type1 == SlabType.BOTTOM && half2 == Half.BOTTOM) {
				return true;
			} else {
				return type1 == SlabType.TOP && half2 == Half.TOP;
			}
		} else {
			return false;
		}
	}
}
