package com.pluralsight;

/**
 * Room (Workbook 4a, Section 2-3 Overloading Methods, Exercise 3)
 *
 * Unchanged from Section 2-2: the exercise only touches Employee and
 * adds Hotel. Kept so the project still has the whole hotel.
 */
public class Room {
    private int numberOfBeds;
    private double price;
    private boolean isOccupied;
    private boolean isDirty;

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

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public boolean isAvailable() {
        return !isOccupied && !isDirty;
    }

    // A guest can only check into a room that is clean and empty
    public void checkIn() {
        if (!isOccupied && !isDirty) {
            isOccupied = true;
            isDirty = true;
        }
    }

    // checkOut: lowerCamelCase, two words, capital O
    public void checkOut() {
        isOccupied = false;
    }

    // Housekeeping only cleans an empty room
    public void cleanRoom() {
        if (!isOccupied) {
            isDirty = false;
        }
    }
}
