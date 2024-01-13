package com.example.basketballapp.presentation.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basketballapp.common.Constants
import com.example.basketballapp.data.repository.GamesRepo
import com.example.basketballapp.domain.use_cases.games.GetGameByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val repo: GamesRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _state = mutableStateOf(GameDetailState())
//    val state: State<GameDetailState> = _state
//
//    init {
//        savedStateHandle.get<Int>(Constants.PARAM_GAME_ID)?.let { getGameById(it) }
//    }
//
//    private fun getGameById(gameId: Int) {
//        getGameByIdUseCase(gameId).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = GameDetailState(game = result.data)
//                }
//                is Resource.Error -> {
//                    _state.value = GameDetailState(error = result.message ?: "An unexpected error occurred")
//                }
//                is Resource.Loading -> {
//                    _state.value = GameDetailState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
    

//
//    val state = savedStateHandle.getStateFlow("id", 0).flatMapLatest {
//        val gameData = getGameByIdUseCase(it)
//        val con1 = gameData.map { resource ->
//            resource.data }
//        Log.e("tag", "data = $gameData")
//        flowOf( GameDetailState(game = con1)
//        )
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(500L),
//        initialValue = GameDetailState()
//    )
//
//
//
    
    private val _game = mutableStateOf(GameDetailState(isLoading = true))
    val game: State<GameDetailState> = _game

    init {
        savedStateHandle.get<Int>(Constants.PARAM_GAME_ID)?.let { gameById(it) }
    }

    private fun gameById(gameId: Int){
        viewModelScope.launch{
            val result = repo.getGame(gameId)
            _game.value = GameDetailState(game = result)
        }
    }
}