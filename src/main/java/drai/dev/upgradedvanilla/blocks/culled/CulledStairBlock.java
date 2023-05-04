package drai.dev.upgradedvanilla.blocks.culled;

import net.fabricmc.api.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

public class CulledStairBlock extends StairBlock {
	public CulledStairBlock(BlockState baseBlockState, BlockBehaviour.Properties settings) {
		super(baseBlockState, settings);
	}

	@Environment(EnvType.CLIENT)
	public boolean skipRendering(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
		if (blockState_2.getBlock() == Blocks.GLASS) {
			return true;
		} else if (blockState_2.getBlock() instanceof CulledSlabBlock && this.isInvisibleToGlassSlab(blockState_1, blockState_2, direction_1)) {
			return true;
		} else {
			return blockState_2.getBlock() == this && this.isInvisibleToGlassStairs(blockState_1, blockState_2, direction_1) ? true : super.skipRendering(blockState_1, blockState_2, direction_1);
		}
	}

	private boolean isInvisibleToGlassSlab(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
		Half half1 = (Half)blockState_1.getValue(StairBlock.HALF);
		Direction facing1 = (Direction)blockState_1.getValue(StairBlock.FACING);
		StairsShape shape1 = (StairsShape)blockState_1.getValue(StairBlock.SHAPE);
		SlabType type2 = (SlabType)blockState_2.getValue(SlabBlock.TYPE);
		if (direction_1 == Direction.UP && type2 != SlabType.TOP) {
			return true;
		} else if (direction_1 == Direction.DOWN && type2 != SlabType.BOTTOM) {
			return true;
		} else if (type2 == SlabType.DOUBLE) {
			return true;
		} else {
			if (direction_1 == facing1.getOpposite()) {
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM) {
					return true;
				}

				if (type2 == SlabType.TOP && half1 == Half.TOP) {
					return true;
				}
			}

			if (direction_1 == facing1.getClockWise() && shape1 == StairsShape.OUTER_LEFT) {
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM) {
					return true;
				}

				if (type2 == SlabType.TOP && half1 == Half.TOP) {
					return true;
				}
			}

			if (direction_1 == facing1.getCounterClockWise() && shape1 == StairsShape.OUTER_RIGHT) {
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM) {
					return true;
				} else {
					return type2 == SlabType.TOP && half1 == Half.TOP;
				}
			} else {
				return false;
			}
		}
	}

	private boolean isInvisibleToGlassStairs(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
		Half half1 = (Half)blockState_1.getValue(StairBlock.HALF);
		Half half2 = (Half)blockState_2.getValue(StairBlock.HALF);
		Direction facing1 = (Direction)blockState_1.getValue(StairBlock.FACING);
		Direction facing2 = (Direction)blockState_2.getValue(StairBlock.FACING);
		StairsShape shape1 = (StairsShape)blockState_1.getValue(StairBlock.SHAPE);
		StairsShape shape2 = (StairsShape)blockState_2.getValue(StairBlock.SHAPE);
		if (direction_1 == Direction.UP) {
			if (half2 == Half.BOTTOM) {
				return true;
			}

			if (half1 != half2) {
				if (facing1 == facing2 && shape1 == shape2) {
					return true;
				}

				switch (shape1) {
					case STRAIGHT:
						if (shape2 == StairsShape.INNER_LEFT && (facing2 == facing1 || facing2 == facing1.getClockWise())) {
							return true;
						}

						if (shape2 == StairsShape.INNER_RIGHT && (facing2 == facing1 || facing2 == facing1.getCounterClockWise())) {
							return true;
						}
						break;
					case INNER_LEFT:
						if (shape2 == StairsShape.INNER_RIGHT && facing2 == facing1.getCounterClockWise()) {
							return true;
						}
						break;
					case INNER_RIGHT:
						if (shape2 == StairsShape.INNER_LEFT && facing2 == facing1.getClockWise()) {
							return true;
						}
						break;
					case OUTER_LEFT:
						if (shape2 == StairsShape.OUTER_RIGHT && facing2 == facing1.getCounterClockWise()) {
							return true;
						}

						if (shape2 != StairsShape.STRAIGHT || facing2 != facing1 && facing2 != facing1.getCounterClockWise()) {
							break;
						}

						return true;
					case OUTER_RIGHT:
						if (shape2 == StairsShape.OUTER_LEFT && facing2 == facing1.getClockWise()) {
							return true;
						}

						if (shape2 == StairsShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.getClockWise())) {
							return true;
						}
				}
			}
		}

		if (direction_1 == Direction.DOWN) {
			if (half2 == Half.TOP) {
				return true;
			}

			switch (shape1) {
				case STRAIGHT:
					if (shape2 == StairsShape.INNER_LEFT && (facing2 == facing1 || facing2 == facing1.getClockWise())) {
						return true;
					}

					if (shape2 == StairsShape.INNER_RIGHT && (facing2 == facing1 || facing2 == facing1.getCounterClockWise())) {
						return true;
					}
					break;
				case INNER_LEFT:
					if (shape2 == StairsShape.INNER_RIGHT && facing2 == facing1.getCounterClockWise()) {
						return true;
					}
					break;
				case INNER_RIGHT:
					if (shape2 == StairsShape.INNER_LEFT && facing2 == facing1.getClockWise()) {
						return true;
					}
					break;
				case OUTER_LEFT:
					if (shape2 == StairsShape.OUTER_RIGHT && facing2 == facing1.getCounterClockWise()) {
						return true;
					}

					if (shape2 != StairsShape.STRAIGHT || facing2 != facing1 && facing2 != facing1.getCounterClockWise()) {
						break;
					}

					return true;
				case OUTER_RIGHT:
					if (shape2 == StairsShape.OUTER_LEFT && facing2 == facing1.getClockWise()) {
						return true;
					}

					if (shape2 == StairsShape.STRAIGHT && (facing2 == facing1 || facing2 == facing1.getClockWise())) {
						return true;
					}
			}
		}

		if (facing2 == direction_1.getOpposite()) {
			return true;
		} else {
			if (direction_1 == facing1 && half1 == half2 && shape1 != StairsShape.STRAIGHT) {
				if (facing2 == facing1.getCounterClockWise() && shape2 != StairsShape.OUTER_RIGHT) {
					return true;
				}

				if (facing2 == facing1.getClockWise() && shape2 != StairsShape.OUTER_LEFT) {
					return true;
				}
			}

			if (direction_1 == facing1.getOpposite() && half1 == half2) {
				if (facing2 == facing1.getCounterClockWise() && shape2 != StairsShape.OUTER_LEFT) {
					return true;
				}

				if (facing2 == facing1.getClockWise() && shape2 != StairsShape.OUTER_RIGHT) {
					return true;
				}

				if (facing2 == facing1.getOpposite()) {
					return true;
				}
			}

			if (direction_1 == facing1.getCounterClockWise() && half1 == half2) {
				if (facing2 == direction_1 && shape1 != StairsShape.INNER_LEFT && shape2 == StairsShape.INNER_RIGHT) {
					return true;
				}

				if (facing2 == facing1 && shape2 != StairsShape.OUTER_LEFT) {
					return true;
				}

				if (facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_RIGHT) {
					return true;
				}
			}

			if (direction_1 == facing1.getClockWise() && half1 == half2) {
				if (facing2 == direction_1 && shape1 != StairsShape.INNER_RIGHT && shape2 == StairsShape.INNER_LEFT) {
					return true;
				} else if (facing2 == facing1 && shape2 != StairsShape.OUTER_RIGHT) {
					return true;
				} else {
					return facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_LEFT;
				}
			} else {
				return false;
			}
		}
	}
}
