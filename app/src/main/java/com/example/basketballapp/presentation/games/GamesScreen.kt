package com.example.basketballapp.presentation.games

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
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
import com.valentinilk.shimmer.shimmer
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GamesScreen(
    navController: NavController,
    gamesViewModel: GamesViewModel = hiltViewModel(),
    toGameDetailScreen: (Int) -> Unit
) {
    val state = gamesViewModel.state.value
    
    var today = LocalDate.now()
   
    val startDate = LocalDate.of(2023, 1, 1)
    val endDate = LocalDate.of(2024, 12, 31)
    val dateList = generateDateList(startDate, endDate)
    
    val indexOfToday = dateList.indexOf(today)
    
    val pagerState = rememberPagerState(
        initialPage = indexOfToday,
        pageCount = {dateList.size}
    )
    
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            today = dateList[page]
            gamesViewModel.getGames(dateList[page].toString())
        }
    }
    
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
            DateRow(currentDate = today)
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) {
                Column (
                    verticalArrangement = Arrangement.Top
                ){
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        state.games?.let { games ->
                            items(games) { game ->
                                GamesListItem(games = game, onClick = {toGameDetailScreen(game.id)})
                                Spacer(modifier = Modifier.height(18.dp))
                            }
                        }
                    }
                }
            }
        }
    }
    if (state.isLoading){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .shimmer(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.White))
        }
    }
}

private fun generateDateList(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
    val dateList = mutableListOf<LocalDate>()
    var currentDate = startDate
    
    while (!currentDate.isAfter(endDate)) {
        dateList.add(currentDate)
        currentDate = currentDate.plusDays(1)
    }
    return dateList
}

@Composable
fun DateItem(date: LocalDate) {
    val dayOfWeek = date.dayOfWeek
    Text(text = "date is $dayOfWeek $date", color = Color.White)
}