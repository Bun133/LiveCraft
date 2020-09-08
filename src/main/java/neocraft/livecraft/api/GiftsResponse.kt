package neocraft.livecraft.api

import com.squareup.moshi.Json

data class GiftsResponse(
        val code: Int,
        val body: Body
)

data class Body(
        val models: List<Gift>,
        val pack: List<Gift>
)

data class Gift(
        @Json(name = "gift_id") val giftId: Int,
        val name: String,
        val price: Int
)