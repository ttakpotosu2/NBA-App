package com.example.basketballapp.data.model

import com.example.basketballapp.data.model.stats.Statistics
import com.example.basketballapp.data.model.stats.StatsTeam

data class GameStatsDetail(
    val statistics: List<Statistics>,
    val team: StatsTeam
)