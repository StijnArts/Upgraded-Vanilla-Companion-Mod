package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class White {
	public static TagKey<Item> WHITE_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		WHITE_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("white_wool_blocks", List.of(new ResourceLocation("minecraft", "white_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
