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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun HighestCardScreen(
    navController: NavHostController,
    highestCardViewModel: HighestCardViewModel
) {
    val imagenId: Int by highestCardViewModel.imageId.observeAsState(initial = 0)
    val descImagen: String by highestCardViewModel.imageDesc.observeAsState(initial = "")
    /*
    var imagenId by rememberSaveable { mutableIntStateOf(R.drawable.carta) }
    var descImagen by rememberSaveable { mutableStateOf("") }
    */
    val context = LocalContext.current

    HighestCardLayout(
        imagenId = imagenId,
        descImagen = descImagen,
        onClickDameCarta = {
            highestCardViewModel.getCard()
        },
        onClickReiniciar = {
            highestCardViewModel.restart(context)
        },
        highestCardViewModel
    )

}

@Composable
fun HighestCardLayout(
    imagenId: Int,
    descImagen: String,
    onClickDameCarta: () -> Unit,
    onClickReiniciar: () -> Unit,
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
            Text(
                text = "Cartas en la baraja: ${highestCardViewModel.getCardsTotal()}",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
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
            Text(
                text = descImagen,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 30.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                enabled = highestCardViewModel.btnGetCardEnabled(),
                onClick = { onClickDameCarta() }
            ) {
                Text(text = "Dame Carta")
            }
            Button(onClick = { onClickReiniciar() }) {
                Text(text = "Reiniciar")
            }
        }
    }
}


