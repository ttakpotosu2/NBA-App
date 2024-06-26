package com.example.basketballapp.presentation.navigation

import com.example.basketballapp.common.Constants

sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object SplashScreen : Screens(route = "splash_screen")
    object GamesScreen : Screens(route = "games_screen")
    object GameDetailScreen : Screens(route = "game_detail_screen/{${Constants.PARAM_GAME_ID}}"){
        fun navToGameDetailScreen(id: Int): String{ return "game_detail_screen/$id" }
    }
    object StandingsScreen: Screens(route = "standings_screen")
    object GameStatsScreen: Screens(route = "game_stats_screen/{${Constants.PARAM_GAME_ID}}"){
        fun navToGameStatsScreen(id: Int): String { return "game_stats_screen/$id" }
    }
    object TeamDetailScreen: Screens(route = "team_detail_screen/{${Constants.PARAM_TEAM_ID}}"){
        fun navToTeamDetailScreen(id: Int): String { return "team_detail_screen/$id"}
    }
    object PlayerDetailScreen: Screens(route = "player_detail_screen/{${Constants.PARAM_PLAYER_ID}}"){
        fun navToPlayerDetailScreen(id: Int): String { return "player_detail_screen/$id"}
    }
}