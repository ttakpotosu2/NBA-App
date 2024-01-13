package com.example.basketballapp.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
	val navigationBarItems = remember { NavigationBarItems.values() }
	var selectedIndex by remember { mutableIntStateOf(0) }
	
	AnimatedNavigationBar(
		selectedIndex = selectedIndex,
		modifier = modifier.height(80.dp).padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
		cornerRadius = shapeCornerRadius(cornerRadius = 30.dp),
		ballAnimation = Parabolic(tween(300)),
		indentAnimation = Height(tween(300)),
		barColor = Color(0xff363535),
		ballColor = Color.Red
	) {
		navigationBarItems.forEach { item ->
			Box(
				modifier = modifier
					.fillMaxSize()
					.clickable(
						interactionSource = MutableInteractionSource(),
						indication = null
					) {
						navController.navigate(item.route)
						selectedIndex = item.ordinal
					},
				contentAlignment = Alignment.Center
			) {
				Icon(
					painter = painterResource(id = item.icon),
					contentDescription = null,
					modifier = modifier.size(26.dp),
					tint = if (selectedIndex == item.ordinal) Color.White else Color.LightGray.copy(
						0.3f
					)
				)
			}
		}
	}
}