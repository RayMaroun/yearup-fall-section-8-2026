package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Vehicle (Workbook 5a, Section 2-2 Abstraction, Exercise 2)
 *
 * A child class of Asset: a Vehicle IS an Asset. It adds the make and
 * model, the model year and the odometer reading.
 *
 * It overrides getValue(): a vehicle loses value every year it gets older.
 *   0 to 3 years old     loses 3% of its value each year
 *   4 to 6 years old     loses 6% of its value each year
 *   7 to 10 years old    loses 8% of its value each year
 *   over 10 years old    a flat 1000.00
 *   then 25% less if it has over 100,000 miles,
 *   unless the make/model contains Honda or Toyota
 *
 * Example: a car that cost 25000.00 and is 2 years old is in the 3% group.
 * After year 1 it is worth 25000.00 x 0.97 = 24250.00,
 * after year 2 it is worth 24250.00 x 0.97 = 23522.50
 */
public class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, BigDecimal originalCost, String makeModel, int year, int odometer) {
        // First statement: the three values every asset has go up to the
        // Asset constructor. The other three belong to Vehicle and stay here
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
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

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public BigDecimal getValue() {
        // The age is counted from today's date, so the same vehicle is worth
        // less when the program runs next year
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;

        // The value starts at the original cost. originalCost is private in
        // Asset, so it is read with the getter. A brand new vehicle (age 0)
        // never enters a loop and keeps its full cost
        BigDecimal value = getOriginalCost();

        // Each pass of a loop is one year: the vehicle keeps 97% (or 94%, or
        // 92%) of what it was worth the year before. multiply() returns a NEW
        // BigDecimal, so the result is assigned back to value
        if (age <= 3) { // 0-1-2-3
            for (int i = 0; i < age; i++) {
                value = value.multiply(new BigDecimal("0.97"));
            }
        } else if (age <= 6) { // 4-5-6
            for (int i = 0; i < age; i++) {
                value = value.multiply(new BigDecimal("0.94"));
            }
        } else if (age <= 10) { // 7-8-9-10
            for (int i = 0; i < age; i++) {
                value = value.multiply(new BigDecimal("0.92"));
            }
        } else {
            // Over 10 years old: a flat amount, whatever it cost
            value = new BigDecimal("1000.00");
        }

        // High mileage takes another 25% off (the vehicle keeps 75% of the
        // value), but not for a Honda or a Toyota. The make and model is
        // turned to lower case first, so "HONDA", "Honda" and "honda" all
        // count. The ! in front of the parentheses reads "NOT (honda or toyota)"
        String makeModelLower = makeModel.toLowerCase();
        if (!(makeModelLower.contains("honda") || makeModelLower.contains("toyota"))
                && odometer > 100000) {
            value = value.multiply(new BigDecimal("0.75"));
        }

        return value;
    }
}
