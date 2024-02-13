package com.example.basketballapp.presentation.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.data.repository.GamesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailScreenViewModel @Inject constructor(

    private val gamesRepo: GamesRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _gamesState = mutableStateOf(GamesState())
    val gamesState: State<GamesState> = _gamesState

    init {
        savedStateHandle.get<Int>(Constants.PARAM_GAME_ID)?.let { getGameById(it) }
    }

    private fun getGameById(gameId: Int) {
        viewModelScope.launch{
            val result = gamesRepo.getGameById(gameId).toGames().first()
            _gamesState.value = GamesState(game = result)
        }
    }
}