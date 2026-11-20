package com.pluralsight.finance;

import java.math.BigDecimal;

/**
 * Gold (Workbook 6a, Section 2-2 Working with Interfaces, Exercise 2)
 *
 * A child class of FixedAsset. It adds the weight and works out its own
 * value from it.
 */
public class Gold extends FixedAsset {
    private double weight;   // a weight, not money, so it stays a double

    public Gold(double weight) {
        // Every Gold object has the same name, so it is not a parameter
        super("Gold");
        this.weight = weight;
    }

    @Override
    public BigDecimal getValue() {
        // Add your specific calculation for Gold value here
        BigDecimal baseValue = BigDecimal.ZERO; // Set the base value for Gold
        // Calculate the value based on weight and current market rate
        // (100 per unit of weight). multiply() needs a BigDecimal, so the
        // double weight is converted with BigDecimal.valueOf()
        return baseValue.add(BigDecimal.valueOf(weight).multiply(new BigDecimal("100")));
    }

    @Override
    public String toString() {
        return "Gold{" +
                "weight=" + weight +
                '}';
    }
}
