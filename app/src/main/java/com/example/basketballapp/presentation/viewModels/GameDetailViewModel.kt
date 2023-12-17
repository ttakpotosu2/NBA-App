package com.example.basketballapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.data.repositories.GamesRepo
import com.example.basketballapp.domain.GameDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed class GameState {
    object Loading : GameState()
    data class Success(val data: GameDetail) : GameState()
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val repo: GamesRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val gameId = savedStateHandle.getStateFlow("id", 0).flatMapLatest {
        val gameData = repo.getGame(it)
        Log.e("tag", "data = $gameData")
        flowOf(
            GameState.Success(
                data = gameData
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = GameState.Loading
    )

//    private val _game = mutableStateOf<GameState>(GameState.Loading)
//    val game: State<GameState> = _game
//
//    init {
//        savedStateHandle.get<Int>("id")?.let { gameById(it) }
//    }
//
//    private fun gameById(gameId: Int){
//        viewModelScope.launch{
//            val result = repo.getGame(gameId)
//            _game.value = GameState.Success(data = result)
//        }
//    }
}