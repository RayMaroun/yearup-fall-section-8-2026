package com.pluralsight;

import java.util.Random;   // used by the BONUS version below
import java.util.Scanner;

/**
 * QuotesApp (Workbook 3a, Section 1-1 Handling Exceptions, Exercise 1)
 *
 * Stores 10 famous sayings in an array. The user picks one by number (1 to 10)
 * or types R for a random one, and the program prints it.
 *
 * The point of the exercise is the try/catch: a number outside 1 to 10, or text
 * that is not a number, throws an exception. Without try/catch the program
 * crashes; with it, we print a friendly message and keep going.
 *
 * The live code is the required part: one quote, with exception handling, no loop.
 *
 * Both BONUS parts are kept switched off inside main, right below it:
 *   - a loop that asks "Do you want to see another quote?"
 *   - R picks a random quote
 */
public class QuotesApp {
    public static void main(String[] args) {
        // 10 quotes in an array: positions 0 to 9, the user thinks in 1 to 10
        String[] quotes = {
                "All that glitters is not gold",
                "A stitch in time saves nine",
                "Actions speak louder than words",
                "Honesty is the best policy",
                "You can't have your cake and eat it too",
                "The early bird catches the worm",
                "An apple a day keeps the doctor away",
                "There's no place like home",
                "When in Rome, do as the Romans do",
                "You reap what you sow"
        };

        Scanner scanner = new Scanner(System.in);

        // Required part: one quote, with exception handling but no loop.
        try {
            System.out.println("Enter a number between 1-10 to display a specific quote.");
            System.out.print("Your choice: ");

            // nextInt throws InputMismatchException for text like "abc"
            int indexFromUser = scanner.nextInt();

            // - 1 turns the user's 1 to 10 into the array's 0 to 9;
            // 0 or 11 throws ArrayIndexOutOfBoundsException
            System.out.println(quotes[indexFromUser - 1]);

        } catch (Exception exception) {
            // Exception catches both problems above (bad number, out of range)
            System.out.println("Invalid input. Please enter a number between 1 and 10.");
            scanner.nextLine();  // Clear the buffer.
        }

        // BONUS version: keep going until the user answers N, and R picks a random quote.
        // To try it, comment out the try/catch above and remove the comment markers here.
        /*
        boolean continueLoop = true;
        // One Random object for the whole program, reused for every random quote
        Random random = new Random();

        while (continueLoop) {
            // Everything that can throw sits inside try, so one bad answer
            // does not end the program: we jump to catch and loop again
            try {
                System.out.println("1. Enter a number between 1-10 to display a specific quote.");
                System.out.println("2. Enter 'R' to display a random quote.");
                System.out.print("Your choice: ");

                // Read the answer as text, because it can be a number OR the letter R
                String input = scanner.next();

                if (input.equalsIgnoreCase("R")) {
                    // nextInt(10) gives 0 to 9, exactly the valid positions.
                    // What is the formula if I want a different range of numbers?
                    //   random.nextInt(max - min + 1) + min
                    //   example, 1 to 10:  random.nextInt(10 - 1 + 1) + 1
                    //   example, 5 to 20:  random.nextInt(20 - 5 + 1) + 5
                    int randomIndex = random.nextInt(quotes.length);
                    System.out.println(quotes[randomIndex]);
                } else {
                    // parseInt throws NumberFormatException for text like "abc"
                    int index = Integer.parseInt(input);
                    // - 1 turns the user's 1 to 10 into the array's 0 to 9;
                    // 0 or 11 throws ArrayIndexOutOfBoundsException
                    System.out.println(quotes[index - 1]);
                }

                System.out.print("Do you want to see another quote? (Y/N): ");
                String answer = scanner.next();

                // Any answer other than N keeps the loop going
                if (answer.equalsIgnoreCase("N")) {
                    continueLoop = false;
                }
            } catch (Exception exception) {
                // Exception catches both problems above (bad number, out of range)
                System.out.println("Invalid input. Please enter a number between 1 and 10 or 'R' for a random quote.");
                scanner.nextLine();  // Clear the buffer.
            }
        }
        */

        scanner.close();
    }
}
