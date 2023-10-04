package com.example.basketballapp.presentation.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object SplashScreen : Screens(route = "splash_screen")
    object GamesScreen : Screens(route = "games_screen")
}

data class NavItems(
    val label: String,
    val route: String
)

