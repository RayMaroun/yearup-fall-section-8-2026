package com.pluralsight;

/*
 * Demo 01 - Inheritance
 *
 * Animal.java is the parent class, Dog.java is the child class.
 * This file uses them, one idea per block:
 *
 *   1. Parent class: an Animal on its own.
 *   2. Child class: a Dog uses methods it never wrote, because it inherits them.
 *   3. private: inherited, but not reachable from outside Animal.
 *   4. protected: reachable from the child classes.
 *   5. Constructors: the parent constructor always runs first, and
 *      super(...) chooses which parent constructor that is.
 *
 * Each block prints a title line, then its results, then a blank line.
 * The "I am in the ... Constructor" lines are printed by the constructors
 * themselves: read them to see the order in which the constructors run.
 *
 * Three pairs of names for the same two roles:
 *   parent class - child class
 *   super class  - subclass
 *   base class   - derived class
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Parent class ----------
        // Nothing new yet: an Animal object, a setter, a getter.
        System.out.println("========== Parent class ==========");
        Animal animal = new Animal();
        animal.setName("Potato");
        System.out.println(animal.getName());
        System.out.println();

        // ---------- Child class ----------
        // Dog.java has no setName method. The call still works because
        // Dog extends Animal, so a Dog has every public method of Animal.
        // Look at the output: creating a Dog runs Animal() first, then Dog().
        System.out.println("========== Child class ==========");
        Dog dog = new Dog();

        dog.setName("Lucky");                                       // inherited from Animal
        dog.setBreed("Doberman");                                   // written in Dog
        dog.bark();
        System.out.println();

        // ---------- private ----------
        // The Dog object has a name, but the field is private in Animal.
        // Remove the // and the compiler says: name has private access in Animal
        //dog.name = "Potato";

        // ---------- protected ----------
        // favoriteFood is protected, so the code inside Dog can use it directly
        // (see eat() in Dog.java). This line compiles here as well, because
        // classes in the same package can also reach a protected member.
        System.out.println("========== protected ==========");
        dog.favoriteFood = "potatoes";
        dog.eat();
        System.out.println();

        // ---------- Constructors: Dog with 2 arguments ----------
        // Dog(name, breed) calls super(name): the Animal constructor with
        // 1 parameter runs first, then the Dog constructor finishes.
        System.out.println("========== Constructors: Dog with 2 arguments ==========");
        Dog dog2 = new Dog("Lucky", "Doberman");
        System.out.println();

        // ---------- Constructors: Dog with 3 arguments ----------
        // Dog(name, age, breed) calls super(name, age): this time the Animal
        // constructor with 2 parameters runs first.
        System.out.println("========== Constructors: Dog with 3 arguments ==========");
        Dog dog3 = new Dog("Potato", 15, "Doberman");
        System.out.println();
    }
}
