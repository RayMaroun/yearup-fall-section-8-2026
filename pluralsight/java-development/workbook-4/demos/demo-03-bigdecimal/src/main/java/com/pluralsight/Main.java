package com.pluralsight;

import java.math.BigDecimal;                                        // the exact-decimal type
import java.math.RoundingMode;                                      // needed whenever you round or divide

/*
 * Demo 03 - BigDecimal
 *
 * double and float are stored in binary, and most decimal fractions have no
 * exact binary form, so 0.1 + 0.2 comes out as 0.30000000000000004. Fine for
 * a measurement, wrong for a price. BigDecimal stores the decimal digits as
 * they are, so it is the type for money.
 *
 * Blocks, in order:
 *   1. the problem: a double is inexact
 *   2. the fix: build a BigDecimal from a String (or valueOf from an int)
 *   3. immutability: every operation returns a NEW object, assign it back
 *   4. add / subtract / multiply on a cart total
 *   5. compareTo for order and equality (never == or equals)
 *   6. setScale to round to cents
 *   7. divide needs a scale and a rounding mode too
 *
 * Three lines are commented out on purpose. Uncomment ONE, run, and read the
 * message: that is the lesson.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- 1. the problem ----------
        // A double is stored in binary; most decimal fractions are an approximation
        System.out.println("===== the problem: double is inexact =====");
        System.out.println(" 0.1 + 0.2 as double = " + (0.1 + 0.2));           // 0.30000000000000004
        System.out.println();

        // ---------- 2. the fix ----------
        // A BigDecimal knows the decimal value exactly. It is an object, so you
        // build it with new, and you build it from a STRING, never from a double
        System.out.println("===== the fix: BigDecimal (from a String) =====");
        BigDecimal oneTenth = new BigDecimal("0.1");
        BigDecimal twoTenths = new BigDecimal("0.2");
        System.out.println(" 0.1 + 0.2 as BigDecimal = " + oneTenth.add(twoTenths));

        System.out.println("from a double (wrong): " + new BigDecimal(0.1));    // copies the approximation: 0.1000000000000000055...
        System.out.println("from a String (right): " + new BigDecimal("0.1"));
        System.out.println("from a String: " + new BigDecimal("139.00"));       // keeps the two decimals: scale 2
        System.out.println("from an int (valueOf): " + BigDecimal.valueOf(139)); // valueOf for whole numbers, no new
        System.out.println();

        // ---------- 3. immutability ----------
        // add() does not change total. It builds a NEW BigDecimal and hands it
        // back. If you do not assign the result, it is thrown away
        System.out.println("===== immutability: you MUST reassign =====");
        BigDecimal total = new BigDecimal("100.00");

        total.add(new BigDecimal("25.00"));                                     // result thrown away: total still 100.00
        System.out.println("after add without assigning: " + total);

        BigDecimal result = total.add(new BigDecimal("25.00"));                 // assigned: result is 125.00
        System.out.println("after total.add(...) assigned to result: " + result);
        System.out.println("total is still: " + total);                         // and total did not move
        System.out.println();

        // ---------- 4. add / subtract / multiply ----------
        // A cart total: start at an exact zero and assign after every operation
        System.out.println("===== a cart total (add / subtract / multiply) =====");
        Product keyboard = new Product("Keyboard", new BigDecimal("49.99"));
        Product mouse = new Product("Mouse", new BigDecimal("19.99"));
        Product cable = new Product("USB Cable", new BigDecimal("7.50"));

        BigDecimal cartTotal = BigDecimal.ZERO;                                 // a ready-made exact 0
        cartTotal = cartTotal.add(keyboard.getPrice());                         // assign every time
        cartTotal = cartTotal.add(mouse.getPrice());
        cartTotal = cartTotal.add(cable.getPrice());
        System.out.println("items total: " + cartTotal);                        // 77.48

        BigDecimal coupon = new BigDecimal("5.00");
        cartTotal = cartTotal.subtract(coupon);
        System.out.println("after $5 coupon: " + cartTotal);                    // 72.48

        BigDecimal taxed = cartTotal.multiply(new BigDecimal("1.075"));
        System.out.println("after 7.5% tax (raw): " + taxed);                   // 77.91600: multiply adds the decimals up (2 + 3 = 5 places)
        // taxed = new BigDecimal("75.00");                                     // uncomment: compareTo below gives 0 (exactly on the threshold)
        System.out.println();

        // ---------- 5. compareTo ----------
        // Never == (compares the two object references) and never equals()
        // (says 5.0 and 5.00 are different). compareTo returns -1, 0 or 1
        System.out.println("===== compareTo (order and equality) =====");
        BigDecimal freeShippingThreshold = new BigDecimal("75.00");

        int comparison = taxed.compareTo(freeShippingThreshold);                // -1 smaller, 0 equal, 1 bigger
        System.out.println("compareTo result: " + comparison);

        if (taxed.compareTo(freeShippingThreshold) >= 0) {                      // "taxed >= 75.00", written as a method call
            System.out.println("qualifies for FREE shipping (>= 75)");
        } else {
            System.out.println("add more to reach free shipping");
        }

        BigDecimal five = new BigDecimal("5.0");
        BigDecimal fiveHundredths = new BigDecimal("5.00");
        System.out.println("5.0 equals 5.00: " + five.equals(fiveHundredths));              // false: the scale (1 vs 2) differs
        System.out.println("5.0 compareTo 5.00: " + five.compareTo(fiveHundredths));        // 0: same value
        System.out.println();

        // ---------- 6. setScale ----------
        // Round to a number of decimal places. You must say HOW to round:
        // HALF_UP is school rounding (5 and up goes up)
        System.out.println("===== setScale (rounding to 2 places) =====");
        // BigDecimal broken = taxed.setScale(2);                               // uncomment: ArithmeticException: Rounding necessary (no mode given)

        BigDecimal finalTotal = taxed.setScale(2, RoundingMode.HALF_UP);        // 77.91600 -> 77.92
        System.out.println("final total: $" + finalTotal);
        System.out.println("formatted: " + String.format("%.2f", finalTotal));  // String.format works too
        System.out.println();

        // ---------- 7. divide ----------
        // 10 / 3 never ends, so divide needs the scale and the rounding mode,
        // or it throws. Always give it both
        System.out.println("===== divide gotcha =====");
        // BigDecimal broken = new BigDecimal("10.00").divide(new BigDecimal("3")); // uncomment: ArithmeticException: Non-terminating decimal expansion
        BigDecimal split = new BigDecimal("10.00").divide(new BigDecimal("3"), 10, RoundingMode.HALF_UP);
        System.out.println("$10.00 split 3 ways = " + split);                   // 3.3333333333 (10 places)

        BigDecimal splitCents = new BigDecimal("10.00").divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP);
        System.out.println("$10.00 split 3 ways, in cents = " + splitCents);    // 3.33
    }
}
