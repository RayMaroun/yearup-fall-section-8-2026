package com.pluralsight;

public class Person {
    // Instance members: every Person gets its own name and age
    private String name;
    private int age;

    public Person(String name, int age) {
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

    // Instance method: needs an object to be called on
    public void greet() {
        System.out.println("Hello Learners of Potato Sensei!");
    }
}
