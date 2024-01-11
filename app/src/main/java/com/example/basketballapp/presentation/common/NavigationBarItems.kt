package com.example.basketballapp.presentation.common

import com.example.basketballapp.R

enum class NavigationBarItems(
	val icon: Int,
	val route: String
){
	Games(
		icon = R.drawable.ball_basket_basketball_svgrepo_com,
		route = ""
	),
	Teams(
		icon = R.drawable.shield_bolt_svgrepo_com,
		route = ""
	),
	Players(
		icon = R.drawable.basketball_men_sport_3_svgrepo_com,
		route = ""
	),
	Stats(
		icon = R.drawable.chart_square_svgrepo_com,
		route = ""
	)
}

