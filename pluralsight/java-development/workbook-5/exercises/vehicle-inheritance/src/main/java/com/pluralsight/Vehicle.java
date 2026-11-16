package com.pluralsight;

/**
 * Vehicle (Workbook 5a, Section 1-2 Simple Inheritance, Exercise 2)
 *
 * The parent class (also called the base class or superclass). Everything
 * that every kind of vehicle has in common is written here once: the
 * properties, their getters and setters, and the actions any vehicle can do.
 *
 * Moped, Car, SemiTruck and Hovercraft extend this class, so they all get
 * these members without repeating the code (Don't Repeat Yourself).
 *
 * There is no constructor in this exercise. When a class has no constructor,
 * Java supplies an empty one with no parameters, and that is the one used
 * by "new Moped()", "new Car()" and so on.
 */
public class Vehicle {
    // Backing variables: private, so not even the child classes can touch
    // them directly. Children (and everyone else) go through the public
    // getters and setters below
    private String model;
    private String color;
    private int topSpeed;
    private int fuelCapacity;
    private int numberOfPassengers;
    private int cargoCapacity;

    // Getters and setters: public, so they are inherited by every child
    // class and can be called on a Moped, a Car, a SemiTruck or a Hovercraft
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    // Actions every vehicle can do. They are inherited too
    public void startEngine() {
        System.out.println("Engine started.");
    }

    public void stopEngine() {
        System.out.println("Engine stopped.");
    }

    public void accelerate() {
        System.out.println("Vehicle accelerated.");
    }

    public void brake() {
        System.out.println("Brakes applied.");
    }

}
