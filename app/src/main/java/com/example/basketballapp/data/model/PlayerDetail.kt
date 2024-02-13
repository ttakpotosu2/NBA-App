package com.example.basketballapp.data.model

import com.example.basketballapp.data.model.players.Birth
import com.example.basketballapp.data.model.players.Height
import com.example.basketballapp.data.model.players.Leagues
import com.example.basketballapp.data.model.players.Nba
import com.example.basketballapp.data.model.players.Weight

data class PlayerDetail(
    val affiliation: String?,
    val birth: Birth,
    val college: String?,
    val firstname: String,
    val height: Height,
    val id: Int,
    val lastname: String,
    val leagues: Leagues,
    val nba: Nba,
    val weight: Weight
)