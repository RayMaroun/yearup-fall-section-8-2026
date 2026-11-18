package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Main (Workbook 5a, Section 2-2 Abstraction, Exercise 2)
 *
 * The same program as assets-manager, re-tested after Asset became an
 * abstract class, and with a Cash asset added to the list.
 *
 * Nothing changes in how the list and the loop work: a variable of type
 * Asset is still allowed, and it can still hold a House, a Vehicle or a
 * Cash object. The only thing that is no longer allowed is creating a
 * plain Asset.
 *
 * The vehicle values depend on the year the program runs in, because the
 * age is counted from today's date. Expected values when run in 2026:
 *   My House        180.00 x 2000 + 0.25 x 5000   = 361250.00
 *   Vacation Home   130.00 x 1500 + 0.25 x 10000  = 197500.00
 *   My Car          5 years old, keeps 94% five times: 25000.00 x 0.94^5 = 18347.60
 *   Tom's Truck     7 years old, keeps 92% seven times: 35000.00 x 0.92^7 = 19524.63
 *   Work Van        8 years old, keeps 92% eight times: 28000.00 x 0.92^8 = 14370.13,
 *                   over 100,000 miles: 14370.13 x 0.75 = 10777.60
 *   Old Car         16 years old: flat 1000.00 (a Honda, so the miles do not count)
 *   Old Truck       17 years old: flat 1000.00, over 100,000 miles: 1000.00 x 0.75 = 750.00
 *   Mattress Cash   worth what was put there: 1500.00
 */
public class Main {
    public static void main(String[] args) {
        // Asset is abstract, but it is still a type: a list of Asset is fine
        ArrayList<Asset> assets = new ArrayList<>();

        // Trying to create an instance of Asset. Remove the // to see the error:
        // 'Asset' is abstract; cannot be instantiated
        //Asset asset1 = new Asset("Something I Own", "2023-03-01", new BigDecimal("500.00"));

        // Create house objects
        // Money amounts are BigDecimal objects built from a String, so the
        // amount is stored exactly as it is written
        House house1 = new House("My House", "2021-01-01", new BigDecimal("200000.00"), "123 Main St", 1, 2000, 5000);
        House house2 = new House("Vacation Home", "2022-05-01", new BigDecimal("300000.00"), "456 Beach Rd", 2, 1500, 10000);

        // Create vehicle objects
        Vehicle vehicle1 = new Vehicle("My Car", "2021-01-01", new BigDecimal("25000.00"), "Toyota Camry", 2021, 50000);
        Vehicle vehicle2 = new Vehicle("Tom's Truck", "2018-06-01", new BigDecimal("35000.00"), "Ford F-150", 2019, 80000);
        // Over 100,000 miles and not a Honda or a Toyota: loses the extra 25%
        Vehicle vehicle3 = new Vehicle("Work Van", "2018-03-15", new BigDecimal("28000.00"), "Ford Transit", 2018, 130000);
        // Over 10 years old: worth a flat 1000.00
        Vehicle vehicle4 = new Vehicle("Old Car", "2012-09-10", new BigDecimal("18000.00"), "Honda Civic", 2010, 150000);
        // Over 10 years old AND over 100,000 miles, not a Honda or a Toyota:
        // the flat 1000.00 also loses the extra 25%
        Vehicle vehicle5 = new Vehicle("Old Truck", "2011-04-20", new BigDecimal("22000.00"), "Chevrolet Silverado", 2009, 180000);

        // Create a cash object
        Cash cash1 = new Cash("Mattress Cash", "2024-02-10", new BigDecimal("1500.00"));

        // Add assets to the list
        assets.add(house1);
        assets.add(house2);
        assets.add(vehicle1);
        assets.add(vehicle2);
        assets.add(vehicle3);
        assets.add(vehicle4);
        assets.add(vehicle5);
        assets.add(cash1);

        // Display asset information
        for (Asset asset : assets) {
            // These getters are defined in Asset, so they work for every item
            System.out.println("Description: " + asset.getDescription());
            System.out.println("Date Acquired: " + asset.getDateAcquired());
            // %.2f prints the amount with two decimal places, %n ends the line
            System.out.printf("Original Cost: $%.2f%n", asset.getOriginalCost());

            // instanceof asks "is this object really a House?". If it is,
            // the downcast (House) gives us a House variable for the same
            // object, and the House methods become available.
            // Cash has nothing extra to show, so it matches neither branch
            if (asset instanceof House) {
                House house = (House) asset;
                System.out.println("Address: " + house.getAddress());
            } else if (asset instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) asset;
                System.out.println("Make/Model: " + vehicle.getMakeModel());
                System.out.println("Year: " + vehicle.getYear());
            }

            // Asset has no body for getValue(), and this call is still safe:
            // every object in the list is a House, a Vehicle or a Cash, and
            // the compiler made sure each of those classes has its own version
            System.out.printf("Value: $%.2f%n", asset.getValue());
            System.out.println();
        }
    }
}
