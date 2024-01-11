package com.example.basketballapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.basketballapp.presentation.games.GameDetailScreen
import com.example.basketballapp.presentation.games.GamesScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.GamesScreen.route
    ) {
        composable(route = Screens.GamesScreen.route){
            GamesScreen(
                toGameDetailScreen = {
                    navHostController.navigate("game_detail_screen/${it}")
                }
            )
        }
        composable(
            route = Screens.GameDetailScreen.route,
            arguments = listOf(navArgument("id"){ defaultValue = 0 })
        ){
            GameDetailScreen()
        }
    }
}