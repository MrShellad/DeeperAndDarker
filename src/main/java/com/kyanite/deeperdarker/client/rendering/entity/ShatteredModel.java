package com.kyanite.deeperdarker.client.rendering.entity;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.registry.entities.custom.ShatteredEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ShatteredModel<T extends ShatteredEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DeeperAndDarker.MOD_ID, "shattered"), "main");
	private final ModelPart Shattered;

	public ShatteredModel(ModelPart root) {
		this.Shattered = root.getChild("Shattered");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Shattered = partdefinition.addOrReplaceChild("Shattered", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Body = Shattered.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -13.0F, -2.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(4, 2).addBox(0.0F, -2.0F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -4.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -6.0F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-0.5F, -8.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(0.0F, -10.0F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 16).addBox(-0.5F, -12.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 14).addBox(0.0F, -13.0F, 2.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition Tendril = Head.addOrReplaceChild("Tendril", CubeListBuilder.create().texOffs(24, 0).addBox(-8.0F, -5.5F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.5F, 0.0F));

		PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 16).addBox(0.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(0.0F, 12.0F, -2.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, 12.0F, -2.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(4.0F, 12.0F, -2.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, 0.0F));

		PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).mirror().addBox(-1.0F, 12.0F, -2.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-3.0F, 12.0F, -2.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 3).mirror().addBox(-4.0F, 12.0F, -2.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -12.0F, 0.0F));

		PartDefinition LeftLeg = Shattered.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 33).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -13.0F, 0.0F));

		PartDefinition RightLeg = Shattered.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 33).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -13.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float f = Math.min((float)entity.getDeltaMovement().lengthSqr() * 200.0F, 8.0F);
		this.animate(entity.idleAnimationState, ShatteredAnimations.SHATTERED_IDLE, ageInTicks);
		this.animate(entity.walkingAnimationState, ShatteredAnimations.SHATTERED_WALK, ageInTicks, f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Shattered.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.Shattered;
	}
}