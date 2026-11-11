package com.pluralsight;

/**
 * Room (Workbook 4a, Section 3-2 Creating Unit Tests, Exercise 1)
 *
 * The Room from hotel-operations-3, unchanged. The tests in
 * src/test/java/com/pluralsight/RoomTest.java check checkIn(), checkOut()
 * and cleanRoom() against the rules written in the comments below.
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

    // Rule 1: a guest can only check into a clean, empty room
    public void checkIn() {
        if (!isOccupied && !isDirty) {
            isOccupied = true;
            isDirty = true;
        }
    }


    // Rule 2: checking out empties the room but leaves it dirty.
    // checkOut: lowerCamelCase, two words, capital O
    public void checkOut() {
        isOccupied = false;
    }


    // Rule 3: housekeeping only cleans an empty room
    public void cleanRoom() {
        if (!isOccupied) {
            isDirty = false;
        }
    }
}
