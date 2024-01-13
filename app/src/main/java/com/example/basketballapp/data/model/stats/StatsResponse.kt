package com.example.basketballapp.data.model.stats

data class StatsResponse(
    val statistics: List<Statistics>,
    val team: StatsTeam
)