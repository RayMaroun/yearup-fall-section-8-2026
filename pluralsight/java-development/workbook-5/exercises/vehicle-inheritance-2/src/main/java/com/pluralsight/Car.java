package com.pluralsight;

/**
 * Car (Workbook 5a, Section 1-3 Working with Constructors, Exercise 3)
 *
 * A child class of Vehicle with its own constructor. The constructor takes
 * seven values: six for the Vehicle part, passed up with super(...), and
 * the number of doors, which belongs to Car.
 */
public class Car extends Vehicle {
    // Only a car has this, so it lives here and not in Vehicle
    private int numberOfDoors;

    public Car(String model, String color, int topSpeed, int fuelCapacity, int numberOfPassengers, int cargoCapacity, int numberOfDoors) {
        // First statement: call the Vehicle constructor with the six values
        // that Vehicle stores. Car cannot set them itself, they are private
        // in Vehicle
        super(model, color, topSpeed, fuelCapacity, numberOfPassengers, cargoCapacity);
        // numberOfDoors stays here: it is the one variable Car declares itself
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    // Actions only a car has
    public void openTrunk() {
        System.out.println("Trunk opened.");
    }

    public void closeTrunk() {
        System.out.println("Trunk closed.");
    }
}
