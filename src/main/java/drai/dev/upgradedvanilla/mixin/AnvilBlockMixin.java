package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.registry.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import static net.minecraft.world.level.block.AnvilBlock.FACING;

@Mixin(AnvilBlock.class)
public abstract class AnvilBlockMixin {



	@Inject(
			method = {"damage"},
			at = {@At("RETURN")},
			cancellable = true
	)
	private static void supports(BlockState state, CallbackInfoReturnable<BlockState> cir) {
		if (state.getBlock() instanceof VariantAnvilBlock){
			String material = ((VariantAnvilBlock) state.getBlock()).getMaterial();
			if (state.is(AnvilRegistry.getAnvil(material))) {
				cir.setReturnValue((BlockState)AnvilRegistry.getChippedAnvil(material).defaultBlockState().setValue(FACING, state.getValue(FACING)));
			}
			if (state.is(AnvilRegistry.getChippedAnvil(material))) {
				cir.setReturnValue((BlockState)AnvilRegistry.getDamagedAnvil(material).defaultBlockState().setValue(FACING, state.getValue(FACING)));
			}
		}
	}
}
