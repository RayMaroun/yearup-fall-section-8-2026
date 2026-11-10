package com.pluralsight;

/*
 * Person: a class is a blueprint. It says what every Person KNOWS (the fields)
 * and what every Person can DO (the methods). Nothing exists until Main
 * writes new Person(...), which builds one object from the blueprint.
 */
public class Person {
    // Fields (also called attributes, instance variables, properties).
    // private: only code inside this class can read or change them directly.
    // Every object gets its own copy of each field.
    private String firstName;
    private String lastName;
    private int age;
    private int energy;
    private boolean learner;

    // Constructors: same name as the class, no return type. They run once,
    // when new Person(...) is written, and fill the fields.
    // There are five of them: the name is the same, the parameter list differs.
    // That is called overloading, and the compiler picks the one whose
    // parameter types and order match the arguments you pass.

    // No-argument constructor: every field keeps its default (null, 0, false).
    // Fill the object afterwards with the setters.
    public Person() {
    }

    // Three arguments: first name, last name, age. Energy starts at 100.
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;     // this.firstName is the field, firstName alone is the parameter
        this.lastName = lastName;
        this.age = age;
        this.energy = 100;
    }

    // Two Strings: first name and last name, no age yet.
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.energy = 100;
    }

    // An int then a String: age first, then the first name.
    public Person(int age, String firstName) {
        this.age = age;
        this.firstName = firstName;
        this.energy = 100;
    }

    // A String then an int: same two types as above, other order.
    // The order is part of the signature, so this is a different constructor:
    // new Person("Sensei", 33) lands here, new Person(33, "Sensei") lands above.
    public Person(String lastName, int age) {
        this.lastName = lastName;
        this.age = age;
    }

    // Getters and setters: the only way in and out of the private fields.
    // A getter returns the value, a setter replaces it. Generate them in
    // IntelliJ with Alt+Insert (Cmd+N on Mac) > Getter and Setter.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    // Boolean getter: the name starts with "is", not "get", and reads like a
    // question: person.isLearner() answers true or false.
    public boolean isLearner() {
        return learner;
    }

    public void setLearner(boolean learner) {
        this.learner = learner;
    }

    // Derived getters: no field called fullName exists. The value is calculated
    // from other fields every time the getter is called, so it is never out of date.
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPersonalDetails() {
        return "FirstName: " + firstName + " Age: " + age;
    }

    // A derived getter can call another getter of the same object.
    public String getFullInfo() {
        return "FullName: " + getFullName() + " Age: " + age;
    }

    // Methods (actions): they do more than get and set. They change the
    // object's state or do some work with it.

    // eat() with no parameters: always adds 20 energy.
    public void eat() {
        this.energy += 20;
        System.out.println("I am eating potatoes! And I added 20 energy!");
    }

    // eat(int) is an overload of eat(): same name, different parameter list.
    // The caller decides how much energy to add. Same idea as the constructors.
    public void eat(int energyEaten) {
        this.energy += energyEaten;
        System.out.println("I am eating potatoes! And I added " + energyEaten + " energy!");
    }

    public void work() {
        this.energy -= 20;
        System.out.println("I am working!");
    }

    // A method can take another object of the same class as a parameter.
    // Inside, "person" is the other object and "this" is the one the method
    // was called on: person1.greet(person2) prints person2's name, then person1's.
    public void greet(Person person) {
        System.out.println("Hello " + person.getFirstName() + " I am " + this.firstName);
    }

    // toString: what println prints for the object. Without it you would see
    // something like com.pluralsight.Person@1b6d3586 (the class name and an address).
    @Override
    public String toString() {
        return firstName + " | " + lastName + " | " + age;
    }
}
