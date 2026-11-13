package com.pluralsight;

import java.util.Scanner;

/**
 * BlackJack (Workbook 4a, Section 5-1 Blackjack, Exercise 1)
 *
 * The required game, two players:
 *   1. ask each player's name
 *   2. build a deck and shuffle it
 *   3. deal two cards to each hand
 *   4. show both hands and declare the player closest to 21 without
 *      going over (a tie when both bust or both score the same)
 *
 * The optional bonuses (any number of players, hit or stay, ace as 1)
 * are not done here. The suit symbols and console colors from
 * WorkshopFiles (Symbols.java, ColorCodes.java) are optional too.
 *
 * Example run (the deck is shuffled, so the cards differ every time):
 *   Enter player 1 name: Ann
 *   Enter player 2 name: Bob
 *   Ann's hand:
 *   Cards in Hand:
 *   K of Hearts
 *   7 of Clubs
 *
 *   Bob's hand:
 *   Cards in Hand:
 *   A of Spades
 *   4 of Diamonds
 *   Ann wins!
 */
public class BlackJack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter player 1 name: ");
        String player1Name = scanner.nextLine();

        System.out.print("Enter player 2 name: ");
        String player2Name = scanner.nextLine();

        Deck deck = new Deck();
        deck.shuffle();

        // deck.deal() hands back the top card, the hand stores it
        Hand player1Hand = new Hand();
        player1Hand.deal(deck.deal());
        player1Hand.deal(deck.deal());

        Hand player2Hand = new Hand();
        player2Hand.deal(deck.deal());
        player2Hand.deal(deck.deal());

        System.out.println(player1Name + "'s hand:");
        player1Hand.print();

        System.out.println();

        System.out.println(player2Name + "'s hand:");
        player2Hand.print();

        int player1Score = player1Hand.getValue();
        int player2Score = player2Hand.getValue();

        // A player wins when they are at 21 or under AND either score higher
        // than the other player or the other player went over 21
        if (player1Score <= 21 && (player1Score > player2Score || player2Score > 21)) {
            System.out.println(player1Name + " wins!");
        } else if (player2Score <= 21 && (player2Score > player1Score || player1Score > 21)) {
            System.out.println(player2Name + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
        scanner.close();
    }
}
