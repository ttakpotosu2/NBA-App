package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.players.PlayersResponse
import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.model.stats.GameStatsResponse
import com.example.basketballapp.data.model.teams.TeamsResponse
import com.example.basketballapp.data.remote.NbaAppApi
import com.example.basketballapp.domain.repository.BasketballAppRepository
import javax.inject.Inject

class BasketballAppRepositoryImpl @Inject constructor(
	private val api: NbaAppApi
): BasketballAppRepository {
	override suspend fun getGames(date: String): GamesResponse {
		return api.getGames(date)
	}
	
	override suspend fun getGameById(gameId: Int): GamesResponse {
		return api.getGame(gameId)
	}
	
	override suspend fun getStandings(league: String, season: String): StandingsResponse {
		return api.getStandings(league, season)
	}

	override suspend fun getConferenceStandings(
		league: String,
		season: String,
		conference: String
	): StandingsResponse {
		return api.getConferenceStandings(league, season, conference)
	}

	override suspend fun getDivisionStandings(
		league: String,
		season: String,
		division: String
	): StandingsResponse {
		return api.getDivisionStandings(league, season, division)
	}

	override suspend fun getGameStats(gameId: Int): GameStatsResponse {
		return api.getGameStats(gameId)
	}

	override suspend fun getTeamById(teamId: Int): TeamsResponse {
		return api.getTeamById(teamId)
	}

	override suspend fun getGamesPerTeam(teamId: Int, season: String): GamesResponse {
		return api.getGamesPerTeam(teamId, season)
	}

	override suspend fun getPlayersPerTeam(teamId: Int, season: String): PlayersResponse {
		return api.getPlayersPerTeam(teamId, season)
	}

	override suspend fun getStandingsPerTeam(
		teamId: Int,
		season: String,
		league: String
	): StandingsResponse {
		return api.getStandingsPerTeam(teamId, season, league)
	}

	override suspend fun getStatsPerTeam(teamId: Int, season: String): GameStatsResponse {
		return api.getStatsPerTeam(teamId, season)
	}
}