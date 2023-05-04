package drai.dev.upgradedvanilla.modules.minecraft.soil.dirt;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.dirt.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;

public class RootedDirt {
	public static TagKey<Item> ROOTED_DIRT_BLOCK_ITEM_TAG;
	public static Block ROOTED_DIRT_SLAB;
	public static Block ROOTED_DIRT_STAIRS;
	public static Block ROOTED_DIRT_WALL;

	private static void registerTags(){
		ROOTED_DIRT_BLOCK_ITEM_TAG = TagKeyHelper.createItemTagKeyWithItems(new ResourceLocation(UpgradedVanilla.ID,"rooted_dirt_blocks"), List.of(new ResourceLocation("minecraft","rooted_dirt")));
	}
	public  static void register(){
		registerTags();
		ROOTED_DIRT_SLAB = MinecraftDirtBlocks.coarseDirtSlabBlock("rooted_dirt",Blocks.ROOTED_DIRT, ROOTED_DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		ROOTED_DIRT_STAIRS = MinecraftDirtBlocks.coarseDirtStairsBlock("rooted_dirt",Blocks.ROOTED_DIRT, ROOTED_DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, UpgradedVanillaTags.PODZOL_REPLACEABLE),List.of());
		ROOTED_DIRT_WALL = MinecraftDirtBlocks.coarseDirtWallBlock("rooted_dirt",Blocks.ROOTED_DIRT,ROOTED_DIRT_BLOCK_ITEM_TAG,
				List.of(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.WALLS),List.of());
	}
}
