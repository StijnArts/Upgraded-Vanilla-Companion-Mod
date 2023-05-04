package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.tag.*;
import games.twinhead.moreslabsstairsandwalls.registry.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({BushBlock.class})
public class PlantBlockMixin extends Block {
	public PlantBlockMixin(BlockBehaviour.Properties settings) {
		super(settings);
	}

	@Inject(
			method = {"mayPlaceOn"},
			at = {@At("HEAD")},
			cancellable = true
	)
	private void injected(BlockState floor, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if (floor.is(UpgradedVanillaTags.DIRT_BLOCKS)) {
			if (floor.getBlock() instanceof BaseDirtSlab && floor.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) {
				cir.setReturnValue(false);
			}

			if (floor.getBlock() instanceof BaseDirtSlab && floor.getValue(StairBlock.HALF) == Half.BOTTOM) {
				cir.setReturnValue(false);
			}
		}

	}
}
