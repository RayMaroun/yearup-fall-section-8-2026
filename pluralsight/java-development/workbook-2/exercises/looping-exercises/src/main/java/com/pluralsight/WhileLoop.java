package com.pluralsight;

/*
 * Exercise 1, Step 1 (Section 3-1): print "I love Java" 5 times with a while loop.
 * A while loop checks its condition BEFORE each pass, so if the condition is false
 * at the start, the body never runs at all.
 */
public class WhileLoop {
    public static void main(String[] args) {
        // The counter starts at 0 and tells us how many times we have printed so far.
        int count = 0;

        // Keep looping while we have printed fewer than 5 times (count 0, 1, 2, 3, 4 = 5 passes).
        while (count < 5) {
            System.out.println("I love Java");
            // Move the counter forward; without this line the condition never becomes false (infinite loop).
            count++;
        }
    }
}
