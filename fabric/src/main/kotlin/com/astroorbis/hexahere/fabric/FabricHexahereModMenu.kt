package com.astroorbis.hexahere.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.astroorbis.hexahere.HexahereClient

object FabricHexahereModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexahereClient::getConfigScreen)
}
