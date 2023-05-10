package drai.dev.upgradedvanilla.registry;

import drai.dev.upgradedvanilla.blocks.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class AnvilRegistry {
	private static HashMap<String, AnvilBlock> ANVILS = new HashMap<>();
	private static HashMap<String, AnvilBlock> CHIPPED_ANVILS = new HashMap<>();
	private static HashMap<String, AnvilBlock> DAMAGED_ANVILS = new HashMap<>();
	public static void putAnvils(String material, AnvilBlock anvil, AnvilBlock chipped, AnvilBlock damaged){
		ANVILS.put(material, anvil);
		CHIPPED_ANVILS.put(material, chipped);
		DAMAGED_ANVILS.put(material, damaged);
	}

	public static AnvilBlock getAnvil(String material){
		return(ANVILS.get(material));
	}
	public static AnvilBlock getChippedAnvil(String material){
		return(CHIPPED_ANVILS.get(material));
	}
	public static AnvilBlock getDamagedAnvil(String material){
		return(DAMAGED_ANVILS.get(material));
	}
}
