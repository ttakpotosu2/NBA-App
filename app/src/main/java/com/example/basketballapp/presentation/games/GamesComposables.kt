package com.example.basketballapp.presentation.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.basketballapp.data.model.GameDetail
import com.example.basketballapp.presentation.teams.TeamsViewModel
import com.example.basketballapp.presentation.ui.theme.Anton
import com.valentinilk.shimmer.shimmer
import java.time.LocalDate

@Composable
fun GamesListItem(
    games: GameDetail,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.fillMaxWidth()
			.clickable { onClick(games.id) },
		contentAlignment = Alignment.Center
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.clip(RoundedCornerShape(3.dp))
				.border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(3.dp))
				.background(Color(0xff191919))
				.padding(12.dp)
		) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.width(80.dp)
			) {
				SubcomposeAsyncImage(
					model = games.teams?.visiting?.logo,
					loading = { CircularProgressIndicator(color = Color.LightGray) },
					contentDescription = null,
					contentScale = ContentScale.Inside,
					modifier = Modifier.size(40.dp)
				)
				games.teams?.visiting?.nickname?.let {
					Text(
						text = it,
						style = TextStyle(
							color = Color.LightGray,
							fontFamily = Anton,
							fontSize = 16.sp
						)
					)
				}
			}
			Column(
				verticalArrangement = Arrangement.Top,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.weight(1f)
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically
				) {
					val home = games.scores?.home?.points
					val visiting = games.scores?.visitors?.points
					
					Text(
						text = visiting.toString(),
						style = TextStyle(
							color = Color.LightGray,
							fontFamily = Anton,
							fontSize = 20.sp
						)
					)
					if (visiting != null) {
						Icon(
							imageVector = if (visiting > home!!) {
								Icons.AutoMirrored.Filled.ArrowLeft
							} else if (visiting < home) {
								Icons.AutoMirrored.Filled.ArrowRight
							} else {
								Icons.Default.ArrowDropDown
							},
							modifier = Modifier
								.size(26.dp)
								.offset(y = 2.dp),
							contentDescription = null,
							tint = Color.LightGray,
						)
					}
					Text(
						text = home.toString(),
						style = TextStyle(
							color = Color.LightGray,
							fontFamily = Anton,
							fontSize = 20.sp
						)
					)
				}
				games.date?.start?.let {
					Text(
						text = it.substring(11, 19),
						style = TextStyle(
							color = Color.LightGray,
							fontFamily = Anton,
							fontSize = 14.sp
						)
					)
				}
			}
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.width(80.dp)
			) {
				SubcomposeAsyncImage(
					model = games.teams?.home?.logo,
					loading = {
						CircularProgressIndicator(color = Color.LightGray)
					},
					contentDescription = null,
					contentScale = ContentScale.Inside,
					modifier = Modifier.size(40.dp)
				)
				games.teams?.home?.nickname?.let {
					Text(
						text = it,
						style = TextStyle(
							color = Color.LightGray,
							fontFamily = Anton,
							fontSize = 16.sp
						)
					)
				}
			}
		}
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(6.dp),
			modifier = Modifier
				.offset(y = 35.dp)
				.clip(RoundedCornerShape(3.dp))
				.border(
					width = 1.dp,
					color = Color.DarkGray,
					shape = RoundedCornerShape(3.dp)
				)
				.background(Color.LightGray)
				.padding(vertical = 3.dp, horizontal = 6.dp)
		) {
			Box(
				modifier = Modifier
					.clip(CircleShape)
					.background(
						color = when (games.status?.long) {
							"In Play" -> {
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
			games.status?.let {
				Text(
					text = it.long.uppercase(),
					style = TextStyle(
						color = Color.Black
					)
				)
			}
		}
	}
}

@Composable
fun DateRow(
	modifier: Modifier = Modifier,
	currentDate: LocalDate
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(12.dp),
		modifier = modifier.padding(12.dp)
	) {
		val nextDay = currentDate.plusDays(1)
		val nextTwoDays = currentDate.plusDays(2)
		val nextThreeDays = currentDate.plusDays(3)
		val previousDay = currentDate.minusDays(1)
		val previousTwoDays = currentDate.minusDays(2)
		val previousThreeDays = currentDate.minusDays(3)
		
		val style = TextStyle(
			fontSize = 16.sp,
			color = Color.White
		)
		Text(text = currentDate.month.toString(), style = style)
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = previousThreeDays.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = previousThreeDays.dayOfMonth.toString(), style = style,
					modifier = modifier.padding(6.dp)
				)
			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = previousTwoDays.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = previousTwoDays.dayOfMonth.toString(), style = style,
					modifier = modifier.padding(6.dp)
				)
			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = previousDay.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = previousDay.dayOfMonth.toString(), style = style,
					modifier = modifier.padding(6.dp)
				)
			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = currentDate.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = currentDate.dayOfMonth.toString(),
					style = style.copy(color = Color.Black),
					modifier = modifier
						.clip(RoundedCornerShape(3.dp))
						.background(Color.White)
						.padding(6.dp)
				)
			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = nextDay.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = nextDay.dayOfMonth.toString(),
					style = style,
					modifier = modifier.padding(6.dp)
				)
			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = nextTwoDays.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = nextTwoDays.dayOfMonth.toString(), style = style,
					modifier = modifier.padding(6.dp)
				)
			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = modifier.weight(1f),
				verticalArrangement = Arrangement.spacedBy(6.dp)
			) {
				Text(text = nextThreeDays.dayOfWeek.toString().substring(0, 1), style = style)
				Text(
					text = nextThreeDays.dayOfMonth.toString(), style = style,
					modifier = modifier.padding(6.dp)
				)
			}
		}
	}
}

@Composable
fun GameDetailScreenShimmer() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.shimmer(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		CircularProgressIndicator(modifier = Modifier.size(50.dp))
	}
}

@Composable
fun GamesPerTeamTab(
	viewModel: TeamsViewModel,
	onItemClicked:(Int) -> Unit
) {
	val state = viewModel.state.value
	Column{
		Spacer(modifier = Modifier.height(16.dp))
		LazyColumn(
			modifier = Modifier.padding(horizontal = 12.dp),
			verticalArrangement = Arrangement.Top
		) {
			items(state.games) { game ->
				GamesListItem(games = game, onClick = {onItemClicked(game.id)})
				Spacer(modifier = Modifier.height(18.dp))
			}
		}
	}
}