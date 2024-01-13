package com.example.basketballapp.presentation.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object SplashScreen : Screens(route = "splash_screen")
    object GamesScreen : Screens(route = "games_screen")
    object GameDetailScreen : Screens(route = "game_detail_screen/{id}")
    
    object StandingsScreen: Screens(route = "standings_screen")
}