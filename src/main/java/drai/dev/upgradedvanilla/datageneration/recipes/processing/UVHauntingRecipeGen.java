package drai.dev.upgradedvanilla.datageneration.recipes.processing;

import com.simibubi.create.*;
import com.simibubi.create.foundation.data.recipe.*;
import com.simibubi.create.foundation.utility.*;

import drai.dev.upgradedvanilla.helpers.*;
import me.alphamode.forgetags.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.function.*;

public class UVHauntingRecipeGen extends UVProcessingRecipeGen {

	@Override
	protected void init() {
		ProcessingRecipeHelper.getHauntingRecipes().forEach(((itemLike, consumer) -> consumer.accept(this, itemLike)));
	}

	public GeneratedRecipe convert(ItemLike input, ItemLike result) {
		return convert(() -> Ingredient.of(input), () -> result);
	}

	public GeneratedRecipe convert(Supplier<Ingredient> input, Supplier<ItemLike> result) {
		return create(Create.asResource(RegisteredObjects.getKeyOrThrow(result.get()
								.asItem())
						.getPath()),
				p -> p.withItemIngredients(input.get())
						.output(result.get()));
	}

	public UVHauntingRecipeGen(FabricDataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.HAUNTING;
	}

}
