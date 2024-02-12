package com.example.basketballapp.presentation.standings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.data.repository.StandingsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val repository: StandingsRepo
) : ViewModel() {

    private val _state = mutableStateOf(StandingsState())
    val state: State<StandingsState> = _state

    fun getStandings(season: String, league: String) {
        viewModelScope.launch {
            _state.value = StandingsState(
                standing = repository.getStandings(
                    league,
                    season
                ).toStandings()
            )
        }
    }

    fun getConferenceStandings(league: String, season: String, conference: String) {
        repository.getConferenceStandings(league, season, conference).onEach { result ->
            _state.value = StandingsState(standing = result.toStandings())
        }.launchIn(viewModelScope)
    }
}