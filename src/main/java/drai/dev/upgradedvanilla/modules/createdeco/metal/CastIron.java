package drai.dev.upgradedvanilla.modules.createdeco.metal;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class CastIron {
	public static TagKey<Item> CAST_IRON_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		CAST_IRON_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"cast_iron_blocks"), List.of(new ResourceLocation("createdeco", "cast_iron_block")));
		}
	public  static void register() {
		registerTags();
	}
}
