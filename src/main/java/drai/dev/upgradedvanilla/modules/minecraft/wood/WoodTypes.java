package drai.dev.upgradedvanilla.modules.minecraft.wood;

import drai.dev.upgradedvanilla.modules.minecraft.wood.oak.*;

public class WoodTypes {
	public static void register(){
		registerLeaves();
	}

	private static void registerLeaves(){
		OakLeaves.register();
	}
}
