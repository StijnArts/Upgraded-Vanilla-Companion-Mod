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

	public static TagKey<Block> createBlockTagKey(String id){
		return new TagKey(Registry.BLOCK_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
	}

	public static TagKey<Block> createCommonBlockTagKey(String id){
		return new TagKey(Registry.BLOCK_REGISTRY, new ResourceLocation("c:"+id));
	}

	public static TagKey<Item> createCommonItemTagKey(String id){
		return new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation("c:"+id));
	}

	public static TagKey<Block> createBlockTagKeyWithCompositeTags(String id, List<TagKey<Block>> blockTags){
		TagKey<Block> returnTag = new TagKey(Registry.BLOCK_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		blockTags.forEach(tag->TagHelper.addBlockTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Block> createBlockTagKeyWithBlocks(String id, List<ResourceLocation> blocks){
		TagKey<Block> returnTag = new TagKey(Registry.BLOCK_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		blocks.forEach(block->TagHelper.addBlockTags(block,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Block> createBlockTagKeyWithCompositeTagsAndBlocks(String id, List<TagKey<Block>> blockTags, List<ResourceLocation> blocks){
		TagKey<Block> returnTag = new TagKey(Registry.BLOCK_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		blockTags.forEach(tag->TagHelper.addBlockTags(tag,List.of(returnTag)));
		blocks.forEach(block->TagHelper.addBlockTags(block,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKey(String id){
		return new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTags(String id, List<TagKey<Item>> itemTags){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTagsResource(String id, List<ResourceLocation> itemTags){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithItems(String id, List<ResourceLocation> items){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		items.forEach(item->TagHelper.addItemTags(item,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTagsAndItems(String id, List<TagKey<Item>> itemTags, List<ResourceLocation> items){
		TagKey<Item> returnTag = new TagKey(Registry.ITEM_REGISTRY, new ResourceLocation(UpgradedVanilla.ID+":"+id));
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		items.forEach(item->TagHelper.addItemTags(item,List.of(returnTag)));
		return returnTag;
	}
}
