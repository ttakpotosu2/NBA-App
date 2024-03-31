package com.example.basketballapp.presentation.players

import com.example.basketballapp.data.model.PlayerDetail

data class PlayersState(
    val isLoading: Boolean = false,
    val players: List<PlayerDetail> = emptyList(),
    val error: String = "",
    val season: String = "2023",
    val league: String = "standard",
    val conference: String = "",
    val division: String = ""
)