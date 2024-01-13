package com.example.basketballapp.data.model.standings

import com.example.basketballapp.domain.model.Standings

data class StandingsResponse(
    val errors: List<Int>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<StandingDetailResponse>,
    val results: Int
){
 fun toStandings(): Standings{
     return Standings(
         response.map { it.toStandingDetail() }
     )
 }
}