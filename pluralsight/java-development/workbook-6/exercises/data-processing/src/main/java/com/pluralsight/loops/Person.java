package com.pluralsight.loops;

/**
 * Person (Workbook 6a, Section 4-1 Collections vs Streams, Exercise 1)
 *
 * The data the program works on: a first name, a last name and an age.
 * The variables are private and there are only getters, so a Person
 * cannot be changed once it is created.
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
