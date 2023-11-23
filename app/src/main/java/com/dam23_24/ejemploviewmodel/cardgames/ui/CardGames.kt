package com.dam23_24.ejemploviewmodel.cardgames.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dam23_24.ejemploviewmodel.cardgames.data.Routes.*

@Composable
fun CardGamesScreen(
    navController: NavHostController,
    cardGamesViewModel: CardGamesViewModel
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            modifier = Modifier.padding(bottom = 50.dp),
            onClick = { navController.navigate(HighestCardScreen.route) }
        ) {
            Text(text = "Carta más alta")
        }

        Button(
            onClick = { navController.navigate(BlackJackScreen.route) }
        ) {
            Text(text = "BlackJack")
        }

    }

}