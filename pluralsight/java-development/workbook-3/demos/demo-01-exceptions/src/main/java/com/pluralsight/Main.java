package com.pluralsight;

import java.util.Scanner;

/*
 * Demo 01 - Exceptions
 *
 * This program shows the three kinds of errors a Java program can have, and
 * then the tool for the one kind you can do something about at run time:
 * try / catch.
 *
 *   1. Compile error: the code is wrong before it runs. IntelliJ underlines it
 *      and javac refuses to build. Nothing runs at all.
 *   2. Logic error: the code compiles and runs, but the answer is wrong.
 *      Java cannot see it; only you can, by reading the output.
 *   3. Runtime error (an exception): the code compiles, starts running, and
 *      stops in the middle because something impossible was asked of it.
 *
 * Each block prints a title line, then its results. Two blocks are switched
 * off with a comment marker on purpose: remove the markers around ONE block,
 * run, and read what happens. That error message is part of the lesson.
 *
 * The last block is the one to study: the same reading-and-dividing code,
 * wrapped in try / catch, so a bad entry ends with a message instead of a
 * crash.
 */
public class Main {
    public static void main(String[] args) {
        // One Scanner for every block that reads the keyboard. Make it once, reuse it.
        Scanner scanner = new Scanner(System.in);

        // ---------- Compile error ----------
        // Java method names are case sensitive: println exists, printLn does not.
        // Remove the // in front of the line and the project will not even build.
        // The program never starts, so no output at all, not even the title line.
        System.out.println("========== Compile error ==========");
        // System.out.printLn("Hello Raymond");                      // compile error: printLn does not exist, nothing runs
        System.out.println();

        // ---------- Logic error ----------
        // Everything here compiles and runs. Switch the block on and compare the
        // two finalPrice lines: price * discount gives 20.0, which is the discount
        // amount, not the price after the discount. Java is happy, the answer is wrong.
        // Only reading the output tells you something is off.
        System.out.println("========== Logic error ==========");
        /*
        double price = 100.0;
        double discount = 0.2;

        // double finalPrice = price * discount;                     // logic error: runs fine, prints 20.0, the discount, not the price
        double finalPrice = price - (price * discount);
        System.out.println("The final price is: " + finalPrice);
        */
        System.out.println();

        // ---------- Runtime error ----------
        // The array has three names at positions 0, 1 and 2. Switch the block on,
        // run, and type 3 (or anything bigger): Java throws
        // ArrayIndexOutOfBoundsException and the program stops right there.
        // Type a letter instead of a number and nextInt throws InputMismatchException.
        // Both are exceptions: errors that happen while the program is running.
        System.out.println("========== Runtime error ==========");
        /*
        String[] listOfNames = {"Ray", "Mason", "Claude"};

        System.out.println("Please enter the index of the name that you want: ");
        int index = scanner.nextInt();

        System.out.println(listOfNames[index]);                      // runtime error: 3 or more stops the program here
        */
        System.out.println();

        // ---------- try / catch ----------
        // try { ... } holds the code that might throw. If every line inside works,
        // the catch block is skipped. The moment one line throws, Java leaves the
        // try block at that line (the lines after it do not run) and jumps to catch.
        //
        // catch (Exception ex) catches any exception. The variable ex holds the
        // details: ex.getMessage() is Java's own one-line explanation.
        //
        // Two ways to make this block throw:
        //   - type a letter for a number: InputMismatchException
        //   - type 0 as the second number: ArithmeticException, "/ by zero"
        // Either way the program ends with our message instead of a red stack trace.
        System.out.println("========== try / catch ==========");
        try {

            System.out.println("Enter number 1: ");
            int firstNumber = scanner.nextInt();                     // a letter here: InputMismatchException

            System.out.println("Enter number 2: ");
            int secondNumber = scanner.nextInt();

            int result = firstNumber / secondNumber;                 // 0 here: ArithmeticException

            System.out.println("The result is: " + result);         // only reached when nothing above threw

        } catch (Exception ex) {
            System.out.println(ex.getMessage());                     // what Java says went wrong
            scanner.close();                                         // we are done reading, release the keyboard
            System.out.println("A big sorry from Potato Sensei, but an error has occurred!");
        }
        System.out.println();

    }
}
