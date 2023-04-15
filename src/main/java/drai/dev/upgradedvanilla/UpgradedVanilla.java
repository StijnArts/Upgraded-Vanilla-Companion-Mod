package drai.dev.upgradedvanilla;

import com.ibm.icu.impl.*;
import com.simibubi.create.Create;

import com.terraformersmc.modmenu.*;
import com.terraformersmc.modmenu.util.mod.*;

import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.registry.*;
import drai.dev.upgradedvanilla.tag.*;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.registry.*;
import net.fabricmc.fabric.api.registry.*;
import net.fabricmc.loader.api.*;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.block.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

import static com.terraformersmc.modmenu.ModMenu.MODS;

public class UpgradedVanilla implements ModInitializer {
	public static final String ID = "upgradedvanilla";
	public static final String NAME = "Upgraded Vanilla: Complete Edition";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
	@Override
	public void onInitialize() {
		UpgradedVanillaTags.register();
		BaseBlockEntities.register();
		BlockRegistry.RegisterBlocks();
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
