package com.pluralsight;

import java.util.Scanner;

// Approach 2: a new Scanner in every method.
// Repeated code, and two Scanners on one keyboard: what the first reads ahead, the second never sees.
// Try it: type "1 25.25 5.25" and Enter in one go. main takes the whole line; doAdd waits forever.
public class ScannerPerMethod {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("What do you want to do (1 = add, 2 = subtract)? ");
        int command = myScanner.nextInt();
        if (command == 1) {
            doAdd();
        } else if (command == 2) {
            doSubtract();
        } else {
            System.out.printf("%d -- Invalid command!%n", command);
        }
    }

    public static void doAdd() {
        Scanner myScanner = new Scanner(System.in);            // repeated
        System.out.print("Enter 1st number: ");
        double num1 = myScanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        double num2 = myScanner.nextDouble();
        double sum = num1 + num2;
        System.out.printf("%.2f + %.2f = %.2f%n", num1, num2, sum);
    }

    public static void doSubtract() {
        Scanner myScanner = new Scanner(System.in);            // repeated again
        System.out.print("Enter 1st number: ");
        double num1 = myScanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        double num2 = myScanner.nextDouble();
        double difference = num1 - num2;
        System.out.printf("%.2f - %.2f = %.2f%n", num1, num2, difference);
    }
}
