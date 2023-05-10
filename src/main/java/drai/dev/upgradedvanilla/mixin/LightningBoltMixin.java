package drai.dev.upgradedvanilla.mixin;

import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin {


	@Shadow
	protected abstract BlockPos getStrikePosition();

	@Inject(
			method = {"powerLightningRod"},
			at = {@At("RETURN")},
			cancellable = true
	)
	private void supports(CallbackInfo ci) {
		BlockPos blockPos = this.getStrikePosition();
		BlockState  blockState = ((LightningBolt)(Object)this).level.getBlockState(blockPos);
		if(blockState.getBlock() instanceof LightningRodBlock){
			((LightningRodBlock)blockState.getBlock()).onLightningStrike(blockState, ((LightningBolt)(Object)this).level, blockPos);
		}

	}
}
