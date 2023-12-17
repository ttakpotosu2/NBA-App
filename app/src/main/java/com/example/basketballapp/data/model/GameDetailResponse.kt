package com.example.basketballapp.data.model

import com.example.basketballapp.domain.GameDetail

data class GameDetailResponse(
    val arena: Arena,
    val date: Date,
    val id: Int,
    val leadChanges: Int,
    val league: String,
    val nugget: String,
    val officials: List<String>,
    val periods: Periods,
    val scores: Scores,
    val season: Int,
    val stage: Int,
    val status: Status,
    val teams: Teams,
    val timesTied: Int
) {
    fun toGameDetail(): GameDetail {
        return GameDetail(
            arena,
            date,
            id,
            leadChanges,
            league,
            nugget,
            officials,
            periods,
            scores,
            season,
            stage,
            status,
            teams,
            timesTied
        )
    }
}