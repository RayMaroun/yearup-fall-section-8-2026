package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * BedtimeStories (Workbook 3a, Section 1-2 Using BufferedReader, Exercise 2)
 *
 * Asks for the name of a story file (goldilocks.txt, hansel_and_gretel.txt or
 * mary_had_a_little_lamb.txt), reads it one line at a time with a BufferedReader
 * and prints each line with its line number in front:
 *   1. Goldilocks and the Three Bears
 *   2.
 *   3. Once upon a time ...
 *
 * The story files sit in the project folder (next to pom.xml), because that is
 * the folder IntelliJ runs the program from, so a plain file name finds them.
 */
public class BedtimeStories {
    public static void main(String[] args) {
        // Scanner reads the keyboard; the BufferedReader below reads the file
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the name of the story file: ");
        String fileName = console.nextLine();

        // Opening a file can fail (wrong name, missing file), so Java makes us
        // handle the exception with try/catch
        try {
            // FileReader opens the file; BufferedReader wraps it so we can read whole lines
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 1;
            // readLine() returns null when there are no more lines, which ends the loop.
            // The assignment and the null check happen in the same condition.
            while ((line = reader.readLine()) != null) {
                // %d the line number, %s the text, %n a new line
                System.out.printf("%d. %s%n", lineNumber, line);
                lineNumber++;
            }
            // Close the file when we are done with it
            reader.close();
            console.close();
        } catch (Exception e) {
            // Most likely cause: the name typed does not match a file in the project folder
            System.out.println("File not found: " + fileName);
        }
    }
}
