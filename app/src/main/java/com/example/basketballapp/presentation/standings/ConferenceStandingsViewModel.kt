package com.example.basketballapp.presentation.standings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.domain.repository.BasketballAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConferenceStandingsViewModel @Inject constructor(
    private val repository: BasketballAppRepository,
) : ViewModel() {

    private val _state = mutableStateOf(StandingsState())
    val state: State<StandingsState> = _state

    fun getConferenceStandings(league: String, season: String, conference: String) {
        viewModelScope.launch {
            _state.value = StandingsState(
                standing = repository.getConferenceStandings(
                    league,
                    season,
                    conference
                ).toStandings()
            )
        }
    }
}