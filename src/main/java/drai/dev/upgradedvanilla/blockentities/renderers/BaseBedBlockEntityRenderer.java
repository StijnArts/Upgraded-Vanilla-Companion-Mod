package drai.dev.upgradedvanilla.blockentities.renderers;

import com.google.common.collect.*;
import com.mojang.blaze3d.vertex.*;

import com.mojang.math.*;

import drai.dev.upgradedvanilla.blockentities.*;
import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.registry.*;
import it.unimi.dsi.fastutil.ints.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import java.util.*;

public class BaseBedBlockEntityRenderer implements BlockEntityRenderer<BaseBedBlockEntity> {
	private static final HashMap<Block, RenderType> LAYERS = Maps.newHashMap();
	private static final RenderType RENDER_TYPE = RenderType.entityCutout(new ResourceLocation("minecraft", "textures/entity/bed/white.png"));
	private final ModelPart headRoot;
	private final ModelPart footRoot;
	public BaseBedBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
		this.headRoot = context.bakeLayer(ModelLayers.BED_HEAD);
		this.footRoot = context.bakeLayer(ModelLayers.BED_FOOT);
	}

	@Override
	public void render(BaseBedBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		Level level = blockEntity.getLevel();
		BlockState blockState = blockEntity.getBlockState();
		if (level != null) {
			DoubleBlockCombiner.NeighborCombineResult<BaseBedBlockEntity> neighborCombineResult = DoubleBlockCombiner.combineWithNeigbour(BaseBlockEntities.BED, BaseBedBlock::getBlockType, BaseBedBlock::getConnectedDirection, ChestBlock.FACING, blockState, level, blockEntity.getBlockPos(), (world, pos) -> false);
			int i = ((Int2IntFunction)neighborCombineResult.apply(new BrightnessCombiner())).get(packedLight);
			this.renderPiece(poseStack, bufferSource, blockState.getValue(BedBlock.PART) == BedPart.HEAD ? this.headRoot : this.footRoot, blockState.getValue(BedBlock.FACING), blockState.getBlock(), i, packedOverlay, false);
		} else {
			Material material = Sheets.BED_TEXTURES[blockEntity.getColor().getId()];
			this.renderPiece(poseStack, bufferSource, this.headRoot, Direction.SOUTH, material, packedLight, packedOverlay, false);
			this.renderPiece(poseStack, bufferSource, this.footRoot, Direction.SOUTH, material, packedLight, packedOverlay, true);
		}
	}

	private void renderPiece(PoseStack poseStack, MultiBufferSource bufferSource, ModelPart modelPart ,Direction direction, Block block, int packedLight, int packedOverlay, boolean foot) {
		poseStack.pushPose();
		poseStack.translate(0.0, 0.5625, foot ? -1.0 : 0.0);
		poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0f));
		poseStack.translate(0.5, 0.5, 0.5);
		poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f + direction.toYRot()));
		poseStack.translate(-0.5, -0.5, -0.5);
		VertexConsumer vertexConsumer = getConsumer(bufferSource, block);
		modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}

	private void renderPiece(PoseStack poseStack, MultiBufferSource bufferSource, ModelPart modelPart, Direction direction, Material material, int packedLight, int packedOverlay, boolean foot) {
		poseStack.pushPose();
		poseStack.translate(0.0, 0.5625, foot ? -1.0 : 0.0);
		poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0f));
		poseStack.translate(0.5, 0.5, 0.5);
		poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f + direction.toYRot()));
		poseStack.translate(-0.5, -0.5, -0.5);
		VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entitySolid);
		modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}

	public static VertexConsumer getConsumer(MultiBufferSource provider, Block block) {
		RenderType layers = (RenderType)LAYERS.getOrDefault(block, RENDER_TYPE);
		return provider.getBuffer(layers);
	}

	public static void registerRenderLayer(Block block) {
		ResourceLocation blockId = Registry.BLOCK.getKey(block);
		String modId = blockId.getNamespace();
		String path = blockId.getPath();
		LAYERS.put(block, RenderType.entityCutout(new ResourceLocation(modId, "textures/entity/bed/" + path + ".png")));
	}
}
