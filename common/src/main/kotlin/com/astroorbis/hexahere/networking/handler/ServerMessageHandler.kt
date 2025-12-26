package com.astroorbis.hexahere.networking.handler

import com.astroorbis.hexahere.networking.msg.HexahereMessageC2S
import dev.architectury.networking.NetworkManager.PacketContext

fun HexahereMessageC2S.applyOnServer(ctx: PacketContext) = ctx.queue {
    // NOTE: this is commented out because otherwise it fails to compile if there's nothing inside of the when expression
    /*
    when (this) {
        // add server-side message handlers here
    }
    */
}
