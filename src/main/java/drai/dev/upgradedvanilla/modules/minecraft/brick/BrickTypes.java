package drai.dev.upgradedvanilla.modules.minecraft.brick;

import drai.dev.upgradedvanilla.modules.minecraft.wool.*;

public class BrickTypes {
	public static void register(){
		Brick.register();
		registerColored();
	}

	private static void registerColored() {
		OrangeBrick.register();
		MagentaBrick.register();
		LimeBrick.register();
		PinkBrick.register();
		GrayBrick.register();
		LightGrayBrick.register();
		CyanBrick.register();
		PurpleBrick.register();
		BrownBrick.register();
		BlueBrick.register();
		GreenBrick.register();
	}
}
