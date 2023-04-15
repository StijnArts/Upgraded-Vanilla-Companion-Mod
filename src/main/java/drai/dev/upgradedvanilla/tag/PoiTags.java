package drai.dev.upgradedvanilla.tag;

import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;

import org.betterx.worlds.together.tag.v3.TagManager;

public class PoiTags {
	public static final TagKey<Block> LIBRARIAN_WORKSTATION;
	public static final TagKey<Block> WEAPONSMITH_WORKSTATION;
	public static final TagKey<Block> CARTOGRAPHER_WORKSTATION;
	public static final TagKey<Block> FLETCHER_WORKSTATION;
	public static final TagKey<Block> BUTCHER_WORKSTATION;
	public static final TagKey<Block> MASON_WORKSTATION;
	public static final TagKey<Block> ARMORER_WORKSTATION;

	public static void register() {
	}

	static {
		LIBRARIAN_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/librarian");
		TagManager.BLOCKS.add(LIBRARIAN_WORKSTATION,Blocks.LECTERN);
		WEAPONSMITH_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/weaponsmith");
		TagManager.BLOCKS.add(WEAPONSMITH_WORKSTATION,Blocks.GRINDSTONE);
		CARTOGRAPHER_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/cartorgrapher");
		TagManager.BLOCKS.add(WEAPONSMITH_WORKSTATION,Blocks.CARTOGRAPHY_TABLE);
		FLETCHER_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/fletcher");
		TagManager.BLOCKS.add(WEAPONSMITH_WORKSTATION,Blocks.FLETCHING_TABLE);
		BUTCHER_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/butcher");
		TagManager.BLOCKS.add(BUTCHER_WORKSTATION,Blocks.SMOKER);
		MASON_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/mason");
		TagManager.BLOCKS.add(MASON_WORKSTATION,Blocks.STONECUTTER);
		ARMORER_WORKSTATION = TagManager.BLOCKS.makeCommonTag("workstation/armorer");
		TagManager.BLOCKS.add(ARMORER_WORKSTATION,Blocks.SMITHING_TABLE);
	}
}
