@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.dam23_24.ejemploviewmodel.cardgames.data.Routes
import com.dam23_24.ejemploviewmodel.R
import com.dam23_24.ejemploviewmodel.cardgames.data.Card

@Composable
fun BlackJackScreen(
    navController: NavHostController,
    blackJackViewModel: BlackJackViewModel
) {
    val showConfigPlayersDialog: Boolean by blackJackViewModel.showConfigPlayersDialog.observeAsState(initial = true)
    val showFinishGameDialog: Boolean by blackJackViewModel.showFinishGameDialog.observeAsState(initial = false)
    val refreshPlayerCards: Boolean by blackJackViewModel.refreshPlayerCards.observeAsState(initial = false)

    ConfigPlayersDialog(
        navController,
        blackJackViewModel,
        showConfigPlayersDialog
    ) { blackJackViewModel.onDismissConfigDialog() }

    FinishGameDialog(
        navController,
        blackJackViewModel,
        showFinishGameDialog
    )

    BlackJackLayout(
        blackJackViewModel,
        showConfigPlayersDialog,
        refreshPlayerCards
    )
}

@Composable
fun BlackJackLayout(
    blackJackViewModel: BlackJackViewModel,
    showConfigPlayersDialog: Boolean,
    refreshPlayerCards: Boolean
) {
    val standPlayer1: Boolean by blackJackViewModel.standPlayer1.observeAsState(initial = false)
    val standPlayer2: Boolean by blackJackViewModel.standPlayer2.observeAsState(initial = false)
    val playerShift: Int by blackJackViewModel.playerShift.observeAsState(initial = 1)

    if (!showConfigPlayersDialog) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Player1(
                    blackJackViewModel,
                    standPlayer1,
                    playerShift
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 10.dp)
            ) {
                //Cartas del jugador 1
                items(blackJackViewModel.getPlayerCards(1)) { card ->
                    ItemCardPlayer(card = card)
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 10.dp)
            ) {
                //Cartas del jugador 2
                items(blackJackViewModel.getPlayerCards(2)) { card ->
                    ItemCardPlayer(card = card)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Player2(
                    blackJackViewModel,
                    standPlayer2,
                    playerShift
                )
            }
        }
    }
}

@Composable
fun ItemCardPlayer(card: Card) {
    Image(
        modifier = Modifier
            .height(200.dp)
            .padding(vertical = 10.dp),
        painter = painterResource(id = card.idDrawable),
        contentDescription = "${card.name} de ${card.suit}"
    )
}

@Composable
fun Player1(
    blackJackViewModel: BlackJackViewModel,
    standPlayer1: Boolean,
    playerShift: Int
) {
    Row {
        Button(
            modifier = Modifier.padding(end = 4.dp),
            enabled = !standPlayer1 && (playerShift == 1),
            onClick = {
                blackJackViewModel.requestNewCard(1)
            }
        ) {
            Text(
                text = "Carta"
            )
        }
        Button(
            enabled = !standPlayer1 && (playerShift == 1),
            onClick = {
                blackJackViewModel.playerStand(1, stand = true, updateShift = true)
            }
        ) {
            Text(
                text = "Paso"
            )
        }
    }
    //Texto Jugador 1 y puntos
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = blackJackViewModel.getPlayerDesc(1)
    )
}

@Composable
fun Player2(
    blackJackViewModel: BlackJackViewModel,
    standPlayer2: Boolean,
    playerShift: Int
) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = blackJackViewModel.getPlayerDesc(2)
    )
    Row {
        Button(
            modifier = Modifier.padding(end = 4.dp),
            enabled = !standPlayer2 && (playerShift == 2),
            onClick = {
                blackJackViewModel.requestNewCard(2)
            }
        ) {
            Text(
                text = "Carta"
            )
        }
        Button(
            enabled = !standPlayer2 && (playerShift == 2),
            onClick = {
                blackJackViewModel.playerStand(2, stand = true, updateShift = true)
            }
        ) {
            Text(
                text = "Paso"
            )
        }
    }
}

@Composable
fun ConfigPlayersDialog(
    navController: NavHostController,
    blackJackViewModel: BlackJackViewModel,
    showConfigPlayersDialog: Boolean,
    onDissmiss: () -> Unit
) {
    val nickNamePlayer1: String by blackJackViewModel.nickNamePlayer1.observeAsState(initial = "")
    val nickNamePlayer2: String by blackJackViewModel.nickNamePlayer2.observeAsState(initial = "")

    if (showConfigPlayersDialog) {
        Dialog(onDismissRequest = { onDissmiss() }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(all = 24.dp)
                    .fillMaxWidth()
            ) {
                TitleDialog(text = "Configuración del juego")
                NickNameItem(
                    blackJackViewModel,
                    playerId = 1,
                    drawable = R.drawable.avatar1,
                    nickNamePlayer1
                )
                NickNameItem(
                    blackJackViewModel,
                    playerId = 2,
                    drawable = R.drawable.avatar2,
                    nickNamePlayer2
                )
                Buttons(
                    navController,
                    blackJackViewModel
                )
            }
        }
    }
}

@Composable
fun TitleDialog(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NickNameItem(
    blackJackViewModel: BlackJackViewModel,
    playerId: Int,
    @DrawableRes drawable: Int,
    nickNamePlayer: String
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Jugador $playerId",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = nickNamePlayer,
                onValueChange = { blackJackViewModel.onNickNameChange(playerId, it) },
                modifier = Modifier
                    .padding(8.dp),
                label = { Text(text = "Introduce tu apodo") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Blue
                )
            )
        }
    }
}

@Composable
fun Buttons(
    navController: NavHostController,
    blackJackViewModel: BlackJackViewModel
) {
    val showBtnAccept: Boolean by blackJackViewModel.showBtnAccept.observeAsState(initial = false)

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Button(
            enabled = showBtnAccept,
            onClick = {
                blackJackViewModel.newPlayers()
                blackJackViewModel.onClickConfigDialogAccept()
            }
        ) {
            Text(text = "Aceptar")
        }
        Button(
            modifier = Modifier.padding(start = 4.dp),
            onClick = { navController.navigate(Routes.CardGamesScreen.route) }
        ) {
            Text(text = "Cancelar")
        }
    }
}

@Composable
fun FinishGameDialog(
    navController: NavHostController,
    blackJackViewModel: BlackJackViewModel,
    showFinishGameDialog: Boolean
) {
    if (showFinishGameDialog) {
        Dialog(onDismissRequest = {
                navController.navigate(Routes.CardGamesScreen.route)
                blackJackViewModel.onFinishGameClose()
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .padding(all = 24.dp)
                    .fillMaxWidth()
            ) {
                TitleDialog(text = blackJackViewModel.getWinner())
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        navController.navigate(Routes.CardGamesScreen.route)
                        blackJackViewModel.onFinishGameClose()
                    }
                ) {
                    Text(text = "Cerrar")
                }
            }
        }
    }
}