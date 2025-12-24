package com.astroorbis.hexahere

import com.astroorbis.hexahere.config.HexahereClientConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HexahereClient {
    fun init() {
        HexahereClientConfig.init()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(HexahereClientConfig.GlobalConfig::class.java, parent).get()
    }
}
