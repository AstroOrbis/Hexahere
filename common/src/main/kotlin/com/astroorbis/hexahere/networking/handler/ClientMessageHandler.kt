package com.astroorbis.hexahere.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import com.astroorbis.hexahere.config.HexahereServerConfig
import com.astroorbis.hexahere.networking.msg.*

fun HexahereMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexahereServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
