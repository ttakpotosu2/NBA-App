package com.example.basketballapp.presentation.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.domain.repository.BasketballAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesPerTeamViewModel @Inject constructor(
    private val repository: BasketballAppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(GamesPerTeamState())
    val state: State<GamesPerTeamState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_TEAM_ID)
            ?.let { getGamesPerTeam(it, season = _state.value.season) }
    }

    private fun getGamesPerTeam(teamId: Int, season: String) {
        viewModelScope.launch {
            _state.value =
                GamesPerTeamState(games = repository.getGamesPerTeam(teamId, season).toGames())
        }
    }
}

data class GamesPerTeamState(
    val isLoading: Boolean = false,
    val games: List<GameDetail> = emptyList(),
    val error: String = "",
    val season: String = "2023"
)