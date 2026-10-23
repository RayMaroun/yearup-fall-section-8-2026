package com.pluralsight;

/*
 * Exercise 2 (Section 3-1): roll a pair of dice 100 times, show every roll,
 * and count how many times the SUM of the two dice is 2, 4, 6 or 7.
 * The counts change every run because the rolls are random.
 */
// Define the Main class.
public class Main {
    public static void main(String[] args) {
        // Create an instance of the Dice class named "dice".
        Dice dice = new Dice();
        // Declare integer variables for the two dice rolls and the counters for the different sums rolled.
        // Every counter starts at 0 because nothing has been rolled yet.
        int roll1, roll2;
        int twoCounter = 0, fourCounter = 0, sixCounter = 0, sevenCounter = 0;

        // Loop 100 times (i = 0, 1, ... 99).
        for (int i = 0; i < 100; i++) {
            // Roll the dice twice and store the results in the roll1 and roll2 variables.
            roll1 = dice.roll();
            roll2 = dice.roll();
            // Calculate the sum of the two dice rolls.
            int sum = roll1 + roll2;
            // Print out the value of each roll of the dice, formatted in a specific way.
            // %2d prints a whole number in a space 2 characters wide, so the columns line up.
            System.out.printf("Roll 1: %2d - Roll 2: %2d - Sum: %2d\n", roll1, roll2, sum);
            // Use a switch statement to determine which sum was rolled and increment the appropriate counter.
            // Any other sum (3, 5, 8, 9, 10, 11, 12) matches no case, so nothing is counted.
            // break stops Java from "falling through" into the next case.
            switch (sum) {
                case 2:
                    twoCounter++;
                    break;
                case 4:
                    fourCounter++;
                    break;
                case 6:
                    sixCounter++;
                    break;
                case 7:
                    sevenCounter++;
                    break;
            }
        }

        // After the loop: print out the number of times each sum was rolled.
        System.out.printf("\nNumber of times each sum was rolled:\n");
        System.out.printf(" 2: %d\n", twoCounter);
        System.out.printf(" 4: %d\n", fourCounter);
        System.out.printf(" 6: %d\n", sixCounter);
        System.out.printf(" 7: %d\n", sevenCounter);
    }
}
