package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;   // Collections.shuffle() mixes a list for us

/**
 * Deck (Workbook 4a, Section 5-1 Blackjack, Exercise 1)
 *
 * The 52 cards, built in the constructor by pairing every suit with every
 * value (two nested loops: 4 x 13). shuffle() mixes them, deal() takes
 * the top card off the list, so the deck shrinks as the game goes on.
 */
public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8",
                "9", "10", "J", "Q", "K", "A"};

        // these loops create all the cards in the deck
        // and add them to the ArrayList
        for (String suit : suits) {
            for (String value : values) {
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }

    // No need to write a shuffle by hand: the Collections class has one
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        // deal the top card (if there are any cards left)
        // remove(0) takes the card out of the list and hands it back
        if (cards.size() > 0) {
            Card card = cards.remove(0);
            return card;
        } else {
            return null;
        }
    }

    public int getSize() {
        return cards.size();
    }
}
