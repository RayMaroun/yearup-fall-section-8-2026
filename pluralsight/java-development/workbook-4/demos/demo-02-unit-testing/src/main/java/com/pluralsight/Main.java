package com.pluralsight;

/*
 * Demo 02 - Unit Testing
 *
 * This main checks Car by hand: build one, call the methods, print the
 * speed, read the numbers and decide if they are right. That works once.
 * A unit test does the same check, but writes the expected number down
 * and lets JUnit compare, every time you run the tests.
 *
 * Open src/test/java/com/pluralsight/CarTest.java to see the same three
 * checks as tests. Run them with the green arrow next to the class name.
 */
public class Main {
    public static void main(String[] args) {
        // Using the methods by hand: this is what a unit test automates
        System.out.println("========== Using the methods ==========");
        String make = "Ford";
        String model = "Mustang";
        Car car1;
        car1 = new Car(make, model);

        System.out.println(car1.getSpeed());                        // 0: a new car stands still

        car1.accelerate(10);
        car1.accelerate(25);
        System.out.println(car1.getSpeed());                        // 35

        car1.brake(10);
        System.out.println(car1.getSpeed());                        // 25
    }
}
