package com.astroorbis.hexahere.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import com.astroorbis.hexahere.Hexahere
import com.astroorbis.hexahere.networking.HexahereNetworking
import com.astroorbis.hexahere.networking.handler.applyOnClient
import com.astroorbis.hexahere.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HexahereMessage

sealed interface HexahereMessageC2S : HexahereMessage {
    fun sendToServer() {
        HexahereNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HexahereMessageS2C : HexahereMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HexahereNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HexahereNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HexahereMessageCompanion<T : HexahereMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                Hexahere.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HexahereMessageC2S -> msg.applyOnServer(ctx)
                    else -> Hexahere.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                Hexahere.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HexahereMessageS2C -> msg.applyOnClient(ctx)
                    else -> Hexahere.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
