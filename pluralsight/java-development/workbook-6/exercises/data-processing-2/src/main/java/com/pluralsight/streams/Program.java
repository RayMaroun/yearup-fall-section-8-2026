package com.pluralsight.streams;

import com.pluralsight.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Program (Workbook 6a, Section 4-3 Stream Methods, Exercise 2)
 *
 * The same program as com.pluralsight.loops.Program, with every for loop
 * replaced by a stream. The loop it replaces is kept in a comment above
 * each stream, so the two can be compared.
 *
 * A stream pipeline always has three parts:
 *   1. a source                 people.stream() : the list hands out its
 *                               items one by one. The list is not changed
 *   2. intermediate operations  filter, map, sorted : each one takes the
 *                               items coming in and passes a new stream on.
 *                               There can be none, one or several
 *   3. a terminal operation     toList, reduce, forEach : ends the
 *                               pipeline and produces the result. Nothing
 *                               runs until the terminal operation is called
 *
 * The -> is a lambda expression: a short method without a name. On the
 * left is the parameter, on the right what to do with it.
 */
public class Program {
    public static void main(String[] args) {
        List<Person> people = createPeopleList();

        System.out.print("Enter a name to search: ");
        Scanner scanner = new Scanner(System.in);
        String searchName = scanner.nextLine();

/*        List<Person> matchingPeople = new ArrayList<>();
        for (Person person : people) {
            if (person.getFirstName().equalsIgnoreCase(searchName) ||
                    person.getLastName().equalsIgnoreCase(searchName)) {
                matchingPeople.add(person);
            }
        }*/

        // Source: people.stream()
        // Intermediate: filter() keeps only the people for whom the lambda
        //   answers true. It is the if of the loop above
        // Terminal: toList() collects what is left into a new list
        List<Person> matchingPeople = people.stream()
                .filter(person -> person.getFirstName().equalsIgnoreCase(searchName) || person.getLastName().equalsIgnoreCase(searchName))
                .toList();

        System.out.println("People with matching name:");
/*        for (Person person : matchingPeople) {
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }*/

        // forEach() runs the lambda once for every item. A List has this
        // method itself, so no .stream() is needed here
        matchingPeople.forEach(person -> System.out.println(person.getFirstName() + " " + person.getLastName()));


        double averageAge = calculateAverageAge(people);
        // %.1f prints the average with one decimal place, %n ends the line
        System.out.printf("Average age: %.1f%n", averageAge);

        int oldestAge = findOldestAge(people);
        System.out.println("Oldest person's age: " + oldestAge);

        int youngestAge = findYoungestAge(people);
        System.out.println("Youngest person's age: " + youngestAge);
    }

    // The same ten people as in the loop version, so both programs give
    // the same answers
    private static List<Person> createPeopleList() {
        List<Person> people = new ArrayList<>();
        // Add at least 10 people to the list
        people.add(new Person("John", "Doe", 25));
        people.add(new Person("Jane", "Smith", 30));
        people.add(new Person("Michael", "Johnson", 35));
        people.add(new Person("Emily", "Brown", 22));
        people.add(new Person("David", "Miller", 40));
        people.add(new Person("Sarah", "Clark", 28));
        people.add(new Person("Victor", "Anderson", 33));
        people.add(new Person("Jessica", "Wilson", 27));
        people.add(new Person("Matthew", "Martinez", 31));
        people.add(new Person("Olivia", "Taylor", 29));

        return people;
    }


    private static double calculateAverageAge(List<Person> people) {
/*        int totalAge = 0;
        for (Person person : people) {
            totalAge += person.getAge();
        }
        return (double) totalAge / people.size();*/

        // Source: people.stream()
        // Intermediate: map() turns every item into something else. Here a
        //   Person goes in and its age comes out, so the stream of people
        //   becomes a stream of numbers. Person::getAge is a method
        //   reference, short for person -> person.getAge()
        // Terminal: reduce() boils all the numbers down to ONE value. It
        //   starts at 0, and for every number it runs the lambda: temp is
        //   the total so far, num is the next age, and the result becomes
        //   the new total
        int totalAge = people.stream().map(Person::getAge).reduce(0, (temp, num) -> temp + num);
        // The cast to double keeps the decimals in the division
        return (double) totalAge / people.size();


        // Another way: mapToInt() gives a stream of int values, which has
        // an average() method of its own. orElse(0) is the answer for an
        // empty list, where there is no average
/*        return people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);*/
    }

    private static int findOldestAge(List<Person> people) {
  /*      int maxAge = Integer.MIN_VALUE;
        for (Person person : people) {
            if (person.getAge() > maxAge) {
                maxAge = person.getAge();
            }
        }*/

        // Another way: the stream of int values has a max() method
        //return people.stream().mapToInt(Person::getAge).max().orElse(-1);

        // Source: people.stream()
        // Intermediate: map() turns each Person into its age
        // Intermediate: sorted() puts the ages in order, lowest first
        // Terminal: toList() collects the sorted ages into a list
        List<Integer> sortedAges = people.stream().map(Person::getAge).sorted().toList();

        // Lowest first, so the oldest age is the LAST item in the list
        int maxAge = sortedAges.get(sortedAges.size() - 1);
        return maxAge;
    }

    private static int findYoungestAge(List<Person> people) {
/*        int minAge = Integer.MAX_VALUE;
        for (Person person : people) {
            if (person.getAge() < minAge) {
                minAge = person.getAge();
            }
        }*/

        // Another way: the stream of int values has a min() method
        /*        return people.stream().mapToInt(Person::getAge).min().orElse(-1);*/

        // The same pipeline as in findOldestAge: map to the ages, sort
        // them, collect them into a list
        List<Integer> sortedAges = people.stream().map(Person::getAge).sorted().toList();

        // Lowest first, so the youngest age is the FIRST item in the list
        int minAge = sortedAges.get(0);
        return minAge;
    }
}
