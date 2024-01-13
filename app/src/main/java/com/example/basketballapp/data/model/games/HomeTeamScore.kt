package com.example.basketballapp.data.model.games

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HomeTeamScore(
	@SerializedName("linescore") val lineScore: List<String>,
	val loss: Int,
	val points: Int,
	val series: Series,
	val win: Int
)