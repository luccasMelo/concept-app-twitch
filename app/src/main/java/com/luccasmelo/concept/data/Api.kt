package com.luccasmelo.concept.data


import com.luccasmelo.concept.BuildConfig
import com.luccasmelo.concept.data.model.Game
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


public interface ApiService{
    @Headers("Accept: 'application/vnd.twitchtv.v5+json'",
                    "Client-ID: ${BuildConfig.CLIENT_ID}")

    @GET
    fun loadGames(@Query("offset") offset:String? = null, @Query("limit") limit:String = "10"): Flowable<List<Game>>
}