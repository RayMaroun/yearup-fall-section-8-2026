package com.pluralsight;

import java.math.BigDecimal;   // BigDecimal lives in java.math, not java.lang, so it needs an import

/**
 * MoneyMath (Workbook 4a, Section 4-1 Representing Prices and Balances, Exercise 3)
 *
 * The same additions done three ways, side by side: a float, a double and
 * a BigDecimal. float and double store numbers in binary, and ten cents
 * (0.1) has no exact binary form, so every addition carries a tiny error
 * that adds up. BigDecimal stores the decimal digits exactly.
 *
 * Steps from the text:
 *   1. three variables at 0 (BigDecimal.ZERO for the BigDecimal)
 *   2. loop 10 times adding ten cents, print all three on one line each time
 *   3. reset all three to 0
 *   4. loop 1000 times adding 19.99, print once after the loop
 *   5. compare the variables to each other
 *
 * The answers to the three questions are in the comment at the bottom.
 */
public class MoneyMath {
    public static void main(String[] args) {
        // Step 1: three zeros. 0.0f is a float literal, 0.0 a double literal,
        // and BigDecimal.ZERO is a ready-made constant (no "new" needed)
        float floatTotal = 0.0f;
        double doubleTotal = 0.0;
        BigDecimal bigDecimalTotal = BigDecimal.ZERO;

        // Ten cents as a BigDecimal, built from a String so it is exactly 0.10
        // (new BigDecimal(0.1) would copy the binary approximation of 0.1)
        BigDecimal tenCents = new BigDecimal("0.10");

        // Step 2: add ten cents ten times and print after each addition
        System.out.println("Adding ten cents, 10 times:");
        for (int i = 1; i <= 10; i++) {
            floatTotal += 0.10f;
            doubleTotal += 0.10;
            // BigDecimal math is a method call that returns a NEW object:
            // the result has to be assigned back, or the total never changes
            bigDecimalTotal = bigDecimalTotal.add(tenCents);
            System.out.println(i + ": float = " + floatTotal
                    + "   double = " + doubleTotal
                    + "   BigDecimal = " + bigDecimalTotal);
        }

        // Step 3: back to zero
        floatTotal = 0.0f;
        doubleTotal = 0.0;
        bigDecimalTotal = BigDecimal.ZERO;

        // Step 4: add 19.99 a thousand times, print once at the end
        BigDecimal price = new BigDecimal("19.99");
        for (int i = 0; i < 1000; i++) {
            floatTotal += 19.99f;
            doubleTotal += 19.99;
            bigDecimalTotal = bigDecimalTotal.add(price);
        }
        System.out.println();
        System.out.println("Adding 19.99, 1000 times (exact answer is 19990.00):");
        System.out.println("float = " + floatTotal
                + "   double = " + doubleTotal
                + "   BigDecimal = " + bigDecimalTotal);

        // Step 5: compare the variables to each other
        System.out.println();
        System.out.println("Comparing:");

        // float with double: == works between primitives, but the float is
        // widened to a double first and carries its own error along, so false
        System.out.println("float == double ? " + (floatTotal == doubleTotal));

        // double with BigDecimal: they are different types.
        //   doubleTotal == bigDecimalTotal      does not compile (primitive vs object)
        //   bigDecimalTotal.equals(doubleTotal) compiles but is always false,
        //                                       a BigDecimal never equals a Double
        // The sensible comparison turns the double into a BigDecimal first and
        // uses compareTo (0 means equal). BigDecimal.valueOf(double) keeps the
        // digits the double prints with, so you compare what you saw on screen
        BigDecimal doubleAsBigDecimal = BigDecimal.valueOf(doubleTotal);
        System.out.println("double as BigDecimal = " + doubleAsBigDecimal);
        System.out.println("double compareTo BigDecimal == 0 ? "
                + (doubleAsBigDecimal.compareTo(bigDecimalTotal) == 0));
    }
}

/*
 * Questions to answer:
 *
 * 1. How many cents is the float off after 1000 iterations?
 *    19990.184 - 19990.00 = 0.184 dollars, about 18 cents (18.4) too much.
 *    The double is off by 0.000000000135, far less than a cent, but not zero.
 *
 * 2. Why does the BigDecimal print 1.00 and not 1?
 *    A BigDecimal remembers its scale (the number of decimal places). We added
 *    new BigDecimal("0.10"), which has scale 2, and add() keeps the larger scale
 *    of the two operands, so the total has two decimal places and prints 1.00.
 *    That is what you want for money: 1.00 says "exact to the cent".
 *
 * 3. What happens if you call .add() without assigning the result back?
 *    Nothing changes. A BigDecimal is immutable: add() builds and returns a new
 *    BigDecimal and leaves the original as it was. Writing
 *    bigDecimalTotal.add(tenCents); on its own computes the sum and throws it
 *    away, and the total stays 0 through the whole loop. It has to be
 *    bigDecimalTotal = bigDecimalTotal.add(tenCents);
 */
