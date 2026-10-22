package com.pluralsight;

import java.util.Scanner;

/*
 * Demo 06 - Loops
 *
 * This program walks through loops: running the same lines again and again
 * without writing them again. It starts with why loops exist, then shows a
 * while loop that counts, a while loop that keeps asking the user for input,
 * do / while, the difference between while and do / while when the condition
 * is false from the start, the for loop, and finally break and continue.
 *
 * Each block prints a title line, then its results.
 * Run it, read the output next to the code, and change the values.
 *
 * The second block waits for you to type. Type any word and press Enter to go
 * around again; type exit (any capitalization) to stop and let the rest of the
 * program run.
 *
 * Some lines are commented out on purpose. The block of println lines at the
 * top is the long way of doing what the loop below it does: it is there to
 * compare with, and you can remove the comment markers to see both print the
 * same thing.
 *
 * Every loop has the same three parts: a start (where the counter begins), a
 * condition (checked to decide whether to go around again), and an update
 * (what changes each time). Look for those three parts in every block.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Why loops ----------
        // Why loops
        System.out.println("========== Why loops ==========");
        // Without a loop, printing 0 to 9 takes ten lines that are almost the same.
        // Printing 0 to 999 this way would take a thousand lines.
        /*
        System.out.println(0);
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
        System.out.println(8);
        System.out.println(9);
        */
        // The loop below prints exactly the same thing (0 to 9) with one println.
        // Change 10 to 1000 and it still needs only these few lines.
        int counter = 0;                             // start
        while (counter < 10) {                       // condition: checked before every pass
            System.out.println(counter);
            counter++;                               // update: without it the loop never ends
        }
        // When counter reaches 10, 10 < 10 is false and the program moves on.
        System.out.println();

        // ---------- while with a Scanner ----------
        // while with a Scanner
        // A counting loop knows in advance how many times it runs. Here we do not:
        // the loop keeps going until the user types exit.
        System.out.println("========== while with a Scanner ==========");
        Scanner scanner = new Scanner(System.in);
        // operation starts as an empty String so the condition is true the first time
        // and the loop runs at least once.
        String operation = "";
        while (!operation.equalsIgnoreCase("exit")) { // we do not know how many times the user will type
            System.out.println("What is the operation that you want: ");
            operation = scanner.nextLine();          // reads the whole line the user typed

            // The condition of the while is only checked at the top of the next pass,
            // so this if is what lets us react to exit right away.
            if (operation.equalsIgnoreCase("exit")) {
                System.out.println("We are done!");
            } else {
                System.out.println("We continue");
            }
        }
        // equalsIgnoreCase compares Strings without caring about capitals: exit, EXIT and Exit all stop the loop.
        // Strings are compared with equals or equalsIgnoreCase, not with ==.
        System.out.println();

        // ---------- do / while ----------
        // do / while
        // The body comes first and the condition comes last. Here it prints 0 to 9,
        // the same as the while loop at the top.
        System.out.println("========== do / while ==========");
        int number = 0;
        do {
            System.out.println(number);
            number++;
        } while (number < 10);                       // condition is checked after the pass
        // Note the semicolon after while (...) in a do / while. A normal while has none.
        System.out.println();

        // ---------- while vs do / while ----------
        // while vs do / while: condition false from the start
        // Both loops below have the same body and the same condition. The only
        // difference is when the condition is checked. count starts at 11, so
        // count <= 10 is false from the very beginning.
        System.out.println("========== while vs do / while ==========");
        // sum and count are declared here, once. A variable can only be declared once
        // in the same method, so the later blocks reuse them and reset their values
        // instead of writing int sum again.
        int sum = 0;
        int count = 11;
        while (count <= 10) {                       // 11 <= 10 is false: the body never runs
            sum += count; // sum = sum + count
            count++;
        }
        System.out.println(sum);                     // 0: nothing was added

        // Both variables are reset to the same starting values so the two loops get a fair comparison.
        // Writing int sum = 0; here would not compile: sum already exists in this method.
        sum = 0;                                     // same variables, reset for the second loop
        count = 11;
        do {                                         // body runs once before the condition is checked
            sum += count; // sum = sum + count
            count++;
        } while (count <= 10);
        System.out.println(sum);                     // 11: the body ran once and added 11, then the condition stopped it
        // Use do / while when the body must run at least once, for example showing a menu before reading a choice.
        System.out.println();

        // ---------- for ----------
        // for
        // A for loop puts the three parts on one line, so you can see at a glance
        // where it starts, when it stops and how it moves. Prints 0 to 9.
        System.out.println("========== for ==========");
        for (int i = 0; i < 10; i++) {               // start; condition; update in one line
            System.out.println(i);
        }
        // i is declared inside the for, so it exists only inside this loop.
        // That is why each for loop below can declare its own int i.
        System.out.println();

        // ---------- break ----------
        // break
        // Adds 1, 2, 3, ... 10, but stops the whole loop at the first multiple of 3.
        // i % 3 is the remainder of i divided by 3; it is 0 when i is a multiple of 3.
        System.out.println("========== break ==========");
        sum = 0;                                     // reset, declared above
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                break;                               // break: leave the loop now, 4 to 10 never run
            }
            sum += i;
        }
        System.out.println("The sum is: " + sum);   // 3: only 1 and 2 were added
        System.out.println();

        // ---------- continue ----------
        // continue
        // Same loop with continue instead of break: multiples of 3 are skipped,
        // but the loop keeps going until i reaches 10.
        System.out.println("========== continue ==========");
        sum = 0;                                     // reset again: otherwise it would start from 3, the result of the break block
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                continue;                            // continue: skip the rest of this pass, go to the next i
            }
            sum += i;
        }
        System.out.println("The sum is: " + sum);   // 37: 3, 6 and 9 were skipped
        // 1 + 2 + ... + 10 is 55. Taking away 3 + 6 + 9 (18) leaves 37.
        System.out.println();

        // ---------- the same result with an if ----------
        // the same result with an if, no continue
        // continue is not the only way: flipping the condition (!= instead of ==)
        // and putting sum += i inside the if gives the same 37.
        System.out.println("========== the same result with an if ==========");
        sum = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 3 != 0) {                        // only add numbers that are not multiples of 3
                sum += i;
            }
        }
        System.out.println("The sum is: " + sum);   // 37 again
        System.out.println();

        // Close the Scanner once you no longer need input.
        scanner.close();
    }
}
