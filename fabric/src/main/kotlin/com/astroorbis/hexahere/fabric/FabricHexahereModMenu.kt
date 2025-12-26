package com.astroorbis.hexahere.fabric

import com.astroorbis.hexahere.HexahereClient
import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi

object FabricHexahereModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexahereClient::getConfigScreen)
}
