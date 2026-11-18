package com.pluralsight;

import java.math.BigDecimal;

/**
 * House (Workbook 5a, Section 2-2 Abstraction, Exercise 2)
 *
 * A child class of Asset: a House IS an Asset. It adds an address, a
 * condition, the size of the house and the size of the lot.
 *
 * It overrides getValue(): a house is not worth what was paid for it, its
 * value is worked out from its size and its condition.
 *   condition 1 (excellent)  180.00 per square foot
 *   condition 2 (good)       130.00 per square foot
 *   condition 3 (fair)        90.00 per square foot
 *   condition 4 (poor)        80.00 per square foot
 *   plus 0.25 (25 cents) per square foot of lot size
 */
public class House extends Asset {
    private String address;
    private int condition;      // 1 excellent, 2 good, 3 fair, 4 poor
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, BigDecimal originalCost, String address, int condition, int squareFoot, int lotSize) {
        // First statement: the three values every asset has go up to the
        // Asset constructor. The other four belong to House and stay here
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    // @Override tells the compiler "this method replaces one from the parent".
    // If the name or the parameters did not match Asset's getValue(), the
    // compiler would report it instead of quietly creating a second method
    @Override
    public BigDecimal getValue() {
        BigDecimal value = BigDecimal.ZERO;

        // The price per square foot depends on the condition.
        // BigDecimal has no * operator: multiply() does the work, and it
        // needs another BigDecimal, so the int squareFoot is converted
        // with BigDecimal.valueOf(). The rates are built from a String so
        // they are exactly 180.00, 130.00 and so on
        switch (condition) {
            case 1:
                value = new BigDecimal("180.00").multiply(BigDecimal.valueOf(squareFoot));
                break;
            case 2:
                value = new BigDecimal("130.00").multiply(BigDecimal.valueOf(squareFoot));
                break;
            case 3:
                value = new BigDecimal("90.00").multiply(BigDecimal.valueOf(squareFoot));
                break;
            case 4:
                value = new BigDecimal("80.00").multiply(BigDecimal.valueOf(squareFoot));
                break;
        }
        // Plus 25 cents per square foot of lot. add() returns a NEW
        // BigDecimal, so the result has to be assigned back to value
        value = value.add(new BigDecimal("0.25").multiply(BigDecimal.valueOf(lotSize)));
        return value;
    }
}
