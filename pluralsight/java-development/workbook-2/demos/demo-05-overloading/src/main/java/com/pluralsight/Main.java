package com.pluralsight;

/*
 * Demo 05 - Overloading
 *
 * This program walks through overloading: giving several constructors or
 * several methods the same name, as long as their parameter lists differ in
 * number, types or order. It builds Person objects (Person.java) with
 * different constructors and prints what each one set, then calls eat and
 * greet with different arguments to show Java picking the matching version.
 *
 * Each block prints a title line, then its results.
 * Run it, read the output next to the code, and change the values.
 *
 * Some lines are commented out on purpose. The ones marked "error" contain a
 * mistake: remove the // in front of one, run again, and read the error
 * message. That message is part of the lesson.
 *
 * The comment at the end of each line names the version that runs, using the
 * labels written above each constructor and method in Person.java
 * (person_string_int, eat_int, greet_String, ...).
 *
 * One idea runs through the whole file: Java decides which version to run by
 * matching the arguments you pass, in order, against each parameter list.
 * The name alone is not enough.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Overloaded constructors ----------
        System.out.println("========== Overloaded constructors ==========");
        // Java picks the constructor whose parameter list matches the arguments
        // Try adding your own lines, for example new Person(40) or new Person("Sam", 20, 50),
        // and print them the same way.
        Person person1 = new Person();                  // person: nothing set yet
        Person person2 = new Person("Ray");             // person_string
        Person instructor = new Person("Raymond", 35);  // person_string_int
        Person learner = new Person(35, "Brandon");     // person_int_string: order matters, this is a different constructor
        // 50.5 is a double. There is no Person(double), and Java will not silently turn
        // a double into an int because that would lose the .5.
        // Person guest = new Person(50.5);             // error: no constructor takes a double
        // Each line prints name, age, energy. Compare them with what each constructor sets:
        System.out.println(person1.getName() + ", " + person1.getAge() + ", " + person1.getEnergy());          // null, 0, 0: all defaults
        System.out.println(person2.getName() + ", " + person2.getAge() + ", " + person2.getEnergy());          // Ray, 0, 0: only the name was set
        System.out.println(instructor.getName() + ", " + instructor.getAge() + ", " + instructor.getEnergy()); // Raymond, 35, 100: this constructor sets energy to 100
        System.out.println(learner.getName() + ", " + learner.getAge() + ", " + learner.getEnergy());          // Brandon, 35, 0: this one leaves energy at 0
        System.out.println();

        // ---------- Overloaded methods ----------
        // Methods work the same way: one name, several parameter lists.
        // The call with no arguments runs eat(); the call with an int runs eat(int).
        System.out.println("========== Overloaded methods ==========");
        System.out.println("Energy level: " + instructor.getEnergy());                     // 100
        instructor.eat();                               // eat: +20
        System.out.println("Energy level after eat(): " + instructor.getEnergy());         // 120
        instructor.eat(12);                             // eat_int: +12
        System.out.println("Energy level after eat(12): " + instructor.getEnergy());       // 132

        // Three greet methods, three different argument lists, three different outputs.
        instructor.greet();                             // greet
        instructor.greet("Brandon");                    // greet_String
        instructor.greet("Brandon", 35);                // greet_String_int
        // Same values as the line above, in the opposite order. Order is part of the
        // signature, and no greet takes an int first.
        // instructor.greet(35, "Brandon");             // error: order matters, there is no greet(int, String)
        System.out.println();
    }
}
