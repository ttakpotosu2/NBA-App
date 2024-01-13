package com.example.basketballapp.presentation.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Resource
import com.example.basketballapp.domain.use_cases.games.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
	private val getGamesUseCase: GetGamesUseCase,
) : ViewModel() {
	private val _state = mutableStateOf(GamesState())
	val state: State<GamesState> = _state
	
	init {
		getGames(date = _state.value.currentDate)
	}
	
	private fun getGames(date: String) {
		getGamesUseCase(date).onEach { result ->
			when (result) {
				is Resource.Success -> {
					_state.value = GamesState(games = result.data)
				}
				
				is Resource.Error -> {
					_state.value =
						GamesState(error = result.message ?: "An unexpected error occurred")
				}
				
				is Resource.Loading -> {
					_state.value = GamesState(isLoading = true)
				}
			}
		}.launchIn(viewModelScope)
	}
}


//    private val _seasonQuery = mutableStateOf("2023")
//    val seasonQuery = _seasonQuery
//
//    fun updateSeason(season: String) {
//        _seasonQuery.value = season
//    }
//
//
//
//    val games = snapshotFlow {
//        _seasonQuery
//    }.debounce(500L).flatMapLatest {
//        repo.getGames(season = it.value).cachedIn(viewModelScope)
//    }
