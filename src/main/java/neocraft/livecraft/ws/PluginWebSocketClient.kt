package neocraft.livecraft.ws

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import neocraft.livecraft.api.Gift
import neocraft.livecraft.ext.log
import org.bukkit.ChatColor
import org.bukkit.Server
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class PluginWebSocketClient(private val server: Server, private val roomId: Int, private val gifts: List<Gift>) : WebSocketClient(URI.create("wss://jp-room1.mildom.com/?roomId=$roomId")) {
    override fun onOpen(handshakedata: ServerHandshake?) {
        server.log("WSサーバに接続しました。")
        val guestId = "pc-gp-10ab39f4-f397-4c24-8070-667c729dc531"
        val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(Date())
        val userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36"

        val nonopara = "fr=web`sfr=pc`devi=OS X 10.15.6 64-bit`la=ja`gid=$guestId`na=Japan`loc=Japan|Tokyo`clu=aws_japan`wh=1920*1080`rtm=$time`ua=$userAgent`aid=$roomId`live_type=2`live_subtype=2`isHomePage=false"
        val params = SendParams(
                0,
                1,
                "guest512373",
                guestId,
                nonopara,
                roomId,
                "enterRoom",
                1,
                0,
                0,
                0,
                0,
                0,
                1
        )


        val adapter = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build().adapter(SendParams::class.java)
        val jsonText = adapter.toJson(params)
        send(jsonText)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        server.log("WSサーバから切断しました。reason:${reason}")
    }

    override fun onMessage(message: String?) {
        message ?: return
        val adapter = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build().adapter(ReceiveParams::class.java)
        val params = adapter.fromJson(message) ?: return
        when (params.cmd) {
            "onChat" -> server.broadcastMessage("${ChatColor.AQUA}[配信コメント] ${params.userName} > ${params.msg}")
            "onGift" -> {
                val gift = gifts.firstOrNull { it.giftId == params.giftId }
                if (gift != null) {
                    server.broadcastMessage("${ChatColor.AQUA}[配信ギフト] ${params.userName} から ${gift.name}（${gift.price}） を ${params.count}個 もらったよ")
                } else {
                    server.broadcastMessage("${ChatColor.AQUA}[配信ギフト] ${params.userName} から ${params.giftId} を ${params.count}個 もらったよ")
                }
            }
        }
    }

    override fun onError(ex: Exception?) {
        server.log("エラーが発生しました。 $ex")
    }
}