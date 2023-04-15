package drai.dev.upgradedvanilla.helpers;

import com.google.common.collect.*;

import net.minecraft.data.models.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

import java.util.*;
import java.util.function.*;

public class BlockStateHelper {
	private static LinkedHashMultimap<ItemLike, BiConsumer<BlockModelGenerators, ItemLike>> blockModels = LinkedHashMultimap.create();

	public static void addBlockModel(ItemLike itemLike, BiConsumer<BlockModelGenerators, ItemLike> consumer){
		blockModels.put(itemLike, consumer);
	}

	public static LinkedHashMultimap<ItemLike, BiConsumer<BlockModelGenerators, ItemLike>> getBlockModels() {
		return blockModels;
	}
}
