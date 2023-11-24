@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

class Player(var apodo: String) {

    private val cardsList: ArrayList<Card> = ArrayList()

    private fun requestNewCard() {
        cardsList.add(DeckCards.getCard())
    }

    fun getCardsList() : ArrayList<Card> {
        return cardsList
    }

    fun getLastCard(new: Boolean = false): Card? {
        if (new) {
            requestNewCard()
        }
        return if (cardsList.isEmpty())
            null
        else cardsList.last()
    }

    fun calcPoints(): Int {
        var puntos = 0
        for (carta in cardsList) {
            puntos += carta.minPoints
        }
        puntos = calcPointsAses(puntos)
        return puntos
    }

    private fun calcPointsAses(puntos: Int): Int {
        var puntosAjustados = puntos
        for (carta in cardsList) {
            if (carta.minPoints != carta.maxPoints &&
                (puntosAjustados - carta.minPoints + carta.maxPoints) <= 21) {
                puntosAjustados -= carta.minPoints
                puntosAjustados += carta.maxPoints
            }
        }
        return puntosAjustados
    }

}