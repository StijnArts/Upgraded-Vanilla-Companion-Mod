package drai.dev.upgradedvanilla.mixin.registration;




import drai.dev.upgradedvanilla.modules.betterend.*;
import drai.dev.upgradedvanilla.registry.*;

import org.betterx.betterend.registry.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(org.betterx.betterend.BetterEnd.class)
public class BetterEndBlocksMixin {
	@Inject(method = "onInitialize", at = @At("TAIL"), remap = false)
	private void initializeCompat(CallbackInfo ci) {
		BetterEnd.register();
		CombinationRegistry.getSourceMod("betterend").setInitialized();
		CombinationRegistry.registerCombinationBlocks();

	}
}
