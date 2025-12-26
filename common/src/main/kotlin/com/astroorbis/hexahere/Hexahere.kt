package com.astroorbis.hexahere

import com.astroorbis.hexahere.config.HexahereServerConfig
import com.astroorbis.hexahere.networking.HexahereNetworking
import com.astroorbis.hexahere.registry.HexahereActions
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Hexahere {
    const val MODID = "hexahere"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        LOGGER.info("when the HEXA is HERE !!")
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
