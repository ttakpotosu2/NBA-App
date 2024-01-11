package com.example.basketballapp.domain.repository

import com.example.basketballapp.data.model.GameDetailResponse
import com.example.basketballapp.data.model.GamesResponse

interface BasketballAppRepository {
	
	suspend fun getGames(date: String): GamesResponse
	suspend fun getGameById(gameId: Int): GameDetailResponse
}