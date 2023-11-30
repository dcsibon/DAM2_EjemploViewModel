@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

import android.content.Context
import com.dam23_24.ejemploviewmodel.R

/**
 * Class representing a deck of playing cards.
 */
class DeckCards {

    companion object {
        // The list to store the deck of cards
        private val listaCartas: ArrayList<Card> = ArrayList()

        /**
         * Creates a new deck of cards and populates the list.
         *
         * @param context The application context for retrieving resources.
         */
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

        /**
         * Shuffles the deck of cards.
         */
        fun shuffle() {
            listaCartas.shuffle()
        }

        /**
         * Gets the last card from the deck.
         *
         * @return The last card in the deck.
         */
        fun getCard(): Card {
            val carta = listaCartas.last()
            listaCartas.removeLast()
            return carta
        }

        /**
         * Gets a face-down card.
         *
         * @return A face-down card.
         */
        fun getFaceDownCard(): Card {
            return(Card(CardsName.NINGUNA, Suits.NINGUNA, 0, 0, R.drawable.carta))
        }

        /**
         * Gets the total number of cards in the deck.
         *
         * @return The total number of cards in the deck.
         */
        fun getCardsTotal(): Int {
            return listaCartas.size
        }

        /**
         * Retrieves the resource ID of a card's drawable based on its name.
         *
         * @param context The application context for retrieving resources.
         * @param nombreCarta The name of the card.
         * @return The resource ID of the card's drawable.
         */
        private fun getIdDrawable(context: Context, nombreCarta: String) =
            context.resources.getIdentifier(
                    nombreCarta,
                    "drawable",
                    context.packageName)

    }

}