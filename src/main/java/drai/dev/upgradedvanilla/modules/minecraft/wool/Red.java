package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Red {
	public static TagKey<Item> RED_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		RED_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("red_wool_blocks", List.of(new ResourceLocation("minecraft", "red_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
