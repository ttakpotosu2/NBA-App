package com.example.basketballapp.presentation.teams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.domain.model.TeamDetail
import com.example.basketballapp.domain.repository.BasketballAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: BasketballAppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(TeamsState())
    val state: State<TeamsState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_TEAM_ID)?.let { getTeam(it) }
    }
    private fun getTeam(teamId: Int){
        viewModelScope.launch {
            _state.value = TeamsState(team = repository.getTeamById(teamId).toTeams())
        }
    }
}

data class TeamsState(
    val isLoading: Boolean = false,
    val team: List<TeamDetail> = emptyList(),
    val error: String = ""
)