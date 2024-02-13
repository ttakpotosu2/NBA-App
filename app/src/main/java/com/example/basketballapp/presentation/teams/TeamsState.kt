package com.example.basketballapp.presentation.teams

import com.example.basketballapp.data.model.GameDetail
import com.example.basketballapp.data.model.PlayerDetail
import com.example.basketballapp.data.model.StandingDetail
import com.example.basketballapp.data.model.TeamDetail
import com.example.basketballapp.data.model.teams.TeamStatsDetails

data class TeamsState(
    val isLoading: Boolean = false,
    val teams: List<TeamDetail> = emptyList(),
    val players: List<PlayerDetail> = emptyList(),
    val games: List<GameDetail> = emptyList(),
    val stats: List<TeamStatsDetails> = emptyList(),
    val standings: List<StandingDetail> = emptyList(),
    val error: String = "",
    val season: String = "2023",
    val league: String = "standard"
)