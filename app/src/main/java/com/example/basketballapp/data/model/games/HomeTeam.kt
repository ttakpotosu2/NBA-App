package com.example.basketballapp.data.model.games

import kotlinx.serialization.Serializable

@Serializable
data class HomeTeam(
    val code: String,
    val id: Int,
    val logo: String,
    val name: String,
    val nickname: String
)