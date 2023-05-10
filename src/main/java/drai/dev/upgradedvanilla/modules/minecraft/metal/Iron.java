package drai.dev.upgradedvanilla.modules.minecraft.metal;

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

public class Iron {
	//public static File palette = new File(Iron.class
	//		.getClassLoader().getResource("templatedata\\Metal\\Pallete\\ironPallete.png").toString());
	//public static File palette = new File(new ResourceLocation(UpgradedVanilla.ID, "templatedata/metal/pallete/ironpallete").toDebugFileName()+".png");
	public static File palette = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Metal\\Pallete\\ironPallete.png");
	private static final String MATERIAL_NAME = "iron";
	public static TagKey<Item> IRON_SLABS_ITEM_TAG;
	public static TagKey<Item> IRON_BLOCKS_ITEM_TAG;
	public static TagKey<Item> CUT_IRON_SLABS_ITEM_TAG;
	public static TagKey<Item> CUT_IRON_BLOCKS_ITEM_TAG;
	public static TagKey<Item> RAW_IRON_SLABS_ITEM_TAG;
	public static TagKey<Item> RAW_IRON_BLOCKS_ITEM_TAG;
	public static Block IRON_STAIRS;
	public static Block IRON_SLAB;
	public static Block IRON_WALL;
	public static Block IRON_FENCE;
	public static Block IRON_FENCE_GATE;
	public static Block RAW_IRON_STAIRS;
	public static Block RAW_IRON_SLAB;
	public static Block RAW_IRON_WALL;
	public static Block CUT_IRON_STAIRS;
	public static Block CUT_IRON_SLAB;
	public static Block CUT_IRON_WALL;
	public static Block CUT_IRON_FENCE;
	public static Block CUT_IRON_FENCE_GATE;
	public static Block CUT_IRON_TRAPDOOR;
	public static Block CUT_IRON;
	public static Block IRON_LIGHTNING_ROD;
	public static Block IRON_CHAIN;
	public static Block IRON_HOPPER;
	public static Block IRON_ANVIL;
	private static void registerTags(){
		IRON_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"iron_slabs"));
		IRON_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"iron_blocks"), List.of(new ResourceLocation("minecraft", "iron_block")));
		RAW_IRON_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"raw_iron_slabs"));
		RAW_IRON_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"raw_iron_blocks"), List.of(new ResourceLocation("minecraft", "raw_iron_block")));
		CUT_IRON_SLABS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"cut_iron_slabs"));
		CUT_IRON_BLOCKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"cut_iron_blocks"));
	}
	public  static void register(){
		registerTags();
		//System.out.println(palette);
		IRON_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, Blocks.IRON_BLOCK, IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		IRON_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, Blocks.IRON_BLOCK, IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(IRON_SLABS_ITEM_TAG));
		IRON_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, Blocks.IRON_BLOCK, IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		IRON_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, Blocks.IRON_BLOCK, IRON_SLABS_ITEM_TAG, IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		IRON_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, Blocks.IRON_BLOCK, IRON_SLABS_ITEM_TAG, IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		RAW_IRON_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, Blocks.RAW_IRON_BLOCK, RAW_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		RAW_IRON_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, Blocks.RAW_IRON_BLOCK, RAW_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(IRON_SLABS_ITEM_TAG));
		RAW_IRON_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, Blocks.RAW_IRON_BLOCK, RAW_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		CUT_IRON = MinecraftMetalBlocks.metalCutBlock(MATERIAL_NAME, Blocks.IRON_BLOCK, List.of(BlockTags.MINEABLE_WITH_PICKAXE),
				List.of(CUT_IRON_BLOCKS_ITEM_TAG),palette);
		CUT_IRON_STAIRS = MinecraftStoneBlocks.stoneStairsBlock(MATERIAL_NAME, CUT_IRON, CUT_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CUT_IRON_SLAB = MinecraftStoneBlocks.stoneSlabBlock(MATERIAL_NAME, CUT_IRON, CUT_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE), List.of(CUT_IRON_SLABS_ITEM_TAG));
		CUT_IRON_WALL = MinecraftStoneBlocks.stoneWallBlock(MATERIAL_NAME, CUT_IRON, CUT_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.WALLS,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CUT_IRON_FENCE = MinecraftStoneBlocks.stoneFenceBlock(MATERIAL_NAME, CUT_IRON, CUT_IRON_SLABS_ITEM_TAG, CUT_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CUT_IRON_FENCE_GATE = MinecraftStoneBlocks.stoneFenceGateBlock(MATERIAL_NAME, CUT_IRON, CUT_IRON_SLABS_ITEM_TAG, CUT_IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());
		CUT_IRON_TRAPDOOR = MinecraftStoneBlocks.stoneTrapdoorBlock(MATERIAL_NAME, CUT_IRON, CUT_IRON_SLABS_ITEM_TAG,
				List.of(BlockTags.FENCE_GATES,BlockTags.MINEABLE_WITH_PICKAXE),List.of());

		IRON_LIGHTNING_ROD = MinecraftMetalBlocks.lightningRodBlock(MATERIAL_NAME, Items.IRON_INGOT, Blocks.IRON_BLOCK,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of(), palette);
		IRON_CHAIN = MinecraftMetalBlocks.metalChainBlock(MATERIAL_NAME, Items.IRON_INGOT, Items.IRON_NUGGET, Blocks.IRON_BLOCK,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of(), palette);
		IRON_HOPPER = MinecraftMetalBlocks.hopperBlock(MATERIAL_NAME, Items.IRON_INGOT, Blocks.IRON_BLOCK,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE),List.of(UpgradedVanillaTags.HOPPERS), palette);
		IRON_ANVIL = MinecraftMetalBlocks.anvilBlock(MATERIAL_NAME, Items.IRON_INGOT, Blocks.IRON_BLOCK, IRON_BLOCKS_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.ANVIL),List.of(), palette);
	}
}
