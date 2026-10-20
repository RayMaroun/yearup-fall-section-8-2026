package com.pluralsight;

import java.util.Scanner; // Scanner lives in java.util, so it must be imported

/*
 * Exercise 1: Full Name Generator (Workbook 2a, Section 1-1).
 * Asks for the four parts of a name, then builds one full-name String.
 * First and last name are always there; the middle name and suffix are optional,
 * so each one is only added when the user actually typed something.
 */
public class FullNameApplication {
    public static void main(String[] args) {

        // One Scanner reading from the keyboard for the whole program
        Scanner scanner = new Scanner(System.in);

        // Header line, worded exactly like the workbook trace (no colon)
        System.out.println("Please enter your name");

        // Read each part. trim() removes spaces the user typed before or after the text,
        // so "  Glen  " becomes "Glen" and an Enter-only answer becomes "" (empty).
        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Middle name: ");
        String middleName = scanner.nextLine().trim();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Suffix: ");
        String suffix = scanner.nextLine().trim();

        // Alternative: move the building logic into its own method (see generateFullName below)
        //String fullName = generateFullName(firstName, middleName, lastName, suffix);

        // Start with the part that is always there
        String fullName = firstName;

        // Middle name is optional: only add it when it is not empty.
        // charAt(0) takes the first letter, so "Charles" or "C." both become "C."
        if (!middleName.isEmpty()) {
            fullName += " " + middleName.charAt(0) + ".";
        }

        // Last name is always there
        fullName += " " + lastName;

        // Suffix is optional; when present it goes after a comma: "Glen Williams, PhD"
        if (!suffix.isEmpty()) {
            fullName += ", " + suffix;
        }

        // \n prints an empty line first, to separate the answers from the result
        System.out.println("\nFull name: " + fullName);

    }

/*  Alternative solution: the same logic as a method that returns the full name.
    To use it, uncomment this method and the generateFullName(...) line in main,
    then remove the inline building code above.

    public static String generateFullName(String firstName, String middleName, String lastName, String suffix) {
        String fullName = firstName;

        if (!middleName.isEmpty()) {
            fullName += " " + middleName.charAt(0) + ".";
        }

        fullName += " " + lastName;

        if (!suffix.isEmpty()) {
            fullName += ", " + suffix;
        }

        return fullName;
    }*/
}
