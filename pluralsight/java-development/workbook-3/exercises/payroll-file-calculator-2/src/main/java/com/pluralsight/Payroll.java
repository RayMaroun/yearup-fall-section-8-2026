package com.pluralsight;

import java.io.*;
import java.util.Scanner;

/**
 * Payroll (Workbook 3a, Section 2-1 Writing to Files, Exercise 1)
 *
 * New project payroll-file-calculator-2: the payroll-file-calculator code copied in and
 * changed here, so each exercise keeps its own repository.
 *
 * Instead of printing the payroll to the screen, the program now WRITES it to a
 * file. It asks for two file names:
 *   Enter the name of the file to process: employees.csv
 *   Enter the name of the payroll file to create: payroll-sept-2023.csv
 * then reads every employee from the first file and writes one line per
 * employee into the second one:
 *   10|Dana Wyatt|656.25        (id|name|gross pay)
 * Open the new file in IntelliJ after the run to see the result.
 *
 * The first line of employees.csv is a header (id|name|hours-worked|pay-rate),
 * not an employee, so it is read once and skipped before the loop.
 *
 * The BONUS (write JSON when the file name ends in .json) is kept switched off
 * as an alternative main at the bottom of the class.
 */
public class Payroll {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for both file names first, then do the work
        System.out.print("Enter the name of the file to process: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFileName = scanner.nextLine();

        // Reading and writing files can both throw an IOException
        try {
            // Reader for the employees file, writer for the payroll file.
            // FileWriter creates the file, or empties it if it already exists.
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            String line;

            // Skip the header line: "id" is not a number and parseInt would fail on it
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                // Same parsing as payroll-file-calculator: split on | (escaped as \\|)
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee employee = new Employee(id, name, hoursWorked, payRate);
                String outputLine = "";

                // String.format builds the text exactly like printf, but keeps it
                // in a String instead of printing it; %n ends the line in the file
                outputLine = String.format("%d|%s|%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                writer.write(outputLine);
            }

            // close() on the writer is what pushes the buffered text into the file:
            // forget it and the payroll file stays empty
            reader.close();
            writer.close();
            scanner.close();
        } catch (IOException e) {
            // We do not know which of the two files failed, so both names are shown
            System.err.println("Error reading file: " + inputFileName);
            System.err.println("Error writing output file: " + outputFileName);
        }
    }

    /*
     * BONUS alternative: if the payroll file name ends in .json, write JSON instead of csv.
     *   payroll-sept-2023.json
     *   [
     *     { "id": 10, "name": "Dana Wyatt", "grossPay": 656.25 },
     *     { "id": 20, "name": "Ezra Aiden", "grossPay": 284.75 }
     *   ]
     * JSON needs a [ before the first object, a , between objects (not after the
     * last one) and a ] at the end. reader.ready() tells us whether another line
     * is still waiting in the file, so we know when to add the comma.
     * Any other extension is refused before a file is created.
     * To try it, comment out the main above and remove the comment markers below.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the file to process: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFileName = scanner.nextLine();

        if (!outputFileName.endsWith(".csv") && !outputFileName.endsWith(".json")) {
            System.err.println("Unsupported file type.");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            String line;

            // Skip the header line of employees.csv
            reader.readLine();

            if (outputFileName.endsWith(".json")) {
                writer.write("[\n");  // Begin JSON array
            }

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee employee = new Employee(id, name, hoursWorked, payRate);
                String outputLine = "";

                if (outputFileName.endsWith(".csv")) {
                    outputLine = String.format("%d|%s|%.2f%n",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                } else if (outputFileName.endsWith(".json")) {
                    // \" puts a double quote inside the String
                    outputLine = String.format("  { \"id\": %d, \"name\": \"%s\", \"grossPay\": %.2f }",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                    if (reader.ready()) {  // Check if there's another line to read
                        outputLine += ",";  // Add a comma if there's another JSON object after this
                    }

                    outputLine += "\n";  // New line after each JSON object
                }
                writer.write(outputLine);

            }
            if (outputFileName.endsWith(".json")) {
                writer.write("]\n");  // End JSON array
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + inputFileName);
            System.err.println("Error writing output file: " + outputFileName);
        }
    }
    */
}
