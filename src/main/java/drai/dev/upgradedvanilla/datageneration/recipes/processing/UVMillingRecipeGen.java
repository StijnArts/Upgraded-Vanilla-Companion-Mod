package drai.dev.upgradedvanilla.datageneration.recipes.processing;

import com.simibubi.create.*;
import com.simibubi.create.foundation.data.recipe.*;
import com.tterrag.registrate.util.entry.*;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.resource.conditions.v1.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public class UVMillingRecipeGen extends UVProcessingRecipeGen {

	@Override
	protected void init() {
		ProcessingRecipeHelper.getMillingRecipes().forEach(((itemLike, consumer) -> consumer.accept(this, itemLike)));
	}

	protected GeneratedRecipe metalOre(String name, ItemEntry<? extends Item> crushed, int duration) {
		return create(name + "_ore", b -> b.duration(duration)
				.withCondition(DefaultResourceConditions.not(DefaultResourceConditions.tagsPopulated(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", name + "_ores")))))
				.require(AllTags.forgeItemTag(name + "_ores"))
				.output(crushed.get()));
	}

	public UVMillingRecipeGen(FabricDataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.MILLING;
	}

}
