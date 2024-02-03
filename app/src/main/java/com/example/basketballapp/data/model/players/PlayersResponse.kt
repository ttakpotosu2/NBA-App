package com.example.basketballapp.data.model.players

import com.example.basketballapp.domain.model.PlayerDetail

data class PlayersResponse(
    val errors: List<Any>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<PlayerDetailsResponse>,
    val results: Int
){
    fun toPlayers(): List<PlayerDetail> {
        return response.map { it.toPlayerDetail() }
    }
}