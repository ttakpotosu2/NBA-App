package com.example.basketballapp.presentation.standings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
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

        StandingsHeading("TEAMS")
        LazyColumn {
            itemsIndexed(state.standing) { index, standingDetail ->
                StandingsItem(
                    standings = standingDetail,
                    rank = "${index + 1}",
                    onStandingsItemClicked = { onStandingsItemClicked(standingDetail.team.id) }
                )
                if (index < state.standing.size) {
                    Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConferenceStandings(
    viewModel: StandingsViewModel,
    onStandingsItemClicked: (Int) -> Unit
) {
    val state = viewModel.state.value
    val conferenceState = viewModel.conferenceState

    LaunchedEffect(Unit) {
        viewModel.getConferenceStandings(
            season = state.season,
            league = state.league
        )
    }
    Column {
        LazyColumn {
            conferenceState.forEach { (conference, teams) ->
                stickyHeader {
                    StandingsHeading(heading = conference.uppercase())
                }
                itemsIndexed(teams) { index, standingDetail ->
                    StandingsItem(
                        standings = standingDetail,
                        rank = "${index + 1}",
                        onStandingsItemClicked = { onStandingsItemClicked(standingDetail.team.id) }
                    )
                    if (index < teams.size) Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DivisionsStandings(
    viewModel: StandingsViewModel,
    onStandingsItemClicked: (Int) -> Unit
) {
    val state = viewModel.state.value
    val divisionsState = viewModel.divisionState

    LaunchedEffect(Unit) {
        viewModel.getDivisionStandings(
            season = state.season,
            league = state.league
        )
    }
    Column {
        LazyColumn {
            divisionsState.forEach { (division, teams) ->
                stickyHeader {
                    StandingsHeading(heading = division.uppercase())
                }
                itemsIndexed(teams) { index, standingDetail ->
                    StandingsItem(
                        standings = standingDetail,
                        rank = "${index + 1}",
                        onStandingsItemClicked = { onStandingsItemClicked(standingDetail.team.id) }
                    )
                    if (index < teams.size) Divider()
                }
            }
        }
    }
}
