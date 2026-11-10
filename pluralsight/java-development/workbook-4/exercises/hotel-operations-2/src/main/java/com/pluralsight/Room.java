package com.pluralsight;

/**
 * Room (Workbook 4a, Section 2-2 Creating Classes, Exercise 2)
 *
 * The Section 2-1 Room plus three methods that change its state:
 *   checkIn()   a guest arrives: the room becomes occupied and dirty
 *   checkOut()  the guest leaves: not occupied, but still dirty
 *   cleanRoom() housekeeping cleans it, only once the guest has left
 *
 * The rule from the text: after a check out the room must be cleaned
 * before another guest can check in. That is why checkIn() only works
 * on a room that is neither occupied nor dirty.
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

    // Derived getter: clean and empty
    public boolean isAvailable() {
        return !isOccupied && !isDirty;
    }

    // Only an available room can take a guest; otherwise nothing happens
    public void checkIn() {
        if (!isOccupied && !isDirty) {
            isOccupied = true;
            isDirty = true;
        }
        // Same check written with the derived getter (kept switched off):
        /*if (isAvailable()) {
            isOccupied = true;
            isDirty = true;
        }*/
    }

    // checkOut: lowerCamelCase, two words, capital O
    // The room stays dirty: cleaning is housekeeping's job, not the guest's
    public void checkOut() {
        isOccupied = false;
    }

    // Housekeeping cannot clean while a guest is still inside
    public void cleanRoom() {
        if (!isOccupied) {
            isDirty = false;
        }
    }
}
