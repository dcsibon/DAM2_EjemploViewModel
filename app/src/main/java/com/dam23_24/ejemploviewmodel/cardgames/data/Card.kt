@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

import androidx.annotation.DrawableRes

/**
 * Data class representing a playing card.
 *
 * @property name The name or rank of the card (e.g., Ace, King, Queen).
 * @property suit The suit of the card (e.g., Hearts, Diamonds, Clubs, Spades).
 * @property minPoints The minimum points the card can have in a game.
 * @property maxPoints The maximum points the card can have in a game.
 * @property idDrawable The resource ID for the card's image drawable.
 */
data class Card(
    var name: CardsName,
    var suit: Suits,
    var minPoints: Int,
    var maxPoints: Int,
    @DrawableRes var idDrawable: Int
)