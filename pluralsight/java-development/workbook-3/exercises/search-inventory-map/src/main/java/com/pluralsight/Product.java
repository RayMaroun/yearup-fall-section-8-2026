package com.pluralsight;

/**
 * Product (Workbook 3a, Section 4-1 Using a HashMap, Exercise 2)
 *
 * One product the store carries: id, name and price.
 * Read-only: the values are set by the constructor and only read afterwards,
 * so there are getters and no setters.
 */
public class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
