package drai.dev.upgradedvanilla.modules.minecraft.concrete;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.sand.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class RedConcretePowder {
	static int dustColor = 0xA73433;
	public static TagKey<Item> RED_CONCRETE_POWDER_BLOCK_ITEM_TAG;
	public static Block RED_CONCRETE_POWDER_SLAB;
	public static Block RED_CONCRETE_POWDER_STAIRS;

	private static void registerTags(){
		RED_CONCRETE_POWDER_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"red_concrete_powder_blocks"), List.of(new ResourceLocation("minecraft","red_concrete_powder")));
	}
	public  static void register(){
		registerTags();
		RED_CONCRETE_POWDER_SLAB = MinecraftSandBlocks.sandSlabBlock("red_concrete_powder",Blocks.RED_CONCRETE_POWDER, RED_CONCRETE_POWDER_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of(), dustColor);
		RED_CONCRETE_POWDER_STAIRS = MinecraftSandBlocks.sandStairsBlock("red_concrete_powder",Blocks.RED_CONCRETE_POWDER, RED_CONCRETE_POWDER_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL),List.of());
	}
}
