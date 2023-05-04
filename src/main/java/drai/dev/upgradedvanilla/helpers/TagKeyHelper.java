package drai.dev.upgradedvanilla.helpers;

import drai.dev.upgradedvanilla.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class TagKeyHelper {

	public static TagKey<Block> createBlockTagKey(ResourceLocation id){
		return new TagKey(Registry.BLOCK_REGISTRY, id);
	}

	public static TagKey<Block> createCommonBlockTagKey(String id){
		return new TagKey(Registry.BLOCK_REGISTRY, new ResourceLocation("c:"+id));
	}

	public static TagKey<Item> createCommonItemTagKey(String id){
		return new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation("c:"+id));
	}

	public static TagKey<Block> createBlockTagKeyWithCompositeTags(ResourceLocation id, List<TagKey<Block>> blockTags){
		TagKey<Block> returnTag = new TagKey(Registry.BLOCK_REGISTRY, id);
		blockTags.forEach(tag->TagHelper.addBlockTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Block> createBlockTagKeyWithBlocks(ResourceLocation id, List<ResourceLocation> blocks){
		TagKey<Block> returnTag = new TagKey(Registry.BLOCK_REGISTRY, id);
		blocks.forEach(block->TagHelper.addBlockTags(block,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Block> createBlockTagKeyWithCompositeTagsAndBlocks(ResourceLocation id, List<TagKey<Block>> blockTags, List<ResourceLocation> blocks){
		TagKey<Block> returnTag = new TagKey(Registry.BLOCK_REGISTRY, id);
		blockTags.forEach(tag->TagHelper.addBlockTags(tag,List.of(returnTag)));
		blocks.forEach(block->TagHelper.addBlockTags(block,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKey(ResourceLocation id){
		return new TagKey(Registry.ITEM_REGISTRY, id);
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTags(ResourceLocation id, List<TagKey<Item>> itemTags){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, id);
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTagsResource(ResourceLocation id, List<ResourceLocation> itemTags){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, id);
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithItems(ResourceLocation id, List<ResourceLocation> items){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, id);
		items.forEach(item->TagHelper.addItemTags(item,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTagsAndItems(ResourceLocation id, List<TagKey<Item>> itemTags, List<ResourceLocation> items){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, id);
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		items.forEach(item->TagHelper.addItemTags(item,List.of(returnTag)));
		return returnTag;
	}
}
