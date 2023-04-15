package drai.dev.upgradedvanilla.helpers;

import com.google.common.collect.*;

import drai.dev.upgradedvanilla.datageneration.recipes.processing.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.function.*;

public class ProcessingRecipeHelper {
	private static LinkedHashMultimap<Block, BiConsumer<UVWashingRecipeGen, ItemLike>> washingRecipes = LinkedHashMultimap.create();

	public static void addWashingRecipe(Block itemLike, BiConsumer<UVWashingRecipeGen, ItemLike> recipeConsumer){
		washingRecipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<Block, BiConsumer<UVWashingRecipeGen, ItemLike>> getWashingRecipes() {
		return washingRecipes;
	}

	private static LinkedHashMultimap<Block, BiConsumer<UVCuttingRecipeGen, ItemLike>> cuttingRecipes = LinkedHashMultimap.create();

	public static void addCuttingRecipe(Block itemLike, BiConsumer<UVCuttingRecipeGen, ItemLike> recipeConsumer){
		cuttingRecipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<Block, BiConsumer<UVCuttingRecipeGen, ItemLike>> getCuttingRecipes() {
		return cuttingRecipes;
	}

	private static LinkedHashMultimap<ItemLike, BiConsumer<UVHauntingRecipeGen, ItemLike>> hauntingRecipes = LinkedHashMultimap.create();

	public static void addHauntingRecipe(ItemLike itemLike, BiConsumer<UVHauntingRecipeGen, ItemLike> recipeConsumer){
		hauntingRecipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<ItemLike, BiConsumer<UVHauntingRecipeGen, ItemLike>> getHauntingRecipes() {
		return hauntingRecipes;
	}

	private static LinkedHashMultimap<ItemLike, BiConsumer<UVCrushingRecipeGen, ItemLike>> crushingRecipes = LinkedHashMultimap.create();

	public static void addCrushingRecipe(ItemLike itemLike, BiConsumer<UVCrushingRecipeGen, ItemLike> recipeConsumer){
		crushingRecipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<ItemLike, BiConsumer<UVCrushingRecipeGen, ItemLike>> getCrushingRecipes() {
		return crushingRecipes;
	}

	private static LinkedHashMultimap<ItemLike, BiConsumer<UVMillingRecipeGen, ItemLike>> millingRecipes = LinkedHashMultimap.create();

	public static void addMillingRecipe(ItemLike itemLike, BiConsumer<UVMillingRecipeGen, ItemLike> recipeConsumer){
		millingRecipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<ItemLike, BiConsumer<UVMillingRecipeGen, ItemLike>> getMillingRecipes() {
		return millingRecipes;
	}
}
