package com.pluralsight;

import java.math.BigDecimal;   // BigDecimal lives in java.math, so it needs an import

/**
 * Asset (Workbook 5a, Section 2-1 Overriding Methods, Exercise 1)
 *
 * The base class: something you own that is worth money. It holds what
 * every asset has in common: a description, the date it was acquired and
 * what it cost.
 *
 * getValue() answers "what is it worth today?". Here the answer is simply
 * the original cost. House and Vehicle extend this class and override
 * getValue() with their own calculation.
 *
 * The cost is money, so it is a BigDecimal and not a double: a BigDecimal
 * stores the decimal digits exactly.
 */
public class Asset {
    // Backing variables: private, the child classes use the getters
    private String description;
    private String dateAcquired;
    private BigDecimal originalCost;

    public Asset(String description, String dateAcquired, BigDecimal originalCost) {
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(String dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public BigDecimal getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(BigDecimal originalCost) {
        this.originalCost = originalCost;
    }

    // The general answer for any asset: it is worth what was paid for it.
    // A child class that knows better replaces this method with its own
    public BigDecimal getValue() {
        return originalCost;
    }
}
