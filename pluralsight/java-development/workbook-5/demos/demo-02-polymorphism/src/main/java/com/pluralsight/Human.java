package com.pluralsight;

/*
 * Human is the parent class of this demo. Caveman and ModernPerson extend it.
 * It holds what every person has: a name, an age and an eat() method.
 * The child classes keep the name and the age, and replace eat() with
 * their own version.
 */
public class Human {
    // private: a child class has these two fields, but it reads and changes
    // them through the getters and setters.
    private String name;
    private int age;

    // No-argument constructor: lets us write new Human() without any value.
    public Human() {
    }

    // The child classes call this constructor with super(name, age).
    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    // The parent version of eat(). Every child class inherits it. A child
    // class that wants a different behavior writes its own eat() with the
    // same name and the same parameters: that is called overriding.
    public void eat() {
        System.out.println("I am a Human and I am Hungry!");
    }

    // toString() is not new here: it comes from Object, the parent of every
    // class in Java. Writing it again in Human is an override too, which is
    // why it carries @Override.
    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
