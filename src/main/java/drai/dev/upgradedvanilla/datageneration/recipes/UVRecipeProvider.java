package drai.dev.upgradedvanilla.datageneration.recipes;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import java.util.function.*;

public class UVRecipeProvider {

	public static void smeltingResultFromBase(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, 0.1f, 200)
				.unlockedBy(RecipeProvider.getHasName(ingredient), RecipeProvider.has(ingredient)).save(finishedRecipeConsumer, RecipeProvider.getItemName(result)+"_smelting");
	}
}
