package com.pluralsight;

/*
 * Demo 01 - Java basics
 *
 * This program walks through the first ideas of Java: how a statement is
 * written, what a comment is, the basic data types, how variables are
 * declared and filled, how numbers and characters are written, what a
 * read-only variable is, and how to name things.
 *
 * Each block prints a title line, then its values one per line.
 * Run it, read the output next to the code, and change the values.
 *
 * Some lines are commented out on purpose. Each one contains a mistake.
 * Remove the // in front of one, run again, and read the error message:
 * that message is part of the lesson.
 */
public class Main {

    // A field: a variable that belongs to the class, not to one method.
    // Fields get a default value automatically (false for boolean, 0 for numbers,
    // null for String). We use this one in the "Local vs field" block below.
    static boolean newVariable;

    public static void main(String[] args) {

        // ---------- Syntax and comments ----------
        // A statement ends with a semicolon. System.out.println prints one line.
        // Anything after // on a line is a comment: Java ignores it.
        // Anything between /* and */ is a comment too, and it can span several lines.
        System.out.println("========== Syntax and comments ==========");
        System.out.println("First Message!");
        // System.out.println("Second Message!");        <- commented out: it does not run
        /* System.out.println("Third Message!");
           System.out.println("Fourth Message!"); */     // a block comment: neither line runs
        System.out.println(26);                           // numbers are printed without quotes
        System.out.println();                             // an empty println prints a blank line

        // ---------- Data types ----------
        // Every variable has a type. The type decides what the variable can hold.
        // These five cover most of what you will write in the first weeks.
        System.out.println("========== Data types ==========");
        int age = 26;                   // int: a whole number
        double price = 19.99;           // double: a number with decimals
        char grade = 'A';               // char: exactly one character, in single quotes
        boolean isStudent = true;       // boolean: true or false, nothing else
        String city = "Boston";         // String: text, in double quotes
        System.out.println(age);
        System.out.println(price);
        System.out.println(grade);
        System.out.println(isStudent);
        System.out.println(city);
        System.out.println();

        // ---------- Variables ----------
        // A variable is a named box. Declaring creates the box, initializing fills it
        // the first time. You can do both in one line or in two separate lines.
        System.out.println("========== Variables ==========");
        int firstNumber = 26;           // declaration and initialization in one line
        System.out.println(firstNumber);

        String name;                    // declaration only: the box exists but is empty
        name = "Raymond";               // initialization: the box gets its first value
        System.out.println(name);

        // Several variables of the same type can be declared in one statement.
        // x and z get a value right away, y is declared but still empty.
        int x = 5, y, z = 50;
        System.out.println(x + z);      // reads x and z: prints 55
        // System.out.println(y);       // error: y has no value yet. Remove the // to see the message.
        System.out.println();

        // ---------- Numbers ----------
        // int holds whole numbers up to about 2.1 billion. Bigger whole numbers need long,
        // and a long literal ends with L. double is the default type for decimals;
        // float is a smaller decimal type and its literal ends with f.
        System.out.println("========== Numbers ==========");
        int cityPopulation = 650000;            // fits in an int
        long worldPopulation = 8000000000L;     // too big for an int, so long, and the L is required
        double gpa = 3.75;                      // a decimal literal is a double by default
        float temperature = 36.6f;              // a float literal needs the f
        System.out.println(cityPopulation);
        System.out.println(worldPopulation);
        System.out.println(gpa);
        System.out.println(temperature);
        // int tooBig = 8000000000;             // error: the number is too large for an int
        // float wrong = 36.6;                  // error: 36.6 is a double, it does not fit in a float without f
        System.out.println();

        // ---------- Characters ----------
        // A char is one single character and is written with single quotes.
        // Double quotes make a String, even for one character.
        System.out.println("========== Characters ==========");
        char letter = 'C';              // single quotes for char
        char symbol = '$';              // any single character, not only letters
        System.out.println(letter);
        System.out.println(symbol);
        System.out.println(letter + " " + x);   // joined with a String, it prints as text: C 5
        // char two = 'CD';             // error: a char holds one character, not two
        // char wrongQuotes = "C";      // error: double quotes make a String, not a char
        System.out.println();

        // ---------- Local vs field, default values ----------
        // A field (declared in the class, like newVariable at the top) gets a default
        // value automatically. A local variable (declared inside a method) does not:
        // Java refuses to read it until you give it a value.
        System.out.println("========== Local vs field, default values ==========");
        System.out.println(newVariable);        // field: false by default, nothing was assigned
        int localCount;                         // local: declared, no value
        // System.out.println(localCount);      // error: variable localCount might not have been initialized
        System.out.println();

        // ---------- Read-only variables ----------
        // final means the value can be set once and never changed again.
        // Use it for values that must not move, like the number of days in a week.
        // By convention a final variable is named in UPPER_SNAKE_CASE, but the keyword,
        // not the name, is what makes it read-only.
        System.out.println("========== Read-only variables ==========");
        final int DAYS_IN_WEEK = 7;
        System.out.println("Days in a week: " + DAYS_IN_WEEK);
        final String myName = "Raymond";
        System.out.println("Your name is: " + myName);
        // myName = myName + " Maroun";                  // error: cannot assign a value to final variable myName
        // System.out.println("Full name: " + myName);   // try it: remove final from myName above, then remove these two //
        System.out.println();

        // ---------- Naming ----------
        // Variables use camelCase: first word lowercase, each next word capitalized.
        // Constants use UPPER_SNAKE_CASE. A name may contain letters, digits, _ and $,
        // but it cannot start with a digit and cannot be a Java keyword.
        // Choose names that say what the value is: studentCount, not n.
        System.out.println("========== Naming ==========");
        int studentCount = 25;                  // camelCase
        final double TAX_RATE = 0.0825;         // UPPER_SNAKE_CASE for a constant
        System.out.println(studentCount);
        System.out.println(TAX_RATE);
        // int 1stPlace = 1;                    // error: a name cannot start with a digit
    }
}
