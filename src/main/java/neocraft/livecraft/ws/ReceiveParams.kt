package neocraft.livecraft.ws

data class ReceiveParams(
        val cmd: String,
        val msg: String?,
        val userName: String?,
        val giftId: Int?,
        val count: Int?
)