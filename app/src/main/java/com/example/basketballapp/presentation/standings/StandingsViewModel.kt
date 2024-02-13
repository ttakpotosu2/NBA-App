package com.example.basketballapp.presentation.standings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.data.repository.StandingsRepo
import com.example.basketballapp.domain.model.StandingDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val repository: StandingsRepo
) : ViewModel() {

    private val _state = mutableStateOf(StandingsState())
    val state: State<StandingsState> = _state

    var conferenceState = mutableStateMapOf<String, List<StandingDetail>>()
        private set

    var divisionState = mutableStateMapOf<String, List<StandingDetail>>()
        private set

    fun getStandings(season: String, league: String) {
        viewModelScope.launch {
            _state.value = StandingsState(
                standing = repository.getStandings(
                    league,
                    season
                ).toStandings().sortedByDescending { it.win.percentage }
            )
        }
    }

    fun getConferenceStandings(league: String, season: String) {
        viewModelScope.launch {
            listOf("east", "west").forEach { conference ->
                val response = repository.getConferenceStandings(league, season, conference)
                conferenceState[conference] = response.toStandings().sortedByDescending { it.win.percentage }
            }
        }
    }

    fun getDivisionStandings(league: String, season: String) {
        viewModelScope.launch {
            listOf("atlantic", "central", "northwest","pacific","southeast").forEach { division ->
                val response = repository.getDivisionStandings(league, season, division)
                divisionState[division] = response.toStandings().sortedByDescending { it.win.percentage }
            }
        }
    }
}