package com.pluralsight;

import java.math.BigDecimal;

/*
 * Asset is the parent class of this demo, and it is abstract.
 * It holds what every asset has: a description, the year it was acquired
 * and what it cost. It also says that every asset has a value, without
 * saying how to calculate it: that part is left to the child classes.
 */
public abstract class Asset {                                       // abstract: it can be a parent, but new Asset(...) does not compile
    // An abstract class holds fields like any other class.
    // private: a child class has these three fields, but it reads them
    // through the getters.
    private String description;
    private int yearAcquired;
    private BigDecimal originalCost;                                // money is a BigDecimal, not a double

    // An abstract class still has a constructor. Nobody can call it with
    // new Asset(...), but the child classes call it with super(...) to
    // fill the three fields above.
    public Asset(String description, int yearAcquired, BigDecimal originalCost) {
        this.description = description;
        this.yearAcquired = yearAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public int getYearAcquired() {
        return yearAcquired;
    }

    // Abstract method: only the first line, ended by a semicolon. There is
    // no { } and no body, because Asset does not know how to calculate the
    // value of an asset in general.
    // It is a promise: every child class that is not abstract MUST write
    // its own getValue(), with the same name and the same return type.
    // Only an abstract class is allowed to have abstract methods.
    public abstract BigDecimal getValue();

    // Concrete method: a normal method, with a body. An abstract class can
    // hold finished methods too. The child classes inherit this one as it
    // is and do not have to write anything.
    public void printMessage(){
        System.out.println("Potato");
    }
}
