package com.pluralsight;

/*
 * Exercise 2 (Section 3-1): a Dice object knows how to roll itself.
 * Putting the random-number logic in its own class keeps main() short:
 * main() just asks the dice for a number with dice.roll().
 */
// Define the Dice class.
public class Dice {

    // Define the constructor for the Dice class.
    // It is empty because a die has no data to set up; Java would add this for us anyway.
    public Dice() {
    }

    // Define the roll method for the Dice class.
    // Returns a random whole number from 1 to 6, like one real die.
    public int roll() {
        // The workbook formula for a random number in a range:
        //   (int) (Math.random() * (maxValue - minValue + 1)) + minValue
        // Math.random() gives a decimal from 0.0 up to (not including) 1.0.
        // Multiplying by (6 - 1 + 1) = 6 gives 0.0 to 5.999..., (int) cuts off the decimals (0 to 5),
        // and adding the minimum 1 shifts the result to 1 to 6.
        int minValue = 1;
        int maxValue = 6;
        return (int) (Math.random() * (maxValue - minValue + 1)) + minValue;

        // Same result with the numbers written in directly (6 possible values, starting at 1):
        // return (int) (Math.random() * 6) + 1;
    }
}
