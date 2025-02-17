package com.kyanite.deeperdarker.registry.sounds;

import com.kyanite.deeperdarker.DeeperAndDarker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DDSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeeperAndDarker.MOD_ID);

    // Sculk Stone
    public static final RegistryObject<SoundEvent> SCULK_STONE_BREAK = register("block.sculk_stone.break");
    public static final RegistryObject<SoundEvent> SCULK_STONE_FALL = register("block.sculk_stone.fall");
    public static final RegistryObject<SoundEvent> SCULK_STONE_HIT = register("block.sculk_stone.hit");
    public static final RegistryObject<SoundEvent> SCULK_STONE_PLACE = register("block.sculk_stone.place");
    public static final RegistryObject<SoundEvent> SCULK_STONE_STEP = register("block.sculk_stone.step");
    public static final ForgeSoundType SCULK_STONE = new ForgeSoundType(1, 1, SCULK_STONE_BREAK, SCULK_STONE_STEP, SCULK_STONE_PLACE, SCULK_STONE_HIT, SCULK_STONE_FALL);

    // Vase
    public static final RegistryObject<SoundEvent> VASE_BREAK = register("block.vase.break");
    public static final RegistryObject<SoundEvent> VASE_FALL = register("block.vase.fall");
    public static final RegistryObject<SoundEvent> VASE_HIT = register("block.vase.hit");
    public static final RegistryObject<SoundEvent> VASE_PLACE = register("block.vase.place");
    public static final RegistryObject<SoundEvent> VASE_STEP = register("block.vase.step");
    public static final ForgeSoundType VASE = new ForgeSoundType(5, 1, VASE_BREAK, VASE_STEP, VASE_PLACE, VASE_HIT, VASE_FALL);

    // Sculk Snapper
    public static final RegistryObject<SoundEvent> SCULK_SNAPPER_AMBIENT = register("entity.snapper.ambient");
    public static final RegistryObject<SoundEvent> SCULK_SNAPPER_BITE = register("entity.snapper.bite");
    public static final RegistryObject<SoundEvent> SCULK_SNAPPER_HURT = register("entity.snapper.hurt");
    public static final RegistryObject<SoundEvent> SCULK_SNAPPER_SNIFF = register("entity.snapper.sniff");

    // Stalker
    public static final RegistryObject<SoundEvent> STALKER_RING = register("entity.stalker.ring");

    // Portal
    public static final RegistryObject<SoundEvent> PORTAL_GROAN = register("ambience.portal.groan");

    // Item
    public static final RegistryObject<SoundEvent> SCULK_LINK = register("item.transmitter.link");
    public static final RegistryObject<SoundEvent> SCULK_TRANSMIT = register("item.transmitter.transmit");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(DeeperAndDarker.MOD_ID, name)));
    }
}
