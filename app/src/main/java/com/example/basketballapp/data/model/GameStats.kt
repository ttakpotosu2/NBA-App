package com.example.basketballapp.data.model


import com.google.gson.annotations.SerializedName

data class GameStats(
    val errors: List<Any>,
    val `get`: String,
    val parameters: ParametersX,
    val response: List<StatsResponse>,
    val results: Int
)