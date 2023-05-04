package drai.dev.upgradedvanilla.modules.minecraft.wool;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;

public class Blue {
	public static TagKey<Item> BLUE_WOOL_BLOCKS_ITEM_TAG;
	private static void registerTags(){
		BLUE_WOOL_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"blue_wool_blocks"), List.of(new ResourceLocation("minecraft", "blue_wool")));
	}

	public  static void register(){
		registerTags();
	}
}
