package com.example.basketballapp.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.basketballapp.data.model.GamesResponse
import com.example.basketballapp.data.remote.NbaAppApi

class GamesPagingSource(
    private val api: NbaAppApi,
    private val season: String
): PagingSource<Int, GamesResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GamesResponse> {
        val currentPage = params.key ?: 1
        return try {
            val games = api.getGames(season = season)
            val endOfPaginationReached = games.response.isEmpty()

            if (games.response.isNotEmpty()){
                LoadResult.Page(
                    data = games.response,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (error: Exception){
            LoadResult.Error(error)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GamesResponse>): Int? {
        return state.anchorPosition
    }
}