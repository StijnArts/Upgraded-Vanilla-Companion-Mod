package drai.dev.upgradedvanilla.datageneration;

import drai.dev.upgradedvanilla.datageneration.providers.*;
import drai.dev.upgradedvanilla.datageneration.providers.language.*;
import drai.dev.upgradedvanilla.datageneration.providers.loottables.*;
import drai.dev.upgradedvanilla.datageneration.providers.tags.*;
import drai.dev.upgradedvanilla.datageneration.recipes.processing.*;
import net.fabricmc.fabric.api.datagen.v1.*;

public class DataGeneration implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		TextureProvider.run();
		fabricDataGenerator.addProvider(EnglishLanguageProvider::new);
		fabricDataGenerator.addProvider(ModelProvider::new);
		fabricDataGenerator.addProvider(ItemTagGenerator::new);
		fabricDataGenerator.addProvider(BlockTagGenerator::new);
		fabricDataGenerator.addProvider(PoiTypeTagsProvider::new);
		fabricDataGenerator.addProvider(BlockLootProvider::new);
		fabricDataGenerator.addProvider(RecipeProvider::new);
		UVProcessingRecipeGen.registerAll(fabricDataGenerator);
	}
}
