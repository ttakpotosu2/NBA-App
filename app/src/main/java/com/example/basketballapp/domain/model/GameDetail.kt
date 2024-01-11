package com.example.basketballapp.domain.model

import com.example.basketballapp.data.model.Arena
import com.example.basketballapp.data.model.Date
import com.example.basketballapp.data.model.Periods
import com.example.basketballapp.data.model.Scores
import com.example.basketballapp.data.model.Status
import com.example.basketballapp.data.model.Teams

data class GameDetail(
    val arena: Arena?,
    val date: Date?,
    val id: Int,
    val leadChanges: Int,
    val league: String?,
    val nugget: String?,
    val officials: List<String>?,
    val periods: Periods?,
    val scores: Scores?,
    val season: Int,
    val stage: Int,
    val status: Status?,
    val teams: Teams?,
    val timesTied: Int
)