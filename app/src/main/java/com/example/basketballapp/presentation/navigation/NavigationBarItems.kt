package com.example.basketballapp.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.example.basketballapp.R

data class NavigationBarItems(
	val icon: Int,
	val route: String
)

val navigationItems = listOf(
	NavigationBarItems(
		icon = R.drawable.ball_basket_basketball_svgrepo_com,
		route = Screens.GamesScreen.route
	),
	NavigationBarItems(
		icon = R.drawable.chart_square_svgrepo_com,
		route = Screens.StandingsScreen.route
	),
	NavigationBarItems(
		icon = R.drawable.basketball_men_sport_3_svgrepo_com,
		route = ""
	)
)

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
	this.clickable(
		indication = null,
		interactionSource = remember { MutableInteractionSource() }
	){
		onClick()
	}
}