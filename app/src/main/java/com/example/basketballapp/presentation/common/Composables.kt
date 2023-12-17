package com.example.basketballapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.basketballapp.data.model.GameDetailResponse
import com.example.basketballapp.presentation.ui.theme.JomhuriaRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    OutlinedTextField(modifier = Modifier.width(100.dp),
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
        keyboardActions = KeyboardActions(onDone = { onSearchClicked(text) }),
        trailingIcon = {
            IconButton(onClick = {
                if (text.isNotEmpty()) {
                    onTextChange(text)
                } else {
                    onCloseClicked()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White
                )
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonMenu() {
    val seasons = listOf(
        "2023",
        "2022",
        "2021",
        "2020",
        "2019",
        "2018",
        "2017",
        "2016",
        "2015",
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedSeason by remember { mutableStateOf(seasons[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {expanded = !expanded}
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedSeason,
            onValueChange = {  },
            label = { Text("Season") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(

            ),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            seasons.forEach { seasonSelected ->
                DropdownMenuItem(
                    text = { selectedSeason },
                    onClick = {
                        selectedSeason = seasonSelected
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun GamesCard(
    gameDetailResponse: GameDetailResponse, onItemClick: () -> Unit
) {
    val visitingTeamLogo = rememberAsyncImagePainter(model = gameDetailResponse.teams?.visiting?.logo)
    val homeTeamLogo = rememberAsyncImagePainter(model = gameDetailResponse.teams?.home?.logo)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .width(300.dp)
            .fillMaxWidth()
            .clickable { onItemClick() }
         //   .border(width = 1.dp, Color.White)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        gameDetailResponse.date?.start?.let {
            Text(
                text = it.substring(0, 10),
                modifier = Modifier
                    .size(130.dp, 40.dp)
                    .background(Color.White.copy(0.1f))
                    .clip(RoundedCornerShape(6.dp))
                    .padding(12.dp),
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = visitingTeamLogo,
                contentDescription = gameDetailResponse.teams?.visiting?.nickname,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "vs", fontFamily = JomhuriaRegular, fontSize = 80.sp, color = Color.White
            )
            Image(
                painter = homeTeamLogo,
                contentDescription = gameDetailResponse.teams?.home?.nickname,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
       // Spacer(modifier = Modifier.weight(0.5f))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 40.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White.copy(.1f))
            )
            Text(
                text = "${gameDetailResponse.scores?.visitors?.points} - ${gameDetailResponse.scores?.home?.points}",
                fontFamily = JomhuriaRegular,
                fontSize = 80.sp,
                color = Color.White
            )
        }

        // Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${gameDetailResponse.arena?.name}, ${gameDetailResponse.arena?.city}",
            fontFamily = JomhuriaRegular,
            fontSize = 40.sp,
            color = Color.White
        )
        //  Text(text = gamesResponse.arena.country ?: "To be Determined")
        Text(
            text = (gameDetailResponse.date?.start?.substring(11, 16) ?: "00:00") + "HRS",
            fontFamily = JomhuriaRegular,
            fontSize = 40.sp,
            color = Color.White
        )
    }
}

@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        // Keep given height constraints, but set an exact width
        val itemConstraints = constraints.copy(
            minWidth = itemWidth,
            maxWidth = itemWidth
        )
        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(itemConstraints) }
        // Track each columns height so we can calculate the overall height
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight)
            .coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // Track the Y co-ord per column we have placed up to
            val columnY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.placeRelative(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
            }
        }
    }
}