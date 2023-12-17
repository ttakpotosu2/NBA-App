package com.example.basketballapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.basketballapp.presentation.common.VerticalGrid
import com.example.basketballapp.presentation.viewModels.GameState
import com.example.basketballapp.presentation.viewModels.GameDetailViewModel
import com.valentinilk.shimmer.shimmer

@Composable
fun GameDetailScreen(
   viewModel: GameDetailViewModel = hiltViewModel()
) {
    val game by viewModel.gameId.collectAsState()
   //val game = viewModel.game.value

    when(game){
        is GameState.Loading -> {
            CircularProgressIndicator(color = Color.White)
        }
        is GameState.Success -> {
//            val visitingTeam = (game as GameState.Success).data.teams.visiting
//            val homeTeam = (game as GameState.Success).data.teams.home
            Log.e("UI", "before game images is called")
            val visitingTeamLogo = rememberAsyncImagePainter(
                model = (game as GameState.Success).data.teams?.visiting?.logo
            )
            val homeTeamLogo = rememberAsyncImagePainter(
                model = (game as GameState.Success).data.teams?.home?.logo
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff191919))
                    .padding(24.dp)
                    .border(
                        width = Dp.Hairline,
                        shape = RoundedCornerShape(6.dp),
                        color = Color.White
                    )
            ) {
                Row {
                    Column {
                        Log.e("UI", "before game data is called")
                        Image(painter = visitingTeamLogo, contentDescription = (game as GameState.Success).data.teams?.visiting?.nickname)
                        (game as GameState.Success).data.teams?.visiting?.let { Text(text = it.code) }
                        Log.e("UI", "after game data is called")
                    }
                    Column {
                        Image(painter = homeTeamLogo, contentDescription = (game as GameState.Success).data.teams?.home?.nickname)
                        (game as GameState.Success).data.teams?.home?.let { Text(text = it.code) }
                    }
                }
                Text(text = "${(game as GameState.Success).data.scores?.visitors?.points} - ${(game as GameState.Success).data.scores?.home?.points}")
                VerticalGrid(
                    columns = 4
                ) {
                    val scoresPerQtr = (game as GameState.Success).data.scores?.visitors?.lineScore
                    scoresPerQtr?.forEach { score ->
                        Text(text = score)
                    }
                }
            }
        }
    }
}

@Composable
fun GameDetailScreenShimmer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .shimmer(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.Red)
        )
    }
}