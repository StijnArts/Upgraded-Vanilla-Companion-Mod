package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Magenta {
	public static TagKey<Item> MAGENTA_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		MAGENTA_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("magenta_wool_blocks", List.of(new ResourceLocation("minecraft", "magenta_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
