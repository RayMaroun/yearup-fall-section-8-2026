package com.pluralsight;

/**
 * Reservation (Workbook 4a, Section 2-1 Creating Classes, Exercise 1)
 *
 * Stores one guest stay: the room type, the number of nights and
 * whether the stay falls on a weekend.
 *
 * There is no price field. getPrice() is a derived getter: the price per
 * night comes from the room type (king 139.00, double 124.00) plus 10% on
 * a weekend, worked out every time it is called. getReservationTotal() is
 * derived too: price per night x nights.
 *
 * Why no price field: the exercise says the price per night goes up on a
 * weekend, and a derived getter cannot go stale when setRoomType() or
 * setWeekend() is called later.
 */
public class Reservation {
    private String roomType;
    private int numberOfNights;
    private boolean isWeekend;

    public Reservation(String roomType, int numberOfNights, boolean isWeekend) {
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.isWeekend = isWeekend;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Derived getter: no backing variable. The price per night depends on
    // the room type, plus 10% if the stay is on a weekend.
    // Strings are compared with equals(), never with ==
    public double getPrice() {
        double price = 0;
        if (roomType.equals("king")) {
            price = 139.00;
        } else if (roomType.equals("double")) {
            price = 124.00;
        }
        if (isWeekend) {
            price *= 1.1;
            // price = price + price * (10/100);
            // (the line above is the same idea written out, but 10/100 is
            // integer division and gives 0, so the shorthand x 1.1 is used)
        }
        return price;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    // Setter for a boolean: setWeekend, the getter is isWeekend
    public void setWeekend(boolean weekend) {
        this.isWeekend = weekend;
    }

    // Derived getter: price per night x nights. The weekend rule lives in
    // getPrice() only, so it is never applied twice.
    public double getReservationTotal() {
        return getPrice() * numberOfNights;
    }
}
