package com.pluralsight;

import java.math.BigDecimal;                                        // BigDecimal lives in java.math, so it needs an import

/*
 * Product: one item with a name and a price.
 * The price is a BigDecimal, not a double: anything that is money, tax or
 * interest needs the exact cents, and a double cannot promise that.
 */
public class Product {
    private String name;
    private BigDecimal price;                                       // money is a BigDecimal, not a double

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
