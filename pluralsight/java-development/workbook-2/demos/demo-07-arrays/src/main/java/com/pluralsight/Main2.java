package com.pluralsight;

import java.util.Arrays;
import java.util.Collections;

/*
 * Main2 - two ways to loop over an array, and sorting.
 *
 * Runs on its own: start this class, not Main. It prints the same list of
 * names with a for loop and with a for-each loop, then sorts the list A to Z
 * and Z to A. Change the names, add some, and run it again.
 */
public class Main2 {
    public static void main(String[] args) {
        // An array literal can be spread over several lines; the size (9) comes from the values.
        String[] nameList = {
                "Natalie", "Brittany", "Zachary", "Ezra", "Ian", "Siddalee", "Elisha", "Pursalane", "Zephaniah"
        };

        // ---------- A for loop with an index ----------
        // A for loop with an index
        // Use this form when you need the position, for example to print "1. Natalie"
        // or to change a slot: nameList[i] = ...
        System.out.println("========== A for loop with an index ==========");
        for (int i = 0; i < nameList.length; i++) {
            System.out.println(nameList[i]);                         // the index i is available
        }
        System.out.println();

        // ---------- The for-each loop ----------
        // The for-each loop
        // Read it as "for each String name in nameList". Shorter, and no chance of
        // an index going out of bounds, but there is no i: use it when you only need
        // the values. Same output as the block above.
        System.out.println("========== The for-each loop ==========");
        for (String name : nameList) {
            System.out.println(name);                                // the element directly, no index
        }
        System.out.println();

        // ---------- Sort ----------
        // Sort
        // Arrays.sort does not return a new array. It rearranges nameList itself,
        // so the original order is gone after this line.
        System.out.println("========== Sort ==========");
        Arrays.sort(nameList);                                       // sorts the array itself, A to Z
        for (String name : nameList) {
            System.out.println(name);
        }
        // Printing the array directly (System.out.println(nameList)) shows a code like
        // [Ljava.lang.String;@1b6d3586, not the names. Arrays.toString gives readable output.
        System.out.println(Arrays.toString(nameList));               // quick way to print a whole array on one line
        System.out.println();

        // ---------- Sort in reverse ----------
        // Sort in reverse
        // Collections.reverseOrder() tells sort to use the opposite order.
        System.out.println("========== Sort in reverse ==========");
        Arrays.sort(nameList, Collections.reverseOrder());           // Z to A; works on objects like String, not int[]
        for (String name : nameList) {
            System.out.println(name);
        }
        System.out.println();
    }
}
