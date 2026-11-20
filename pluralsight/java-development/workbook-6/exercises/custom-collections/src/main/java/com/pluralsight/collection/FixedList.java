package com.pluralsight.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * FixedList (Workbook 6a, Section 3-3 Limiting Generic Types, Exercise 1)
 *
 * A list with a maximum size. It works like an ArrayList, but it refuses
 * to grow past the size given to the constructor.
 *
 * The <T> after the class name makes the class generic. T is a
 * placeholder for a type that is chosen later, when a list is created:
 *   FixedList<Integer>    every T in this class becomes Integer
 *   FixedList<LocalDate>  every T in this class becomes LocalDate
 * One class is written once and works for any type of item, and the
 * compiler still checks that only that type goes in.
 */
public class FixedList<T> {

    // The real storage is a normal ArrayList, hidden inside this class.
    // It is private, so the only way to put something in is add(), and
    // add() checks the size first
    private List<T> items;
    private int maxSize;

    public FixedList(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }

    // The parameter is a T: for a FixedList<Integer> only an Integer is
    // accepted, anything else is a compile error
    public void add(T item) {
        if (items.size() < maxSize) {
            items.add(item);
        } else {
            // The list is full. Throwing an exception stops the method and
            // tells the caller that something went wrong. Nobody catches it
            // in this program, so the program ends with this message
            throw new IllegalStateException("Maximum size exceeded");
        }
    }

    public List<T> getItems() {
        return items;
    }



}
