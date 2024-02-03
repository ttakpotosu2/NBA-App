package com.example.basketballapp.data.model.teams

import com.example.basketballapp.domain.model.TeamDetail

data class TeamDetailsResponse(
    val allStar: Boolean,
    val city: String,
    val code: String,
    val id: Int,
    val leagues: Leagues,
    val logo: String,
    val name: String,
    val nbaFranchise: Boolean,
    val nickname: String
) {
    fun toTeamDetail(): TeamDetail {
        return TeamDetail(
            allStar, city, code, id, leagues, logo, name, nbaFranchise, nickname
        )
    }
}