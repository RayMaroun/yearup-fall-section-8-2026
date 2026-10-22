package com.pluralsight;

/*
 * Person - a class with overloaded constructors and overloaded methods.
 *
 * Main.java creates Person objects in several different ways and calls eat and
 * greet with different arguments. Read this file next to Main.java: for each
 * call there, find the constructor or method here whose parameter list matches.
 */

public class Person {
    // Attributes or Instance Variables or Properties
    // private: code outside this class reads and changes them through getters and setters.
    private String name;
    private int age;
    private int energy;

    // Constructor
    // Overloading: same name, different parameter list (number, types or order).
    // The label above each one is its signature. No two labels may be the same.
    // A signature is the name plus the parameter types in order. Parameter NAMES
    // do not count: Person(String name, int age) and Person(String city, int year)
    // would have the same signature, and Java would reject the second one.
    // Overloading lets callers build a Person with whatever information they have.

    // person
    // Fields keep their defaults: name null, age 0, energy 0
    // Every field gets a default even if no constructor sets it:
    // null for objects like String, 0 for numbers.
    public Person() {
    }

    // person_string_int
    // The only constructor that gives full energy. The others leave energy at 0
    // unless an energy value is passed in.
    public Person(String name, int age) {
        this.name = name;   // this.name is the field, name is the parameter
        this.age = age;
        this.energy = 100;
    }

    // person_int_string
    // Same types as above in a different order, so it is a different signature
    // It does not set energy, so a Person built this way starts at 0.
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    // person_string_int_int
    // Three parameters: the caller chooses the starting energy.
    public Person(String name, int age, int energy) {
        this.name = name;
        this.age = age;
        this.energy = energy;
    }

    // person_string
    // Only the name is known; age and energy stay 0.
    public Person(String name) {
        this.name = name;
    }

    // person_int
    // Only the age is known; name stays null and energy stays 0.
    public Person(int age) {
        this.age = age;
    }

    // person_int_string_int
    // Same three values as person_string_int_int, with int first: a different order, so a different signature.
    public Person(int age, String name, int energy) {
        this.age = age;
        this.name = name;
        this.energy = energy;
    }

    // Getters and Setters
    // The public way to read and change the private fields above.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // Actions/ Methods/ Functions
    // Methods can be overloaded exactly like constructors: same name, different parameter list.
    public void work(int hours) {
        this.energy -= (hours * 10);   // 10 energy per hour of work
    }

    public void sleep(int hours) {
        this.energy += (hours * 10);   // 10 energy back per hour of sleep
    }

    // eat
    // No parameters: a standard meal, always +20.
    public void eat() {
        this.energy += 20;
    }

    // eat_int
    // Same name, one int parameter: the caller decides how much energy the meal gives
    // Java chooses between eat() and eat(int) by looking at the arguments in the call.
    public void eat(int energyEaten) {
        this.energy += energyEaten;
    }

    // greet
    // No parameters: greets everyone.
    public void greet() {
        System.out.println(this.name + " is saying Hi to Everyone!");
    }

    // greet_String
    // One String: greets one person by name.
    public void greet(String personToGreet) {
        System.out.println(this.name + " is saying Hi to " + personToGreet + "!");
    }

    // greet_String_int
    // A String then an int. Overloads do not have to do similar work: this one
    // ignores the object's own name and prints the values passed in.
    // The order of the types is part of the signature, so greet(int, String) does not exist.
    public void greet(String learnerName, int age) {
        System.out.println("Hello Learner: " + learnerName);
        System.out.println("Your age: " + age);
    }
}
