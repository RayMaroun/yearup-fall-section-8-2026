package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main (Workbook 6a, Section 2-2 Working with Interfaces, Exercise 3)
 *
 * Builds a list of Person objects, sorts it and prints it.
 *
 * Collections.sort() can sort Strings and numbers on its own, because
 * Java knows how to compare those. It has no idea how to compare one
 * Person to another: by last name? by age? While Person does not
 * implement Comparable, the Collections.sort(myFamily) line does not even
 * compile. Once Person implements Comparable<Person>, sort() calls
 * compareTo() on pairs of people to decide who goes first.
 *
 * Expected order: by last name, then first name, then age.
 *   Aiden, Ezra, 17
 *   Aslan, Elisha, 14
 *   Auston, Ian, 16
 *   Hughes, Zephaniah, 9
 *   Westly, John, 14
 *   Westly, Zachary, 31
 *   Wyatt, Dana, 43
 *   Wyatt, Dana, 63
 */
public class Main {
    public static void main(String[] args) {
        List<Person> myFamily = new ArrayList<>();
        myFamily.add(new Person("Dana", "Wyatt", 63));
        myFamily.add(new Person("Zachary", "Westly", 31));
        myFamily.add(new Person("Elisha", "Aslan", 14));
        myFamily.add(new Person("Ian", "Auston", 16));
        myFamily.add(new Person("Zephaniah", "Hughes", 9));
        myFamily.add(new Person("Ezra", "Aiden", 17));
        // Same last name as Zachary: tests the sort by first name
        myFamily.add(new Person("John", "Westly", 14));
        // Same first and last name as the first Dana: tests the sort by age
        myFamily.add(new Person("Dana", "Wyatt", 43));


        // Sorts the list itself (no new list is created). It works because
        // Person implements Comparable<Person>
        Collections.sort(myFamily);

        // println calls toString() on each person
        for (Person person : myFamily) {
            System.out.println(person);
        }
    }
}
