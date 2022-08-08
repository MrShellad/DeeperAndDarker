package com.kyanite.deeperdarker.client.rendering.entity;

import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.registry.entities.custom.ShatteredEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShatteredRenderer extends MobRenderer<ShatteredEntity, ShatteredModel<ShatteredEntity>> {
    public ShatteredRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ShatteredModel<ShatteredEntity>(pContext.bakeLayer(ShatteredModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ShatteredEntity pEntity) {
        return new ResourceLocation(DeeperAndDarker.MOD_ID, "textures/entity/shattered.png");
    }
}
