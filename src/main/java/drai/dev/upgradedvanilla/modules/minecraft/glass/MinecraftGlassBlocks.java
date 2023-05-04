package drai.dev.upgradedvanilla.modules.minecraft.glass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.culled.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.model.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
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

public class MinecraftGlassBlocks {
	public static Block glassStairsBlock(String material, Block glassBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
											List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(glassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_stairs", StringUtil.capitalizeWord(material) + " Stairs",
				UpgradedVanilla.ID, new CulledStairBlock(glassBlock.defaultBlockState(), FabricBlockSettings.copyOf(glassBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(glassBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(glassBlock,"_stair_small"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(glassBlock,"_stair_side"))
							.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(glassBlock,"_slab_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(glassBlock));
					ResourceLocation inner = UpgradedVanillaModelTemplates.GLASS_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = UpgradedVanillaModelTemplates.GLASS_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = UpgradedVanillaModelTemplates.GLASS_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(UpgradedVanillaBlockStateGeneration.createStairsBlockState((Block) block, inner, straight, outer, false));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,glassBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.translucent());
		return returnBlock;
	}
	public static Block glassSlabBlock(String material, Block glassBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										  List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(glassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_slab", StringUtil.capitalizeWord(material) + " Slab",
				UpgradedVanilla.ID, new CulledSlabBlock(FabricBlockSettings.copyOf(glassBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(glassBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(glassBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(glassBlock,"_slab_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(glassBlock));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(glassBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,glassBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.translucent());
		return returnBlock;
	}

	public static Block stainedGlassStairsBlock(String material, Block glassBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										 List<TagKey<Item>> itemTags, DyeColor dyeColor) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(glassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_stairs", StringUtil.capitalizeWord(material) + " Stairs",
				UpgradedVanilla.ID, new CulledStainedStairBlock(glassBlock.defaultBlockState(), dyeColor, FabricBlockSettings.copyOf(glassBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(glassBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(glassBlock,"_stair_small"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(glassBlock,"_stair_side"))
							.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(glassBlock,"_slab_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(glassBlock));
					ResourceLocation inner = UpgradedVanillaModelTemplates.GLASS_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = UpgradedVanillaModelTemplates.GLASS_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = UpgradedVanillaModelTemplates.GLASS_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(UpgradedVanillaBlockStateGeneration.createStairsBlockState((Block) block, inner, straight, outer, false));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,glassBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.translucent());
		return returnBlock;
	}
	public static Block stainedGlassSlabBlock(String material, Block glassBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									   List<TagKey<Item>> itemTags, DyeColor dyeColor) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(glassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_slab", StringUtil.capitalizeWord(material) + " Slab",
				UpgradedVanilla.ID, new CulledStainedSlabBlock(dyeColor, FabricBlockSettings.copyOf(glassBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(glassBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(glassBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(glassBlock,"_slab_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(glassBlock));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(glassBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,glassBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.translucent());
		return returnBlock;
	}
}
