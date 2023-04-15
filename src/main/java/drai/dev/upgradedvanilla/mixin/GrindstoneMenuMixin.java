package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.registry.*;
import net.minecraft.client.gui.screens.inventory.*;

import net.minecraft.core.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;

import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(GrindstoneMenu.class)
public class GrindstoneMenuMixin {

	@Shadow
	@Final
	private ContainerLevelAccess access;
	@Inject(
			method = {"stillValid"},
			at = {@At("RETURN")},
			cancellable = true
	)
	private void supports(Player player, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(this.access.evaluate((world, pos) -> {
			if (!(world.getBlockState((BlockPos)pos).getBlock() instanceof GrindstoneBlock)) {
				return false;
			}
			return player.distanceToSqr((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5) <= 64.0;
		}, true));
	}
}
