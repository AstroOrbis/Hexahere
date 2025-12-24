package com.astroorbis.hexahere.mixin;

import com.astroorbis.hexahere.Hexahere;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

// scuffed workaround for https://github.com/architectury/architectury-loom/issues/189
@Mixin({
    net.minecraft.data.Main.class,
    net.minecraft.server.Main.class,
})
public abstract class MixinDatagenMain {
    @WrapMethod(method = "main", remap = false)
    private static void hexahere$systemExitAfterDatagenFinishes(String[] strings, Operation<Void> original) {
        try {
            original.call((Object) strings);
        } catch (Throwable throwable) {
            Hexahere.LOGGER.error("Datagen failed!", throwable);
            System.exit(1);
        }
        Hexahere.LOGGER.info("Datagen succeeded, terminating.");
        System.exit(0);
    }
}
