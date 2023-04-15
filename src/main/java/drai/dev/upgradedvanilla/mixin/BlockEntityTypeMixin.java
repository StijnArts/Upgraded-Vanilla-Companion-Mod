package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.registry.*;
import net.bunten.enderscape.blocks.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({BlockEntityType.class})
public class BlockEntityTypeMixin<T extends BlockEntity> {
	public BlockEntityTypeMixin() {
	}

	@Inject(
			method = {"isValid"},
			at = {@At("HEAD")},
			cancellable = true
	)
	private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
		BlockEntityType<T> that = (BlockEntityType)BlockEntityType.class.cast(this);
		if (BlockEntityType.SIGN.equals(that) && (state.getBlock() instanceof SignBlock || state.getBlock() instanceof WallSignBlock)) {
			info.setReturnValue(true);
		} else if (BlockEntityType.LECTERN.equals(that) && (state.getBlock() instanceof LecternBlock)){
			info.setReturnValue(true);
		} else if (BlockEntityType.CAMPFIRE.equals(that) && (state.getBlock() instanceof CampfireBlock)){
			info.setReturnValue(true);
		} else if (BaseBlockEntities.BED.equals(that) && (state.getBlock() instanceof BaseBedBlock)){
			info.setReturnValue(true);
		} else if (BlockEntityType.BEEHIVE.equals(that) && (state.getBlock() instanceof BeehiveBlock)){
			info.setReturnValue(true);
		} else if (BlockEntityType.SMOKER.equals(that) && (state.getBlock() instanceof SmokerBlock)){
			info.setReturnValue(true);
		} else if (BlockEntityType.FURNACE.equals(that) && (state.getBlock() instanceof FurnaceBlock)){
			info.setReturnValue(true);
		} else if (BlockEntityType.BLAST_FURNACE.equals(that) && (state.getBlock() instanceof BlastFurnaceBlock)){
			info.setReturnValue(true);
		}
	}
}
