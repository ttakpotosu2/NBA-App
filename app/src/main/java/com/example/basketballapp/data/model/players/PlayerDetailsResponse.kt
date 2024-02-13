package com.example.basketballapp.data.model.players

import com.example.basketballapp.data.model.PlayerDetail

data class PlayerDetailsResponse(
    val affiliation: String,
    val birth: Birth,
    val college: String,
    val firstname: String,
    val height: Height,
    val id: Int,
    val lastname: String,
    val leagues: Leagues,
    val nba: Nba,
    val weight: Weight
){
    fun toPlayerDetail(): PlayerDetail {
        return PlayerDetail(
            affiliation, birth, college, firstname, height, id, lastname, leagues, nba, weight
        )
    }
}