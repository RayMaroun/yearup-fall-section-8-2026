package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

/*
 * Demo 02 - Polymorphism
 *
 * Human.java is the parent class. Caveman.java and ModernPerson.java are
 * its two child classes, and each one has its own version of eat().
 * This file uses them, one idea per block:
 *
 *   1. Overriding (Human, Caveman, Modern Person): the same call, eat(),
 *      gives a different result for each class.
 *   2. Base class reference: a variable of type Human can hold a Caveman
 *      or a ModernPerson object.
 *   3. Parent type as a parameter: one method that takes a Human accepts
 *      every child of Human.
 *   4. Heterogeneous collection: one list of Human with different kinds
 *      of objects inside.
 *   5. instanceof and downcast: ask an object what it really is, then
 *      reach the methods that only its own class has.
 *   6. getClass: every object knows the class it was created from.
 *
 * Each block prints a title line, then its results, then a blank line.
 *
 * Polymorphism means "many forms": one variable type (Human), many
 * possible objects behind it, and each object answers eat() its own way.
 * Two rules explain every block of this file:
 *   - The type of the VARIABLE decides which methods you are allowed to call.
 *   - The type of the OBJECT decides which version of the method runs.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Overriding: Human ----------
        // The starting point: a Human object runs the eat() written in Human.
        System.out.println("========== Human ==========");
        Human human = new Human("Leo", 55);
        human.eat();
        System.out.println();

        // ---------- Overriding: Caveman ----------
        // Caveman inherits eat() from Human, but it also writes its own.
        // The version in Caveman replaces the inherited one, so the same
        // call prints a different line.
        System.out.println("========== Caveman ==========");
        Caveman caveman = new Caveman("Rocko", 35, 99, "Cleave");
        caveman.eat();
        System.out.println();

        // ---------- Overriding: Modern Person ----------
        // ModernPerson overrides eat() as well: a third result for the
        // same call.
        System.out.println("========== Modern Person ==========");
        ModernPerson modernPerson = new ModernPerson("Hank", 18, 99, 234234324);
        modernPerson.eat();
        System.out.println();

        // ---------- Base class reference ----------
        // The variable human1 has the type Human. A ModernPerson is a Human
        // and a Caveman is a Human, so both objects fit in it. No cast is
        // needed in this direction (child object into parent variable).
        // The second assignment replaces the first: human1 ends up pointing
        // to the Caveman.
        System.out.println("========== Base class reference ==========");
        Human human1;

        human1 = new ModernPerson("Paul", 57, 15, 80);
        human1 = new Caveman("John", 35, 30, "Hunting");
        human1.eat();                                               // the object is a Caveman, so Caveman's eat() runs

        // The object is a Caveman, but the variable is a Human, and Human
        // has no run() method. Remove the // and the compiler says:
        // cannot find symbol - method run(), variable human1 of type Human
        //human1.run();
        System.out.println();

        // The opposite direction: a parent object into a child variable.
        // The compiler refuses it without a cast, and accepts it with the
        // (ModernPerson) cast, because a Human variable COULD hold a
        // ModernPerson. Here it holds a plain Human, so if you remove the
        // comment marks the code compiles and then stops while running with:
        // ClassCastException: class com.pluralsight.Human cannot be cast to
        // class com.pluralsight.ModernPerson
        /*
        ModernPerson modernPerson1;
        Human firstHuman = new Human();

        modernPerson1 = (ModernPerson) firstHuman;*/

        // ---------- Parent type as a parameter ----------
        // feed() is written once, at the bottom of this file, and its
        // parameter is a Human. We can pass it a Human, a Caveman or a
        // ModernPerson. Without this we would need three feed methods,
        // one per class.
        System.out.println("========== Parent type as a parameter ==========");
        feed("Human", human);
        feed("Caveman", caveman);                                   // a Caveman is a Human, so it fits the Human parameter
        feed("ModernPerson", modernPerson);
        System.out.println();

        // ---------- Heterogeneous collection ----------
        // Heterogeneous means "of different kinds". The list is declared
        // as a list of Human, and it holds three objects of three different
        // classes. The loop does not check what each one is: it calls eat()
        // and every object runs its own version.
        System.out.println("========== Heterogeneous collection ==========");
        List<Human> people = new ArrayList<>();
        people.add(human);
        people.add(caveman);
        people.add(modernPerson);

        for (Human person : people) {
            person.eat();                                           // polymorphism: the object decides which eat() runs
        }
        System.out.println();

        // ---------- instanceof and downcast ----------
        // instanceof asks an object: are you of this type? It answers
        // true or false.
        // The order of the tests matters. A Caveman is also a Human, so
        // "person instanceof Human" is true for all three objects. If that
        // test came first, the other two branches would never run. Test the
        // most specific types first and Human last.
        System.out.println("========== instanceof and downcast ==========");
        for (Human person : people) {
            if (person instanceof Caveman) {
                System.out.println("This is a Caveman!");
                // Downcast: from the parent type down to the child type.
                // The variable person is a Human, so it cannot call run().
                // The cast gives us a Caveman variable for the same object.
                Caveman caveman1 = (Caveman) person;                // safe: instanceof just confirmed the type
                caveman1.run();

            } else if (person instanceof ModernPerson) {
                System.out.println("This is a Modern Person");
                // The downcast in two steps, as in the Caveman branch.
                // Remove the comment marks and it works: the drive() line
                // is printed a second time for the modern person.
                /*ModernPerson modernPerson1 = (ModernPerson) person;
                modernPerson1.drive();*/

                // The same downcast in one line, without an extra variable.
                // The outer parentheses are required: the cast must happen
                // before the call to drive().
                ((ModernPerson) person).drive();
            } else if (person instanceof Human) {
                System.out.println("This is a Human");
            }
            System.out.println();
        }

        // ---------- getClass ----------
        // Another way to find out what an object really is: getClass()
        // returns the class the object was created from, whatever the type
        // of the variable. getSimpleName() gives the short name of it.
        System.out.println("========== getClass ==========");
        for (Human person : people) {
            // Remove the // to also print the full name, package included:
            // class com.pluralsight.Human, class com.pluralsight.Caveman,
            // class com.pluralsight.ModernPerson
            //System.out.println(person.getClass());
            System.out.println(person.getClass().getSimpleName());
        }
        System.out.println();
    }

    // The parameter is a Human, so any object of a child class of Human can
    // be passed in. Inside the method we only know it is some kind of Human,
    // which is enough to call eat().
    public static void feed(String label, Human human) {
        System.out.print(label + "--> ");
        human.eat();                                                // chosen while the program runs, by the real type of the object
    }
}
