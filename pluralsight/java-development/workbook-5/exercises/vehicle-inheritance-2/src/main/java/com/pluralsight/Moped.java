package com.pluralsight;

/**
 * Moped (Workbook 5a, Section 1-3 Working with Constructors, Exercise 3)
 *
 * A child class of Vehicle with its own constructor.
 *
 * Constructors are NOT inherited. A Moped object contains a Vehicle part,
 * and that part can only be built by Vehicle's constructor, which needs
 * six values. So the Moped constructor takes seven values: it hands the
 * first six up to Vehicle with super(...) and keeps the seventh for itself.
 *
 * Without the super(...) line this class does not compile: Java would try
 * to call super() with no arguments, and Vehicle has no such constructor.
 */
public class Moped extends Vehicle {
    // Only a moped has this, so it lives here and not in Vehicle
    private int maxWeight;

    public Moped(String model, String color, int topSpeed, int fuelCapacity, int numberOfPassengers, int cargoCapacity, int maxWeight) {
        // super(...) calls the Vehicle constructor. It must be the first
        // statement: the parent part of the object is built before the
        // child part. These six arguments go up to Vehicle, in the same
        // order as Vehicle's constructor lists them
        super(model, color, topSpeed, fuelCapacity, numberOfPassengers, cargoCapacity);
        // maxWeight stays here: it is the one variable Moped declares itself
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    // An action only a moped has
    public void ride() {
        System.out.println("Riding the moped.");
    }

}
