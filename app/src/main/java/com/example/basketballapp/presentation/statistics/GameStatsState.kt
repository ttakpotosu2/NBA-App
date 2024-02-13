package com.example.basketballapp.presentation.statistics

import com.example.basketballapp.data.model.GameStatsDetail

data class GameStatsState(
    val isLoading: Boolean = false,
    val stat: List<GameStatsDetail> = emptyList(),
    val error: String = ""
)