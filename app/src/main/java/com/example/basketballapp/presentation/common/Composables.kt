package com.example.basketballapp.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.basketballapp.data.model.games.GameDetailResponse
import com.example.basketballapp.presentation.ui.theme.Anton
import com.example.basketballapp.presentation.ui.theme.JomhuriaRegular

@Composable
fun GamesCard(
	gameDetailResponse: GameDetailResponse, onItemClick: () -> Unit
) {
	val visitingTeamLogo =
		rememberAsyncImagePainter(model = gameDetailResponse.teams?.visiting?.logo)
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
fun JerseyCanvas(
	modifier: Modifier = Modifier,
	jerseyColor: Color = Color.White,
	strokeColor: Color = Color.LightGray,
	strokeThickness: Float = 5f,
	playerName: String = "Player",
	jerseyNumber: String = "00"
) {
	Box(
		contentAlignment = Alignment.Center
	){
		Canvas(modifier = Modifier.fillMaxSize()) {
			val height = size.height
			val width = size.width
			
			val jerseyPath = Path().apply {
				moveTo(x = width * 0.15f, y = 0f)
				lineTo(x = width * 0.15f, y = height * 0.15f)
				quadraticBezierTo(
					x2 = 0f, y2 = height * 0.25f,
					x1 = width * .15f, y1 = height * 0.25f,
				)
				lineTo(x = 0f, y = height)
				// add curve here?
				lineTo(x = width, y = height)
				// add curve here?
				lineTo(x = width, y = 0.25f * height)
				quadraticBezierTo(
					x1 = width * .85f, y1 = height * 0.25f,
					x2 = width * .85f, y2 = height * 0.15f
				)
				lineTo(x = width * 0.85f, y = 0f)
				lineTo(x = width * 0.7f, y = 0f)
				lineTo(x = width * 0.7f, y = height * 0.1f)
				quadraticBezierTo(
					x1 = width * .7f, y1 = height * 0.15f,
					x2 = width * .6f,y2 = height * .15f
				)
				lineTo(x = width * 0.4f, y = height * 0.15f)
				quadraticBezierTo(
					x1 = width * .3f, y1 = height * .15f,
					x2 = width * .3f, y2 = height * .1f
				)
				lineTo(x = width * .3f, y = 0f)
				close()
			}
			drawPath(path = jerseyPath, style = Stroke(width = strokeThickness), color = strokeColor)
		}
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
		//	modifier = modifier.offset(y = (-200).dp)
		) {
			Text(text = playerName, style = TextStyle(
				fontFamily = Anton,
				fontSize = 100.sp
			))
			Text(text = jerseyNumber, style = TextStyle(
				fontFamily = Anton,
				fontSize = 200.sp
			))
		}
	}
}