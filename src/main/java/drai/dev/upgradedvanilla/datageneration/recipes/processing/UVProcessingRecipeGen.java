package drai.dev.upgradedvanilla.datageneration.recipes.processing;

import com.simibubi.create.*;
import com.simibubi.create.content.contraptions.processing.*;
import com.simibubi.create.foundation.data.recipe.*;
import com.simibubi.create.foundation.utility.*;
import com.simibubi.create.foundation.utility.recipe.*;

import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

public abstract class UVProcessingRecipeGen extends UVCreateRecipeProvider {

	protected static final List<UVProcessingRecipeGen> GENERATORS = new ArrayList<>();
	protected static final long BUCKET = FluidConstants.BUCKET;
	protected static final long BOTTLE = FluidConstants.BOTTLE;

	public UVProcessingRecipeGen(FabricDataGenerator generator) {
		super(generator);
	}

	public static void registerAll(FabricDataGenerator gen) {

		GENERATORS.add(new UVWashingRecipeGen(gen));
		GENERATORS.add(new UVCrushingRecipeGen(gen));
		GENERATORS.add(new UVMillingRecipeGen(gen));
		GENERATORS.add(new UVCuttingRecipeGen(gen));
//		GENERATORS.add(new PolishingRecipeGen(gen));
//		GENERATORS.add(new DeployingRecipeGen(gen));
//		GENERATORS.add(new MixingRecipeGen(gen));
//		GENERATORS.add(new CompactingRecipeGen(gen));
//		GENERATORS.add(new PressingRecipeGen(gen));
//		GENERATORS.add(new FillingRecipeGen(gen));
//		GENERATORS.add(new EmptyingRecipeGen(gen));
		GENERATORS.add(new UVHauntingRecipeGen(gen));
//		GENERATORS.add(new ItemApplicationRecipeGen(gen));

		gen.addProvider(true, new DataProvider() {

			@Override
			public String getName() {
				return "Create's Processing Recipes";
			}

			@Override
			public void run(CachedOutput dc) throws IOException {
				GENERATORS.forEach(g -> {
					g.init();
					try {
						g.run(dc);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		});
	}

	protected void init(){
	}

	/**
	 * Create a processing recipe with a single itemstack ingredient, using its id
	 * as the name of the recipe
	 */
	protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(String namespace,
																	 Supplier<ItemLike> singleIngredient, UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		ProcessingRecipeSerializer<T> serializer = getSerializer();
		GeneratedRecipe generatedRecipe = c -> {
			ItemLike iItemProvider = singleIngredient.get();
			transform
					.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(),
							new ResourceLocation(namespace, RegisteredObjects.getKeyOrThrow(iItemProvider.asItem())
									.getPath())).withItemIngredients(Ingredient.of(iItemProvider)))
					.build(c);
		};
		all.add(generatedRecipe);
		return generatedRecipe;
	}

	/**
	 * Create a processing recipe with a single itemstack ingredient, using its id
	 * as the name of the recipe
	 */
	protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
														   UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return create(Create.ID, singleIngredient, transform);
	}

	protected <T extends ProcessingRecipe<?>> GeneratedRecipe createWithDeferredId(Supplier<ResourceLocation> name,
																				   UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		ProcessingRecipeSerializer<T> serializer = getSerializer();
		GeneratedRecipe generatedRecipe =
				c -> transform.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(), name.get()))
						.build(c);
		all.add(generatedRecipe);
		return generatedRecipe;
	}

	/**
	 * Create a new processing recipe, with recipe definitions provided by the
	 * function
	 */
	protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(ResourceLocation name,
																	 UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return createWithDeferredId(() -> name, transform);
	}

	/**
	 * Create a new processing recipe, with recipe definitions provided by the
	 * function
	 */
	public <T extends ProcessingRecipe<?>> GeneratedRecipe create(String name,
														   UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return create(Create.asResource(name), transform);
	}

	protected abstract IRecipeTypeInfo getRecipeType();

	protected <T extends ProcessingRecipe<?>> ProcessingRecipeSerializer<T> getSerializer() {
		return getRecipeType().getSerializer();
	}

	protected Supplier<ResourceLocation> idWithSuffix(Supplier<ItemLike> item, String suffix) {
		return () -> {
			ResourceLocation registryName = RegisteredObjects.getKeyOrThrow(item.get()
					.asItem());
			return Create.asResource(registryName.getPath() + suffix);
		};
	}

	@Override
	public String getName() {
		return "Create's Processing Recipes: " + getRecipeType().getId()
				.getPath();
	}

}
