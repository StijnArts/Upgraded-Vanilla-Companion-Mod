package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.modules.betterend.*;
import drai.dev.upgradedvanilla.registry.*;

import drai.dev.upgradedvanilla.tag.*;

import org.betterx.worlds.together.tag.v3.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(CommonPoiTags.class)
public class CommonPoiTagsMixin {

	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void example$init(CallbackInfo ci) {
		PoiTags.register();
	}
}
