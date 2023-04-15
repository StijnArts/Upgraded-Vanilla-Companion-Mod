package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Black {
	public static TagKey<Item> BLACK_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		BLACK_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("black_wool_blocks", List.of(new ResourceLocation("minecraft", "black_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
