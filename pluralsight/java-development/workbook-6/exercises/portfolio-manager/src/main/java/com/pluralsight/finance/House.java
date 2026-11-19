package com.pluralsight.finance;

import java.math.BigDecimal;

/**
 * House (Workbook 6a, Section 2-1 Implementing Interfaces, Exercise 1)
 *
 * A child class of FixedAsset. It adds the year it was built, its size
 * and the number of bedrooms.
 */
public class House extends FixedAsset {
    private int yearBuilt;
    private int squareFeet;
    private int bedrooms;

    public House(int yearBuilt, int squareFeet, int bedrooms) {
        // Every House object has the same name, so it is not a parameter
        super("House");
        this.yearBuilt = yearBuilt;
        this.squareFeet = squareFeet;
        this.bedrooms = bedrooms;
    }


    @Override
    public BigDecimal getValue() {
        BigDecimal baseValue = BigDecimal.ZERO; // Set the base value for House
        // Every house is worth the same amount here. A real calculation
        // would use the year, the size and the bedrooms
        return baseValue.add(new BigDecimal("10000")); // Dummy value for illustration
    }
}
