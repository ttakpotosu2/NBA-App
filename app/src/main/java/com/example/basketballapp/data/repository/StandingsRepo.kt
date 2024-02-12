package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.remote.NbaAppApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StandingsRepo @Inject constructor(
    private val api: NbaAppApi
) {
    suspend fun getStandings(
        league: String,
        season: String
    ): StandingsResponse {
        return api.getStandings(league, season)
    }

    fun getConferenceStandings(
        league: String,
        season: String,
        conference: String
    ): Flow<StandingsResponse> = flow {
        val response = api.getConferenceStandings(league, season, conference)
        emit(response)
    }

    suspend fun getDivisionStandings(
        league: String,
        season: String,
        division: String
    ): StandingsResponse {
        return api.getDivisionStandings(league, season, division)
    }
}