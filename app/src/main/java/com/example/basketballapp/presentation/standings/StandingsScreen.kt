package com.example.basketballapp.presentation.standings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basketballapp.presentation.navigation.NavBar
import com.example.basketballapp.presentation.ui.theme.Anton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StandingsScreen(
    navController: NavController,
    standingsViewModel: StandingsViewModel = hiltViewModel(),
    toTeamDetailScreen: (Int) -> Unit
) {
    val state = standingsViewModel.state.value

    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("League", "Conference", "Division")
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            tabIndex = pagerState.currentPage
        }
    }

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
            //Standings
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = Color.Transparent
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = {
                            Text(
                                text = title,
                                style = TextStyle(
                                    color = if (tabIndex == index) {
                                        Color.White
                                    } else Color.White.copy(0.3f)
                                )
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { index ->
                when (index) {
                    0 -> {
                        LeagueStandings(
                            viewModel = standingsViewModel,
                            onStandingsItemClicked = {
                                toTeamDetailScreen(it)
                            }
                        )
                    }
                    1 -> {
                        ConferenceStandings(
                            viewModel = standingsViewModel,
                            onStandingsItemClicked = {
                                toTeamDetailScreen(it)
                            }
                        )
                    }
                    2 -> {
                        DivisionsStandings(
                            viewModel = standingsViewModel,
                            onStandingsItemClicked = {
                                toTeamDetailScreen(it)
                            }
                        )
                    }
                }
            }
        }
    }
}