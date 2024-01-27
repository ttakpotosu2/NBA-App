package com.example.basketballapp.presentation.games

import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.model.Games

data class GamesState(
	val isLoading: Boolean = false,
	val games: Games? = null,
	val error: String = "",
	val currentDate: String = "",
)

data class GameDetailState(
	val isLoading: Boolean = false,
	val game: GameDetail? = null,
	val error: String = ""
)