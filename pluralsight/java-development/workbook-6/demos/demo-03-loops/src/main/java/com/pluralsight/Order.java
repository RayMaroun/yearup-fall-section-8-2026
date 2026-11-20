package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * Order is one customer's order. Besides its id and customer, it holds a
 * collection of its own: a list of LineItem objects, one per product.
 * The order's total is not stored anywhere: getTotal() works it out from
 * the line items each time it is called.
 */
public class Order {
    private int id;
    private String customer;
    private List<LineItem> lineItems;

    // A new order starts with an empty list of line items. addItem() fills
    // it one item at a time.
    public Order(int id, String customer) {
        this.id = id;
        this.customer = customer;
        this.lineItems = new ArrayList<>();
    }

    public void addItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    // Aggregate with a loop: many values in, one value out.
    // Start a running total at zero, then walk the line items and add each
    // line total to it. After the last item, the running total is the
    // order's total.
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (LineItem lineItem : lineItems) {
            total = total.add(lineItem.getLineTotal());             // no += on a BigDecimal: add() returns a new value, so we store it back in total
        }
        return total;
    }

    // Prints the order with its id, its customer and its whole list of
    // line items. Printing the list calls toString() on each LineItem.
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}
