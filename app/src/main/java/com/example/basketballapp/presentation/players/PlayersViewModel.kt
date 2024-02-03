package com.example.basketballapp.presentation.players

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.domain.model.PlayerDetail
import com.example.basketballapp.domain.repository.BasketballAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val repository: BasketballAppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PlayersState())
    val state: State<PlayersState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_TEAM_ID)
            ?.let { getPlayerPerTeam(it, season = _state.value.season) }
    }

    private fun getPlayerPerTeam(teamId: Int, season: String) {
        viewModelScope.launch {
            _state.value =
                PlayersState(player = repository.getPlayersPerTeam(teamId, season).toPlayers())
        }
    }
}

data class PlayersState(
    val isLoading: Boolean = false,
    val player: List<PlayerDetail> = emptyList(),
    val error: String = "",
    val season: String = "2023"
)