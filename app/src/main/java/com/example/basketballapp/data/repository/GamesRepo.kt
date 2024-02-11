package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.stats.GameStatsResponse
import com.example.basketballapp.data.remote.NbaAppApi
import javax.inject.Inject

class GamesRepo @Inject constructor(
    private val api: NbaAppApi
) {
    suspend fun getGames(date: String): GamesResponse {
        return api.getGames(date)
    }

    suspend fun getGameById(gameId: Int): GamesResponse {
        return api.getGame(gameId)
    }

    suspend fun getGamesPerTeam(teamId: Int, season: String): GamesResponse {
        return api.getGamesPerTeam(teamId, season)
    }

    suspend fun getGameStats(gameId: Int): GameStatsResponse {
        return api.getGameStats(gameId)
    }
}