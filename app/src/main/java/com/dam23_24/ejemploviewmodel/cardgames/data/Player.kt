@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

/**
 * Data class representing a player in a card game.
 *
 * @property apodo The nickname or alias of the player.
 * @property cardsList The list of cards held by the player.
 * @property points The total points accumulated by the player.
 */
data class Player(
    var apodo: String,
    val cardsList: ArrayList<Card> = ArrayList(),
    var points: Int
)