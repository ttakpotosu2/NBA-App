package com.example.basketballapp.presentation.games

import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.model.Games
import java.time.LocalDateTime

data class GamesState(
	val isLoading: Boolean = false,
	val games: Games? = null,
	val error: String = "",
	var currentDate: String = LocalDateTime.now().toString().substring(0, 10),
)

data class GameDetailState(
	val isLoading: Boolean = false,
	val game: GameDetail? = null,
	val error: String = ""
)