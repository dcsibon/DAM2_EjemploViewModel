@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dam23_24.ejemploviewmodel.cardgames.data.DeckCards
import com.dam23_24.ejemploviewmodel.cardgames.data.Card

class HighestCardViewModel : ViewModel() {

    private val _imageId = MutableLiveData<Int>()
    val imageId : LiveData<Int> = _imageId

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc : LiveData<String> = _imageDesc

    private val _card = MutableLiveData<Card>()

    fun getCard() {
        _card.value = DeckCards.getCard()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = "${_card.value?.name} de ${_card.value?.suit}"
    }

    fun restart(context: Context) {
        DeckCards.newDeckOfCards(context)
        DeckCards.shuffle()
        _card.value = DeckCards.getFaceDownCard()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = ""
    }

    fun btnGetCardEnabled() = DeckCards.getCardsTotal() >= 1

    fun getCardsTotal() = DeckCards.getCardsTotal()

}

