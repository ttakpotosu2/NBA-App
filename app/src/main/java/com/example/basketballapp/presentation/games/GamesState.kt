package com.example.basketballapp.presentation.games

import com.example.basketballapp.domain.model.GameDetail

data class GamesState(
	val isLoading: Boolean = false,
	val games: List<GameDetail> = emptyList(),
	val error: String = "",
	val currentDate: String = "",
)

data class GameDetailState(
	val isLoading: Boolean = false,
	val game: GameDetail? = null,
	val error: String = ""
)