package drai.dev.upgradedvanilla.tag;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class UpgradedVanillaTags {
	public static TagKey<Item> STICKS;
	public static TagKey<Block> DIRT_BLOCKS;
	public static TagKey<Block> WOODEN_WALLS_BLOCK_TAG;
	public static TagKey<Item> WOODEN_WALLS_ITEM_TAG;
	public static TagKey<Block> WOODEN_LADDER_BLOCK_TAG;
	public static TagKey<Item> WOODEN_LADDER_ITEM_TAG;
	public static TagKey<Block> LEVERS_BLOCK_TAG;
	public static TagKey<Item> LEVERS_ITEM_TAG;
	public static TagKey<Block> TORCH_BLOCK_TAG;
	public static TagKey<Item> TORCH_ITEM_TAG;
	public static TagKey<Block> SOUL_TORCH_BLOCK_TAG;
	public static TagKey<Item> SOUL_TORCH_ITEM_TAG;
	public static TagKey<Block> REDSTONE_TORCH_BLOCK_TAG;
	public static TagKey<Item> REDSTONE_TORCH_ITEM_TAG;
	public static TagKey<Block> WORKBENCH_BLOCK_TAG;
	public static TagKey<Block> PODZOL_REPLACEABLE;
	public static TagKey<Item> WORKBENCH_ITEM_TAG;
	public static TagKey<Item> CAMPFIRE_ITEM_TAG;
	public static TagKey<Item> STONE_PRESSURE_PLATE_ITEMS;
	public static TagKey<Item> SOUL_CAMPFIRE_ITEM_TAG;
	public static TagKey<Item> BOOKSHELVES_ITEM_TAG;
	public static TagKey<Item> STONE_BLOCK_ITEMS;
	public static TagKey<Item> TRIPWIRE_HOOK_ITEMS;
	public static boolean initialized = false;
	public static TagKey<Block> MINEABLE_KNIFE;
	public static TagKey<Block> WINDMILL_SAILS;
	public static TagKey<Item> BRICK_ITEM_TAG;
	public static TagKey<Item> HOPPERS;
	public static void register(){
		if(!initialized){
			UVCommonItemTags.register();
			UVCommonBlockTags.register();
			HOPPERS = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"hopper"), List.of(new ResourceLocation("minecraft:hopper")));
			MINEABLE_KNIFE = TagKeyHelper.createBlockTagKey(new ResourceLocation("farmersdelight","mineable/knife"));
			WINDMILL_SAILS = TagKeyHelper.createBlockTagKey(new ResourceLocation("create","windmill_sails"));
			DIRT_BLOCKS = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,"dirt_blocks"));
			PODZOL_REPLACEABLE = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,"podzol_replaceable"));
			WOODEN_WALLS_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,"wooden_walls"));
			WOODEN_WALLS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"wooden_walls"));
			WOODEN_LADDER_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,"wooden_ladders"));
			WOODEN_LADDER_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"wooden_ladders"));
			LEVERS_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks(new ResourceLocation(UpgradedVanilla.ID,"levers"), List.of(new ResourceLocation("minecraft:lever")));
			LEVERS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"levers"), List.of(new ResourceLocation("minecraft:lever")));
			TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks(new ResourceLocation(UpgradedVanilla.ID,"torches"), List.of(new ResourceLocation("minecraft:torch")));
			TORCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"torches"), List.of(new ResourceLocation("minecraft:torch")));
			SOUL_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks(new ResourceLocation(UpgradedVanilla.ID,"soul_torches"), List.of(new ResourceLocation("minecraft:soul_torch")));
			SOUL_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"soul_torches"), List.of(new ResourceLocation("minecraft:soul_torch")));
			REDSTONE_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks(new ResourceLocation(UpgradedVanilla.ID,"redstone_torches"), List.of(new ResourceLocation("minecraft:redstone_torch")));
			REDSTONE_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"redstone_torches"), List.of(new ResourceLocation("minecraft:redstone_torch")));
			WORKBENCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks(new ResourceLocation(UpgradedVanilla.ID,"workbenches"), List.of(new ResourceLocation("minecraft:crafting_table")));
			WORKBENCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"workbenches"), List.of(new ResourceLocation("minecraft:crafting_table")));
			CAMPFIRE_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"campfires"), List.of(new ResourceLocation("minecraft:campfire"),new ResourceLocation("minecraft:soul_campfire")));
			BOOKSHELVES_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"bookshelves"));
			STONE_BLOCK_ITEMS = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"generic_stone_blocks"),
					List.of(new ResourceLocation("minecraft:stone"),
							new ResourceLocation("minecraft:granite"),
							new ResourceLocation("minecraft:deepslate"),
							new ResourceLocation("minecraft:andesite"),
							new ResourceLocation("minecraft:diorite"),
							new ResourceLocation("minecraft:calcite")));
			STONE_PRESSURE_PLATE_ITEMS = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"stone_pressure_plates"), List.of(new ResourceLocation("minecraft:stone_pressure_plate")));
			STICKS = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"sticks"), List.of(new ResourceLocation("minecraft:stick")));
			TRIPWIRE_HOOK_ITEMS = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"tripwire_hooks"), List.of(new ResourceLocation("minecraft:tripwire_hook")));
			BRICK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"brick_items"), List.of(new ResourceLocation("minecraft:brick"),
					new ResourceLocation("createdeco:scarlet_brick"),
					new ResourceLocation("createdeco:worn_brick"),
					new ResourceLocation("createdeco:blue_brick"),
					new ResourceLocation("createdeco:dusk_brick"),
					new ResourceLocation("createdeco:pearl_brick"),
					new ResourceLocation("createdeco:dean_brick")));
			initialized = true;
		}
	}
}
