package com.example.basketballapp.presentation.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.basketballapp.presentation.navigation.NavBar
import com.example.basketballapp.presentation.ui.theme.Anton

@Composable
fun GameStatScreen(
    navController: NavController,
    viewModel: GameStatsScreenViewModel = hiltViewModel(),
    //onNavItemClicked: () -> Unit
) {
    val state = viewModel.state.value

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
                .padding(16.dp)
                .border(
                    width = Dp.Hairline,
                    shape = RoundedCornerShape(6.dp),
                    color = Color.White
                )
        ) {
            if (state.stats.isNotEmpty()) {
                val visiting = state.stats.last()
                val home = state.stats.first()
                val visitingStat = visiting.statistics.last()
                val homeStat = home.statistics.first()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xff191919)),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Row(
                        modifier = Modifier
                            .height(500.dp)
                            .alpha(0.1f)
                            .padding(top = 100.dp)
                    ) {
                        SubcomposeAsyncImage(
                            model = visiting.team.logo,
                            loading = {
                                CircularProgressIndicator(color = Color.White)
                            },
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.CenterEnd,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )
                        SubcomposeAsyncImage(
                            model = home.team.logo,
                            loading = {
                                CircularProgressIndicator(color = Color.White)
                            },
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.CenterStart,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = visiting.team.nickname,
                                fontFamily = Anton,
                                fontSize = 40.sp,
                                color = Color.White
                            )
                            Text(
                                text = home.team.nickname,
                                fontFamily = Anton,
                                fontSize = 40.sp,
                                color = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color.White
                                )
                                .padding(horizontal = 22.dp)
                                .padding(bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "${visitingStat.points}",
                                fontFamily = Anton,
                                fontSize = 48.sp,
                                color = Color.White
                            )
                            Box(
                                modifier = Modifier
                                    .offset(y = 4.dp)
                                    .rotate(45f)
                                    .size(8.dp)
                                    .background(Color.White)
                            )
                            Text(
                                text = "${homeStat.points}",
                                fontSize = 48.sp,
                                color = Color.White,
                                fontFamily = Anton
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = Dp.Hairline,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            StatsPerGameItem(
                                visitingStat = visitingStat.fieldGoalsMade,
                                homeStat = homeStat.fieldGoalsMade,
                                statName = "Field Goals Made"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.fieldGoalAttempted,
                                homeStat = homeStat.fieldGoalAttempted,
                                statName = "Field Goals Attempted"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.freeThrowsMade,
                                homeStat = homeStat.freeThrowsMade,
                                statName = "Free Throws Made"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.freeThrowsAttempted,
                                homeStat = homeStat.freeThrowsAttempted,
                                statName = "Free Throws Attempted"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.threePointsMade,
                                homeStat = homeStat.threePointsMade,
                                statName = "Three Pointers Made"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.threePointsAttempted,
                                homeStat = homeStat.threePointsAttempted,
                                statName = "Three Pointers Attempted"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.fieldGoalAttempted,
                                homeStat = homeStat.fieldGoalAttempted,
                                statName = "Field Goals Attempted"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.offensiveRebounds,
                                homeStat = homeStat.offensiveRebounds,
                                statName = "Offensive Rebounds"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.defensiveRebounds,
                                homeStat = homeStat.defensiveRebounds,
                                statName = "Defensive Rebounds"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.totalRebounds,
                                homeStat = homeStat.totalRebounds,
                                statName = "Total Rebounds"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.assists,
                                homeStat = homeStat.assists,
                                statName = "Assists"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.personalFouls,
                                homeStat = homeStat.personalFouls,
                                statName = "Personal Fouls"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.steals,
                                homeStat = homeStat.steals,
                                statName = "Steals"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.turnovers,
                                homeStat = homeStat.turnovers,
                                statName = "Turn Overs"
                            )
                            StatsPerGameItem(
                                visitingStat = visitingStat.blocks,
                                homeStat = homeStat.blocks,
                                statName = "Blocks"
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
