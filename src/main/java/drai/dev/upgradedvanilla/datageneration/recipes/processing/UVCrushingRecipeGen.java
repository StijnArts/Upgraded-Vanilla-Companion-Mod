package drai.dev.upgradedvanilla.datageneration.recipes.processing;

import com.simibubi.create.*;
import com.simibubi.create.content.contraptions.processing.*;
import com.simibubi.create.content.palettes.*;
import com.simibubi.create.foundation.data.recipe.*;
import com.simibubi.create.foundation.utility.*;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.resource.conditions.v1.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.function.*;

import static com.simibubi.create.foundation.data.recipe.CompatMetals.ALUMINUM;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.LEAD;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.NICKEL;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.OSMIUM;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.PLATINUM;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.QUICKSILVER;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.SILVER;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.TIN;
import static com.simibubi.create.foundation.data.recipe.CompatMetals.URANIUM;

public class UVCrushingRecipeGen extends UVProcessingRecipeGen {

	@Override
	protected void init() {
		ProcessingRecipeHelper.getCrushingRecipes().forEach(((itemLike, consumer) -> consumer.accept(this, itemLike)));
	}

	protected GeneratedRecipe stoneOre(Supplier<ItemLike> ore, Supplier<ItemLike> raw, float expectedAmount,
									   int duration) {
		return ore(Blocks.COBBLESTONE, ore, raw, expectedAmount, duration);
	}

	protected GeneratedRecipe deepslateOre(Supplier<ItemLike> ore, Supplier<ItemLike> raw, float expectedAmount,
										   int duration) {
		return ore(Blocks.COBBLED_DEEPSLATE, ore, raw, expectedAmount, duration);
	}

	protected GeneratedRecipe netherOre(Supplier<ItemLike> ore, Supplier<ItemLike> raw, float expectedAmount,
										int duration) {
		return ore(Blocks.NETHERRACK, ore, raw, expectedAmount, duration);
	}

	protected GeneratedRecipe mineralRecycling(AllPaletteStoneTypes type, Supplier<ItemLike> crushed,
											   Supplier<ItemLike> nugget, float chance) {
		return mineralRecycling(type, b -> b.duration(250)
				.output(chance, crushed.get(), 1)
				.output(chance, nugget.get(), 1));
	}

	protected GeneratedRecipe mineralRecycling(AllPaletteStoneTypes type,
											   UnaryOperator<ProcessingRecipeBuilder<ProcessingRecipe<?>>> transform) {
		create(Lang.asId(type.name()) + "_recycling", b -> transform.apply(b.require(type.materialTag)));
		return create(type.getBaseBlock()::get, transform);
	}

	public GeneratedRecipe ore(ItemLike stoneType, Supplier<ItemLike> ore, Supplier<ItemLike> raw,
								  float expectedAmount, int duration) {
		return create(ore, b -> {
			ProcessingRecipeBuilder<ProcessingRecipe<?>> builder = b.duration(duration)
					.output(raw.get(), Mth.floor(expectedAmount));
			float extra = expectedAmount - Mth.floor(expectedAmount);
			if (extra > 0)
				builder.output(extra, raw.get(), 1);
			builder.output(.75f, AllItems.EXP_NUGGET.get(), 1);
			return builder.output(.125f, stoneType);
		});
	}

	protected GeneratedRecipe rawOre(Supplier<ItemLike> input, Supplier<ItemLike> result, int amount) {
		return create(input, b -> b.duration(400)
				.output(result.get(), amount)
				.output(.75f, AllItems.EXP_NUGGET.get(), amount));
	}

	protected GeneratedRecipe moddedRawOre(CompatMetals metal, Supplier<ItemLike> result, int amount) {
		String name = metal.getName();
		return create("raw_" + name + (amount == 1 ? "_ore" : "_block"), b -> {
			String suffix = amount == 1 ? "_ores" : "_blocks";
			return b.duration(400)
					.withCondition(DefaultResourceConditions.tagsPopulated(AllTags.forgeItemTag("raw_" + name + suffix)))
					.require(AllTags.forgeItemTag("raw_" + name + suffix))
					.output(result.get(), amount)
					.output(.75f, AllItems.EXP_NUGGET.get(), amount);
		});
	}

	protected GeneratedRecipe moddedOre(CompatMetals metal, Supplier<ItemLike> result) {
		String name = metal.getName();
		return create(name + "_ore", b -> {
			String suffix = "_ores";
			return b.duration(400)
					.withCondition(DefaultResourceConditions.tagsPopulated(AllTags.forgeItemTag(name + suffix)))
					.require(AllTags.forgeItemTag(name + suffix))
					.output(result.get(), 1)
					.output(.75f, result.get(), 1)
					.output(.75f, AllItems.EXP_NUGGET.get(), 1);
		});
	}

	public UVCrushingRecipeGen(FabricDataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.CRUSHING;
	}

}
