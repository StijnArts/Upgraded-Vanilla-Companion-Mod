package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import net.minecraft.core.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;

import net.minecraft.world.item.context.*;

import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(WrittenBookItem.class)
public class WrittenBookItemMixin {
	@Inject(method = "useOn", at = @At("RETURN"), cancellable = true)
	private void placeBook(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
		BlockPos blockPos;
		Level level = context.getLevel();
		BlockState blockState = level.getBlockState(blockPos = context.getClickedPos());
		InteractionResult interactionResult;
		if (blockState.getBlock() instanceof LecternBlock) {
			interactionResult = BaseLecternBlock.tryPlaceBook(context.getPlayer(), level, blockPos, blockState, context.getItemInHand()) ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
		} else {
			interactionResult =InteractionResult.PASS;
		}
		cir.setReturnValue(interactionResult);
	}
}
