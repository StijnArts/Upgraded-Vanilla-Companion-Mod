package drai.dev.upgradedvanilla.blockentities.renderers;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;

import drai.dev.upgradedvanilla.blockentities.*;
import net.fabricmc.api.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

@Environment(value=EnvType.CLIENT)
public class BaseLecternBlockEntityRenderer
		implements BlockEntityRenderer<BaseLecternBlockEntity> {
	private final BookModel bookModel;

	public BaseLecternBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
		this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
	}

	@Override
	public void render(BaseLecternBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		BlockState blockState = blockEntity.getBlockState();
		if (!blockState.getValue(LecternBlock.HAS_BOOK).booleanValue()) {
			return;
		}
		poseStack.pushPose();
		poseStack.translate(0.5, 1.0625, 0.5);
		float f = blockState.getValue(LecternBlock.FACING).getClockWise().toYRot();
		poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
		poseStack.mulPose(Vector3f.ZP.rotationDegrees(67.5f));
		poseStack.translate(0.0, -0.125, 0.0);
		this.bookModel.setupAnim(0.0f, 0.1f, 0.9f, 1.2f);
		VertexConsumer vertexConsumer = EnchantTableRenderer.BOOK_LOCATION.buffer(bufferSource, RenderType::entitySolid);
		this.bookModel.render(poseStack, vertexConsumer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
		poseStack.popPose();
	}
}
