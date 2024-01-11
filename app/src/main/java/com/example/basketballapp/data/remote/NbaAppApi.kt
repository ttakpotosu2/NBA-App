package com.example.basketballapp.data.remote

import com.example.basketballapp.data.model.GameDetailResponse
import com.example.basketballapp.data.model.GamesResponse
import com.example.basketballapp.data.model.StatsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaAppApi {

    @GET("games")
    suspend fun getGames(@Query("date") date: String): GamesResponse

    @GET("games")
    suspend fun getGame(@Query("id") id: Int): GameDetailResponse

    @GET("games/statistics/")
    suspend fun getGameStats(@Query("gameId") gameId: Int): StatsResponse
}