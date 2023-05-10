package drai.dev.upgradedvanilla.modules.minecraft.stone.stone;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.*;
import drai.dev.upgradedvanilla.tag.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.io.*;
import java.util.*;

public class Deepslate {
	public static String MATERIAL_NAME = "deepslate";
	public static File deepslatePalette;
	public static TagKey<Item> DEEPSLATE_SLABS_ITEM_TAG;
	public static TagKey<Item> DEEPSLATE_BLOCKS_ITEM_TAG;
	public static TagKey<Item> COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG;
	public static Block DEEPSLATE_FURNACE;
	public static Block DEEPSLATE_BLAST_FURNACE;
	public static Block DEEPSLATE_DISPENSER;
	public static Block DEEPSLATE_DROPPER;
	public static Block DEEPSLATE_OBSERVER;
	public static Block DEEPSLATE_STAIRS;
	private static void registerTags(){
		DEEPSLATE_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"deepslate_slabs"));
		DEEPSLATE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"deepslate_blocks"), List.of(new ResourceLocation("minecraft", "deepslate")));
		COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"cobbled_deepslate_blocks"), List.of(new ResourceLocation("minecraft", "cobbled_deepslate")));
	}

	public  static void register(){
		registerTags();
			deepslatePalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\create-fabric-addon-template-1.19\\create-fabric-addon-template-1.19\\template data\\Stone\\Palletes\\deepslatePalette.png");
			DEEPSLATE_FURNACE = MinecraftStoneBlocks.furnaceBlock(MATERIAL_NAME, Blocks.COBBLED_DEEPSLATE, COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG,
					List.of(BlockTags.MINEABLE_WITH_PICKAXE),
					List.of(UVCommonItemTags.FURNACES),
					deepslatePalette,
					SoundType.DEEPSLATE);
			DEEPSLATE_BLAST_FURNACE = MinecraftStoneBlocks.blastFurnaceBlock(MATERIAL_NAME, DEEPSLATE_FURNACE, Blocks.DEEPSLATE, COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG,
					List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.FURNACES), deepslatePalette, SoundType.DEEPSLATE);
			DEEPSLATE_DISPENSER = MinecraftStoneBlocks.dispenserBlock(MATERIAL_NAME, DEEPSLATE_FURNACE, Blocks.DEEPSLATE, COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG,
					List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.FURNACES), deepslatePalette, SoundType.DEEPSLATE);
			DEEPSLATE_DROPPER = MinecraftStoneBlocks.dropperBlock(MATERIAL_NAME, DEEPSLATE_FURNACE, Blocks.DEEPSLATE, COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG,
					List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.FURNACES), deepslatePalette, SoundType.DEEPSLATE);
			DEEPSLATE_OBSERVER = MinecraftStoneBlocks.observerBlock(MATERIAL_NAME, Blocks.DEEPSLATE, COBBLED_DEEPSLATE_BLOCKS_ITEM_TAG,
					List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.FURNACES), deepslatePalette, SoundType.DEEPSLATE);
	}
}
