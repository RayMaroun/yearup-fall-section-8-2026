package com.pluralsight.interfaces;

/*
 * Chargeable is an interface for every device that has a battery to fill.
 * It has one method to write, charge(), and one default method, print().
 * Phone and SmartWatch implement it.
 */
public interface Chargeable {
    // Abstract method: every class that implements Chargeable must write it.
    void charge();

    // Default method: it comes with a body, so the class gets it for free.
    // Playable and Caller also have a default print() with no parameters.
    // A class that implements two of these interfaces receives two
    // different bodies for the same method. Java does not choose for it:
    // the class must override print() and decide itself.
    // Phone.java and SmartWatch.java show how.
    default void print() {
        System.out.println("I can charge!");
    }
}
