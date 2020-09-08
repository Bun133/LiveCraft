package neocraft.livecraft.api

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import neocraft.livecraft.LiveCraft
import neocraft.livecraft.ws.PluginWebSocketClient
import org.bukkit.Server
import retrofit2.HttpException

class MildomUseCase(
        private val plugin: LiveCraft
) {
    fun connect(roomId: Int) {
        GlobalScope.launch {
            try {
                val gifts = MildomClient().getGifts()
                plugin.client = PluginWebSocketClient(plugin.server, roomId, gifts)
                plugin.client?.connect()
                plugin.server.broadcast("Success", Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
            } catch (e: HttpException) {
                plugin.server.broadcast("Failed ${e.code()} ${e.message()}", Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
            } catch (e: Exception) {
                plugin.server.broadcast("Failed $e", Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
            }
        }
    }
}