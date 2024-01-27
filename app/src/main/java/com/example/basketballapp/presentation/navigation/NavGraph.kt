package com.example.basketballapp.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.basketballapp.common.Constants
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
        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            Log.e(
                "nav",
                destination.route.toString()
            )
        }
        composable(
            route = Screens.GamesScreen.route + "/{${Constants.PARAM_GAME_ID}}",
            arguments = listOf(navArgument(Constants.PARAM_GAME_ID){
                type = NavType.IntType
            })
        ){
            val id = requireNotNull(it.arguments).getInt(Constants.PARAM_GAME_ID)
            GamesScreen(
                toGameDetailScreen = {
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