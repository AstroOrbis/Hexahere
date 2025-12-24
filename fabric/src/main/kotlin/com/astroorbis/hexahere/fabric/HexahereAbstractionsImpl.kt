@file:JvmName("HexahereAbstractionsImpl")

package com.astroorbis.hexahere.fabric

import com.astroorbis.hexahere.registry.HexahereRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexahereRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
