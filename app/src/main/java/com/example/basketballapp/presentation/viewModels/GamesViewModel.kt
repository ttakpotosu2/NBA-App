package com.example.basketballapp.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.basketballapp.data.repositories.GamesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class GamesViewModel @Inject constructor(
    private val repo: GamesRepo,
): ViewModel() {

    private val _seasonQuery = mutableStateOf("2023")
    val seasonQuery = _seasonQuery

    fun updateSeason(season: String) {
        _seasonQuery.value = season
    }



    val games = snapshotFlow {
        _seasonQuery
    }.debounce(500L).flatMapLatest {
        repo.getGames(season = it.value).cachedIn(viewModelScope)
    }



}