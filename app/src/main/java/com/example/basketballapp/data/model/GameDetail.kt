package com.example.basketballapp.data.model

import com.example.basketballapp.data.model.games.Arena
import com.example.basketballapp.data.model.games.Date
import com.example.basketballapp.data.model.games.Periods
import com.example.basketballapp.data.model.games.Scores
import com.example.basketballapp.data.model.games.Status
import com.example.basketballapp.data.model.games.Teams

data class GameDetail(
	val arena: Arena?,
	val date: Date?,
	val id: Int,
	val leadChanges: Int,
	val league: String?,
	val nugget: String?,
	val officials: List<String>,
	val periods: Periods?,
	val scores: Scores?,
	val season: Int,
	val stage: Int,
	val status: Status?,
	val teams: Teams?,
	val timesTied: Int
)