package drai.dev.upgradedvanilla.datageneration.providers;

import com.google.common.collect.*;
import com.google.gson.*;

import com.mojang.datafixers.util.*;

import com.simibubi.create.content.curiosities.deco.*;

import drai.dev.upgradedvanilla.mixin.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.stream.*;

public class ItemFrameBlockStateGenerator implements BlockStateGenerator {
	private final ResourceLocation item;
	private final List<Variant> baseVariants;
	private final List<PropertyDispatch> declaredPropertySets = Lists.newArrayList();

	private ItemFrameBlockStateGenerator(ResourceLocation item, List<Variant> baseVariants) {
		this.item = item;
		this.baseVariants = baseVariants;
	}
	public ItemFrameBlockStateGenerator with(PropertyDispatch propertyDispatch) {
		this.declaredPropertySets.add(propertyDispatch);
		return this;
	}


	@Override
	public Block getBlock() {
		return Registry.register(Registry.BLOCK, item,new PlacardBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_BUTTON)));
	}

	@Override
	public JsonElement get() {
		Stream<Pair<Selector, List<Variant>>> stream = Stream.of(Pair.of(Selector.empty(), this.baseVariants));
		for (PropertyDispatch propertyDispatch : this.declaredPropertySets) {
			Map<Selector, List<Variant>> map = ((PropertyDispatchAccessor)propertyDispatch).invokeGetEntries();
			stream = stream.flatMap(variant -> map.entrySet().stream().map(entry -> {
				Selector selector = ((Selector)variant.getFirst()).extend((Selector)entry.getKey());
				List<Variant> list = ItemFrameBlockStateGenerator.mergeVariants((List<Variant>)variant.getSecond(), (List<Variant>)entry.getValue());
				return Pair.of(selector, list);
			}));
		}
		TreeMap map2 = new TreeMap();
		stream.forEach(variant -> map2.put(((Selector)variant.getFirst()).getKey(), Variant.convertList((List<Variant>)variant.getSecond())));
		JsonObject jsonObject2 = new JsonObject();
		jsonObject2.add("variants", Util.make(new JsonObject(), jsonObject -> map2.keySet().forEach(key->jsonObject.add((String) key, (JsonElement) map2.get(key)))));
		return jsonObject2;
	}

	private static List<Variant> mergeVariants(List<Variant> variants1, List<Variant> variants2) {
		ImmutableList.Builder builder = ImmutableList.builder();
		variants1.forEach(leftVariant -> variants2.forEach(rightVariant -> builder.add(Variant.merge(leftVariant, rightVariant))));
		return builder.build();
	}

	public static ItemFrameBlockStateGenerator multiVariant(ResourceLocation resourceLocation) {
		return new ItemFrameBlockStateGenerator(resourceLocation, ImmutableList.of(Variant.variant()));
	}

	public static ItemFrameBlockStateGenerator multiVariant(ResourceLocation resourceLocation, Variant variant) {
		return new ItemFrameBlockStateGenerator(resourceLocation, ImmutableList.of(variant));
	}

	public static ItemFrameBlockStateGenerator multiVariant(ResourceLocation resourceLocation, Variant ... variants) {
		return new ItemFrameBlockStateGenerator(resourceLocation, ImmutableList.copyOf(variants));
	}
}
