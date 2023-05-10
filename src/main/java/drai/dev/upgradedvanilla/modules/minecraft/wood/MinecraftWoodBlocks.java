package drai.dev.upgradedvanilla.modules.minecraft.wood;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.*;

import javax.imageio.*;

import com.nhoryzon.mc.farmersdelight.recipe.*;
import com.simibubi.create.foundation.data.recipe.*;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blockentities.renderers.*;
import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.blocks.leaves.*;
import drai.dev.upgradedvanilla.blocks.logs.*;
import drai.dev.upgradedvanilla.datageneration.recipes.processing.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.mixin.*;
import drai.dev.upgradedvanilla.model.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.stone.*;
import drai.dev.upgradedvanilla.modules.minecraft.wool.*;
import drai.dev.upgradedvanilla.tag.*;
import net.bunten.enderscape.registry.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;

import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;

import org.apache.commons.io.*;
import org.betterx.bclib.blocks.*;

import static drai.dev.upgradedvanilla.model.UpgradedVanillaModelTemplates.HOOK;
import static drai.dev.upgradedvanilla.model.UpgradedVanillaModelTemplates.LEG;
import static drai.dev.upgradedvanilla.model.UpgradedVanillaModelTemplates.LOG;
import static drai.dev.upgradedvanilla.model.UpgradedVanillaModelTemplates.PIVOT;
import static drai.dev.upgradedvanilla.model.UpgradedVanillaModelTemplates.ROUND;
import static drai.dev.upgradedvanilla.model.UpgradedVanillaModelTemplates.WOOD;
import static net.minecraft.core.Registry.POINT_OF_INTEREST_TYPE;
import static net.minecraft.core.Registry.POINT_OF_INTEREST_TYPE_REGISTRY;
import static net.minecraft.data.loot.BlockLoot.applyExplosionCondition;

