package com.example.basketballapp.presentation.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.basketballapp.common.Constants
import com.example.basketballapp.presentation.games.GameDetailScreen
import com.example.basketballapp.presentation.games.GamesScreen
import com.example.basketballapp.presentation.players.PlayerDetailScreen
import com.example.basketballapp.presentation.standings.StandingsScreen
import com.example.basketballapp.presentation.statistics.GameStatScreen
import com.example.basketballapp.presentation.teams.TeamDetailScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.GamesScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            Log.i("nav", destination.route.toString())
        }

        //Games
        composable(
            route = Screens.GamesScreen.route
        ) {
            GamesScreen(
                navController = navHostController,
                toGameDetailScreen = {
                    navHostController.navigate(Screens.GameDetailScreen.navToGameDetailScreen(it))
                }
            )
        }
        composable(
            route = Screens.GameDetailScreen.route,
            arguments = listOf(navArgument(Constants.PARAM_GAME_ID) { type = NavType.IntType })
        ) {
            GameDetailScreen(
                navController = navHostController,
                toGameStatsScreen = {
                    navHostController.navigate(Screens.GameStatsScreen.navToGameStatsScreen(it))
                },
                toTeamDetailScreen = {
                    navHostController.navigate(Screens.TeamDetailScreen.navToTeamDetailScreen(it))
                }
            )
        }

        //Standings
        composable(
            route = Screens.StandingsScreen.route
        ) {
            StandingsScreen(
                navController = navHostController,
                toTeamDetailScreen = {
                    navHostController.navigate(Screens.TeamDetailScreen.navToTeamDetailScreen(it))
                }
            )
        }

        //Stats
        composable(
            route = Screens.GameStatsScreen.route,
            arguments = listOf(navArgument(Constants.PARAM_GAME_ID) { type = NavType.IntType })
        ) {
            GameStatScreen(navController = navHostController)
        }

        //Teams
        composable(
            route = Screens.TeamDetailScreen.route,
            arguments = listOf(navArgument(Constants.PARAM_TEAM_ID) { type = NavType.IntType })
        ) {
            TeamDetailScreen(
                navController = navHostController,
                toGameDetailScreen = {
                    navHostController.navigate(Screens.GameDetailScreen.navToGameDetailScreen(it))
                },
                toPlayerDetailScreen = {
                    navHostController.navigate(Screens.PlayerDetailScreen.navToPlayerDetailScreen(it))
                }
            )
        }
        //Players
        composable(
            route = Screens.PlayerDetailScreen.route
        ){
            PlayerDetailScreen(navController = navHostController)
        }
    }
}