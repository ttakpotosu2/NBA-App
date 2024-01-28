package com.example.basketballapp.presentation.statistics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.domain.repository.BasketballAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameStatsViewModel @Inject constructor(
    private val repository: BasketballAppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(GameStatsState())
    val state: State<GameStatsState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_GAME_ID)?.let { getGameStat(it) }
    }
    private fun getGameStat(gameId: Int){
        viewModelScope.launch {
            _state.value = GameStatsState(stat = repository.getGameStats(gameId).toGameStats())
        }
    }
}