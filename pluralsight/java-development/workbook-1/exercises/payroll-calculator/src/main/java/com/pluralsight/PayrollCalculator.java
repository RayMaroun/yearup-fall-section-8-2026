package com.pluralsight;

import java.util.Scanner;

// Exercise: Payroll Calculator. Name, hours and rate in; gross pay out with two decimals.
public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter their name, hours worked, and pay rate
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter hours worked: ");
        double hours = input.nextDouble();
        System.out.print("Enter pay rate: ");
        double rate = input.nextDouble();

        // Calculate gross pay: hours times the hourly rate
        double grossPay = hours * rate;

        // OPTIONAL (not required): overtime, hours above 40 are paid 1.5 times the rate.
        // It uses an if statement, which comes in Section 4-1.
        // To try it: comment out the grossPay line above, then delete the /* and */ below.
        /*
        double grossPay;
        if (hours > 40) {
            grossPay = 40 * rate + (hours - 40) * 1.5 * rate;
        } else {
            grossPay = hours * rate;
        }
        */

        // Display the employee's name and gross pay
        System.out.println("Name: " + name);
        System.out.println("Gross Pay: $" + String.format("%.2f", grossPay));

        input.close();
    }
}
