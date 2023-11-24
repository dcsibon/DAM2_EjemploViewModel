@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
import com.dam23_24.ejemploviewmodel.cardgames.data.Routes

@Composable
fun BlackJackScreen(
    navController: NavHostController,
    blackJackViewModel: BlackJackViewModel
) {

    BlackJackLayout(
        navController,
        blackJackViewModel
        )

}

@Composable
fun BlackJackLayout(
    navController: NavHostController,
    blackJackViewModel: BlackJackViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
            )
        {
            Jugador1(
                navController,
                blackJackViewModel
            )
        }
        LazyRow(modifier = Modifier
            .weight(3f)) {
            //Cartas del jugador 1
            //items()
        }
        LazyRow(modifier = Modifier
            .weight(3f)) {
            //Cartas del jugador 2
            //items()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Jugador2(
                navController,
                blackJackViewModel
            )
        }
    }

}

@Composable
fun ItemCardPlayer() {

}

@Composable
fun Jugador1(navController: NavHostController,
             blackJackViewModel: BlackJackViewModel) {
    Row(){
        Button(
            modifier = Modifier.padding(end = 4.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Carta"
            )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Paso"
            )
        }
    }
    //Texto Jugador 1 y puntos
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = "Jugador 1 - 21 puntos"
    )
}

@Composable
fun Jugador2(navController: NavHostController,
             blackJackViewModel: BlackJackViewModel) {
    //Texto Jugador 2 y puntos
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = "Jugador 2 - 17 puntos"
    )
    Row(){
        Button(
            modifier = Modifier.padding(end = 4.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Carta"
            )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Paso"
            )
        }
    }
}
