package com.example.basketballapp.presentation.games

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.basketballapp.presentation.common.NavigationBarItems
import com.example.basketballapp.presentation.ui.theme.Anton
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.mrerror.singleRowCalendar.SingleRowCalendar
import java.util.Date


@Composable
fun GamesScreen(
    gamesViewModel: GamesViewModel = hiltViewModel(),
    toGameDetailScreen: (Int) -> Unit
) {
    val state = gamesViewModel.state.value
    val navigationBarItems = remember { NavigationBarItems.values() }
    var selectedIndex by remember { mutableIntStateOf(0) }
    var day by remember { mutableStateOf(Date()) }
    
    
    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                selectedIndex = selectedIndex,
                modifier = Modifier
                    .height(100.dp)
                    .padding(12.dp),
                cornerRadius = shapeCornerRadius(cornerRadius = 30.dp),
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = Color(0xff363535),
                ballColor = Color.Red
            ) {
                navigationBarItems.forEach { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { selectedIndex = item.ordinal },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selectedIndex == item.ordinal) Color.White else Color.LightGray.copy(
                                0.3f
                            )
                        )
                    }
                }
            }
        }
    ) {paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff191919))
                .padding(paddingValues)
                .padding(12.dp)
                .border(
                    width = Dp.Hairline,
                    shape = RoundedCornerShape(6.dp),
                    color = Color.White
                )
        ) {
            Text(
                text = "games",
                fontFamily = Anton,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dp.Hairline,
                color = Color.White
            )
            SingleRowCalendar(
                onSelectedDayChange = { day = it },
                
                )
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    modifier = Modifier.align(Alignment.Center),
//                    text = "Selected Day is ${SimpleDateFormat("dd-MM-yyyy", Locale.US).format(day)}"
//                )
//            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dp.Hairline,
                color = Color.White
            )
            Spacer(Modifier.height(18.dp))
            LazyColumn (
                modifier = Modifier.padding(horizontal = 12.dp)
            ){
                state.games?.let { games ->
                    items(games.response) {game ->
                        GamesListItem(games = game, onClick = {toGameDetailScreen(game.id)})
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}




/*
@Composable
fun GamesScreen(
    gamesViewModel: GamesViewModel = hiltViewModel(),
    toGameDetailScreen: (Int) -> Unit
) {
    val state = gamesViewModel.state.value
    val navigationBarItems = remember { NavigationBarItems.values() }
    var selectedIndex by remember { mutableIntStateOf(0) }
    
    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                selectedIndex = selectedIndex,
                modifier = Modifier
                    .height(100.dp)
                    .padding(12.dp),
                cornerRadius = shapeCornerRadius(cornerRadius = 30.dp),
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = Color(0xff363535),
                ballColor = Color.Red
            ) {
                navigationBarItems.forEach {item ->
                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { selectedIndex = item.ordinal }
                        ,
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selectedIndex == item.ordinal) Color.White else Color.LightGray.copy(0.3f)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff191919))
                .padding(paddingValues)
                .padding(12.dp)
                .border(
                    width = Dp.Hairline,
                    shape = RoundedCornerShape(6.dp),
                    color = Color.White
                )
        ) {
            Text(
                text = "games",
                fontFamily = Anton,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dp.Hairline,
                color = Color.White
            )
            Spacer(Modifier.height(18.dp))
            LazyColumn (
                modifier = Modifier.padding(horizontal = 12.dp)
            ){
                state.games?.let { games ->
                    items(games.response) {
                        GamesListItem(games = it, onClick = {})
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}
 */



/*
 Detail Screen
    //val season by gamesViewModel.seasonQuery
    val games = gamesViewModel.games.collectAsLazyPagingItems()

    val pagerState = rememberPagerState(
        pageCount = { games.itemCount }
    )
    val date = LocalDateTime.now().toString().substring(0, 10)
    val gameIndex = games

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
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "games",
            fontFamily = Anton,
            fontSize = 24.sp,
            color = Color.White
        )
        Divider(modifier = Modifier.fillMaxWidth(), thickness = Dp.Hairline, color = Color.White)
        Spacer(Modifier.height(18.dp))
//        SeasonInput(
//            text = season,
//            onTextChange = { gamesViewModel.updateSeason(season = it) },
//            onSearchClicked = { gamesViewModel.games },
//            onCloseClicked = {}
//        )
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                val game = games[page]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                ) {
                    if (game != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        game.date.start.let {
                            Text(
                                text = it.substring(0, 10),
                                modifier = Modifier
                                    .size(130.dp, 40.dp)
                                    .background(Color.White.copy(0.1f))
                                    .clip(RoundedCornerShape(6.dp))
                                    .padding(12.dp),
                                color = Color.White,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color.White.copy(0.25f))
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                SubcomposeAsyncImage(
                                    model = game.teams.visiting.logo,
                                    loading = {
                                        CircularProgressIndicator(color = Color.White)
                                    },
                                    contentDescription = null,
                                    contentScale = ContentScale.Inside,
                                    modifier = Modifier.size(100.dp)
                                )
                                Text(
                                    text = game.teams.visiting.code,
                                    fontFamily = Anton,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = "vs",
                                fontFamily = JomhuriaRegular,
                                fontSize = 80.sp,
                                color = Color.White
                            )
                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                SubcomposeAsyncImage(
                                    model = game.teams.home.logo,
                                    loading = {
                                        CircularProgressIndicator(color = Color.White)
                                    },
                                    contentDescription = null,
                                    contentScale = ContentScale.Inside,
                                    modifier = Modifier.size(100.dp)
                                )
                                Text(
                                    text = game.teams.home.code,
                                    fontFamily = Anton,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
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
                                text = "${game.scores.visitors.points}",
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
                                text = "${game.scores.home.points}",
                                fontSize = 48.sp,
                                color = Color.White,
                                fontFamily = Anton
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 50.dp)
                        ) {

                            val visitingScoresPerQtr = game.scores.visitors.lineScore
                            val homeScoresPerQtr = game.scores.home.lineScore

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(IntrinsicSize.Min)
                                    .border(
                                        width = 1.dp,
                                        shape = RoundedCornerShape(3.dp),
                                        color = Color.DarkGray
                                    )
                            ) {

                                for (i in 1..visitingScoresPerQtr.size) {
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = if (i > 4) "OT" else "Q$i", color = Color.Gray)
                                        Divider(thickness = 1.dp, color = Color.DarkGray)
                                        Text(
                                            text = visitingScoresPerQtr[i - 1],
                                            color = Color.White
                                        )
                                    }
                                    if (i < visitingScoresPerQtr.size) {
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(1.dp),
                                            thickness = 1.dp, color = Color.DarkGray
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(IntrinsicSize.Min)
                                    .border(
                                        width = 1.dp,
                                        shape = RoundedCornerShape(3.dp),
                                        color = Color.DarkGray
                                    )
                            ) {
                                for (i in 1..homeScoresPerQtr.size) {
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = if (i > 4) "OT" else "Q$i", color = Color.Gray)
                                        Divider(thickness = 1.dp, color = Color.DarkGray)
                                        Text(text = homeScoresPerQtr[i - 1], color = Color.White)
                                    }
                                    if (i < homeScoresPerQtr.size) {
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(1.dp),
                                            thickness = 1.dp, color = Color.DarkGray
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(3.dp)
                                )
                                .padding(vertical = 3.dp, horizontal = 6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        color = when (game.status.long) {
                                            "Live" -> {
                                                Color.Green
                                            }

                                            "Finished" -> {
                                                Color.Gray
                                            }

                                            else -> {
                                                Color.Red
                                            }
                                        }
                                    )
                                    .size(10.dp)
                            )
                            Text(
                                text = game.status.long,
                                style = TextStyle(
                                    color = Color.White
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(3.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "${game.arena.name}, ${game.arena.city}",
                                fontFamily = Anton,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Text(
                                text = game.date.start.substring(0,10),
                                fontFamily = Anton,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Text(
                                text = game.date.start.substring(11, 19) + "HRS",
                                fontFamily = Anton,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            if (game.officials.isNotEmpty()){
                                Text(
                                    text = "Officials",
                                    fontFamily = Anton,
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                                game.officials.forEach { official ->
                                    Text(
                                        text = official,
                                        fontFamily = Anton,
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            TextButton(
                                onClick = { /*TODO*/ },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.DarkGray
                                )
                            ) {
                                Text(
                                    text = "game stats",
                                    fontFamily = Anton,
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}
 */