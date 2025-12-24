package com.astroorbis.hexahere.forge

import com.astroorbis.hexahere.Hexahere
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHexahereServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        Hexahere.initServer()
    }
}
