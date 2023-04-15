package drai.dev.upgradedvanilla.datageneration.providers.tags;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

public class ItemTagGenerator extends FabricTagProvider{
	public ItemTagGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator, Registry.ITEM);
	}

	@Override
	protected void generateTags() {
		for (TagKey<Item> itemTag : TagHelper.getResourceItemTags().keySet()) {
			for (ResourceLocation item : TagHelper.getResourceItemTags().get(itemTag)) {
				getOrCreateTagBuilder(itemTag).add(item);
			}
		}

		for (TagKey<Item> itemTag : TagHelper.getItemTags().keySet()) {
			for (Item item : TagHelper.getItemTags().get(itemTag)) {
				getOrCreateTagBuilder(itemTag).add(item);
			}
		}

		for (TagKey<Item> itemTag : TagHelper.getCompositeItemTags().keySet()) {
			for (TagKey<Item> subTag : TagHelper.getCompositeItemTags().get(itemTag)) {
				getOrCreateTagBuilder(itemTag).addOptionalTag(subTag);
			}
		}
	}
}
