package com.example.basketballapp.data.model.games

import com.example.basketballapp.domain.model.GameDetail

data class GamesResponse(
    val errors: List<Int>,
    val `get`: String,
    val parametersResponse: ParametersResponse,
    val response: List<GameDetailResponse>,
    val results: Int
) {
    fun toGames(): List<GameDetail> {
        return response.map { it.toGameDetail() }
    }
}