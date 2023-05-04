package drai.dev.upgradedvanilla.blocks.culled;

import games.twinhead.moreslabsstairsandwalls.block.culled.CulledStainedSlabBlock;
import net.fabricmc.api.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

public final class CulledStainedStairBlock extends StairBlock implements BeaconBeamBlock {
	private final DyeColor color;

	public CulledStainedStairBlock(BlockState blockState, DyeColor color, BlockBehaviour.Properties settings) {
		super(blockState, settings);
		this.color = color;
	}

	@Environment(EnvType.CLIENT)
	public boolean skipRendering(BlockState state1, BlockState state2, Direction direction) {
		Block block = state2.getBlock();
		if (block instanceof StainedGlassBlock && ((StainedGlassBlock)block).getColor() == this.color) {
			return true;
		} else if (block instanceof CulledStainedSlabBlock && ((CulledStainedSlabBlock)block).getColor() == this.color && this.slabInvisible(state1, state2, direction)) {
			return true;
		} else {
			return block == this && this.stairsInvisible(state1, state2, direction) ? true : super.skipRendering(state1, state2, direction);
		}
	}

	private boolean slabInvisible(BlockState state1, BlockState state2, Direction direction) {
		Half half1 = (Half)state1.getValue(StairBlock.HALF);
		Direction facing1 = (Direction)state1.getValue(StairBlock.FACING);
		StairsShape shape1 = (StairsShape)state1.getValue(StairBlock.SHAPE);
		SlabType type2 = (SlabType)state2.getValue(SlabBlock.TYPE);
		if (direction == Direction.UP && type2 != SlabType.TOP) {
			return true;
		} else if (direction == Direction.DOWN && type2 != SlabType.BOTTOM) {
			return true;
		} else if (type2 == SlabType.DOUBLE) {
			return true;
		} else {
			if (direction == facing1.getOpposite()) {
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM) {
					return true;
				}

				if (type2 == SlabType.TOP && half1 == Half.TOP) {
					return true;
				}
			}

			if (direction == facing1.getClockWise() && shape1 == StairsShape.OUTER_LEFT) {
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM) {
					return true;
				}

				if (type2 == SlabType.TOP && half1 == Half.TOP) {
					return true;
				}
			}

			if (direction == facing1.getCounterClockWise() && shape1 == StairsShape.OUTER_RIGHT) {
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM) {
					return true;
				}

				if (type2 == SlabType.TOP && half1 == Half.TOP) {
					return true;
				}
			}

			if (direction == facing1.getCounterClockWise() && shape1 == StairsShape.INNER_RIGHT) {
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

	private boolean stairsInvisible(BlockState state1, BlockState state2, Direction direction) {
		Half half1 = (Half)state1.getValue(StairBlock.HALF);
		Half half2 = (Half)state2.getValue(StairBlock.HALF);
		Direction facing1 = (Direction)state1.getValue(StairBlock.FACING);
		Direction facing2 = (Direction)state2.getValue(StairBlock.FACING);
		StairsShape shape1 = (StairsShape)state1.getValue(StairBlock.SHAPE);
		StairsShape shape2 = (StairsShape)state2.getValue(StairBlock.SHAPE);
		if (direction == Direction.UP) {
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

		if (direction == Direction.DOWN) {
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

		if (facing2 == direction.getOpposite()) {
			return true;
		} else {
			if (direction == facing1 && half1 == half2 && shape1 != StairsShape.STRAIGHT) {
				if (facing2 == facing1.getCounterClockWise() && shape2 != StairsShape.OUTER_RIGHT) {
					return true;
				}

				if (facing2 == facing1.getClockWise() && shape2 != StairsShape.OUTER_LEFT) {
					return true;
				}
			}

			if (direction == facing1.getOpposite() && half1 == half2) {
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

			if (direction == facing1.getCounterClockWise() && half1 == half2) {
				if (facing2 == direction && shape1 != StairsShape.INNER_LEFT && shape2 == StairsShape.INNER_RIGHT) {
					return true;
				}

				if (facing2 == facing1 && shape2 != StairsShape.OUTER_LEFT) {
					return true;
				}

				if (facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_RIGHT) {
					return true;
				}
			}

			if (direction == facing1.getClockWise() && half1 == half2) {
				if (facing2 == direction && shape1 != StairsShape.INNER_RIGHT && shape2 == StairsShape.INNER_LEFT) {
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

	public DyeColor getColor() {
		return this.color;
	}
}
