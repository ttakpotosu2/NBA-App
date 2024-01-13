package com.example.basketballapp.presentation.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.mrerror.singleRowCalendar.SingleRowCalendar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun GamesScreen(
    navController: NavController,
    gamesViewModel: GamesViewModel = hiltViewModel(),
    toGameDetailScreen: (Int) -> Unit
) {
    val state = gamesViewModel.state.value
    var day by remember { mutableStateOf(Date()) }
    
    Scaffold(
        bottomBar = { NavBar(navController)}
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
                text = "games",
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
            SingleRowCalendar(
                onSelectedDayChange = {
                    day = it
                    val formatDate = SimpleDateFormat("yyyy-mm-dd", Locale.US).format(day)
                    state.currentDate = formatDate
                },
                dayNumTextColor = Color.LightGray,
                selectedDayBackgroundColor = Color.LightGray,
                headTextColor = Color.LightGray,
                iconsTintColor = Color.LightGray,
                selectedDayTextColor = Color.Black
                )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dp.Hairline,
                color = Color.White
            )
            Spacer(Modifier.height(18.dp))
            LazyColumn (
                modifier = Modifier.padding(horizontal = 12.dp)
            ){
                val formatDate = SimpleDateFormat("yyyy-mm-dd", Locale.US).format(day)
                state.currentDate = formatDate
                state.games?.let { games ->
                    items(games.response) {game ->
                        GamesListItem(games = game, onClick = {toGameDetailScreen(game.id)})
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
    if (state.isLoading){
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp)
        )
    }
}