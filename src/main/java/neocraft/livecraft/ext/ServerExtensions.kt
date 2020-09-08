package neocraft.livecraft.ext

import org.bukkit.Server

fun Server.log(message: String) {
    this.broadcast(message, Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
}