package com.pluralsight;

import java.math.BigDecimal;

/**
 * Data holder for one vehicle on the lot.
 * <p>
 * One Vehicle is one line of inventory.csv (from the second line on):
 * vin|year|make|model|vehicleType|color|odometer|price
 * <p>
 * Fields
 * • vin         - the number the user types to pick a vehicle (whole number in the file)
 * • year        - model year
 * • make, model - for example Ford, Explorer
 * • vehicleType - car, truck, SUV, van
 * • color
 * • odometer    - mileage
 * • price       - asking price, a BigDecimal so the cents stay exact
 * <p>
 * This class knows nothing about files or menus. It only stores values and
 * formats itself for display, so Dealership, DealershipFileManager and
 * UserInterface all work with the same object and none of them repeats the
 * field list.
 */
public class Vehicle {

    // private: the other classes read the values through the getters and
    // change them through the setters, never by touching the field directly
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private BigDecimal price;

    // The constructor takes one value per column of the csv file, in the same
    // order, so the file manager can pass the parsed fields straight through
    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, BigDecimal price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    /* -----------------------------------------------------------------
       Getters and setters, one pair per field (required by the workshop)
       ----------------------------------------------------------------- */
    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /* -----------------------------------------------------------------
       Display
       ----------------------------------------------------------------- */
    // Overriding toString lets the user interface print a vehicle with one
    // println call. The widths (%-10s, %-4d) line the columns up when a list
    // of vehicles is printed; %,.2f gives the price a thousands separator and
    // two decimals. This is the display format only, the file keeps its own.
    @Override
    public String toString() {
        return String.format("%-10d | %-4d | %-10s | %-10s | %-10s | %-10s | %-10d | $%,.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }
}
