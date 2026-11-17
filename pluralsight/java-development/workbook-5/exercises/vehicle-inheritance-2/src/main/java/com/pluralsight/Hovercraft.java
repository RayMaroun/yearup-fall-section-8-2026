package com.pluralsight;

/**
 * Hovercraft (Workbook 5a, Section 1-3 Working with Constructors, Exercise 3)
 *
 * A child class of Vehicle with its own constructor. The constructor takes
 * seven values: six for the Vehicle part, passed up with super(...), and
 * the air cushion pressure, which belongs to Hovercraft.
 */
public class Hovercraft extends Vehicle {
    // Only a hovercraft has this, so it lives here and not in Vehicle
    private int airCushionPressure;

    public Hovercraft(String model, String color, int topSpeed, int fuelCapacity, int numberOfPassengers, int cargoCapacity, int airCushionPressure) {
        // First statement: call the Vehicle constructor with the six values
        // that Vehicle stores. Hovercraft cannot set them itself, they are
        // private in Vehicle
        super(model, color, topSpeed, fuelCapacity, numberOfPassengers, cargoCapacity);
        // airCushionPressure stays here: it is the one variable Hovercraft declares itself
        this.airCushionPressure = airCushionPressure;
    }

    public int getAirCushionPressure() {
        return airCushionPressure;
    }

    public void setAirCushionPressure(int airCushionPressure) {
        this.airCushionPressure = airCushionPressure;
    }

    // An action only a hovercraft has
    public void hover() {
        System.out.println("Hovercraft is hovering.");
    }
}
