package com.example.basketballapp.domain.repository

import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.players.PlayersResponse
import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.model.stats.GameStatsResponse
import com.example.basketballapp.data.model.teams.TeamsResponse

interface BasketballAppRepository {
	
	suspend fun getGames(date: String): GamesResponse
	suspend fun getGameById(gameId: Int): GamesResponse
	suspend fun getStandings(league: String, season: String): StandingsResponse
	suspend fun getConferenceStandings(league: String, season: String, conference: String): StandingsResponse
	suspend fun getDivisionStandings(league: String, season: String, division: String): StandingsResponse
	suspend fun getGameStats(gameId: Int): GameStatsResponse
	suspend fun getTeamById(teamId: Int): TeamsResponse
	suspend fun getGamesPerTeam(teamId: Int, season: String): GamesResponse
	suspend fun getPlayersPerTeam(teamId: Int, season: String): PlayersResponse
	suspend fun getStandingsPerTeam(teamId: Int, season: String, league: String): StandingsResponse
	suspend fun getStatsPerTeam(teamId: Int, season: String): GameStatsResponse
}