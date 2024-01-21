package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.games.GameDetailResponse
import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.remote.NbaAppApi
import com.example.basketballapp.domain.repository.BasketballAppRepository
import javax.inject.Inject

class BasketballAppRepositoryImpl @Inject constructor(
	private val api: NbaAppApi
): BasketballAppRepository {
	override suspend fun getGames(date: String): GamesResponse {
		return api.getGames(date)
	}
	
	override suspend fun getGameById(gameId: Int): GameDetailResponse {
		return api.getGame(gameId)
	}
	
	override suspend fun getStandings(league: String, season: String): StandingsResponse {
		return api.getStandings(league, season)
	}
}