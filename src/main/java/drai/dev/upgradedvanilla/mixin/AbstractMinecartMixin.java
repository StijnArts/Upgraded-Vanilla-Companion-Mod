package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import net.minecraft.world.phys.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

	@Shadow
	protected abstract boolean isRedstoneConductor(BlockPos pos);

	@Inject(
			method = {"moveAlongTrack"},
			at = {@At("HEAD")},
			cancellable = true
	)
	public void allPoweredRails(BlockPos pos, BlockState state, CallbackInfo ci) {
		boolean poweredRail = false;
		boolean poweredRail2 = false;

		if (state.getBlock() instanceof PoweredRailBlock) {
			poweredRail = state.getValue(PoweredRailBlock.POWERED);
			poweredRail2 = !poweredRail;
		}
		if (poweredRail2) {
			double o = ((AbstractMinecart)(Object)this).getDeltaMovement().horizontalDistance();
			if (o < 0.03) {
				((AbstractMinecart)(Object)this).setDeltaMovement(Vec3.ZERO);
			} else {
				((AbstractMinecart)(Object)this).setDeltaMovement(((AbstractMinecart)(Object)this).getDeltaMovement().multiply(0.5, 0.0, 0.5));
			}
		}
		if (poweredRail) {
			Vec3 vector35 = ((AbstractMinecart)(Object)this).getDeltaMovement();
			double horizontalDistance = vector35.horizontalDistance();
			RailShape poweredRailShape = state.getValue(((BaseRailBlock)state.getBlock()).getShapeProperty());
			if (horizontalDistance > 0.01) {
				double z = 0.06;
				((AbstractMinecart)(Object)this).setDeltaMovement(vector35.add(vector35.x / horizontalDistance * 0.06, 0.0, vector35.z / horizontalDistance * 0.06));
			} else {
				Vec3 vec36 = ((AbstractMinecart)(Object)this).getDeltaMovement();
				double aa = vec36.x;
				double ab = vec36.z;
				if (poweredRailShape == RailShape.EAST_WEST) {
					if (this.isRedstoneConductor(pos.west())) {
						aa = 0.02;
					} else if (this.isRedstoneConductor(pos.east())) {
						aa = -0.02;
					}
				} else if (poweredRailShape == RailShape.NORTH_SOUTH) {
					if (this.isRedstoneConductor(pos.north())) {
						ab = 0.02;
					} else if (this.isRedstoneConductor(pos.south())) {
						ab = -0.02;
					}
				} else {
					return;
				}
				((AbstractMinecart)(Object)this).setDeltaMovement(aa, vec36.y, ab);
			}
		}
	}

	@Inject(
			method = "moveAlongTrack",
			at = {@At("TAIL")},
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void allActivatorRails(BlockPos pos, BlockState state, CallbackInfo ci) {
		if (!state.is(Blocks.ACTIVATOR_RAIL) && state.getBlock() instanceof VariantActivatorRailBlock) {
			((AbstractMinecart)(Object)this).activateMinecart(pos.getX(), pos.getY(), pos.getZ(), state.getValue(PoweredRailBlock.POWERED));
		}
	}
}
