@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui

import androidx.lifecycle.ViewModel
import com.dam23_24.ejemploviewmodel.cardgames.data.Card
import com.dam23_24.ejemploviewmodel.cardgames.data.Player
import java.util.ArrayList

class BlackJackViewModel : ViewModel() {

    private val _player1 = Player("Jugador 1")

    private val _player2 = Player("Jugador 2")

    /*
    private val _player2 = MutableLiveData<Jugador>()
    val player2 : LiveData<Jugador> = _player2
    */

    fun getCards(player: Int) : ArrayList<Card>{
        return if (player == 1) _player1.getCardsList() else _player2.getCardsList()
    }

}