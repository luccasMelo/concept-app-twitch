package com.luccasmelo.concept.data.repository


import com.luccasmelo.concept.data.model.GameResponse
import io.reactivex.Observable

public class GamesRepository(val apiService: ApiService) {


    public var offset = -10

    public fun getNextGames(): Observable<GameResponse> {
        offset += 10
        return apiService.loadGames(offset = offset.toString())
    }

    fun reload() {
        offset = -10
    }
}