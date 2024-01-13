package com.example.basketballapp.presentation.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.basketballapp.domain.model.StandingDetail

@Composable
fun StandingsItem(
	standings: StandingDetail,
	rank: String
) {
	Row (
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.padding(12.dp)
	){
		Row (
			Modifier.weight(4f),
			horizontalArrangement = Arrangement.spacedBy(6.dp)
		){
			Text(text = rank, color = Color.White)
			SubcomposeAsyncImage(
				model = standings.team.logo,
				loading = {
					CircularProgressIndicator(color = Color.LightGray)
				},
				contentDescription = standings.team.nickname,
				contentScale = ContentScale.Inside,
				modifier = Modifier.size(20.dp)
			)
			Text(text = standings.team.code, color = Color.White)
		}
		Box(modifier = Modifier.weight(1f)){
			Text(text = "${standings.win.total}", color = Color.White)
		}
		Box(modifier = Modifier.weight(1f)) {
			Text(text = "${standings.loss.total}", color = Color.White)
		}
		Box (modifier = Modifier.weight(1.5f)){
			Text(text = standings.win.percentage, color = Color.White)
		}
		Box(modifier = Modifier.weight(1f)) {
			Text(text = "${standings.win.lastTen}-${standings.loss.lastTen}")
		}
		Box(modifier = Modifier.weight(1f)) {
			Text(text = if (standings.gamesBehind == null) "-" else "${standings.gamesBehind}")
		}
	}
}

@Composable
fun StandingsHeading() {
	Row (
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.LightGray)
			.height(50.dp)
			.padding(12.dp)
	){
		Box(Modifier.weight(4f)){ Text(text = "TEAMS", color = Color.Black) }
		Box(Modifier.weight(1f)){ Text(text = "W", color = Color.Black) }
		Box(Modifier.weight(1f)) { Text(text = "L", color = Color.Black) }
		Box (Modifier.weight(1.5f)){ Text(text = "PCT", color = Color.Black) }
		Box(Modifier.weight(1f)) { Text(text = "L10", color = Color.Black) }
		Box(Modifier.weight(1f)) { Text(text ="GB", color = Color.Black) }
	}
}