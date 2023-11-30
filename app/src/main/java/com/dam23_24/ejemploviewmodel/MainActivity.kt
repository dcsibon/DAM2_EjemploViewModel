package com.dam23_24.ejemploviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam23_24.ejemploviewmodel.cardgames.data.Routes.*
import com.dam23_24.ejemploviewmodel.cardgames.ui.BlackJackViewModel
import com.dam23_24.ejemploviewmodel.cardgames.ui.HighestCardViewModel
import com.dam23_24.ejemploviewmodel.cardgames.ui.CardGamesScreen
import com.dam23_24.ejemploviewmodel.cardgames.ui.HighestCardScreen
import com.dam23_24.ejemploviewmodel.cardgames.ui.BlackJackScreen
import com.dam23_24.ejemploviewmodel.ui.theme.EjemploViewModelTheme

class MainActivity : ComponentActivity() {

    private val highestCardViewModel: HighestCardViewModel by viewModels()
    private val blackJackViewModel: BlackJackViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val context = LocalContext.current
                    //highestCardViewModel.restart(context)

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = CardGamesScreen.route
                    ) {
                        composable(CardGamesScreen.route) {
                            CardGamesScreen(
                                navController = navController
                            )
                        }
                        composable(HighestCardScreen.route) {
                            HighestCardScreen(
                                navController = navController,
                                highestCardViewModel = highestCardViewModel
                            )
                        }
                        composable(BlackJackScreen.route) {
                            //blackJackViewModel.newDeckOfCards(context)
                            BlackJackScreen(
                                navController = navController,
                                blackJackViewModel = blackJackViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

