package drai.dev.upgradedvanilla.blocks.dirt;

import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.*;

public class BaseDirtSlab extends SlabBlock {
	public BaseDirtSlab(BlockBehaviour.Properties settings) {
		super(settings);
	}
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!(player.getMainHandItem().getItem() instanceof ShovelItem)) {
			return InteractionResult.PASS;
		} else {
			if (!world.isClientSide) {
				world.setBlockAndUpdate(pos, (BlockState) Path.PATH_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, (SlabType)state.getValue(SlabBlock.TYPE)));
				player.getMainHandItem().hurtAndBreak(1, player, (p) -> {
					p.broadcastBreakEvent(hand);
				});
			} else {
				world.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
			}

			return InteractionResult.SUCCESS;
		}
	}
}
