package com.pluralsight;

import java.util.Scanner;

/*
 * Demo 03 - Writing to the screen and reading from the keyboard
 *
 * This program walks through Section 3-3: print and println, formatting
 * numbers with String.format and printf, the format specifiers, and reading
 * what the user types with a Scanner. The last block shows the one trap
 * every Java beginner hits: the Enter key left behind after a number.
 *
 * Each block prints a title line, then its output. The Scanner blocks wait
 * for you to type something and press Enter.
 *
 * Try it: in the last block, put // in front of the line marked "eats the
 * Enter", run again, and watch the name question get skipped.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- print vs println ----------
        // print writes the text and stays on the same line.
        // println writes the text and then moves to the next line.
        System.out.println("========== print vs println ==========");
        System.out.print("Hello ");
        System.out.print("World!");
        System.out.println();                                            // ends the line the two prints started
        System.out.println("Hello World!");                              // same text, one call, line ended for you
        System.out.println();

        // ---------- Formatting output ----------
        // A float or double prints with all its digits. To show money with two decimals,
        // format it: String.format builds a new String you can keep, printf formats and
        // prints in one step. %.2f means "a decimal number, 2 digits after the point".
        System.out.println("========== Formatting output ==========");
        float subtotal = 22.87f;
        float tax = subtotal * 0.0825f;
        float totalDue = subtotal + tax;
        System.out.println(totalDue);                                    // 24.756777, too many digits for money
        String formattedTotalDue = String.format("%.2f", totalDue);     // builds the String, prints later
        System.out.println("Total due is: " + formattedTotalDue);
        System.out.printf("Total due is: %.2f%n", totalDue);            // formats and prints at once; %n ends the line
        System.out.println();

        // ---------- Format specifiers ----------
        // A specifier starts with % and ends with a letter for the type:
        // %s a String, %d a whole number, %f a decimal, %n a new line.
        // Between them you can add a width (how many characters) and a precision (.2 = two decimals).
        System.out.println("========== Format specifiers ==========");
        int id = 10135;
        String name = "Brandon Plyers";
        float pay = 5239.77f;
        System.out.printf("%s (id: %d) $%.2f%n", name, id, pay);        // %s String, %d int, %.2f two decimals
        System.out.printf("|%-20s|%n", name);                            // width 20, left aligned (the minus sign)
        System.out.printf("|%20s|%n", name);                             // width 20, right aligned
        System.out.printf("%,d%n", 100000000);                           // the comma adds thousands separators
        System.out.println();

        // ---------- Reading a whole line ----------
        // A Scanner on System.in reads what you type. nextLine() takes the whole line,
        // spaces included, up to the Enter key, and throws the Enter away.
        System.out.println("========== Reading a whole line ==========");
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter your name: ");                           // print, so the answer is typed on the same line
        String userName = myScanner.nextLine();
        System.out.println("Howdy " + userName);
        System.out.println();

        // ---------- Reading numbers ----------
        // nextInt() reads one whole number. It skips spaces and Enters before the number,
        // reads the digits, and stops right after them. Type letters and it throws an error.
        System.out.println("========== Reading numbers ==========");
        System.out.print("Enter the first number: ");
        int firstNumber = myScanner.nextInt();
        System.out.print("Enter the second number: ");
        int secondNumber = myScanner.nextInt();
        int sum = firstNumber + secondNumber;
        System.out.println("The sum is " + sum);
        System.out.println();

        // ---------- The leftover Enter ----------
        // When you type 26 and press Enter, nextInt() takes the 26 and leaves the Enter waiting.
        // Another nextInt() does not mind: it skips it like a space.
        // But nextLine() reads "up to the next Enter", finds that leftover Enter at once,
        // and returns an empty String without waiting for you. The fix is one extra
        // nextLine() after the last number, just to eat the leftover Enter.
        System.out.println("========== The leftover Enter ==========");
        System.out.print("How old are you? ");
        int age = myScanner.nextInt();
        System.out.print("How many pets do you have? ");
        int pets = myScanner.nextInt();                                  // a number after a number: fine, the Enter is skipped
        myScanner.nextLine();                                            // eats the Enter left after the last number
        System.out.print("What is your name? ");
        String fullName = myScanner.nextLine();                          // text after a number: needs the line above
        System.out.println("Hello " + fullName + ", " + age + " years old, " + pets + " pets.");
        System.out.println();

        myScanner.close();                                               // done reading: release the keyboard
    }
}
