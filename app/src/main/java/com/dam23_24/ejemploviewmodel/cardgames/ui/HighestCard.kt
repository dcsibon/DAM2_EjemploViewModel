@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.activity.compose.BackHandler
import androidx.navigation.NavHostController

/**
 * Composable function representing the screen for the Highest Card game.
 *
 * @param navController The navigation controller used for navigating to different screens.
 * @param highestCardViewModel The ViewModel responsible for managing the Highest Card game logic.
 */
@Composable
fun HighestCardScreen(
    navController: NavHostController,
    highestCardViewModel: HighestCardViewModel
) {
    val imagenId: Int by highestCardViewModel.imageId.observeAsState(initial = 0)
    val descImagen: String by highestCardViewModel.imageDesc.observeAsState(initial = "")

    //Component to handle presses of the system back button
    BackHandler {
        highestCardViewModel.restart()
        navController.popBackStack()
    }

    HighestCardLayout(
        imagenId = imagenId,
        descImagen = descImagen,
        onClickRequestCard = {
            highestCardViewModel.getCard()
        },
        onClickReset = {
            highestCardViewModel.restart()
        },
        highestCardViewModel
    )

}

/**
 * Composable function representing the layout of the Highest Card game.
 *
 * @param imagenId The resource ID of the card image to be displayed.
 * @param descImagen The description of the card image.
 * @param onClickRequestCard Callback for the "Dame Carta" button click.
 * @param onClickReset Callback for the "Reiniciar" button click.
 * @param highestCardViewModel The ViewModel responsible for managing the Highest Card game logic.
 */
@Composable
fun HighestCardLayout(
    imagenId: Int,
    descImagen: String,
    onClickRequestCard: () -> Unit,
    onClickReset: () -> Unit,
    highestCardViewModel: HighestCardViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp, top = 100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .padding(horizontal = 40.dp)
        ) {
            // Display the number of cards remaining in the deck
            Text(
                text = "Cartas en la baraja: ${highestCardViewModel.getCardsTotal()}",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            // Display the card image
            Box(
                modifier = Modifier
                    .weight(4f)
            ) {
                Image(
                    painter = painterResource(id = imagenId),
                    contentDescription = descImagen,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            // Display the description of the card
            Text(
                text = descImagen,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)
            )
        }
        // Row for action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 30.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Button to draw a new card
            Button(
                enabled = highestCardViewModel.btnGetCardEnabled(),
                onClick = { onClickRequestCard() }
            ) {
                Text(text = "Dame Carta")
            }
            // Button to restart the game
            Button(
                enabled = highestCardViewModel.btnResetDeckOfCardsEnabled(),
                onClick = { onClickReset() }
            ) {
                Text(text = "Reiniciar")
            }
        }
    }
}


