package com.pluralsight;

// Exercise: Method Practice, file 3. A method that takes two Strings and returns one, in "Last, First" order.
public class Format {
    public static void main(String[] args) {
        // Call the method directly inside println: what it returns is what gets printed
        System.out.println(formatName("Bob", "Smith"));

        // Bonus: keep the returned value in a variable first, then print the variable
        String formatted = formatName("Alice", "Johnson");
        System.out.println(formatted);
    }

    // Returns a String: the caller decides what to do with it (print it, store it)
    public static String formatName(String first, String last) {
        return last + ", " + first;   // "Last, First"
    }
}
