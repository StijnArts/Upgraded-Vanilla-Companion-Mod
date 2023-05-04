package drai.dev.upgradedvanilla.blocks.logs;

import games.twinhead.moreslabsstairsandwalls.block.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;

public class StrippableWallBlock extends WallBlock {
	private final Block strippedBlock;

	public StrippableWallBlock(Block strippedBlock, BlockBehaviour.Properties settings) {
		super(settings);
		this.strippedBlock = strippedBlock;
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!(player.getMainHandItem().getItem() instanceof AxeItem)) {
			return InteractionResult.PASS;
		} else {
			if (!world.isClientSide) {
				world.setBlockAndUpdate(pos, this.strippedBlock.withPropertiesOf(state));
				player.getMainHandItem().hurtAndBreak(1, player, (p) -> {
					p.broadcastBreakEvent(hand);
				});
			} else {
				world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			}

			return InteractionResult.SUCCESS;
		}
	}
}
