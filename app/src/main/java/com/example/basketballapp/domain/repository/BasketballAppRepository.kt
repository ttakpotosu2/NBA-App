package com.example.basketballapp.domain.repository

import com.example.basketballapp.data.model.games.GameDetailResponse
import com.example.basketballapp.data.model.games.GamesResponse
import com.example.basketballapp.data.model.standings.StandingsResponse

interface BasketballAppRepository {
	
	suspend fun getGames(date: String): GamesResponse
	suspend fun getGameById(gameId: Int): GameDetailResponse
	
	suspend fun getStandings(league: String, season: String): StandingsResponse
}