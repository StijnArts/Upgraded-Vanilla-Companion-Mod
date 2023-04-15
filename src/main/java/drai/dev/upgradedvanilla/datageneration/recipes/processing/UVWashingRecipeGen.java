package drai.dev.upgradedvanilla.datageneration.recipes.processing;

import com.simibubi.create.*;
import com.simibubi.create.foundation.data.recipe.*;
import com.tterrag.registrate.util.entry.*;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.function.*;

public class UVWashingRecipeGen extends UVProcessingRecipeGen {

	@Override
	protected void init() {
		ProcessingRecipeHelper.getWashingRecipes().forEach(((itemLike, consumer) -> consumer.accept(this, itemLike)));
	}

	public GeneratedRecipe convert(Block block, Block result) {
		return create(() -> block, b -> b.output(result));
	}

	public GeneratedRecipe crushedOre(ItemEntry<Item> crushed, Supplier<ItemLike> nugget, Supplier<ItemLike> secondary,
									  float secondaryChance) {
		return create(crushed::get, b -> b.output(nugget.get(), 9)
				.output(secondaryChance, secondary.get(), 1));
	}

	public GeneratedRecipe moddedCrushedOre(ItemEntry<? extends Item> crushed, CompatMetals metal) {
		String metalName = metal.getName();
		for (Mods mod : metal.getMods()) {
			ResourceLocation nugget = mod.nuggetOf(metalName);
			create(mod.getId() + "/" + crushed.getId()
							.getPath(),
					b -> b.withItemIngredients(Ingredient.of(crushed::get))
							.output(1, nugget, 9)
							.whenModLoaded(mod.getId()));
		}
		return null;
	}

	public UVWashingRecipeGen(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.SPLASHING;
	}

}
