package drai.dev.upgradedvanilla.mixin;

import com.google.common.base.*;

import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(TripWireHookBlock.class)
public abstract class TripwireHookBlockMixin {

	@Shadow
	protected abstract void notifyNeighbors(Level level, BlockPos pos, Direction direction);

	@Shadow
	protected abstract void emitState(Level world, BlockPos pos, boolean attached, boolean on, boolean detached, boolean off);

	@Inject(method = "calculateState", at=@At("HEAD"), cancellable = true)
	private void calculateState(Level level, BlockPos pos, BlockState hookState, boolean attaching, boolean shouldNotifyNeighbours, int searchRange, BlockState state, CallbackInfo ci) {
		BlockPos blockPos;
		Direction direction = hookState.getValue(TripWireHookBlock.FACING);
		boolean bl = hookState.getValue(TripWireHookBlock.ATTACHED);
		boolean bl2 = hookState.getValue(TripWireHookBlock.POWERED);
		boolean bl3 = !attaching;
		boolean bl4 = false;
		int i = 0;
		BlockState[] blockStates = new BlockState[42];
		for (int j = 1; j < 42; ++j) {
			blockPos = pos.relative(direction, j);
			BlockState blockState = level.getBlockState(blockPos);
			if (blockState.getBlock() instanceof TripWireHookBlock) {
				if (blockState.getValue(TripWireHookBlock.FACING) != direction.getOpposite()) break;
				i = j;
				break;
			}
			if (blockState.is(Blocks.TRIPWIRE) || j == searchRange) {
				if (j == searchRange) {
					blockState = MoreObjects.firstNonNull(state, blockState);
				}
				boolean bl5 = !blockState.getValue(TripWireBlock.DISARMED);
				boolean bl6 = blockState.getValue(TripWireBlock.POWERED);
				bl4 |= bl5 && bl6;
				blockStates[j] = blockState;
				if (j != searchRange) continue;
				((LevelAccessor)level).scheduleTick(pos, ((TripWireHookBlock)(Object)this), 10);
				bl3 &= bl5;
				continue;
			}
			blockStates[j] = null;
			bl3 = false;
		}
		BlockState blockState2 = (BlockState)((BlockState)((TripWireHookBlock)(Object)this).defaultBlockState()
				.setValue(TripWireHookBlock.ATTACHED, bl3)).setValue(TripWireHookBlock.POWERED, bl4 &= (bl3 &= i > 1));
		if (i > 0) {
			blockPos = pos.relative(direction, i);
			Direction direction2 = direction.getOpposite();
			level.setBlock(blockPos, (BlockState)blockState2.setValue(TripWireHookBlock.FACING, direction2), 3);
			this.notifyNeighbors(level, blockPos, direction2);
			this.emitState(level, blockPos, bl3, bl4, bl, bl2);
		}
		this.emitState(level, pos, bl3, bl4, bl, bl2);
		if (!attaching) {
			level.setBlock(pos, (BlockState)blockState2.setValue(TripWireHookBlock.FACING, direction), 3);
			if (shouldNotifyNeighbours) {
				this.notifyNeighbors(level, pos, direction);
			}
		}
		if (bl != bl3) {
			for (int k = 1; k < i; ++k) {
				BlockPos blockPos2 = pos.relative(direction, k);
				BlockState blockState3 = blockStates[k];
				if (blockState3 == null) continue;
				level.setBlock(blockPos2, (BlockState)blockState3.setValue(TripWireHookBlock.ATTACHED, bl3), 3);
				if (level.getBlockState(blockPos2).isAir()) continue;
			}
		}
		ci.cancel();
	}
}
