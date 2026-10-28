package com.pluralsight;

/*
 * Demo 05 - Vehicle
 *
 * A small class so the demo can show a list of objects, not only Strings and
 * numbers. Nothing new here: private fields, a constructor, getters and setters.
 *
 * toString() matters for the demo: when a Vehicle is printed, Java calls it and
 * shows the fields instead of something like com.pluralsight.Vehicle@1b6d3586.
 * IntelliJ generates it: Code > Generate > toString().
 */
public class Vehicle {
    private String makeModel;
    private int year;
    private String color;

    public Vehicle(String makeModel, int year, String color) {
        this.makeModel = makeModel;
        this.year = year;
        this.color = color;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "makeModel='" + makeModel + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}
