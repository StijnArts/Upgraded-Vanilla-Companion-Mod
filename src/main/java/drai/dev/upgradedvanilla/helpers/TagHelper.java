package drai.dev.upgradedvanilla.helpers;

import com.google.common.collect.*;

import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.ai.village.poi.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class TagHelper {
	private static LinkedHashMultimap<TagKey<Block>, ResourceLocation> resourceBlockTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<Block>, Block> blockTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<Block>, TagKey<Block>> compositeBlockTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<Item>, ResourceLocation> resourceItemTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<Item>, Item> itemTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<Item>, TagKey<Item>> compositeItemTags = LinkedHashMultimap.create();

	private static LinkedHashMultimap<TagKey<PoiType>, ResourceLocation> resourcePoiTypeTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<PoiType>, PoiType> poiTypeTags = LinkedHashMultimap.create();
	private static LinkedHashMultimap<TagKey<PoiType>, TagKey<PoiType>> compositePoiTypeTags = LinkedHashMultimap.create();
	public static void addItemTags(ResourceLocation item, List<TagKey<Item>> tags){
		for (TagKey<Item> tag : tags) {
			resourceItemTags.put(tag,item);
		}
	}
	public static void addItemTags(TagKey<Item> itemTag, List<TagKey<Item>> tags){
		for (TagKey<Item> tag : tags) {
			compositeItemTags.put(tag,itemTag);
		}
	}
	public static void addItemTags(Item item, List<TagKey<Item>> tags){
		for (TagKey<Item> tag : tags) {
			itemTags.put(tag,item);
		}
	}
	public static void addBlockTags(ResourceLocation block, List<TagKey<Block>> tags){
		for (TagKey<Block> tag : tags) {
			resourceBlockTags.put(tag,block);
		}
	}
	public static void addBlockTags(TagKey<Block> blockTag, List<TagKey<Block>> tags){
		for (TagKey<Block> tag : tags) {
			compositeBlockTags.put(tag,blockTag);
		}
	}
	public static void addBlockTags(Block block, List<TagKey<Block>> tags){
		for (TagKey<Block> tag : tags) {
			blockTags.put(tag,block);
		}
	}

	public static void addPoiTypeTags(ResourceLocation poiType, List<TagKey<PoiType>> tags){
		for (TagKey<PoiType> tag : tags) {
			resourcePoiTypeTags.put(tag,poiType);
		}
	}
	public static void addPoiTypeTags(TagKey<PoiType> poiTypeTag, List<TagKey<PoiType>> tags){
		for (TagKey<PoiType> tag : tags) {
			compositePoiTypeTags.put(tag,poiTypeTag);
		}
	}
	public static void addPoiTypeTags(PoiType poiType, List<TagKey<PoiType>> tags){
		for (TagKey<PoiType> tag : tags) {
			poiTypeTags.put(tag,poiType);
		}
	}

	public static LinkedHashMultimap<TagKey<Item>, Item> getItemTags() {
		return itemTags;
	}
	public static LinkedHashMultimap<TagKey<Item>, ResourceLocation> getResourceItemTags() {
		return resourceItemTags;
	}
	public static LinkedHashMultimap<TagKey<Item>, TagKey<Item>> getCompositeItemTags() {
		return compositeItemTags;
	}
	public static LinkedHashMultimap<TagKey<Block>, Block> getBlockTags(){
		return blockTags;
	}
	public static LinkedHashMultimap<TagKey<Block>, ResourceLocation> getResourceBlockTags() {
		return resourceBlockTags;
	}
	public static LinkedHashMultimap<TagKey<Block>, TagKey<Block>> getCompositeBlockTags() {
		return compositeBlockTags;
	}
	public static LinkedHashMultimap<TagKey<PoiType>, PoiType> getPoiTypeTags(){
		return poiTypeTags;
	}
	public static LinkedHashMultimap<TagKey<PoiType>, ResourceLocation> getResourcePoiTypeTags() {
		return resourcePoiTypeTags;
	}
	public static LinkedHashMultimap<TagKey<PoiType>, TagKey<PoiType>> getCompositePoiTypeTags() {
		return compositePoiTypeTags;
	}
}
