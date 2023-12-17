package com.example.basketballapp.presentation

import com.example.basketballapp.data.model.GamesResponse

sealed class GamesUIState{
    object Loading: GamesUIState()
    data class Success(val data: List<GamesResponse>): GamesUIState()
}
