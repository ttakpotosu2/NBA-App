package com.example.basketballapp.data.model.stats


data class GameStats(
	val errors: List<Int>,
	val `get`: String,
	val parameters: ParametersX,
	val response: List<StatsResponse>,
	val results: Int
)