package com.example.basketballapp.data.remote

import com.example.basketballapp.data.model.Games
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaAppApi {

    @GET("games/")
    suspend fun getGames (@Query("season") season: Int ): Games
}