package com.example.basketballapp.data.model


data class StatsResponse(
    val statistics: List<Statistic>,
    val team: StatsTeam
)