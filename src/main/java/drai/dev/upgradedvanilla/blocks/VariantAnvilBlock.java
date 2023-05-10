package drai.dev.upgradedvanilla.blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import org.jetbrains.annotations.*;

public class VariantAnvilBlock extends AnvilBlock {
	private String material;
	public VariantAnvilBlock(Properties properties, String material) {
		super(properties);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}
}
