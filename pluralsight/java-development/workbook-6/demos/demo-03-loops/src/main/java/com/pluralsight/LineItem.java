package com.pluralsight;

import java.math.BigDecimal;

/*
 * LineItem is one line of an order: a product, its price and how many of
 * it were bought. An Order holds a list of these.
 */
public class LineItem {
    private int productId;
    private String productName;
    private BigDecimal price;                                       // money is a BigDecimal, not a double
    private int quantity;

    public LineItem(int productId, String productName, BigDecimal price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // The total for this line: price times quantity.
    // A BigDecimal has no * operator, so we call multiply(). multiply()
    // only takes another BigDecimal, so the int quantity is turned into a
    // BigDecimal first with BigDecimal.valueOf().
    public BigDecimal getLineTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));        // price * quantity, written the BigDecimal way
    }

    // Prints the line item with all its fields. Order's toString() uses
    // it when it prints its list of line items.
    @Override
    public String toString() {
        return "LineItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
