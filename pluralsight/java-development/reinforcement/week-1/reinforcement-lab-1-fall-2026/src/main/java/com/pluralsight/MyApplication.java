package com.pluralsight;

import java.util.Scanner;

// Reinforcement lab, week 1: one small program that uses everything from the week.
// Variables, Scanner, if/else, methods, String comparison, logical operators, Math, switch, boolean, ternary.
public class MyApplication {

    public static void main(String[] args) {
        // One Scanner for the whole program, passed to every method that needs input
        Scanner scanner = new Scanner(System.in);

        // Step 3 and 6: read the name and the age through methods
        String name = getName(scanner);
        int age = getAge(scanner);

        System.out.println("=============================================");
        printNameAndAge(name, age);                 // Step 3 and 6: print what was typed
        System.out.println("=============================================");
        printVotingEligibility(age);                // Step 4: if / else
        System.out.println("=============================================");
        printMessage(name, age);                    // Step 5: method with two parameters
        System.out.println("=============================================");
        printWelcomeMessage(name);                  // Step 7 then 10: String comparison, then switch
        System.out.println("=============================================");
        printDrinkingEligibility(name, age);        // Step 8: && logical operator
        System.out.println("=============================================");

        // Step 9: Math.sqrt, printed with printf rounded to 2 decimals
        double number = getNumber(scanner);
        double squareRoot = calculateSquareRoot(number);
        System.out.printf("The square root of %.2f is %.2f%n", number, squareRoot);
        System.out.println("=============================================");

        // Step 11: a method that returns a boolean
        int firstNumber = getFirstNumber(scanner);
        int secondNumber = getSecondNumber(scanner);
        boolean firstIsBigger = isGreaterThan(firstNumber, secondNumber);
        System.out.println(firstNumber + " is greater than " + secondNumber + ": " + firstIsBigger);
        System.out.println("=============================================");

        // Step 12: ternary operator, condition ? value if true : value if false
        String welcomeMessage = name.equalsIgnoreCase("Lara") ? "You are not welcome, " + name + "!" : "Welcome, " + name + "!";
        System.out.println(welcomeMessage);
        System.out.println("=============================================");

        // Step 13: Math.max and Math.min on the same two numbers
        int maxNumber = getMax(firstNumber, secondNumber);
        int minNumber = getMin(firstNumber, secondNumber);
        System.out.println("The larger number is: " + maxNumber);
        System.out.println("The smaller number is: " + minNumber);

        scanner.close();
    }

    // Reads a whole line of text
    public static String getName(Scanner scanner) {
        System.out.println("Enter your name:");
        return scanner.nextLine();
    }

    // Reads one whole number
    public static int getAge(Scanner scanner) {
        System.out.println("Enter your age:");
        return scanner.nextInt();
    }

    public static void printNameAndAge(String name, int age) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    // if / else: exactly one of the two branches runs
    public static void printVotingEligibility(int age) {
        if (age >= 18) {
            System.out.println("You are old enough to vote.");
        } else {
            System.out.println("You are not old enough to vote.");
        }
    }

    public static void printMessage(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }

    // switch compares the exact text: "alice" goes to default (ignoring case comes next week)
    public static void printWelcomeMessage(String name) {
        switch (name) {
            case "Alice" -> System.out.println("Welcome, Alice!");
            case "Bob" -> System.out.println("Hey Bob, do you want to grab a drink?");
            default -> System.out.println("Hello, stranger!");
        }
    }

    // && means both conditions must be true; equalsIgnoreCase accepts bob, BOB, Bob
    public static void printDrinkingEligibility(String name, int age) {
        if (name.equalsIgnoreCase("Bob") && age >= 21) {
            System.out.println("You are old enough to drink.");
        } else {
            System.out.println("You are not old enough to drink.");
        }
    }

    public static double getNumber(Scanner scanner) {
        System.out.println("Enter a number:");
        return scanner.nextDouble();
    }

    public static double calculateSquareRoot(double number) {
        return Math.sqrt(number);
    }

    public static int getFirstNumber(Scanner scanner) {
        System.out.println("Enter the first number:");
        return scanner.nextInt();
    }

    public static int getSecondNumber(Scanner scanner) {
        System.out.println("Enter the second number:");
        return scanner.nextInt();
    }

    // The comparison itself is already a boolean, so it can be returned directly
    public static boolean isGreaterThan(int firstNumber, int secondNumber) {
        return firstNumber > secondNumber;
    }

    public static int getMax(int firstNumber, int secondNumber) {
        return Math.max(firstNumber, secondNumber);
    }

    public static int getMin(int firstNumber, int secondNumber) {
        return Math.min(firstNumber, secondNumber);
    }
}
