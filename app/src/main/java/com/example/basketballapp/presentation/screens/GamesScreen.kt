package com.example.basketballapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.basketballapp.presentation.common.GamesCard
import com.example.basketballapp.presentation.common.SeasonInput
import com.example.basketballapp.presentation.viewModels.GamesViewModel

@Composable
fun GamesScreen(
    gamesViewModel: GamesViewModel = hiltViewModel()
) {
    val season by gamesViewModel.season
    val games = gamesViewModel.games.collectAsLazyPagingItems()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(top = 30.dp, bottom = 30.dp)
            .padding(16.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = Color.White
            )
    ) {
        Text(
            text = "games",
            modifier = Modifier.padding(16.dp),
            fontSize = 48.sp,
            color = Color.White
        )
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SeasonInput(
                text = season,
                onTextChange = { gamesViewModel.updateSeason(season = it) },
                onSearchClicked = { gamesViewModel.games(season = it) },
                onCloseClicked = {}
            )
            LazyRow(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = games.itemCount,
                    key = games.itemKey(),
                    contentType = games.itemContentType()
                ) { index ->
                    val item = games[index]
                    if (item != null) {
                        GamesCard(
                            gamesResponse = item,
                            onItemClick = {}
                        )
                    }
                }
            }
        }
    }
}