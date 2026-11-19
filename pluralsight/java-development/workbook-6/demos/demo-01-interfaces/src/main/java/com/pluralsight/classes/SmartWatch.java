package com.pluralsight.classes;

import com.pluralsight.interfaces.Caller;
import com.pluralsight.interfaces.Chargeable;

/*
 * SmartWatch implements two interfaces: it makes calls AND it can be charged.
 * It must write call() for Caller and charge() for Chargeable.
 * Both interfaces also give it a default print(), so it has to override
 * that one. This file shows two ways to do it.
 */
public class SmartWatch implements Caller, Chargeable {
    private String lastNumber;

    public SmartWatch() {
        this.lastNumber = "";
    }

    public String getLastNumber() {
        return lastNumber;
    }

    // Promised to Chargeable.
    @Override
    public void charge() {
        System.out.println("The smart watch is charging.");
    }

    // Promised to Caller.
    @Override
    public void call(String number) {
        this.lastNumber = number;
        System.out.println("Calling " + number + " from the smart watch.");
    }

    // Caller and Chargeable both have a default print(), so SmartWatch
    // must override it. With no print() at all in this class, the compiler
    // says:
    // types Caller and Chargeable are incompatible;
    // class SmartWatch inherits unrelated defaults for print() from types Caller and Chargeable
    //
    // First way: keep both inherited versions and call them one after the
    // other, each with InterfaceName.super.print().
    // A class cannot have two print() methods with the same parameters. If
    // you only remove the comment marks here, the compiler says:
    // method print() is already defined in class SmartWatch
    // To try this version, put the comment marks around the other print()
    // below instead. smartWatch.print() then prints two lines:
    // I can charge!
    // This device can make calls
/*    @Override
    public void print() {
        Chargeable.super.print();
        Caller.super.print();
    }*/

    // Second way, the one in use: ignore both inherited versions and write
    // a new body.
    // print(String), the other default method of Caller, is not written
    // here. Only Caller has it, so SmartWatch inherits it as it is.
    @Override
    public void print() {
        System.out.println("The smart watch is printing!");
    }
}
