package com.example.basketballapp.presentation.players

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.data.repository.PlayersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val repository: PlayersRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(PlayersState())
    val state: State<PlayersState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_PLAYER_ID)?.toIntOrNull()?.let { playerId ->
            getPlayerDetails(playerId)
        }
    }

    private fun getPlayerDetails(playerId: Int){
        viewModelScope.launch {
            _state.value = PlayersState(
                players = repository.getPlayerById(playerId).toPlayers()
            )
        }
    }
}