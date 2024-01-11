package com.example.basketballapp.data.model

import com.example.basketballapp.domain.model.Games

data class GamesResponse(
    val errors: List<Int>,
    val `get`: String,
    val parametersResponse: ParametersResponse,
    val response: List<GameDetailResponse>,
    val results: Int
) {
    fun toGames(): Games {
        return Games(
            response.map { it.toGameDetail() }
        )
    }
}