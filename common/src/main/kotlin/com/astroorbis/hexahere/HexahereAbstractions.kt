@file:JvmName("HexahereAbstractions")

package com.astroorbis.hexahere

import com.astroorbis.hexahere.registry.HexahereRegistrar
import dev.architectury.injectables.annotations.ExpectPlatform

fun initRegistries(vararg registries: HexahereRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexahereRegistrar<T>) {
    throw AssertionError()
}
