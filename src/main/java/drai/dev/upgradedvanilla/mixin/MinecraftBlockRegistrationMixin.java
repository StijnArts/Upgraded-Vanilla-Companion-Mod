package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.modules.betterend.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import drai.dev.upgradedvanilla.registry.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(net.minecraft.world.level.block.Blocks.class)
public class MinecraftBlockRegistrationMixin {
	@Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
	private static void initializeCompat(CallbackInfo ci) {
		Minecraft.register();
		CombinationRegistry.getSourceMod("minecraft").setInitialized();
		CombinationRegistry.registerCombinationBlocks();
	}
}
