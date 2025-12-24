package com.astroorbis.hexahere.fabric

import com.astroorbis.hexahere.Hexahere
import net.fabricmc.api.ModInitializer

object FabricHexahere : ModInitializer {
    override fun onInitialize() {
        Hexahere.init()
    }
}
