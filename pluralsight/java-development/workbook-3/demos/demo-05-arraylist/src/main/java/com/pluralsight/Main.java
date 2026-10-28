package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Demo 05 - ArrayList
 *
 * An array has a fixed size: new String[5] is five boxes, never six. An
 * ArrayList grows as you add and shrinks as you remove, and it can hold any
 * kind of object: Strings, Integers, your own classes.
 *
 * The things you do with a list, in the order of this demo:
 *   add(x)            put x at the end
 *   get(i) / size()   read by index, count the items (indexes start at 0)
 *   remove(i)         take the item at i out; everything after it moves up
 *   add(i, x)         insert x at i; everything from i moves down
 *   set(i, x)         replace the item at i, size stays the same
 *   Collections.sort  order the list A to Z (or Z to A with reverseOrder)
 *   clear()           empty the list
 *
 * Two details that trip people up:
 *   - the type goes in angle brackets: List<String>, List<Integer>. A list holds
 *     objects, so it is Integer (the class), never int.
 *   - the variable is declared as List (the interface) and created as ArrayList
 *     (the class). Both spellings work; List is the one you will see most.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Create and add: a list of Strings ----------
        System.out.println("========== Create and add: Strings ==========");
        // The array way, for comparison: five boxes, two used, three print null.
        /*
        String[] names = new String[5];
        names[0] = "Raymond";
        names[1] = "Potato";

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        */

        // Alternative: declare it as ArrayList. Same object, narrower type.
        // ArrayList<String> names = new ArrayList<>();
        List<String> names = new ArrayList<>();                     // no size: the list grows as you add
        names.add("Raymond");
        names.add("Potato");

        // Printing the list itself shows [Raymond, Potato]
        // System.out.println(names);

        // The for-each loop reads one item at a time, no index needed
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println();

        // ---------- Create and add: a list of Integers ----------
        System.out.println("========== Create and add: Integers ==========");
        List<Integer> numbers = new ArrayList<>();                  // Integer, not int: a list holds objects

        numbers.add(1);
        numbers.add(2);

        // println on a list prints it in brackets, comma separated
        System.out.println(numbers);
        System.out.println();

        // ---------- Create and add: a list of objects ----------
        System.out.println("========== Create and add: objects ==========");
        List<Vehicle> cars = new ArrayList<>();

        // new Vehicle(...) creates the object, add puts it in the list, in one line
        cars.add(new Vehicle("SUV", 2026, "Black"));
        cars.add(new Vehicle("Benz", 2026, "Red"));

        // Would print [Vehicle{...}, Vehicle{...}] on one line
        // System.out.println(cars);

        for (Vehicle car : cars) {
            System.out.println(car);                                // prints through Vehicle's toString
        }
        System.out.println();

        // ---------- Read by index: get and size ----------
        System.out.println("========== Read by index ==========");
        List<String> kids = new ArrayList<>();

        kids.add("Natalie");
        kids.add("Brittany");
        kids.add("Zachary");

        for (String kid : kids) {
            System.out.println(kid);
        }

        // get(i) reads one item by position, like names[i] on an array
        System.out.println("The first kid is: " + kids.get(0));    // indexes start at 0, like arrays

        // The counted loop, when you need the position too: size() is the count
        for (int i = 0; i < kids.size(); i++) {                     // size(), not length
            System.out.println((i + 1) + " : " + kids.get(i));
        }
        System.out.println();

        // ---------- Remove ----------
        System.out.println("========== Remove ==========");
        // Natalie goes; Brittany becomes index 0, Zachary index 1; size is now 2
        kids.remove(0);                                             // everyone after moves up one place

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }
        System.out.println();

        // ---------- Insert ----------
        System.out.println("========== Insert ==========");
        // Alternative: add with no index puts the item at the END
        // kids.add("Natalie");                                     // adds at the end
        // With an index, the item goes THERE and the rest move down one place
        kids.add(0, "Natalie");                                     // adds at index 0, the rest move down

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }
        System.out.println();

        // ---------- Update ----------
        System.out.println("========== Update ==========");
        // set replaces what is at index 2; nothing moves, the list is still 3 long
        kids.set(2, "Zach");                                        // replaces, the size stays the same

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }

        // contains asks "is this item in the list?" and gives back true or false
        /*
        if (kids.contains("Zach")) {
            System.out.println("It is true");
        }
        */
        System.out.println();

        // ---------- Sort ----------
        System.out.println("========== Sort ==========");
        // Collections is a helper class with methods that work on any list
        Collections.sort(kids);                                     // A to Z

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }
        System.out.println();

        // The second argument says how to compare: reverseOrder gives Z to A
        Collections.sort(kids, Collections.reverseOrder());         // Z to A

        // Not the same thing: reverse flips whatever order the list is in right now
        // Collections.reverse(kids);                               // flips the current order, no sorting

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }
        System.out.println();

        // ---------- Clear ----------
        System.out.println("========== Clear ==========");
        // size() is 0 after clear, so the loop body never runs
        kids.clear();                                               // empties the list: the loop prints nothing

        for (int i = 0; i < kids.size(); i++) {
            System.out.println((i + 1) + " : " + kids.get(i));
        }
        System.out.println();
    }
}
