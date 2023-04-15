package drai.dev.upgradedvanilla.blocks;

import drai.dev.upgradedvanilla.blockentities.*;
import drai.dev.upgradedvanilla.registry.*;
import net.fabricmc.api.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.storage.loot.*;

import org.betterx.bclib.client.models.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BaseBedBlock extends BedBlock {
	public BaseBedBlock(DyeColor color, Properties properties) {
		super(color, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState){
		return BaseBlockEntities.BED.create(blockPos,blockState);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
}
