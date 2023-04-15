package drai.dev.upgradedvanilla.modules.minecraft.stone.stone;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Stone {
	public static TagKey<Item> STONE_SLABS_ITEM_TAG;
	public static TagKey<Item> STONE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> COBBLESTONE_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		STONE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("stone_slabs", List.of(new ResourceLocation("minecraft", "stone_slab")));
		STONE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("stone_blocks", List.of(new ResourceLocation("minecraft", "stone")));
		COBBLESTONE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("cobblestone_blocks", List.of(new ResourceLocation("minecraft", "cobblestone")));
	}
	public  static void register(){
		registerTags();
	}
}
