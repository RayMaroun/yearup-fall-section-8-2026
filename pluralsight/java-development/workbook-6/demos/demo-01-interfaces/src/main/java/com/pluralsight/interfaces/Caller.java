package com.pluralsight.interfaces;

/*
 * Caller is an interface for every device that can make a phone call.
 * It has one method to write, call(), and two default methods named print.
 * SmartWatch implements it.
 */
public interface Caller {
    // Abstract method: every class that implements Caller must write it.
    void call(String number);

    // Default method: the class may override it, it does not have to.
    // Chargeable has a default print() too, so a class that implements
    // both Caller and Chargeable is forced to override this one.
    default void print() {
        System.out.println("This device can make calls");
    }

    // A second default method. It has a String parameter, so for Java it
    // is a different method from print() above (an overload).
    // Only Caller has print(String): there is nothing to choose between,
    // and the class inherits it as it is.
    default void print(String message) {
        System.out.println("This device can make calls " + message);
    }
}
