package drai.dev.upgradedvanilla.helpers;

import com.google.common.collect.*;
import com.ibm.icu.impl.*;

import drai.dev.upgradedvanilla.datageneration.providers.loottables.*;
import net.minecraft.data.models.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;

import java.util.*;
import java.util.function.*;

public class BlockLootHelper {
	private static LinkedHashMultimap<Block, BiConsumer<BlockLootProvider, Block>> blockLoot = LinkedHashMultimap.create();

	public static LinkedHashMultimap<Block, BiConsumer<BlockLootProvider, Block>> getBlockLoot() {
		return blockLoot;
	}

	public static void addBlockLoot(Block block, BiConsumer<BlockLootProvider, Block> consumer){
		blockLoot.put(block, consumer);
	}
}
