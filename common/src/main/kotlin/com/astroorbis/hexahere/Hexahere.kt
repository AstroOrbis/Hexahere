package com.astroorbis.hexahere

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import com.astroorbis.hexahere.config.HexahereServerConfig
import com.astroorbis.hexahere.networking.HexahereNetworking
import com.astroorbis.hexahere.registry.HexahereActions

object Hexahere {
    const val MODID = "hexahere"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexahereServerConfig.init()
        initRegistries(
            HexahereActions,
        )
        HexahereNetworking.init()
    }

    fun initServer() {
        HexahereServerConfig.initServer()
    }
}
