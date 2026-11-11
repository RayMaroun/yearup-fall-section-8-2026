package com.pluralsight;

/**
 * Main (Workbook 4a, Section 2-3 Overloading Methods, Exercise 3)
 *
 * Tests the double overloads on one employee (8.5 to 17.25 = 8.75 hours,
 * 175.0 at 20.00) and a Hotel with 10 suites and 20 rooms: books 2 suites,
 * then shows 8 suites and 20 rooms left.
 *
 * The no-argument punchIn()/punchOut() are not called here on purpose:
 * they read the real clock, so their output changes every run.
 */
public class Main {
    public static void main(String[] args) {
        // Test Employee class
        Employee employee1 = new Employee(1, "John Doe", "IT", 20.0);
        employee1.punchIn(8.5);
        employee1.punchOut(17.25);
        System.out.println(employee1.getName() + " worked " + employee1.getHoursWorked() + " hours.");
        System.out.println("Total pay for " + employee1.getName() + " is $" + employee1.getTotalPay());

        // Test Hotel class
        Hotel hotel1 = new Hotel("Holiday Inn", 10, 20);
        System.out.println(hotel1.getName() + " has " + hotel1.getNumberOfSuites() + " suites and " + hotel1.getNumberOfRooms() + " rooms.");
        System.out.println(hotel1.getAvailableSuites() + " suites and " + hotel1.getAvailableRooms() + " rooms are available.");

        // bookRoom returns true or false, so the result drives the message
        boolean booked = hotel1.bookRoom(2, true);
        if (booked) {
            System.out.println("Booked 2 suites.");
        } else {
            System.out.println("Could not book 2 suites.");
        }
        System.out.println(hotel1.getAvailableSuites() + " suites and " + hotel1.getAvailableRooms() + " rooms are available.");
    }
}
