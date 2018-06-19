package com.luccasmelo.concept.data.repository


import com.luccasmelo.concept.data.ApiService
import com.luccasmelo.concept.data.model.Game
import io.reactivex.Flowable

public class GamesRepository(private val apiService: ApiService) {


    public val offset = -10

    public fun getNextGames(): Flowable<List<Game>> {
        offset +10
       return apiService.loadGames(offset = offset.toString())
    }
}