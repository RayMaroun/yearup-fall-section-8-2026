package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * BlackJack (Workbook 4a, Section 5-1 Blackjack, Exercise 1, BONUS VERSION)
 *
 * The core game plus the three optional bonuses from the text, each marked
 * BONUS where it happens:
 *   1. any number of players: ask how many, keep the names and the hands
 *      in two lists that line up by index
 *   2. hit or stay: each player in turn sees their hand and chooses H to
 *      take another card or S to stop; a hand over 21 is bust and stops
 *   3. ace as 1 when 11 would bust: inside Hand.getValue()
 *
 * Card and Deck are the same files as the core solution.
 * With 2 players who both Stay, this plays exactly the required game.
 *
 * Example run (the deck is shuffled, so the cards differ every time):
 *   How many players? 2
 *   Enter player 1 name: Ann
 *   Enter player 2 name: Bob
 *
 *   Ann's turn
 *   Cards in Hand:
 *   K of Hearts
 *   7 of Clubs
 *   Value: 17
 *   Hit or Stay? (H/S) S
 *
 *   Bob's turn
 *   Cards in Hand:
 *   A of Spades
 *   4 of Diamonds
 *   Value: 15
 *   Hit or Stay? (H/S) H
 *   Cards in Hand:
 *   A of Spades
 *   4 of Diamonds
 *   9 of Hearts
 *   Value: 14
 *   Hit or Stay? (H/S) S
 *
 *   Final hands:
 *   Ann: 17
 *   Bob: 14
 *   Ann wins!
 */
public class BlackJack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // BONUS 1: how many players, then one name and one hand per player.
        // Two lists that line up: names.get(i) plays hands.get(i)
        System.out.print("How many players? ");
        int playerCount = Integer.parseInt(scanner.nextLine().trim());

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Hand> hands = new ArrayList<>();

        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Enter player " + i + " name: ");
            names.add(scanner.nextLine());
            hands.add(new Hand());
        }

        Deck deck = new Deck();
        deck.shuffle();

        // deck.deal() hands back the top card, each hand stores two
        for (Hand hand : hands) {
            hand.deal(deck.deal());
            hand.deal(deck.deal());
        }

        // BONUS 2: hit or stay, one player at a time
        for (int i = 0; i < hands.size(); i++) {
            Hand hand = hands.get(i);
            System.out.println();
            System.out.println(names.get(i) + "'s turn");

            boolean playing = true;
            while (playing) {
                hand.print();
                int value = hand.getValue();
                System.out.println("Value: " + value);

                if (value > 21) {                                   // bust: the turn ends by itself
                    System.out.println("Bust!");
                    playing = false;
                } else {
                    System.out.print("Hit or Stay? (H/S) ");
                    String choice = scanner.nextLine().trim().toUpperCase();

                    if (choice.equals("H")) {
                        hand.deal(deck.deal());                     // one more card, then show the hand again
                    } else {
                        playing = false;                            // anything else counts as Stay
                    }
                }
            }
        }

        // Final scores: the best value at 21 or under wins, a shared best is a tie
        System.out.println();
        System.out.println("Final hands:");

        int bestScore = 0;
        for (int i = 0; i < hands.size(); i++) {
            int score = hands.get(i).getValue();
            System.out.println(names.get(i) + ": " + score + (score > 21 ? " (bust)" : ""));

            if (score <= 21 && score > bestScore) {
                bestScore = score;
            }
        }

        if (bestScore == 0) {
            System.out.println("Everyone went bust: nobody wins!");
        } else {
            // Collect every player who reached the best score (a tie has more than one)
            ArrayList<String> winners = new ArrayList<>();
            for (int i = 0; i < hands.size(); i++) {
                if (hands.get(i).getValue() == bestScore) {
                    winners.add(names.get(i));
                }
            }

            if (winners.size() == 1) {
                System.out.println(winners.get(0) + " wins!");
            } else {
                System.out.println("It's a tie between " + String.join(" and ", winners) + "!");
            }
        }

        scanner.close();
    }
}
