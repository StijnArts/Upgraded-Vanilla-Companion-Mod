package drai.dev.upgradedvanilla.modules.minecraft.soil;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.dirt.*;
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
import net.minecraft.world.level.block.state.*;

import java.util.*;

public class MinecraftDirtBlocks {
	private static final Set<BlockState> podzolReplaceableStairs = new HashSet();
	private static final Set<BlockState> podzolReplaceableSlabs = new HashSet();
	public static Block dirtStairsBlock(String material, Block dirtBlock, TagKey<Item> dirtTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment : Registry.BLOCK.getKey(dirtBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+ StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(dirtBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new DirtStairs(dirtBlock.defaultBlockState(), FabricBlockSettings.copyOf(dirtBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(dirtBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,dirtBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', dirtTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_dirt", FabricRecipeProvider.has(dirtTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		podzolReplaceableStairs.addAll(returnBlock.getStateDefinition().getPossibleStates());
		return returnBlock;
	}
	public static Block dirtSlabBlock(String material, Block dirtBlock, TagKey<Item> dirtTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(dirtBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(dirtBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new DirtSlab(FabricBlockSettings.copyOf(dirtBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(dirtBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(dirtBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,dirtBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',dirtTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(dirtTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		podzolReplaceableSlabs.addAll(returnBlock.getStateDefinition().getPossibleStates());
		return returnBlock;
	}

	public static Block dirtWallBlock(String material, Block dirtBlock, TagKey<Item> dirtTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(dirtBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(dirtBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new DirtWall(FabricBlockSettings.copyOf(dirtBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(dirtBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,dirtBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',dirtTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_dirt", FabricRecipeProvider.has(dirtTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}

	public static Set<BlockState> getPodzolReplaceableStairs(){
		return podzolReplaceableStairs;
	}

	public static Set<BlockState> getPodzolReplaceableSlabs(){
		return podzolReplaceableSlabs;
	}

}
