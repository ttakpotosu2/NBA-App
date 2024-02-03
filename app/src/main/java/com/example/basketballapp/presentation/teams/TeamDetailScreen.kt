package com.example.basketballapp.presentation.teams

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.basketballapp.presentation.games.GamesPerTeamTab
import com.example.basketballapp.presentation.games.GamesPerTeamViewModel
import com.example.basketballapp.presentation.navigation.NavBar
import com.example.basketballapp.presentation.players.PlayersPerTeamTab
import com.example.basketballapp.presentation.players.PlayersViewModel
import com.example.basketballapp.presentation.ui.theme.Anton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamDetailScreen(
    navController: NavController,
    viewModel: TeamsViewModel = hiltViewModel(),
    gamesPerTeamViewModel: GamesPerTeamViewModel = hiltViewModel(),
    playersPerTeamViewModel: PlayersViewModel = hiltViewModel(),
    toGameDetailScreen: (Int) -> Unit
) {
    val state = viewModel.state.value

    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Games", "Players", "Standings", "Stats")
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
    ) { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff191919))
                .padding(paddingValue)
                .padding(16.dp)
                .border(
                    width = Dp.Hairline,
                    shape = RoundedCornerShape(6.dp),
                    color = Color.White
                )
        ) {
            if (state.team.isNotEmpty()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        SubcomposeAsyncImage(
                            model = state.team.first().logo,
                            loading = { CircularProgressIndicator(color = Color.White) },
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {}
                        )
                        Column {
                            Text(
                                text = state.team.first().name,
                                fontFamily = Anton,
                                fontSize = 30.sp,
                                color = Color.White

                            )
                            Text(
                                text = state.team.first().code,
                                fontFamily = Anton,
                                fontSize = 20.sp,
                                color = Color.White

                            )
                            Text(
                                text = "${state.team.first().leagues.standard.conference}ern Conference |" +
                                        " ${state.team.first().leagues.standard.division} Division",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                            //TODO: Add all star tag and franchise tag
                        }
                    }
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
                                GamesPerTeamTab(
                                    onItemClicked = { toGameDetailScreen(it) },
                                    viewModel = gamesPerTeamViewModel
                                )
                            }

                            1 -> {
                                PlayersPerTeamTab(
                                    viewModel = playersPerTeamViewModel,
                                    onItemClicked = {})
                            }

                            2 -> {

                            }

                            3 -> {

                            }
                        }
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}