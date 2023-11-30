@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

/**
 * Sealed class representing the different screens/routes in the application.
 *
 * @property route The identifier for the route/screen.
 */
sealed class Routes(val route: String) {
    /**
     * Object representing the "Card Games" screen/route.
     */
    object CardGamesScreen : Routes("cardGamesScreen")

    /**
     * Object representing the "Highest Card" game screen/route.
     */
    object HighestCardScreen : Routes("highestCardScreen")

    /**
     * Object representing the "BlackJack" game screen/route.
     */
    object BlackJackScreen : Routes("blackJackScreen")
}