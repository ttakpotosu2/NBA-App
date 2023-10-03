package com.example.basketballapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Statistic(
    val assists: Int,
    val biggestLead: Int,
    val blocks: Int,
    @SerializedName("defReb") val defensiveRebounds: Int,
    val fastBreakPoints: Int,
    @SerializedName("fga") val fieldGoalAttempted: Int,
    @SerializedName("fgm") val fieldGoalsMade: Int,
    @SerializedName("fgp") val fieldGoalPercentage: String,
    @SerializedName("fta") val freeThrowsAttempted: Int,
    @SerializedName("ftm") val freeThrowsMade: Int,
    @SerializedName("ftp") val freeThrowPercentage: String,
    val longestRun: Int,
    val min: String,
    @SerializedName("offReb") val offensiveRebounds: Int,
    @SerializedName("pFouls") val personalFouls: Int,
    val plusMinus: String,
    val points: Int,
    val pointsInPaint: Int,
    val pointsOffTurnovers: Int,
    val secondChancePoints: Int,
    val steals: Int,
    @SerializedName("totReb") val totalRebounds: Int,
    @SerializedName("tpa") val threePointsAttempted: Int,
    @SerializedName("tpm") val threePointsMade: Int,
    @SerializedName("tpp") val threePointsPercentage: String,
    val turnovers: Int
)