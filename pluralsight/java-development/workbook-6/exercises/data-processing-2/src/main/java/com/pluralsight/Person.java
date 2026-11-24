package com.pluralsight;

/**
 * Person (Workbook 6a, Section 4-3 Stream Methods, Exercise 2)
 *
 * The data the program works on: a first name, a last name and an age.
 * The variables are private and there are only getters, so a Person
 * cannot be changed once it is created.
 *
 * It lives in com.pluralsight, one level above the two programs, because
 * both the loops package and the streams package use it. Each Program
 * imports it.
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
