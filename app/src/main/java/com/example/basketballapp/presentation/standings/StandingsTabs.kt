package com.example.basketballapp.presentation.standings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect


@Composable
fun LeagueStandings(
    standingsViewModel: StandingsViewModel,
    onStandingsItemClicked: (Int) -> Unit
) {
    val state = standingsViewModel.state.value

    LaunchedEffect(Unit) {
        standingsViewModel.getStandings(
            season = state.season,
            league = state.league
        )
    }

    Column {
        val sortedStandings = state.standing.sortedByDescending { it.win.percentage }
        StandingsHeading("TEAMS")
        LazyColumn {
            items(sortedStandings) { standingDetail ->
                StandingsItem(
                    standings = standingDetail,
                    rank = "${sortedStandings.indexOf(standingDetail) + 1}",
                    onStandingsItemClicked = { onStandingsItemClicked(standingDetail.team.id) }
                )
                if(sortedStandings.indexOf(standingDetail) < sortedStandings.size){
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ConferenceStandings(
    viewModel: StandingsViewModel,
    onStandingsItemClicked: (Int) -> Unit
) {
    val state = viewModel.state.value
    val conference = listOf("east", "west")

    Column {
        conference.forEach { conf ->
            val sortedStandings = state.standing.sortedByDescending { it.win.percentage }

            LaunchedEffect(conf) {
                viewModel.getConferenceStandings(
                    season = state.season,
                    league = state.league,
                    conference = conf
                )
            }

            LazyColumn {
                item { StandingsHeading(heading = conf.uppercase()) }
                itemsIndexed(sortedStandings) { index, standingDetail ->
                    StandingsItem(
                        standings = standingDetail,
                        rank = "${sortedStandings.indexOf(standingDetail) + 1}",
                        onStandingsItemClicked = { onStandingsItemClicked(standingDetail.team.id) }
                    )
                    if (index < sortedStandings.size) {
                        Divider()
                    }
                }
            }
        }
    }
}