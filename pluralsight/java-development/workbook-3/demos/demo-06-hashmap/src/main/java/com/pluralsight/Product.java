package com.pluralsight;

/*
 * Demo 06 - Product
 *
 * The value type for the first map in Main: a product with an id, a name and
 * a price. Private fields, constructor, getters and setters, nothing new.
 *
 * getProductID() matters for the map: the product's own id is used as its key,
 * so the key is never typed twice. toString() is what println shows for a
 * product, instead of com.pluralsight.Product@1b6d3586.
 */
public class Product {
    private int productID;
    private String productName;
    private double price;

    public Product(int productID, String productName, double price) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
