package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.registry.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import org.betterx.bclib.blockentities.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(DynamicBlockEntityType.class)
public class DynamicBlockEntityTypeMixin<T extends BlockEntity> {
	@Inject(
			method = {"isValid"},
			at = {@At("HEAD")},
			cancellable = true
	)
	private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
		BlockEntityType<T> that = (BlockEntityType)BlockEntityType.class.cast(this);
		if (BaseBlockEntities.BED.equals(that) && (state.getBlock() instanceof BaseBedBlock)){
			info.setReturnValue(true);
		}
	}
}
