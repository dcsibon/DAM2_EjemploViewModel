@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dam23_24.ejemploviewmodel.cardgames.data.Card
import com.dam23_24.ejemploviewmodel.cardgames.data.DeckCards
import com.dam23_24.ejemploviewmodel.cardgames.data.Player
import java.util.ArrayList

/**
 * ViewModel for the Blackjack game.
 *
 * This class manages the game logic and user interface interaction.
 *
 * @property _showConfigPlayersDialog Private LiveData Boolean to show the player configuration dialog.
 * @property showConfigPlayersDialog Public LiveData Boolean to observe the state of _showConfigPlayersDialog.
 * @property _showFinishGameDialog Private LiveData Boolean to show the game finish dialog.
 * @property showFinishGameDialog Public LiveData Boolean to observe the state of _showFinishGameDialog.
 * @property _showBtnAccept Private LiveData Boolean to enable/disable the accept button in player configuration.
 * @property showBtnAccept Public LiveData Boolean to observe the state of _showBtnAccept.
 * @property _player1 Private LiveData Player to store information about player 1.
 * @property _player2 Public LiveData Player to store information about player 2.
 * @property _playerShift Private LiveData Integer to track the player's turn.
 * @property playerShift Public LiveData Integer to observe the player's turn.
 * @property _nickNamePlayer1 Private LiveData String to store the nickname of player 1.
 * @property nickNamePlayer1 Public LiveData String to observe the nickname of player 1.
 * @property _nickNamePlayer2 Private LiveData String to store the nickname of player 2.
 * @property nickNamePlayer2 Public LiveData String to observe the nickname of player 2.
 * @property _standPlayer1 Private LiveData Boolean to track the stand state of player 1.
 * @property standPlayer1 Public LiveData Boolean to observe the state of _standPlayer1.
 * @property _standPlayer2 Private LiveData Boolean to track the stand state of player 2.
 * @property standPlayer2 Public LiveData Boolean to observe the state of _standPlayer2.
 * @property _refreshPlayerCards Private LiveData Boolean to force the update of player cards in BlackJack Screen.
 * @property refreshPlayerCards Public LiveData Boolean to observe the state of _refreshPlayerCards.
 *
 * @param application The application context used to initialize the ViewModel.
 */
class BlackJackViewModel(application: Application) : AndroidViewModel(application) {
// If we don't need to use the context inside, it's better to inherit from ViewModel
//class BlackJackViewModel : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _showConfigPlayersDialog = MutableLiveData<Boolean>()
    val showConfigPlayersDialog: LiveData<Boolean> = _showConfigPlayersDialog

    private val _showFinishGameDialog = MutableLiveData<Boolean>()
    val showFinishGameDialog: LiveData<Boolean> = _showFinishGameDialog

    private val _showBtnAccept = MutableLiveData<Boolean>()
    val showBtnAccept: LiveData<Boolean> = _showBtnAccept

    private val _player1 = MutableLiveData<Player>()
    private val _player2 = MutableLiveData<Player>()

    private val _playerShift = MutableLiveData<Int>()
    val playerShift: LiveData<Int> = _playerShift

    private val _nickNamePlayer1 = MutableLiveData<String>()
    val nickNamePlayer1: LiveData<String> = _nickNamePlayer1

    private val _nickNamePlayer2 = MutableLiveData<String>()
    val nickNamePlayer2: LiveData<String> = _nickNamePlayer2

    private val _standPlayer1 = MutableLiveData<Boolean>()
    val standPlayer1: LiveData<Boolean> = _standPlayer1

    private val _standPlayer2 = MutableLiveData<Boolean>()
    val standPlayer2: LiveData<Boolean> = _standPlayer2

    private val _refreshPlayerCards = MutableLiveData<Boolean>()
    val refreshPlayerCards: LiveData<Boolean> = _refreshPlayerCards

    init {
        newDeckOfCards()
    }

    /**
     * Initializes a new deck of cards for the game.
     */
    private fun newDeckOfCards() {
        DeckCards.newDeckOfCards(context)
        DeckCards.shuffle()
    }

    /**
     * Handles the dismissal of the player configuration dialog.
     */
    fun onDismissConfigDialog() {
        _showConfigPlayersDialog.value = true
    }

    /**
     * Handles the click on the buttons that close dialogs.
     */
    fun onClickCloseDialog() {
        _showConfigPlayersDialog.value = false
    }

    /**
     * Handles changes in the nickname of a player.
     *
     * @param playerId The ID of the player whose nickname is changing (1 or 2).
     * @param nickName The new nickname for the player.
     */
    fun onNickNameChange(playerId: Int, nickName: String) {
        if (playerId == 1) {
            _nickNamePlayer1.value = nickName
        }
        else {
            _nickNamePlayer2.value = nickName
        }
        _showBtnAccept.value = !(_nickNamePlayer1.value.isNullOrEmpty() || _nickNamePlayer2.value.isNullOrEmpty())
    }

