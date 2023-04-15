package drai.dev.upgradedvanilla.datageneration.providers.tags;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.ai.village.poi.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public class PoiTypeTagsProvider extends TagsProvider<PoiType> {

	public PoiTypeTagsProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator, Registry.POINT_OF_INTEREST_TYPE);
	}

//	@Override
//	protected void generateTags() {
//		for (TagKey<PoiType> poiTypeTag : TagHelper.getResourcePoiTypeTags().keySet()) {
//			for (ResourceLocation block : TagHelper.getResourcePoiTypeTags().get(poiTypeTag)) {
//				getOrCreateTagBuilder(poiTypeTag).add(block);
//			}
//		}
//
//		for (TagKey<PoiType> poiTypeTag : TagHelper.getPoiTypeTags().keySet()) {
//			for (Block block : TagHelper.getPoiTypeTags().get(poiTypeTag)) {
//				getOrCreateTagBuilder(poiTypeTag).add(block);
//			}
//		}
//
//		for (TagKey<PoiType> poiTypeTag : TagHelper.getCompositePoiTypeTags().keySet()) {
//			for (TagKey<PoiType> subTag : TagHelper.getCompositePoiTypeTags().get(poiTypeTag)) {
//				getOrCreateTagBuilder(poiTypeTag).addOptionalTag(subTag);
//			}
//		}
//	}

	@Override
	protected void addTags() {
		for (TagKey<PoiType> poiTypeTag : TagHelper.getPoiTypeTags().keySet()) {
			for (PoiType poiType : TagHelper.getPoiTypeTags().get(poiTypeTag)) {
				this.tag(poiTypeTag).add(poiType);
			}
		}
	}
}
