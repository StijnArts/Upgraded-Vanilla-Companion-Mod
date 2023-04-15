package drai.dev.upgradedvanilla.mixin;

import net.minecraft.core.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(TripWireBlock.class)
public class TripwireBlockMixin {

	@Inject(method = "updateSource", at=@At("RETURN"))
	private void updateSource(Level level, BlockPos pos, BlockState state, CallbackInfo ci) {
		block0: for (Direction direction : new Direction[]{Direction.SOUTH, Direction.WEST}) {
			for (int i = 1; i < 42; ++i) {
				BlockPos blockPos = pos.relative(direction, i);
				BlockState blockState = level.getBlockState(blockPos);
				if (blockState.getBlock() instanceof TripWireHookBlock) {
					if (blockState.getValue(TripWireHookBlock.FACING) != direction.getOpposite()) continue block0;
					((TripWireHookBlock)blockState.getBlock()).calculateState(level, blockPos, blockState, false, true, i, state);
					continue block0;
				}
				if (!(blockState.getBlock() instanceof TripWireHookBlock)) continue block0;
			}
		}
	}

	@Inject(method = "shouldConnectTo", at=@At("RETURN"), cancellable = true)
	public void shouldConnectTo(BlockState state, Direction direction, CallbackInfoReturnable<Boolean> cir) {
		if (state.getBlock() instanceof TripWireHookBlock) {
			cir.setReturnValue(state.getValue(TripWireHookBlock.FACING) == direction.getOpposite());
		}
		cir.setReturnValue(state.is(((TripWireBlock)(Object)this)));
	}
}
