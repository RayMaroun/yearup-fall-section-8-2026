package com.pluralsight;

/*
 * Dog is a child class of Animal (also called a subclass or a derived class).
 * "extends Animal" means: a Dog IS an Animal. It gets everything Animal has,
 * and adds only what is special about a dog: a breed, bark() and eat().
 */
public class Dog extends Animal {
    // The only field written here. name, age and favoriteFood come from Animal.
    private String breed;

    // No-argument constructor. super() calls the Animal() constructor, so the
    // Animal part of the object is built first. Java makes this call for us
    // even when we do not write it.
    public Dog() {
        super();
        System.out.println("I am in the Dog Empty Constructor");
    }

    // super(name) hands the name up to the Animal constructor that takes one
    // value. It must be the first statement. The breed belongs to Dog, so it
    // is stored here.
    public Dog(String name, String breed) {
        super(name);
        System.out.println("I am in the Dog Constructor with 2 params");
        this.breed = breed;
    }

    // Same idea with two values going up: super(name, age) picks the Animal
    // constructor that takes a name and an age.
    public Dog(String name, int age, String breed) {
        super(name, age);
        System.out.println("I am in the Dog Constructor with 3 params");
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // name is private in Animal, so even a Dog cannot write "name" here.
    // It uses the getter it inherited.
    public void bark() {
        System.out.println(getName() + " says: Ruff, ruff!");
    }

    // favoriteFood is protected in Animal, so a Dog can use it directly.
    public void eat() {
        System.out.println(getName() + " eats " + favoriteFood + "!");
    }
}
