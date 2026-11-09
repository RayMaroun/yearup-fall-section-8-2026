package com.pluralsight;

/**
 * Room (Workbook 4a, Section 2-1 Creating Classes, Exercise 1)
 *
 * Knows everything about one hotel room: how many beds, the price,
 * and whether it is occupied or dirty.
 *
 * A room is only available if it is clean AND not occupied, so
 * isAvailable() is a derived getter: it has no backing variable
 * and works the answer out from the two booleans.
 */
public class Room {
    // Backing variables: private so only this class can change them
    private int numberOfBeds;
    private double price;
    private boolean isOccupied;
    private boolean isDirty;

    // A new room starts empty and clean
    public Room(int numberOfBeds, double price) {
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.isOccupied = false;
        this.isDirty = false;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    // Boolean getters start with "is" instead of "get"
    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isDirty() {
        return isDirty;
    }

    // Derived getter: no variable called "available", the answer is calculated
    public boolean isAvailable() {
        return !isOccupied && !isDirty;
    }
}
