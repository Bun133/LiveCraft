package neocraft.livecraft.ws

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendParams(
        val userId: Int,
        val level: Int,
        val userName: String,
        val guestId: String,
        val nonopara: String,
        val roomId: Int,
        val cmd: String,
        val reConnect: Int,
        val nobleLevel: Int,
        val avatarDecortaion: Int,
        val enterroomEffect: Int,
        val nobleClose: Int,
        val nobleSeatClose: Int,
        val reqId: Int
)