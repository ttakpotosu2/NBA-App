package com.example.basketballapp.data.remote

import com.example.basketballapp.data.model.GameStats
import com.example.basketballapp.data.model.Games
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaAppApi {

    @GET("games/")
    suspend fun getGames(
        @Query("season") season: String
    ): Games

    @GET("games/statistics/")
    suspend fun getGameStats(
        @Query("gameId") gameId: Int,
    ): GameStats
}