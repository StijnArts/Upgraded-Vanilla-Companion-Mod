package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;
import drai.dev.upgradedvanilla.tag.*;
import games.twinhead.moreslabsstairsandwalls.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.feature.treedecorators.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;
import java.util.function.*;

@Mixin({AlterGroundDecorator.class})
public abstract class TreeDecoratorGeneratorMixin {

	@Inject(
			method = {"placeBlockAt"},
			at = {@At("HEAD")},
			cancellable = true
	)
	private void replace(TreeDecorator.Context context, BlockPos pos, CallbackInfo ci) {
		for (int i = 2; i >= -3; --i) {
			BlockPos blockPos = pos.above(i);
			if (context.level().isStateAtPosition(blockPos, blockState -> blockState.is(UpgradedVanillaTags.PODZOL_REPLACEABLE))) {
				BlockState modBlockState;
				ArrayList<BlockState> blockStates = new ArrayList<>();
				if (context.level().isStateAtPosition(blockPos, (blockState -> {
						if (blockState.getBlock() instanceof BaseDirtStairs) {
							blockStates.add(blockState);
							return true;
						}
						return false;
					}))) {
					System.out.println("attempting to place a podzol stairs");
					modBlockState = blockStates.get(0);
					System.out.println(modBlockState.getBlock());
					context.setBlock(blockPos, (BlockState) ((BlockState) ((BlockState) Podzol.PODZOL_STAIRS.defaultBlockState().setValue(StairBlock.FACING,
							(Direction) modBlockState.getValue(StairBlock.FACING))).setValue(StairBlock.SHAPE,
							(StairsShape) modBlockState.getValue(StairBlock.SHAPE))).setValue(StairBlock.HALF,
							(Half) modBlockState.getValue(StairBlock.HALF)));
					ci.cancel();
					break;
				}
				if (context.level().isStateAtPosition(blockPos, (blockState -> {
					if (blockState.getBlock() instanceof BaseDirtSlab) {
						blockStates.add(blockState);
						return true;
					}
					return false;
				}))) {
					System.out.println("attempting to place a podzol slab");
					modBlockState = blockStates.get(0);
					context.setBlock(blockPos, (BlockState) Podzol.PODZOL_SLAB.defaultBlockState().setValue(SlabBlock.TYPE,
							(SlabType) modBlockState.getValue(SlabBlock.TYPE)));
					ci.cancel();
					break;
				}
			}
			if (!context.isAir(blockPos) && i < 0) break;
		}
	}
}
