package drai.dev.upgradedvanilla.mixin;

import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.registry.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.ai.village.poi.*;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

import net.minecraft.world.level.block.state.properties.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import samebutdifferent.ecologics.mixin.fabric.*;

import java.util.*;

@Mixin(PoiTypes.class)
public interface PoiTypesAccessor {
	@Invoker("register")
	public static PoiType register(Registry<PoiType> key, ResourceKey<PoiType> value, Set<BlockState> matchingStates, int maxTickets, int validRange) {
		throw new AssertionError();
	}

	@Invoker("getBlockStates")
	public static Set<BlockState> getBlockStates(Block block){
		throw new AssertionError();
	}

}
