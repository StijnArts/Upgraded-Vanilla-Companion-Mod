package drai.dev.upgradedvanilla.model;

import net.minecraft.data.models.model.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.state.properties.*;

import java.util.*;

public class UpgradedVanillaModelTemplates {
	public static TextureSlot BASE = TextureSlot.create("base");
	public static TextureSlot LEVER = TextureSlot.create("lever");
	public static TextureSlot LOG = TextureSlot.create("log");
	public static TextureSlot SIDES = TextureSlot.create("sides");
	public static TextureSlot WOOD = TextureSlot.create("wood");
	public static TextureSlot PIVOT = TextureSlot.create("pivot");
	public static TextureSlot ROUND = TextureSlot.create("round");
	public static TextureSlot LEG = TextureSlot.create("leg");
	public static TextureSlot HOOK = TextureSlot.create("hook");
	public static final BooleanProperty MAP = BooleanProperty.create("map");
	public static final ModelTemplate LOG_WALL_POST = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/template_log_wall_post")),
			Optional.of("_post"),TextureSlot.SIDE,TextureSlot.END);
	public static final ModelTemplate LOG_WALL_SIDE = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/template_log_wall_side")),
			Optional.of("_side"),TextureSlot.SIDE);
	public static final ModelTemplate LOG_WALL_SIDE_TALL = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/template_log_wall_side_tall")),
			Optional.of("_side_tall"),TextureSlot.SIDE);
	public static final ModelTemplate LOG_WALL_INVENTORY = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/log_wall_inventory")),
			Optional.of("_inventory"),TextureSlot.SIDE);
	public static final ModelTemplate LADDER = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/ladder")),
			Optional.of(""),TextureSlot.ALL);
	public static final ModelTemplate LEVER_ON = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/lever_on")),
			Optional.of("_on"),TextureSlot.PARTICLE,BASE,LEVER);
	public static final ModelTemplate LEVER_OFF = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/lever")),
			Optional.of(""),TextureSlot.PARTICLE,BASE,LEVER);
	public static final ModelTemplate CAMPFIRE_OFF = new ModelTemplate(Optional.of(new ResourceLocation("upgradedvanilla","block/campfire_off")),
			Optional.of("_off"),TextureSlot.PARTICLE,LOG);
	public static final ModelTemplate CAMPFIRE_TEMPLATE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/template_campfire")),
			Optional.of("_template"),TextureSlot.PARTICLE,LOG);
	public static final ModelTemplate LECTERN = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/lectern")),
			Optional.of("_template"),TextureSlot.PARTICLE, TextureSlot.BOTTOM, BASE, TextureSlot.FRONT, SIDES, TextureSlot.TOP);
	public static final ModelTemplate GRINDSTONE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/grindstone")),
			Optional.of(""), PIVOT, ROUND,TextureSlot.SIDE, TextureSlot.PARTICLE,LEG);
	public static final ModelTemplate CRAFTING_TABLE_LIKE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/cartography_table")),
			Optional.of(""), TextureSlot.DOWN, TextureSlot.EAST, TextureSlot.NORTH, TextureSlot.PARTICLE, TextureSlot.SOUTH, TextureSlot.UP, TextureSlot.WEST);
	public static final ModelTemplate SMOKER = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/smoker")),
			Optional.of(""), TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.TOP);
	public static final ModelTemplate SMOKER_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/smoker_on")),
			Optional.of("_on"), TextureSlot.BOTTOM, TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.TOP);
	public static final ModelTemplate FURNACE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/furnace")),
			Optional.of(""), TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.TOP);
	public static final ModelTemplate FURNACE_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/furnace_on")),
			Optional.of("_on"), TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.TOP);
	public static final ModelTemplate TRIPWIRE_HOOK = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook")),
			Optional.of(""), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate TRIPWIRE_HOOK_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook_on")),
			Optional.of("_on"), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate TRIPWIRE_HOOK_ATTACHED = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook_attached")),
			Optional.of("_attached"), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate TRIPWIRE_HOOK_ATTACHED_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook_attached_on")),
			Optional.of("_attached_on"), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate STONECUTTER = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/stonecutter")),
			Optional.of(""), TextureSlot.PARTICLE, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.TOP);
	public static final ModelTemplate OBSERVER = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/observer")),
			Optional.of(""), TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
	public static final ModelTemplate OBSERVER_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/observer_on")),
			Optional.of("_on"), TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
}
