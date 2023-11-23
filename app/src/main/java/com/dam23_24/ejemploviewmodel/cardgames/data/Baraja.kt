@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

import android.content.Context
import com.dam23_24.ejemploviewmodel.R

class Baraja {

    companion object {
        private val listaCartas: ArrayList<Carta> = ArrayList()

        fun crearBaraja(context: Context) {

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
                        Carta(
                            Naipes.values()[cont],
                            Palos.values()[palo],
                            puntosMin,
                            puntosMax,
                            loadImage(
                                context,
                                "${Palos.values()[palo].toString().lowercase()}_${cont}"
                            )
                        )
                    )
                }
            }
        }

        fun barajar() {
            listaCartas.shuffle()
        }

        fun dameCarta(): Carta {
            val carta = listaCartas.last()
            listaCartas.removeLast()
            return carta
        }

        fun dameCartaBocaAbajo(): Carta {
            return(Carta(Naipes.NINGUNA, Palos.NINGUNA, 0, 0, R.drawable.carta))
        }

        fun totalCartas(): Int {
            return listaCartas.size
        }

        private fun loadImage(context: Context, nombreCarta: String) =
            context.resources.getIdentifier(
                    nombreCarta,
                    "drawable",
                    context.packageName)

    }

}