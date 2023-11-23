package com.dam23_24.ejemploviewmodel.cardgames.data

import androidx.lifecycle.LiveData

data class Carta(
    var nombre: Naipes,
    var palo: Palos,
    var puntosMin: Int,
    var puntosMax: Int,
    var idDrawable: Int
)