@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

import android.content.Context
import com.dam23_24.ejemploviewmodel.R

class DeckCards {

    companion object {
        private val listaCartas: ArrayList<Card> = ArrayList()

        fun newDeckOfCards(context: Context) {
            listaCartas.clear()
            var puntosMin: Int
            var puntosMax: Int

            for (palo in 1..4) {
                for (cont in 1..13) {
                    when (cont) {
                        1 -> {
                            puntosMin = 1
                            puntosMax = 11
                        }
                        11, 12, 13 -> {
                            puntosMin = 10
                            puntosMax = 10
                        }
                        else -> {
                            puntosMin = cont
                            puntosMax = cont
                        }
                    }

                    listaCartas.add(
                        Card(
                            CardsName.values()[cont],
                            Suits.values()[palo],
                            puntosMin,
                            puntosMax,
                            getIdDrawable(
                                context,
                                "${Suits.values()[palo].toString().lowercase()}_${cont}"
                            )
                        )
                    )
                }
            }
        }

        fun shuffle() {
            listaCartas.shuffle()
        }

        fun getCard(): Card {
            val carta = listaCartas.last()
            listaCartas.removeLast()
            return carta
        }

        fun getFaceDownCard(): Card {
            return(Card(CardsName.NINGUNA, Suits.NINGUNA, 0, 0, R.drawable.carta))
        }

        fun getCardsTotal(): Int {
            return listaCartas.size
        }

        private fun getIdDrawable(context: Context, nombreCarta: String) =
            context.resources.getIdentifier(
                    nombreCarta,
                    "drawable",
                    context.packageName)

    }

}