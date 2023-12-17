package com.example.basketballapp.data.model


data class GameStats(
    val errors: List<Any>,
    val `get`: String,
   // val parameters: ParametersX,
    val response: List<StatsResponse>,
    val results: Int
)