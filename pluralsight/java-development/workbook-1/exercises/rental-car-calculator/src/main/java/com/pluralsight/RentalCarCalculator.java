package com.pluralsight;

import java.util.Scanner;

// Exercise: Rental Car Company. Six questions in, four amounts out; if statements choose which fees apply.
public class RentalCarCalculator {

    public static void main(String[] args) {

        // Constants for the fees: named once, used below, never retyped
        final double CAR_RENTAL_RATE = 29.99;
        final double TOLL_TAG_RATE = 3.95;
        final double GPS_RATE = 2.95;
        final double ROADSIDE_ASSISTANCE_RATE = 3.95;
        final double UNDERAGE_DRIVER_SURCHARGE = 0.3;

        // get user inputs
        Scanner input = new Scanner(System.in);
        System.out.print("Enter pickup date (mm/dd/yyyy): ");
        String pickupDate = input.nextLine();
        System.out.print("Enter number of rental days: ");
        int rentalDays = input.nextInt();
        input.nextLine();                   // eats the Enter left after the number
        System.out.print("Do you want an electronic toll tag (y/n)? ");
        String tollTagInput = input.nextLine();
        boolean tollTag = tollTagInput.equalsIgnoreCase("yes") || tollTagInput.equalsIgnoreCase("y");   // the answer becomes true or false; capitals do not matter
        System.out.print("Do you want a GPS (y/n)? ");
        String gpsInput = input.nextLine();
        boolean gps = gpsInput.equalsIgnoreCase("yes") || gpsInput.equalsIgnoreCase("y");
        System.out.print("Do you want roadside assistance (y/n)? ");
        String roadsideAssistanceInput = input.nextLine();
        boolean roadsideAssistance = roadsideAssistanceInput.equalsIgnoreCase("yes") || roadsideAssistanceInput.equalsIgnoreCase("y");
        System.out.print("Enter your age: ");
        int age = input.nextInt();

        // Calculate costs: each option adds its daily rate times the days, only when chosen
        double carRentalCost = CAR_RENTAL_RATE * rentalDays;
        double optionsCost = 0.0;
        if (tollTag) {
            optionsCost += TOLL_TAG_RATE * rentalDays;
        }
        if (gps) {
            optionsCost += GPS_RATE * rentalDays;
        }
        if (roadsideAssistance) {
            optionsCost += ROADSIDE_ASSISTANCE_RATE * rentalDays;
        }
        // 30% of the basic rental, only for drivers under 25; everyone else stays at 0.0
        double underageDriverSurcharge = 0.0;
        if (age < 25) {
            underageDriverSurcharge = carRentalCost * UNDERAGE_DRIVER_SURCHARGE;
        }
        double totalCost = carRentalCost + optionsCost + underageDriverSurcharge;

        // Display results: the four amounts the exercise asks for, money with two decimals
        System.out.println("\nRental Summary");
        System.out.println("--------------");
        System.out.println("Pickup date: " + pickupDate);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Car rental rate: $" + CAR_RENTAL_RATE + " per day");
        System.out.println("Basic car rental: $" + String.format("%.2f", carRentalCost));
        System.out.println("Options cost: $" + String.format("%.2f", optionsCost));
        if (age < 25) {
            System.out.println("Underage driver surcharge: $" + String.format("%.2f", underageDriverSurcharge));
        }
        System.out.println("Total cost: $" + String.format("%.2f", totalCost));

        input.close();
    }
}
