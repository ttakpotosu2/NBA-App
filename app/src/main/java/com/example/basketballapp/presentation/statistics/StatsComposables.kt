package com.example.basketballapp.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basketballapp.presentation.ui.theme.Anton

@Composable
fun StatsItem(
    visitingStat: Int,
    homeStat: Int,
    statName: String,
    visitingColor: Color = Color(0xff8B0202),
    homeColor: Color = Color(0xff182B6F)
) {
    val progress = visitingStat / (homeStat.toDouble() + visitingStat.toDouble())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = visitingStat.toString(),
            style = TextStyle(
                color = Color.White,
                fontFamily = Anton,
                fontSize = 16.sp
            ),
            modifier = Modifier.offset(y = (-2).dp)
        )
        Box(
            contentAlignment = Alignment.Center
        ) {
            LinearProgressIndicator(
                progress = progress.toFloat(),
                trackColor = homeColor,
                color = visitingColor,
                modifier = Modifier
                    .height(30.dp)
                    .clip(RoundedCornerShape(6.dp))

            )
            Text(
                text = statName,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp
                )
            )
        }
        Text(
            text = homeStat.toString(),
            style = TextStyle(
                color = Color.White,
                fontFamily = Anton,
                fontSize = 16.sp
            ),
            modifier = Modifier.offset(y = (-2).dp)
        )
    }
}