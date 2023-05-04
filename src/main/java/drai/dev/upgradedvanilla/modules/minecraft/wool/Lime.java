package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Lime {
	public static TagKey<Item> LIME_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		LIME_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"lime_wool_blocks"), List.of(new ResourceLocation("minecraft", "lime_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
