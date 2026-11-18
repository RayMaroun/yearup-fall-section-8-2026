package com.pluralsight;

import java.math.BigDecimal;

/**
 * Cash (Workbook 5a, Section 2-2 Abstraction, Exercise 2)
 *
 * A child class of Asset: the cash stashed away under the mattress.
 *
 * Questions from the exercise: what issues do you run into when you
 * create the Cash class, and what are your options?
 *
 * Writing only "public class Cash extends Asset { }" gives two errors.
 *
 * 1. Cash does not implement the abstract method getValue().
 *    Asset promises that every asset has a getValue(), but gives it no
 *    body. A class you can create objects from must have a body for
 *    every method. The options:
 *      a) override getValue() in Cash (done below), or
 *      b) declare Cash abstract as well, which only passes the problem
 *         on: then "new Cash(...)" is not allowed either
 *
 * 2. There is no constructor that Java can call in Asset.
 *    With no constructor in Cash, Java supplies an empty one that calls
 *    super() with no arguments. Asset has no constructor without
 *    parameters, only the one that takes three values. The options:
 *      a) write a Cash constructor that calls super(...) with the three
 *         values (done below), or
 *      b) add a constructor without parameters to Asset
 */
public class Cash extends Asset {
    // Cash has no variables of its own, so all three values go up to the
    // Asset constructor. super(...) must be the first statement
    public Cash(String description, String dateAcquired, BigDecimal originalCost) {
        super(description, dateAcquired, originalCost);
    }

    // The body that Asset left out. No calculation is needed
    @Override
    public BigDecimal getValue() {
        return getOriginalCost();  // Cash value does not fluctuate, so it is the same as the original cost
    }
}
