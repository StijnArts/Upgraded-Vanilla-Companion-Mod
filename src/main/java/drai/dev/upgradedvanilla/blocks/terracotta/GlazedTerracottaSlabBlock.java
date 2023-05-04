package drai.dev.upgradedvanilla.blocks.terracotta;

import net.minecraft.world.item.context.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

public class GlazedTerracottaSlabBlock extends SlabBlock {
	public static final DirectionProperty FACING;

	public GlazedTerracottaSlabBlock(BlockBehaviour.Properties settings) {
		super(settings);
	}

	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return (BlockState)super.getStateForPlacement(ctx).setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(new Property[]{FACING});
	}

	static {
		FACING = HorizontalDirectionalBlock.FACING;
	}
}
