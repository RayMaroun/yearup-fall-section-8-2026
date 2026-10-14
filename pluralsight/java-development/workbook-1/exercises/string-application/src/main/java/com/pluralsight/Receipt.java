package com.pluralsight;

// Exercise: String Application, file 2. Three variables joined into one receipt line.
public class Receipt {
    public static void main(String[] args) {
        // Step 3: Create variables for item
        String itemName = "apples";
        double itemPrice = 2.37;

        // Step 3: Create quantity
        int quantity = 3;

        // Step 3: Calculate the total. int * double gives a double.
        double total = quantity * itemPrice;

        // Step 3: Build the sentence and print it, e.g. "You bought 3 apples for $7.11"
        System.out.println("You bought " + quantity + " " + itemName + " for $" + total);
    }
}
