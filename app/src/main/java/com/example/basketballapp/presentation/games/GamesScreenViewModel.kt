package com.example.basketballapp.presentation.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.data.repository.GamesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesScreenViewModel @Inject constructor(
	private val gamesRepo: GamesRepo
) : ViewModel() {
	private val _gamesState = mutableStateOf(GamesState())
	val gamesState: State<GamesState> = _gamesState

	fun getGames(date: String) {
		viewModelScope.launch {
			val result = gamesRepo.getGames(date).toGames()
			_gamesState.value = GamesState(games = result)
		}
	}
}