package com.example.basketballapp.presentation.players

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.AnchorType
import androidx.wear.compose.foundation.CurvedLayout
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.curvedText
import com.example.basketballapp.data.model.PlayerDetail
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
            Text(
                text = "${player.firstname} ${player.lastname}",
                color = Color.White,
                fontSize = 22.sp
            )
            Text(
                text = player.leagues.standard.pos,
                color = Color.White,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun PlayersPerTeamTab(
    viewModel: TeamsViewModel,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit
) {
    val state = viewModel.state.value
    Column(modifier = modifier) {
        Spacer(modifier = modifier.height(16.dp))
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

@Composable
fun PlayerDetails(
    modifier: Modifier = Modifier,
    strokeColor: Color = Color.LightGray,
    player: PlayerDetail
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
    ) {
        Canvas(modifier) {
            val height = size.height
            val width = size.width

            val jerseyPath = Path().apply {
                moveTo(x = width * 0.15f, y = 0f)
                lineTo(x = width * 0.15f, y = height * 0.15f)
                quadraticBezierTo(
                    x2 = 0f, y2 = height * 0.25f,
                    x1 = width * 0.15f, y1 = height * 0.25f,
                )
                lineTo(x = 0f, y = height)
                // add curve here?
                lineTo(x = width, y = height)
                // add curve here?
                lineTo(x = width, y = 0.25f * height)
                quadraticBezierTo(
                    x1 = width * 0.85f, y1 = height * 0.25f,
                    x2 = width * 0.85f, y2 = height * 0.15f
                )
                lineTo(x = width * 0.85f, y = 0f)
                lineTo(x = width * 0.7f, y = 0f)
                lineTo(x = width * 0.7f, y = height * 0.1f)
                quadraticBezierTo(
                    x1 = width * 0.7f, y1 = height * 0.15f,
                    x2 = width * 0.6f, y2 = height * 0.15f
                )
                lineTo(x = width * 0.4f, y = height * 0.15f)
                quadraticBezierTo(
                    x1 = width * 0.3f, y1 = height * 0.15f,
                    x2 = width * 0.3f, y2 = height * 0.1f
                )
                lineTo(x = width * 0.3f, y = 0f)
                close()
            }
            drawPath(
                path = jerseyPath,
                style = Stroke(width = 3f),
                color = strokeColor
            )
        }
        CurvedLayout(
            anchor = 270f,
            anchorType = AnchorType.Center,
            modifier = modifier.offset(y = (-50).dp)
        ){
            curvedText(
                text = player.lastname,
                style = CurvedTextStyle(
                    fontFamily = Anton,
                    fontSize = 50.sp,
                    color = Color.White
                )
            )
        }

        Column(
            modifier.offset(y = (200).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = player.leagues.standard.jersey.toString(),
                style = TextStyle(
                    fontFamily = Anton,
                    fontSize = 200.sp,
                    color = Color.White
                ),

            )
            Column (
                modifier = modifier
                    .padding(16.dp)
                    .border(
                        width = Dp.Hairline,
                        shape = RoundedCornerShape(6.dp),
                        color = Color.White
                    )
            ){
                Text(text = "Full Name: ${player.firstname} ${player.lastname}")
                Text(text = "Country of Birth: ${player.birth.country}")
                Text(text = "In the NBA Since ${player.nba.start}")
                Text(text = "Height: ${player.height.feets}ft ${player.height.inches} in (${player.height.meters} m")
                Text(text = "Weight: ${player.weight.pounds}lbs (${player.weight.kilograms})")
            }
        }
    }
}