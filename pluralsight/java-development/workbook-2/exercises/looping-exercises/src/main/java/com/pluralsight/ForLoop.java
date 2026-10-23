package com.pluralsight;

/*
 * Exercise 1, Step 2 (Section 3-1): count down from 10 to 1, then print "Launch!".
 * A for loop keeps the start value, the condition and the step on one line,
 * which makes it the natural choice when you know how many times to loop.
 *
 * Thread.sleep(1000) pauses the program for 1000 milliseconds (1 second).
 * It can throw an InterruptedException, so main() must declare
 * "throws InterruptedException" or the code does not compile.
 */
public class ForLoop {
    public static void main(String[] args) throws InterruptedException {
        // Loop from 10 to 1: start at 10, keep going while i is at least 1, subtract 1 each pass.
        for (int i = 10; i >= 1; i--) {
            // Print the current countdown value
            System.out.println(i);
            // Pause for one second
            Thread.sleep(1000);
        }
        // Print "Launch!" after the loop ends
        System.out.println("Launch!");
    }

/*  Alternative solution: count UP from 1 to 10 and print 11 - i,
    which gives the same countdown 10, 9, ... 1.
    To try it, comment out the main() above and uncomment this one
    (a class can only have one main(String[] args)).

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            // Print the current countdown value
            System.out.println(11 - i);
            // Pause for one second
            Thread.sleep(1000);
        }
        // Print "Launch!" after the loop ends
        System.out.println("Launch!");
    }*/
}
