package com.pluralsight;

/*
 * Dog - a second class, written the same way as Person.
 *
 * Main.java has a commented-out block that creates a Dog. Uncomment it to use
 * this class, and read the comment above the empty constructor below to see
 * why new Dog() fails until you add one.
 */

// A second class built the same way: fields, constructor, getters and setters.
// Any thing you can describe with "it has..." and "it does..." can become a class.
public class Dog {
    // Attributes
    // private for the same reason as in Person: outside code goes through getters and setters.
    private String breed;
    private int age;
    private String color;

    // Constructor
    // The only way to build a Dog right now is with all three values.
    public Dog(String breed, int age, String color) {
        this.breed = breed;   // this.breed is the field, breed is the parameter
        this.age = age;
        this.color = color;
    }

    // Uncomment this empty constructor and new Dog() in Main starts to compile.
    // Java only gives you a free empty constructor when you write no constructor at all.
    // Once you write the three-value constructor above, that free one is gone,
    // so you must write the empty one yourself if you still want it.
    /*public Dog() {

    }*/

    // Getters and Setters
    // One getter and one setter per field, the same pattern as Person.
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
