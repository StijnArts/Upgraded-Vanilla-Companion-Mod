package drai.dev.upgradedvanilla.modules.minecraft.terracotta.glazed;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.terracotta.*;
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

public class MinecraftGlazedTerracottaBlocks {
	public static Block glazedStairsBlock(String material, Block thatchBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										  List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID, new GlazedTerracottaStairsBlock(thatchBlock.defaultBlockState(), FabricBlockSettings.copyOf(thatchBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(thatchBlock));
					ResourceLocation inner = UpgradedVanillaModelTemplates.GLAZED_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = UpgradedVanillaModelTemplates.GLAZED_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = UpgradedVanillaModelTemplates.GLAZED_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(UpgradedVanillaBlockStateGeneration.createStairsBlockState((Block) block, inner, straight, outer,false));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,thatchBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block glazedSlabBlock(String material, Block thatchBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new GlazedTerracottaSlabBlock(FabricBlockSettings.copyOf(thatchBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(thatchBlock));
					ResourceLocation bottom = UpgradedVanillaModelTemplates.GLAZED_SLAB.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = UpgradedVanillaModelTemplates.GLAZED_SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(UpgradedVanillaBlockStateGeneration.createRotatableSlabBlockState((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(thatchBlock),false));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,thatchBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material, FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}

	public static Block glazedWallBlock(String material, Block thatchBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
										List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(thatchBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.WALL, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(thatchBlock))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(thatchBlock));
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(UpgradedVanillaBlockStateGeneration.createRotatableWall((Block) block, post, lowSide,tallSide, false));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,thatchBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material, FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
}
