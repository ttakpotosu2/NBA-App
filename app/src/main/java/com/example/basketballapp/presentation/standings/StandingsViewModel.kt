package com.example.basketballapp.presentation.standings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Resource
import com.example.basketballapp.domain.use_cases.standings.GetStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
	private val standingsUseCase: GetStandingsUseCase
): ViewModel() {
	
	private val _state = mutableStateOf(StandingsState())
	val state: State<StandingsState> = _state
	
	init {
		getStandings(
			season = _state.value.season,
			league = _state.value.league
		)
	}
	
	private fun getStandings(season: String, league: String) {
		standingsUseCase(league, season).onEach { result ->
			when (result) {
				is Resource.Success -> {
					_state.value = StandingsState(standing = result.data)
				}
				is Resource.Error -> {
					_state.value = StandingsState(error = result.message ?: "An unexpected error occurred")
				}
				is Resource.Loading -> {
					_state.value = StandingsState(isLoading = true)
				}
			}
		}.launchIn(viewModelScope)
	}
	
}