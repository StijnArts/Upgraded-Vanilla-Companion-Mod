package drai.dev.upgradedvanilla.helpers;

import com.google.common.collect.*;

import net.minecraft.data.models.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.*;

import java.util.*;
import java.util.function.*;

public class RecipeHelper {
	private static LinkedHashMultimap<ItemLike, BiConsumer<Consumer<FinishedRecipe>, ItemLike>> recipes = LinkedHashMultimap.create();

	public static void addRecipe(ItemLike itemLike, BiConsumer<Consumer<FinishedRecipe>, ItemLike> recipeConsumer){
		recipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<ItemLike, BiConsumer<Consumer<FinishedRecipe>, ItemLike>> getRecipes() {
		return recipes;
	}
}
