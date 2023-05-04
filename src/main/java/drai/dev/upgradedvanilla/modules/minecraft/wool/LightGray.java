package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class LightGray {
	public static TagKey<Item> LIGHT_GRAY_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		LIGHT_GRAY_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"light_gray_wool_blocks"), List.of(new ResourceLocation("minecraft", "light_gray_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
