package com.example.basketballapp.presentation.teams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.data.repository.TeamsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: TeamsRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(TeamsState())
    val state: State<TeamsState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_TEAM_ID)?.let { teamId ->
            getTeamDetails(teamId, season = _state.value.season, league = _state.value.league)
        }
    }

    private fun getTeamDetails(teamId: Int, season: String, league: String){
        viewModelScope.launch {
            _state.value = TeamsState(
                teams = repository.getTeamById(teamId).toTeams(),
                games = repository.getGamesPerTeam(teamId, season).toGames(),
                players = repository.getPlayersPerTeam(teamId, season).toPlayers(),
                //stats = repository.getStatsPerTeam(teamId, season).toTeamStats(),
                //standings = repository.getStandingsPerTeam(teamId, season, league).toStandings()
            )
        }
    }
}