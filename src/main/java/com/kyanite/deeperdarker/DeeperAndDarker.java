package com.kyanite.deeperdarker;

import com.kyanite.deeperdarker.client.rendering.armor.WardenArmorRenderer;
import com.kyanite.deeperdarker.client.rendering.entity.*;
import com.kyanite.deeperdarker.registry.blocks.DDBlocks;
import com.kyanite.deeperdarker.registry.blocks.entity.DDBlockEntityTypes;
import com.kyanite.deeperdarker.registry.enchantments.DDEnchantments;
import com.kyanite.deeperdarker.registry.entities.DDEntities;
import com.kyanite.deeperdarker.registry.entities.custom.SculkLeechEntity;
import com.kyanite.deeperdarker.registry.entities.custom.SculkSnapperEntity;
import com.kyanite.deeperdarker.registry.entities.custom.SculkWormEntity;
import com.kyanite.deeperdarker.registry.entities.custom.ShatteredEntity;
import com.kyanite.deeperdarker.registry.items.DDItems;
import com.kyanite.deeperdarker.registry.items.custom.WardenArmorItem;
import com.kyanite.deeperdarker.registry.sounds.DDSounds;
import com.kyanite.deeperdarker.registry.world.biomes.OthersideBiomes;
import com.kyanite.deeperdarker.registry.world.dimension.DDPoiTypes;
import com.kyanite.deeperdarker.registry.world.features.DDConfiguredFeatures;
import com.kyanite.deeperdarker.registry.world.features.DDFeatures;
import com.kyanite.deeperdarker.registry.world.features.DDPlacedFeatures;
import com.kyanite.deeperdarker.util.DDWoodTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod(DeeperAndDarker.MOD_ID)
public class DeeperAndDarker {
    public static final String MOD_ID = "deeperdarker";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DeeperAndDarker() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DDSounds.SOUND_EVENTS.register(eventBus);
        DDBlocks.BLOCKS.register(eventBus);
        DDFeatures.FEATURES.register(eventBus);
        DDEntities.ENTITY_TYPES.register(eventBus);
        DDConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
        DDPlacedFeatures.PLACED_FEATURES.register(eventBus);
        OthersideBiomes.BIOMES.register(eventBus);
        DDItems.ITEMS.register(eventBus);
        DDEnchantments.ENCHANTMENTS.register(eventBus);
        DDBlockEntityTypes.BLOCK_ENTITY_TYPES.register(eventBus);
        DDPoiTypes.POI.register(eventBus);

        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class DeeperAndDarkerClient {
        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event) {
            WoodType.register(DDWoodTypes.BONE);
            WoodType.register(DDWoodTypes.SCULK_BONE);
            BlockEntityRenderers.register(DDBlockEntityTypes.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);

            EntityRenderers.register(DDEntities.SCULK_SNAPPER.get(), SculkSnapperRenderer::new);
            EntityRenderers.register(DDEntities.SCULK_WORM.get(), SculkWormRenderer::new);
            EntityRenderers.register(DDEntities.SCULK_LEECH.get(), SculkLeechRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
            e.registerLayerDefinition(ShatteredModel.LAYER_LOCATION, ShatteredModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers e) {
            e.registerEntityRenderer(DDEntities.SHATTERED.get(), ShatteredRenderer::new);
        }

        @SubscribeEvent
        public static void commonSetup(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                Sheets.addWoodType(DDWoodTypes.BONE);
                Sheets.addWoodType(DDWoodTypes.SCULK_BONE);
            });

            ComposterBlock.COMPOSTABLES.put(DDBlocks.SCULK_GLEAM.get().asItem(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DDBlocks.SCULK_VINES.get().asItem(), 0.5F);

            ComposterBlock.COMPOSTABLES.put(DDBlocks.GLOOM_GRASS.get().asItem(), 0.65F);

            ComposterBlock.COMPOSTABLES.put(DDItems.GLOOM_BERRIES.get(), 0.3F);
        }

        @SubscribeEvent
        public static void entityRender(final EntityRenderersEvent.AddLayers event) {
            GeoArmorRenderer.registerArmorRenderer(WardenArmorItem.class, new WardenArmorRenderer());
        }

        @SubscribeEvent
        public static void entityAttribute(final EntityAttributeCreationEvent event) {
            event.put(DDEntities.SCULK_SNAPPER.get(), SculkSnapperEntity.attributes());
            event.put(DDEntities.SCULK_WORM.get(), SculkWormEntity.attributes());
            event.put(DDEntities.SCULK_LEECH.get(), SculkLeechEntity.attributes());
            event.put(DDEntities.SHATTERED.get(), ShatteredEntity.attributes());
        }
    }
}
