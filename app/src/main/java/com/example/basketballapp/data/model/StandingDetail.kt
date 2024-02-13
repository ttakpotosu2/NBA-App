package com.example.basketballapp.data.model

import com.example.basketballapp.data.model.standings.Conference
import com.example.basketballapp.data.model.standings.Division
import com.example.basketballapp.data.model.standings.Loss
import com.example.basketballapp.data.model.standings.Team
import com.example.basketballapp.data.model.standings.Win

data class StandingDetail(
	val conference: Conference,
	val division: Division,
	val gamesBehind: String? = "",
	val league: String,
	val loss: Loss,
	val season: Int,
	val streak: Int,
	val team: Team,
	val tieBreakerPoints: Int?,
	val win: Win,
	val winStreak: Boolean
)