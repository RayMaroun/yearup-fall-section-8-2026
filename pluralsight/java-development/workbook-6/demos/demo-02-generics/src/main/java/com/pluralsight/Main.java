package com.pluralsight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Demo 02 - Generics
 *
 * Generics let you write a class or a method once and choose the type it
 * works with later, when you use it. The project holds:
 *
 *   Pair<T>                  a generic class: two things of the same type
 *   Duet<T extends Musician> a generic class limited to musicians
 *   Musician                 the parent class, with a perform() method
 *   Cellist, Violinist       two child classes of Musician
 *
 * This file uses them, one idea per block:
 *
 *   1. Generics already in use: List<Integer> and HashMap<Integer, String>
 *      are generic types from Java itself. The type goes between < >.
 *   2. A generic class with Integer: the same Pair class, filled in with
 *      Integer.
 *   3. A generic class with String: the same Pair class again, this time
 *      filled in with String. No second class was written.
 *   4. A generic method: one method that accepts a value of any type, in
 *      place of one overload per type.
 *   5. Limiting generic types: a Duet only accepts musicians, so it can
 *      ask them to perform().
 *
 * Each block that prints starts with a title line, then its results,
 * then a blank line.
 *
 * Without generics you would write a PairOfIntegers class, then a
 * PairOfStrings class with the same code, and so on for every type.
 * With generics you write Pair<T> once. T is a placeholder for a type,
 * and you fill it in each time you create a Pair.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Generics already in use ----------
        // You have used generics since the first ArrayList. The type
        // between < > says what the list holds: here only Integer values.
        // Only class types go between < >, never primitive types. That is
        // why we write the wrapper types Integer, Double, Boolean and not
        // int, double, boolean. Java converts 5 to an Integer for you when
        // you add it.
        List<Integer> numbers = new ArrayList<>();

        // A HashMap needs two types: the type of the keys, then the type
        // of the values. Here an Integer id leads to a String name.
        HashMap<Integer, String> idsAndNames = new HashMap<>();

        // ---------- A generic class with Integer ----------
        // Pair<Integer> fills in T with Integer. For this object, every T
        // in Pair.java now means Integer: both things are Integer values
        // and getLeftThing() returns an Integer.
        // The empty <> on the right is enough: Java reads the type from
        // the left side.
        // swap() exchanges the two things, so the left one goes from 63
        // to 65.
        System.out.println("========== Pair<Integer> ==========");
        Pair<Integer> twoNums = new Pair<>(63, 65);
        System.out.println(twoNums.getLeftThing());
        twoNums.swap();
        System.out.println(twoNums.getLeftThing());
        System.out.println();

        // ---------- A generic class with String ----------
        // The very same Pair class, with T filled in as String this time.
        // Nothing in Pair.java changed: the type is chosen at the moment
        // the object is created, not when the class is written.
        // Here the left thing goes from "Me" to "You".
        System.out.println("========== Pair<String> ==========");
        Pair<String> twoWords = new Pair<>("Me", "You");
        System.out.println(twoWords.getLeftThing());
        twoWords.swap();
        System.out.println(twoWords.getLeftThing());
        System.out.println();

        // ---------- A generic method ----------
        // displayWithLabel() is written once, at the bottom of this file.
        // You never write the type in the call: Java works it out from the
        // argument. With 55, T is Integer. With "Raymond", T is String.
        System.out.println("========== Generic method ==========");
        displayWithLabel("Age", 55);
        displayWithLabel("Name", "Raymond");
        System.out.println();

        // ---------- Limiting generic types ----------
        // Duet is declared as Duet<T extends Musician>: T can only be
        // Musician or a child class of Musician. Here T is Musician, so the
        // Duet holds any two musicians, a Cellist and a Violinist.
        // perform() on the Duet asks each musician to perform, and each
        // one runs its own version of perform().
        System.out.println("========== Duet<Musician> ==========");
        Duet<Musician> duets = new Duet<>(new Cellist("Leo", "Cello"),
                new Violinist("Maya", "Violin"));

        duets.perform();

        // Integer is not a Musician, so a Duet of numbers is refused before
        // the program even runs. Remove the // and the compiler says:
        // type argument Integer is not within bounds of type-variable T
        //Duet<Integer> twoNumbers = new Duet<>(15, 32);
        System.out.println();
    }

    // Before generics you needed one method per type of value: an
    // overload for int, another for String, and one more for every new
    // type. These two say the same thing with a different parameter type.
    // Remove the comment marks and the program still compiles and prints
    // the same lines: Java prefers the overload that matches the argument
    // exactly, so these two run in place of the generic method below.
    /*// displayWithLabel_String_int
    private static void displayWithLabel(String label, int value) {
        System.out.println(label + ": " + value);
    }

    // displayWithLabel_String_String
    private static void displayWithLabel(String label, String value) {
        System.out.println(label + ": " + value);
    }*/

    // Generic method: the <T> before the return type says "this method has
    // its own type placeholder called T". The parameter value is a T, so
    // it can be an Integer, a String or anything else. The method only
    // prints the value, and every type can be printed, so it works for all
    // of them.
    private static <T> void displayWithLabel(String label, T value) {
        System.out.println(label + ": " + value);
    }
}
