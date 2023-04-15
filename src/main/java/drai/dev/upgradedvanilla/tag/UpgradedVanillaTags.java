package drai.dev.upgradedvanilla.tag;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class UpgradedVanillaTags {
	public static TagKey<Item> STICKS;
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
	public static TagKey<Item> WORKBENCH_ITEM_TAG;
	public static TagKey<Item> CAMPFIRE_ITEM_TAG;
	public static TagKey<Item> STONE_PRESSURE_PLATE_ITEMS;
	public static TagKey<Item> SOUL_CAMPFIRE_ITEM_TAG;
	public static TagKey<Item> BOOKSHELVES_ITEM_TAG;
	public static TagKey<Item> STONE_BLOCK_ITEMS;
	public static TagKey<Item> TRIPWIRE_HOOK_ITEMS;
	public static boolean initialized = false;

	public static void register(){
		if(!initialized){
			UVCommonItemTags.register();
			UVCommonBlockTags.register();
			WOODEN_WALLS_BLOCK_TAG = TagKeyHelper.createBlockTagKey("wooden_walls");
			WOODEN_WALLS_ITEM_TAG = TagKeyHelper.createItemTagKey("wooden_walls");
			WOODEN_LADDER_BLOCK_TAG = TagKeyHelper.createBlockTagKey("wooden_ladders");
			WOODEN_LADDER_ITEM_TAG = TagKeyHelper.createItemTagKey("wooden_ladders");
			LEVERS_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks("levers", List.of(new ResourceLocation("minecraft:lever")));
			LEVERS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("levers", List.of(new ResourceLocation("minecraft:lever")));
			TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks("torches", List.of(new ResourceLocation("minecraft:torch")));
			TORCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("torches", List.of(new ResourceLocation("minecraft:torch")));
			SOUL_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks("soul_torches", List.of(new ResourceLocation("minecraft:soul_torch")));
			SOUL_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("soul_torches", List.of(new ResourceLocation("minecraft:soul_torch")));
			REDSTONE_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks("redstone_torches", List.of(new ResourceLocation("minecraft:redstone_torch")));
			REDSTONE_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("redstone_torches", List.of(new ResourceLocation("minecraft:redstone_torch")));
			WORKBENCH_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks("workbenches", List.of(new ResourceLocation("minecraft:crafting_table")));
			WORKBENCH_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("workbenches", List.of(new ResourceLocation("minecraft:crafting_table")));
			CAMPFIRE_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("campfires", List.of(new ResourceLocation("minecraft:campfire"),new ResourceLocation("minecraft:soul_campfire")));
			BOOKSHELVES_ITEM_TAG = TagKeyHelper.createItemTagKey("bookshelves");
			STONE_BLOCK_ITEMS = TagKeyHelper.createItemTagKeyWithItems("generic_stone_blocks",
					List.of(new ResourceLocation("minecraft:stone"),
							new ResourceLocation("minecraft:granite"),
							new ResourceLocation("minecraft:deepslate"),
							new ResourceLocation("minecraft:andesite"),
							new ResourceLocation("minecraft:diorite"),
							new ResourceLocation("minecraft:calcite")));
			STONE_PRESSURE_PLATE_ITEMS = TagKeyHelper.createItemTagKeyWithItems("stone_pressure_plates", List.of(new ResourceLocation("minecraft:stone_pressure_plate")));
			STICKS = TagKeyHelper.createItemTagKeyWithItems("sticks", List.of(new ResourceLocation("minecraft:stick")));
			TRIPWIRE_HOOK_ITEMS = TagKeyHelper.createItemTagKeyWithItems("tripwire_hooks", List.of(new ResourceLocation("minecraft:tripwire_hook")));
			initialized = true;
		}
	}
}
