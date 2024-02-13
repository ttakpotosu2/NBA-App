package com.example.basketballapp.data.model.standings

import com.example.basketballapp.data.model.StandingDetail

data class StandingDetailResponse(
	val conference: Conference,
	val division: Division,
	val gamesBehind: String,
	val league: String,
	val loss: Loss,
	val season: Int,
	val streak: Int,
	val team: Team,
	val tieBreakerPoints: Int,
	val win: Win,
	val winStreak: Boolean
) {
	fun toStandingDetail(): StandingDetail {
		return StandingDetail(
			conference,
			division,
			gamesBehind,
			league,
			loss,
			season,
			streak,
			team,
			tieBreakerPoints,
			win,
			winStreak
		)
	}
}