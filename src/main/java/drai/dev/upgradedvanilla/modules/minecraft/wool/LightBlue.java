package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class LightBlue {
	public static TagKey<Item> LIGHT_BLUE_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		LIGHT_BLUE_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems("light_blue_wool_blocks", List.of(new ResourceLocation("minecraft", "light_blue_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
