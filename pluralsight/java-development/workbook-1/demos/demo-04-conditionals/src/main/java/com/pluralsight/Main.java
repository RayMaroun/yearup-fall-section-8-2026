package com.pluralsight;

import java.util.Scanner;

/*
 * Demo 04 - Conditionals
 *
 * This program walks through Section 4-1: making the program do different
 * things depending on a condition. It asks for your name and age once, then
 * every block uses them: if, if / else, if / else if / else, the conditional
 * operator (the one-line if / else), the logical operators && || and !,
 * and how to compare Strings.
 *
 * Run it several times with different ages (17, 26, 70) and a different
 * name, and watch which lines print. That is the whole idea: a condition is
 * a question with a true or false answer, and the answer picks the path.
 *
 * Some lines are commented out on purpose: they show the longer or the
 * wrong way. Swap them in and run again.
 */
public class Main {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = myScanner.nextLine();
        System.out.print("Please enter your age: ");
        int age = myScanner.nextInt();
        System.out.println();

        // ---------- if ----------
        // The code between the braces runs only when the condition is true.
        // When it is false, Java skips the braces and carries on below them.
        System.out.println("========== if ==========");
        double price = 25.00;
        if (age < 18) {
            price = price * 0.9;                     // 10% off, only when the condition is true
        }
        System.out.println("Price of admission: " + price);
        System.out.println();

        // ---------- if / else ----------
        // Two paths, exactly one of them runs. else has no condition of its own:
        // it is "everything the if did not catch".
        System.out.println("========== if / else ==========");
        if (age >= 21) {
            System.out.println("You are allowed to drink!");
        } else {
            System.out.println("You are not allowed to drink!");
        }
        System.out.println();

        // ---------- if / else if / else ----------
        // Several paths, tested top to bottom; the first true condition wins and
        // the rest are skipped. That is why "age < 65" does not need "age >= 18":
        // anyone under 18 was already caught by the first branch.
        System.out.println("========== if / else if / else ==========");
        double ticketPrice;
        if (age < 18) {
            ticketPrice = 22.50;
        } else if (age < 65) {
            ticketPrice = 30.50;
        } else {
            ticketPrice = 25.00;
        }
        System.out.println("Ticket price: " + ticketPrice);
        System.out.println();

        // ---------- Conditional operator ----------
        // An if / else that only chooses a value can be written in one line:
        // condition ? value if true : value if false. Same result as the five lines above it.
        System.out.println("========== Conditional operator ==========");
        /*
        double entryPrice;
        if (age < 18) {
            entryPrice = 22.50;
        } else {
            entryPrice = 25.00;
        }
        */
        double entryPrice = (age < 18) ? 22.50 : 25.00;   // condition ? value if true : value if false
        System.out.println("Entry price: " + entryPrice);
        System.out.println();

        // ---------- Logical operators ----------
        // Conditions can be combined: || (or) is true when either side is true,
        // && (and) only when both are, and ! (not) flips true and false.
        System.out.println("========== Logical operators ==========");
        if (age < 18 || age >= 65) {                 // or: either side true is enough
            System.out.println("Discount price: 18.00");
        } else {
            System.out.println("Full price: 25.00");
        }
        if (age >= 18 && age < 65) {                 // and: both sides must be true
            System.out.println("Working age");
        }
        if (!(age < 18)) {                           // not: flips true and false
            System.out.println("Adult");
        }
        System.out.println();

        // ---------- Comparing Strings ----------
        // Never compare Strings with ==. It asks "are these the same object?", and text
        // typed by the user is a different object from the text in your code, even when
        // the letters are identical. equals() asks the right question: same characters,
        // same order. equalsIgnoreCase() does the same but ignores capitals.
        // Try it: swap the == line in and type Raymond. It prints "I don't love Potatoes!".
        System.out.println("========== Comparing Strings ==========");
        // if (name == "Raymond") {
        if (name.equals("Raymond")) {                // equals: same characters, same order
            System.out.println("I love Potatoes!");
        } else {
            System.out.println("I don't love Potatoes!");
        }
        if (name.equalsIgnoreCase("raymond")) {      // capitals do not matter
            System.out.println("Welcome back, Raymond!");
        }
        System.out.println();

        myScanner.close();                           // done reading: release the keyboard
    }
}
