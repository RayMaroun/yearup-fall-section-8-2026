package com.pluralsight;

import com.pluralsight.classes.Phone;
import com.pluralsight.classes.Radio;
import com.pluralsight.classes.SmartWatch;
import com.pluralsight.classes.Speaker;
import com.pluralsight.interfaces.Chargeable;
import com.pluralsight.interfaces.Playable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Demo 01 - Interfaces
 *
 * The interfaces package holds three interfaces: Playable, Chargeable and
 * Caller. The classes package holds four classes that implement them:
 *
 *   Speaker    implements Playable
 *   Radio      implements Playable
 *   Phone      implements Playable, Chargeable
 *   SmartWatch implements Caller, Chargeable
 *
 * This file uses them, one idea per block:
 *
 *   1. An interface already in use: List is an interface, ArrayList is a
 *      class that implements it.
 *   2. Interfaces and heterogeneous collections: instanceof works with an
 *      interface too. It finds the objects that can be charged, whatever
 *      their class.
 *   3. Default methods: an interface method that comes with a body. A
 *      class inherits it as it is, or overrides it. When two interfaces
 *      give the same default method, the class must override it.
 *   4. Interface as a variable type: a Playable variable holds any object
 *      whose class implements Playable, and can only call the methods
 *      listed in Playable.
 *   5. List interface: a variable declared as List can switch from an
 *      ArrayList to a LinkedList.
 *
 * Each block that prints starts with a title line, then its results,
 * then a blank line.
 *
 * An interface is a list of methods that a class promises to have. It says
 * WHAT can be done (play, stop, charge, call), never HOW: each class writes
 * the how. Inheritance says what an object IS (a Caveman is a Human). An
 * interface says what an object CAN DO, and classes with nothing else in
 * common can share it: a Phone and a SmartWatch are both Chargeable.
 *
 * Interface names are written like class names, with no special prefix:
 * Playable, like Comparable and List in Java itself.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- An interface already in use ----------
        // You have written this line many times. List is an interface: it
        // lists what every list can do (add, get, size and so on).
        // ArrayList is a class that implements List. The variable has the
        // interface type, the object comes from the class.
        List<Integer> numbers = new ArrayList<>();

        // ---------- Interfaces and heterogeneous collections ----------
        // Object is the parent of every class, so a List<Object> accepts
        // anything: here a Radio, a Phone and a SmartWatch. These three
        // classes have no parent class in common except Object.
        System.out.println("========== Interfaces and heterogeneous collections ==========");
        List<Object> things = new ArrayList<>();

        things.add(new Radio("Retro"));
        things.add(new Phone("Pocket"));
        things.add(new SmartWatch());

        // instanceof with an interface asks: does the class of this object
        // implement Chargeable? Phone and SmartWatch do, Radio does not, so
        // two lines are printed for three objects.
        // The variable thing is an Object, and Object has no charge()
        // method. The cast gives us the same object seen as a Chargeable.
        for (Object thing : things) {
            if (thing instanceof Chargeable) {                      // true for the Phone and the SmartWatch, false for the Radio
                // The cast in two steps, with a Chargeable variable.
                // Remove the comment marks and it works: each "is charging"
                // line is printed a second time.
                /*Chargeable chargeable = (Chargeable) thing;
                chargeable.charge();*/

                // The same cast in one line, without an extra variable.
                // The outer parentheses are required: the cast must happen
                // before the call to charge().
                ((Chargeable) thing).charge();                      // safe: instanceof just confirmed the type
            }
        }
        System.out.println();

        // ---------- Default methods ----------
        // SmartWatch implements Caller and Chargeable.
        // print() with no parameters is a default method in both of them,
        // so SmartWatch had to override it: its own version runs.
        // print(String) is a default method in Caller only. SmartWatch.java
        // does not write it: it is inherited with the body from Caller.
        System.out.println("========== Default methods ==========");
        SmartWatch smartWatch = new SmartWatch();
        smartWatch.print();                                         // the override written in SmartWatch
        smartWatch.print("Potato");                                 // the default inherited from Caller
        System.out.println();

        // ---------- Interface as a variable type ----------
        // An interface can be the type of a variable, like a parent class.
        // No object is ever created from an interface: new Playable() does
        // not compile. The variable holds an object of any class that
        // implements Playable.
        // The same two rules as with a parent class:
        //   - The type of the VARIABLE (Playable) decides which methods you
        //     are allowed to call.
        //   - The type of the OBJECT (Phone, then Speaker) decides which
        //     version of the method runs.
        System.out.println("========== Interface as a variable type ==========");
        Playable player = new Phone("Pocket");
        player.play("Potato Song");
        player.print();                                             // Phone's override, which runs the default from Playable

        // The object is a Phone and a Phone can charge, but the variable is
        // a Playable, and Playable has no charge() method. Remove the //
        // and the compiler says:
        // cannot find symbol - method charge(), variable player of type Playable
        //player.charge();
        player = new Speaker("Boom");                               // the same variable now points to a Speaker
        player.play("Potato Song");                                 // same call, the Speaker's own version runs
        player.print();                                             // Speaker has no print(): the default from Playable runs
        System.out.println();

        // ---------- List interface ----------
        // ArrayList and LinkedList are two classes that implement List.
        // They store their elements in different ways, and both have every
        // method of List.
        // With a variable declared as ArrayList, the variable can only hold
        // an ArrayList. A LinkedList is a List, but it is not an ArrayList.
        // Remove the comment marks (and put them around the two lines
        // below, which declare names too) and the compiler says:
        // incompatible types: cannot infer type arguments for LinkedList<>
        /*
        ArrayList<String> names = new ArrayList<>();
        names = new LinkedList<>();*/

        // With a variable declared as List, both objects fit. The rest of
        // the code only uses the methods of List, so the kind of list can
        // change in one place without touching anything else. That is why
        // we declare lists as List and not as ArrayList.
        List<String> names = new ArrayList<>();
        names = new LinkedList<>();
    }

}
