package drai.dev.upgradedvanilla.registry;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.modules.betterend.amaranita.*;

import java.util.*;

public class CombinationRegistry {

	private static ArrayList<SourceMod> sourceMods = new ArrayList<>();
	private static boolean initialized = false;
	public static void registerCombinationBlocks(){
		if(allSourceModsHaveBeenLoaded()){
			AmaranitaCombinationBlocks.registerCombinationBlocks();
		}
	}

	private static void initializeSourceMods() {
		sourceMods.add(new SourceMod("minecraft"));
		sourceMods.add(new SourceMod("betterend"));
	}

	private static boolean allSourceModsHaveBeenLoaded() {
		if(sourceMods.stream().anyMatch(sourceMod -> {if(sourceMod.isInitialized()){return false;}return true;})){
			return false;
		}
		return true;
	}

	public static SourceMod getSourceMod(String id){
		if(!initialized){
			initializeSourceMods();
			initialized = true;
		}
		return sourceMods.stream().filter(sourceMod -> sourceMod.getId().equals(id)).findFirst().get();
	}
}
