package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Green {
	public static TagKey<Item> GREEN_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		GREEN_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"green_wool_blocks"), List.of(new ResourceLocation("minecraft", "green_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
