package drai.dev.upgradedvanilla.modules.minecraft.soil;
import drai.dev.upgradedvanilla.modules.minecraft.soil.grass.*;

public class SoilTypes {
	public static void register(){
		Dirt.register();
		Path.register();
		Grass.register();
		Podzol.register();
		Mycelium.register();
	}
}
