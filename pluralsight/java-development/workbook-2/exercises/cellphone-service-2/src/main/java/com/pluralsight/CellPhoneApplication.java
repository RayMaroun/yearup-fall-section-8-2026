package com.pluralsight;

import java.util.Scanner;

/*
 * CellPhoneApplication holds main(), the starting point of the program.
 * It creates two empty phones, fills each one from the user's answers,
 * prints both with the display() helper, then has each phone dial the other.
 */
public class CellPhoneApplication {
    public static void main(String[] args) {
        // Create two new instances of CellPhone.
        // Same blueprint, two separate objects: each keeps its own five properties.
        CellPhone cellPhone1 = new CellPhone();
        CellPhone cellPhone2 = new CellPhone();

        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // ---- Phone 1 ----
        System.out.println("Enter the information for CellPhone 1:");
        // Prompt user for cell phone information and set the properties
        System.out.print("What is the serial number? ");
        int serialNumber = scanner.nextInt();  // Read integer value
        cellPhone1.setSerialNumber(serialNumber);
        scanner.nextLine();  // Consume newline: nextInt() leaves the Enter key in the buffer,
                             // and without this line the next nextLine() would read an empty answer

        System.out.print("What model is the phone? ");
        String model = scanner.nextLine();  // Read entire line till newline (keeps spaces, ex: iPhone 15 Pro Max)
        cellPhone1.setModel(model);

        System.out.print("Who is the carrier? ");
        String carrier = scanner.nextLine();
        cellPhone1.setCarrier(carrier);

        System.out.print("What is the phone number? ");
        String phoneNumber = scanner.nextLine();
        cellPhone1.setPhoneNumber(phoneNumber);

        System.out.print("Who is the owner of the phone? ");
        String owner = scanner.nextLine();
        cellPhone1.setOwner(owner);

        // ---- Phone 2 ----
        // The local variables are reused (no "int" or "String" in front this time):
        // phone 1 already holds its own copy of the values, so overwriting the
        // variables does not change phone 1.
        System.out.println("Enter the information for CellPhone 2:");
        // Prompt user for cell phone information and set the properties
        System.out.print("What is the serial number? ");
        serialNumber = scanner.nextInt();  // Read integer value
        cellPhone2.setSerialNumber(serialNumber);
        scanner.nextLine();  // Consume newline

        System.out.print("What model is the phone? ");
        model = scanner.nextLine();  // Read entire line till newline
        cellPhone2.setModel(model);

        System.out.print("Who is the carrier? ");
        carrier = scanner.nextLine();
        cellPhone2.setCarrier(carrier);

        System.out.print("What is the phone number? ");
        phoneNumber = scanner.nextLine();
        cellPhone2.setPhoneNumber(phoneNumber);

        System.out.print("Who is the owner of the phone? ");
        owner = scanner.nextLine();
        cellPhone2.setOwner(owner);

        // Close the scanner: we are done reading input
        scanner.close();

        // Print both phones with one reusable method instead of repeating five println lines twice
        display(cellPhone1);
        display(cellPhone2);

        // Each phone dials the other: the phone before the dot is the caller,
        // the number in the parentheses is the one being called.
        cellPhone1.dial(cellPhone2.getPhoneNumber());
        cellPhone2.dial(cellPhone1.getPhoneNumber());
    }

    // display() is static because it belongs to the application, not to a phone object,
    // so main() (also static) can call it directly. The phone to print is passed in as a parameter.
    public static void display(CellPhone phone) {
        System.out.println("\nCellPhone Details:");
        System.out.println("Serial Number: " + phone.getSerialNumber());
        System.out.println("Model: " + phone.getModel());
        System.out.println("Carrier: " + phone.getCarrier());
        System.out.println("Phone Number: " + phone.getPhoneNumber());
        System.out.println("Owner: " + phone.getOwner());
    }
}
