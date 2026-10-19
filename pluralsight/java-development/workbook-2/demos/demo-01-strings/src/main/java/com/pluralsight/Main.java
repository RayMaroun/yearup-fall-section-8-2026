package com.pluralsight;

import java.util.Scanner;               // used in the switched-off input block under toUpperCase
import java.util.regex.Pattern;         // used in the switched-off split block at the end

/*
 * Demo 01 - Strings
 *
 * This program walks through working with text in Java: how to put special
 * characters inside a String, how to compare two Strings correctly, and the
 * everyday String methods: length, trim, toUpperCase, toLowerCase,
 * startsWith, endsWith, charAt, indexOf, lastIndexOf, substring, isEmpty,
 * replace and contains.
 *
 * Each block prints a title line, then its results.
 * Run it, read the output next to the code, and change the values.
 *
 * Some lines are commented out on purpose. A few are alternatives: swap one in
 * for the line under it and see how the output changes. The ones marked
 * "error" contain a mistake: remove the // in front of one, run again, and
 * read the error message. That message is part of the lesson.
 *
 * One idea runs through the whole file: a String never changes. Every method
 * below gives you a NEW String (or a number, or true/false) and leaves the
 * original exactly as it was.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Strings and escape characters ----------
        // A String is written between double quotes. So how do you put a double quote
        // INSIDE the text? You escape it with a backslash: \" means "a real quote
        // character", not "the end of the String". The backslash also starts other
        // special characters: \n is a new line, \t is a tab, \\ is one backslash.
        System.out.println("========== Strings and escape characters ==========");
        // String quotedName = "Raymond 'Maroun'";        // try it: a single quote needs no escape inside double quotes
        // String quotedName = "Raymond\nMaroun";         // try it: \n starts a new line, so this prints on two lines
        // String quotedName = "Raymond\tMaroun";         // try it: \t adds a tab between the two words
        String quotedName = "Raymond \"Maroun\"";         // \" puts a double quote inside the text: Raymond "Maroun"
        System.out.println(quotedName);
        System.out.println();

        // ---------- Comparing Strings: == vs equals ----------
        // A String variable holds a reference: an address that points to the text in
        // memory. == compares those addresses, so it asks "are these the SAME object?".
        // equals compares the characters, so it asks "do these hold the same TEXT?".
        // To compare text, always use equals.
        System.out.println("========== == vs equals ==========");
        String s1 = "ABC";
        String prefix = "A";
        String s2 = prefix + "BC";                        // built while the program runs, so it is a new String object
        // String s2 = new String("ABC");                 // try it: new also creates a new object, == is false again
        System.out.println("This is s1: " + s1);
        System.out.println("This is s2: " + s2);          // both print ABC...
        if (s1 == s2) {                                   // ...but == checks same object in memory, not same text: false
            System.out.println("Both strings are the same!");
        } else {
            System.out.println("Both strings are not the same!");
        }
        // if (s1 == s2) {                                // the wrong way, kept here so you can compare it with the line below
        if (s1.equals(s2)) {                              // equals checks same characters in the same order: true
            System.out.println("Both strings are the same!");
        } else {
            System.out.println("Both strings are not the same!");
        }
        System.out.println();

        // ---------- equalsIgnoreCase ----------
        // equals is strict: "A" and "a" are different characters.
        // equalsIgnoreCase compares the text but treats capitals and small letters as equal.
        // Useful for user input, where someone may type "YES", "yes" or "Yes".
        System.out.println("========== equalsIgnoreCase ==========");
        String s3 = "abc";
        System.out.println(s1.equals(s3));                // false: "ABC" and "abc" differ in capitals
        System.out.println(s1.equalsIgnoreCase(s3));      // true: capitals do not matter here
        System.out.println();

        // ---------- length ----------
        // length() counts every character in the String, including spaces and symbols.
        // Note the () : for a String, length is a method you call.
        System.out.println("========== length ==========");
        String fullName = "Raymond Maroun";
        int length = fullName.length();                   // 7 + 1 space + 6 = 14: the space counts as a character
        System.out.println(length);
        System.out.println();

        // ---------- trim ----------
        // trim() removes spaces (and tabs, new lines) at the START and the END of a String.
        // Spaces in the middle stay. Users often type extra spaces by accident, so trim
        // input before you compare or store it.
        // The [ ] around the value are only there so you can SEE where the spaces are.
        System.out.println("========== trim ==========");
        String username = "   danaw          ";
        System.out.println("[" + username + "]");         // the spaces show inside the brackets
        String trimmedUserName = username.trim();         // removes spaces at both ends only
        System.out.println("[" + trimmedUserName + "]");  // [danaw]
        System.out.println();

        // ---------- toUpperCase and toLowerCase ----------
        // toUpperCase() gives back a copy of the text in capitals, toLowerCase() in small letters.
        // The original String does not change: if you want to keep the result, store it in a variable.
        System.out.println("========== toUpperCase and toLowerCase ==========");
        // The block below reads a name from the keyboard and prints it in capitals.
        // To try it, remove the /* and */ lines around it, run, and type a name.
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String typedName = scanner.nextLine().toUpperCase();    // read the line, then uppercase it right away
        System.out.println(typedName);
        */
        String name = "raymond";
        // String upperCaseName = name.toUpperCase();     // try it: store the result in a new variable, then print that
        System.out.println(name.toUpperCase());           // RAYMOND
        System.out.println(name);                         // raymond: the original never changes
        String studentName = "ADAM";
        System.out.println(studentName.toLowerCase());    // adam
        // Chaining: each method returns a String, so you can call the next method on it directly.
        // Read it left to right: first trim the spaces, then uppercase what is left.
        String instructorName = "          raymond         ";
        System.out.println(instructorName.trim().toUpperCase()); // chaining: trim, then uppercase -> RAYMOND
        System.out.println();

        // ---------- startsWith and endsWith ----------
        // startsWith checks the beginning of a String, endsWith checks the end.
        // Both return true or false, so they fit straight into an if.
        // Both are case sensitive, which is why the text is lowercased first:
        // then "Raymond", "RAYMOND" and "raymond" all match "ray".
        System.out.println("========== startsWith and endsWith ==========");
        String secondName = "Raymond";
        if (secondName.toLowerCase().startsWith("ray")) { // lowercase first so any capitals match
            System.out.println("This is true!");
        } else {
            System.out.println("This is false!");
        }
        String discountCode = "SAVE-50";
        if (discountCode.toLowerCase().endsWith("-50")) { // "-50" has no letters, so lowercasing changes nothing here
            System.out.println("This is true!");
        } else {
            System.out.println("This is false!");
        }
        System.out.println();

        // ---------- charAt ----------
        // charAt(position) gives back the one character at that position, as a char.
        // Positions (indexes) start at 0, not 1:
        //   S  p  a  g  h  e  t  t  i
        //   0  1  2  3  4  5  6  7  8
        // The last valid index is always length() - 1.
        System.out.println("========== charAt ==========");
        String product = "Spaghetti";
        char letter = product.charAt(5);                  // positions start at 0, so index 5 is the 6th letter: e
        System.out.println(letter);
        // char outOfRange = product.charAt(20);          // error: StringIndexOutOfBoundsException, there is no position 20
        System.out.println();

        // ---------- indexOf and lastIndexOf ----------
        // indexOf searches from the start and gives back the position of the FIRST match.
        // lastIndexOf searches from the end and gives back the position of the LAST match.
        // If the text is not found at all, both give back -1. Check for -1 before you use the result.
        System.out.println("========== indexOf and lastIndexOf ==========");
        String productCode = "ACME-12213-potato-123-567";
        // int dashPosition = productCode.lastIndexOf("-"); // try it: position of the last dash, prints 21
        int dashPosition = productCode.indexOf("-");      // position of the first dash: A C M E are 0 to 3, so 4
        System.out.println(dashPosition);
        System.out.println(productCode.indexOf("#"));     // -1: there is no # in the String
        System.out.println();

        // ---------- substring ----------
        // substring cuts out a piece of a String.
        //   substring(start, end): from start up to, but NOT including, end.
        //   substring(start):      from start to the end of the String.
        // Combined with indexOf, it lets you split text around a character you search for.
        System.out.println("========== substring ==========");
        String secondStudentName = "Raymond Maroun";
        int spaceIndex = secondStudentName.indexOf(" ");  // the space is at index 7
        String firstName = secondStudentName.substring(0, spaceIndex);  // index 0 to 6: the end index is not included
        System.out.println(firstName);
        String lastName = secondStudentName.substring(spaceIndex + 1);  // +1 skips the space; no end: to the end of the String
        System.out.println(lastName);
        // Capitalize the first letter: take the first character, uppercase it,
        // then glue the rest of the String (from index 1) back on with +.
        String personName = "raymond";
        String fixedName = personName.substring(0, 1).toUpperCase() + personName.substring(1);
        System.out.println(fixedName);                    // Raymond
        System.out.println();

        // ---------- isEmpty ----------
        // isEmpty() is true only when the String has no characters at all (length 0).
        // A String with only spaces is NOT empty: a space is a character.
        // To treat "only spaces" as empty, trim first and then check.
        System.out.println("========== isEmpty ==========");
        String potato = " ";
        if (potato.isEmpty()) {                           // a space is a character, so not empty
            System.out.println("This is empty!");
        } else {
            System.out.println("This is not empty!");
        }
        String emptyPotato = "";
        if (emptyPotato.isEmpty()) {                      // "" has length 0, so it is empty
            System.out.println("This is empty!");
        } else {
            System.out.println("This is not empty!");
        }
        System.out.println(potato.trim().isEmpty());      // true: trim first removes the space, then check
        System.out.println();

        // ---------- replace ----------
        // replace(old, new) gives back a copy where every match of old is swapped for new.
        // Every match, not only the first one. startDate itself stays "10-15-2026".
        System.out.println("========== replace ==========");
        String startDate = "10-15-2026";
        System.out.println(startDate.replace("-", "/"));  // every match is replaced: 10/15/2026
        System.out.println();

        // ---------- contains ----------
        // contains checks whether a piece of text appears anywhere in the String.
        // It returns true or false and, like most String methods, it is case sensitive.
        System.out.println("========== contains ==========");
        String sentence = "Raymond teaches Java at Year Up";
        System.out.println(sentence.contains("Java"));    // true
        System.out.println(sentence.contains("java"));    // false: case sensitive, small j does not match capital J
        System.out.println();

        // ---------- split ----------
        // split cuts a String into pieces at a separator and gives back an array of Strings.
        // Arrays come later in the course, so this block is switched off for now.
        // When you get there, remove the /* and */ lines around it and run it.
        //
        // Watch the trap: split reads its separator as a pattern (a regular expression),
        // and in a pattern | has a special meaning. split("|") ends up splitting after
        // every single character. Pattern.quote("|") tells Java to treat | as a plain character.
        /*
        System.out.println("========== split ==========");
        String cityList = "Dallas|Ft. Worth|Austin";
        // String[] cities = cityList.split("|");         // trap: | is special in a pattern, splits every character
        String[] cities = cityList.split(Pattern.quote("|")); // quote: treat | as a plain character
        System.out.println(cities.length);                // 3 pieces
        System.out.println(cities[0]);                    // Dallas: the first piece is at index 0
        System.out.println(cities[1]);                    // Ft. Worth
        System.out.println(cities[2]);                    // Austin
        System.out.println();
        */
    }
}
