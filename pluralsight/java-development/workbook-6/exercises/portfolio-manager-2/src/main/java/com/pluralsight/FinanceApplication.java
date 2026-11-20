package com.pluralsight;

import com.pluralsight.finance.*;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * FinanceApplication (Workbook 6a, Section 2-2 Working with Interfaces, Exercise 2)
 *
 * The bonus version: the user builds the portfolio from the keyboard.
 * The program asks for the type of asset, then for the values that type
 * needs, creates the object and adds it to the portfolio. Typing quit
 * ends the loop, and the program prints the net value, the most valuable
 * and the least valuable item.
 *
 * Every case creates an object of a different class, and every one of
 * them goes into the same portfolio.add(...) call: the method takes a
 * Valuable, and all five classes are Valuable.
 *
 * readInt, readDouble and readBigDecimal are private helpers that ask
 * again on a typo instead of crashing.
 */
public class FinanceApplication {
    // One Scanner for the whole program, shared by main and the read
    // helpers; two Scanners on System.in would fight over the same input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /* Part 1 of the exercise, without the keyboard: build the portfolio in
           code and print the three results. To try it, remove the comment
           marks here and put them around the rest of main instead.
        Portfolio myPortfolio = new Portfolio("My Portfolio", "John");

        myPortfolio.add(new Jewelry(5));
        myPortfolio.add(new Gold(2.5));
        myPortfolio.add(new House(1990, 1800, 3));
        myPortfolio.add(new BankAccount("Pam", "123", new BigDecimal("12500")));
        myPortfolio.add(new CreditCard("Pam", "999", new BigDecimal("2500.50")));

        // Net value: 5000.00 + 250.00 + 10000.00 + 12500.00 - 2500.50 = 25249.50
        System.out.printf("Portfolio Value: $%.2f%n", myPortfolio.getValue());
        // The bank account, 12500.00
        System.out.println("Most Valuable Asset: " + myPortfolio.getMostValuable());
        // The credit card: what is owed counts as a negative value, -2500.50
        System.out.println("Least Valuable Asset: " + myPortfolio.getLeastValuable());*/

        // Create a portfolio
        Portfolio portfolio = new Portfolio("My Portfolio", "John");

        while (true) {
            System.out.println("Enter the type of asset (Jewelry, Gold, House, BankAccount, CreditCard) or 'quit' to exit:");
            String assetType = scanner.nextLine();

            // equalsIgnoreCase: quit, Quit and QUIT all end the loop
            if (assetType.equalsIgnoreCase("quit")) {
                break;
            }

            // toLowerCase() so the user can type the type in any case
            switch (assetType.toLowerCase()) {
                case "jewelry":
                    double karat = readDouble("Enter the karat value:");
                    Jewelry jewelry = new Jewelry(karat);
                    portfolio.add(jewelry);
                    break;
                case "gold":
                    double weight = readDouble("Enter the weight:");
                    Gold gold = new Gold(weight);
                    portfolio.add(gold);
                    break;
                case "house":
                    int year = readInt("Enter the year:");
                    int squareFeet = readInt("Enter the square feet:");
                    int bedrooms = readInt("Enter the bedrooms number:");
                    House house = new House(year,squareFeet,bedrooms);
                    portfolio.add(house);
                    break;
                case "bankaccount":
                    System.out.println("Enter the bank account owner name:");
                    String bankAccountOwner = scanner.nextLine();
                    System.out.println("Enter the bank account number:");
                    String accountNumber = scanner.nextLine();
                    // A balance is money, so it is read as a BigDecimal
                    BigDecimal bankAccountBalance = readBigDecimal("Enter the balance:");
                    BankAccount bankAccount = new BankAccount(bankAccountOwner, accountNumber, bankAccountBalance);
                    portfolio.add(bankAccount);
                    break;
                case "creditcard":
                    System.out.println("Enter the credit card owner name:");
                    String creditCardOwner = scanner.nextLine();
                    System.out.println("Enter the credit card number:");
                    String creditCardAccountNumber = scanner.nextLine();
                    BigDecimal creditCardBalance = readBigDecimal("Enter the balance:");
                    CreditCard creditCard = new CreditCard(creditCardOwner,creditCardAccountNumber, creditCardBalance);
                    portfolio.add(creditCard);
                    break;
                default:
                    System.out.println("Invalid asset type. Please try again.");
                    break;
            }
        }

        // Print the portfolio value
        // %.2f prints the amount with two decimal places, %n ends the line
        BigDecimal portfolioValue = portfolio.getValue();
        System.out.printf("Portfolio Value: $%.2f%n", portfolioValue);

        // Get the most valuable asset
        // An empty portfolio has no most valuable asset: the method returns
        // null, and calling a method on null would crash the program with a
        // NullPointerException. So the line is printed only when there is one
        Valuable mostValuableAsset = portfolio.getMostValuable();
        if (mostValuableAsset != null) {
            // %s prints the object as text: Java calls its toString()
            System.out.printf("Most Valuable Asset(Value: %.2f): %s%n", mostValuableAsset.getValue(), mostValuableAsset);
        }

        // Get the least valuable asset
        Valuable leastValuableAsset = portfolio.getLeastValuable();
        if (leastValuableAsset != null) {
            System.out.printf("Least Valuable Asset(Value: %.2f): %s%n", leastValuableAsset.getValue(), leastValuableAsset);
        }
    }

    /* -----------------------------------------------------------------
       Reading numbers safely
       scanner.nextInt() and nextDouble() throw InputMismatchException on a
       typo like "abc" and leave the Enter key in the buffer. Reading the
       whole line as text and converting it avoids both: a bad entry is
       caught and the question is asked again.
       ----------------------------------------------------------------- */

    private static int readInt(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    // A money amount is read as a BigDecimal, built from the text the user
    // typed, so 2500.50 stays 2500.50
    private static BigDecimal readBigDecimal(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
