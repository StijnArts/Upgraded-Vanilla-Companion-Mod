package drai.dev.upgradedvanilla.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;

import drai.dev.upgradedvanilla.blocks.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.*;

import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(PoweredRailBlock.class)
public abstract class PoweredRailBlockMixin {
	@Shadow
	protected abstract boolean findPoweredRailSignal(Level level, BlockPos pos, BlockState state, boolean searchForward, int recursionCount);
	@Inject(method = "isSameRailWithPower", at=@At("RETURN"), cancellable = true)
	public void isSameRailWithPowerVariant(Level level, BlockPos state, boolean searchForward, int recursionCount, RailShape shape, CallbackInfoReturnable<Boolean> cir) {
		BlockState railState = level.getBlockState(state);
		if(!(railState.getBlock() instanceof VariantPoweredRailBlock)) {
			cir.setReturnValue(false);
			cir.cancel();
		} else {
			RailShape railShape = railState.getValue(PoweredRailBlock.SHAPE);
			if (shape == RailShape.EAST_WEST && (railShape == RailShape.NORTH_SOUTH || railShape == RailShape.ASCENDING_NORTH || railShape == RailShape.ASCENDING_SOUTH)) {
				cir.setReturnValue(false);
				cir.cancel();
			}
			if (shape == RailShape.NORTH_SOUTH && (railShape == RailShape.EAST_WEST || railShape == RailShape.ASCENDING_EAST || railShape == RailShape.ASCENDING_WEST)) {
				cir.setReturnValue(false);
				cir.cancel();
			}
			if (railState.getValue(PoweredRailBlock.POWERED).booleanValue()) {
				if (level.hasNeighborSignal(state)) {
					cir.setReturnValue(true);
					cir.cancel();
				} else{
					boolean returnValue = this.findPoweredRailSignal(level, state, railState, searchForward, recursionCount + 1);
					cir.setReturnValue(returnValue);
					cir.cancel();
				}
			}
		}
	}
}

