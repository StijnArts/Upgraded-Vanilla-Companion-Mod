package drai.dev.upgradedvanilla.datageneration.recipes.processing;

import com.simibubi.create.*;
import com.simibubi.create.foundation.data.recipe.*;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.minecraft.world.level.block.*;

public class UVCuttingRecipeGen extends UVProcessingRecipeGen {

	@Override
	protected void init() {
		ProcessingRecipeHelper.getCuttingRecipes().forEach(((itemLike, consumer) -> consumer.accept(this, itemLike)));
	}

	public GeneratedRecipe stripAndMakePlanks(Block wood, Block stripped, Block planks) {
		create(() -> wood, b -> b.duration(50)
				.output(stripped));
		return create(() -> stripped, b -> b.duration(50)
				.output(planks, 6));
	}

	GeneratedRecipe cuttingCompat(Mods mod, String... woodtypes) {
		for (String type : woodtypes) {
			String planks = type + "_planks";

			if (mod == Mods.ARS_N && type.contains("archwood"))
				planks = "archwood_planks";

			String strippedPre = mod.strippedIsSuffix ? "" : "stripped_";
			String strippedPost = mod.strippedIsSuffix ? "_stripped" : "";
			stripAndMakePlanks(mod, type + "_log", strippedPre + type + "_log" + strippedPost, planks);

			String wood = type + (mod.omitWoodSuffix ? "" : "_wood");
			stripAndMakePlanks(mod, wood, strippedPre + wood + strippedPost, planks);
		}
		return null;
	}

	public GeneratedRecipe stripAndMakePlanks(Mods mod, String wood, String stripped, String planks) {
		if (wood != null)
			create("compat/" + mod.getId() + "/" + wood, b -> b.duration(50)
					.require(mod, wood)
					.output(1, mod, stripped, 1)
					.whenModLoaded(mod.getId()));
		if (planks != null)
			create("compat/" + mod.getId() + "/" + stripped, b -> b.duration(50)
					.require(mod, stripped)
					.output(1, mod, planks, 6)
					.whenModLoaded(mod.getId()));
		return null;
	}

	public UVCuttingRecipeGen(FabricDataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.CUTTING;
	}

}
