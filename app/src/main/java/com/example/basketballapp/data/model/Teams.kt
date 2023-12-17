package com.example.basketballapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Teams(
    val home: HomeTeam,
    @SerializedName("visitors") val visiting: VisitingTeam
)