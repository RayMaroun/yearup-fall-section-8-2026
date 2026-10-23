package com.pluralsight;

import java.util.Arrays;

/**
 * TestStatistics (Workbook 2, Section 3, Exercise 3)
 *
 * Stores 10 test scores in an array and prints:
 *   - the average
 *   - the high score
 *   - the low score
 *   - BONUS: the median (the middle value once the scores are sorted)
 *
 * Average vs median: the average adds everything and divides, so one very low
 * or very high score pulls it a lot. The median only looks at the middle of the
 * sorted list, so a single extreme score barely moves it.
 *
 * An alternative solution that finds the high and low score by sorting a copy
 * of the array is kept at the bottom of the class.
 */
public class TestStatistics {
    public static void main(String[] args) {
        // Create an array of 10 test scores
        // The { } list creates the array and fills it in one step; its length is 10
        int[] scores = {87, 91, 65, 98, 75, 82, 90, 79, 88, 94};

        // Initialize variables to calculate average, high score, and low score
        // high and low start at the first score (not 0), so any real score can replace them
        int sum = 0;
        int highScore = scores[0];
        int lowScore = scores[0];

        // Loop through each score in the array
        // i goes from 0 to length - 1, the valid positions in the array
        for (int i = 0; i < scores.length; i++) {
            // Calculate the sum of all scores
            sum += scores[i];
            // Check if the current score is higher than the previous high score
            if (scores[i] > highScore) {
                highScore = scores[i];
            }
            // Check if the current score is lower than the previous low score
            if (scores[i] < lowScore) {
                lowScore = scores[i];
            }
        }

        // Calculate the average score by dividing the sum by the number of scores
        // (double) converts sum before dividing; int / int would drop the decimals
        double average = (double) sum / scores.length;

        // Print out the average, high score, and low score
        // %.2f prints the double with 2 decimal places
        System.out.printf("Average: %.2f\n", average);
        System.out.println("High score: " + highScore);
        System.out.println("Low score: " + lowScore);

        // Calculate and print the median value
        // The median needs the scores in order, so sort the array first (smallest to largest)
        Arrays.sort(scores);
        double median;
        if (scores.length % 2 == 0) {
            // If the array has an even number of elements, average the middle two
            // With 10 scores the middle two are at positions 4 and 5
            int middle1 = scores.length / 2 - 1;
            int middle2 = scores.length / 2;
            // Dividing by 2.0 (not 2) keeps the .5 when the two values add up to an odd number
            median = (scores[middle1] + scores[middle2]) / 2.0;
        } else {
            // If the array has an odd number of elements, take the middle one
            int middle = scores.length / 2;
            median = scores[middle];
        }
        System.out.println("Median: " + median);
    }

    /*
     * Alternative solution: find the high and low score by sorting.
     * Arrays.sort puts the scores in order from smallest to largest, so the low
     * score is at index 0 and the high score is at the last index, length - 1.
     * We sort a COPY made with Arrays.copyOf so the original order of the scores
     * is kept; sorting the scores array itself would lose the order they were
     * entered in. Sorting is shorter to write, but the loop version above is the
     * pattern to know: "keep the biggest so far" works on anything, for example
     * a list of vehicles where there is nothing to sort by yet.
     * To try it, comment out the main above and remove the comment markers below.

    public static void main(String[] args) {
        int[] scores = {87, 91, 65, 98, 75, 82, 90, 79, 88, 94};

        // Sum with a loop, as before
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        double average = (double) sum / scores.length;

        // Sort a copy, then read the two ends
        int[] sortedScores = Arrays.copyOf(scores, scores.length);
        Arrays.sort(sortedScores);
        int lowScore = sortedScores[0];
        int highScore = sortedScores[sortedScores.length - 1];

        System.out.printf("Average: %.2f\n", average);
        System.out.println("High score: " + highScore);
        System.out.println("Low score: " + lowScore);

        // Median from the same sorted copy: with 10 scores, average the middle two
        double median = (sortedScores[4] + sortedScores[5]) / 2.0;
        System.out.println("Median: " + median);
    }
    */
}
