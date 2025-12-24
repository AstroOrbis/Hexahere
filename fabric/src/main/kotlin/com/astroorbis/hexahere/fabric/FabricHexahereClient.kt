package com.astroorbis.hexahere.fabric

import com.astroorbis.hexahere.HexahereClient
import net.fabricmc.api.ClientModInitializer

object FabricHexahereClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexahereClient.init()
    }
}
