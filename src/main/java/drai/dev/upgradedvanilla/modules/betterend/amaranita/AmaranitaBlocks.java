package drai.dev.upgradedvanilla.modules.betterend.amaranita;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.modules.minecraft.*;
import drai.dev.upgradedvanilla.modules.minecraft.wood.*;
import drai.dev.upgradedvanilla.tag.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;

import org.betterx.bclib.items.boat.*;
import org.betterx.betterend.registry.*;

import org.betterx.worlds.together.tag.v3.*;

import samebutdifferent.ecologics.mixin.fabric.WoodTypeAccessor;

import java.io.*;
import java.util.*;

public class AmaranitaBlocks {
	public static final String MATERIAL_NAME = "amaranita";
	private static WoodType AMARANITA = WoodTypeAccessor.invokeRegister(WoodTypeAccessor.invokeConstructor(MATERIAL_NAME));
	private static ArrayList<Block> logBlocks = new ArrayList<>();
	private static ArrayList<Block> woodBlocks = new ArrayList<>();
	public static File amaranitaPalette;
	public static void registerTags(){
		TagHelper.addBlockTags(AMARANITA_STEMS_BLOCK_TAG,List.of(BlockTags.LOGS, BlockTags.LOGS_THAT_BURN, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_STEMS_ITEM_TAG,List.of(ItemTags.LOGS, ItemTags.LOGS_THAT_BURN));
		TagHelper.addBlockTags(AMARANITA_PLANKS_BLOCK_TAG, List.of(BlockTags.PLANKS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_PLANKS_ITEM_TAG, List.of(ItemTags.PLANKS));
		TagHelper.addBlockTags(AMARANITA_STAIRS_BLOCK_TAG, List.of(BlockTags.WOODEN_STAIRS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_STAIRS_ITEM_TAG, List.of(ItemTags.WOODEN_STAIRS));
		TagHelper.addBlockTags(AMARANITA_SLAB_BLOCK_TAG, List.of(BlockTags.WOODEN_SLABS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_SLAB_ITEM_TAG, List.of(ItemTags.WOODEN_SLABS));
		TagHelper.addBlockTags(AMARANITA_FENCE_BLOCK_TAG, List.of(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_FENCE_ITEM_TAG, List.of(ItemTags.WOODEN_FENCES));
		TagHelper.addBlockTags(AMARANITA_FENCE_GATE_BLOCK_TAG, List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_BUTTON_ITEM_TAG, List.of(ItemTags.WOODEN_BUTTONS));
		TagHelper.addBlockTags(AMARANITA_BUTTON_BLOCK_TAG, List.of(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_PRESSURE_PLATE_ITEM_TAG, List.of(ItemTags.WOODEN_PRESSURE_PLATES));
		TagHelper.addBlockTags(AMARANITA_PRESSURE_PLATE_BLOCK_TAG, List.of(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_DOOR_ITEM_TAG, List.of(ItemTags.WOODEN_DOORS));
		TagHelper.addBlockTags(AMARANITA_DOOR_BLOCK_TAG, List.of(BlockTags.WOODEN_DOORS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_TRAPDOOR_ITEM_TAG, List.of(ItemTags.WOODEN_TRAPDOORS));
		TagHelper.addBlockTags(AMARANITA_TRAPDOOR_BLOCK_TAG, List.of(BlockTags.WOODEN_TRAPDOORS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_WALL_ITEM_TAG, List.of(UpgradedVanillaTags.WOODEN_WALLS_ITEM_TAG, ItemTags.WALLS));
		TagHelper.addBlockTags(AMARANITA_WALL_BLOCK_TAG, List.of(UpgradedVanillaTags.WOODEN_WALLS_BLOCK_TAG, BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_LADDER_ITEM_TAG, List.of(UpgradedVanillaTags.WOODEN_LADDER_ITEM_TAG));
		TagHelper.addBlockTags(AMARANITA_LADDER_BLOCK_TAG, List.of(UpgradedVanillaTags.WOODEN_LADDER_BLOCK_TAG, BlockTags.CLIMBABLE, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_BARREL_ITEM_TAG, List.of(UVCommonItemTags.BARREL));
		TagHelper.addBlockTags(AMARANITA_BARREL_BLOCK_TAG, List.of(UVCommonBlockTags.BARREL, UVCommonBlockTags.WOODEN_BARRELS, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_TORCH_ITEM_TAG, List.of(UpgradedVanillaTags.TORCH_ITEM_TAG));
		TagHelper.addBlockTags(AMARANITA_TORCH_BLOCK_TAG, List.of(UpgradedVanillaTags.TORCH_BLOCK_TAG));
		TagHelper.addItemTags(AMARANITA_SOUL_TORCH_ITEM_TAG, List.of(UpgradedVanillaTags.SOUL_TORCH_ITEM_TAG));
		TagHelper.addBlockTags(AMARANITA_SOUL_TORCH_BLOCK_TAG, List.of(UpgradedVanillaTags.SOUL_TORCH_BLOCK_TAG));
		TagHelper.addItemTags(AMARANITA_REDSTONE_TORCH_ITEM_TAG, List.of(UpgradedVanillaTags.REDSTONE_TORCH_ITEM_TAG));
		TagHelper.addBlockTags(AMARANITA_REDSTONE_TORCH_BLOCK_TAG, List.of(UpgradedVanillaTags.REDSTONE_TORCH_BLOCK_TAG));
		TagHelper.addItemTags(AMARANITA_WORKBENCH_ITEM_TAG, List.of(UVCommonItemTags.WORKBENCH));
		TagHelper.addBlockTags(AMARANITA_WORKBENCH_BLOCK_TAG, List.of(UVCommonBlockTags.WORKBENCH, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_BOOKSHELF_ITEM_TAG, List.of(UVCommonItemTags.BOOKSHELVES));
		TagHelper.addBlockTags(AMARANITA_BOOKSHELF_BLOCK_TAG, List.of(UVCommonBlockTags.BOOKSHELVES, BlockTags.MINEABLE_WITH_AXE));
		TagHelper.addItemTags(AMARANITA_LECTERN_ITEM_TAG, List.of());
		TagHelper.addBlockTags(AMARANITA_LECTERN_BLOCK_TAG, List.of(BlockTags.MINEABLE_WITH_AXE));
	}
	public static void registerStrippableLists() {
		logBlocks.add(EndBlocks.AMARANITA_STEM);
		woodBlocks.add(EndBlocks.AMARANITA_HYPHAE);
	}
	public static TagKey<Block> AMARANITA_STRIPPED_STEMS_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_STRIPPED_STEMS_ITEM_TAG;
	public static Block STRIPPED_AMARANITA_STEM;
	public static TagKey<Block> AMARANITA_STRIPPED_HYPHAE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_STRIPPED_HYPHAE_ITEM_TAG;
	public static TagKey<Block> AMARANITA_HYPHAE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_HYPHAE_ITEM_TAG;
	public static TagKey<Block> AMARANITA_STEMS_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_STEMS_ITEM_TAG;
	public static Block STRIPPED_AMARANITA_HYPHAE;
	public static TagKey<Block> AMARANITA_PLANKS_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_PLANKS_ITEM_TAG;
	public static Block AMARANITA_PLANKS;
	public static TagKey<Block> AMARANITA_STAIRS_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_STAIRS_ITEM_TAG;
	public static Block AMARANITA_STAIRS;
	public static TagKey<Block> AMARANITA_SLAB_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_SLAB_ITEM_TAG;
	public static Block AMARANITA_SLAB;
	public static Item AMARANITA_STICK;
	public static TagKey<Block> AMARANITA_FENCE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_FENCE_ITEM_TAG;
	public static Block AMARANITA_FENCE;
	public static TagKey<Block> AMARANITA_FENCE_GATE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_FENCE_GATE_ITEM_TAG;
	public static Block AMARANITA_FENCE_GATE;
	public static TagKey<Block> AMARANITA_BUTTON_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_BUTTON_ITEM_TAG;
	public static Block AMARANITA_BUTTON;
	public static TagKey<Block> AMARANITA_PRESSURE_PLATE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_PRESSURE_PLATE_ITEM_TAG;
	public static Block AMARANITA_PRESSURE_PLATE;
	public static TagKey<Block> AMARANITA_DOOR_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_DOOR_ITEM_TAG;
	public static Block AMARANITA_DOOR;
	public static TagKey<Block> AMARANITA_TRAPDOOR_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_TRAPDOOR_ITEM_TAG;
	public static Block AMARANITA_TRAPDOOR;
	public static TagKey<Block> AMARANITA_WALL_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_WALL_ITEM_TAG;
	public static Block AMARANITA_WALL;
	public static TagKey<Block> AMARANITA_LADDER_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_LADDER_ITEM_TAG;
	public static Block AMARANITA_LADDER;
	public static Block AMARANITA_CHEST;
	public static Block AMARANITA_STANDING_SIGN;
	public static Block AMARANITA_WALL_SIGN;
	public static Item AMARANITA_SIGN_ITEM;
	public static Item AMARANITA_BOAT;
	public static Item AMARANITA_CHEST_BOAT;
	public static BoatTypeOverride AMARANITA_BOAT_TYPE;
	public static TagKey<Block> AMARANITA_BARREL_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_BARREL_ITEM_TAG;
	public static Block AMARANITA_BARREL;
	public static TagKey<Block> AMARANITA_TORCH_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_TORCH_ITEM_TAG;
	public static Block AMARANITA_WALL_TORCH;
	public static Block AMARANITA_STANDING_TORCH;
	public static Item AMARANITA_TORCH_ITEM;
	public static TagKey<Block> AMARANITA_SOUL_TORCH_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_SOUL_TORCH_ITEM_TAG;
	public static Block AMARANITA_WALL_SOUL_TORCH;
	public static Block AMARANITA_STANDING_SOUL_TORCH;
	public static Item AMARANITA_SOUL_TORCH_ITEM;
	public static TagKey<Block> AMARANITA_REDSTONE_TORCH_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_REDSTONE_TORCH_ITEM_TAG;
	public static Block AMARANITA_WALL_REDSTONE_TORCH;
	public static Block AMARANITA_STANDING_REDSTONE_TORCH;
	public static Item AMARANITA_REDSTONE_TORCH_ITEM;
	public static TagKey<Block> AMARANITA_WORKBENCH_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_WORKBENCH_ITEM_TAG;
	public static Block AMARANITA_WORKBENCH;
	public static TagKey<Block> AMARANITA_CAMPFIRE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_CAMPFIRE_ITEM_TAG;
	public static Block AMARANITA_CAMPFIRE;
	public static TagKey<Block> AMARANITA_SOUL_CAMPFIRE_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_SOUL_CAMPFIRE_ITEM_TAG;
	public static Block AMARANITA_SOUL_CAMPFIRE;
	public static TagKey<Block> AMARANITA_BOOKSHELF_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_BOOKSHELF_ITEM_TAG;
	public static Block AMARANITA_BOOKSHELF;
	public static TagKey<Block> AMARANITA_LECTERN_BLOCK_TAG;
	public static TagKey<Item> AMARANITA_LECTERN_ITEM_TAG;
	public static Block AMARANITA_LECTERN;
	public static Block AMARANITA_RAIL;
	public static Block AMARANITA_POWERED_RAIL;
	public static Block AMARANITA_ACTIVATOR_RAIL;
	public static Block AMARANITA_DETECTOR_RAIL;
	public static Block AMARANITA_BEEHIVE;
	public static Block AMARANITA_COMPOSTER;
	public static Block AMARANITA_CARTOGRAPHY_TABLE;
	public static Block AMARANITA_FLETCHING_TABLE;
	public static Block AMARANITA_SMITHING_TABLE;
	public static Block AMARANITA_STEM_STAIRS;
	public static Block AMARANITA_STEM_SLAB;
	public static Block AMARANITA_STEM_WALL;
	public static Block AMARANITA_HYPHAE_STAIRS;
	public static Block AMARANITA_HYPHAE_SLAB;
	public static Block AMARANITA_HYPHAE_WALL;
	public static Block STRIPPED_AMARANITA_STEM_STAIRS;
	public static Block STRIPPED_AMARANITA_STEM_SLAB;
	public static Block STRIPPED_AMARANITA_STEM_WALL;
	public static Block STRIPPED_AMARANITA_HYPHAE_STAIRS;
	public static Block STRIPPED_AMARANITA_HYPHAE_SLAB;
	public static Block STRIPPED_AMARANITA_HYPHAE_WALL;

	public static void register(){
		registerStrippableLists();
		amaranitaPalette = new File("C:\\Users\\Stijn\\Desktop\\Upgraded Vanilla project\\template data\\wood\\Palletes\\AmaranitaPallete.png");
		AMARANITA_STRIPPED_STEMS_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,"stripped_"+MATERIAL_NAME+"_stems"));
		AMARANITA_STRIPPED_STEMS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,"stripped_"+MATERIAL_NAME+"_stems"));
		STRIPPED_AMARANITA_STEM = MinecraftWoodBlocks.strippedStemBlock(MATERIAL_NAME, logBlocks, List.of(AMARANITA_STRIPPED_STEMS_BLOCK_TAG, UVCommonBlockTags.STRIPPED_LOGS),
				List.of(AMARANITA_STRIPPED_STEMS_ITEM_TAG));
		AMARANITA_STRIPPED_HYPHAE_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,"stripped_"+MATERIAL_NAME+"_hyphae"));
		AMARANITA_STRIPPED_HYPHAE_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID, "stripped_"+MATERIAL_NAME+"_hyphae"));
		AMARANITA_HYPHAE_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithBlocks(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_hyphae"),
				List.of(new ResourceLocation("betterend","amaranita_hyphae")));
		AMARANITA_HYPHAE_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_hyphae"),
				List.of(new ResourceLocation("betterend","amaranita_hyphae")));
		AMARANITA_STEMS_BLOCK_TAG = TagKeyHelper.createBlockTagKeyWithCompositeTagsAndBlocks(
				new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_stems"), List.of(AMARANITA_STRIPPED_STEMS_BLOCK_TAG,AMARANITA_HYPHAE_BLOCK_TAG,AMARANITA_STRIPPED_HYPHAE_BLOCK_TAG),
				List.of(new ResourceLocation("betterend","amaranita_stem")));
		AMARANITA_STEMS_ITEM_TAG = TagKeyHelper.createItemTagKeyWithCompositeTagsAndItems(
				new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_stems"), List.of(AMARANITA_STRIPPED_STEMS_ITEM_TAG,AMARANITA_HYPHAE_ITEM_TAG,AMARANITA_STRIPPED_HYPHAE_ITEM_TAG),
				List.of(new ResourceLocation("betterend","amaranita_stem")));
		STRIPPED_AMARANITA_HYPHAE = MinecraftWoodBlocks.strippedHyphaeBlock(MATERIAL_NAME, woodBlocks, AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of(AMARANITA_STRIPPED_HYPHAE_BLOCK_TAG, UVCommonBlockTags.STRIPPED_WOOD),
				List.of(AMARANITA_STRIPPED_HYPHAE_ITEM_TAG));

		AMARANITA_PLANKS_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_planks"));
		AMARANITA_PLANKS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_planks"));
		AMARANITA_PLANKS = MinecraftWoodBlocks.endFungusPlankBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM, STRIPPED_AMARANITA_STEM,
				EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE,
				AMARANITA_STEMS_ITEM_TAG, MaterialColor.COLOR_LIGHT_BLUE,
				List.of(AMARANITA_PLANKS_BLOCK_TAG),List.of(AMARANITA_PLANKS_ITEM_TAG), amaranitaPalette);
		AMARANITA_STAIRS_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_stairs"));
		AMARANITA_STAIRS_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_stairs"));
		AMARANITA_STAIRS = MinecraftWoodBlocks.plankStairsBlock(MATERIAL_NAME,AMARANITA_PLANKS,AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_STAIRS_BLOCK_TAG),List.of(AMARANITA_STAIRS_ITEM_TAG));
		AMARANITA_SLAB_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_slabs"));
		AMARANITA_SLAB_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_slabs"));
		AMARANITA_SLAB = MinecraftWoodBlocks.plankSlabBlock(MATERIAL_NAME,AMARANITA_PLANKS,AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_SLAB_BLOCK_TAG),List.of(AMARANITA_SLAB_ITEM_TAG));
		AMARANITA_STICK = MinecraftItems.stick(MATERIAL_NAME,AmaranitaBlocks.AMARANITA_PLANKS_ITEM_TAG,
				List.of(UpgradedVanillaTags.STICKS), amaranitaPalette);
		AMARANITA_FENCE_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_fences"));
		AMARANITA_FENCE_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_fences"));
		AMARANITA_FENCE = MinecraftWoodBlocks.plankFenceBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_STICK,AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_FENCE_BLOCK_TAG),List.of(AMARANITA_FENCE_ITEM_TAG));
		AMARANITA_FENCE_GATE_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_fence_gates"));
		AMARANITA_FENCE_GATE_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_fence_gates"));
		AMARANITA_FENCE_GATE = MinecraftWoodBlocks.plankFenceGateBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_STICK,AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_FENCE_GATE_BLOCK_TAG),List.of(AMARANITA_FENCE_GATE_ITEM_TAG));
		AMARANITA_BUTTON_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_buttons"));
		AMARANITA_BUTTON_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_buttons"));
		AMARANITA_BUTTON = MinecraftWoodBlocks.plankButtonBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_BUTTON_BLOCK_TAG),List.of(AMARANITA_BUTTON_ITEM_TAG));
		AMARANITA_PRESSURE_PLATE_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_pressure_plates"));
		AMARANITA_PRESSURE_PLATE_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_pressure_plates"));
		AMARANITA_PRESSURE_PLATE = MinecraftWoodBlocks.plankPressurePlateBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_PRESSURE_PLATE_BLOCK_TAG),List.of(AMARANITA_PRESSURE_PLATE_ITEM_TAG));
		AMARANITA_DOOR_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_doors"));
		AMARANITA_DOOR_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_doors"));
		AMARANITA_DOOR = MinecraftWoodBlocks.plankDoorBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_DOOR_BLOCK_TAG),List.of(AMARANITA_DOOR_ITEM_TAG));
		AMARANITA_TRAPDOOR_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_trapdoors"));
		AMARANITA_TRAPDOOR_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_trapdoors"));
		AMARANITA_TRAPDOOR = MinecraftWoodBlocks.plankTrapdoorBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_TRAPDOOR_BLOCK_TAG),List.of(AMARANITA_TRAPDOOR_ITEM_TAG));
		AMARANITA_WALL_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_walls"));
		AMARANITA_WALL_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_walls"));
		AMARANITA_WALL = MinecraftWoodBlocks.plankWallBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_SLAB_ITEM_TAG,
				List.of(AMARANITA_WALL_BLOCK_TAG),List.of(AMARANITA_WALL_ITEM_TAG));
		AMARANITA_LADDER_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_ladders"));
		AMARANITA_LADDER_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_ladders"));
		AMARANITA_LADDER = MinecraftWoodBlocks.ladderBlock(MATERIAL_NAME, AMARANITA_STICK,
				List.of(AMARANITA_LADDER_BLOCK_TAG),List.of(AMARANITA_LADDER_ITEM_TAG), amaranitaPalette);
		AMARANITA_STANDING_SIGN = MinecraftWoodBlocks.standingSignBlock(MATERIAL_NAME,AMARANITA, AMARANITA_PLANKS,
				List.of(BlockTags.STANDING_SIGNS), amaranitaPalette);
		AMARANITA_WALL_SIGN = MinecraftWoodBlocks.wallSignBlock(MATERIAL_NAME,AMARANITA, AMARANITA_PLANKS,
				List.of(BlockTags.WALL_SIGNS));
		AMARANITA_SIGN_ITEM = MinecraftItems.signItem(MATERIAL_NAME,AMARANITA_STICK , AMARANITA_PLANKS, AMARANITA_STANDING_SIGN,
				AMARANITA_WALL_SIGN, AMARANITA_PLANKS_ITEM_TAG, List.of(ItemTags.SIGNS), amaranitaPalette);
		AMARANITA_BOAT_TYPE = BoatTypeOverride.create(UpgradedVanilla.ID, MATERIAL_NAME, AMARANITA_PLANKS);
		AMARANITA_BOAT = MinecraftItems.boatItem(MATERIAL_NAME, AMARANITA_BOAT_TYPE,AMARANITA_PLANKS_ITEM_TAG,
				List.of(), amaranitaPalette);
		AMARANITA_CHEST = MinecraftWoodBlocks.chestBlock(MATERIAL_NAME, AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG,
				List.of(UVCommonBlockTags.WOODEN_CHESTS), List.of(UVCommonItemTags.WOODEN_CHESTS), amaranitaPalette);
		AMARANITA_CHEST_BOAT = MinecraftItems.chestBoatItem(MATERIAL_NAME, AMARANITA_BOAT_TYPE, AMARANITA_BOAT,
				UVCommonItemTags.WOODEN_CHESTS, List.of(), amaranitaPalette);
		STRIPPED_AMARANITA_STEM_STAIRS = MinecraftWoodBlocks.strippedLogStairsBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM,STRIPPED_AMARANITA_STEM,
				AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of());
		AMARANITA_STEM_STAIRS = MinecraftWoodBlocks.endStemStairsBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_STEM_STAIRS,
				AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of());
		STRIPPED_AMARANITA_STEM_SLAB = MinecraftWoodBlocks.strippedLogSlabBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM, STRIPPED_AMARANITA_STEM,
				AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of());
		AMARANITA_STEM_SLAB = MinecraftWoodBlocks.endStemSlabBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_STEM_STAIRS,
				AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of( BlockTags.MINEABLE_WITH_AXE),
				List.of());
		STRIPPED_AMARANITA_STEM_WALL = MinecraftWoodBlocks.strippedLogWallBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM, STRIPPED_AMARANITA_STEM,
				AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE),
				List.of());
		AMARANITA_STEM_WALL = MinecraftWoodBlocks.endStemWallBlock(MATERIAL_NAME, EndBlocks.AMARANITA_STEM, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_STEM_STAIRS,
				AMARANITA_STRIPPED_STEMS_ITEM_TAG, List.of(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE),
				List.of());
		STRIPPED_AMARANITA_HYPHAE_STAIRS = MinecraftWoodBlocks.strippedLogStairsBlock(MATERIAL_NAME, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE,
				AMARANITA_STRIPPED_HYPHAE_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of(AMARANITA_STRIPPED_HYPHAE_ITEM_TAG));
		AMARANITA_HYPHAE_STAIRS = MinecraftWoodBlocks.endHyphaeStairsBlock(MATERIAL_NAME, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE_STAIRS,
				AMARANITA_STRIPPED_HYPHAE_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of());
		STRIPPED_AMARANITA_HYPHAE_SLAB = MinecraftWoodBlocks.strippedLogSlabBlock(MATERIAL_NAME, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE,
				AMARANITA_STRIPPED_HYPHAE_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of());
		AMARANITA_HYPHAE_SLAB = MinecraftWoodBlocks.endHyphaeSlabBlock(MATERIAL_NAME, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE_STAIRS,
				AMARANITA_STRIPPED_HYPHAE_ITEM_TAG, List.of(BlockTags.MINEABLE_WITH_AXE),
				List.of());
		STRIPPED_AMARANITA_HYPHAE_WALL = MinecraftWoodBlocks.strippedLogWallBlock(MATERIAL_NAME, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE,
				AMARANITA_STRIPPED_HYPHAE_ITEM_TAG, List.of(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE),
				List.of());
		AMARANITA_HYPHAE_WALL = MinecraftWoodBlocks.endHyphaeWallBlock(MATERIAL_NAME, EndBlocks.AMARANITA_HYPHAE, STRIPPED_AMARANITA_HYPHAE_STAIRS,
				AMARANITA_STRIPPED_HYPHAE_ITEM_TAG, List.of(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE),
				List.of());
		AMARANITA_BARREL_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_barrels"));
		AMARANITA_BARREL_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_barrels"));
		AMARANITA_BARREL = MinecraftWoodBlocks.barrelBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG, AMARANITA_SLAB_ITEM_TAG,
				List.of(AMARANITA_BARREL_BLOCK_TAG,CommonPoiTags.FISHERMAN_WORKSTATION),List.of(AMARANITA_BARREL_ITEM_TAG), amaranitaPalette);
		AMARANITA_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_torches"));
		AMARANITA_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_torches"));
		AMARANITA_WALL_TORCH = MinecraftWoodBlocks.wallTorchBlock(MATERIAL_NAME, List.of(AMARANITA_TORCH_BLOCK_TAG));
		AMARANITA_STANDING_TORCH = MinecraftWoodBlocks.torchBlock(MATERIAL_NAME, List.of(AMARANITA_TORCH_BLOCK_TAG));
		AMARANITA_TORCH_ITEM = MinecraftItems.torchItem(MATERIAL_NAME,AMARANITA_STANDING_TORCH,AMARANITA_WALL_TORCH,AMARANITA_STICK,
				List.of(AMARANITA_TORCH_ITEM_TAG),amaranitaPalette);
		AMARANITA_SOUL_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_soul_torches"));
		AMARANITA_SOUL_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_soul_torches"));
		AMARANITA_WALL_SOUL_TORCH = MinecraftWoodBlocks.wallSoulTorchBlock(MATERIAL_NAME, List.of(AMARANITA_SOUL_TORCH_BLOCK_TAG));
		AMARANITA_STANDING_SOUL_TORCH = MinecraftWoodBlocks.soulTorchBlock(MATERIAL_NAME, List.of(AMARANITA_SOUL_TORCH_BLOCK_TAG));
		AMARANITA_SOUL_TORCH_ITEM = MinecraftItems.soulTorchItem(MATERIAL_NAME, AMARANITA_TORCH_ITEM,AMARANITA_STANDING_SOUL_TORCH,AMARANITA_WALL_SOUL_TORCH,AMARANITA_STICK,
				List.of(AMARANITA_SOUL_TORCH_ITEM_TAG),amaranitaPalette);
		AMARANITA_REDSTONE_TORCH_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_redstone_torches"));
		AMARANITA_REDSTONE_TORCH_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_redstone_torches"));
		AMARANITA_WALL_REDSTONE_TORCH = MinecraftWoodBlocks.wallRedstoneTorchBlock(MATERIAL_NAME, List.of(AMARANITA_REDSTONE_TORCH_BLOCK_TAG));
		AMARANITA_STANDING_REDSTONE_TORCH = MinecraftWoodBlocks.redstoneTorchBlock(MATERIAL_NAME, List.of(AMARANITA_REDSTONE_TORCH_BLOCK_TAG));
		AMARANITA_REDSTONE_TORCH_ITEM = MinecraftItems.redstoneTorchItem(MATERIAL_NAME,AMARANITA_STANDING_REDSTONE_TORCH,AMARANITA_WALL_REDSTONE_TORCH,AMARANITA_STICK,
				List.of(AMARANITA_REDSTONE_TORCH_ITEM_TAG),amaranitaPalette);
		AMARANITA_WORKBENCH_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_workbenches"));
		AMARANITA_WORKBENCH_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_workbenches"));
		AMARANITA_WORKBENCH = MinecraftWoodBlocks.craftingTableBlock(MATERIAL_NAME,AMARANITA_PLANKS,
				List.of(AMARANITA_WORKBENCH_BLOCK_TAG),List.of(AMARANITA_WORKBENCH_ITEM_TAG), amaranitaPalette);
		AMARANITA_CAMPFIRE_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_campfires"));
		AMARANITA_CAMPFIRE_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_campfires"));
		AMARANITA_CAMPFIRE = MinecraftWoodBlocks.campfireBlock(MATERIAL_NAME,AMARANITA_STEMS_ITEM_TAG,AMARANITA_STICK,
				List.of(BlockTags.CAMPFIRES),List.of(UpgradedVanillaTags.CAMPFIRE_ITEM_TAG), amaranitaPalette);
		AMARANITA_SOUL_CAMPFIRE_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_soul_campfires"));
		AMARANITA_SOUL_CAMPFIRE_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_soul_campfires"));
		AMARANITA_SOUL_CAMPFIRE = MinecraftWoodBlocks.soulCampfireBlock(MATERIAL_NAME, AMARANITA_CAMPFIRE, AMARANITA_STEMS_ITEM_TAG,AMARANITA_STICK,
				List.of(BlockTags.CAMPFIRES),List.of(UpgradedVanillaTags.CAMPFIRE_ITEM_TAG), amaranitaPalette);
		AMARANITA_BOOKSHELF_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_bookshelves"));
		AMARANITA_BOOKSHELF_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_bookshelves"));
		AMARANITA_BOOKSHELF = MinecraftWoodBlocks.bookshelfBlock(MATERIAL_NAME,AMARANITA_PLANKS, AMARANITA_PLANKS_ITEM_TAG,
				List.of(AMARANITA_BOOKSHELF_BLOCK_TAG),List.of(AMARANITA_BOOKSHELF_ITEM_TAG), amaranitaPalette);
		AMARANITA_LECTERN_BLOCK_TAG = TagKeyHelper.createBlockTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_lecterns"));
		AMARANITA_LECTERN_ITEM_TAG = TagKeyHelper.createItemTagKey(new ResourceLocation(UpgradedVanilla.ID,MATERIAL_NAME+"_lecterns"));
		AMARANITA_LECTERN = MinecraftWoodBlocks.lecternBlock(MATERIAL_NAME, AMARANITA_PLANKS,AMARANITA_SLAB, UpgradedVanillaTags.BOOKSHELVES_ITEM_TAG, AMARANITA_SLAB_ITEM_TAG,
				List.of(PoiTags.LIBRARIAN_WORKSTATION, AMARANITA_LECTERN_BLOCK_TAG),List.of(AMARANITA_LECTERN_ITEM_TAG), amaranitaPalette);
		AMARANITA_RAIL = MinecraftWoodBlocks.railBlock(MATERIAL_NAME, AMARANITA_STICK,
				List.of(BlockTags.RAILS, BlockTags.MINEABLE_WITH_PICKAXE),List.of(ItemTags.RAILS), amaranitaPalette);
		AMARANITA_POWERED_RAIL = MinecraftWoodBlocks.poweredRailBlock(MATERIAL_NAME, AMARANITA_STICK,
				List.of(BlockTags.RAILS, BlockTags.MINEABLE_WITH_PICKAXE),List.of(ItemTags.RAILS), amaranitaPalette);
		AMARANITA_ACTIVATOR_RAIL = MinecraftWoodBlocks.activatorRailBlock(MATERIAL_NAME, AMARANITA_STICK,
				List.of(BlockTags.RAILS, BlockTags.MINEABLE_WITH_PICKAXE),List.of(ItemTags.RAILS), amaranitaPalette);
		AMARANITA_DETECTOR_RAIL = MinecraftWoodBlocks.detectorRailBlock(MATERIAL_NAME, AMARANITA_STICK,
				List.of(BlockTags.RAILS, BlockTags.MINEABLE_WITH_PICKAXE),List.of(ItemTags.RAILS), amaranitaPalette);
		AMARANITA_BEEHIVE = MinecraftWoodBlocks.beehiveBlock(MATERIAL_NAME, AMARANITA_PLANKS_ITEM_TAG,
				List.of(BlockTags.BEEHIVES, BlockTags.MINEABLE_WITH_AXE),List.of(), amaranitaPalette);
		AMARANITA_COMPOSTER = MinecraftWoodBlocks.composterBlock(MATERIAL_NAME, AMARANITA_PLANKS,AMARANITA_SLAB_ITEM_TAG,
				List.of(CommonPoiTags.FARMER_WORKSTATION, BlockTags.MINEABLE_WITH_AXE),List.of(), amaranitaPalette);
		AMARANITA_CARTOGRAPHY_TABLE = MinecraftWoodBlocks.cartographyTableBlock(MATERIAL_NAME, AMARANITA_PLANKS,AMARANITA_SLAB_ITEM_TAG,
				List.of(PoiTags.CARTOGRAPHER_WORKSTATION, BlockTags.MINEABLE_WITH_AXE),List.of(), amaranitaPalette);
		AMARANITA_FLETCHING_TABLE = MinecraftWoodBlocks.fletchingTableBlock(MATERIAL_NAME, AMARANITA_PLANKS,AMARANITA_SLAB_ITEM_TAG,
				List.of(PoiTags.FLETCHER_WORKSTATION, BlockTags.MINEABLE_WITH_AXE),List.of(), amaranitaPalette);
		AMARANITA_SMITHING_TABLE = MinecraftWoodBlocks.smithingTableBlock(MATERIAL_NAME, AMARANITA_PLANKS,AMARANITA_SLAB_ITEM_TAG,
				List.of(PoiTags.ARMORER_WORKSTATION, BlockTags.MINEABLE_WITH_AXE),List.of(), amaranitaPalette);

		//LAST
		registerTags();
	}
}
