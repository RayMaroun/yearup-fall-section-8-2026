package com.pluralsight;

import java.util.Scanner;

/*
 * CellPhoneApplication holds main(), the starting point of the program.
 * Phones 1 and 2 are built the old way: an empty object, then the setters.
 * Phone 3 is built with the overloaded five-argument constructor in a single line.
 * All three are printed with display(), then the phones dial each other,
 * using both versions of dial().
 */
public class CellPhoneApplication {
    public static void main(String[] args) {
        // Create two new instances of CellPhone with the parameterless constructor.
        // Phone 3 is NOT created here: it needs its data first (see below).
        CellPhone cellPhone1 = new CellPhone();
        CellPhone cellPhone2 = new CellPhone();

        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // ---- Phone 1: read each answer, then store it with a setter ----
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

        // ---- Phone 2: same steps, reusing the same local variables ----
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


        // ---- Phone 3: collect all five answers first, no setters ----
        System.out.println("Enter the information for CellPhone 3:");
        // Prompt user for cell phone information (stored in the local variables for now)
        System.out.print("What is the serial number? ");
        serialNumber = scanner.nextInt();  // Read integer value
        scanner.nextLine();  // Consume newline

        System.out.print("What model is the phone? ");
        model = scanner.nextLine();  // Read entire line till newline

        System.out.print("Who is the carrier? ");
        carrier = scanner.nextLine();

        System.out.print("What is the phone number? ");
        phoneNumber = scanner.nextLine();

        System.out.print("Who is the owner of the phone? ");
        owner = scanner.nextLine();

        // Now that we have all five values, the overloaded constructor builds the phone in one line.
        // Java picks this constructor because we pass five arguments (int, String, String, String, String).
        CellPhone cellPhone3 = new CellPhone(serialNumber, model, carrier, phoneNumber, owner);

        // Close the scanner: we are done reading input
        scanner.close();

        // Print all three phones; phone 3 shows that the constructor stored every property
        display(cellPhone1);
        display(cellPhone2);
        display(cellPhone3);

        // dial(String): we pass a phone number
        cellPhone1.dial(cellPhone2.getPhoneNumber());
        cellPhone2.dial(cellPhone1.getPhoneNumber());
        // dial(CellPhone): we pass the whole phone and dial() looks up its number.
        // Java chooses the version by the type of the argument.
        cellPhone1.dial(cellPhone3);
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
