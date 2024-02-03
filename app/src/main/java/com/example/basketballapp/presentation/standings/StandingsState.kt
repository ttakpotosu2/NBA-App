package com.example.basketballapp.presentation.standings

import com.example.basketballapp.domain.model.Standings

data class StandingsState(
	val isLoading: Boolean = false,
	val standing: Standings? = null,
	val error: String = "",
	val season: String = "2023",
	val league: String = "standard",
	val conference: String = "",
	val division: String = ""
)