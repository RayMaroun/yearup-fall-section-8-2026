package com.pluralsight;

/**
 * Hovercraft (Workbook 5a, Section 1-2 Simple Inheritance, Exercise 2)
 *
 * A child class of Vehicle: a Hovercraft IS a Vehicle. It inherits every
 * public member of Vehicle and adds what is special about a hovercraft:
 * the pressure of its air cushion and the hover() method.
 */
public class Hovercraft extends Vehicle {
    // Only a hovercraft has this, so it lives here and not in Vehicle
    private int airCushionPressure;

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
