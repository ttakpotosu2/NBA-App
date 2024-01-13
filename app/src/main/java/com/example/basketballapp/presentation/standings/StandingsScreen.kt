package com.example.basketballapp.presentation.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basketballapp.presentation.navigation.NavBar
import com.example.basketballapp.presentation.ui.theme.Anton

@Composable
fun StandingsScreen(
	navController: NavController,
	standingsViewModel: StandingsViewModel = hiltViewModel()
) {
	val state = standingsViewModel.state.value
	
	Scaffold(
		bottomBar = { NavBar(navController) }
	) { paddingValues ->
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxSize()
				.background(Color(0xff191919))
				.padding(paddingValues)
				.padding(12.dp)
				.border(
					width = Dp.Hairline,
					shape = RoundedCornerShape(6.dp),
					color = Color.White
				)
		) {
			Text(
				text = "standings",
				fontFamily = Anton,
				fontSize = 24.sp,
				color = Color.White,
				modifier = Modifier.padding(vertical = 8.dp)
			)
			Divider(
				modifier = Modifier.fillMaxWidth(),
				thickness = Dp.Hairline,
				color = Color.White
			)
			StandingsHeading()
			val sortedStandings = state.standing?.response?.sortedByDescending { it.win.percentage }
			LazyColumn {
				sortedStandings?.let { standings ->
					items(standings) {standingDetail ->
						StandingsItem(
							standings = standingDetail,
							rank = "${sortedStandings.indexOf(standingDetail) + 1}")
						Divider()
					}
				}
			}
		}
	}
}