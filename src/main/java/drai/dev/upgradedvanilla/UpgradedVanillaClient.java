package drai.dev.upgradedvanilla;

import drai.dev.upgradedvanilla.blockentities.renderers.*;
import drai.dev.upgradedvanilla.registry.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.model.geom.*;

public class UpgradedVanillaClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BaseBlockEntityRenders.register();
	}
}
