package com.example.basketballapp.data.repository

import com.example.basketballapp.data.model.standings.StandingsResponse
import com.example.basketballapp.data.remote.NbaAppApi
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

    suspend fun getConferenceStandings(
        league: String,
        season: String,
        conference: String
    ): StandingsResponse {
        return api.getConferenceStandings(league, season, conference)
    }

    suspend fun getDivisionStandings(
        league: String,
        season: String,
        division: String
    ): StandingsResponse {
        return api.getDivisionStandings(league, season, division)
    }
}