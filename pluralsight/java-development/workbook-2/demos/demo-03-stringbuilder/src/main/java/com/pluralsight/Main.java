package com.pluralsight;

/*
 * Demo 03 - StringBuilder
 *
 * This program walks through building text step by step: why joining Strings
 * with + over and over creates a pile of throwaway objects, how a
 * StringBuilder collects text in one object with append, and how toString
 * turns the finished result back into a String. The append block shows the
 * line-by-line version switched off, and the live version does the same work
 * inside a method that takes a StringBuilder and returns a String.
 *
 * Each block prints a title line, then its results.
 * Run it, read the output next to the code, and change the values.
 *
 * Some lines are commented out on purpose. The ones marked "error" contain a
 * mistake: remove the // in front of one, put // in front of the line
 * under it (the correct version), run again, and read the error message.
 * That message is part of the lesson.
 *
 * One idea runs through the whole file: a String never changes, a
 * StringBuilder does. Use a String for text you keep; use a StringBuilder
 * while you are still assembling text piece by piece.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Strings are immutable ----------
        // "Immutable" means "cannot be changed". Once a String object exists, its
        // characters stay the same forever. Anything that looks like it changes a
        // String really builds a brand new String object.
        System.out.println("========== Strings are immutable ==========");
        String greeting = "Hello";
        greeting = greeting + ", Raymond";           // greeting now points at the new object, the old one is unreachable
        System.out.println(greeting);                // Hello, Raymond: the variable moved, the old object did not change

        // Building text with += looks harmless, but each += copies the whole String so far
        // into a new object and throws the previous one away.
        // Three changes are nothing; thousands of changes waste a lot of memory and time.
        String countdown = "3 ";
        countdown += "2 ";                           // += is the same story: a new String, the old one thrown away
        countdown += "1 ";                           // and again
        countdown += "Go!";                          // three changes, three new objects
        System.out.println(countdown);               // 3 2 1 Go!
        System.out.println();

        // ---------- append ----------
        // A StringBuilder is a container for text that CAN change. append adds text to
        // the end of the same object, so nothing is copied or thrown away.
        // \n inside the text starts a new line, so the result prints on several lines.
        // The block below is the first version, written line by line in main.
        // It is switched off because the live version does the same appends inside a method.
        // Swap the two to compare: comment the method call, uncomment this block.
        System.out.println("========== append ==========");
        StringBuilder skills = new StringBuilder();  // starts empty
        /*
        skills.append("Git, ");                      // append changes the same object in place, no new String each time
        skills.append("HTML, ");
        skills.append("CSS, ");
        skills.append("and Bootstrap\n");
        skills.append("JavaScript, ");
        skills.append("ES6, ");
        skills.append("jQuery, ");
        skills.append("REST APIs, ");
        skills.append("and Express\n");
        skills.append("Angular\n");
        skills.append("Java\n");
        // A StringBuilder and a String are different types. When the text is finished,
        // call toString() to get a normal String you can store, compare, or return.
        // String mySkills = skills;                 // error: a StringBuilder is not a String
        String mySkills = skills.toString();         // toString hands back the finished text as a String
        */
        // The same appends moved into a method: the method receives the SAME object
        // (not a copy), appends to it, and returns the finished text as a String.
        String mySkills = generateParagraph(skills); // the same appends moved into a method: it fills the builder and hands back a String
        System.out.println(mySkills);                // the last \n plus println leave one empty line after Java
    }

    // Takes a StringBuilder, adds the skills list to it, and returns the result as a String.
    // The return type is String, so whatever comes after return must be a String.
    public static String generateParagraph(StringBuilder stringBuilder) {
        // append changes the same object in place, no new String each time
        stringBuilder.append("Git, ");
        stringBuilder.append("HTML, ");
        stringBuilder.append("CSS, ");
        stringBuilder.append("and Bootstrap\n");
        stringBuilder.append("JavaScript, ");
        stringBuilder.append("ES6, ");
        stringBuilder.append("jQuery, ");
        stringBuilder.append("REST APIs, ");
        stringBuilder.append("and Express\n");
        stringBuilder.append("Angular\n");
        stringBuilder.append("Java\n");
        // return stringBuilder;                     // error: a StringBuilder is not a String
        return stringBuilder.toString();             // toString hands back the finished text as a String
    }
}
