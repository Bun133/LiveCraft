package neocraft.livecraft.api

import retrofit2.http.GET

interface MildomService {
    @GET("nonolive/gappserv/gift/find")
    suspend fun gifts(): GiftsResponse
}