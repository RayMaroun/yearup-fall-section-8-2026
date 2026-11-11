package com.pluralsight;

/*
 * Demo 04 - Static
 *
 * Two words to keep apart:
 *   instance: belongs to each object, one copy per object, reached through
 *             the object (alice.printDetails())
 *   static:   belongs to the class, one copy for everyone, reached through
 *             the class name (Learner.changeSchool(...)), exists even when
 *             no object has been built
 *
 * Blocks, in order:
 *   1. instance members: two Learner objects, two names, two grades
 *   2. a static member: one schoolName, changed once, both objects see it
 *   3. Math is a utility class: only static members, never new Math()
 *   4. a utility class of our own, TimeConverter, built the same way
 *
 * Two lines are commented out on purpose. Uncomment one and the project
 * will not compile: read the error, it is the point.
 */
public class Main {
    /* Static inside Main itself: main is static, so it can reach a static field
       and a static method directly, but NOT an instance method (no Main object exists).
       Swap this block in for the main below to see it.
    public static String lastName = "Maroun";

    public static void main(String[] args) {
        System.out.println(lastName);
        greetEveryone();
    }

    public void printLastName() {                                   // instance method: main cannot call it without a Main object
        System.out.println(lastName);
    }

    public static void greetEveryone() {
        System.out.println("Hello Everyone did you miss me?");
    }*/

    public static void main(String[] args) {
        // ---------- 1. Instance members ----------
        // Two objects, two copies of name and grade. Each prints its own.
        System.out.println("========== Instance members ==========");
        Learner alice = new Learner("Alice", 10);
        Learner bob = new Learner("Bob", 9);

        alice.printDetails();
        bob.printDetails();
        System.out.println();

        // ---------- 2. Static member ----------
        // schoolName lives once, on the class. It is changed through the class
        // name, no object involved, and every Learner sees the new value
        System.out.println("========== Static member: shared by all ==========");
        Learner.changeSchool("Lakeside Academy");

        alice.printDetails();                                       // both objects see the new school
        bob.printDetails();
        System.out.println();

        // ---------- 3. Math ----------
        // Math, which you have used since week 1, is a utility class: every member is
        // static, so you call it on the class name and never create a Math object
        System.out.println("========== Utility class: Math ==========");
        System.out.println(Math.round(12.8));                       // 13: called on the class, no object anywhere

        // Math potato = new Math();                                // uncomment: compile error, Math() has private access in Math
        System.out.println();

        // ---------- 4. Our own utility class ----------
        // Private constructor, static method, same shape as Math. This is the
        // shape of NameFormatter in the exercise
        System.out.println("========== Our own utility class ==========");
        System.out.println(TimeConverter.hoursToMinutes(2));        // 120, called on the class name
        // TimeConverter converter = new TimeConverter();           // uncomment: compile error, same reason as Math
    }
}
