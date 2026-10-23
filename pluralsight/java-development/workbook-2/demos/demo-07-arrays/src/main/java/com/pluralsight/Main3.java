package com.pluralsight;

import java.util.Arrays;

/*
 * Main3 - copying arrays.
 *
 * Runs on its own: start this class, not Main. It copies part of an array
 * with System.arraycopy, changes the original to show the copy is separate,
 * then copies a whole array with Arrays.copyOf. Change the start positions
 * and the count, and run it again.
 *
 * The commented-out line copy = colors; is an alternative to try: it looks
 * like a copy but is not one. Remove the // and compare the output.
 */
public class Main3 {
    public static void main(String[] args) {
        // ---------- Copy part of an array ----------
        // Copy part of an array
        System.out.println("========== Copy part of an array ==========");
        String[] colors = {"red", "white", "blue", "yellow", "orange", "green", "brown"};
        String[] copy = new String[7];                               // 7 slots, all null for now
        // An array variable holds a reference (the address of the array), not the values.
        // copy = colors makes both variables point to the same array, so changing
        // colors[1] below would show up in "copy" too.
        //copy = colors;                                             // not a copy: two names for the same array
        // Copies 3 elements, starting at index 2 of colors (blue, yellow, orange),
        // into copy starting at index 2. Slots 0, 1, 5 and 6 of copy are not touched.
        System.arraycopy(colors, 2, copy, 2, 3);                     // source, source start, destination, destination start, how many
        colors[1] = "black";                                         // changes colors only, copy is a separate array
        System.out.println("Colors:");
        for (String color : colors) {
            System.out.println(color);
        }
        System.out.println("Copy:");
        for (String copyElement : copy) {
            System.out.println(copyElement);                         // slots not copied stay null
        }
        // Copy prints null, null, blue, yellow, orange, null, null.
        System.out.println();

        // ---------- Copy a whole array ----------
        // Copy a whole array
        // Arrays.copyOf creates the new array for you, so there is no need for new String[7] first.
        // Passing colors.length copies everything. A smaller number copies only the
        // first elements; a bigger one pads the end with null.
        System.out.println("========== Copy a whole array ==========");
        String[] fullCopy = Arrays.copyOf(colors, colors.length);    // makes a new array with the first n elements
        System.out.println(Arrays.toString(fullCopy));               // includes black: colors was changed before this copy
        System.out.println();
    }
}
