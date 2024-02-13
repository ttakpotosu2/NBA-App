package com.example.basketballapp.data.model.teams

import com.example.basketballapp.data.model.TeamDetail

data class TeamsResponse(
    val errors: List<Int>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<TeamDetailsResponse>,
    val results: Int
){
    fun toTeams(): List<TeamDetail> {
        return response.map { it.toTeamDetail() }
    }
}