package com.astroorbis.hexahere.networking

import com.astroorbis.hexahere.Hexahere
import com.astroorbis.hexahere.networking.msg.HexahereMessageCompanion
import dev.architectury.networking.NetworkChannel

object HexahereNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(Hexahere.id("networking_channel"))

    fun init() {
        for (subclass in HexahereMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
