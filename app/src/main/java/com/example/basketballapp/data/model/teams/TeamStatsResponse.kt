package com.example.basketballapp.data.model.teams

data class TeamStatsResponse(
    val errors: List<Int>,
    val `get`: String,
    val parameters: ParametersX,
    val teamStatsDetailsResponse: List<TeamStatsDetailsResponse>,
    val results: Int
){
    fun toTeamStats(): List<TeamStatsDetails> {
        return teamStatsDetailsResponse.map { it.toTeamStatsDetail() }
    }
}