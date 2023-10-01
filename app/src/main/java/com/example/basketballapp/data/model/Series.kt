package com.example.basketballapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Series(
    val loss: Int,
    val win: Int
)