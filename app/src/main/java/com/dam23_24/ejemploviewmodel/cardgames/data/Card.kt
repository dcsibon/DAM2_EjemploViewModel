@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

import androidx.annotation.DrawableRes

data class Card(
    var name: CardsName,
    var suit: Suits,
    var minPoints: Int,
    var maxPoints: Int,
    @DrawableRes var idDrawable: Int
)