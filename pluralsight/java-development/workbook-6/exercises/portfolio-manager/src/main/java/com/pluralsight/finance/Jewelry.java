package com.pluralsight.finance;

import java.math.BigDecimal;

/**
 * Jewelry (Workbook 6a, Section 2-1 Implementing Interfaces, Exercise 1)
 *
 * A child class of FixedAsset: a Jewelry IS a FixedAsset, and because
 * FixedAsset implements Valuable, a Jewelry IS a Valuable as well.
 *
 * It adds the karat and works out its own value from it.
 */
public class Jewelry extends FixedAsset {
    private double karat;   // a measure of the gold, not money, so it stays a double

    public Jewelry(double karat) {
        // First statement: the name goes up to the FixedAsset constructor
        super("Jewelry");
        this.karat = karat;
    }

    // @Override tells the compiler "this method replaces one from the
    // parent". Here it supplies the body that FixedAsset left out
    @Override
    public BigDecimal getValue() {
        // Add your specific calculation for Jewelry value here
        BigDecimal baseValue = BigDecimal.ZERO; // Set the base value for Jewelry
        // BigDecimal has no * operator: multiply() does the work, and it
        // needs another BigDecimal, so the double karat is converted with
        // BigDecimal.valueOf()
        BigDecimal karatValue = BigDecimal.valueOf(karat).multiply(new BigDecimal("1000")); // Assuming each karat is worth $1000
        // add() returns a NEW BigDecimal: the sum
        return baseValue.add(karatValue);
    }
}
