package com.example.basketballapp.data.model

data class Games(
    val errors: List<Any>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<GamesResponse>,
    val results: Int
)