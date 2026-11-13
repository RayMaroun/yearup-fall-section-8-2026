package com.pluralsight;

/**
 * Card (Workbook 4a, Section 5-1 Blackjack, Exercise 1)
 *
 * One playing card: a suit, a face value ("A", "K", "10", ...) and whether
 * it is face up. A card that is face down hides everything: the getters
 * return "#" or 0, so the only way to read a card is to flip() it first.
 * That is the class design from the module, the hiding is on purpose.
 *
 * The backing variable is faceUp and the getter is isFaceUp(), the usual
 * name for a boolean getter.
 */
public class Card {
    private String suit;
    private String value;
    private boolean faceUp;

    // Every card starts face down
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.faceUp = false;
    }

    public String getSuit() {
        // only return the suit if the card is face up
        if (faceUp) {
            return suit;
        } else {
            return "#";
        }
    }

    public String getValue() {
        // only return the value if the card is face up
        if (faceUp) {
            // this is the string value of the card
            // i.e. A, K, Q, J, 10, 9 ...
            return value;
        } else {
            return "#";
        }
    }

    // Scoring from the exercise: A = 11, K Q J = 10, number cards = their number
    public int getPointValue() {
        // only return the value if the card is face up
        if (faceUp) {
            // determine point value and return it
            switch (value) {
                case "A":
                    return 11;
                case "K":
                case "Q":
                case "J":
                    return 10;
                default:
                    // "2" to "10": the number is in the text, so parse it
                    return Integer.parseInt(value);
            }
        } else {
            return 0;
        }
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    // Turn the card over: up becomes down, down becomes up
    public void flip() {
        faceUp = !faceUp;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
