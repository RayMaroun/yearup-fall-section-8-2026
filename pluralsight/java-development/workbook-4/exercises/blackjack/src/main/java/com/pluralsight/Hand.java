package com.pluralsight;

import java.util.ArrayList;

/**
 * Hand (Workbook 4a, Section 5-1 Blackjack, Exercise 1)
 *
 * The cards one player holds. The Deck deals a Card, the Hand stores it.
 * The Hand never looks inside a card itself: to score or print it flips
 * each card up, asks the card for its value, and flips it back down, so
 * the cards stay hidden between uses.
 */
public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // A Card is dealt to the Hand and the Hand is responsible
    // to store the card
    public void deal(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    // The Hand uses the methods of each card to determine
    // the value of each card - and adds up all values
    public int getValue() {
        int value = 0;

        for (Card card : cards) {
            card.flip(); // turn the card over to see the value
            value += card.getPointValue();
            card.flip(); // hide the card again
        }

        return value;
    }

    // Same flip / read / flip pattern, printing instead of adding
    public void print() {
        System.out.println("Cards in Hand:");
        for (Card card : cards) {
            card.flip();
            System.out.println(card.getValue() + " of " + card.getSuit());
            card.flip();
        }
    }
}
