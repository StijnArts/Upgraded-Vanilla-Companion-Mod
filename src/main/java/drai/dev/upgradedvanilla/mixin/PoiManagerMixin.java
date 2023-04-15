package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.world.entity.ai.village.poi.*;

import org.betterx.bclib.api.v2.poi.PoiManager;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(PoiManager.class)
public class PoiManagerMixin {

	@Inject(
			method = {"registerAll"},
			at = {@At("HEAD")},
			remap = false
	)
	private static void supports(CallbackInfo ci) {
		PoiManager.setTag(PoiTypes.LIBRARIAN, PoiTags.LIBRARIAN_WORKSTATION);
		PoiManager.setTag(PoiTypes.WEAPONSMITH, PoiTags.WEAPONSMITH_WORKSTATION);
		PoiManager.setTag(PoiTypes.CARTOGRAPHER, PoiTags.CARTOGRAPHER_WORKSTATION);
		PoiManager.setTag(PoiTypes.FLETCHER, PoiTags.FLETCHER_WORKSTATION);
		PoiManager.setTag(PoiTypes.BUTCHER, PoiTags.BUTCHER_WORKSTATION);
		PoiManager.setTag(PoiTypes.MASON, PoiTags.MASON_WORKSTATION);
		PoiManager.setTag(PoiTypes.ARMORER, PoiTags.ARMORER_WORKSTATION);
	}
}
