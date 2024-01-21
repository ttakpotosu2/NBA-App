package com.example.basketballapp.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.basketballapp.presentation.games.GameDetailScreen
import com.example.basketballapp.presentation.games.GamesScreen
import com.example.basketballapp.presentation.standings.StandingsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.GamesScreen.route
    ) {
        navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.e(
                "nav",
                destination.route.toString()
            )
        }
        composable(route = Screens.GamesScreen.route){
            GamesScreen(
                toGameDetailScreen = { id ->
                    navHostController.navigate(Screens.GameDetailScreen.route + "$id")
                },
                navController = navHostController
            )
        }
        composable(route = Screens.GameDetailScreen.route){
            GameDetailScreen(navHostController)
        }
        composable(route = Screens.StandingsScreen.route){
            StandingsScreen(navHostController)
        }
    }
}