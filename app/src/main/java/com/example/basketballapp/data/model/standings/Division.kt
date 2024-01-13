package com.example.basketballapp.data.model.standings


import com.google.gson.annotations.SerializedName

data class Division(
    val gamesBehind: String,
    val loss: Int,
    val name: String,
    val rank: Int,
    val win: Int
)