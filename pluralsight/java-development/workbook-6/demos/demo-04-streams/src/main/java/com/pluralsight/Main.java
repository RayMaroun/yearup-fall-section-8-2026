package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * Demo 04 - Streams and lambda expressions
 *
 * The previous demo processed a list with for loops. This one does the
 * same kind of work with a stream. The project holds:
 *
 *   Employee  a first name, a last name and an age
 *
 * A stream is a pipeline with three parts:
 *
 *   Source          the collection the data comes from (data at rest)
 *   Transformation  the work done on the data while it travels through
 *                   the pipeline, for example filter (data in transit)
 *   Destination     where the result ends up, for example a new list
 *                   (data at rest again)
 *
 * The work a stream method has to do is given to it as a lambda
 * expression. A lambda is a small method without a name, written right
 * where it is used:
 *
 *   parameter -> expression                 one expression, no braces,
 *                                           no return
 *   parameter -> { statements }             several statements need
 *                                           braces
 *
 * You never call a lambda yourself. You hand it to a method such as
 * filter or forEach, and that method calls it once for each element.
 *
 * This file shows, in order:
 *
 *   1. The collection: four employees in a list.
 *   2. Filter: the loop version first (switched off), then the same
 *      result with a stream.
 *   3. forEach: the loop that prints (switched off), then forEach with a
 *      lambda.
 *   4. forEach with a lambda of several statements.
 *
 * The program asks for a last name. Type maroun to get two results.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- The collection ----------
        // employees is the source: the list every block below reads from.
        // Two employees share the last name Maroun, so a search for it
        // gives more than one result.
        // The collection
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Leo", "Maroun", 55));
        employees.add(new Employee("Potato", "Sensei", 35));
        employees.add(new Employee("John", "Doe", 26));
        employees.add(new Employee("Raymond", "Maroun", 25));

        // ---------- Filter ----------
        // The goal: a new list with only the employees whose last name is
        // the one the user typed. equalsIgnoreCase() compares two Strings
        // without looking at upper or lower case, so maroun finds Maroun.
        // Filter: the loop version first, then the same result with a stream
        System.out.println("========== Filter with a stream ==========");
        System.out.println("Enter a last name to search: ");
        Scanner scanner = new Scanner(System.in);
        String lastNameToSearch = scanner.nextLine();

        // The loop version, switched off. You write every step yourself:
        // create the empty list, walk the employees one by one, test each
        // one with the if, and add the ones that pass.
        /*List<Employee> matchingEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getLastName().equalsIgnoreCase(lastNameToSearch)) {
                matchingEmployees.add(employee);
            }
        }*/

        // The stream version. Same result, and no loop and no if written:
        //   employees   the source, the list at rest
        //   .stream()   puts the employees in the pipeline
        //   .filter()   calls the lambda once for each employee and keeps
        //               the ones for which it answers true
        //   .toList()   takes what reaches the end and saves it in a new
        //               list
        // The lambda is the condition of the if from the loop version:
        // employee is the one employee it receives, and the expression
        // after the arrow is its answer, true or false.
        // The list employees itself is not changed.
        List<Employee> matchingEmployees =
                employees // Source - Data At Rest
                        .stream().filter(employee -> employee.getLastName().equalsIgnoreCase(lastNameToSearch))  // Data Transformation - Data in Transit
                        .toList(); // Destination - Data At Rest

        // toList() gives a List that cannot be changed afterwards. When the
        // result has to be an ArrayList, the destination is written this
        // way instead. This block is switched off.
        // Same pipeline collected into an ArrayList instead of a List
        /*ArrayList<Employee> matchingEmployees =
                employees // Source - Data At Rest
                        .stream().filter(employee -> employee.getLastName().equalsIgnoreCase(lastNameToSearch))  // Data Transformation - Data in Transit
                        .collect(Collectors.toCollection(ArrayList::new)); // Destination - Data At Rest*/

        System.out.println("The results are: ");

        // ---------- forEach ----------
        // The loop that prints, switched off: one pass per employee, and
        // you write the loop yourself.
        // forEach with a lambda: replaces the loop that prints
        /*for (Employee matchingEmployee : matchingEmployees) {
            System.out.println(matchingEmployee);
        }*/

        // forEach does the looping. It calls the lambda once for each
        // employee of the list, and the lambda prints that employee.
        // forEach expects no answer, so this lambda gives nothing back.
        // The switched-off line does the same through a stream; a list has
        // its own forEach, so .stream() is not needed here.
        //matchingEmployees.stream().forEach(matchingEmployee -> System.out.println(matchingEmployee));
        matchingEmployees.forEach(matchingEmployee -> System.out.println(matchingEmployee));     // parameter -> expression body
        System.out.println();

        // ---------- A lambda with several statements ----------
        // When the body of a lambda is more than one expression, it goes
        // between braces, and each statement inside ends with a semicolon,
        // like the body of any method.
        // This lambda only prints, so it has no return. A lambda that has
        // to answer, like the one given to filter, would end with a return
        // statement when it is written with braces.
        // Lambda with several statements: the body needs { }
        System.out.println("========== forEach with a multi-line lambda ==========");
        employees.forEach(employee -> {
            if (employee.getAge() < 40) {
                System.out.println(employee.getFirstName() + " is young.");
            } else {
                System.out.println(employee.getFirstName() + " is experienced.");
            }
        });
        System.out.println();
    }
}
