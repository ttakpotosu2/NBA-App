package com.example.basketballapp.presentation.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.basketballapp.data.model.GamesResponse
import com.example.basketballapp.data.repositories.GamesRepo
import com.example.basketballapp.presentation.GamesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repo: GamesRepo,
): ViewModel() {

    private val _season = mutableStateOf("2023")
    val season = _season

    private val _games = MutableStateFlow<PagingData<GamesResponse>>(PagingData.empty())
    val games = _games

    fun games(season: String) {
        viewModelScope.launch {
            repo.getGames(season).cachedIn(viewModelScope).collect{
                _games.value = it
            }
        }
    }

    fun updateSeason(season: String) {
        _season.value = season
    }
}