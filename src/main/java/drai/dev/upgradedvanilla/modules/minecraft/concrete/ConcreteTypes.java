package drai.dev.upgradedvanilla.modules.minecraft.concrete;

public class ConcreteTypes {
	public static void register(){
		registerPowders();
		RedConcrete.register();
	}

	private static void registerPowders() {
		RedConcretePowder.register();
	}
}