    /**
     * Handles a player's decision to stand or not.
     *
     * @param playerId The ID of the player making the decision (1 or 2).
     * @param stand If true, the player stands; if false, the player does not stand.
     * @param changePlayer If true, run updateShift() to change user turn.
     */
    fun playerStand(playerId: Int, stand: Boolean, changePlayer: Boolean = false) {
        if (playerId == 1) {
            _standPlayer1.value = stand
        } else {
            _standPlayer2.value = stand
        }

        //Verify if game has finished...
        _showFinishGameDialog.value = (_standPlayer1.value == true && _standPlayer2.value == true)

        if (changePlayer) {
            updateShift()
        }
    }

    /**
     * Changes player turn.
     */
    private fun updateShift() {
        if (_playerShift.value == 1) {
            _playerShift.value = 2
        }
        else{
            _playerShift.value = 1
        }
    }

    /**
     * Forces the update of player cards in the View.
     * (trick to force recomposing component LazyRow in BlackJack.kt)
     */
    private fun forceRefreshPlayersCards() {
        if (_refreshPlayerCards.value == null) {
            _refreshPlayerCards.value = false
        } else {
            _refreshPlayerCards.value = !_refreshPlayerCards.value!!
        }
    }

    /**
     * Initiates a new game by creating new players and resetting info.
     */
    fun newGame() {
        _player1.value = Player(_nickNamePlayer1.value!!, ArrayList(), points = 0)
        _player2.value = Player(_nickNamePlayer2.value!!, ArrayList(), points = 0)
        resetGame()
    }

    /**
     * Reset deck of cards and player's info to start a new game
     *
     * @param resetPlayers Boolean indicating if reset info players is necessary
     * (default value is false)
     */
    fun resetGame(resetPlayers: Boolean = false) {
        if (resetPlayers) {
            _player1.value!!.cardsList.clear()
            _player1.value!!.points = 0
            _player2.value!!.cardsList.clear()
            _player2.value!!.points = 0
        }
        _standPlayer1.value = false
        _standPlayer2.value = false
        _playerShift.value = 1
        newDeckOfCards()
        requestNewCard(1)
        requestNewCard(2)
    }

    /**
     * Determines the winner of the game based on players' points.
     *
     * @return A string indicating the winner or a tie.
     */
    fun getWinner() : String {
        return if (_player1.value!!.points <= 21 && _player1.value!!.points > _player2.value!!.points) {
            "¡¡Gana el jugador ${_player1.value!!.apodo}!!"
        } else if (_player2.value!!.points <= 21) {
            "¡¡Gana el jugador ${_player2.value!!.apodo}!!"
        } else {
            "¡Empate!"
        }
    }

    /**
     * Generates a description of a player, including their nickname and points.
     *
     * @param playerId The ID of the player (1 or 2).
     * @return A string containing the player's description.
     */
    fun getPlayerDesc(playerId: Int) : String {
        return if (playerId == 1) {
            "${_player1.value?.apodo ?: "Jugador 1"} - ${_player1.value?.points ?: 0} puntos"
        } else {
            "${_player2.value?.apodo ?: "Jugador 2"} - ${_player2.value?.points ?: 0} puntos"
        }
    }

    /**
     * Retrieves the list of cards held by a player.
     *
     * @param playerId The ID of the player (1 or 2).
     * @return An ArrayList of Card objects representing the player's cards.
     */
    fun getPlayerCards(playerId: Int) : ArrayList<Card> {
        return if (playerId == 1) {
            _player1.value!!.cardsList
        } else {
            _player2.value!!.cardsList
        }
    }

    /**
     * Requests a new card from the deck for the specified player.
     *
     * @param playerId The ID of the player (1 or 2) requesting a new card.
     */
    fun requestNewCard(playerId: Int) {
        val player = if (playerId == 1) _player1.value!! else _player2.value!!

        player.cardsList.add(DeckCards.getCard())
        calcPoints(player)

        //Automatic stand if points of player 1 is >= 21 points
        playerStand(playerId, player.points >= 21)

        //Verify if game has finished...
        _showFinishGameDialog.value = (_standPlayer1.value == true && _standPlayer2.value == true)

        forceRefreshPlayersCards()

        if (
            (_playerShift.value == 1 && _standPlayer2.value == false) ||
            (_playerShift.value == 2 && _standPlayer1.value == false)
            )
        {
            updateShift()
        }
    }

    /**
     * Calculates the total points for a player based on their cards.
     *
     * @param player The Player object for whom points are calculated.
     */
    private fun calcPoints(player: Player) {
        player.points = 0
        for (card in player.cardsList) {
            player.points += card.minPoints
        }
        calcPointsAses(player)
    }

    /**
     * Adjusts the points of a player to account for aces.
     *
     * @param player The Player object for whom points are adjusted.
     */
    private fun calcPointsAses(player: Player) {
        for (card in player.cardsList) {
            if (card.minPoints != card.maxPoints &&
                (player.points - card.minPoints + card.maxPoints) <= 21) {
                player.points -= card.minPoints
                player.points += card.maxPoints
            }
        }
    }

    /**
     * Restart properties as new game
     */
    fun onFinishGameClose(){
        _showConfigPlayersDialog.value = true
        _showFinishGameDialog.value = false
        _nickNamePlayer1.value = ""
        _nickNamePlayer2.value = ""
        _showBtnAccept.value = false
    }
}