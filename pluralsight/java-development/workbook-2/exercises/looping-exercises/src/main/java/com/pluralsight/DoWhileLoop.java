package com.pluralsight;

/*
 * Exercise 1, Step 1 (Section 3-1): print "I love Java" 5 times with a do/while loop.
 * A do/while loop runs its body FIRST and checks the condition AFTER, so the body
 * always runs at least once. Here the output is the same as WhileLoop.
 */
public class DoWhileLoop {
    public static void main(String[] args) {
        // The counter starts at 0 and tells us how many times we have printed so far.
        int count = 0;

        do {
            System.out.println("I love Java");
            // Move the counter forward so the loop can end.
            count++;
        } while (count < 5); // Checked after each pass; note the semicolon after a do/while.
    }
}
