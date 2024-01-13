package com.example.basketballapp.presentation.standings

import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.model.Standings

data class StandingsState(
	val isLoading: Boolean = false,
	val standing: Standings? = null,
	val error: String = "",
	var season: String = "2023",
	var league: String = "standard"
)

data class StandingDetailState(
	val isLoading: Boolean = false,
	val game: GameDetail? = null,
	val error: String = ""
)