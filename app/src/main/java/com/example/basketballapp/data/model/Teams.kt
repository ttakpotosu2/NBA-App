package com.example.basketballapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Teams(
    @SerializedName("home") val homeTeam: HomeTeam,
    @SerializedName("visitors") val visitingTeam: VisitingTeam
)