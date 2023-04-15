package drai.dev.upgradedvanilla.datageneration.providers;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.recipes.*;

import java.util.function.*;

public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
		RecipeHelper.getRecipes().forEach((itemLike, consumer) -> consumer.accept(exporter,itemLike));
	}
}
