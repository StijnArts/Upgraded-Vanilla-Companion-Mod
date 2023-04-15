package drai.dev.upgradedvanilla.blocks;

import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.*;

public class RedstoneOreStairsBlock extends StairBlock {
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	private final BlockState baseState;
	/**
	 * Access widened by fabric-transitive-access-wideners-v1 to accessible
	 *
	 * @param baseState
	 * @param properties
	 */
	public RedstoneOreStairsBlock(BlockState baseState, Properties properties) {
		super(baseState, properties);
		this.baseState = baseState;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (level.isClientSide) {
			RedstoneOreStairsBlock.spawnParticles(level, pos);
		} else {
			RedstoneOreStairsBlock.interact(state, level, pos);
		}
		return this.baseState.use(level, player, hand, hit);
	}
	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(LIT);
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
		super.spawnAfterBreak(state, level, pos, stack, dropExperience);
		if (dropExperience && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			int i = 1 + level.random.nextInt(5);
			this.popExperience(level, pos, i);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(LIT).booleanValue()) {
			level.setBlock(pos, (BlockState)state.setValue(LIT, false), 3);
		}
	}
	private static void spawnParticles(Level level, BlockPos pos) {
		double d = 0.5625;
		RandomSource randomSource = level.random;
		for (Direction direction : Direction.values()) {
			BlockPos blockPos = pos.relative(direction);
			if (level.getBlockState(blockPos).isSolidRender(level, blockPos)) continue;
			Direction.Axis axis = direction.getAxis();
			double e = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getStepX() : (double)randomSource.nextFloat();
			double f = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction.getStepY() : (double)randomSource.nextFloat();
			double g = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getStepZ() : (double)randomSource.nextFloat();
			level.addParticle(DustParticleOptions.REDSTONE, (double)pos.getX() + e, (double)pos.getY() + f, (double)pos.getZ() + g, 0.0, 0.0, 0.0);
		}
	}

	private static void interact(BlockState state, Level level, BlockPos pos) {
		RedstoneOreStairsBlock.spawnParticles(level, pos);
		if (!state.getValue(LIT).booleanValue()) {
			level.setBlock(pos, (BlockState)state.setValue(LIT, true), 3);
		}
	}
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (state.getValue(LIT).booleanValue()) {
			RedstoneOreStairsBlock.spawnParticles(level, pos);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}
