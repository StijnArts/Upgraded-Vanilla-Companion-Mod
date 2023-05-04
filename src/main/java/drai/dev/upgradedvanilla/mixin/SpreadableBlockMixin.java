package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;
import games.twinhead.moreslabsstairsandwalls.block.spreadable.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import static drai.dev.upgradedvanilla.modules.minecraft.soil.grass.Grass.onTick;

@Debug(export = true)
@Mixin({SpreadingSnowyDirtBlock.class})
public abstract class SpreadableBlockMixin extends SnowyDirtBlock {
	@Shadow
	public static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
		return false;
	}

	public SpreadableBlockMixin(BlockBehaviour.Properties settings) {
		super(settings);
	}

	@Inject(
			method = {"randomTick"},
			at = @At(value = "TAIL"),
			cancellable = true,
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
		if (!canBeGrass(state, level, pos)) {
			//Tells the block to turn into a dirt block if its obstructed
			level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			ci.cancel();
		} else if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
			onTick(this.defaultBlockState(), level, pos, random, ci);
		}
	}
}
