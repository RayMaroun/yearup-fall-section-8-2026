package com.pluralsight;

import java.util.Scanner;

// Approach 3, the one we use: one Scanner created in main, passed as a parameter.
// One object, one buffer, and the header says out loud that the method reads input.
// Same program as ScannerStatic.java and ScannerPerMethod.java; this is the one to copy.
public class ScannerAsParameter {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("What do you want to do (1 = add, 2 = subtract)? ");
        int command = myScanner.nextInt();
        if (command == 1) {
            doAdd(myScanner);                        // the one Scanner travels to the method
        } else if (command == 2) {
            doSubtract(myScanner);
        } else {
            System.out.printf("%d -- Invalid command!%n", command);
        }
        myScanner.close();
    }

    public static void doAdd(Scanner potatoScanner) {           // the parameter name can be anything
        System.out.print("Enter 1st number: ");
        double num1 = potatoScanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        double num2 = potatoScanner.nextDouble();
        double sum = num1 + num2;
        System.out.printf("%.2f + %.2f = %.2f%n", num1, num2, sum);
    }

    public static void doSubtract(Scanner tomatoScanner) {      // same object, different name
        System.out.print("Enter 1st number: ");
        double num1 = tomatoScanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        double num2 = tomatoScanner.nextDouble();
        double difference = num1 - num2;
        System.out.printf("%.2f - %.2f = %.2f%n", num1, num2, difference);
    }
}
