package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Purple {
	public static TagKey<Item> PURPLE_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		PURPLE_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("purple_wool_blocks", List.of(new ResourceLocation("minecraft", "purple_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
