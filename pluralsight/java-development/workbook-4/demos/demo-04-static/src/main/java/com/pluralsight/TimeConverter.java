package com.pluralsight;

// A utility class of our own: same shape as Math
public class TimeConverter {
    // Private constructor: nobody can write new TimeConverter().
    // There is nothing to build: the class has no instance fields
    private TimeConverter() {
    }

    // Static method: called on the class name, no object needed
    public static int hoursToMinutes(int hours) {
        return hours * 60;
    }
}
