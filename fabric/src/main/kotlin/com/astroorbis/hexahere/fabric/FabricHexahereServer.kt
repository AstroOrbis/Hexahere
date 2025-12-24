package com.astroorbis.hexahere.fabric

import com.astroorbis.hexahere.Hexahere
import net.fabricmc.api.DedicatedServerModInitializer

object FabricHexahereServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Hexahere.initServer()
    }
}
