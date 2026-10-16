package com.pluralsight;

// Exercise: Method Practice, file 1. Three void methods, each prints one line; main calls each once.
public class Hello {
    public static void main(String[] args) {
        // Call each method once: the code inside it runs, then main carries on
        sayHello();
        sayGoodbye();
        sayGoodMorning();
    }

    // void: these methods do something (print) and return nothing
    public static void sayHello() {
        System.out.println("Hello, World!");
    }

    public static void sayGoodbye() {
        System.out.println("Goodbye!");
    }

    public static void sayGoodMorning() {
        System.out.println("Good morning!");
    }
}
