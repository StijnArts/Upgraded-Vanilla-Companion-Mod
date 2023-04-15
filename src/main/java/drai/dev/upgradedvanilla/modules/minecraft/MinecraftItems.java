package drai.dev.upgradedvanilla.modules.minecraft;

import com.simibubi.create.content.curiosities.deco.*;

import com.simibubi.create.foundation.data.recipe.*;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.datageneration.providers.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.helpers.item.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.model.*;
import drai.dev.upgradedvanilla.tag.*;
import io.github.fabricators_of_create.porting_lib.mixin.client.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.properties.*;

import org.betterx.bclib.items.boat.*;

import javax.imageio.*;

import java.awt.image.*;
import java.io.*;
import java.util.List;

public class MinecraftItems {
	public static Item stick(String material, TagKey<Item> plankTag, List<TagKey<Item>> itemTags, File out){
		Item returnItem = ItemHandler.registerItemWithRecipe(material+"_stick", StringUtil.capitalizeWord(material)+" Stick",
				new Item(new FabricItemSettings().stacksTo(64).tab(CreativeModeTab.TAB_MATERIALS)),
				(itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
				(finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,4).define('P',plankTag)
							.pattern("P")
							.pattern("P")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				},
				itemTags
				);
		TextureHelper.addTexture(()->{
			File stickTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\MATERIAL_stick.png");
			try {
				TextureHelper.swapColors("item\\"+material+"_stick", "item", UpgradedVanilla.ID, ImageIO.read(stickTextureLocation), TextureHelper.woodPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnItem;
	}

	public static Item signItem(String material, Item stick, Block plankBlock, Block standing, Block wall, TagKey<Item> plankTag, List<TagKey<Item>> itemTags, File out){
		Item returnItem = ItemHandler.registerItemWithRecipeWithBlockState(material+"_sign", StringUtil.capitalizeWord(material)+" Sign",
				new SignItem(new FabricItemSettings().stacksTo(16).tab(CreativeModeTab.TAB_MATERIALS),standing, wall),
				(itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.defaultTexture(plankBlock);
					ResourceLocation resourceLocation = ModelTemplates.PARTICLE_ONLY.create(standing, textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(standing, resourceLocation));
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wall, resourceLocation));
					blockModelGenerators.skipAutoItemBlock(standing);
				},
				(finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,3).define('P',plankTag).define('S',stick)
							.pattern("PPP")
							.pattern("PPP")
							.pattern(" S ")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				},
				itemTags
		);
		TextureHelper.addTexture(()->{
			try {
				File ladderTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\MATERIAL_sign.png");
				BufferedImage signTexture = TextureHelper.swapColors("item\\"+material+"_sign", "item", UpgradedVanilla.ID,
						ImageIO.read(ladderTextureLocation), TextureHelper.woodPresetPalette,out);
				File textTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\item\\MATERIAL_sign.png");
				TextureHelper.overlayTexture(signTexture,ImageIO.read(textTextureLocation), 0,0,"item\\"+material+"_sign", "item", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnItem;
	}

	public static Item boatItem(String material, BoatTypeOverride boatTypeOverride, TagKey<Item> plankTag, List<TagKey<Item>> itemTags, File out){
		Item returnItem = ItemHandler.registerItemWithRecipe(material+"_boat", StringUtil.capitalizeWord(material)+" Boat",
				boatTypeOverride.createItem(false),
				(itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
				(finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('P',plankTag)
							.pattern("P P")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				},
				itemTags
		);
		TextureHelper.addTexture(()->{
			try {
				File boatEntityTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\boat\\MATERIAL.png");
				File boatTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\MATERIAL_boat.png");
				TextureHelper.swapColors("entity\\boat\\"+material, "entity\\boat", UpgradedVanilla.ID,
						ImageIO.read(boatEntityTexture), TextureHelper.woodPresetPalette,out);
				TextureHelper.swapColors("item\\"+material+"_boat", "item", UpgradedVanilla.ID,
						ImageIO.read(boatTexture), TextureHelper.woodPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnItem;
	}

	public static Item chestBoatItem(String material, BoatTypeOverride boatTypeOverride, Item boat, TagKey<Item> chestTag, List<TagKey<Item>> itemTags, File out){
		Item returnItem = ItemHandler.registerItemWithRecipe(material+"_chest_boat", StringUtil.capitalizeWord(material)+" Chest Boat",
				boatTypeOverride.createItem(true),
				(itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
				(finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('C',chestTag).define('B',boat)
							.pattern("C")
							.pattern("B")
							.unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(boat)).save(finishedRecipeConsumer);
				},
				itemTags
		);
		TextureHelper.addTexture(()->{
			try {
				File boatEntityTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\entity\\chest_boat\\MATERIAL.png");
				File boatItemTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\item\\MATERIAL_chest_boat.png");
				BufferedImage chestBoatTexture = TextureHelper.swapColors("entity\\chest_boat\\"+material, "entity\\chest_boat", UpgradedVanilla.ID,
						ImageIO.read(boatEntityTexture), TextureHelper.woodPresetPalette,out);
				File chestBoatOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\entity\\chest_boat\\MATERIAL.png");
				TextureHelper.overlayTexture(chestBoatTexture,ImageIO.read(chestBoatOverlayLocation), 0,0,"entity\\chest_boat\\"+material, "entity\\chest_boat", UpgradedVanilla.ID);
				BufferedImage chestBoatItemTexture = TextureHelper.swapColors("item\\"+material+"_chest_boat", "item", UpgradedVanilla.ID,
						ImageIO.read(boatItemTexture), TextureHelper.woodPresetPalette,out);
				File chestBoatItemOverlayLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\item\\MATERIAL_chest_boat.png");
				TextureHelper.overlayTexture(chestBoatItemTexture,ImageIO.read(chestBoatItemOverlayLocation), 0,0,"item\\"+material+"_chest_boat", "item", UpgradedVanilla.ID);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnItem;
	}

	public static Item torchItem(String woodMaterial, Block torch, Block wallTorch, Item stick, List<TagKey<Item>> itemTags, File out) {
		Item returnItem = ItemHandler.registerItemWithRecipeWithBlockState(woodMaterial + "_torch", StringUtil.capitalizeWord(woodMaterial) + " Torch",
				new StandingAndWallBlockItem(torch,wallTorch,new FabricItemSettings().stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS)),

				(itemModelGenerator,item)->{},
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.torch(torch);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(torch, ModelTemplates.TORCH.create(torch, textureMapping,blockModelGenerators.modelOutput)));
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(wallTorch, Variant.variant().with(VariantProperties.MODEL, ModelTemplates.WALL_TORCH.create(wallTorch, textureMapping, blockModelGenerators.modelOutput))).with(BlockModelGenerators.createTorchHorizontalDispatch()));
					blockModelGenerators.createSimpleFlatItemModel(torch);
					blockModelGenerators.skipAutoItemBlock(wallTorch);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,4).define('S',stick).define('C',ItemTags.COALS)
							.pattern("C")
							.pattern("S")
							.unlockedBy("has_"+woodMaterial+"_sticks", FabricRecipeProvider.has(stick))
							.save(finishedRecipeConsumer);
				}), itemTags);
		TextureHelper.addTexture(()->{
			try {
				File leverTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_torch.png");
				BufferedImage leverTexture = TextureHelper.swapColors("block\\"+woodMaterial +"_torch", "block", UpgradedVanilla.ID, ImageIO.read(leverTextureLocation), TextureHelper.woodPresetPalette,out);
				File leverOverlay = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_torch.png");
				TextureHelper.overlayTexture(leverTexture,ImageIO.read(leverOverlay), 0,0,"block\\"+woodMaterial + "_torch", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnItem;
	}

	public static Item soulTorchItem(String woodMaterial, Item normalTorch, Block torch, Block wallTorch, Item stick, List<TagKey<Item>> itemTags, File out) {
		Item returnItem = ItemHandler.registerItemWithRecipeWithBlockState(woodMaterial + "_soul_torch", StringUtil.capitalizeWord(woodMaterial) + " Soul Torch",
				new StandingAndWallBlockItem(torch,wallTorch,new FabricItemSettings().stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS)),

				(itemModelGenerator,item)->{},
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.torch(torch);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(torch, ModelTemplates.TORCH.create(torch, textureMapping,blockModelGenerators.modelOutput)));
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(wallTorch, Variant.variant().with(VariantProperties.MODEL, ModelTemplates.WALL_TORCH.create(wallTorch, textureMapping, blockModelGenerators.modelOutput))).with(BlockModelGenerators.createTorchHorizontalDispatch()));
					blockModelGenerators.createSimpleFlatItemModel(torch);
					blockModelGenerators.skipAutoItemBlock(wallTorch);
				},
				((finishedRecipeConsumer, item) -> {
					//TODO Haunting Recipe
					ShapedRecipeBuilder.shaped(item,4).define('S',stick).define('F',ItemTags.SOUL_FIRE_BASE_BLOCKS).define('C', UVCommonItemTags.COAL)
							.pattern("C")
							.pattern("S")
							.pattern("F")
							.unlockedBy("has_"+woodMaterial+"_soul_fire_base_blocks", FabricRecipeProvider.has(ItemTags.SOUL_FIRE_BASE_BLOCKS))
							.save(finishedRecipeConsumer);
				}), itemTags);
		TextureHelper.addTexture(()->{
			try {
				File leverTextureLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_soul_torch.png");
				BufferedImage leverTexture = TextureHelper.swapColors("block\\"+woodMaterial +"_soul_torch", "block", UpgradedVanilla.ID, ImageIO.read(leverTextureLocation), TextureHelper.woodPresetPalette,out);
				File leverOverlay = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_soul_torch.png");
				TextureHelper.overlayTexture(leverTexture,ImageIO.read(leverOverlay), 0,0,"block\\"+woodMaterial + "_soul_torch", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addHauntingRecipe(returnItem,(gen, item)->{gen.convert(normalTorch,item);});
		return returnItem;
	}

	public static Item redstoneTorchItem(String woodMaterial, Block torch, Block wallTorch, Item stick, List<TagKey<Item>> itemTags, File out) {
		Item returnItem = ItemHandler.registerItemWithRecipeWithBlockState(woodMaterial + "_redstone_torch", StringUtil.capitalizeWord(woodMaterial) + " Redstone Torch",
				new StandingAndWallBlockItem(torch,wallTorch,new FabricItemSettings().stacksTo(16).tab(CreativeModeTab.TAB_REDSTONE)),

				(itemModelGenerator,item)->{},
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.torch(torch);
					TextureMapping textureMapping2 = TextureMapping.torch(TextureMapping.getBlockTexture(torch, "_off"));
					ResourceLocation resourceLocation = ModelTemplates.TORCH.create(torch, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation resourceLocation2 = ModelTemplates.TORCH.createWithSuffix(torch, "_off", textureMapping2, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(torch).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, resourceLocation, resourceLocation2)));
					ResourceLocation resourceLocation3 = ModelTemplates.WALL_TORCH.create(wallTorch, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation resourceLocation4 = ModelTemplates.WALL_TORCH.createWithSuffix(wallTorch, "_off", textureMapping2, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(wallTorch).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, resourceLocation3, resourceLocation4)).with(BlockModelGenerators.createTorchHorizontalDispatch()));
					blockModelGenerators.createSimpleFlatItemModel(torch);
					blockModelGenerators.skipAutoItemBlock(wallTorch);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,4).define('S',stick).define('C',Items.REDSTONE)
							.pattern("C")
							.pattern("S")
							.unlockedBy("has_"+woodMaterial+"_sticks", FabricRecipeProvider.has(stick))
							.save(finishedRecipeConsumer);
				}), itemTags);
		TextureHelper.addTexture(()->{
			try {
				File redstoneTorchOnLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_redstone_torch.png");
				File redstoneTorchOffLocation = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\block\\MATERIAL_redstone_torch_off.png");
				BufferedImage redstoneTorchONTexture = TextureHelper.swapColors("block\\"+woodMaterial +"_redstone_torch", "block", UpgradedVanilla.ID, ImageIO.read(redstoneTorchOnLocation), TextureHelper.woodPresetPalette,out);
				File redstoneTorchOnOverlay = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_redstone_torch.png");
				TextureHelper.overlayTexture(redstoneTorchONTexture,ImageIO.read(redstoneTorchOnOverlay), 0,0,"block\\"+woodMaterial + "_redstone_torch", "block", UpgradedVanilla.ID);
				BufferedImage redstoneTorchOffTexture = TextureHelper.swapColors("block\\"+woodMaterial +"_redstone_torch_off", "block", UpgradedVanilla.ID, ImageIO.read(redstoneTorchOffLocation), TextureHelper.woodPresetPalette,out);
				File redstoneTorchOffOverlay = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\assets\\textures\\overlay\\block\\MATERIAL_redstone_torch_off.png");
				TextureHelper.overlayTexture(redstoneTorchOffTexture,ImageIO.read(redstoneTorchOffOverlay), 0,0,"block\\"+woodMaterial + "_redstone_torch_off", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnItem;
	}
}
