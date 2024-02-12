package com.example.basketballapp.presentation.teams

import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.model.GameStatsDetail
import com.example.basketballapp.domain.model.PlayerDetail
import com.example.basketballapp.domain.model.StandingDetail
import com.example.basketballapp.domain.model.TeamDetail

data class TeamsState(
    val isLoading: Boolean = false,
    val teams: List<TeamDetail> = emptyList(),
    val players: List<PlayerDetail> = emptyList(),
    val games: List<GameDetail> = emptyList(),
    val stats: List<GameStatsDetail> = emptyList(),
    val standings: List<StandingDetail> = emptyList(),
    val error: String = "",
    val season: String = "2023",
    val league: String = "standard"
)