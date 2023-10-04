package com.example.basketballapp.data.model

data class GamesResponse(
    val arena: Arena,
    val date: Date,
    val id: Int,
    val leadChanges: Int,
    val league: String,
    val nugget: Any,
    val officials: List<String>,
    val periods: Periods,
    val scores: Scores,
    val season: Int,
    val stage: Int,
    val status: Status,
    val teams: Teams,
    val timesTied: Int
)