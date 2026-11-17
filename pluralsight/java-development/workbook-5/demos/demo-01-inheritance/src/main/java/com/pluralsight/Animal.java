package com.pluralsight;

/*
 * Animal is the parent class (also called the super class or the base class).
 * Everything written here is shared by every class that extends Animal,
 * so it is written once instead of once per kind of animal.
 */
public class Animal {
    // private: only the code inside Animal can touch these two fields.
    // A child class still HAS them, but it must go through the getters and setters.
    private String name;
    private int age;

    // protected: the code inside Animal AND inside its child classes can use
    // this field directly. Classes in the same package can reach it too.
    protected String favoriteFood;

    // No-argument constructor. If a class has no constructor at all, Java
    // generates an empty one like this. The print line is only here so we can
    // see WHEN this constructor runs.
    public Animal() {
        System.out.println("I am in the Animal Empty Constructor");
    }

    // Constructor with one value: an animal that only knows its name.
    public Animal(String name) {
        System.out.println("I am in the Animal Constructor with 1 param");
        this.name = name;
    }

    // Constructor with two values: name and age.
    public Animal(String name, int age) {
        System.out.println("I am in the Animal Constructor with 2 params");
        this.name = name;
        this.age = age;
    }

    // Public getters and setters: the way the outside world, and the child
    // classes, read and change the private fields.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
}
