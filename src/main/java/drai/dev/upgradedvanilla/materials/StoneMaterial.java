package drai.dev.upgradedvanilla.materials;

import net.minecraft.world.level.block.*;

import java.awt.image.*;

public class StoneMaterial implements IMaterial{
	private String materialName;
	private Block cobbledStone;
	private Block stone;
	private BufferedImage palette;


	@Override
	public String getMaterialName() {
		return materialName;
	}
}
