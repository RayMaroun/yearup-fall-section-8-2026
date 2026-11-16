package com.pluralsight;

/**
 * Moped (Workbook 5a, Section 1-2 Simple Inheritance, Exercise 2)
 *
 * "extends Vehicle" makes Moped a child class of Vehicle: a Moped IS a
 * Vehicle. It inherits every public member of Vehicle (the getters, the
 * setters, startEngine() and the others) and adds only what is special
 * about a moped: a maximum weight and the ride() method.
 */
public class Moped extends Vehicle {
    // Only a moped has this, so it lives here and not in Vehicle
    private int maxWeight;

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

    // Every class in Java inherits a toString() from the Object class, and
    // that version prints something like com.pluralsight.Moped@1b6d3586.
    // This method replaces it with a readable line. Replacing an inherited
    // method is called overriding, and it is covered in the next module.
    //
    // maxWeight is this class's own variable, so it is used directly. The
    // other values are private in Vehicle, so they are read through the
    // inherited getters
    @Override
    public String toString() {
        return "Moped{" +
                "maxWeight=" + maxWeight +
                ", model='" + getModel() + '\'' +
                ", color='" + getColor() + '\'' +
                ", topSpeed=" + getTopSpeed() +
                ", fuelCapacity=" + getFuelCapacity() +
                ", numberOfPassengers=" + getNumberOfPassengers() +
                ", cargoCapacity=" + getCargoCapacity() +
                '}';
    }
}
