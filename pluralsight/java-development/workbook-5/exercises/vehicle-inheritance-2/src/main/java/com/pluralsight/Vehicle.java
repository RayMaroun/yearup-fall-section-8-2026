package com.pluralsight;

/**
 * Vehicle (Workbook 5a, Section 1-3 Working with Constructors, Exercise 3)
 *
 * The parent class of Moped, Car, SemiTruck and Hovercraft, now with a
 * constructor that takes a value for each of its private variables.
 *
 * As soon as a class has a constructor of its own, Java stops supplying
 * the empty one. "new Vehicle()" no longer exists, and that changes the
 * child classes: each of them must call this constructor with super(...)
 * and pass the six values up, or it does not compile.
 */
public class Vehicle {
    // Backing variables: private, only this class can change them directly
    private String model;
    private String color;
    private int topSpeed;
    private int fuelCapacity;
    private int numberOfPassengers;
    private int cargoCapacity;

    // The only way to build the Vehicle part of an object: all six values
    // must be given. The child constructors reach it through super(...)
    public Vehicle(String model, String color, int topSpeed, int fuelCapacity, int numberOfPassengers, int cargoCapacity) {
        this.model = model;
        this.color = color;
        this.topSpeed = topSpeed;
        this.fuelCapacity = fuelCapacity;
        this.numberOfPassengers = numberOfPassengers;
        this.cargoCapacity = cargoCapacity;
    }

    // Getters and setters: public, so every child class inherits them
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
