@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

sealed class Routes(val route: String) {
    object CardGamesScreen : Routes("cardGamesScreen")
    object HighestCardScreen : Routes("highestCardScreen")
    object BlackJackScreen : Routes("blackJackScreen")
}