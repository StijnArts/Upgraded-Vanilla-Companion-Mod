package drai.dev.upgradedvanilla.registry;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blockentities.*;
import drai.dev.upgradedvanilla.blocks.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.entity.*;

import org.betterx.bclib.*;
import org.betterx.bclib.blockentities.*;
import org.betterx.bclib.blocks.*;

public class BaseBlockEntities {
	public static final DynamicBlockEntityType<BaseBedBlockEntity> BED = registerBlockEntityType(new ResourceLocation(UpgradedVanilla.ID,"bed"), BaseBedBlockEntity::new);

	public static <T extends BlockEntity> DynamicBlockEntityType<T> registerBlockEntityType(ResourceLocation typeId, DynamicBlockEntityType.BlockEntitySupplier<? extends T> supplier) {
		return (DynamicBlockEntityType)Registry.register(Registry.BLOCK_ENTITY_TYPE, typeId, new DynamicBlockEntityType(supplier));
	}

	public static void register() {
	}
	public static Block[] getBeds() {
		return (Block[]) Registry.BLOCK.stream().filter((block) -> {
			return block instanceof BaseBedBlock;
		}).toArray((x$0) -> {
			return new Block[x$0];
		});
	}
}
