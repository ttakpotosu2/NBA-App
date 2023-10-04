package com.example.basketballapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.basketballapp.presentation.screens.GamesScreen
import com.example.basketballapp.presentation.screens.HomeScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.GamesScreen.route
    ) {
        composable(route = Screens.GamesScreen.route){
            GamesScreen()
        }
    }
}
