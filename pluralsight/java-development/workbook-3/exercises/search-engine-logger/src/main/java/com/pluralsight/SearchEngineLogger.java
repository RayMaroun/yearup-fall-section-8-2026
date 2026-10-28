package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * SearchEngineLogger (Workbook 3a, Section 2-2 Date Basics, Exercise 3)
 *
 * Asks for search terms until the user types X (or x), and logs every action
 * to logs.txt with the date and time in front:
 *   2026-04-22 12:42:20 launch
 *   2026-04-22 12:42:45 search : How to use ChatGPT
 *   2026-04-22 12:45:32 exit
 *
 * logs.txt is created in the project folder on the first run. Each run ADDS
 * to the end of the file (append mode), so the history of every run is kept.
 */
public class SearchEngineLogger {

    // static final: one value shared by the whole class, never changed.
    // Written once here so the file name and the date format live in one place.
    private static final String LOG_FILE_NAME = "logs.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        // Action a: launching the application
        logAction("launch");

        Scanner scanner = new Scanner(System.in);

        // Loop forever; the only way out is the break when the user types X
        while (true) {
            System.out.print("Enter a search term (X or x to exit): ");
            String searchTerm = scanner.nextLine();

            // equalsIgnoreCase accepts both X and x.
            // Written "X".equalsIgnoreCase(...) so it never fails on a null value.
            if ("X".equalsIgnoreCase(searchTerm)) {
                // Action c: exiting the application
                logAction("exit");
                break;
            } else {
                // Action b: searching for a term
                logAction("search : " + searchTerm);
            }
        }

        scanner.close();
    }

    // One method writes every kind of action, so the log format is defined only once
    private static void logAction(String action) {
        try {
            // true = append: add to the end of logs.txt instead of erasing it.
            // The file is opened and closed for each action, so every line is
            // saved right away, even if the program stops unexpectedly.
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_NAME, true));
            writer.write(LocalDateTime.now().format(FORMATTER) + " " + action);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            // System.err prints in red in IntelliJ, separate from normal output
            System.err.println("Error writing to the log file: " + e.getMessage());
        }
    }

}
