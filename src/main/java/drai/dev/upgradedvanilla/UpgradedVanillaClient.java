package drai.dev.upgradedvanilla;

import drai.dev.upgradedvanilla.blockentities.renderers.*;
import drai.dev.upgradedvanilla.registry.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.model.geom.*;

import java.util.*;

public class UpgradedVanillaClient implements ClientModInitializer {

	private static ArrayList<Runnable> operations = new ArrayList<>();

	@Override
	public void onInitializeClient() {
		BaseBlockEntityRenders.register();
		operations.forEach((operation)->operation.run());
	}

	public static void addClientSideOperation(Runnable operation){
		operations.add(operation);
	}

}
