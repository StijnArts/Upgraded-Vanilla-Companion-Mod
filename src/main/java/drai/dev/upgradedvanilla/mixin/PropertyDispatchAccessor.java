package drai.dev.upgradedvanilla.mixin;

import com.google.common.collect.*;

import net.minecraft.core.*;
import net.minecraft.data.models.blockstates.*;

import net.minecraft.resources.*;
import net.minecraft.world.entity.ai.village.poi.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

import java.util.*;

@Mixin(PropertyDispatch.class)
public interface PropertyDispatchAccessor {

	@Invoker
	public  Map<Selector, List<Variant>> invokeGetEntries();
}
