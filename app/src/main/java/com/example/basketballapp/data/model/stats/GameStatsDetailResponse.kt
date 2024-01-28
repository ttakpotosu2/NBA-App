package com.example.basketballapp.data.model.stats

import com.example.basketballapp.domain.model.GameStatsDetail

data class GameStatsDetailResponse(
    val statistics: List<Statistics>,
    val team: StatsTeam
){
    fun toGameStatsDetail(): GameStatsDetail{
        return GameStatsDetail(
            statistics, team
        )
    }
}