package drai.dev.upgradedvanilla.registry;

import net.bunten.enderscape.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.state.properties.*;

public class UpgradedVanillaSignType extends WoodType {
	public UpgradedVanillaSignType(String name) {
		super(name);
	}

	public ResourceLocation getTexturePath() {
		return new ResourceLocation("minecraft","entity/signs/" + this.name());
	}
}
