package drai.dev.upgradedvanilla.modules.minecraft.sand;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.gravityblocks.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.core.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class MinecraftSandBlocks {
	public static Block sandStairsBlock(String material, Block woolBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID, new StairBlock(woolBlock.defaultBlockState(), FabricBlockSettings.copyOf(woolBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(woolBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(woolBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(woolBlock))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(woolBlock));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,woolBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_bales", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block sandSlabBlock(String material, Block sandBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									  List<TagKey<Item>> itemTags, int dustColor) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new FallingSlab(dustColor,sandBlock), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sandBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(sandBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(sandBlock))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(sandBlock));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(sandBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,sandBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
}
