package drai.dev.upgradedvanilla.helpers.block;

import drai.dev.upgradedvanilla.datageneration.providers.*;
import drai.dev.upgradedvanilla.datageneration.providers.loottables.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.minecraft.core.*;
import net.minecraft.data.models.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;

import java.util.*;
import java.util.function.*;

public class BlockHandler {


	public static Block registerBlockWithoutItem(String name, String modId, Block block,
									  BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  List<TagKey<Block>> blockTags){
		Block returnBlock = Registry.register(Registry.BLOCK, IDHelper.createID(name,modId),block);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}
	public static Block registerBlockWithRecipe(String name, String translation, String modId, Block block, CreativeModeTab tab,
												BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
												BiConsumer<Consumer<FinishedRecipe>, ItemLike> recipeConsumer,
												BiConsumer<BlockLootProvider, Block> lootTableConsumer,
												List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, tab, itemTags);
		Block returnBlock = Registry.register(Registry.BLOCK, IDHelper.createID(name,modId),block);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnedItem,recipeConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	public static Block registerNonStackableBlockWithRecipe(String name, String translation, String modId, Block block, CreativeModeTab tab,
												BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
												BiConsumer<Consumer<FinishedRecipe>, ItemLike> recipeConsumer,
												BiConsumer<BlockLootProvider, Block> lootTableConsumer,
												List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerNonStackableBlockItem(name, modId, block, tab, itemTags);
		Block returnBlock = Registry.register(Registry.BLOCK, IDHelper.createID(name,modId),block);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnedItem,recipeConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}
	public static Block registerBlockWithRecipe(String name, String translation, String modId, Block block, CreativeModeTab tab,
									  	BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  	BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
										BiConsumer<Consumer<FinishedRecipe>, ItemLike> recipeConsumer,
												BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  	List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, tab,itemTags);
		Block returnBlock = Registry.register(Registry.BLOCK, IDHelper.createID(name,modId),block);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnedItem,recipeConsumer);
		ItemModelHelper.addItemModel(returnedItem,itemModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	public static Block registerBlock(String name, String translation, String modId, Block block, CreativeModeTab tab,
									  BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, tab, itemTags);
		Block returnBlock = Registry.register(Registry.BLOCK, IDHelper.createID(name,modId),block);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	public static Block registerBlock(String name, String translation, String modId, Block block, CreativeModeTab tab,
									  BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
									  BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, tab,itemTags);
		Block returnBlock = Registry.register(Registry.BLOCK, IDHelper.createID(name,modId),block);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		ItemModelHelper.addItemModel(returnedItem,itemModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	private static Item registerBlockItem(String name, String modId, Block block, CreativeModeTab tab, List<TagKey<Item>> itemTags){
		Item returnItem = Registry.register(Registry.ITEM,IDHelper.createID(name,modId),new BlockItem(block, new FabricItemSettings().group(tab)));
		TagHelper.addItemTags(returnItem,itemTags);
		return returnItem;
	}

	private static Item registerNonStackableBlockItem(String name, String modId, Block block, CreativeModeTab tab, List<TagKey<Item>> itemTags){
		Item returnItem = Registry.register(Registry.ITEM,IDHelper.createID(name,modId),new BlockItem(block, new FabricItemSettings().group(tab).stacksTo(1)));
		TagHelper.addItemTags(returnItem,itemTags);
		return returnItem;
	}
}
