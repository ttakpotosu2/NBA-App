package com.example.basketballapp.presentation

import com.example.basketballapp.data.model.Games

sealed class GamesUIState{
    object Loading: GamesUIState()
    data class Success(val data: List<Games>): GamesUIState()
}
