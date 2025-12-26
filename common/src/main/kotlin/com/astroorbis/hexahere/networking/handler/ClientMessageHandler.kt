package com.astroorbis.hexahere.networking.handler

import com.astroorbis.hexahere.config.HexahereServerConfig
import com.astroorbis.hexahere.networking.msg.HexahereMessageS2C
import com.astroorbis.hexahere.networking.msg.MsgSyncConfigS2C
import dev.architectury.networking.NetworkManager.PacketContext

fun HexahereMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexahereServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
