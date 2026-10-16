package com.pluralsight;

import java.util.Scanner;

/*
 * Demo 05 - Methods
 *
 * A method is a named block of code you can call as often as you like.
 * This program walks through Section 4-2: a method that only does something
 * (void), a method that hands a value back (return), how values travel in
 * (parameters and arguments), methods that work with Strings, and the reason
 * methods exist at all: Don't Repeat Yourself.
 *
 * The methods are written below main, but they only run when main calls them.
 * The last block asks for three rectangles; the commented lines above each
 * call are the copy-paste version the method replaced.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- A method with an effect ----------
        // void means "does something, returns nothing". Calling it twice runs it twice.
        System.out.println("========== A method with an effect ==========");
        sayHello();
        sayHello();                                  // call it again: same effect, no copy of the code
        greetUser("Raymond");
        System.out.println();

        // ---------- A method that returns a value ----------
        // The header promises an int, and return hands one back to whoever called.
        // The caller can keep it in a variable or use it straight away.
        System.out.println("========== A method that returns a value ==========");
        int firstNumber = 17;
        int secondNumber = 3;
        int result = addNumbers(firstNumber, secondNumber);   // the returned value lands in result
        System.out.println(result);
        System.out.println(addNumbers(2, 2));        // or use the returned value directly
        System.out.println();

        // ---------- Parameters and arguments ----------
        // The values in the call (a, b) are the arguments. The names in the header
        // (num1, num2) are the parameters: boxes that receive the arguments, in order.
        System.out.println("========== Parameters and arguments ==========");
        int a = 4, b = 9, c = 10, d = 3;
        addAndDisplay(a, b);                         // a and b are the arguments
        addAndDisplay(b, c);                         // num1 and num2 are the parameters
        addAndDisplay(a, d);
        System.out.println();

        // ---------- Methods with Strings ----------
        // A method can take and return any type, String included.
        System.out.println("========== Methods with Strings ==========");
        String fullName = generateFullName("Raymond", "Maroun");
        System.out.println("The full name is: " + fullName);
        System.out.println();

        // ---------- Don't repeat yourself ----------
        // The same four lines for every rectangle, copied three times: one typo, three bugs.
        // Move the lines into one method, make the two numbers parameters, call it three times.
        System.out.println("========== Don't repeat yourself ==========");
        Scanner myScanner = new Scanner(System.in);

        System.out.print("Rectangle 1, enter the length: ");
        int length1 = myScanner.nextInt();
        System.out.print("Rectangle 1, enter the width: ");
        int width1 = myScanner.nextInt();
        /*
        int area1 = length1 * width1;
        int perimeter1 = 2 * (length1 + width1);
        System.out.println("The area is: " + area1);
        System.out.println("The perimeter is: " + perimeter1);
        */
        displayRectangleInfo(length1, width1);

        System.out.print("Rectangle 2, enter the length: ");
        int length2 = myScanner.nextInt();
        System.out.print("Rectangle 2, enter the width: ");
        int width2 = myScanner.nextInt();
        /*
        int area2 = length2 * width2;
        int perimeter2 = 2 * (length2 + width2);
        System.out.println("The area is: " + area2);
        System.out.println("The perimeter is: " + perimeter2);
        */
        displayRectangleInfo(length2, width2);

        System.out.print("Rectangle 3, enter the length: ");
        int length3 = myScanner.nextInt();
        System.out.print("Rectangle 3, enter the width: ");
        int width3 = myScanner.nextInt();
        /*
        int area3 = length3 * width3;
        int perimeter3 = 2 * (length3 + width3);
        System.out.println("The area is: " + area3);
        System.out.println("The perimeter is: " + perimeter3);
        */
        displayRectangleInfo(length3, width3);
        System.out.println();

        myScanner.close();
    }

    // void: the method does something and returns nothing.
    public static void sayHello() {
        System.out.println("Hello, World!");
    }

    public static void greetUser(String name) {
        System.out.println("Hello " + name + "!");
    }

    // int: the method hands back a whole number with return.
    public static int addNumbers(int num1, int num2) {
        /*
        int sum = num1 + num2;
        return sum;
        */
        return num1 + num2;
    }

    public static void addAndDisplay(int num1, int num2) {
        int sum = num1 + num2;
        System.out.printf("%d + %d = %d%n", num1, num2, sum);
    }

    public static String generateFullName(String firstName, String lastName) {
        //String generated = firstName + " " + lastName;
        //return generated;
        return firstName + " " + lastName;
    }

    // One place for the formula: fix it here and every call is fixed.
    public static void displayRectangleInfo(int length, int width) {
        int area = length * width;
        int perimeter = 2 * (length + width);
        System.out.println("The area is: " + area);
        System.out.println("The perimeter is: " + perimeter);
    }
}
