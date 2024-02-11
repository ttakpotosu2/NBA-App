package com.example.basketballapp.presentation.players

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basketballapp.domain.model.PlayerDetail
import com.example.basketballapp.presentation.teams.TeamsViewModel
import com.example.basketballapp.presentation.ui.theme.Anton

@Composable
fun PlayerItem(
    player: PlayerDetail,
    onItemClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(player.id) }
            .border(
                width = Dp.Hairline,
                shape = RoundedCornerShape(6.dp),
                color = Color.White
            )
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = player.leagues.standard.jersey.toString(),
            color = Color.White,
            fontSize = 64.sp,
            fontFamily = Anton,
            modifier = Modifier.width(75.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(text = "${player.firstname} ${player.lastname}",
                color = Color.White,
                fontSize = 22.sp)
            Text(text = player.leagues.standard.pos,
                color = Color.White,
                fontSize = 22.sp)
        }
    }
}

@Composable
fun PlayersPerTeamTab(
    viewModel: TeamsViewModel,
    onItemClicked: (Int) -> Unit
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.Top
        ) {
            items(state.players) { player ->
                PlayerItem(player = player, onItemClicked = { onItemClicked(player.id) })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}