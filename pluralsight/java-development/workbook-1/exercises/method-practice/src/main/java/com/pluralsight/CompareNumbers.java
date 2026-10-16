package com.pluralsight;

// Exercise: Method Practice, file 4. Two methods that answer a yes/no question with a boolean.
public class CompareNumbers {
    public static void main(String[] args) {
        // Testing methods: each call returns true or false, println shows it
        System.out.println(isEven(5));     // false
        System.out.println(isEven(10));    // true
        System.out.println(isPositive(-3.5));  // false
        System.out.println(isPositive(7.2));   // true
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;      // a comparison is already a boolean, so return it directly
    }

    public static boolean isPositive(double number) {
        return number > 0;           // true when the number is above zero
    }
}
