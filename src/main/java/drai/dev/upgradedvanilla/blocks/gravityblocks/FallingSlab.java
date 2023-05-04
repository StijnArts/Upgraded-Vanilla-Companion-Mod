package drai.dev.upgradedvanilla.blocks.gravityblocks;

import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Material;

import org.jetbrains.annotations.NotNull;

public class FallingSlab extends SlabBlock {
	private final int dustColor;

	public FallingSlab(int dustColor, Block baseBlock) {
		super(FabricBlockSettings.copyOf(baseBlock));
		this.dustColor = dustColor;
	}

	public static boolean isFree(BlockState blockState) {
		final Material material = blockState.getMaterial();
		return blockState.isAir() || blockState.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
	}

	@Override
	public void onPlace(
			@NotNull BlockState blockState,
			Level level,
			BlockPos blockPos,
			BlockState blockState2,
			boolean bl
	) {
		level.scheduleTick(blockPos, this, this.getDelayAfterPlace());
	}

	@Override
	public BlockState updateShape(
			@NotNull BlockState blockState,
			@NotNull Direction direction,
			@NotNull BlockState blockState2,
			LevelAccessor levelAccessor,
			@NotNull BlockPos blockPos,
			@NotNull BlockPos blockPos2
	) {
		levelAccessor.scheduleTick(blockPos, this, this.getDelayAfterPlace());
		return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
	}

	public BlockState makeState(BlockState state, SlabType type) {
		return this.defaultBlockState()
				.setValue(TYPE, type)
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED));
	}

	@Override
	public void tick(@NotNull BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
		final BlockState below = serverLevel.getBlockState(blockPos.below());
		SlabType belowType = (SlabType) below.getValues().get(TYPE);

		if ((FallingBlock.isFree(below) || (belowType != null && belowType == SlabType.BOTTOM)) && blockPos.getY() >= serverLevel.getMinBuildHeight()) {
			BlockState state = serverLevel.getBlockState(blockPos);

			SlabType type = (SlabType) state.getValues().get(TYPE);
			if (belowType == SlabType.BOTTOM && type == SlabType.BOTTOM) {
				state = makeState(state, SlabType.TOP);
			} else if (type == SlabType.TOP) {
				state = makeState(state, SlabType.BOTTOM);
			}

			FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(serverLevel, blockPos, state);

			this.falling(fallingBlockEntity);
			serverLevel.addFreshEntity(fallingBlockEntity);
		}
	}

	protected void falling(FallingBlockEntity fallingBlockEntity) {
	}

	protected int getDelayAfterPlace() {
		return 2;
	}

	@Override
	public void animateTick(
			@NotNull BlockState blockState,
			@NotNull Level level,
			@NotNull BlockPos blockPos,
			RandomSource random
	) {
		BlockPos blockPos2;
		if (random.nextInt(16) == 0 && FallingBlock.isFree(level.getBlockState(blockPos2 = blockPos.below()))) {
			double d = (double) blockPos.getX() + random.nextDouble();
			double e = (double) blockPos.getY() - 0.05;
			double f = (double) blockPos.getZ() + random.nextDouble();
			level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, blockState), d, e, f, 0.0, 0.0, 0.0);
		}
	}
}
