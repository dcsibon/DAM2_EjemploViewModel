@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

class Jugador(var apodo: String) {

    private val listaCartas: ArrayList<Carta> = ArrayList()

    private fun requestNewCard() {
        listaCartas.add(Baraja.dameCarta())
    }

    fun getLastCard(new: Boolean = false): Carta? {
        if (new) {
            requestNewCard()
        }
        return if (listaCartas.isEmpty())
            null
        else listaCartas.last()
    }

    fun calcPoints(): Int {
        var puntos = 0
        for (carta in listaCartas) {
            puntos += carta.puntosMin
        }
        puntos = calcPointsAses(puntos)
        return puntos
    }

    private fun calcPointsAses(puntos: Int): Int {
        var puntosAjustados = puntos
        for (carta in listaCartas) {
            if (carta.puntosMin != carta.puntosMax &&
                (puntosAjustados - carta.puntosMin + carta.puntosMax) <= 21) {
                puntosAjustados -= carta.puntosMin
                puntosAjustados += carta.puntosMax
            }
        }
        return puntosAjustados
    }

}