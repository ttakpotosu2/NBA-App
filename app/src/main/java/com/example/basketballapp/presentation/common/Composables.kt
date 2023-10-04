package com.example.basketballapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.basketballapp.data.model.GamesResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.width(100.dp),
        value = text,
        onValueChange = { onTextChange(it) },
        placeholder = {
            Text(
                modifier = Modifier.alpha(0.5f), text = "Season", color = Color.White
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
        trailingIcon = {
            IconButton(
                onClick = {
                if (text.isNotEmpty()) { onTextChange("") } else { onCloseClicked() }
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun GamesCard(
    gamesResponse: GamesResponse, onItemClick: () -> Unit
) {
    val visitingTeamLogo = rememberAsyncImagePainter(model = gamesResponse.teams.visiting.logo)
    val homeTeamLogo = rememberAsyncImagePainter(model = gamesResponse.teams.home.logo)

    Card(modifier = Modifier.clickable { onItemClick() }) {
        Text(
            text = gamesResponse.date.start,
            modifier = Modifier.background(Color.LightGray).clip(RoundedCornerShape(3.dp))
        )
        Row {
            Image(
                painter = visitingTeamLogo,
                contentDescription = gamesResponse.teams.visiting.nickname,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = "vs")
            Image(
                painter = homeTeamLogo,
                contentDescription = gamesResponse.teams.home.nickname,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = "${gamesResponse.scores.visitors.points} - ${gamesResponse.scores.home.points}")
        Text(text = "${gamesResponse.arena.name}, ${gamesResponse.arena.city}")
        Text(text = gamesResponse.arena.country)
        Text(text = gamesResponse.date.start)
    }
}