package drai.dev.upgradedvanilla.registry;

import drai.dev.upgradedvanilla.blockentities.renderers.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.betterx.bclib.client.render.BaseChestBlockEntityRenderer;
import org.betterx.bclib.client.render.BaseSignBlockEntityRenderer;

@Environment(EnvType.CLIENT)
public class BaseBlockEntityRenders {
	public BaseBlockEntityRenders() {
	}

	public static void register() {
		BlockEntityRendererRegistry.register(BaseBlockEntities.BED, BaseBedBlockEntityRenderer::new);
	}
}
