package com.pluralsight;

// FixedList is in another package, so it needs an import
import com.pluralsight.collection.FixedList;

import java.time.LocalDate;

/**
 * Main (Workbook 6a, Section 3-3 Limiting Generic Types, Exercise 1)
 *
 * Tests FixedList with two different types of items, and tests the two
 * ways an add can fail:
 *   - the list is full: the program compiles, and fails while it RUNS
 *   - the item has the wrong type: the program does not COMPILE at all
 *
 * As it is, the program prints 3.
 */
public class Main {
    public static void main(String[] args) {
        // A list of Integer that holds 3 items at most
        FixedList<Integer> numbers = new FixedList<>(3);
        numbers.add(10);
        numbers.add(3);
        numbers.add(92);
        // A fourth item does not fit. The line compiles (43 is an Integer),
        // but when it runs, add() throws an exception and the program stops
        // right here, before anything is printed:
        // Exception in thread "main" java.lang.IllegalStateException: Maximum size exceeded
        // Remove the // to see it
        //numbers.add(43); // this line should fail

        System.out.println(numbers.getItems().size());

        // The same class, now as a list of LocalDate that holds 2 items
        FixedList<LocalDate> dates = new FixedList<>(2);
        dates.add(LocalDate.now());
        dates.add(LocalDate.now());
        // This one fails earlier and for a different reason: 15 is an int,
        // not a LocalDate, so the line does not compile and the program
        // cannot run at all. That is why it stays commented out:
        // incompatible types: int cannot be converted to LocalDate
        //dates.add(15); // this line should fail


    }
}
