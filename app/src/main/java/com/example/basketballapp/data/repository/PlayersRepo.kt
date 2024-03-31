package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.players.PlayersResponse
import com.example.basketballapp.data.remote.NbaAppApi
import javax.inject.Inject

class PlayersRepo @Inject constructor(
    private val api: NbaAppApi
) {
    suspend fun getPlayerById(playerId: Int ): PlayersResponse {
        return api.getPlayerById(playerId)
    }

    suspend fun getPlayersPerTeam(teamId: Int, season: String): PlayersResponse {
        return api.getPlayersPerTeam(teamId, season)
    }
}