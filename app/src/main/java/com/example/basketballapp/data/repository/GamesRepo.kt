package com.example.basketballapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.basketballapp.data.model.games.GameDetailResponse
import com.example.basketballapp.data.remote.NbaAppApi
import com.example.basketballapp.domain.model.GameDetail
import com.example.basketballapp.presentation.paging.GamesPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GamesRepo @Inject constructor(
    private val api: NbaAppApi
) {
    fun getGames(season: String): Flow<PagingData<GameDetailResponse>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                GamesPagingSource(api, season)
            }
        ).flow.flowOn(Dispatchers.IO)
    }

    suspend fun getGame(id: Int): GameDetail {
        return api.getGame(id).toGameDetail()
    }
}