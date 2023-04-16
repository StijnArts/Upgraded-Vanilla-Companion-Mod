package drai.dev.upgradedvanilla.modules.minecraft;

import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.*;
import drai.dev.upgradedvanilla.modules.minecraft.wool.*;
import drai.dev.upgradedvanilla.registry.*;
import drai.dev.upgradedvanilla.tag.*;

public class Minecraft {
	public static void register(){
		UpgradedVanillaTags.register();
		StoneTypes.register();
		WoolTypes.register();
		SoilTypes.register();
		CombinationRegistry.getSourceMod("minecraft").setInitialized();
		CombinationRegistry.registerCombinationBlocks();
	}
}
