package com.example.basketballapp.data.model


data class StatsResponse(
    val statistics: List<Statistics>,
    val team: StatsTeam
)