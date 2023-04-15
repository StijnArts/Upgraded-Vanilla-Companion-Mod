package drai.dev.upgradedvanilla.datageneration.providers.tags;

import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;

public class BlockTagGenerator extends FabricTagProvider {
	public BlockTagGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator, Registry.BLOCK);
	}

	@Override
	protected void generateTags() {
		for (TagKey<Block> blockTag : TagHelper.getResourceBlockTags().keySet()) {
			for (ResourceLocation block : TagHelper.getResourceBlockTags().get(blockTag)) {
				getOrCreateTagBuilder(blockTag).add(block);
			}
		}

		for (TagKey<Block> blockTag : TagHelper.getBlockTags().keySet()) {
			for (Block block : TagHelper.getBlockTags().get(blockTag)) {
				getOrCreateTagBuilder(blockTag).add(block);
			}
		}

		for (TagKey<Block> BlockTag : TagHelper.getCompositeBlockTags().keySet()) {
			for (TagKey<Block> subTag : TagHelper.getCompositeBlockTags().get(BlockTag)) {
				getOrCreateTagBuilder(BlockTag).addOptionalTag(subTag);
			}
		}
	}
}
