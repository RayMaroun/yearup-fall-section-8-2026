package com.pluralsight;

/**
 * Main (Workbook 5a, Section 1-3 Working with Constructors, Exercise 3)
 *
 * The same four vehicles as in the previous exercise, but every object is
 * now created with constructor arguments instead of empty parentheses
 * followed by setters. "new Moped()" no longer compiles, because none of
 * the classes has a constructor without parameters any more.
 *
 * Every child constructor takes seven values in the same order:
 *   model, color, topSpeed, fuelCapacity, numberOfPassengers, cargoCapacity
 *   (these six travel up to Vehicle through super)
 *   plus one value of its own (maxWeight, numberOfTrailers,
 *   airCushionPressure or numberOfDoors)
 */
public class Main {
    public static void main(String[] args) {
        // One line per object: the constructor fills in every value at once,
        // so an object can never be left half set up.
        // Reading the first line: model "Moped Model", color "Red", top speed 50,
        // fuel capacity 5, 1 passenger, cargo capacity 10, and max weight 100
        Moped slowRide = new Moped("Moped Model", "Red", 50, 5, 1, 10, 100);
        SemiTruck semiTruck = new SemiTruck("SemiTruck Model", "Blue", 120, 200, 2, 500, 2);
        Hovercraft hovercraft = new Hovercraft("Hovercraft Model", "Yellow", 80, 100, 4, 200, 30);
        Car car = new Car("Car Model", "Silver", 180, 60, 5, 400, 4);

        // Test the functionality of the classes and methods
        // Each of these methods exists only in its own child class
        slowRide.ride();
        semiTruck.loadCargo();
        hovercraft.hover();
        car.openTrunk();

        // Accessing Vehicle getters
        // The values printed here were stored by the Vehicle constructor
        // (color, fuel capacity, top speed) and by the Hovercraft constructor
        // (air cushion pressure). The getters still work as before
        System.out.println("Color of the Moped: " + slowRide.getColor());
        System.out.println("Fuel capacity of the SemiTruck: " + semiTruck.getFuelCapacity());
        System.out.println("Air cushion pressure of the Hovercraft: " + hovercraft.getAirCushionPressure());
        System.out.println("Top speed of the Car: " + car.getTopSpeed());
    }
}
