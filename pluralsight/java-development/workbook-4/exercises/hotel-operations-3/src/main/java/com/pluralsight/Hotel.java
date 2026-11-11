package com.pluralsight;

/**
 * Hotel (Workbook 4a, Section 2-3 Overloading Methods, Exercise 3)
 *
 * A hotel with two kinds of rooms, king suites and basic double rooms,
 * that can say whether rooms are free today and book them.
 *
 * Two overloaded constructors: one for a hotel with nothing booked yet,
 * one that also takes how many suites and rooms are already booked.
 * No public setters: the only way the numbers change is through bookRoom().
 *
 * getAvailableSuites() and getAvailableRooms() are derived getters:
 * total minus booked, no extra variable to keep in sync.
 */
public class Hotel {
    private String name;
    private int numberOfSuites;
    private int numberOfRooms;
    private int bookedSuites;
    private int bookedBasicRooms;


    // Signature Hotel_String_int_int: nothing booked yet
    public Hotel(String name, int numberOfSuites, int numberOfRooms) {
        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfRooms = numberOfRooms;
        this.bookedSuites = 0;
        this.bookedBasicRooms = 0;
    }

    // Signature Hotel_String_int_int_int_int: some rooms already booked
    public Hotel(String name, int numberOfSuites, int numberOfRooms, int bookedSuites, int bookedRooms) {
        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfRooms = numberOfRooms;
        this.bookedSuites = bookedSuites;
        this.bookedBasicRooms = bookedRooms;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSuites() {
        return numberOfSuites;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getBookedSuites() {
        return bookedSuites;
    }

    public int getBookedBasicRooms() {
        return bookedBasicRooms;
    }

    // Signature from the exercise: bookRoom(int roomCount, boolean suite).
    // Books roomCount suites (suite == true) or basic rooms (suite == false)
    // if that many are free, and says whether it worked
    public boolean bookRoom(int roomCount, boolean suite) {
        if (roomCount <= 0) {
            return false; // Ensure a valid number of rooms is requested
        }

        if (suite) {
            if (getAvailableSuites() >= roomCount) {
                bookedSuites += roomCount;
                return true;
            }
        } else {
            if (getAvailableRooms() >= roomCount) {
                bookedBasicRooms += roomCount;
                return true;
            }
        }

        return false; // Return false if the booking can't be made
    }

    // Derived getters: calculated, no backing variable
    public int getAvailableSuites() {
        return numberOfSuites - bookedSuites;
    }

    public int getAvailableRooms() {
        return numberOfRooms - bookedBasicRooms;
    }
}
