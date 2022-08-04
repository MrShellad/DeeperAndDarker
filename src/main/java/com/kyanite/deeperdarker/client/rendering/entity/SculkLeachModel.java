package com.kyanite.deeperdarker.client.rendering.entity;

import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.registry.entities.custom.SculkLeechEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SculkLeachModel extends AnimatedGeoModel<SculkLeechEntity> {
    @Override
    public ResourceLocation getModelResource(SculkLeechEntity object) {
        return new ResourceLocation(DeeperAndDarker.MOD_ID, "geo/sculk_leach.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SculkLeechEntity object) {
        return new ResourceLocation(DeeperAndDarker.MOD_ID, "textures/entity/sculk_leach.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SculkLeechEntity animatable) {
        return new ResourceLocation(DeeperAndDarker.MOD_ID, "animations/sculk_leach.animation.json");
    }
}
