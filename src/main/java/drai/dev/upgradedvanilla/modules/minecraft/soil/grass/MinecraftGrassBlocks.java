package drai.dev.upgradedvanilla.modules.minecraft.soil.grass;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.blocks.grass.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.model.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
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
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import javax.xml.transform.*;

import java.util.*;

import static drai.dev.upgradedvanilla.model.UpgradedVanillaBlockStateGeneration.createGrassStairsBlockState;
import static drai.dev.upgradedvanilla.model.UpgradedVanillaBlockStateGeneration.createStairsBlockState;

public class MinecraftGrassBlocks {
	public static Block pathStairsBlock(Block pathBlock, Block dirtBlock, Block dirtSlab, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(pathBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}

		return BlockHandler.registerBlock(Registry.BLOCK.getKey(pathBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new PathBlockStairs(FabricBlockSettings.copyOf(pathBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(pathBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(pathBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation inner = UpgradedVanillaModelTemplates.PATH_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairs = UpgradedVanillaModelTemplates.PATH_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = UpgradedVanillaModelTemplates.PATH_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation innerUp = UpgradedVanillaModelTemplates.PATH_STAIRS_INNER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairsUp = UpgradedVanillaModelTemplates.PATH_STAIRS_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outerUp = UpgradedVanillaModelTemplates.PATH_STAIRS_OUTER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(createGrassStairsBlockState((Block) block, inner, innerUp, stairs, stairsUp, outer, outerUp, false));
					blockModelGenerators.delegateItemModel((Block) block, stairs);
				},
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtSlab));},
				blockTags, itemTags);
	}
	public static Block pathSlabBlock(Block pathBlock, Block dirtBlock, Block dirtSlab, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(pathBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		return BlockHandler.registerBlock(Registry.BLOCK.getKey(pathBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new PathBlockSlab(FabricBlockSettings.copyOf(pathBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(pathBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(pathBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation bottom = UpgradedVanillaModelTemplates.PATH_SLAB.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top = UpgradedVanillaModelTemplates.PATH_SLAB_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(pathBlock)));
				},
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtSlab));},
				blockTags, itemTags);
	}

	public static Block pathWallBlock(Block pathBlock, Block dirtBlock, Block dirtSlab, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(pathBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlock(Registry.BLOCK.getKey(pathBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new PathBlockWall(FabricBlockSettings.copyOf(pathBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(pathBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(pathBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.PATH_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.PATH_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.PATH_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.PATH_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtSlab));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block grassStairsBlock(String material, Block grassBlock, Block dirtBlock, Block dirtStairs, TagKey<Item> grassTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new SpreadableStairsBlock((SpreadingSnowyDirtBlock) grassBlock, (BaseDirtStairs) dirtStairs), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock))
							.put(UpgradedVanillaModelTemplates.OVERLAY, TextureMapping.getBlockTexture(grassBlock,"_side_overlay"));
					ResourceLocation inner = UpgradedVanillaModelTemplates.GRASS_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairs = UpgradedVanillaModelTemplates.GRASS_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = UpgradedVanillaModelTemplates.GRASS_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation innerUp = UpgradedVanillaModelTemplates.GRASS_STAIRS_INNER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairsUp = UpgradedVanillaModelTemplates.GRASS_STAIRS_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outerUp = UpgradedVanillaModelTemplates.GRASS_STAIRS_OUTER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(createGrassStairsBlockState((Block) block, inner, innerUp, stairs, stairsUp, outer, outerUp, false));
					blockModelGenerators.delegateItemModel((Block) block, stairs);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', grassTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_dirt", FabricRecipeProvider.has(grassTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtStairs));},
				blockTags, itemTags);
		UpgradedVanillaClient.addClientSideOperation(()->{ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5, 1.0);
		}, new Block[]{returnBlock});
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return GrassColor.get(0.5, 1.0);
			}, new ItemLike[]{returnBlock.asItem()});});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block grassSlabBlock(String material, Block grassBlock, Block dirtBlock, Block dirtSlab, TagKey<Item> grassTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SpreadableSlabBlock((SpreadingSnowyDirtBlock) grassBlock, (BaseDirtSlab) dirtSlab), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock))
							.put(UpgradedVanillaModelTemplates.OVERLAY, TextureMapping.getBlockTexture(grassBlock,"_side_overlay"));
					ResourceLocation bottom = UpgradedVanillaModelTemplates.GRASS_SLAB.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top = UpgradedVanillaModelTemplates.GRASS_SLAB_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(grassBlock)));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',grassTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(grassTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtSlab));},
				blockTags, itemTags);

		UpgradedVanillaClient.addClientSideOperation(()->{ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5, 1.0);
		}, new Block[]{returnBlock});
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return GrassColor.get(0.5, 1.0);
			}, new ItemLike[]{returnBlock.asItem()});});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block grassWallBlock(String material, Block grassBlock, Block dirtBlock, Block dirtWall,TagKey<Item> grassTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new SpreadableWallBlock((SpreadingSnowyDirtBlock) grassBlock, (BaseDirtWall) dirtWall), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock))
							.put(UpgradedVanillaModelTemplates.OVERLAY, TextureMapping.getBlockTexture(grassBlock,"_side_overlay"));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.GRASS_WALL_INVENTORY_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.GRASS_WALL_POST_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.GRASS_WALL_SIDE_TALL_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.GRASS_WALL_SIDE_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',grassTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(grassTag))
							.save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtWall));},
				blockTags, itemTags);
		UpgradedVanillaClient.addClientSideOperation(()->{ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5, 1.0);
		}, new Block[]{returnBlock});
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return GrassColor.get(0.5, 1.0);
			}, new ItemLike[]{returnBlock.asItem()});});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block podzolStairsBlock(String material, Block grassBlock, Block dirtBlock, Block dirtStairs, TagKey<Item> grassTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new BaseDirtStairs(grassBlock.defaultBlockState(), FabricBlockSettings.copy(grassBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairs = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation innerUp = UpgradedVanillaModelTemplates.COLUMN_STAIRS_INNER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairsUp = UpgradedVanillaModelTemplates.COLUMN_STAIRS_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outerUp = UpgradedVanillaModelTemplates.COLUMN_STAIRS_OUTER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(createGrassStairsBlockState((Block) block, inner, innerUp, stairs, stairsUp, outer, outerUp, false));
					blockModelGenerators.delegateItemModel((Block) block, stairs);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', grassTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_dirt", FabricRecipeProvider.has(grassTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtStairs));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block podzolSlabBlock(String material, Block grassBlock, Block dirtBlock, Block dirtSlab, TagKey<Item> grassTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new BaseDirtSlab(FabricBlockSettings.copyOf(grassBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock))
							.put(UpgradedVanillaModelTemplates.OVERLAY, TextureMapping.getBlockTexture(grassBlock,"_side_overlay"));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(grassBlock)));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',grassTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(grassTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtSlab));},
				blockTags, itemTags);

		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block podzolWallBlock(String material, Block grassBlock, Block dirtBlock, Block dirtWall,TagKey<Item> grassTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new BaseDirtWall(FabricBlockSettings.copyOf(grassBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.WALL_INVENTORY_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.WALL_POST_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.WALL_SIDE_TALL_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.WALL_SIDE_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',grassTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(grassTag))
							.save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtWall));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block myceliumStairsBlock(String material, Block grassBlock, Block dirtBlock, Block dirtStairs, TagKey<Item> grassTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new SpreadableStairsBlock((SpreadingSnowyDirtBlock) grassBlock, (BaseDirtStairs) dirtStairs), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairs = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation innerUp = UpgradedVanillaModelTemplates.COLUMN_STAIRS_INNER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation stairsUp = UpgradedVanillaModelTemplates.COLUMN_STAIRS_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outerUp = UpgradedVanillaModelTemplates.COLUMN_STAIRS_OUTER_UP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(createGrassStairsBlockState((Block) block, inner, innerUp, stairs, stairsUp, outer, outerUp, false));
					blockModelGenerators.delegateItemModel((Block) block, stairs);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', grassTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_dirt", FabricRecipeProvider.has(grassTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtStairs));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block myceliumSlabBlock(String material, Block grassBlock, Block dirtBlock, Block dirtSlab, TagKey<Item> grassTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SpreadableSlabBlock((SpreadingSnowyDirtBlock) grassBlock, (BaseDirtSlab) dirtSlab), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock))
							.put(UpgradedVanillaModelTemplates.OVERLAY, TextureMapping.getBlockTexture(grassBlock,"_side_overlay"));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(grassBlock)));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',grassTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(grassTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtSlab));},
				blockTags, itemTags);

		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block myceliumWallBlock(String material, Block grassBlock, Block dirtBlock, Block dirtWall,TagKey<Item> grassTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(grassBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(grassBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new SpreadableWallBlock((SpreadingSnowyDirtBlock) grassBlock, (BaseDirtWall) dirtWall), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM,TextureMapping.getBlockTexture(dirtBlock))
							.put(TextureSlot.TOP,TextureMapping.getBlockTexture(grassBlock,"_top"))
							.put(TextureSlot.SIDE,TextureMapping.getBlockTexture(grassBlock,"_side"))
							.put(TextureSlot.PARTICLE,TextureMapping.getBlockTexture(dirtBlock));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.WALL_INVENTORY_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.WALL_POST_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.WALL_SIDE_TALL_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.WALL_SIDE_SIDE_BOTTOM_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,grassBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',grassTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(grassTag))
							.save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSingleItemTableWithSilkTouch(block, dirtWall));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
}
