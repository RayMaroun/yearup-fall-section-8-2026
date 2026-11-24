package com.pluralsight.loops;

// Person moved to the parent package, so it now needs an import
import com.pluralsight.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Program (Workbook 6a, Section 4-3 Stream Methods, Exercise 2)
 *
 * Searches a list of people by name and works out three numbers from the
 * ages: the average, the oldest and the youngest.
 *
 * Everything here is done with traditional for loops. Each one follows
 * the same pattern: prepare a result before the loop, visit every person,
 * update the result inside the loop, use the result after the loop.
 *
 * This is the loop version, kept unchanged next to the stream version in
 * com.pluralsight.streams. Run both with the same name: the output must
 * be the same.
 */
public class Program {
    public static void main(String[] args) {
        List<Person> people = createPeopleList();

        System.out.print("Enter a name to search: ");
        Scanner scanner = new Scanner(System.in);
        String searchName = scanner.nextLine();

        // The result list starts empty. The loop looks at every person and
        // copies the ones that match into it
        List<Person> matchingPeople = new ArrayList<>();
        for (Person person : people) {
            // A match on the first name OR on the last name counts.
            // equalsIgnoreCase: smith finds Smith
            if (person.getFirstName().equalsIgnoreCase(searchName) ||
                    person.getLastName().equalsIgnoreCase(searchName)) {
                matchingPeople.add(person);
            }
        }

        System.out.println("People with matching name:");
        for (Person person : matchingPeople) {
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }

        //int averageAge = calculateAverageAge(people);
        double averageAge = calculateAverageAge(people);
        // %.1f prints the average with one decimal place, %n ends the line
        System.out.printf("Average age: %.1f%n", averageAge);

        int oldestAge = findOldestAge(people);
        System.out.println("Oldest person's age: " + oldestAge);

        int youngestAge = findYoungestAge(people);
        System.out.println("Youngest person's age: " + youngestAge);
    }

    // Builds the test data in one place, so main stays short
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

    // A first version that returns the average as a whole number: the
    // division is done with decimals, then rounded to the nearest int
/*    private static int calculateAverageAge(List<Person> people) {
        int totalAge = 0;
        for (Person person : people) {
            totalAge += person.getAge();
        }
        return (int) Math.round((double) totalAge / people.size());
    }*/

    // Average = the sum of all ages divided by the number of people.
    // totalAge is a double on purpose: dividing an int by an int would
    // throw the decimals away (305 / 10 would give 30, not 30.5)
    private static double calculateAverageAge(List<Person> people) {
        double totalAge = 0;
        for (Person person : people) {
            totalAge += person.getAge();
        }
        return totalAge / people.size();
    }

    // Keeps the highest age seen so far. maxAge starts at the smallest
    // int there is, so the first person always replaces it
    private static int findOldestAge(List<Person> people) {
        int maxAge = Integer.MIN_VALUE;
        for (Person person : people) {
            if (person.getAge() > maxAge) {
                maxAge = person.getAge();
            }
        }
        return maxAge;
    }

    // The same idea the other way round: minAge starts at the biggest int
    // there is, and every lower age replaces it
    private static int findYoungestAge(List<Person> people) {
        int minAge = Integer.MAX_VALUE;
        for (Person person : people) {
            if (person.getAge() < minAge) {
                minAge = person.getAge();
            }
        }
        return minAge;
    }
}
