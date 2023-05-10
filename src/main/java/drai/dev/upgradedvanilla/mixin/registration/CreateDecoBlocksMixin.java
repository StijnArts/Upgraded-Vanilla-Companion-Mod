package drai.dev.upgradedvanilla.mixin.registration;


import com.github.talrey.createdeco.*;

import drai.dev.upgradedvanilla.modules.betterend.*;
import drai.dev.upgradedvanilla.modules.createdeco.*;
import drai.dev.upgradedvanilla.registry.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(CreateDecoMod.class)
public class CreateDecoBlocksMixin {
	@Inject(method = "onInitialize", at = @At("TAIL"), remap = false)
	private void initializeCompat(CallbackInfo ci) {
		CreateDeco.register();
		CombinationRegistry.getSourceMod("createdeco").setInitialized();
		CombinationRegistry.registerCombinationBlocks();
	}
}
