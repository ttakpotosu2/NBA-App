package com.example.basketballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.basketballapp.presentation.navigation.NavGraph
import com.example.basketballapp.presentation.ui.theme.BasketBallAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            BasketBallAppTheme {
                NavGraph(navHostController = rememberNavController())
            }
//            GamesScreenDummy()
        }
    }
}