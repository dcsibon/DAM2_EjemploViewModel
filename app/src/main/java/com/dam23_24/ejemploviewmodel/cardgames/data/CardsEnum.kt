@file:Suppress("SpellCheckingInspection")

package com.dam23_24.ejemploviewmodel.cardgames.data

/**
 * Enum class representing the suits of playing cards.
 *
 * @property NINGUNA Represents an undefined or placeholder suit.
 * @property CORAZONES Represents the Hearts suit.
 * @property DIAMANTES Represents the Diamonds suit.
 * @property TREBOLES Represents the Clubs suit.
 * @property PICAS Represents the Spades suit.
 */
enum class Suits {
    NINGUNA,
    CORAZONES,
    DIAMANTES,
    TREBOLES,
    PICAS
}

/**
 * Enum class representing the names or ranks of playing cards.
 *
 * @property NINGUNA Represents an undefined or placeholder card name.
 * @property AS Represents the Ace card.
 * @property DOS Represents the Two card.
 * @property TRES Represents the Three card.
 * @property CUATRO Represents the Four card.
 * @property CINCO Represents the Five card.
 * @property SEIS Represents the Six card.
 * @property SIETE Represents the Seven card.
 * @property OCHO Represents the Eight card.
 * @property NUEVE Represents the Nine card.
 * @property DIEZ Represents the Ten card.
 * @property VALET Represents the Jack (Valet) card.
 * @property DAME Represents the Queen (Dame) card.
 * @property ROI Represents the King (Roi) card.
 */
enum class CardsName {
    NINGUNA,
    AS,
    DOS,
    TRES,
    CUATRO,
    CINCO,
    SEIS,
    SIETE,
    OCHO,
    NUEVE,
    DIEZ,
    VALET,
    DAME,
    ROI
}