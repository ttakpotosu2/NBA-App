package com.example.basketballapp.data.model.standings


import com.google.gson.annotations.SerializedName

data class Win(
    val away: Int,
    val home: Int,
    val lastTen: Int,
    val percentage: String,
    val total: Int
)