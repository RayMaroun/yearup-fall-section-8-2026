package com.pluralsight;

/**
 * Main (Workbook 5a, Section 1-2 Simple Inheritance, Exercise 2)
 *
 * Creates one object of each vehicle type, sets some values with the
 * setters, then tests the methods and the getters.
 *
 * None of the classes has a constructor yet, so every object is created
 * with empty parentheses and filled in afterwards with setters. A value
 * that is never set keeps its default: 0 for an int, null for a String.
 * The last line of the output shows that for the moped.
 */
public class Main {
    public static void main(String[] args) {
        // Question from the exercise: why can we call the Vehicle getters and
        // setters when we never created an instance of the Vehicle class?
        //
        // Because Moped extends Vehicle. A child class inherits the public
        // members of its parent, so setColor() and setFuelCapacity() are part
        // of every Moped even though they are written only once, in Vehicle.
        // "new Moped()" builds ONE object that holds the Vehicle part (color,
        // fuelCapacity, ...) and the Moped part (maxWeight) together.
        // A Moped IS a Vehicle, so no separate Vehicle object is needed
        Moped slowRide = new Moped();
        slowRide.setColor("Red");           // inherited from Vehicle
        slowRide.setFuelCapacity(5);        // inherited from Vehicle

        SemiTruck semiTruck = new SemiTruck();
        semiTruck.setNumberOfTrailers(2);   // SemiTruck's own setter
        semiTruck.setFuelCapacity(100);     // inherited from Vehicle

        Hovercraft hovercraft = new Hovercraft();
        hovercraft.setAirCushionPressure(50);   // Hovercraft's own setter
        hovercraft.setColor("Blue");            // inherited from Vehicle

        Car car = new Car();
        car.setNumberOfDoors(4);            // Car's own setter
        car.setTopSpeed(200);               // inherited from Vehicle

        // Test the functionality of the classes and methods
        // Each of these methods exists only in its own child class:
        // slowRide.hover() or car.ride() would not compile
        slowRide.ride();
        semiTruck.loadCargo();
        hovercraft.hover();
        car.openTrunk();

        // Accessing Vehicle getters
        // getColor(), getFuelCapacity() and getTopSpeed() come from Vehicle,
        // getAirCushionPressure() is Hovercraft's own getter
        System.out.println("Color of the Moped: " + slowRide.getColor());
        System.out.println("Fuel capacity of the SemiTruck: " + semiTruck.getFuelCapacity());
        System.out.println("Air cushion pressure of the Hovercraft: " + hovercraft.getAirCushionPressure());
        System.out.println("Top speed of the Car: " + car.getTopSpeed());

        // Printing an object calls its toString() method automatically.
        // Moped has its own toString(), so we get a readable line with every
        // value. Only color and fuelCapacity were set, the rest show defaults
        System.out.println(slowRide);
    }
}
