package drai.dev.upgradedvanilla.datageneration.providers.language;

import com.ibm.icu.impl.*;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.nio.file.*;

public class EnglishLanguageProvider extends FabricLanguageProvider {
	public EnglishLanguageProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		LanguageHelper.getEnglishTranslations().getBlockTranslations().forEach((Pair<Block,String> pair)->translationBuilder.add(pair.first,pair.second));
		LanguageHelper.getEnglishTranslations().getItemTranslations().forEach((Pair<Item,String> pair)->translationBuilder.add(pair.first,pair.second));
	}
}
