package drai.dev.upgradedvanilla.mixin;

import net.minecraft.core.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;

import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(SmithingMenu.class)
public class SmithingTableMixin {
	@Inject(
			method = {"isValidBlock"},
			at = {@At("RETURN")},
			cancellable = true
	)
	private void supports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
			cir.setReturnValue(state.getBlock() instanceof SmithingTableBlock);
	}
}
