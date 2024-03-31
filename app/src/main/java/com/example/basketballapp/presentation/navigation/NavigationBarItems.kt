package com.example.basketballapp.presentation.navigation

import com.example.basketballapp.R

enum class NavigationBarItems(
	val icon: Int,
	val route: String
){
	Games(
		icon = R.drawable.ball_basket_basketball_svgrepo_com,
		route = Screens.GamesScreen.route
	),
	Standings(
		icon = R.drawable.chart_square_svgrepo_com,
		route = Screens.StandingsScreen.route
	),
	Explore(
		icon = R.drawable.basketball_men_sport_3_svgrepo_com,
		route = ""
	)
}

