package com.pluralsight;

import java.util.Scanner;

// Approach 1: one static Scanner shared by every method.
// Works, but it hides which methods read input, and one close() cuts off everyone.
// Compare with ScannerPerMethod.java and ScannerAsParameter.java: same program, different Scanner.
public class ScannerStatic {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("What do you want to do (1 = add, 2 = subtract)? ");
        int command = myScanner.nextInt();
        if (command == 1) {
            doAdd();
        } else if (command == 2) {
            doSubtract();
        } else {
            System.out.printf("%d -- Invalid command!%n", command);
        }
        myScanner.close();
    }

    public static void doAdd() {
        System.out.print("Enter 1st number: ");
        double num1 = myScanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        double num2 = myScanner.nextDouble();
        double sum = num1 + num2;
        System.out.printf("%.2f + %.2f = %.2f%n", num1, num2, sum);
    }

    public static void doSubtract() {
        System.out.print("Enter 1st number: ");
        double num1 = myScanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        double num2 = myScanner.nextDouble();
        double difference = num1 - num2;
        System.out.printf("%.2f - %.2f = %.2f%n", num1, num2, difference);
    }
}
