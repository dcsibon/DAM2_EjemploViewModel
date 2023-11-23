@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.ui


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dam23_24.ejemploviewmodel.cardgames.data.Baraja
import com.dam23_24.ejemploviewmodel.cardgames.data.Carta

class HighestCardViewModel : ViewModel() {

    private val _imagenId = MutableLiveData<Int>()
    val imagenId : LiveData<Int> = _imagenId

    private val _descImagen = MutableLiveData<String>()
    val descImagen : LiveData<String> = _descImagen

    private val _carta = MutableLiveData<Carta>()
    val carta : LiveData<Carta> = _carta


    /*
    val carta = MutableLiveData<Carta>()

    // Método para actualizar la carta
    fun actualizarCarta(carta: Carta) {
        this.carta.value = carta
    }
    */

    fun dameCarta() {
        _carta.value = Baraja.dameCarta()
        _imagenId.value = carta.value?.idDrawable
        _descImagen.value = "${carta.value?.nombre} de ${carta.value?.palo}"
    }

    fun reiniciar(context: Context) {
        Baraja.crearBaraja(context)
        Baraja.barajar()
        _carta.value = Baraja.dameCartaBocaAbajo()
        _imagenId.value = carta.value?.idDrawable
        _descImagen.value = ""
    }

    fun botonDameCartaHabilitado() = Baraja.totalCartas() >= 1

    fun totalCartas() = Baraja.totalCartas()

}

