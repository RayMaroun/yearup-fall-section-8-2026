package com.pluralsight;

import java.util.Scanner; // Scanner lives in java.util, so it must be imported

/*
 * Exercise 2: Full Name Parser (Workbook 2a, Section 1-1).
 * The user types a whole name on one line, "first last" or "first middle last".
 * We find the spaces with indexOf() and cut the pieces out with substring().
 * An alternative solution using split() is kept at the bottom of the class.
 */
public class FullNameParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name.
        // trim() removes leading/trailing spaces, so "   Dana L. Wyatt   " becomes "Dana L. Wyatt"
        System.out.print("Please enter your name: ");
        String input = scanner.nextLine().trim();

        // Initialize variables to store the first, middle, and last names.
        // middleName starts empty and stays empty when the user gives only two words.
        String firstName = "";
        String middleName = "";
        String lastName = "";

        // Find the first space. indexOf returns its position, or -1 when there is no space at all
        int firstSpaceIndex = input.indexOf(' ');

        if (firstSpaceIndex == -1) {
            // If there is only one word, it's the first and last name
            firstName = input;
            lastName = input;
            // Alternative: treat a single word as an error and stop the program
         /*   System.out.println("Name format is wrong");
            return;*/
        } else {
            // Extract the first name: everything before the first space.
            // substring(start, end) includes start and stops just before end.
            firstName = input.substring(0, firstSpaceIndex);

            // Remove the first name and any leading/trailing spaces.
            // "Dana L. Wyatt" becomes "L. Wyatt", and "Dana Wyatt" becomes "Wyatt"
            input = input.substring(firstSpaceIndex).trim();

            // Find the next space (if any) in what is left
            int lastSpaceIndex = input.indexOf(' ');

            if (lastSpaceIndex == -1) {
                // If there's no space left, the remaining input is the last name
                lastName = input;
            } else {
                // Extract the middle and last names: before the space is the middle,
                // after it (lastSpaceIndex + 1 skips the space itself) is the last name
                middleName = input.substring(0, lastSpaceIndex);
                lastName = input.substring(lastSpaceIndex + 1);
            }
        }

        // Display the individual pieces of the name.
        // The ternary ( ? : ) prints "(none)" when there was no middle name, as in the workbook trace
        System.out.println("First name: " + firstName);
        System.out.println("Middle name: " + (middleName.isEmpty() ? "(none)" : middleName));
        System.out.println("Last name: " + lastName);

        scanner.close(); // done reading: release the keyboard
    }


    /* Alternative solution using split().
       split(" ") breaks the line into an array at every space:
       "Dana L. Wyatt" -> ["Dana", "L.", "Wyatt"], "Dana Wyatt" -> ["Dana", "Wyatt"].
       The first element is always the first name, the last element the last name,
       and a middle name exists only when there are exactly 3 parts.
       To try it, comment out the main above and uncomment this one.

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user to enter name
        System.out.print("Please enter your name: ");
        String name = input.nextLine().trim(); // Trim leading/trailing spaces

        // Split name into parts
        String[] parts = name.split(" "); // Split by whitespace

        // Determine number of parts and extract first/last name
        String firstName = parts[0];
        String lastName = parts[parts.length - 1];
        String middleName = "";

        if (parts.length == 3) { // First, middle, last name format
            middleName = parts[1];
        }

        // Display name parts
        System.out.println("First name: " + firstName);
        System.out.println("Middle name: " + middleName);
        System.out.println("Last name: " + lastName);
    }*/
}
