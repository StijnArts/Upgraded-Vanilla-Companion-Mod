package drai.dev.upgradedvanilla.modules.minecraft.wood;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.leaves.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.model.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.color.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.*;

import java.util.*;
import java.util.function.*;

public class MinecraftLeafBlocks {
	private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05f, 0.0625f, 0.083333336f, 0.1f};

	public static Block leafStairsBlock(String material, Block leafBlock, Block sapling, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
										List<TagKey<Item>> itemTags, BiFunction<BlockAndTintGetter, BlockPos,Integer> blockColorFunction, int defaultColor) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_leaf_stairs", StringUtil.capitalizeWord(material) + " Leaf Stairs",
				UpgradedVanilla.ID, new LeafStairsBlock(leafBlock.defaultBlockState(), FabricBlockSettings.copyOf(leafBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(leafBlock);
					ResourceLocation inner = UpgradedVanillaModelTemplates.LEAVES_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = UpgradedVanillaModelTemplates.LEAVES_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = UpgradedVanillaModelTemplates.LEAVES_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,leafBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', plankTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_leaves", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createLeavesDrops(block, sapling, NORMAL_LEAVES_SAPLING_CHANCES));},
				blockTags, itemTags);
		UpgradedVanillaClient.addClientSideOperation(()->{
			ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
				return blockColorFunction.apply(world,pos);
			}, new Block[]{returnBlock});
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return defaultColor;
			}, new ItemLike[]{returnBlock.asItem()});});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block leafSlabBlock(String material, Block leafBlock, Block sapling, TagKey<Item> plankTag, List<TagKey<Block>> blockTags,
									  List<TagKey<Item>> itemTags, BiFunction<BlockAndTintGetter, BlockPos,Integer> blockColorFunction, int defaultColor) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_leaf_slab", StringUtil.capitalizeWord(material) + " Leaf Slab",
				UpgradedVanilla.ID, new LeafSlabBlock(FabricBlockSettings.copyOf(leafBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(leafBlock);
					ResourceLocation bottom = UpgradedVanillaModelTemplates.LEAVES_SLAB.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = UpgradedVanillaModelTemplates.LEAVES_SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(leafBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,leafBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',plankTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_leaves", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createLeavesDrops(block, sapling, NORMAL_LEAVES_SAPLING_CHANCES));},
				blockTags, itemTags);
		UpgradedVanillaClient.addClientSideOperation(()->{
			ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
				return blockColorFunction.apply(world,pos);
			}, new Block[]{returnBlock});
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return defaultColor;
			}, new ItemLike[]{returnBlock.asItem()});});
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block leafWallBlock(String material, Block leafBlock, Block sapling, TagKey<Item> blockTag, List<TagKey<Block>> blockTags,
									  List<TagKey<Item>> itemTags, BiFunction<BlockAndTintGetter, BlockPos,Integer> blockColorFunction, int defaultColor) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_leaf_wall", StringUtil.capitalizeWord(material) + " Leaf Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(leafBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(leafBlock);
					ResourceLocation inventory = UpgradedVanillaModelTemplates.LEAVES_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = UpgradedVanillaModelTemplates.LEAVES_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = UpgradedVanillaModelTemplates.LEAVES_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = UpgradedVanillaModelTemplates.LEAVES_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,leafBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_leaves", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createLeavesDrops(block, sapling, NORMAL_LEAVES_SAPLING_CHANCES));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		UpgradedVanillaClient.addClientSideOperation(()->{
			ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
				return blockColorFunction.apply(world,pos);
			}, new Block[]{returnBlock});
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return defaultColor;
			}, new ItemLike[]{returnBlock.asItem()});});
		return returnBlock;
	}
}
