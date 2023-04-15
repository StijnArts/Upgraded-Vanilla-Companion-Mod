package drai.dev.upgradedvanilla.blockentities;

import drai.dev.upgradedvanilla.registry.*;
import net.minecraft.core.*;
import net.minecraft.network.protocol.*;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

public class BaseBedBlockEntity extends BlockEntity{
	private DyeColor color;
	public BaseBedBlockEntity(BlockPos pos, BlockState blockState) {
		super(BaseBlockEntities.BED, pos, blockState);
		this.color = ((BedBlock)blockState.getBlock()).getColor();
	}
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	public DyeColor getColor() {
		return this.color;
	}

	public void setColor(DyeColor color) {
		this.color = color;
	}
}
