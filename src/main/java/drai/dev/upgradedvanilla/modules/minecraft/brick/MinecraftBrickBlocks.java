package drai.dev.upgradedvanilla.modules.minecraft.brick;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
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

public class MinecraftBrickBlocks {
	public static Block brickFenceBlock(String material, Block brickBlock, TagKey<Item> slabTag, TagKey<Item> brickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(brickBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(brickBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,brickBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',brickTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_bricks", FabricRecipeProvider.has(brickTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block brickFenceGateBlock(String material, Block brickBlock, TagKey<Item> slabTag, TagKey<Item> brickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(brickBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(brickBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,brickBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',brickTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_bricks", FabricRecipeProvider.has(brickTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block brickTrapdoorBlock(String material, Block brickBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(brickBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(brickBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+material+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,brickBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_bricks", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(() -> {
			try {
				File brickBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(brickBlock).getPath()+".png");
				TextureHelper.flipTexture(ImageIO.read(brickBrickTexture), "block\\" + material+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				System.out.println("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(brickBlock).getPath()+".png");
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block brickStairsBlock(String material, Block brickBlock, TagKey<Item> brickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		return BlockHandler.registerBlockWithRecipe(material + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(brickBlock.defaultBlockState(), FabricBlockSettings.copyOf(brickBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(brickBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,brickBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', brickTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_brick", FabricRecipeProvider.has(brickTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block brickSlabBlock(String material, Block brickBlock, TagKey<Item> brickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		return BlockHandler.registerBlockWithRecipe(material + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(brickBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(brickBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(brickBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,brickBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',brickTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_bricks", FabricRecipeProvider.has(brickTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block brickWallBlock(String material, Block brickBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :material.split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(brickBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(brickBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,brickBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_brick_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block brickBlock(String material, Item brickItem, Block bricks, Item dye, TagKey<Item> brickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : material.split("_")) {
			sourceCapitalized = sourceCapitalized + " " + StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material, sourceCapitalized.trim(),
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(bricks)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', brickItem)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material, FabricRecipeProvider.has(brickItem)).save(finishedRecipeConsumer);
					ShapelessRecipeBuilder.shapeless(item, 1).requires(brickTag).requires(dye)
							.unlockedBy("has_" + material, FabricRecipeProvider.has(brickItem)).save(finishedRecipeConsumer,material+"from_brick_and_dye");
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
}
