package drai.dev.upgradedvanilla.datageneration.providers;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.world.level.block.*;

public class ModelProvider extends FabricModelProvider{
	public ModelProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		BlockStateHelper.getBlockModels().forEach((itemLike, consumer) -> consumer.accept(blockStateModelGenerator, itemLike));

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		ItemModelHelper.getItemModels().forEach(((item, consumer) -> consumer.accept(itemModelGenerator,item)));
	}
}
