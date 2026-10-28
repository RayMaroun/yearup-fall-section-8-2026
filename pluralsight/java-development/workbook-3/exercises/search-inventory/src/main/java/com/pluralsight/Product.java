package com.pluralsight;

/**
 * Product (Workbook 3a, Section 3-2 Using an ArrayList, Exercise 1)
 *
 * One product the store carries: id, name and price.
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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString() is what Java calls when the object is printed:
    // System.out.println(product) shows Product{id=1234, name='Hammer', price=19.49}
    // instead of something like com.pluralsight.Product@1b6d3586.
    // IntelliJ can generate it: Code > Generate > toString().
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
