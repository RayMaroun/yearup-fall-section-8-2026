package com.pluralsight;

/**
 * SemiTruck (Workbook 5a, Section 1-3 Working with Constructors, Exercise 3)
 *
 * A child class of Vehicle with its own constructor. The constructor takes
 * seven values: six for the Vehicle part, passed up with super(...), and
 * the number of trailers, which belongs to SemiTruck.
 */
public class SemiTruck extends Vehicle {
    // Only a semi truck has this, so it lives here and not in Vehicle
    private int numberOfTrailers;

    public SemiTruck(String model, String color, int topSpeed, int fuelCapacity, int numberOfPassengers, int cargoCapacity, int numberOfTrailers) {
        // First statement: call the Vehicle constructor with the six values
        // that Vehicle stores. SemiTruck cannot set them itself, they are
        // private in Vehicle
        super(model, color, topSpeed, fuelCapacity, numberOfPassengers, cargoCapacity);
        // numberOfTrailers stays here: it is the one variable SemiTruck declares itself
        this.numberOfTrailers = numberOfTrailers;
    }

    public int getNumberOfTrailers() {
        return numberOfTrailers;
    }

    public void setNumberOfTrailers(int numberOfTrailers) {
        this.numberOfTrailers = numberOfTrailers;
    }

    // Actions only a semi truck has
    public void loadCargo() {
        System.out.println("Cargo loaded onto the truck.");
    }

    public void unloadCargo() {
        System.out.println("Cargo unloaded from the truck.");
    }
}
