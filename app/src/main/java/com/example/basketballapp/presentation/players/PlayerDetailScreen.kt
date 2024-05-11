package com.example.basketballapp.presentation.players

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basketballapp.presentation.navigation.NavBar

@Composable
fun PlayerDetailScreen(
    navController: NavController,
    viewModel: PlayersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
//    val navigationBarItems = remember { NavigationBarItems.values() }
//   var selectedIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavBar(navController = navController)
        }
    ) { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff191919))
                .padding(paddingValue)
                .padding(12.dp)
        ) {
            state.players.firstOrNull()?.let { playerDetail ->
                PlayerDetails(
                    player = playerDetail,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}