package com.example.basketballapp.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

@Composable
fun NavBar(
	navController: NavController,
	modifier: Modifier = Modifier
) {
	var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

	AnimatedNavigationBar(
		selectedIndex = selectedIndex,
		modifier = Modifier
			.height(80.dp)
			.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
		cornerRadius = shapeCornerRadius(cornerRadius = 30.dp),
		ballAnimation = Parabolic(tween(300)),
		indentAnimation = Height(tween(300)),
		barColor = Color(0xff363535),
		ballColor = Color.Red
	) {
		navigationItems.forEachIndexed { index, navigationBarItems ->
			Box(
				modifier = modifier
					.fillMaxSize()
					.noRippleClickable {
						selectedIndex = index
						//navController.navigate(navigationBarItems.route)
					},
				contentAlignment = Alignment.Center
			) {
				Icon(
					modifier = modifier.size(26.dp),
					painter = painterResource(id = navigationBarItems.icon),
					contentDescription = null,
					tint = if (selectedIndex == index) Color.Red else Color.LightGray.copy(0.3f)
				)
			}
		}
	}
}