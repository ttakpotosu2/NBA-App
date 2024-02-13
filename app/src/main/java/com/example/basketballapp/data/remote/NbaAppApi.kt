package com.example.basketballapp.data.remote

import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.players.PlayersResponse
import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.model.stats.GameStatsResponse
import com.example.basketballapp.data.model.teams.TeamStatsResponse
import com.example.basketballapp.data.model.teams.TeamsResponse
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

    @GET("teams")
    suspend fun getTeamById(
        @Query("id") teamId: Int
    ): TeamsResponse

    @GET("games")
    suspend fun getGamesPerTeam(
        @Query("team") teamId: Int,
        @Query("season") season: String
    ): GamesResponse

    @GET("players")
    suspend fun getPlayersPerTeam(
        @Query("team") teamId: Int,
        @Query("season") season: String
    ): PlayersResponse

    @GET("standings")
    suspend fun getStandingsPerTeam(
        @Query("team") teamId: Int,
        @Query("season") season: String,
        @Query("league") league: String
    ): StandingsResponse

    @GET("teams/statistics")
    suspend fun getStatsPerTeam(
        @Query("id") teamId: Int,
        @Query("season") season: String
    ): TeamStatsResponse

    //playerPerId
    //search
    //stats per player per game
    //stats per
    //team stats
    //players stats per game
    //players stats per team

}
