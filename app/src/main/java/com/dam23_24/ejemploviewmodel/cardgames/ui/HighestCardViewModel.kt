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

/**
 * ViewModel for the Highest Card game functionality.
 * This ViewModel manages the game logic, card deck, and provides LiveData for the UI.
 *
 * @property context The application context used throughout the ViewModel.
 * @property _imageId Private MutableLiveData Integer representing card's image resource ID.
 * @property imageId Public LiveData Integer to observe the state of _imageId.
 * @property imageDesc LiveData String to describe card's image content.
 * @property _imageDesc Public LiveData String to observe the state of _imageDesc.
 * @property _card Private MutableLiveData Card object for the current card in play.
 *
 * @param application The application context used to initialize the ViewModel.
 */
class HighestCardViewModel(application: Application) : AndroidViewModel(application) {
// If we don't need to use the context inside, it's better to inherit from ViewModel
//class HighestCardViewModel : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _imageId = MutableLiveData<Int>()
    val imageId : LiveData<Int> = _imageId

    private val _imageDesc = MutableLiveData<String>()
    val imageDesc : LiveData<String> = _imageDesc

    private val _card = MutableLiveData<Card>()

    /**
     * Initializes the ViewModel by restarting the game.
     */
    init {
        restart()
    }

    /**
     * Gets a new card from the deck and updates LiveData values accordingly.
     */
    fun getCard() {
        _card.value = DeckCards.getCard()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = "${_card.value?.name} de ${_card.value?.suit}"
    }

    /**
     * Restarts the deck, shuffles it, and sets the initial face-down card.
     */
    fun restart() {
        DeckCards.newDeckOfCards(context)
        DeckCards.shuffle()
        _card.value = DeckCards.getFaceDownCard()
        _imageId.value = _card.value?.idDrawable
        _imageDesc.value = ""
    }

    /**
     * Checks if the "Get Card" button should be enabled based on the remaining cards in the deck.
     *
     * @return True if the button should be enabled, false otherwise.
     */
    fun btnGetCardEnabled() = DeckCards.getCardsTotal() >= 1

    /**
     * Gets the total number of cards remaining in the deck.
     *
     * @return The total number of cards remaining in the deck.
     */
    fun getCardsTotal() = DeckCards.getCardsTotal()

}