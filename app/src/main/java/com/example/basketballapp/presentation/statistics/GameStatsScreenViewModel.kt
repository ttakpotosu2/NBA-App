package com.example.basketballapp.presentation.statistics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.data.repository.GamesRepo
import com.example.basketballapp.presentation.games.GamesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameStatsScreenViewModel @Inject constructor(
    private val repository: GamesRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(GamesState())
    val state: State<GamesState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_GAME_ID)?.let { getGameStat(it) }
    }
    private fun getGameStat(gameId: Int){
        viewModelScope.launch {
            _state.value = GamesState(stats = repository.getGameStats(gameId).toGameStats())
        }
    }
}