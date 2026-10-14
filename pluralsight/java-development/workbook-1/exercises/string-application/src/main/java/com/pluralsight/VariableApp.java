package com.pluralsight;

// Exercise: String Application, file 1. One variable per question, each printed inside a sentence.
public class VariableApp {
    public static void main(String[] args) {
        // Step 1: Declare and initialize one variable per question.
        // The type matches the data: text is a String, a year is an int,
        // one letter is a char, yes/no is a boolean.
        String favoriteColor = "Blue";
        int yearStarted = 2026;
        char middleInitial = 'M';
        boolean hasPets = true;
        String niceMessage = "Keep pushing forward, success is near!";

        // Step 1: Print each variable with a descriptive message.
        // The + joins the message and the value into one String.
        System.out.println("My favorite color is: " + favoriteColor);
        System.out.println("The year I started this class is: " + yearStarted);
        System.out.println("My middle initial is: " + middleInitial);
        System.out.println("Do I have pets? " + hasPets);
        System.out.println("Here is a nice message: " + niceMessage);

        // Step 2: Declare AND initialize variables
        int daysInWeek = 7;
        double coffeePrice = 4.99;
        char favoriteLetter = 'R';
        boolean isRaining = false;

        // Step 2: Print all values in complete sentences.
        // A value can sit in the middle of the sentence: text + value + text.
        System.out.println("There are " + daysInWeek + " days in a week.");
        System.out.println("The price of coffee is $" + coffeePrice);
        System.out.println("My favorite letter is: " + favoriteLetter);
        System.out.println("Is it raining? " + isRaining);
    }
}
