package com.example.basketballapp.presentation.navigation

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.example.basketballapp.presentation.statistics.GameStatScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.GamesScreen.route,
        enterTransition = {
            slideInHorizontally (
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            )
        }
    ) {
        val arguments = listOf(navArgument(Constants.PARAM_GAME_ID) { type = NavType.IntType })
        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            Log.i(
                "nav",
                destination.route.toString()
            )
        }
        //Games
        composable(
            route = Screens.GamesScreen.route
            ) {
            GamesScreen(
                toGameDetailScreen = {
                    navHostController.navigate(Screens.GameDetailScreen.navToGameDetailScreen(it))
                },
                navController = navHostController
            )
        }
        composable(
            route = Screens.GameDetailScreen.route,
            arguments = arguments
        ){
            GameDetailScreen(
                navController = navHostController,
                toGameStatsScreen = {
                    navHostController.navigate(Screens.GameStatsScreen.navToGameStatsScreen(it))
                },
                toTeamDetailScreen = {}
            )
        }
        //Standings
        composable(route = Screens.StandingsScreen.route){
            StandingsScreen(navHostController)
        }
        //Stats
        composable(
            route = Screens.GameStatsScreen.route,
            arguments = arguments
        ){
            GameStatScreen(navController = navHostController)
        }
    }
}