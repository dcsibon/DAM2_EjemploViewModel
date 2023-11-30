@file:Suppress("SpellCheckingInspection")


package com.dam23_24.ejemploviewmodel.cardgames.ui


import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
import com.dam23_24.ejemploviewmodel.cardgames.data.DeckCards
import com.dam23_24.ejemploviewmodel.cardgames.data.Card


//Si no tenemos que usar context dentro de nuestro ViewModel, lo heredaremos de ViewModel
//class HighestCardViewModel : ViewModel() {
class HighestCardViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _imageId = MutableLiveData<Int>()
    val imageId : LiveData<Int> = _imageId

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc : LiveData<String> = _imageDesc

    private val _card = MutableLiveData<Card>()

    init {
        restart()
    }

    fun getCard() {
        _card.value = DeckCards.getCard()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = "${_card.value?.name} de ${_card.value?.suit}"
    }

    fun restart() {
        DeckCards.newDeckOfCards(context)
        DeckCards.shuffle()
        _card.value = DeckCards.getFaceDownCard()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = ""
    }

    fun btnGetCardEnabled() = DeckCards.getCardsTotal() >= 1

    fun getCardsTotal() = DeckCards.getCardsTotal()

}

