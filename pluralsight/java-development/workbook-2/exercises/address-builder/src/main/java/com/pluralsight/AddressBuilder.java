package com.pluralsight;

import java.util.Scanner; // Scanner lives in java.util, so it must be imported

/*
 * Exercise 5: Address Builder (Workbook 2a, Section 1-4).
 * Reads a full name, a billing address and a shipping address,
 * then uses a StringBuilder to put them together into one block of text.
 * A StringBuilder grows in place with append(), instead of creating a brand-new
 * String every time we add text with +.
 */
public class AddressBuilder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please provide the following information:");

        // Get billing address information (the full name is asked first)
        System.out.print("Full name: ");
        String fullName = input.nextLine();

        System.out.print("Billing Street: ");
        String billingStreet = input.nextLine();

        System.out.print("Billing City: ");
        String billingCity = input.nextLine();

        System.out.print("Billing State: ");
        String billingState = input.nextLine();

        System.out.print("Billing Zip: ");
        String billingZip = input.nextLine();

        // Get shipping address information
        System.out.print("Shipping Street: ");
        String shippingStreet = input.nextLine();

        System.out.print("Shipping City: ");
        String shippingCity = input.nextLine();

        System.out.print("Shipping State: ");
        String shippingState = input.nextLine();

        System.out.print("Shipping Zip: ");
        String shippingZip = input.nextLine();
        System.out.println(); // empty line between the answers and the result

        // Build the address string.
        // Each append() adds to the end and returns the same builder, so calls can be chained.
        // "\n" starts a new line; "\n\n" also leaves an empty line between sections.
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(fullName).append("\n\n");

        // Billing block: street on one line, then "City, ST Zip" on the next
        addressBuilder.append("Billing Address:\n");
        addressBuilder.append(billingStreet).append("\n");
        addressBuilder.append(billingCity).append(", ");
        addressBuilder.append(billingState).append(" ");
        addressBuilder.append(billingZip).append("\n\n");

        // Shipping block: same layout as billing
        addressBuilder.append("Shipping Address:\n");
        addressBuilder.append(shippingStreet).append("\n");
        addressBuilder.append(shippingCity).append(", ");
        addressBuilder.append(shippingState).append(" ");
        addressBuilder.append(shippingZip).append("\n");

        // toString() turns the finished StringBuilder into a normal String
        String addressBuilderString = addressBuilder.toString();

        // Print the address
        System.out.println(addressBuilderString);
    }
}
