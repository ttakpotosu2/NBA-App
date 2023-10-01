package com.example.basketballapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class VisitingTeamScore(
    @SerializedName("linescore") val lineScore: List<String>,
    val loss: Int,
    val points: Int,
    val series: Series,
    val win: Int
)