package com.pluralsight;

/*
 * Demo 01 - Classes and Objects
 *
 * Person.java is the blueprint. This file builds objects from it and uses
 * them, one idea per block:
 *
 *   1. Instantiate: new Person(...) creates an object, a getter reads a field.
 *   2. Derived getters: values calculated from fields, no field of their own.
 *   3. Boolean getter: isLearner() instead of getLearner().
 *   4. Methods change state: eat() adds energy, the getter shows it.
 *   5. Objects interact: one Person greets another.
 *   6. Cohesion: open Employee.java and read it (nothing runs here).
 *   7. Overloaded method: eat() and eat(int), same name, different parameters.
 *   8. Overloaded constructors: the compiler picks by the argument types and order.
 *
 * Each block prints a title line, then its results, then a blank line.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Instantiate an object ----------
        // Left of the =: a variable of type Person. Right: new + a constructor.
        // The constructor with (String, String, int) is the one that matches.
        System.out.println("========== Instantiate an object ==========");
        Person person1 = new Person("Raymond", "Maroun", 55);
        System.out.println(person1.getLastName());                  // through the getter: person1.lastName would not compile, the field is private

        /* The same object built the long way: the no-argument constructor,
           then one setter per field. Both ways end with the same object.
        Person person1 = new Person();
        person1.setFirstName("Raymond");
        person1.setLastName("Maroun");
        person1.setAge(55);*/

        // Declare first, instantiate later. Between the two lines the variable
        // holds null: there is no object yet, only a name for one.
        Person person2;
        person2 = new Person("Potato", "Sensei", 33);

        System.out.println(person2);                                // println calls the object's toString()
        System.out.println();

        // ---------- Derived getters ----------
        // No fullName field exists in Person. getFullName() builds the value
        // from firstName and lastName each time it is called.
        System.out.println("========== Derived getters ==========");
        System.out.println(person1.getFullName());
        System.out.println(person2.getPersonalDetails());
        System.out.println(person1.getFullInfo());
        System.out.println();

        // ---------- Boolean getter ----------
        // The field is boolean, so the getter is isLearner(), not getLearner().
        // The setter is still setLearner(true).
        System.out.println("========== Boolean getters ==========");
        person2.setLearner(true);
        System.out.println(person2.getFirstName() + " is a learner: " + person2.isLearner());
        System.out.println();

        // ---------- Methods change state ----------
        // Energy before, eat(), energy after: the object remembers what happened to it.
        System.out.println("========== Methods change state ==========");
        System.out.println(person2.getEnergy());

        person2.eat();

        System.out.println(person2.getEnergy());
        System.out.println();

        // ---------- Objects interact ----------
        // greet takes another Person as a parameter. person1 does the greeting,
        // person2 is the one being greeted.
        System.out.println("========== Objects interact ==========");
        person1.greet(person2);
        System.out.println();

        // ---------- Cohesion ----------
        // Open Employee.java and read it. The top half uses the employee's own
        // data (high cohesion); the two methods at the bottom do not (low cohesion).

        // ---------- Overloaded method ----------
        // eat(5) has an int argument, so Java picks eat(int), not eat().
        // Energy was 120 after the block above; now it becomes 125.
        System.out.println("========== Overloaded method ==========");
        person2.eat(5);

        System.out.println(person2.getEnergy());
        System.out.println();

        // ---------- Overloaded constructors ----------
        // Same two types, int and String, in opposite orders. The order is part
        // of the signature, so each line lands in a different constructor.
        // Fields the constructor did not set stay at their default (null for a String).
        System.out.println("========== Overloaded constructors ==========");
        Person person3 = new Person(55, "Ray");                     // (int, String): age and first name
        Person person4 = new Person("Sensei", 33);                  // (String, int): last name and age
        System.out.println(person3);
        System.out.println(person4);
        System.out.println();
    }
}
