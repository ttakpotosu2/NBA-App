package com.example.basketballapp.presentation.games

import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.model.GameStatsDetail

data class GamesState(
	val isLoading: Boolean = false,
	val games: List<GameDetail> = emptyList(),
	val game: GameDetail? = null,
	val stats: List<GameStatsDetail> = emptyList(),
	val error: String = "",
	val currentDate: String = "",
)