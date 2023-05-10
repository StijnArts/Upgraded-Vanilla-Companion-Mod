package drai.dev.upgradedvanilla.modules.minecraft.quartz;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.logs.*;
import drai.dev.upgradedvanilla.datageneration.recipes.*;
import drai.dev.upgradedvanilla.helpers.*;
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

import javax.imageio.*;

import java.awt.image.*;
import java.io.*;
import java.util.*;

public class MinecraftQuartzBlocks {
	public static Block smoothQuartzBlock(String material, Block stone, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe("smooth_" + material, "Smooth " + sourceCapitalized.trim(),
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					blockModelGenerators.createTrivialCube((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, item, stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);


		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\" + material + ".png");
				File stoneColor = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\" + material + "_color.png");
				BufferedImage smoothStoneTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor), ImageIO.read(stoneTexture), 0, 0, "block\\" + "smooth_" + material, "block", UpgradedVanilla.ID, 0.3F);
				//File smoothStoneBurnLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\polished_stone_burn.png");
				//TextureHelper.overlayTextureColorBurn(smoothStoneTexture, ImageIO.read(smoothStoneBurnLocation), 0, 0, "block\\" + "smooth_"+material, "block", UpgradedVanilla.ID,1.0F);
				File smoothStoneShadeLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\polished_stone_shading.png");
				//TextureHelper.overlayTextureBurnReverse(smoothStoneTexture, ImageIO.read(smoothStoneShadeLocation), 0, 0, "block\\" + "smooth_"+material, "block", UpgradedVanilla.ID);

				File smoothStoneHighlightLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\polished_stone_highlight.png");
				//TextureHelper.overlayTextureLighten(smoothStoneTexture, ImageIO.read(smoothStoneHighlightLocation),0,0,"block\\" + "smooth_"+material, "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block quartzBricksBlock(String material, Block stone, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_bricks", sourceCapitalized.trim() +" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\"+ material+"_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_bricks", "block", UpgradedVanilla.ID, 0.3F);
				File dropperFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\stone_bricks_normal.png");
				TextureHelper.overlayTextureLightenReverse(brickTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\" + material + "_bricks", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\stone_bricks_multiply.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\" + material + "_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block chiseledQuartzBlock(String material, Block stone, TagKey<Item> stoneBrickSlabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe("chiseled_" + material + "_bricks", "Chiseled " + sourceCapitalized.trim()  + " Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					blockModelGenerators.createTrivialCube((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, item, stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneBrickSlabTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_brick_slabs", FabricRecipeProvider.has(stoneBrickSlabTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\" + material + ".png");
				File stoneColor = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\" + material + "_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor), ImageIO.read(stoneTexture), 0, 0, "block\\chiseled_" + material + "_bricks", "block", UpgradedVanilla.ID, 0.3F);
				File dropperFrontOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\chiseled_stone_bricks_light.png");
				TextureHelper.overlayTextureLightenReverse(brickTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\chiseled_" + material + "_bricks", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\chiseled_stone_brick_shade.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\chiseled_" + material + "_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block quartzTilesBlock(String material, Block quartzBricks, Block polishedQuartz, TagKey<Item> quartzBrickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_tiles", sourceCapitalized.trim() +" Tiles",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(quartzBricks)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,quartzBricks);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,polishedQuartz);
					ShapedRecipeBuilder.shaped(item, 4).define('P', quartzBrickTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_bricks", FabricRecipeProvider.has(quartzBrickTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File quartzTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\"+ material+".png");
				File quartzColor = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\"+ material+"_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(quartzColor),ImageIO.read(quartzTexture), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID, 0.3F);
				File stoneTilesBurnLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\deepslate_tiles_linear_burn.png");
				TextureHelper.overlayTextureBurnReverse(brickTexture, ImageIO.read(stoneTilesBurnLocation), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID);
				File stoneTilesDarkenLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\deepslate_tiles_darken.png");
				TextureHelper.overlayTextureDarken(brickTexture, ImageIO.read(stoneTilesDarkenLocation), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\deepslate_tiles_multiply.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block crackedQuartzBricksBlock(String material, Block quartzBrick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cracked_"+material+"_bricks", "Cracked "+sourceCapitalized.trim() +" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(quartzBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer, item, quartzBrick);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+material+"_bricks.png");
				File cracks = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\cracked_stone_bricks.png");
				TextureHelper.overlayTextureMultiplyReverse(ImageIO.read(stoneBrickTexture),ImageIO.read(cracks), 0, 0, "block\\" + "cracked_"+material+"_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		return returnBlock;
	}
	public static Block crackedQuartzTilesBlock(String material, Block quartzBrick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cracked_"+material+"_tiles", "Cracked "+sourceCapitalized.trim() +" Tiles",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(quartzBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer, item, quartzBrick);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+material+"_tiles.png");
				File cracks = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Overlays\\block\\cracked_stone_tiles.png");
				TextureHelper.overlayTextureMultiplyReverse(ImageIO.read(stoneBrickTexture),ImageIO.read(cracks), 0, 0, "block\\" + "cracked_"+material+"_tiles", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block quartzPillarBlock(String material, Block stone, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_pillar", sourceCapitalized.trim() +" Pillar",
				UpgradedVanilla.ID, new RotatedPillarBlock(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
						TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P")
							.pattern("P")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Palletes\\"+ material+"_color.png");
				BufferedImage base = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_pillar_side", "block", UpgradedVanilla.ID, 0.15F);
				BufferedImage pillarSideTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_pillar_side", "block", UpgradedVanilla.ID, 0.15F);
				File pillarSideMultiplyLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Overlays\\block\\quartz_pillar.png");
				BufferedImage multiply = TextureHelper.overlayTextureMultiply(pillarSideTexture, ImageIO.read(pillarSideMultiplyLocation), 0, 0, "block\\" + material + "_pillar_side", "block", UpgradedVanilla.ID);
				multiply = TextureHelper.maskImage(ImageIO.read(pillarSideMultiplyLocation),multiply, 0, 0, "block\\" + material + "_pillar_side", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(base,multiply, 0, 0, "block\\" + material + "_pillar_side", "block", UpgradedVanilla.ID, 0.15F);

				BufferedImage baseTop = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_pillar_top", "block", UpgradedVanilla.ID, 0.15F);
				BufferedImage pillarTopTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_pillar_top", "block", UpgradedVanilla.ID, 0.15F);
				File pillarTopMultiplyLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Quartz\\Overlays\\block\\quartz_pillar_top.png");
				BufferedImage multiplyTop = TextureHelper.overlayTextureMultiply(pillarTopTexture, ImageIO.read(pillarTopMultiplyLocation), 0, 0, "block\\" + material + "_pillar_top", "block", UpgradedVanilla.ID);
				multiplyTop = TextureHelper.maskImage(ImageIO.read(pillarTopMultiplyLocation),multiplyTop, 0, 0, "block\\" + material + "_pillar_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(baseTop,multiplyTop, 0, 0, "block\\" + material + "_pillar_top", "block", UpgradedVanilla.ID, 0.15F);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block pillarStairsBlock(String material, Block sourceBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
									   List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(sourceBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(sourceBlock).getPath() + "_stairs", sourceCapitalized.trim() + " Stairs",
				UpgradedVanilla.ID, new StairBlock(sourceBlock.defaultBlockState(), FabricBlockSettings.copyOf(sourceBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sourceBlock,"_top"))
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture(sourceBlock,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(sourceBlock,"_side"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(sourceBlock,"_side"));
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,sourceBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', blockTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(blockTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block pillarSlabBlock(String material, Block logBlock, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
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
	public static Block pillarWallBlock(String material, Block logBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(logBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(logBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(logBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
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
}

