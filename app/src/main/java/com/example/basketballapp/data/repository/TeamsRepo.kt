package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.players.PlayersResponse
import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.model.teams.TeamStatsResponse
import com.example.basketballapp.data.model.teams.TeamsResponse
import com.example.basketballapp.data.remote.NbaAppApi
import javax.inject.Inject

class TeamsRepo @Inject constructor(
    private val api: NbaAppApi
) {
    suspend fun getTeamById(teamId: Int): TeamsResponse {
        return api.getTeamById(teamId)
    }

    suspend fun getGamesPerTeam(teamId: Int, season: String): GamesResponse {
        return api.getGamesPerTeam(teamId, season)
    }

    suspend fun getPlayersPerTeam(teamId: Int, season: String): PlayersResponse {
        return api.getPlayersPerTeam(teamId, season)
    }

    suspend fun getStandingsPerTeam(
        teamId: Int,
        season: String,
        league: String
    ): StandingsResponse {
        return api.getStandingsPerTeam(teamId, season, league)
    }

    suspend fun getStatsPerTeam(teamId: Int, season: String): TeamStatsResponse {
        return api.getStatsPerTeam(teamId, season)
    }
}