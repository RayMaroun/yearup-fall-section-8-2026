package com.pluralsight;

import java.math.BigDecimal;   // BigDecimal lives in java.math, so it needs an import

/**
 * Asset (Workbook 5a, Section 2-2 Abstraction, Exercise 2)
 *
 * The base class, now abstract. "An asset" on its own is only an idea:
 * what you really own is a house, a vehicle or cash. The word abstract
 * says exactly that:
 *   - an abstract class cannot be instantiated: "new Asset(...)" does not
 *     compile. It exists only to be extended
 *   - it can still have variables, a constructor, getters and setters,
 *     and the child classes inherit them as before
 *
 * getValue() is abstract too: it has no body here, because there is no
 * sensible general answer. Every child class MUST supply its own version.
 */
public abstract class Asset {
    // Backing variables: private, the child classes use the getters
    private String description;
    private String dateAcquired;
    private BigDecimal originalCost;

    // An abstract class can have a constructor. Nobody can call it with
    // "new Asset(...)", but the child classes call it with super(...)
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

    // An abstract method: only the signature and a semicolon, no { } body.
    // It is a promise that every asset can tell its value, and it leaves
    // the "how" to each child class. There is no @Override here, because
    // this is where the method is first declared
    public abstract BigDecimal getValue();
}
