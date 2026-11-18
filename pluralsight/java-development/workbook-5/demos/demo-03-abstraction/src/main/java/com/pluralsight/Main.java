package com.pluralsight;

import java.math.BigDecimal;

/*
 * Demo 03 - Abstraction
 *
 * Asset.java is an abstract parent class, House.java is its child class.
 * This file uses them, one idea per block:
 *
 *   1. Abstract class: it cannot be instantiated, so new Asset(...) does
 *      not compile.
 *   2. Child class: a class that extends the abstract class and is not
 *      abstract itself can be instantiated as usual.
 *   3. Abstract method: Asset announces getValue() without a body, and
 *      House had to write it.
 *   4. Concrete method: an abstract class can also hold finished methods,
 *      and the child classes inherit them.
 *
 * Each block that prints starts with a title line, then its results,
 * then a blank line.
 *
 * Abstract means "not complete yet". An asset in general is only an idea:
 * we know every asset has a value, but we cannot say how to calculate it
 * until we know which kind of asset it is. So Asset holds what all assets
 * share, and leaves the calculation to each child class.
 *
 * Money is a BigDecimal in this demo, not a double. A double cannot store
 * most decimal numbers exactly, and with money the small errors add up.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Abstract class ----------
        // Asset is marked abstract, so no object can be created from it
        // directly. It only exists to be the parent of other classes.
        // Remove the // and the compiler says:
        // Asset is abstract; cannot be instantiated
        //Asset asset = new Asset("90 inch TV", 2022, new BigDecimal("3500.00"));

        // ---------- Child class ----------
        // House extends Asset and is a normal (concrete) class, so new works.
        // The first three values go up to the Asset constructor, the last
        // three belong to House.
        // The cost is created from a String, "400000.00", so the BigDecimal
        // holds exactly the number that is written.
        House house = new House("5 bedroom", 2023, new BigDecimal("400000.00"),
                "123 El Street", 5000, 5);

        // ---------- Abstract method ----------
        // Asset only says that getValue() exists. The version that runs is
        // the one written in House: (180 * 5000) + (0.25 * 5) = 900001.25
        // %.2f prints the BigDecimal with two digits after the decimal point.
        System.out.println("========== Abstract method ==========");
        System.out.printf("%.2f%n", house.getValue());
        System.out.println();

        // ---------- Concrete method ----------
        // House.java has no printMessage method. It is written once in
        // Asset, with a body, and House inherits it like from any parent.
        System.out.println("========== Concrete method ==========");
        house.printMessage();
        System.out.println();
    }
}
