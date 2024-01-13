package com.example.basketballapp.data.model.games

import kotlinx.serialization.Serializable

@Serializable
data class Series(
    val loss: Int,
    val win: Int
)