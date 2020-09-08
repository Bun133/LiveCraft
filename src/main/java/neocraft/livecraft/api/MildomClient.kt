package neocraft.livecraft.api

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MildomClient() {
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

    suspend fun getGifts(): List<Gift> {
        val result = mutableListOf<Gift>()
        val response = service.gifts()
        response.body.models.forEach {
            result.add(it)
        }
        response.body.pack.forEach {
            result.add(it)
        }
        return result
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }
}