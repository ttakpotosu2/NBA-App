package com.example.basketballapp.data.remote

import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.model.stats.GameStatsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaAppApi {

    @GET("games")
    suspend fun getGames(@Query("date") date: String): GamesResponse

    @GET("games")
    suspend fun getGame(@Query("id") gameId: Int): GamesResponse

    @GET("games/statistics")
    suspend fun getGameStats(
        @Query("id") gameId: Int
    ): GameStatsResponse

    @GET("standings/")
    suspend fun getStandings(
        @Query("league") league: String,
        @Query("season") season: String
    ): StandingsResponse

    @GET("standings/")
    suspend fun getConferenceStandings(
        @Query("league") league: String,
        @Query("season") season: String,
        @Query("conference") conference: String
    ): StandingsResponse

    @GET("standings/")
    suspend fun getDivisionStandings(
        @Query("league") league: String,
        @Query("season") season: String,
        @Query("division") division: String
    ): StandingsResponse
}

