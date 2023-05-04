package drai.dev.upgradedvanilla.modules.minecraft.glass;

public class GlassTypes {
	public static void register(){
		registerStainedGlass();
		Glass.register();

	}

	private static void registerStainedGlass(){
		drai.dev.upgradedvanilla.modules.minecraft.red_stained_glass.RedStainedGlass.register();
	}
}
