package com.pluralsight;

import java.util.Scanner;

// Refactor of payroll-calculator: same questions and output, but the work is split into
// two methods, one that calculates and one that displays. main only reads and calls.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter their name, hours worked, and pay rate
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter hours worked: ");
        double hours = input.nextDouble();
        System.out.print("Enter pay rate: ");
        double rate = input.nextDouble();

        // Calculate gross pay: the method returns the value, main keeps it
        double grossPay = calculateGrossPay(hours, rate);

        // Display the employee's name and gross pay
        displayEmployeeInfo(name, grossPay);

        input.close();
    }

    // void: prints, returns nothing
    public static void displayEmployeeInfo(String name, double grossPay) {
        System.out.println("Name: " + name);
        System.out.println("Gross Pay: $" + String.format("%.2f", grossPay));
    }

    // double: returns the pay. Includes the optional overtime rule from the first exercise,
    // 1.5 times the rate for hours above 40, now that if / else is known.
    public static double calculateGrossPay(double hours, double rate) {
        double grossPay;
        if (hours > 40) {
            grossPay = 40 * rate + (hours - 40) * 1.5 * rate;
        } else {
            grossPay = hours * rate;
        }
        return grossPay;
    }
}
