package com.pluralsight;

import java.math.BigDecimal;

/*
 * House is a child class of Asset: a House IS an Asset.
 * It is a concrete class (not abstract), so objects can be created from it.
 * In exchange, it must write every abstract method of its parent:
 * here that is getValue().
 */
public class House extends Asset {
    // The three fields written here. The description, the year and the
    // cost come from Asset.
    private String address;
    private int squareFeet;
    private int lotSize;

    // super(...) hands the first three values up to the Asset constructor.
    // The other three belong to House, so they are stored here.
    public House(String description, int yearAcquired, BigDecimal originalCost, String address, int squareFeet, int lotSize) {
        super(description, yearAcquired, originalCost);
        this.address = address;
        this.squareFeet = squareFeet;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public int getLotSize() {
        return lotSize;
    }

    // This is the body that Asset left out: how to calculate the value of
    // a house. The method is required. If you delete it, the compiler says:
    // House is not abstract and does not override abstract method getValue() in Asset
    //
    // The formula is (180 * squareFeet) + (0.25 * lotSize).
    // A BigDecimal is an object, so the operators * and + do not work on
    // it. We call its methods instead: multiply() for * and add() for +.
    // Each call gives back a new BigDecimal with the result.
    // BigDecimal.valueOf(...) turns a whole number into a BigDecimal.
    // 0.25 is created from the String "0.25" so that it is exactly 0.25.
    @Override
    public BigDecimal getValue() {
        return BigDecimal.valueOf(180)
                .multiply(BigDecimal.valueOf(squareFeet))           // 180 * squareFeet
                .add(new BigDecimal("0.25")                         // + (0.25 * lotSize): the inner multiply runs first
                        .multiply(BigDecimal.valueOf(lotSize)));
    }
}
