package com.example.basketballapp.data.model.games

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Teams(
	val home: HomeTeam,
	@SerializedName("visitors") val visiting: VisitingTeam
)