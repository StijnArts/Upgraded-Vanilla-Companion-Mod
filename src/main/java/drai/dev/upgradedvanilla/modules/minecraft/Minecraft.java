package drai.dev.upgradedvanilla.modules.minecraft;

import drai.dev.upgradedvanilla.modules.minecraft.brick.*;
import drai.dev.upgradedvanilla.modules.minecraft.concrete.*;
import drai.dev.upgradedvanilla.modules.minecraft.glass.*;
import drai.dev.upgradedvanilla.modules.minecraft.metal.*;
import drai.dev.upgradedvanilla.modules.minecraft.quartz.*;
import drai.dev.upgradedvanilla.modules.minecraft.resourceblocks.*;
import drai.dev.upgradedvanilla.modules.minecraft.sand.*;
import drai.dev.upgradedvanilla.modules.minecraft.soil.*;
import drai.dev.upgradedvanilla.modules.minecraft.stone.*;
import drai.dev.upgradedvanilla.modules.minecraft.terracotta.*;
import drai.dev.upgradedvanilla.modules.minecraft.wood.*;
import drai.dev.upgradedvanilla.modules.minecraft.wool.*;
import drai.dev.upgradedvanilla.registry.*;
import drai.dev.upgradedvanilla.tag.*;

public class Minecraft {
	public static void register(){
		UpgradedVanillaTags.register();
		StoneTypes.register();
		WoolTypes.register();
		SoilTypes.register();
		WoodTypes.register();
		Hay.register();
		Sand.register();
		ResourceTypes.register();
		ConcreteTypes.register();
		TerracottaTypes.register();
		GlassTypes.register();
		QuartzTypes.register();
		BrickTypes.register();
		MetalTypes.register();
		CombinationRegistry.getSourceMod("minecraft").setInitialized();
		CombinationRegistry.registerCombinationBlocks();
	}
}
