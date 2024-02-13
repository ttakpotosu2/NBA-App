package com.example.basketballapp.data.model

import com.example.basketballapp.data.model.teams.Leagues

data class TeamDetail(
    val allStar: Boolean,
    val city: String,
    val code: String,
    val id: Int,
    val leagues: Leagues,
    val logo: String,
    val name: String,
    val nbaFranchise: Boolean,
    val nickname: String
)
