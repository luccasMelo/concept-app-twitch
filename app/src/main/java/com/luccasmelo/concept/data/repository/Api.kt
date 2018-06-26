package com.luccasmelo.concept.data.repository


import com.luccasmelo.concept.BuildConfig
import com.luccasmelo.concept.data.model.Game
import com.luccasmelo.concept.data.model.GameResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


public interface ApiService {
    @Headers("Accept: 'application/vnd.twitchtv.v5+json'",
            "Client-ID: ${BuildConfig.CLIENT_ID}")

    @GET("games/top")
    fun loadGames(@Query("offset") offset: String, @Query("limit") limit: String = "10"): Observable<GameResponse>

    //TODO load videos for stream
    @GET("videos/top")
    fun loadVideos(@Query("game") gameName: String)
}