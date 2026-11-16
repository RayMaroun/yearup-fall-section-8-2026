package com.pluralsight;

/**
 * Car (Workbook 5a, Section 1-2 Simple Inheritance, Exercise 2)
 *
 * A child class of Vehicle: a Car IS a Vehicle. It inherits every public
 * member of Vehicle and adds what is special about a car: the number of
 * doors and a trunk that opens and closes.
 */
public class Car extends Vehicle {
    // Only a car has this, so it lives here and not in Vehicle
    private int numberOfDoors;

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
