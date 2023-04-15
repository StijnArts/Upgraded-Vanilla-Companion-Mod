package drai.dev.upgradedvanilla.datageneration.providers.loottables;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;

import java.util.function.*;

public class BlockLootProvider extends FabricBlockLootTableProvider {
	public BlockLootProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateBlockLootTables() {
		BlockLootHelper.getBlockLoot().forEach((block, consumer)-> consumer.accept(this,block));
	}
}
//resourceLocationBuilderBiConsumer.accept(IDHelper.createID("",""),
//				FabricBlockLootTableProvider.createSingleItemTable(item));
//drops(Tutorial.TEST_BLOCK, Tutorial.TEST_ITEM, ConstantLootNumberProvider.create(9.0F)));
