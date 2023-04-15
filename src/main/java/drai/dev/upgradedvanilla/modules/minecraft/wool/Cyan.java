package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Cyan {
	public static TagKey<Item> CYAN_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		CYAN_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("cyan_wool_blocks", List.of(new ResourceLocation("minecraft", "cyan_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
