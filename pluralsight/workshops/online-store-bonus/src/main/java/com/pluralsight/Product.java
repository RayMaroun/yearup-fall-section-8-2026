package com.pluralsight;

/**
 * Simple data holder for a store item.
 * <p>
 * One Product is one line of products.csv:
 * SKU|Product Name|Price|Department
 * <p>
 * Fields
 * • id         - string code the user types (the SKU, for example “AV1051”)
 * • name       - product title
 * • price      - selling price
 * • department - the store department the product belongs to (for example “Games”)
 */
public class Product {

    // private: the rest of the program reads the values through the getters,
    // nothing outside this class can change a product after it is created
    private String id;
    private String name;
    private double price;
    private String department;

    // The constructor takes one value per column of the csv file, in the same order
    public Product(String id, String name, double price, String department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    /* Getters (read-only access) */

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDepartment() {
        return department;
    }
}
