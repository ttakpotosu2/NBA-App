package com.example.basketballapp.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basketballapp.R
import com.example.basketballapp.data.DummyData
import com.example.basketballapp.presentation.ui.theme.Anton

@Composable
fun StatsScreenDummy() {
    val dummy = DummyData()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff191919)),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            modifier = Modifier
                .height(500.dp)
                .alpha(0.1f)
                .padding(top = 100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.two),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                //alignment = Alignment.CenterEnd,TODO: Fix alignment
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.one),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                //alignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.DarkGray
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "game statistics",
                fontFamily = Anton,
                fontSize = 24.sp,
                color = Color.White
            )
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.DarkGray)
            Spacer(Modifier.height(18.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dummy.teams.visiting.nickname,
                        fontFamily = Anton,
                        fontSize = 40.sp,
                        color = Color.White
                    )

                    Text(
                        text = dummy.teams.home.nickname,
                        fontFamily = Anton,
                        fontSize = 40.sp,
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(4.dp),
                            color = Color.DarkGray
                        )
                        .padding(horizontal = 22.dp)
                        .padding(bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "${dummy.scores.visitors.points}",
                        fontFamily = Anton,
                        fontSize = 48.sp,
                        color = Color.White
                    )
                    Box(
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .rotate(45f)
                            .size(8.dp)
                            .background(Color.White)
                    )
                    Text(
                        text = "${dummy.scores.home.points}",
                        fontSize = 48.sp,
                        color = Color.White,
                        fontFamily = Anton
                    )
                }
            }
            Column {
                StatsItem()
                StatsItem()
                StatsItem()
            }
        }
    }
}

@Preview
@Composable
fun StatsDummy() {
    StatsScreenDummy()
}

@Composable
fun StatsItem(
    visitingStat: Int = 60,
    homeStat: Int = 51,
    statName: String = "Fast Break Point",
    visitingColor: Color = Color.Red,
    homeColor: Color = Color.Blue
) {
    val progress = visitingStat / (homeStat.toDouble() + visitingStat.toDouble())

    Log.e("progress", "$progress")

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
                trackColor = homeColor.copy(0.3f),
                color = visitingColor.copy(0.3f),
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(6.dp))
            )
            Text(
                text = statName,
                style = TextStyle(
                    color = Color.White,
                    //fontFamily = Anton,
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