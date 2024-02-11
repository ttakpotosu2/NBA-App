package com.example.basketballapp.data.model.standings

import com.example.basketballapp.domain.model.StandingDetail

data class StandingsResponse(
    val errors: List<Int>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<StandingDetailResponse>,
    val results: Int
) {
    fun toStandings(): List<StandingDetail> {
        return response.map { it.toStandingDetail() }
    }
}