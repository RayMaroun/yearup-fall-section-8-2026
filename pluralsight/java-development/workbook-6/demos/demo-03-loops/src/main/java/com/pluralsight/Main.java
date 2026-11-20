package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * Demo 03 - Loops
 *
 * A collection holds data. Here it is a list of orders, and each order
 * holds its own list of line items. The project holds:
 *
 *   Order     an order: id, customer and a list of line items
 *   LineItem  one line of an order: product, price and quantity
 *
 * This file processes the list with for loops, one idea per block:
 *
 *   1. The collection: three orders, each with two line items, added to
 *      one list.
 *   2. Filter with a loop: build a new list that keeps only the orders
 *      under $25.
 *   3. Aggregate with a loop: add up the totals of all orders into one
 *      number.
 *
 * Each block that prints starts with a title line, then its results,
 * then a blank line.
 *
 * With a for loop you write every step yourself: walk the list one item
 * at a time, test each item, build the new list, keep the running total.
 *
 * Money is a BigDecimal, not a double, so the usual operators do not
 * work on it. Prices are multiplied with multiply(), totals grow with
 * add(), and "less than" is written compareTo(...) < 0.
 *
 * The next demo does the same work another way.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- The collection ----------
        // allOrders is the list we process in the next blocks. Each order
        // gets its line items first, then the order goes into the list.
        // Prices are created from text, new BigDecimal("100.00"), so the
        // value is exactly what is written.
        List<Order> allOrders = new ArrayList<>();

        Order order1 = new Order(123, "Nadia");
        order1.addItem(new LineItem(11, "Computer", new BigDecimal("100.00"), 1));
        order1.addItem(new LineItem(12, "Mouse", new BigDecimal("15.00"), 2));
        allOrders.add(order1);

        Order order2 = new Order(456, "Omar");
        order2.addItem(new LineItem(13, "Tv", new BigDecimal("200.00"), 1));
        order2.addItem(new LineItem(14, "Keychain", new BigDecimal("10.00"), 2));
        allOrders.add(order2);

        Order order3 = new Order(365, "Felix");
        order3.addItem(new LineItem(15, "ToothBrush", new BigDecimal("2.00"), 3));
        order3.addItem(new LineItem(16, "Notebook", new BigDecimal("4.00"), 1));
        allOrders.add(order3);

        // ---------- Filter with a loop ----------
        // Filtering means keeping only the items that pass a test. With a
        // loop it takes three steps: create an empty list for the results,
        // walk every order, and add the order to the new list when it
        // passes. The original list allOrders is not changed.
        // The order totals are 130.00, 220.00 and 10.00, so only the third
        // order is kept.
        System.out.println("========== Orders under $25 ==========");
        List<Order> impulseOrders = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getTotal().compareTo(new BigDecimal("25")) < 0) {     // no < on a BigDecimal: compareTo(...) < 0 means less than
                impulseOrders.add(order);
            }
        }

        // A second loop prints the orders that were kept.
        for (Order impulseOrder : impulseOrders) {
            System.out.println(impulseOrder);
        }
        System.out.println();

        // ---------- Aggregate with a loop ----------
        // Aggregating means turning many values into one. Start a running
        // total at zero, walk every order and add its total. add() does not
        // change the BigDecimal it is called on: it returns a new one, so
        // the result is stored back in salesTotalAllOrders.
        // 130.00 + 220.00 + 10.00 gives 360.00.
        System.out.println("========== Total sales ==========");
        BigDecimal salesTotalAllOrders = BigDecimal.ZERO;

        for (Order order : allOrders) {
            salesTotalAllOrders = salesTotalAllOrders.add(order.getTotal());
        }

        // %.2f prints the BigDecimal with two decimals.
        System.out.printf("Total: $%.2f%n", salesTotalAllOrders);
        System.out.println();
    }
}
