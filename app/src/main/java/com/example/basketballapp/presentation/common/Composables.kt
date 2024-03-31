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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.basketballapp.data.model.games.GameDetailResponse
import com.example.basketballapp.presentation.ui.theme.JomhuriaRegular

@Composable
fun GamesCard(
	gameDetailResponse: GameDetailResponse, onItemClick: () -> Unit
) {
	val visitingTeamLogo =
		rememberAsyncImagePainter(model = gameDetailResponse.teams.visiting.logo)
	val homeTeamLogo = rememberAsyncImagePainter(model = gameDetailResponse.teams.home.logo)
	
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
		gameDetailResponse.date.start.let {
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
				contentDescription = gameDetailResponse.teams.visiting.nickname,
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
				contentDescription = gameDetailResponse.teams.home.nickname,
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
				text = "${gameDetailResponse.scores.visitors.points} - ${gameDetailResponse.scores.home.points}",
				fontFamily = JomhuriaRegular,
				fontSize = 80.sp,
				color = Color.White
			)
		}
		
		// Spacer(modifier = Modifier.weight(1f))
		Text(
			text = "${gameDetailResponse.arena.name}, ${gameDetailResponse.arena.city}",
			fontFamily = JomhuriaRegular,
			fontSize = 40.sp,
			color = Color.White
		)
		//  Text(text = gamesResponse.arena.country ?: "To be Determined")
		Text(
			text = gameDetailResponse.date.start.substring(11, 16) + "HRS",
			fontFamily = JomhuriaRegular,
			fontSize = 40.sp,
			color = Color.White
		)
	}
}