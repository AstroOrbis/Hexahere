@file:JvmName("HexahereAbstractions")

package com.astroorbis.hexahere

import dev.architectury.injectables.annotations.ExpectPlatform
import com.astroorbis.hexahere.registry.HexahereRegistrar

fun initRegistries(vararg registries: HexahereRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexahereRegistrar<T>) {
    throw AssertionError()
}
