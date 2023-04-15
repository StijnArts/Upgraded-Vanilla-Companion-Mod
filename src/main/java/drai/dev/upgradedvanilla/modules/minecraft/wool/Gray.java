package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Gray {
	public static TagKey<Item> GRAY_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		GRAY_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("gray_wool_blocks", List.of(new ResourceLocation("minecraft", "gray_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
