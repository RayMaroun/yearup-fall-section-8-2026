package com.pluralsight;

/*
 * Exercise 4 (Section 3-2): one used car on the dealership lot.
 * The fields are private so other classes can only reach them through the
 * getters and setters below (encapsulation).
 */
public class Vehicle {
    private long vehicleId;        // ex: 101121
    private String makeModel;      // ex: Ford Explorer
    private String color;          // ex: Red
    private int odometerReading;   // ex: 32775 (miles)
    private double price;          // ex: 12250.00

    // Constructor: builds a Vehicle with all five values at once.
    // "this.vehicleId" is the field; plain "vehicleId" is the parameter with the same name.
    public Vehicle(long vehicleId, String makeModel, String color, int odometerReading, double price) {
        this.vehicleId = vehicleId;
        this.makeModel = makeModel;
        this.color = color;
        this.odometerReading = odometerReading;
        this.price = price;
    }

    // Getters and setters: one pair per field.

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString() decides what System.out.println(vehicle) prints.
    // Without it, Java would print something like "com.pluralsight.Vehicle@1b6d3586".
    @Override
    public String toString() {
        return vehicleId + " | " + makeModel + " | " + color + " | " + odometerReading + " miles | $" + price;
    }
}
