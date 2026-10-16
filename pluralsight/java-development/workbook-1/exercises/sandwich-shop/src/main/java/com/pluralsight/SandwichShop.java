package com.pluralsight;

import java.util.Scanner;

// Exercise: Sandwich Shop. Size and age in, one price out: two if / else if / else decisions.
public class SandwichShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // one Scanner on the keyboard

        // Prompt user for sandwich size
        System.out.print("Enter sandwich size (1 for Regular, 2 for Large): ");
        int sandwichSize = scanner.nextInt();
        scanner.nextLine();

        // Calculate base price: declared here, given its value inside the branches below
        double basePrice;
        if (sandwichSize == 1) {
            basePrice = 5.45;
        } else if (sandwichSize == 2) {
            basePrice = 8.95;
        } else {
            System.out.println("Invalid sandwich size");
            return;                         // leaves main right away: nothing below runs
        }

        System.out.println("Would you like it loaded? (yes/no): ");
        String loadedAnswer = scanner.nextLine();

        if(loadedAnswer.equalsIgnoreCase("yes")){
            if(sandwichSize == 1){
                basePrice = basePrice + 1.00;
            } else {
                basePrice = basePrice + 1.75;
            }
        }

        // Prompt user for age
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        // Calculate discount percentage: everyone else keeps 0.0, no discount
        double discountPercentage = 0.0;
        if (age <= 17) {
            discountPercentage = 0.1;
        } else if (age >= 65) {
            discountPercentage = 0.2;
        }

        // Calculate final price: a 10% discount means paying 90%, so multiply by (1 - 0.1)
        double finalPrice = basePrice * (1 - discountPercentage);

        // Display cost of sandwich with two decimals (%.2f), then end the line (%n)
        System.out.printf("Cost of sandwich: $%.2f%n", finalPrice);

        scanner.close();
    }
}


