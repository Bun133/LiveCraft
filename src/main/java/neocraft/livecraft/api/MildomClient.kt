package neocraft.livecraft.api

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import neocraft.livecraft.LiveCraft
import neocraft.livecraft.ws.PluginWebSocketClient
import okhttp3.OkHttpClient
import org.bukkit.Server
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MildomClient(
        private val plugin: LiveCraft,
) {
    private val baseUrl = "https://cloudac.mildom.com/"
    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(getClient())
            .build()
    private val service = retrofit.create(MildomService::class.java)

    fun getGifts(roomId: Int): List<Gift> {
        val gifts: MutableList<Gift> = mutableListOf()
        GlobalScope.launch {
            try {
                val response = service.gifts()
                response.body.models.forEach {
                    gifts.add(it)
                }
                response.body.pack.forEach {
                    gifts.add(it)
                }
                plugin.client = PluginWebSocketClient(plugin.server, roomId, gifts)
                plugin.client?.connect()
                plugin.server.broadcast("Success", Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
            } catch (e: HttpException) {
                plugin.server.broadcast("Failed ${e.code()} ${e.message()}", Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
            } catch (e: Exception) {
                plugin.server.broadcast("Failed $e", Server.BROADCAST_CHANNEL_ADMINISTRATIVE)
            }
        }
        return gifts
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }
}