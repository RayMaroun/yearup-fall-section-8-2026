package com.pluralsight;

/*
 * Employee: a class to READ, not to run. It shows cohesion.
 *
 * Cohesion is how closely the members of a class belong together.
 * High cohesion: every method works with the class's own data (here name,
 * age and salary). Low cohesion: a method has nothing to do with that data
 * and would be happier in another class. The goal is always high cohesion:
 * one class, one job.
 */
public class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // -------------------- High Cohesion Methods --------------------
    // Every method here uses the Employee's own data: name, age, salary.

    // Calculates a 10% salary bonus
    public double calculateBonus() {
        return this.salary * 0.10;
    }

    // Returns a formatted string of employee details
    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Salary: $" + salary;
    }

    // Checks if the employee is eligible for retirement
    public boolean isEligibleForRetirement() {
        return this.age >= 60;
    }

    // Changes state: the salary grows by a percentage
    public void applyRaise(double percentage) {
        if (percentage > 0) {
            this.salary += this.salary * (percentage / 100);
        }
    }

    // Returns the annual salary based on the monthly salary
    public double getAnnualSalary() {
        return this.salary * 12;
    }

    // Determines a simple tax bracket
    public String getTaxBracket() {
        if (salary > 100000) {
            return "High";
        } else if (salary > 50000) {
            return "Medium";
        } else {
            return "Low";
        }
    }

    // -------------------- Low Cohesion Methods (Unrelated) --------------------
    // These touch none of the Employee's data. They compile and run fine,
    // but they belong in a Printer class and a Network class, not here.

    // Simulates document printing (not related to Employee logic)
    public void printDocument(String document) {
        System.out.println("Printing: " + document);
    }

    // Simulates a network connection (not related to Employee logic)
    public void connectToNetwork(String networkName) {
        System.out.println("Connecting to network: " + networkName);
    }
}
