package com.example.basketballapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.basketballapp.data.model.GamesResponse
import com.example.basketballapp.data.remote.NbaAppApi
import com.example.basketballapp.presentation.paging.GamesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesRepo @Inject constructor(
    private val api: NbaAppApi
) {
   fun getGames(season: String): Flow<PagingData<GamesResponse>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                GamesPagingSource(api, season)
            }
        ).flow
    }
}