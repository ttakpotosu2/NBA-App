package com.example.basketballapp.presentation.standings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember


@Composable
fun LeagueStandings(
    standingsViewModel: StandingsViewModel
) {
    val state = standingsViewModel.state.value
    Column {
        val sortedStandings = state.standing?.response?.sortedByDescending { it.win.percentage }
        StandingsHeading("TEAMS", onHeadingClicked = {})
        LazyColumn {
            sortedStandings?.let { standings ->
                items(standings) { standingDetail ->
                    StandingsItem(
                        standings = standingDetail,
                        rank = "${sortedStandings.indexOf(standingDetail) + 1}"
                    )
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ConferenceStandings(
    viewModel: ConferenceStandingsViewModel
) {
    val state = viewModel.state.value
    val conference = listOf("east", "west")
    val confIndex by remember { mutableIntStateOf(0) }

    val selectedConference = conference[confIndex]

    LaunchedEffect(selectedConference) {
        viewModel.getConferenceStandings(
            conference = conference[confIndex],
            season = state.season,
            league = state.league
        )
    }

    Column {
        Column {
            val sortedStandings = state.standing?.response?.sortedByDescending { it.win.percentage }

            StandingsHeading(
                heading = selectedConference.uppercase(),
                onHeadingClicked = {
                    viewModel.getConferenceStandings(
                        conference = conference[if (confIndex == 0) 1 else 0],
                        season = state.season,
                        league = state.league
                    )
                })
            LazyColumn {
                sortedStandings?.let { standings ->
                    items(standings) { standingDetail ->
                        StandingsItem(
                            standings = standingDetail,
                            rank = "${sortedStandings.indexOf(standingDetail) + 1}"
                        )
                        Divider()
                    }
                }
            }
        }
    }
}