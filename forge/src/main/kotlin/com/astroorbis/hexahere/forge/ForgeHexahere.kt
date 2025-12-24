package com.astroorbis.hexahere.forge

import dev.architectury.platform.forge.EventBuses
import com.astroorbis.hexahere.Hexahere
import com.astroorbis.hexahere.forge.datagen.ForgeHexahereDatagen
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hexahere.MODID)
class ForgeHexahere {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hexahere.MODID, this)
            addListener(ForgeHexahereClient::init)
            addListener(ForgeHexahereDatagen::init)
            addListener(ForgeHexahereServer::init)
        }
        Hexahere.init()
    }
}
