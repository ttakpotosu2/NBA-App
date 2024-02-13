package com.example.basketballapp.data.model.stats

import com.example.basketballapp.data.model.GameStatsDetail

data class GameStatsResponse(
	val errors: List<Int>,
	val `get`: String,
	val parameters: ParametersX,
	val response: List<GameStatsDetailResponse>,
	val results: Int
){
	fun toGameStats(): List<GameStatsDetail> {
		return response.map { it.toGameStatsDetail() }
	}
}