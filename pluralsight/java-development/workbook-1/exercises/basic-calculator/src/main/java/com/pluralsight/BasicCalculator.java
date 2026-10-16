package com.pluralsight;

import java.util.Scanner;                  // Scanner lives in java.util, so it must be imported

// Exercise: Basic Calculator. Reads two whole numbers and a menu choice, always multiplies.
public class BasicCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // one Scanner on the keyboard for the whole program

        // Ask the user for two whole numbers. print (not println) keeps the answer on the same line
        System.out.print("Enter the first number: ");
        int firstNumber = scanner.nextInt();

        System.out.print("Enter the second number: ");
        int secondNumber = scanner.nextInt();
        scanner.nextLine();                 // eats the Enter left after the last number

        // Ask which calculation they want (we will always multiply). \n starts a new line first
        System.out.println("\nPossible calculations:");
        System.out.println("(A)dd");
        System.out.println("(S)ubtract");
        System.out.println("(M)ultiply");
        System.out.println("(D)ivide");
        System.out.print("Please select an option: ");
        String option = scanner.nextLine();   // the answer is text, so nextLine

        // For this exercise, ignore the option and always multiply
        int result = firstNumber * secondNumber;

        // Display the equation and the result
        System.out.println("\n" + firstNumber + " * " + secondNumber + " = " + result);

        scanner.close();                    // done reading: release the keyboard
    }
}
