package drai.dev.upgradedvanilla.modules.minecraft.metal;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.datageneration.recipes.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.model.*;
import drai.dev.upgradedvanilla.registry.*;
import drai.dev.upgradedvanilla.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

import javax.imageio.*;

import java.io.*;
import java.util.*;

public class MinecraftMetalBlocks {
	public static Block metalCutBlock(String material, Block metalBlock, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cut_"+material,"Cut " + sourceCapitalized.trim() ,
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(metalBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', metalBlock)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_blocks", FabricRecipeProvider.has(metalBlock)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File tileTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\cut_copper.png");
				TextureHelper.swapColors("block\\" + "cut_"+material, "block", UpgradedVanilla.ID,ImageIO.read(tileTexture),TextureHelper.metalPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block lightningRodBlock(String material, Item metalIngot, Block metalBlock, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_lighting_rod",sourceCapitalized.trim() + " Lightning Rod",
				UpgradedVanilla.ID, new LightningRodBlock(FabricBlockSettings.copy(metalBlock).noOcclusion()), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture((Block) block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture((Block) block));
					ResourceLocation off = UpgradedVanillaModelTemplates.LIGHTNING_ROD.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation on = UpgradedVanillaModelTemplates.LIGHTNING_ROD_ON.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, off);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block, Variant.variant().with(VariantProperties.MODEL, off)).with(blockModelGenerators.createColumnWithFacing()).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.POWERED, on, off)));
					},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', metalIngot)
							.pattern("P")
							.pattern("P")
							.pattern("P")
							.unlockedBy("has_" + material + "_ingots", FabricRecipeProvider.has(metalIngot)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File tileTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\lightning_rod.png");
				TextureHelper.swapColors("block\\" + material+"_lighting_rod", "block", UpgradedVanilla.ID,ImageIO.read(tileTexture),TextureHelper.metalPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}

	public static Block metalChainBlock(String material, Item metalIngot, Item metalNugget, Block metalBlock, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_chain",sourceCapitalized.trim() + " Chain",
				UpgradedVanilla.ID, new ChainBlock(FabricBlockSettings.copy(metalBlock).noOcclusion().sound(SoundType.CHAIN)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.ALL, TextureMapping.getBlockTexture((Block) block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture((Block) block));
					ResourceLocation chain = UpgradedVanillaModelTemplates.CHAIN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createSimpleFlatItemModel(block.asItem());
					blockModelGenerators.createAxisAlignedPillarBlockCustomModel((Block) block, chain);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', metalIngot).define('N',metalNugget)
							.pattern("N")
							.pattern("P")
							.pattern("N")
							.unlockedBy("has_" + material + "_ingots", FabricRecipeProvider.has(metalIngot)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File tileTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\chain.png");
				TextureHelper.swapColors("block\\" + material+"_chain", "block", UpgradedVanilla.ID,ImageIO.read(tileTexture),TextureHelper.metalPresetPalette,out);
				File itemTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\item\\chain.png");
				TextureHelper.swapColors("item\\" + material+"_chain", "item", UpgradedVanilla.ID,ImageIO.read(itemTexture),TextureHelper.metalPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block hopperBlock(String material, Item metalIngot, Block metalBlock, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_hopper",sourceCapitalized.trim() + " Hopper",
				UpgradedVanilla.ID, new HopperBlock(FabricBlockSettings.copy(metalBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block) block,"_top"))
							.put(TextureSlot.SIDE, TextureMapping.getBlockTexture((Block) block,"_outside"))
							.put(TextureSlot.INSIDE, TextureMapping.getBlockTexture((Block) block,"_inside"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture((Block) block,"_outside"));
					ResourceLocation hopper = UpgradedVanillaModelTemplates.HOPPER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation hopperSide = UpgradedVanillaModelTemplates.HOPPER_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.createSimpleFlatItemModel(block.asItem());
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(PropertyDispatch.property(BlockStateProperties.FACING_HOPPER).select(Direction.DOWN, Variant.variant().with(VariantProperties.MODEL, hopper)).select(Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, hopperSide)).select(Direction.EAST, Variant.variant().with(VariantProperties.MODEL, hopperSide).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, hopperSide).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).select(Direction.WEST, Variant.variant().with(VariantProperties.MODEL, hopperSide).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', metalIngot).define('N', UVCommonItemTags.CHEST)
							.pattern("P P")
							.pattern("PNP")
							.pattern(" P ")
							.unlockedBy("has_" + material + "_ingots", FabricRecipeProvider.has(metalIngot)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File insideHopperTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\hopper_inside.png");
				TextureHelper.swapColors("block\\" + material+"_hopper_inside", "block", UpgradedVanilla.ID,ImageIO.read(insideHopperTexture),TextureHelper.metalPresetPalette,out);
				File outsideHopperTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\hopper_outside.png");
				TextureHelper.swapColors("block\\" + material+"_hopper_outside", "block", UpgradedVanilla.ID,ImageIO.read(outsideHopperTexture),TextureHelper.metalPresetPalette,out);
				File topHopperTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\hopper_top.png");
				TextureHelper.swapColors("block\\" + material+"_hopper_top", "block", UpgradedVanilla.ID,ImageIO.read(topHopperTexture),TextureHelper.metalPresetPalette,out);
				File itemHopperTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\item\\hopper.png");
				TextureHelper.swapColors("item\\" + material+"_hopper", "item", UpgradedVanilla.ID,ImageIO.read(itemHopperTexture),TextureHelper.metalPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Block anvilBlock(String material, Item metalIngot, Block metalBlock, TagKey<Item> metalBlockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block anvil = BlockHandler.registerBlockWithRecipe(material+"_anvil",sourceCapitalized.trim() + " Anvil",
				UpgradedVanilla.ID, new VariantAnvilBlock(FabricBlockSettings.copy(metalBlock).noOcclusion().sound(SoundType.ANVIL), material), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block) block,"_top"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture((Block) block))
							.put(UpgradedVanillaModelTemplates.BODY, TextureMapping.getBlockTexture((Block) block));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.ANVIL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation).with(BlockModelGenerators.createHorizontalFacingDispatchAlt()));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', metalIngot).define('N', metalBlockTag)
							.pattern("NNN")
							.pattern(" P ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_blocks", FabricRecipeProvider.has(metalIngot)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		Block chippedAnvil = BlockHandler.registerBlock("chipped_"+material+"_anvil","Chipped "+sourceCapitalized.trim() + " Anvil",
				UpgradedVanilla.ID, new VariantAnvilBlock(FabricBlockSettings.copy(metalBlock).noOcclusion().sound(SoundType.ANVIL), material), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block) block,"_top"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(anvil))
							.put(UpgradedVanillaModelTemplates.BODY, TextureMapping.getBlockTexture(anvil));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.ANVIL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation).with(BlockModelGenerators.createHorizontalFacingDispatchAlt()));
				},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		Block damagedAnvil = BlockHandler.registerBlock("damaged_"+material+"_anvil","Damaged "+sourceCapitalized.trim() + " Anvil",
				UpgradedVanilla.ID, new VariantAnvilBlock(FabricBlockSettings.copy(metalBlock).noOcclusion().sound(SoundType.ANVIL), material), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block) block,"_top"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(anvil))
							.put(UpgradedVanillaModelTemplates.BODY, TextureMapping.getBlockTexture(anvil));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.ANVIL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation).with(BlockModelGenerators.createHorizontalFacingDispatchAlt()));
				},
				BlockLoot::dropSelf,
				blockTags, itemTags);
		AnvilRegistry.putAnvils(material, (AnvilBlock) anvil, (AnvilBlock) chippedAnvil, (AnvilBlock) damagedAnvil);
		TextureHelper.addTexture(() -> {
			try {
				File anvilTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\anvil.png");
				TextureHelper.swapColors("block\\" + material+"_anvil", "block", UpgradedVanilla.ID,ImageIO.read(anvilTexture),TextureHelper.metalPresetPalette,out);
				File anvilTopTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\anvil_top.png");
				TextureHelper.swapColors("block\\" + material+"_anvil_top", "block", UpgradedVanilla.ID,ImageIO.read(anvilTopTexture),TextureHelper.metalPresetPalette,out);
				File chippedAnvilTopTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\chipped_anvil_top.png");
				TextureHelper.swapColors("block\\" + "chipped_"+material+"_anvil_top", "block", UpgradedVanilla.ID,ImageIO.read(chippedAnvilTopTexture),TextureHelper.metalPresetPalette,out);
				File damagedAnvilTopTexture = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\Metal\\Textures\\damaged_anvil_top.png");
				TextureHelper.swapColors("block\\" + "damaged_"+material+"_anvil_top", "block", UpgradedVanilla.ID,ImageIO.read(damagedAnvilTopTexture),TextureHelper.metalPresetPalette,out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		BlockRenderLayerMap.INSTANCE.putBlock(anvil, RenderType.cutout());
		return anvil;
	}
}
