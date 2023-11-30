@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

data class Player(
    var apodo: String,
    val cardsList: ArrayList<Card> = ArrayList(),
    var points: Int
)