public class MinecraftWoodBlocks {
	private ArrayList<Block> signs = new ArrayList<>();
	public static Block plankBlock(String material, Block log, Block strippedLog, Block wood, Block strippedWood, TagKey<Item> logTag, MaterialColor color, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_planks", StringUtil.capitalizeWord(material) + " Planks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.of(Material.WOOD, color).strength(2.0F, 3.0F).sounds(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					//TODO Farmers delight cutting board recipe, Create sawing recipe
					ShapelessRecipeBuilder.shapeless(item,4).requires(logTag).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File plankTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_planks.png");
				TextureHelper.swapColors("block\\"+material+"_planks", "block", UpgradedVanilla.ID,ImageIO.read(plankTextureLocation), TextureHelper.woodPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addCuttingRecipe(returnBlock, (gen, item)->{
			gen.stripAndMakePlanks(log, strippedLog, (Block) item);
			gen.stripAndMakePlanks(wood, strippedWood, (Block) item);
		});
		return returnBlock;
	}

	public static Block netherPlankBlock(String material, Block log, Block strippedLog, Block wood, Block strippedWood, TagKey<Item> logTag, MaterialColor color, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_planks", StringUtil.capitalizeWord(material) + " Planks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.of(Material.WOOD, color).strength(2.0F, 3.0F).sounds(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					//TODO Farmers delight cutting board recipe, Create sawing recipe
					ShapelessRecipeBuilder.shapeless(item,4).requires(logTag).unlockedBy("has_"+material+"_stems", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File plankTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_planks.png");
				TextureHelper.swapColors("block\\"+material+"_planks", "block", UpgradedVanilla.ID, ImageIO.read(plankTextureLocation), TextureHelper.woodPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addCuttingRecipe(returnBlock, (gen, item)->{
			gen.stripAndMakePlanks(log, strippedLog, (Block) item);
			gen.stripAndMakePlanks(wood, strippedWood, (Block) item);
		});
		return returnBlock;
	}

	public static Block endFungusPlankBlock(String material, Block log, Block strippedLog, Block wood, Block strippedWood, TagKey<Item> logTag, MaterialColor color, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_planks", StringUtil.capitalizeWord(material) + " Planks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.of(Material.WOOD, color).strength(2.0F, 3.0F).sounds(EnderscapeSounds.FUNGUS_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				(finishedRecipeConsumer, item) -> {
					//TODO Farmers delight cutting board recipe, Create sawing recipe
					ShapelessRecipeBuilder.shapeless(item,4).requires(logTag).unlockedBy("has_"+material+"_stems", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File plankTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_planks.png");
				TextureHelper.swapColors("block\\"+material+"_planks", "block", UpgradedVanilla.ID, ImageIO.read(plankTextureLocation), TextureHelper.woodPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addCuttingRecipe(returnBlock, (gen, item)->{
			gen.stripAndMakePlanks(log, strippedLog, (Block) item);
			gen.stripAndMakePlanks(wood, strippedWood, (Block) item);
		});
		return returnBlock;
	}

	public static Block logBlock(String material, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlock(material + "_log", StringUtil.capitalizeWord(material) + " Log",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}

	public static Block strippedLogBlock(String material, List<Block> logBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("stripped_" + material + "_log", "Stripped " + StringUtil.capitalizeWord(material) + " Log",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				(finishedRecipeConsumer, item) ->{
					//TODO Sawing recipe, Cutting board recipe
				},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		logBlocks.forEach((log)->StrippableBlockRegistry.register(log,returnBlock));
		return returnBlock;
	}

	public static Block woodBlock(String material, TagKey<Item> logTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String id = material + "_wood";
		return BlockHandler.registerBlockWithRecipe(id, StringUtil.capitalizeWord(material) + " Wood",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,3).define('L',logTag)
							.pattern("LL")
							.pattern("LL")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block strippedWoodBlock(String material, List<Block> woodBlocks, TagKey<Item> logTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("stripped_" + material + "_wood", "Stripped " + StringUtil.capitalizeWord(material) + " Wood",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {
					//TODO Sawing recipe, Cutting board recipe
					ShapedRecipeBuilder.shaped(item,3).define('L',logTag)
							.pattern("LL")
							.pattern("LL")
							.unlockedBy("has_stripped_"+material+"_logs", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		woodBlocks.forEach((log)->StrippableBlockRegistry.register(log,returnBlock));
		return returnBlock;
	}

	public static Block netherStemBlock(String material, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		return BlockHandler.registerBlock(material + "_stem", StringUtil.capitalizeWord(material) + " Stem",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_STEM)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block strippedNetherStemBlock(String material, List<Block> stemBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String id = "stripped_" + material + "_stem";
		Block returnBlock = BlockHandler.registerBlockWithRecipe(id, "Stripped " + StringUtil.capitalizeWord(material) + " Stem",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_STEM)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				(finishedRecipeConsumer, item) -> {
					//TODO Sawing recipe, Cutting board recipe
				},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		stemBlocks.forEach((log)->StrippableBlockRegistry.register(log,returnBlock));
		return returnBlock;
	}

	public static Block netherHyphaeBlock(String material, List<TagKey<Block>> blockTags, TagKey<Item> logTag, List<TagKey<Item>> itemTags) {
		return BlockHandler.registerBlockWithRecipe(material + "_hyphae", StringUtil.capitalizeWord(material) + " Hyphae",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_HYPHAE)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {

					ShapedRecipeBuilder.shaped(item,3).define('L',logTag)
							.pattern("LL")
							.pattern("LL")
							.unlockedBy("has_"+material+"_stems", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block strippedNetherHyphaeBlock(String material, List<Block> hyphaeBlocks, TagKey<Item> logTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("stripped_" + material + "_hyphae", "Stripped " + StringUtil.capitalizeWord(material) + " Hyphae",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_HYPHAE)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {
					//TODO Sawing recipe, Cutting board recipe
					ShapedRecipeBuilder.shaped(item,3).define('L',logTag)
							.pattern("LL")
							.pattern("LL")
							.unlockedBy("has_stripped_"+material+"_stems", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		hyphaeBlocks.forEach((log)->StrippableBlockRegistry.register(log,returnBlock));
		return returnBlock;
	}

	public static Block stemBlock(String material, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		return BlockHandler.registerBlock(material + "_stem", StringUtil.capitalizeWord(material) + " Stem",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},

				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block strippedStemBlock(String material, List<Block> stemBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String id = "stripped_" + material + "_stem";
		Block returnBlock = BlockHandler.registerBlockWithRecipe(id, "Stripped " + StringUtil.capitalizeWord(material) + " Stem",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				(finishedRecipeConsumer, item) -> {
					//TODO Sawing recipe, Cutting board recipe
				},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		stemBlocks.forEach((log)->StrippableBlockRegistry.register(log,returnBlock));
		return returnBlock;
	}

	public static Block hyphaeBlock(String material, List<TagKey<Block>> blockTags, TagKey<Item> logTag, List<TagKey<Item>> itemTags) {
		return BlockHandler.registerBlockWithRecipe(material + "_hyphae", StringUtil.capitalizeWord(material) + " Hyphae",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,3).define('L',logTag)
							.pattern("LL")
							.pattern("LL")
							.unlockedBy("has_"+material+"_stems", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block strippedHyphaeBlock(String material, List<Block> hyphaeBlocks, TagKey<Item> logTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("stripped_" + material + "_hyphae", "Stripped " + StringUtil.capitalizeWord(material) + " Hyphae",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {
					//TODO Sawing recipe, Cutting board recipe
					ShapedRecipeBuilder.shaped(item,3).define('L',logTag)
							.pattern("LL")
							.pattern("LL")
							.unlockedBy("has_stripped_"+material+"_stems", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		hyphaeBlocks.forEach((log)->StrippableBlockRegistry.register(log,returnBlock));
		return returnBlock;
	}

	public static Block logStairsBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID, new StrippableStairsBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(logBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(logBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock,"_side"));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block endStemStairsBlock(String material, Block logBlock, Block hyphaeBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									   List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID, new StrippableStairsBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(hyphaeBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(hyphaeBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(hyphaeBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hyphaeBlock,"_side"));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block endHyphaeStairsBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									   List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID, new StrippableStairsBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock,"_side"));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block strippedLogStairsBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									   List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(strippedLog).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(strippedLog).getPath() + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID,
				new StairBlock(strippedLog.defaultBlockState(),
						FabricBlockSettings.copyOf(logBlock)),
				CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(strippedLog,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(strippedLog,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(strippedLog,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(strippedLog,"_side"));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,strippedLog);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stripped_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block logSlabBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									  List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new StrippableSlabBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(logBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(logBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock,"_side"));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(logBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block endStemSlabBlock(String material, Block logBlock, Block hyphaeBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new StrippableSlabBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(hyphaeBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(hyphaeBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(hyphaeBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hyphaeBlock,"_side"));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(logBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block endHyphaeSlabBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new StrippableSlabBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock,"_side"));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(logBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block strippedLogSlabBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(strippedLog).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(strippedLog).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(strippedLog,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(strippedLog,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(strippedLog,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(strippedLog,"_side"));
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(strippedLog)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,strippedLog,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stripped_logs", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block logWallBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
									  List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new StrippableWallBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(logBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(logBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock,"_side"));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.LOG_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.LOG_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block endHyphaeWallBlock(String material, Block logBlock, Block strippedLog, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new StrippableWallBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(logBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock,"_side"));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.LOG_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.LOG_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block endStemWallBlock(String material, Block logBlock, Block hyphaeBlock, Block strippedLog, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
										   List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new StrippableWallBlock(strippedLog, FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(hyphaeBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(hyphaeBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(hyphaeBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hyphaeBlock,"_side"));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.LOG_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.LOG_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,logBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block strippedLogWallBlock(String material, Block logBlock,  Block strippedLog, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(strippedLog).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(strippedLog).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(strippedLog,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(strippedLog,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(strippedLog,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(strippedLog,"_side"));
					ResourceLocation inventory = UpgradedVanillaModelTemplates.LOG_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.LOG_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,strippedLog);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stripped_logs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block plankStairsBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		return BlockHandler.registerBlockWithRecipe(material + "_stairs", StringUtil.capitalizeWord(material) + " Stairs",
				UpgradedVanilla.ID, new StairBlock(plankBlock.defaultBlockState(), FabricBlockSettings.copyOf(plankBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,plankBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block plankSlabBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		return BlockHandler.registerBlockWithRecipe(material + "_slab", StringUtil.capitalizeWord(material) + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(plankBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(plankBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,plankBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}

	public static Block plankFenceBlock(String material, Block plankBlock, Item stick, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_fence", StringUtil.capitalizeWord(material) + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(plankBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,plankBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',plankTag).define('S',stick)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}

	public static Block plankFenceGateBlock(String material, Block plankBlock, Item stick, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_fence_gate", StringUtil.capitalizeWord(material) + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(plankBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,plankBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',plankTag).define('S',stick)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}

	public static Block plankButtonBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_button", StringUtil.capitalizeWord(material) + " Button",
				UpgradedVanilla.ID, new WoodButtonBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_BUTTON)), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					ShapelessRecipeBuilder.shapeless(item,1).requires(plankTag)
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}

	public static Block plankPressurePlateBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_pressure_plate", StringUtil.capitalizeWord(material) + " Pressure Plate",
				UpgradedVanilla.ID, new WoodenPressurePlateBlock(plankBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{

					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,plankBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',plankTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}

	public static Block plankDoorBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_door", StringUtil.capitalizeWord(material) + " Door",
				UpgradedVanilla.ID, new DoorBlock(FabricBlockSettings.copyOf(plankBlock).nonOpaque()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{

					TextureMapping textureMapping = TextureMapping.door((Block) block);
					ResourceLocation bottom_left = ModelTemplates.DOOR_BOTTOM_LEFT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation bottom_left_open = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation bottom_right = ModelTemplates.DOOR_BOTTOM_RIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation bottom_right_open = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top_left = ModelTemplates.DOOR_TOP_LEFT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top_left_open = ModelTemplates.DOOR_TOP_LEFT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top_right = ModelTemplates.DOOR_TOP_RIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top_right_open = ModelTemplates.DOOR_TOP_RIGHT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createSimpleFlatItemModel(block.asItem());
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createDoor((Block) block, bottom_left,
							bottom_left_open, bottom_right, bottom_right_open, top_left, top_left_open, top_right, top_right_open));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,3).define('P',plankTag)
							.pattern("PP")
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
							.save(finishedRecipeConsumer);
				}),
				((blockLootProvider, block) -> blockLootProvider.add(block, LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block).when(LootItemBlockStatePropertyCondition
								.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))))))),
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block plankTrapdoorBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_trapdoor", StringUtil.capitalizeWord(material) + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(plankBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube((Block) block);
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,2).define('P',plankTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block plankWallBlock(String material, Block plankBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_wall", StringUtil.capitalizeWord(material) + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(plankBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(plankBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,plankBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_planks_slabs", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block ladderBlock(String material, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_ladder", StringUtil.capitalizeWord(material) + " Ladder",
				UpgradedVanilla.ID, new LadderBlock(FabricBlockSettings.copyOf(Blocks.LADDER).sounds(SoundType.LADDER).nonOpaque()), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube((Block) block);
					UpgradedVanillaModelTemplates.LADDER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createNonTemplateHorizontalBlock((Block) block);
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,3).define('S',stick)
							.pattern("S S")
							.pattern("SSS")
							.pattern("S S")
							.unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stick))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File ladderTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_ladder.png");
				TextureHelper.swapColors("block\\"+material+"_ladder", "block", UpgradedVanilla.ID, ImageIO.read(ladderTextureLocation), TextureHelper.woodPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block standingSignBlock(String material, WoodType woodType, Block plankBlock, List<TagKey<Block>> blockTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(material + "_sign",
				UpgradedVanilla.ID, new StandingSignBlock(FabricBlockSettings.copyOf(plankBlock).noCollision(),woodType),
				(blockModelGenerators,block)->{},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File ladderTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\sign\\MATERIAL.png");
				BufferedImage signTexture = TextureHelper.swapColors("entity\\signs\\"+material, "entity\\signs", "minecraft",
						ImageIO.read(ladderTextureLocation), TextureHelper.woodPresetPalette,out);
				File logTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\entity\\signs\\"+material+".png");
				TextureHelper.overlayTexture(signTexture,ImageIO.read(logTextureLocation), 0,16,"entity\\signs\\"+material, "entity\\signs","minecraft");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block wallSignBlock(String material, WoodType woodType, Block plankBlock, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(material + "_wall_sign",
				UpgradedVanilla.ID, new WallSignBlock(FabricBlockSettings.copyOf(plankBlock).noCollision(),woodType),
				(blockModelGenerators,block)->{},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block chestBlock(String material, Block plankBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_chest", StringUtil.capitalizeWord(material) + " Chest",
				UpgradedVanilla.ID, new BaseChestBlock(plankBlock), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					blockModelGenerators.blockEntityModels(ModelLocationUtils.decorateBlockModelLocation("chest"), plankBlock).createWithoutBlockItem((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('P',plankTag)
							.pattern("PPP")
							.pattern("P P")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File chestEntityTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest.png");
				File chestLeftEntityTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest_left.png");
				File chestRightEntityTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest_right.png");
				BufferedImage chestTexture = TextureHelper.swapColors("entity\\chest\\"+material+"_chest", "entity\\chest", UpgradedVanilla.ID,
						ImageIO.read(chestEntityTextureLocation), TextureHelper.woodPresetPalette,out);
				BufferedImage chestLeftTexture = TextureHelper.swapColors("entity\\chest\\"+material+"_chest_left", "entity\\chest", UpgradedVanilla.ID,
						ImageIO.read(chestLeftEntityTextureLocation), TextureHelper.woodPresetPalette,out);
				BufferedImage chestRightTexture = TextureHelper.swapColors("entity\\chest\\"+material+"_chest_right", "entity\\chest", UpgradedVanilla.ID,
						ImageIO.read(chestRightEntityTextureLocation), TextureHelper.woodPresetPalette,out);
				File chestOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\entity\\chest\\MATERIAL_chest.png");
				File chestLeftOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\entity\\chest\\MATERIAL_chest_left.png");
				File chestRightOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\entity\\chest\\MATERIAL_chest_right.png");
				TextureHelper.overlayTexture(chestTexture,ImageIO.read(chestOverlayLocation), 0,0,"entity\\chest\\"+material+"_chest", "entity\\chest", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(chestLeftTexture,ImageIO.read(chestLeftOverlayLocation), 0,0,"entity\\chest\\"+material+"_chest_left", "entity\\chest", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(chestRightTexture,ImageIO.read(chestRightOverlayLocation), 0,0,"entity\\chest\\"+material+"_chest_right", "entity\\chest", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block barrelBlock(String material, Block plankBlock, TagKey<Item> plankTag, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_barrel", StringUtil.capitalizeWord(material) + " Barrel",
				UpgradedVanilla.ID, new BaseBarrelBlock(plankBlock), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					ResourceLocation texture = TextureMapping.getBlockTexture((Block) block, "_top_open");
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(blockModelGenerators.createColumnWithFacing()).with(PropertyDispatch.property(BlockStateProperties.OPEN)
									.select(false, Variant.variant().with(VariantProperties.MODEL, TexturedModel.CUBE_TOP_BOTTOM
											.create((Block) block, blockModelGenerators.modelOutput))).select(true, Variant.variant().with(VariantProperties.MODEL, TexturedModel.CUBE_TOP_BOTTOM.get((Block) block).updateTextures((textureMapping) -> {
						textureMapping.put(TextureSlot.TOP, texture);
					}).createWithSuffix((Block) block, "_open", blockModelGenerators.modelOutput)))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('P',plankTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("P P")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File barrelTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_barrel_top.png");
				File barrelTopOpenTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_barrel_top_open.png");
				File barrelSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_barrel_side.png");
				File barrelBottomTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_barrel_bottom.png");
				BufferedImage barrelTopTexture = TextureHelper.swapColors("block\\"+material+"_barrel_top", "block", UpgradedVanilla.ID,
						ImageIO.read(barrelTopTextureLocation), TextureHelper.woodPresetPalette, out);
				BufferedImage barrelTopOpenTexture = TextureHelper.swapColors("block\\"+material+"_barrel_top_open", "block", UpgradedVanilla.ID,
						ImageIO.read(barrelTopOpenTextureLocation), TextureHelper.woodPresetPalette, out);
				BufferedImage barrelSideTexture = TextureHelper.swapColors("block\\"+material+"_barrel_side", "block", UpgradedVanilla.ID,
						ImageIO.read(barrelSideTextureLocation), TextureHelper.woodPresetPalette, out);
				BufferedImage barrelBottomTexture = TextureHelper.swapColors("block\\"+material+"_barrel_bottom", "block", UpgradedVanilla.ID,
						ImageIO.read(barrelBottomTextureLocation), TextureHelper.woodPresetPalette, out);
				File barrelTopOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_barrel_top.png");
				File barrelSideOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_barrel_side.png");
				TextureHelper.overlayTexture(barrelTopTexture,ImageIO.read(barrelTopOverlayLocation), 0,0,"block\\"+material+"_barrel_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(barrelSideTexture,ImageIO.read(barrelSideOverlayLocation), 0,0,"block\\"+material+"_barrel_side", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block leverBlockSet(String woodMaterial,Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out){
		leverBlock(woodMaterial, "deepslate", Blocks.COBBLED_DEEPSLATE, Deepslate.DEEPSLATE_BLOCKS_ITEM_TAG,
				stick, blockTags, itemTags, out, SoundType.DEEPSLATE);

		return leverBlock(woodMaterial, "stone", Blocks.COBBLESTONE, Stone.COBBLESTONE_BLOCKS_ITEM_TAG,
				stick, blockTags, itemTags, out, SoundType.STONE);
	}

	public static Block leverBlock(String woodMaterial, String stoneMaterial, Block cobbledStoneBlock, TagKey<Item> stoneTag,
								   Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(woodMaterial + "_" + stoneMaterial +"_lever", StringUtil.capitalizeWord(woodMaterial) + " " + StringUtil.capitalizeWord(stoneMaterial) + " Lever",
				UpgradedVanilla.ID, new LeverBlock(FabricBlockSettings.copyOf(Blocks.LEVER).noOcclusion().noCollission()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(cobbledStoneBlock))
							.put(UpgradedVanillaModelTemplates.BASE, TextureMapping.getBlockTexture(cobbledStoneBlock))
							.put(UpgradedVanillaModelTemplates.LEVER, TextureMapping.getBlockTexture((Block) block));
					ResourceLocation leverOn =UpgradedVanillaModelTemplates.LEVER_ON.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation leverOff =UpgradedVanillaModelTemplates.LEVER_OFF.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block)block)
							.with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.POWERED, leverOff, leverOn))
							.with(PropertyDispatch.properties(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
									.select(AttachFace.CEILING, Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).select(AttachFace.CEILING, Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).select(AttachFace.CEILING, Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
									.select(AttachFace.CEILING, Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.FLOOR, Direction.NORTH, Variant.variant()).select(AttachFace.FLOOR, Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.FLOOR, Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).select(AttachFace.FLOOR, Direction.WEST, Variant.variant()
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).select(AttachFace.WALL, Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.WALL, Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.WALL, Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
									.select(AttachFace.WALL, Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('S',stick).define('R',stoneTag)
							.pattern("S")
							.pattern("R")
							.unlockedBy("has_"+woodMaterial+"_sticks", FabricRecipeProvider.has(stick))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File leverTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_lever.png");
				BufferedImage leverTexture = TextureHelper.swapColors("block\\"+woodMaterial + "_" + stoneMaterial +"_lever", "block", UpgradedVanilla.ID, ImageIO.read(leverTextureLocation), TextureHelper.woodPresetPalette,out);
				File leverStonePart = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\MATERIAL_lever.png");
				BufferedImage leverStonePartTexture = TextureHelper.swapColors("block\\"+woodMaterial + "_" + stoneMaterial +"_lever", "block", UpgradedVanilla.ID, ImageIO.read(leverStonePart), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				TextureHelper.overlayTexture(leverTexture,
						leverStonePartTexture, 0,0,"block\\"+woodMaterial + "_" + stoneMaterial +"_lever", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block wallTorchBlock(String woodMaterial, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(woodMaterial + "_wall_torch",
				UpgradedVanilla.ID, new WallTorchBlock(FabricBlockSettings.copyOf(Blocks.TORCH), ParticleTypes.FLAME),
				(blockModelGenerators,block)->{

				},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block torchBlock(String woodMaterial, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(woodMaterial + "_torch",
				UpgradedVanilla.ID, new TorchBlock(FabricBlockSettings.copyOf(Blocks.TORCH), ParticleTypes.FLAME),
				(blockModelGenerators,block)->{},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block wallSoulTorchBlock(String woodMaterial, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(woodMaterial + "_wall_soul_torch",
				UpgradedVanilla.ID, new WallTorchBlock(FabricBlockSettings.copyOf(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME),
				(blockModelGenerators,block)->{

				},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block soulTorchBlock(String woodMaterial, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(woodMaterial + "_soul_torch",
				UpgradedVanilla.ID, new TorchBlock(FabricBlockSettings.copyOf(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME),
				(blockModelGenerators,block)->{},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block wallRedstoneTorchBlock(String woodMaterial, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(woodMaterial + "_wall_redstone_torch",
				UpgradedVanilla.ID, new RedstoneWallTorchBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_TORCH)),
				(blockModelGenerators,block)->{

				},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block redstoneTorchBlock(String woodMaterial, List<TagKey<Block>> blockTags) {
		Block returnBlock = BlockHandler.registerBlockWithoutItem(woodMaterial + "_redstone_torch",
				UpgradedVanilla.ID, new RedstoneTorchBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_TORCH)),
				(blockModelGenerators,block)->{},
				BlockLoot::dropSelf,
				blockTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block craftingTableBlock(String material, Block plankBlock, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_crafting_table", StringUtil.capitalizeWord(material) + " Crafting Table",
				UpgradedVanilla.ID, new BaseCraftingTableBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.craftingTable((Block) block, plankBlock);
					ResourceLocation model = ModelTemplates.CUBE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block,model ));
					blockModelGenerators.delegateItemModel((Block) block,model);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('S',plankBlock)
							.pattern("SS")
							.pattern("SS")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankBlock))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File craftingTableFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_crafting_table_front.png");
				File craftingTableSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_crafting_table_side.png");
				File craftingTableTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_crafting_table_top.png");
				BufferedImage craftingTableFrontTexture = TextureHelper.swapColors("block\\"+material+"_crafting_table_front", "block", UpgradedVanilla.ID, ImageIO.read(craftingTableFrontTextureLocation), TextureHelper.woodPresetPalette,out);
				File craftingTableFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_crafting_table_front.png");
				TextureHelper.overlayTexture(craftingTableFrontTexture,ImageIO.read(craftingTableFrontOverlayLocation), 0,0,"block\\"+material +"_crafting_table_front", "block", UpgradedVanilla.ID);
				BufferedImage craftingTableSideTexture = TextureHelper.swapColors("block\\"+material+"_crafting_table_side", "block", UpgradedVanilla.ID, ImageIO.read(craftingTableSideTextureLocation), TextureHelper.woodPresetPalette,out);
				File craftingTableSideOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_crafting_table_side.png");
				TextureHelper.overlayTexture(craftingTableSideTexture,ImageIO.read(craftingTableSideOverlayLocation), 0,0,"block\\"+material +"_crafting_table_side", "block", UpgradedVanilla.ID);
				BufferedImage craftingTableTopTexture = TextureHelper.swapColors("block\\"+material+"_crafting_table_top", "block", UpgradedVanilla.ID, ImageIO.read(craftingTableTopTextureLocation), TextureHelper.woodPresetPalette,out);
				File craftingTableTopOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_crafting_table_top.png");
				TextureHelper.overlayTexture(craftingTableTopTexture,ImageIO.read(craftingTableTopOverlayLocation), 0,0,"block\\"+material +"_crafting_table_top", "block", UpgradedVanilla.ID);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block campfireBlock(String material, TagKey<Item> logs, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_campfire", StringUtil.capitalizeWord(material) + " Campfire",
				UpgradedVanilla.ID, new CampfireBlock(true,1,FabricBlockSettings.copyOf(Blocks.CAMPFIRE).nonOpaque()), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					ResourceLocation off = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_campfire_log");
					ResourceLocation lit = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_campfire_log_lit");
					TextureMapping textureMappingOff = (new TextureMapping()).put(TextureSlot.PARTICLE, off)
							.put(LOG, off);
					TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.FIRE, new ResourceLocation("minecraft","block/campfire_fire"))
							.put(TextureSlot.LIT_LOG, lit);
					ResourceLocation campfireTemplate = UpgradedVanillaModelTemplates.CAMPFIRE_TEMPLATE.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					ModelTemplate campfireModel = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/"+material+"_campfire_template")),
							Optional.of(""),TextureSlot.FIRE,TextureSlot.LIT_LOG);
					ResourceLocation campfire = campfireModel.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation campfireOff = UpgradedVanillaModelTemplates.CAMPFIRE_OFF.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, campfire, campfireOff)).with(BlockModelGenerators.createHorizontalFacingDispatchAlt()));
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('L',logs).define('C',ItemTags.COALS).define('S',stick)
							.pattern(" S ")
							.pattern("SCS")
							.pattern("LLL")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(logs))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File campfireLogTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_campfire_log.png");
				File campfireLogLitTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_campfire_log_lit.png");
				File campfireItemTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\MATERIAL_campfire.png");
				FileUtils.copyFile(new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_campfire_log_lit.png.mcmeta"),
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\" +
								"create-fabric-addon-template-1.19\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\" + material + "_campfire_log_lit.png.mcmeta"));
				BufferedImage campfireLogTexture = TextureHelper.swapColors("block\\"+material+"_campfire_log", "block", UpgradedVanilla.ID, ImageIO.read(campfireLogTextureLocation), TextureHelper.woodPresetPalette,out);
				File campfireLogOverLayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_campfire_log.png");
				TextureHelper.overlayTexture(campfireLogTexture,ImageIO.read(campfireLogOverLayLocation), 0,0,"block\\"+material +"_campfire_log", "block", UpgradedVanilla.ID);
				BufferedImage campfireLogLitTexture = TextureHelper.swapColors("block\\"+material+"_campfire_log_lit", "block", UpgradedVanilla.ID, ImageIO.read(campfireLogLitTextureLocation), TextureHelper.woodPresetPalette,out);
				File campfireLogLitOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_campfire_log_lit.png");
				TextureHelper.overlayTexture(campfireLogLitTexture,ImageIO.read(campfireLogLitOverlayLocation), 0,0,"block\\"+material +"_campfire_log_lit", "block", UpgradedVanilla.ID);
				BufferedImage craftingTableTopTexture = TextureHelper.swapColors("block\\"+material+"_campfire", "block", UpgradedVanilla.ID, ImageIO.read(campfireItemTextureLocation), TextureHelper.woodPresetPalette,out);
				File craftingTableTopOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\item\\MATERIAL_campfire.png");
				TextureHelper.overlayTexture(craftingTableTopTexture,ImageIO.read(craftingTableTopOverlayLocation), 0,0,"block\\"+material +"_campfire", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block soulCampfireBlock(String material, Block campfireBlock, TagKey<Item> logs, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_soul_campfire", StringUtil.capitalizeWord(material) + " Soul Campfire",
				UpgradedVanilla.ID, new CampfireBlock(false,2,FabricBlockSettings.copyOf(Blocks.SOUL_CAMPFIRE).nonOpaque()), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					ResourceLocation off = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_campfire_log");
					ResourceLocation lit = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_soul_campfire_log_lit");
					TextureMapping textureMappingOff = (new TextureMapping()).put(TextureSlot.PARTICLE, off)
							.put(LOG, off);
					TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.FIRE, new ResourceLocation("minecraft","block/soul_campfire_fire"))
							.put(TextureSlot.LIT_LOG, lit);
					ModelTemplate campfireModel = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/"+material+"_campfire_template")),
							Optional.of(""),TextureSlot.FIRE,TextureSlot.LIT_LOG);
					ResourceLocation campfire = campfireModel.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation campfireOff = UpgradedVanillaModelTemplates.CAMPFIRE_OFF.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, campfire, campfireOff)).with(BlockModelGenerators.createHorizontalFacingDispatchAlt()));
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					//TODO Haunting Recipe
					ShapedRecipeBuilder.shaped(item,1).define('L',logs).define('C',ItemTags.SOUL_FIRE_BASE_BLOCKS).define('S',stick)
							.pattern(" S ")
							.pattern("SCS")
							.pattern("LLL")
							.unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(logs))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File soulCampfireLogLitTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_soul_campfire_log_lit.png");
				File soulCampfireItemTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\MATERIAL_soul_campfire.png");
				FileUtils.copyFile(new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_soul_campfire_log_lit.png.mcmeta"),
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\" +
								"create-fabric-addon-template-1.19\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\" + material + "_soul_campfire_log_lit.png.mcmeta"));
				BufferedImage soulCampfireLogLitTexture = TextureHelper.swapColors("block\\"+material+"_soul_campfire_log_lit", "block", UpgradedVanilla.ID, ImageIO.read(soulCampfireLogLitTextureLocation), TextureHelper.woodPresetPalette,out);
				File soulCampfireLogLitOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_soul_campfire_log_lit.png");
				TextureHelper.overlayTexture(soulCampfireLogLitTexture,ImageIO.read(soulCampfireLogLitOverlayLocation), 0,0,"block\\"+material +"_soul_campfire_log_lit", "block", UpgradedVanilla.ID);
				BufferedImage campfireItemTexture = TextureHelper.swapColors("block\\"+material+"_soul_campfire", "block", UpgradedVanilla.ID, ImageIO.read(soulCampfireItemTextureLocation), TextureHelper.woodPresetPalette,out);
				File soulCampfireItemOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\item\\MATERIAL_soul_campfire.png");
				TextureHelper.overlayTexture(campfireItemTexture,ImageIO.read(soulCampfireItemOverlayLocation), 0,0,"block\\"+material +"_soul_campfire", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addHauntingRecipe(returnBlock,(gen, item)->{gen.convert(campfireBlock,item);});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block bookshelfBlock(String material, Block plankBlock, TagKey<Item> planks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_bookshelf", StringUtil.capitalizeWord(material) + " Bookshelf",
				UpgradedVanilla.ID, new BaseBookshelfBlock(FabricBlockSettings.copyOf(plankBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.END, TextureMapping.getBlockTexture(plankBlock))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture((Block) block));
					ResourceLocation bookshelf = ModelTemplates.CUBE_COLUMN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, bookshelf));
					blockModelGenerators.delegateItemModel((Block) block,bookshelf);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('B',Items.BOOK).define('P',planks)
							.pattern("PPP")
							.pattern("BBB")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(planks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File bookshelfTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_bookshelf.png");
				BufferedImage bookshelfTexture = TextureHelper.swapColors("block\\"+material+"_bookshelf", "block", UpgradedVanilla.ID, ImageIO.read(bookshelfTextureLocation), TextureHelper.woodPresetPalette,out);
				File bookshelfOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_bookshelf.png");
				TextureHelper.overlayTexture(bookshelfTexture,ImageIO.read(bookshelfOverlayLocation), 0,0,"block\\"+material +"_bookshelf", "block", UpgradedVanilla.ID);
				} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block lecternBlock(String material, Block plankBlock, Block slabBlock, TagKey<Item> bookshelf, TagKey<Item> slabs, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_lectern", StringUtil.capitalizeWord(material) + " Lectern",
				UpgradedVanilla.ID, new LecternBlock(FabricBlockSettings.copyOf(slabBlock)), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					ResourceLocation particle = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_lectern_sides");
					ResourceLocation bottom = TextureMapping.getBlockTexture(plankBlock);
					ResourceLocation base = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_lectern_base");
					ResourceLocation front = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_lectern_front");
					ResourceLocation sides = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_lectern_sides");
					ResourceLocation top = new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_lectern_top");
					TextureMapping textureMapping = (new TextureMapping())
							.put(TextureSlot.PARTICLE, particle)
							.put(TextureSlot.BOTTOM, bottom)
							.put(UpgradedVanillaModelTemplates.BASE, base)
							.put(TextureSlot.FRONT, front)
							.put(UpgradedVanillaModelTemplates.SIDES, sides)
							.put(TextureSlot.TOP, top);
					ResourceLocation lecternModel = UpgradedVanillaModelTemplates.LECTERN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block, Variant.variant().with(VariantProperties.MODEL, lecternModel)).with(BlockModelGenerators.createHorizontalFacingDispatch()));
					blockModelGenerators.delegateItemModel((Block) block,lecternModel);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('B',bookshelf).define('S',slabs)
							.pattern("SSS")
							.pattern(" B ")
							.pattern(" S ")
							.unlockedBy("has_"+material+"_bookshelf", FabricRecipeProvider.has(bookshelf))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(()->{
			try {
				File lecternSidesTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_lectern_sides.png");
				File lecternBaseTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_lectern_base.png");
				File lecternFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_lectern_front.png");
				File lecternTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_lectern_top.png");
				BufferedImage lecternSidesTexture = TextureHelper.swapColors("block\\"+material+"_lectern_sides", "block", UpgradedVanilla.ID, ImageIO.read(lecternSidesTextureLocation), TextureHelper.woodPresetPalette,out);
				BufferedImage lecternTopTexture = TextureHelper.swapColors("block\\"+material+"_lectern_top", "block", UpgradedVanilla.ID, ImageIO.read(lecternTopTextureLocation), TextureHelper.woodPresetPalette,out);
				BufferedImage lecternBaseTexture = TextureHelper.swapColors("block\\"+material+"_lectern_base", "block", UpgradedVanilla.ID, ImageIO.read(lecternBaseTextureLocation), TextureHelper.woodPresetPalette,out);
				File soul_campfireLogLitOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_lectern_base.png");
				TextureHelper.overlayTexture(lecternBaseTexture,ImageIO.read(soul_campfireLogLitOverlayLocation), 0,0,"block\\"+material +"_lectern_base", "block", UpgradedVanilla.ID);
				BufferedImage lecternFrontTexture = TextureHelper.swapColors("block\\"+material+"_lectern_front", "block", UpgradedVanilla.ID, ImageIO.read(lecternFrontTextureLocation), TextureHelper.woodPresetPalette,out);
				File lecternFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_lectern_front.png");
				TextureHelper.overlayTexture(lecternFrontTexture,ImageIO.read(lecternFrontOverlayLocation), 0,0,"block\\"+material +"_lectern_front", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block railBlock(String material, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_rail", StringUtil.capitalizeWord(material) + " Rail",
				UpgradedVanilla.ID, new RailBlock(FabricBlockSettings.copyOf(Blocks.RAIL).nonOpaque().noCollision()), CreativeModeTab.TAB_TRANSPORTATION,
				(blockModelGenerators,block)->{
					TextureMapping straightMapping = TextureMapping.rail((Block) block);
					TextureMapping cornerMapping = TextureMapping.rail(TextureMapping.getBlockTexture((Block) block,"_corner"));
					ResourceLocation straightModel = ModelTemplates.RAIL_FLAT.create((Block) block, straightMapping, blockModelGenerators.modelOutput);
					ResourceLocation raisedNEModel = ModelTemplates.RAIL_RAISED_NE.create((Block) block, straightMapping, blockModelGenerators.modelOutput);
					ResourceLocation raisedSWModel = ModelTemplates.RAIL_RAISED_SW.create((Block) block, straightMapping, blockModelGenerators.modelOutput);
					ResourceLocation cornerModel = ModelTemplates.RAIL_CURVED.create((Block) block, cornerMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(PropertyDispatch.property(BlockStateProperties.RAIL_SHAPE).select(RailShape.NORTH_SOUTH, Variant.variant().with(VariantProperties.MODEL, straightModel)).select(RailShape.EAST_WEST, Variant.variant().with(VariantProperties.MODEL, straightModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(RailShape.ASCENDING_EAST, Variant.variant().with(VariantProperties.MODEL, raisedNEModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(RailShape.ASCENDING_WEST, Variant.variant().with(VariantProperties.MODEL, raisedSWModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(RailShape.ASCENDING_NORTH, Variant.variant().with(VariantProperties.MODEL, raisedNEModel)).select(RailShape.ASCENDING_SOUTH, Variant.variant().with(VariantProperties.MODEL, raisedSWModel)).select(RailShape.SOUTH_EAST, Variant.variant().with(VariantProperties.MODEL, cornerModel)).select(RailShape.SOUTH_WEST, Variant.variant().with(VariantProperties.MODEL, cornerModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(RailShape.NORTH_WEST, Variant.variant().with(VariantProperties.MODEL, cornerModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).select(RailShape.NORTH_EAST, Variant.variant().with(VariantProperties.MODEL, cornerModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,16).define('S',stick).define('I', UVCommonItemTags.IRON_INGOTS)
							.pattern("I I")
							.pattern("ISI")
							.pattern("I I")
							.unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stick))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File railTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_rail.png");
				File railCurveTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_rail_corner.png");
				BufferedImage railTexture = TextureHelper.swapColors("block\\"+material+"_rail", "block", UpgradedVanilla.ID, ImageIO.read(railTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_rail.png");
				TextureHelper.overlayTexture(railTexture,ImageIO.read(railOverlayLocation), 0,0,"block\\"+material +"_rail", "block", UpgradedVanilla.ID);
				BufferedImage railCurveTexture = TextureHelper.swapColors("block\\"+material+"_rail_corner", "block", UpgradedVanilla.ID, ImageIO.read(railCurveTextureLocation), TextureHelper.woodPresetPalette,out);
				File railCurveOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_rail_corner.png");
				TextureHelper.overlayTexture(railCurveTexture,ImageIO.read(railCurveOverlayLocation), 0,0,"block\\"+material +"_rail_corner", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block poweredRailBlock(String material, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_powered_rail", StringUtil.capitalizeWord(material) + " Powered Rail",
				UpgradedVanilla.ID, new VariantPoweredRailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7f).sound(SoundType.METAL)), CreativeModeTab.TAB_TRANSPORTATION,
				(blockModelGenerators,block)->{
					ResourceLocation resourceLocation = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_FLAT, TextureMapping::rail);
					ResourceLocation resourceLocation2 = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail);
					ResourceLocation resourceLocation3 = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail);
					ResourceLocation resourceLocation4 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_FLAT, TextureMapping::rail);
					ResourceLocation resourceLocation5 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail);
					ResourceLocation resourceLocation6 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail);
					PropertyDispatch propertyDispatch = PropertyDispatch.properties(BlockStateProperties.POWERED, BlockStateProperties.RAIL_SHAPE_STRAIGHT).generate((boolean_, railShape) -> {
						switch (railShape) {
							case NORTH_SOUTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation4 : resourceLocation);
							}
							case EAST_WEST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation4 : resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_EAST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation5 : resourceLocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_WEST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation6 : resourceLocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_NORTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation5 : resourceLocation2);
							}
							case ASCENDING_SOUTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation6 : resourceLocation3);
							}
						}
						throw new UnsupportedOperationException("Fix you generator!");
					});
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(propertyDispatch));},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,6).define('S',stick).define('I',Items.GOLD_INGOT).define('R',Items.REDSTONE)
							.pattern("I I")
							.pattern("ISI")
							.pattern("IRI")
							.unlockedBy("has_gold_ingots", FabricRecipeProvider.has(Items.GOLD_INGOT))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File railTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_powered_rail.png");
				File railOnTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_powered_rail_on.png");
				BufferedImage railTexture = TextureHelper.swapColors("block\\"+material+"_powered_rail", "block", UpgradedVanilla.ID, ImageIO.read(railTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_powered_rail.png");
				TextureHelper.overlayTexture(railTexture,ImageIO.read(railOverlayLocation), 0,0,"block\\"+material +"_powered_rail", "block", UpgradedVanilla.ID);
				BufferedImage railOnTexture = TextureHelper.swapColors("block\\"+material+"_powered_rail_on", "block", UpgradedVanilla.ID, ImageIO.read(railOnTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOnOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_powered_rail_on.png");
				TextureHelper.overlayTexture(railOnTexture,ImageIO.read(railOnOverlayLocation), 0,0,"block\\"+material +"_powered_rail_on", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block activatorRailBlock(String material, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_activator_rail", StringUtil.capitalizeWord(material) + " Activator Rail",
				UpgradedVanilla.ID, new VariantActivatorRailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7f).sound(SoundType.METAL)), CreativeModeTab.TAB_TRANSPORTATION,
				(blockModelGenerators,block)->{
					ResourceLocation resourceLocation = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_FLAT, TextureMapping::rail);
					ResourceLocation resourceLocation2 = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail);
					ResourceLocation resourceLocation3 = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail);
					ResourceLocation resourceLocation4 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_FLAT, TextureMapping::rail);
					ResourceLocation resourceLocation5 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail);
					ResourceLocation resourceLocation6 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail);
					PropertyDispatch propertyDispatch = PropertyDispatch.properties(BlockStateProperties.POWERED, BlockStateProperties.RAIL_SHAPE_STRAIGHT).generate((boolean_, railShape) -> {
						switch (railShape) {
							case NORTH_SOUTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation4 : resourceLocation);
							}
							case EAST_WEST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation4 : resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_EAST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation5 : resourceLocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_WEST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation6 : resourceLocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_NORTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation5 : resourceLocation2);
							}
							case ASCENDING_SOUTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation6 : resourceLocation3);
							}
						}
						throw new UnsupportedOperationException("Fix you generator!");
					});
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(propertyDispatch));},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,6).define('S',stick).define('I',UVCommonItemTags.IRON_INGOTS).define('T', UpgradedVanillaTags.REDSTONE_TORCH_ITEM_TAG)
							.pattern("ISI")
							.pattern("ITI")
							.pattern("ISI")
							.unlockedBy("has_redstone_torch", FabricRecipeProvider.has(UpgradedVanillaTags.REDSTONE_TORCH_ITEM_TAG))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File railTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_activator_rail.png");
				File railOnTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_activator_rail_on.png");
				BufferedImage railTexture = TextureHelper.swapColors("block\\"+material+"_activator_rail", "block", UpgradedVanilla.ID, ImageIO.read(railTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_activator_rail.png");
				TextureHelper.overlayTexture(railTexture,ImageIO.read(railOverlayLocation), 0,0,"block\\"+material +"_activator_rail", "block", UpgradedVanilla.ID);
				BufferedImage railOnTexture = TextureHelper.swapColors("block\\"+material+"_activator_rail_on", "block", UpgradedVanilla.ID, ImageIO.read(railOnTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOnOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_activator_rail_on.png");
				TextureHelper.overlayTexture(railOnTexture,ImageIO.read(railOnOverlayLocation), 0,0,"block\\"+material +"_activator_rail_on", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block detectorRailBlock(String material, Item stick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_detector_rail", StringUtil.capitalizeWord(material) + " Detector Rail",
				UpgradedVanilla.ID, new DetectorRailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7f).sound(SoundType.METAL)), CreativeModeTab.TAB_TRANSPORTATION,
				(blockModelGenerators,block)->{
					ResourceLocation resourceLocation = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_FLAT, TextureMapping::rail);
					ResourceLocation resourceLocation2 = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail);
					ResourceLocation resourceLocation3 = blockModelGenerators.createSuffixedVariant((Block) block, "", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail);
					ResourceLocation resourceLocation4 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_FLAT, TextureMapping::rail);
					ResourceLocation resourceLocation5 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail);
					ResourceLocation resourceLocation6 = blockModelGenerators.createSuffixedVariant((Block) block, "_on", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail);
					PropertyDispatch propertyDispatch = PropertyDispatch.properties(BlockStateProperties.POWERED, BlockStateProperties.RAIL_SHAPE_STRAIGHT).generate((boolean_, railShape) -> {
						switch (railShape) {
							case NORTH_SOUTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation4 : resourceLocation);
							}
							case EAST_WEST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation4 : resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_EAST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation5 : resourceLocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_WEST: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation6 : resourceLocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
							}
							case ASCENDING_NORTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation5 : resourceLocation2);
							}
							case ASCENDING_SOUTH: {
								return Variant.variant().with(VariantProperties.MODEL, boolean_ != false ? resourceLocation6 : resourceLocation3);
							}
						}
						throw new UnsupportedOperationException("Fix you generator!");
					});
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(propertyDispatch));},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,6).define('S',stick).define('I',UVCommonItemTags.IRON_INGOTS).define('T', Items.REDSTONE).define('P',UpgradedVanillaTags.STONE_PRESSURE_PLATE_ITEMS)
							.pattern("ISI")
							.pattern("ITI")
							.pattern("IPI")
							.unlockedBy("has_redstone", FabricRecipeProvider.has(Items.REDSTONE))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(()->{
			try {
				File railTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_detector_rail.png");
				File railOnTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_detector_rail_on.png");
				BufferedImage railTexture = TextureHelper.swapColors("block\\"+material+"_detector_rail", "block", UpgradedVanilla.ID, ImageIO.read(railTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_detector_rail.png");
				TextureHelper.overlayTexture(railTexture,ImageIO.read(railOverlayLocation), 0,0,"block\\"+material +"_detector_rail", "block", UpgradedVanilla.ID);
				BufferedImage railOnTexture = TextureHelper.swapColors("block\\"+material+"_detector_rail_on", "block", UpgradedVanilla.ID, ImageIO.read(railOnTextureLocation), TextureHelper.woodPresetPalette,out);
				File railOnOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_detector_rail_on.png");
				TextureHelper.overlayTexture(railOnTexture,ImageIO.read(railOnOverlayLocation), 0,0,"block\\"+material +"_detector_rail_on", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block bedBlock(String material, DyeColor color, Block woolBlock, TagKey<Item> plankBlocks, TagKey<Item> woolBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerNonStackableBlockWithRecipe(color + "_"+material+"_bed", StringUtil.capitalizeWord(color.getName())+" "+StringUtil.capitalizeWord(material) + " Bed",
				UpgradedVanilla.ID, new BaseBedBlock(color,
						FabricBlockSettings.of(Material.WOOL, state -> state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMaterialColor() : MaterialColor.WOOL).sound(SoundType.WOOD).strength(0.2f).noOcclusion()), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping bedMapping = new TextureMapping().put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(woolBlock));

					ResourceLocation particle = ModelTemplates.PARTICLE_ONLY.create((Block) block,bedMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block, Variant.variant().with(VariantProperties.MODEL,particle)));
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('P',plankBlocks).define('W',woolBlocks)
							.pattern("WWW")
							.pattern("PPP")
							.unlockedBy("has_"+color.getName()+"_wool", FabricRecipeProvider.has(woolBlocks))
							.save(finishedRecipeConsumer);
				}),
				(blockLootProvider,block)->blockLootProvider.add(block,BlockLoot.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)),
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());

		TextureHelper.addTexture(()->{
			try {
				File bedBaseTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\bed\\bed_base.png");
				File bedItemBaseTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\bed_base.png");
				BufferedImage bedTexture = TextureHelper.swapColors("entity\\bed\\"+color.getName()+"_"+material+"_bed", "entity\\bed", UpgradedVanilla.ID, ImageIO.read(bedBaseTextureLocation), TextureHelper.woodPresetPalette,out);
				File bedOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\entity\\bed\\"+color.getName()+".png");
				TextureHelper.overlayTexture(bedTexture,ImageIO.read(bedOverlayLocation), 0,0,"entity\\bed\\"+color.getName()+"_"+material+"_bed", "entity\\bed", UpgradedVanilla.ID);
				BufferedImage bedItemTexture = TextureHelper.swapColors("block\\"+color.getName()+"_"+material+"_bed", "block", UpgradedVanilla.ID, ImageIO.read(bedItemBaseTextureLocation), TextureHelper.woodPresetPalette,out);
				File bedItemOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\item\\bed\\"+color.getName()+".png");
				TextureHelper.overlayTexture(bedItemTexture,ImageIO.read(bedItemOverlayLocation), 0,0,"block\\"+color.getName()+"_"+material+"_bed", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		BaseBedBlockEntityRenderer.registerRenderLayer(returnBlock);
		return returnBlock;
	}

	public static Block bedBlockSet(String material, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out){
		bedBlock(material, DyeColor.BLACK,Blocks.BLACK_WOOL,plankBlocks, Black.BLACK_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.BLUE,Blocks.BLUE_WOOL,plankBlocks, Blue.BLUE_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.BROWN,Blocks.BROWN_WOOL,plankBlocks, Brown.BROWN_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.CYAN,Blocks.CYAN_WOOL,plankBlocks, Cyan.CYAN_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.GRAY,Blocks.GRAY_WOOL,plankBlocks, Gray.GRAY_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.GREEN,Blocks.GREEN_WOOL,plankBlocks, Green.GREEN_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.LIGHT_BLUE,Blocks.LIGHT_BLUE_WOOL,plankBlocks, LightBlue.LIGHT_BLUE_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.LIGHT_GRAY,Blocks.LIGHT_GRAY_WOOL,plankBlocks, LightGray.LIGHT_GRAY_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.LIME,Blocks.LIME_WOOL,plankBlocks, Lime.LIME_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.MAGENTA,Blocks.MAGENTA_WOOL,plankBlocks, Magenta.MAGENTA_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.ORANGE,Blocks.ORANGE_WOOL,plankBlocks, Orange.ORANGE_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.PINK,Blocks.PINK_WOOL,plankBlocks, Pink.PINK_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.PURPLE,Blocks.PURPLE_WOOL,plankBlocks, Purple.PURPLE_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.RED,Blocks.RED_WOOL,plankBlocks, Red.RED_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		bedBlock(material, DyeColor.YELLOW,Blocks.YELLOW_WOOL,plankBlocks, Yellow.YELLOW_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
		return bedBlock(material, DyeColor.WHITE,Blocks.WHITE_WOOL,plankBlocks, White.WHITE_WOOL_BLOCKS_ITEM_TAG, blockTags,itemTags,out);
	}

	public static Block beehiveBlock(String material, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_beehive", StringUtil.capitalizeWord(material) + " Beehive",
				UpgradedVanilla.ID, new BeehiveBlock(FabricBlockSettings.copyOf(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.orientableCubeSameEnds((Block) block).copyForced(TextureSlot.SIDE, TextureSlot.PARTICLE);
					TextureMapping textureMapping2 = textureMapping.copyAndUpdate(TextureSlot.FRONT, TextureMapping.getBlockTexture((Block) block, "_front_honey"));
					ResourceLocation resourceLocation = ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation resourceLocation2 = ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.createWithSuffix((Block) block, "_honey", textureMapping2, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(BlockModelGenerators.createHorizontalFacingDispatch()).with(BlockModelGenerators.createEmptyOrFullDispatch(BlockStateProperties.LEVEL_HONEY, 5, resourceLocation2, resourceLocation)));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', plankBlocks).define('H', Items.HONEYCOMB)
							.pattern("PPP")
							.pattern("HHH")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankBlocks))
							.save(finishedRecipeConsumer);
				}),
				(blockLootProvider, block) -> blockLootProvider.add(block, BlockLoot.createBeeHiveDrop(block)),
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			try {
				File beehiveFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_beehive_front.png");
				File beehiveEndTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_beehive_end.png");
				File beehiveSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_beehive_side.png");
				File beehiveFrontHoneyTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_beehive_front_honey.png");
				BufferedImage beehiveFrontTexture = TextureHelper.swapColors("block\\" + material + "_beehive_front", "block", UpgradedVanilla.ID, ImageIO.read(beehiveFrontTextureLocation), TextureHelper.woodPresetPalette, out);
				File beehiveFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_beehive_front.png");
				TextureHelper.overlayTexture(beehiveFrontTexture, ImageIO.read(beehiveFrontOverlayLocation), 0, 0, "block\\" + material + "_beehive_front", "block", UpgradedVanilla.ID);
				BufferedImage beehiveEndTexture = TextureHelper.swapColors("block\\" + material + "_beehive_end", "block", UpgradedVanilla.ID, ImageIO.read(beehiveEndTextureLocation), TextureHelper.woodPresetPalette, out);
				BufferedImage beehiveSideTexture = TextureHelper.swapColors("block\\" + material + "_beehive_side", "block", UpgradedVanilla.ID, ImageIO.read(beehiveSideTextureLocation), TextureHelper.woodPresetPalette, out);
				File beehiveSideOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_beehive_side.png");
				TextureHelper.overlayTexture(beehiveSideTexture, ImageIO.read(beehiveSideOverlayLocation), 0, 0, "block\\" + material + "_beehive_side", "block", UpgradedVanilla.ID);
				BufferedImage beehiveFrontHoneyTexture = TextureHelper.swapColors("block\\" + material + "_beehive_front_honey", "block", UpgradedVanilla.ID, ImageIO.read(beehiveFrontHoneyTextureLocation), TextureHelper.woodPresetPalette, out);
				File beehiveFrontHoneyOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_beehive_front_honey.png");
				TextureHelper.overlayTexture(beehiveFrontHoneyTexture, ImageIO.read(beehiveFrontHoneyOverlayLocation), 0, 0, "block\\" + material + "_beehive_front_honey", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		TagHelper.addPoiTypeTags(PoiTypesAccessor.register(POINT_OF_INTEREST_TYPE,
				ResourceKey.create(POINT_OF_INTEREST_TYPE_REGISTRY , new ResourceLocation(UpgradedVanilla.ID,material+"_beehive")),
				PoiTypesAccessor.getBlockStates(returnBlock),0,1), List.of(PoiTypeTags.BEE_HOME));
		return returnBlock;
	}

	public static Block composterBlock(String material, Block plankBlock, TagKey<Item> slabBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_composter", StringUtil.capitalizeWord(material) + " Composter",
				UpgradedVanilla.ID, new BaseComposterBlock(plankBlock), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', slabBlocks)
							.pattern("P P")
							.pattern("P P")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_slabs", FabricRecipeProvider.has(slabBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			try {
				File composterFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_composter_side.png");
				File composterEndTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_composter_top.png");
				File composterSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_composter_bottom.png");
				BufferedImage composterFrontTexture = TextureHelper.swapColors("block\\" + material + "_composter_side", "block", UpgradedVanilla.ID, ImageIO.read(composterFrontTextureLocation), TextureHelper.woodPresetPalette, out);
				File composterFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_composter_side.png");
				TextureHelper.overlayTexture(composterFrontTexture, ImageIO.read(composterFrontOverlayLocation), 0, 0, "block\\" + material + "_composter_side", "block", UpgradedVanilla.ID);
				TextureHelper.swapColors("block\\" + material + "_composter_top", "block", UpgradedVanilla.ID, ImageIO.read(composterEndTextureLocation), TextureHelper.woodPresetPalette, out);
				TextureHelper.swapColors("block\\" + material + "_composter_bottom", "block", UpgradedVanilla.ID, ImageIO.read(composterSideTextureLocation), TextureHelper.woodPresetPalette, out);
				} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block grindstoneSet(String woodMaterial,Item stick, Block log, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out){
		grindstoneBlock(woodMaterial, "deepslate", stick, log, plankBlocks,
				Deepslate.DEEPSLATE_SLABS_ITEM_TAG, blockTags, itemTags, out, SoundType.DEEPSLATE);
		return grindstoneBlock(woodMaterial, "stone", stick, log, plankBlocks,
				Stone.STONE_SLABS_ITEM_TAG, blockTags, itemTags, out, SoundType.STONE);
	}
	public static Block grindstoneBlock(String woodMaterial, String stoneMaterial, Item stick, Block log, TagKey<Item> plankBlocks,
										TagKey<Item> stoneSlabs, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(woodMaterial + "_" + stoneMaterial + "_grindstone", StringUtil.capitalizeWord(woodMaterial) + " " + StringUtil.capitalizeWord(stoneMaterial) + " Grindstone",
				UpgradedVanilla.ID, new GrindstoneBlock(FabricBlockSettings.copyOf(Blocks.GRINDSTONE).sounds(soundType)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(PIVOT,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_grindstone_pivot"))
							.put(ROUND,new ResourceLocation(UpgradedVanilla.ID, "block/"+stoneMaterial + "_grindstone_round"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+stoneMaterial + "_grindstone_side"))
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+stoneMaterial + "_grindstone_side"))
							.put(LEG, TextureMapping.getBlockTexture(log,"_side"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.GRINDSTONE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(
							(Block) block, Variant.variant().with(VariantProperties.MODEL, resourceLocation))
							.with(PropertyDispatch.properties(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
									.select(AttachFace.FLOOR, Direction.NORTH, Variant.variant())
									.select(AttachFace.FLOOR, Direction.EAST, Variant.variant()
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.FLOOR, Direction.SOUTH, Variant.variant()
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
									.select(AttachFace.FLOOR, Direction.WEST, Variant.variant()
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
									.select(AttachFace.WALL, Direction.NORTH, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.WALL, Direction.EAST, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.WALL, Direction.SOUTH, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
									.select(AttachFace.WALL, Direction.WEST, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
									.select(AttachFace.CEILING, Direction.SOUTH, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
									.select(AttachFace.CEILING, Direction.WEST, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
									.select(AttachFace.CEILING, Direction.NORTH, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
									.select(AttachFace.CEILING, Direction.EAST, Variant.variant()
											.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', plankBlocks).define('S', stick).define('#',stoneSlabs)
							.pattern("S#S")
							.pattern("P P")
							.unlockedBy("has_" + stoneMaterial + "_slabs", FabricRecipeProvider.has(stoneSlabs))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			try {
				File grindstonePivotTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_grindstone_pivot.png");
				File grindstoneSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_grindstone_side.png");
				File grindstoneRoundTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_grindstone_round.png");
				File stonePalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png");
				TextureHelper.swapColors("block\\" + woodMaterial + "_grindstone_pivot", "block", UpgradedVanilla.ID, ImageIO.read(grindstonePivotTextureLocation), TextureHelper.woodPresetPalette, out);
				TextureHelper.swapColors("block\\" + stoneMaterial + "_grindstone_round", "block", UpgradedVanilla.ID, ImageIO.read(grindstoneRoundTextureLocation), TextureHelper.stonePresetPalette, stonePalette);
				TextureHelper.swapColors("block\\" + stoneMaterial + "_grindstone_side", "block", UpgradedVanilla.ID, ImageIO.read(grindstoneSideTextureLocation), TextureHelper.stonePresetPalette, stonePalette);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block cartographyTableBlock(String material, Block plankBlock, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_cartography_table", StringUtil.capitalizeWord(material) + " Cartography Table",
				UpgradedVanilla.ID, new CartographyTableBlock(FabricBlockSettings.copyOf(Blocks.CARTOGRAPHY_TABLE)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.DOWN, TextureMapping.getBlockTexture(plankBlock))
							.put(TextureSlot.EAST, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_cartography_table_side3"))
							.put(TextureSlot.NORTH,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_cartography_table_side3"))
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_cartography_table_side3"))
							.put(TextureSlot.SOUTH, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_cartography_table_side1"))
							.put(TextureSlot.UP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_cartography_table_top"))
							.put(TextureSlot.WEST,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_cartography_table_side2"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.CRAFTING_TABLE_LIKE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', plankBlocks).define('S', Items.PAPER)
							.pattern("SS")
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File darkOakPalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\Palletes\\DarkOakPalette.png");
				File cartographyTableSide1TextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_side1.png");
				File cartographyTableSide2TextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_side2.png");
				File cartographyTableSide3TextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_side3.png");
				File cartographyTableTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_top.png");
				BufferedImage cartographyTableSide1Texture = TextureHelper.swapColors("block\\" + material + "_cartography_table_side1", "block", UpgradedVanilla.ID, ImageIO.read(cartographyTableSide1TextureLocation), darkOakPalette, out);
				File cartographyTableSide1OverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_cartography_table_side1.png");
				TextureHelper.overlayTexture(cartographyTableSide1Texture, ImageIO.read(cartographyTableSide1OverlayLocation), 0, 0, "block\\" + material + "_cartography_table_side1", "block", UpgradedVanilla.ID);
				BufferedImage cartographyTableSide2Texture =TextureHelper.swapColors("block\\" + material + "_cartography_table_side2", "block", UpgradedVanilla.ID, ImageIO.read(cartographyTableSide2TextureLocation), darkOakPalette, out);
				File cartographyTableSide2OverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_cartography_table_side2.png");
				TextureHelper.overlayTexture(cartographyTableSide2Texture, ImageIO.read(cartographyTableSide2OverlayLocation), 0, 0, "block\\" + material + "_cartography_table_side2", "block", UpgradedVanilla.ID);
				TextureHelper.swapColors("block\\" + material + "_cartography_table_side3", "block", UpgradedVanilla.ID, ImageIO.read(cartographyTableSide3TextureLocation), darkOakPalette, out);
				BufferedImage cartographyTableTopTexture =TextureHelper.swapColors("block\\" + material + "_cartography_table_top", "block", UpgradedVanilla.ID, ImageIO.read(cartographyTableTopTextureLocation), darkOakPalette, out);
				File cartographyTableTopOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_cartography_table_top.png");
				TextureHelper.overlayTexture(cartographyTableTopTexture, ImageIO.read(cartographyTableTopOverlayLocation), 0, 0, "block\\" + material + "_cartography_table_top", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block fletchingTableBlock(String material, Block plankBlock, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_fletching_table", StringUtil.capitalizeWord(material) + " Fletching Table",
				UpgradedVanilla.ID, new FletchingTableBlock(FabricBlockSettings.copyOf(Blocks.FLETCHING_TABLE)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.DOWN, TextureMapping.getBlockTexture(plankBlock))
							.put(TextureSlot.EAST, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_fletching_table_side"))
							.put(TextureSlot.NORTH,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_fletching_table_front"))
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_fletching_table_front"))
							.put(TextureSlot.SOUTH, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_fletching_table_front"))
							.put(TextureSlot.UP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_fletching_table_top"))
							.put(TextureSlot.WEST,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_fletching_table_side"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.CRAFTING_TABLE_LIKE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', plankBlocks).define('S', Items.FLINT)
							.pattern("SS")
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File birchPalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\Palletes\\BirchPallete.png");
				File fletchingTableSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_fletching_table_side.png");
				File fletchingTableFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_fletching_table_front.png");
				File fletchingTableTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_fletching_table_top.png");
				BufferedImage fletchingTableSideTexture = TextureHelper.swapColors("block\\" + material + "_fletching_table_side", "block", UpgradedVanilla.ID, ImageIO.read(fletchingTableSideTextureLocation), birchPalette, out);
				File fletchingTableSideOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_fletching_table_side.png");
				TextureHelper.overlayTexture(fletchingTableSideTexture, ImageIO.read(fletchingTableSideOverlayLocation), 0, 0, "block\\" + material + "_fletching_table_side", "block", UpgradedVanilla.ID);
				BufferedImage fletchingTableFrontTexture =TextureHelper.swapColors("block\\" + material + "_fletching_table_front", "block", UpgradedVanilla.ID, ImageIO.read(fletchingTableFrontTextureLocation), birchPalette, out);
				File fletchingTableFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_fletching_table_front.png");
				TextureHelper.overlayTexture(fletchingTableFrontTexture, ImageIO.read(fletchingTableFrontOverlayLocation), 0, 0, "block\\" + material + "_fletching_table_front", "block", UpgradedVanilla.ID);
				BufferedImage fletchingTableTopTexture =TextureHelper.swapColors("block\\" + material + "_fletching_table_top", "block", UpgradedVanilla.ID, ImageIO.read(fletchingTableTopTextureLocation), birchPalette, out);
				File fletchingTableTopOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_fletching_table_top.png");
				TextureHelper.overlayTexture(fletchingTableTopTexture, ImageIO.read(fletchingTableTopOverlayLocation), 0, 0, "block\\" + material + "_fletching_table_top", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block tripwireHookSet(String woodMaterial,  Item stick, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out){
		tripwireHookBlock(woodMaterial, stick,"deepslate",  Deepslate.DEEPSLATE_BLOCKS_ITEM_TAG,
				plankBlocks, blockTags,itemTags, out, SoundType.DEEPSLATE);
		return tripwireHookBlock(woodMaterial, stick,"stone",  Stone.STONE_BLOCKS_ITEM_TAG,
				plankBlocks, blockTags,itemTags, out, SoundType.STONE);
	}
	public static Block tripwireHookBlock(String woodMaterial, Item stick, String stoneMaterial,  TagKey<Item> stoneBlocks,
										  TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(woodMaterial + "_" + stoneMaterial + "_tripwire_hook", StringUtil.capitalizeWord(woodMaterial) + " " + StringUtil.capitalizeWord(stoneMaterial) + " Tripwire Hook",
				UpgradedVanilla.ID, new TripWireHookBlock(FabricBlockSettings.copyOf(Blocks.GRINDSTONE).sounds(soundType).noCollision().noOcclusion()), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_planks"))
							.put(WOOD,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_planks"))
							.put(HOOK,new ResourceLocation(UpgradedVanilla.ID, "block/"+stoneMaterial + "_tripwire_hook"));
					UpgradedVanillaModelTemplates.TRIPWIRE_HOOK.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					UpgradedVanillaModelTemplates.TRIPWIRE_HOOK_ON.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					UpgradedVanillaModelTemplates.TRIPWIRE_HOOK_ATTACHED.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					UpgradedVanillaModelTemplates.TRIPWIRE_HOOK_ATTACHED_ON.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createSimpleFlatItemModel((Block) block);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(PropertyDispatch.properties(BlockStateProperties.ATTACHED, BlockStateProperties.POWERED)
									.generate((boolean_, boolean2) -> Variant.variant()
											.with(VariantProperties.MODEL, TextureMapping.getBlockTexture((Block) block, (boolean_ ? "_attached" : "") + (boolean2 ? "_on" : "")))))
							.with(BlockModelGenerators.createHorizontalFacingDispatch()));

				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 2).define('P', plankBlocks).define('S', stick).define('I',stoneBlocks)
							.pattern("I")
							.pattern("S")
							.pattern("P")
							.unlockedBy("has_" + stoneBlocks + "_stone", FabricRecipeProvider.has(stoneBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(() -> {
			try {
				File tripwireHookTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_tripwire_hook.png");
				File tripwireHookStoneTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_tripwire_hook.png");
				BufferedImage tripwireHookTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_tripwire_hook", "block", UpgradedVanilla.ID, ImageIO.read(tripwireHookTextureLocation), TextureHelper.woodPresetPalette, out);
				BufferedImage tripwireHookStoneTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_tripwire_hook", "block", UpgradedVanilla.ID, ImageIO.read(tripwireHookStoneTextureLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				TextureHelper.overlayTexture(tripwireHookTexture, tripwireHookStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_tripwire_hook", "block", UpgradedVanilla.ID);
				} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block smokerSet(String woodMaterial, TagKey<Item> logBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out){
		smokerBlock(woodMaterial, "deepslate", Blocks.COBBLED_DEEPSLATE, Blocks.FURNACE,logBlocks,
				blockTags, itemTags, out, SoundType.DEEPSLATE);
		return smokerBlock(woodMaterial, "stone", Blocks.COBBLESTONE, Blocks.FURNACE,logBlocks,
				blockTags, itemTags, out, SoundType.STONE);
	}
	public static Block smokerBlock(String woodMaterial, String stoneMaterial, Block cobbledStone, Block furnace, TagKey<Item> logBlocks,
									List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(woodMaterial + "_" + stoneMaterial + "_smoker", StringUtil.capitalizeWord(woodMaterial) + " " + StringUtil.capitalizeWord(stoneMaterial) + " Smoker",
				UpgradedVanilla.ID, new SmokerBlock(FabricBlockSettings.copyOf(Blocks.SMOKER).sounds(soundType)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingOff = new TextureMapping()
							.put(TextureSlot.BOTTOM,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_bottom"))
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_top"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.SMOKER.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					TextureMapping textureMappingOn = new TextureMapping()
							.put(TextureSlot.BOTTOM,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_bottom"))
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_front_on"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_smoker_top"));
					ResourceLocation resourceLocationOn = UpgradedVanillaModelTemplates.SMOKER_ON.create((Block) block, textureMappingOn, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, resourceLocationOn, resourceLocation))
							.with(BlockModelGenerators.createHorizontalFacingDispatch()));

				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', logBlocks).define('#',furnace)
							.pattern(" P ")
							.pattern("P#P")
							.pattern(" P ")
							.unlockedBy("has_" + stoneMaterial + "_furnace", FabricRecipeProvider.has(furnace))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+Registry.BLOCK.getKey(cobbledStone).getPath()+".png");
				File smokerSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smoker_side.png");
				File smokerFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smoker_front.png");
				File smokerFrontOnTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smoker_front_on.png");
				File smokerTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smoker_bottom.png");
				File smokerBottomTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smoker_bottom.png");
				FileUtils.copyFile(new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smoker_front_on.png.mcmeta"),
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\" +
								"create-fabric-addon-template-1.19\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front_on.png.mcmeta"));

				BufferedImage smokerSideTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_side", "block", UpgradedVanilla.ID, ImageIO.read(smokerSideTextureLocation), TextureHelper.woodPresetPalette, out);
				File smokerSideStoneTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\smoker_side.png");
				BufferedImage smokerSideRimStoneTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_side", "block", UpgradedVanilla.ID, ImageIO.read(smokerSideStoneTextureLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				File smokerSideStoneMaskLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_smoker_side.png");
				BufferedImage stonecutterSideStoneTexture = TextureHelper.maskImage(ImageIO.read(smokerSideStoneMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_side", "block", UpgradedVanilla.ID);
				File smokerSideStoneOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_smoker_side.png");
				stonecutterSideStoneTexture = TextureHelper.overlayTextureDarken(stonecutterSideStoneTexture, ImageIO.read(smokerSideStoneOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_side", "block", UpgradedVanilla.ID);
				stonecutterSideStoneTexture = TextureHelper.overlayTexture(stonecutterSideStoneTexture, smokerSideRimStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_side", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smokerSideTexture, stonecutterSideStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_side", "block", UpgradedVanilla.ID);

				BufferedImage smokerFrontTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID, ImageIO.read(smokerFrontTextureLocation), TextureHelper.woodPresetPalette, out);
				File smokerFrontStoneTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_smoker_front.png");
				BufferedImage smokerFrontRimStoneTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID, ImageIO.read(smokerFrontStoneTextureLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				File smokerFrontStoneMaskLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_smoker_front.png");
				BufferedImage stonecutterFrontStoneTexture = TextureHelper.maskImage(ImageIO.read(smokerFrontStoneMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_front", "block", UpgradedVanilla.ID);
				File smokerFrontStoneOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_smoker_front.png");
				File smokerFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_smoker_front.png");
				File smokerFrontStoneOverlayShadeLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_smoker_front_shading.png");
				stonecutterFrontStoneTexture = TextureHelper.overlayTextureDarken(stonecutterFrontStoneTexture, ImageIO.read(smokerFrontStoneOverlayShadeLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID);
				stonecutterFrontStoneTexture = TextureHelper.overlayTexture(stonecutterFrontStoneTexture, ImageIO.read(smokerFrontStoneOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID);
				stonecutterFrontStoneTexture = TextureHelper.overlayTexture(stonecutterFrontStoneTexture, ImageIO.read(smokerFrontOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID);
				stonecutterFrontStoneTexture = TextureHelper.overlayTexture(stonecutterFrontStoneTexture, smokerFrontRimStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smokerFrontTexture, stonecutterFrontStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front", "block", UpgradedVanilla.ID);

				BufferedImage smokerFrontOnTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front_on", "block", UpgradedVanilla.ID, ImageIO.read(smokerFrontOnTextureLocation), TextureHelper.woodPresetPalette, out);
				File smokerFrontOnStoneTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_smoker_front_on.png");
				BufferedImage smokerFrontOnRimStoneTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front_on", "block", UpgradedVanilla.ID, ImageIO.read(smokerFrontOnStoneTextureLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				File smokerFrontOnStoneMaskLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_smoker_front_on.png");
				BufferedImage stonecutterFrontOnStoneTexture = TextureHelper.maskImage(ImageIO.read(smokerFrontOnStoneMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_front_on", "block", UpgradedVanilla.ID);
				stonecutterFrontOnStoneTexture = TextureHelper.overlayTexture(stonecutterFrontOnStoneTexture, smokerFrontOnRimStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front_on", "block", UpgradedVanilla.ID);
				File smokerFrontOnOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_smoker_front_on.png");
				BufferedImage smokerOnTexture = TextureHelper.overlayTexture(smokerFrontOnTexture, stonecutterFrontOnStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front_on", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smokerOnTexture, ImageIO.read(smokerFrontOnOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_front_on", "block", UpgradedVanilla.ID);

				BufferedImage smokerTopTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_top", "block", UpgradedVanilla.ID, ImageIO.read(smokerTopTextureLocation), TextureHelper.woodPresetPalette, out);
				File smokerTopStoneTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\smoker_top.png");
				BufferedImage smokerTopRimStoneTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_top", "block", UpgradedVanilla.ID, ImageIO.read(smokerTopStoneTextureLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				File smokerTopStoneMaskLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_smoker_top.png");
				BufferedImage stonecutterTopStoneTexture = TextureHelper.maskImage(ImageIO.read(smokerTopStoneMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_top", "block", UpgradedVanilla.ID);
				File smokerTopStoneOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_smoker_top.png");
				File smokerTopStoneOverlayShadeLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_smoker_top_shade.png");
				stonecutterTopStoneTexture = TextureHelper.overlayTexture(stonecutterTopStoneTexture, smokerTopRimStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_top", "block", UpgradedVanilla.ID);
				stonecutterTopStoneTexture = TextureHelper.overlayTextureDarken(stonecutterTopStoneTexture, ImageIO.read(smokerTopStoneOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_top", "block", UpgradedVanilla.ID);
				stonecutterTopStoneTexture = TextureHelper.overlayTextureDarken(stonecutterTopStoneTexture, ImageIO.read(smokerTopStoneOverlayShadeLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smokerTopTexture, stonecutterTopStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_top", "block", UpgradedVanilla.ID);

				BufferedImage smokerBottomTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_bottom", "block", UpgradedVanilla.ID, ImageIO.read(smokerBottomTextureLocation), TextureHelper.woodPresetPalette, out);
				File smokerBottomStoneMaskLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_smoker_bottom.png");
				BufferedImage stonecutterBottomStoneTexture = TextureHelper.maskImage(ImageIO.read(smokerBottomStoneMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_top", "block", UpgradedVanilla.ID);
				File smokerBottomStoneOverlayShadeLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_smoker_bottom.png");
				stonecutterBottomStoneTexture = TextureHelper.overlayTextureDarken(stonecutterBottomStoneTexture, ImageIO.read(smokerBottomStoneOverlayShadeLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_bottom", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smokerBottomTexture, stonecutterBottomStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_smoker_bottom", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block stonecutterSet(String woodMaterial, TagKey<Item> logBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out){
		stonecutterBlock(woodMaterial, "deepslate", Deepslate.DEEPSLATE_BLOCKS_ITEM_TAG, logBlocks,
				blockTags, itemTags, out, SoundType.DEEPSLATE);
		return stonecutterBlock(woodMaterial, "stone", Stone.STONE_BLOCKS_ITEM_TAG, logBlocks,
				blockTags, itemTags, out, SoundType.STONE);
	}
	public static Block stonecutterBlock(String woodMaterial, String stoneMaterial, TagKey<Item> stoneBlocks, TagKey<Item> logBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(woodMaterial + "_" + stoneMaterial + "_stonecutter", StringUtil.capitalizeWord(woodMaterial) + " " + StringUtil.capitalizeWord(stoneMaterial) + " Cutter",
				UpgradedVanilla.ID, new StonecutterBlock(FabricBlockSettings.copyOf(Blocks.STONECUTTER).sounds(soundType)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingOff = new TextureMapping()
							.put(TextureSlot.BOTTOM,new ResourceLocation(UpgradedVanilla.ID, "block/"+stoneMaterial + "_stonecutter_bottom"))
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+stoneMaterial + "_stonecutter_bottom"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_stonecutter_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+woodMaterial + "_" + stoneMaterial + "_stonecutter_top"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.STONECUTTER.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block, Variant.variant()
							.with(VariantProperties.MODEL, resourceLocation)).with(BlockModelGenerators.createHorizontalFacingDispatch()));


				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', logBlocks).define('#',stoneBlocks).define('S',UVCommonItemTags.IRON_INGOTS)
							.pattern(" S ")
							.pattern("P#P")
							.unlockedBy("has_" + stoneMaterial + "_stone", FabricRecipeProvider.has(stoneBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(() -> {
			try {
				File sprucePalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\Palletes\\SprucePallete.png");
				File stonecutterSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_stonecutter_side.png");
				File stonecutterTopTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_stonecutter_top.png");
				BufferedImage stonecutterSideTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_side", "block", UpgradedVanilla.ID, ImageIO.read(stonecutterSideTextureLocation), sprucePalette, out);
				File stonecutterSideMask = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_stonecutter_side.png");
				File stoneTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+".png");
				File stonecutterSideOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_stonecutter_side.png");
				File stonecutterSideSwapLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_stonecutter_side.png");
				BufferedImage stonecutterSideStoneTexture = TextureHelper.maskImage(ImageIO.read(stonecutterSideMask),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_side", "block", UpgradedVanilla.ID);
				stonecutterSideStoneTexture = TextureHelper.overlayTextureDarken(stonecutterSideStoneTexture, ImageIO.read(stonecutterSideOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_side", "block", UpgradedVanilla.ID);
				BufferedImage stonecutterSideRimTexture = TextureHelper.swapColors("block\\" + stoneMaterial + "_stonecutter_side", "block", UpgradedVanilla.ID, ImageIO.read(stonecutterSideSwapLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				stonecutterSideStoneTexture = TextureHelper.overlayTexture(stonecutterSideStoneTexture,stonecutterSideRimTexture, 0, 0, "block\\" + stoneMaterial + "_stonecutter_bottom", "block", UpgradedVanilla.ID);

				TextureHelper.overlayTexture(stonecutterSideTexture, stonecutterSideStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_side", "block", UpgradedVanilla.ID);

				BufferedImage stonecutterTopTexture = TextureHelper.swapColors("block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_top", "block", UpgradedVanilla.ID, ImageIO.read(stonecutterTopTextureLocation), sprucePalette, out);
				File stonecutterTopMask = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_stonecutter_top.png");
				File stonecutterTopOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Overlays\\block\\stone_stonecutter_top.png");
				BufferedImage stonecutterTopStoneTexture = TextureHelper.maskImage(ImageIO.read(stonecutterTopMask),ImageIO.read(stoneTexture), 0,0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_top", "block", UpgradedVanilla.ID);
				stonecutterTopStoneTexture = TextureHelper.overlayTextureDarken(stonecutterTopStoneTexture, ImageIO.read(stonecutterTopOverlayLocation), 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(stonecutterTopTexture, stonecutterTopStoneTexture, 0, 0, "block\\" + woodMaterial + "_" + stoneMaterial + "_stonecutter_top", "block", UpgradedVanilla.ID);

				File stonecutterBottomMask = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Masks\\stone_stonecutter_bottom.png");
				File stonecutterBottomOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Textures\\block\\stone_stonecutter_bottom.png");
				BufferedImage stonecutterBottomStoneTexture = TextureHelper.maskImage(ImageIO.read(stonecutterBottomMask),ImageIO.read(stoneTexture), 0,0, "block\\" + stoneMaterial + "_stonecutter_bottom", "block", UpgradedVanilla.ID);
				BufferedImage stonecutterBottomRimTexture = TextureHelper.swapColors("block\\" + stoneMaterial + "_stonecutter_bottom", "block", UpgradedVanilla.ID, ImageIO.read(stonecutterBottomOverlayLocation), TextureHelper.stonePresetPalette,
						new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Stone\\Palletes\\"+stoneMaterial+"Palette.png"));
				TextureHelper.overlayTexture(stonecutterBottomRimTexture,stonecutterBottomStoneTexture, 0, 0, "block\\" + stoneMaterial + "_stonecutter_bottom", "block", UpgradedVanilla.ID);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block smithingTableBlock(String material, Block plankBlock, TagKey<Item> plankBlocks, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_smithing_table", StringUtil.capitalizeWord(material) + " Smithing Table",
				UpgradedVanilla.ID, new SmithingTableBlock(FabricBlockSettings.copyOf(Blocks.SMITHING_TABLE)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.DOWN, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_smithing_table_bottom"))
							.put(TextureSlot.EAST, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_smithing_table_side"))
							.put(TextureSlot.NORTH, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_smithing_table_front"))
							.put(TextureSlot.PARTICLE, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_smithing_table_front"))
							.put(TextureSlot.SOUTH, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_smithing_table_front"))
							.put(TextureSlot.UP, new ResourceLocation("minecraft", "block/smithing_table_top"))
							.put(TextureSlot.WEST, new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_smithing_table_side"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.CRAFTING_TABLE_LIKE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('P', plankBlocks).define('S', UVCommonItemTags.IRON_INGOTS)
							.pattern("SS")
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_iron_ingots", FabricRecipeProvider.has(UVCommonItemTags.IRON_INGOTS))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			try {
				File mangrovePalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\Palletes\\MangrovePallete.png");
				File smithingTableSideTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smithing_table_side.png");
				File smithingTableFrontTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smithing_table_front.png");
				File smithingTableBottomTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_smithing_table_bottom.png");
				BufferedImage smithingTableSideTexture = TextureHelper.swapColors("block\\" + material + "_smithing_table_side", "block", UpgradedVanilla.ID, ImageIO.read(smithingTableSideTextureLocation), mangrovePalette, out);
				File smithingTableSideOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_smithing_table_side.png");
				TextureHelper.overlayTexture(smithingTableSideTexture, ImageIO.read(smithingTableSideOverlayLocation), 0, 0, "block\\" + material + "_smithing_table_side", "block", UpgradedVanilla.ID);
				BufferedImage smithingTableFrontTexture =TextureHelper.swapColors("block\\" + material + "_smithing_table_front", "block", UpgradedVanilla.ID, ImageIO.read(smithingTableFrontTextureLocation), mangrovePalette, out);
				File smithingTableFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_smithing_table_front.png");
				TextureHelper.overlayTexture(smithingTableFrontTexture, ImageIO.read(smithingTableFrontOverlayLocation), 0, 0, "block\\" + material + "_smithing_table_front", "block", UpgradedVanilla.ID);
				BufferedImage smithingTableBottomTexture =TextureHelper.swapColors("block\\" + material + "_smithing_table_bottom", "block", UpgradedVanilla.ID, ImageIO.read(smithingTableBottomTextureLocation), mangrovePalette, out);
				File smithingTableBottomOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_smithing_table_bottom.png");
				TextureHelper.overlayTexture(smithingTableBottomTexture, ImageIO.read(smithingTableBottomOverlayLocation), 0, 0, "block\\" + material + "_smithing_table_bottom", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
}
