package drai.dev.upgradedvanilla.blocks.terracotta;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;

public class GlazedTerracottaStairsBlock extends StairBlock {
	public GlazedTerracottaStairsBlock(BlockState baseBlockState, BlockBehaviour.Properties settings) {
		super(baseBlockState, settings);
	}

	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.PUSH_ONLY;
	}
}
