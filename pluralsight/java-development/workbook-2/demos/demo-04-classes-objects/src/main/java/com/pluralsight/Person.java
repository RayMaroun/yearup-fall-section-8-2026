package com.pluralsight;

/*
 * Person - the class this program builds objects from.
 *
 * Main.java and com/potato/Main2.java both create Person objects. Read this
 * file next to Main.java: every new Person(...) there runs the constructor
 * here, and every getName(), work(...) or eat() call there runs a method here.
 */

// A class is a blueprint: it describes what every Person has (fields) and does (methods).
// The class itself is not a person. Each "new Person(...)" builds one object from it,
// and every object gets its own copy of the fields below.
public class Person {
    // Attributes or Instance Variables or Properties
    // private: only code inside this class can touch them, everyone else goes through getters and setters
    // Keeping them private means the class decides how they change. For example, no
    // code outside can set energy to a nonsense value without going through a method here.
    private String name;
    private int age;
    private int energy;

    // Constructor
    // Runs once, when someone writes new Person(...). Same name as the class, no return type.
    // Its job is to give a brand new object its starting values.
    // Because this is the only constructor, every Person MUST be created with a name and an age.
    public Person(String name, int age) {
        this.name = name;   // this.name is the field, name is the parameter
        this.age = age;
        this.energy = 100;  // every new person starts with full energy
    }

    // Getters and Setters
    // A getter returns a field's value; a setter replaces it.
    // They are the public doors to the private fields. Naming them getX and setX is
    // a Java convention that tools and other programmers expect.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;   // same trick as the constructor: this.name is the field
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // energy has a getter so Main can print it. Main changes it through
    // work, sleep and eat below instead of setting a number directly.
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // Actions/ Methods/ Functions
    // Methods change the object's own state, so the energy rules live in one place
    // Each method works on the object it is called on: instructor.work(5) changes
    // only the instructor's energy, never another Person's.
    public void work(int hours) {
        this.energy -= (hours * 10);   // 10 energy per hour of work
    }

    public void sleep(int hours) {
        this.energy += (hours * 10);   // 10 energy back per hour of sleep
    }

    public void eat() {
        this.energy += 20;             // a meal is always worth 20
    }

    // void means the method returns nothing; it just prints.
    // this.name is whichever name the object holds right now.
    public void greet() {
        System.out.println(this.name + " is saying Hi to Everyone!");
    }
}
