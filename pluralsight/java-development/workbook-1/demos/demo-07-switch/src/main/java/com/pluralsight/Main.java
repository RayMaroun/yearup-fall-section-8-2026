package com.pluralsight;

import java.util.Scanner;

/*
 * Demo 07 - The switch statement
 *
 * When one variable can have many values and each value needs its own
 * answer, a chain of if / else if gets long. switch says the same thing
 * in a shape that is easier to read. This program asks for a day number
 * (0 to 6) and shows three things: the switch statement (with the if/else
 * chain and the older colon-and-break form switched off above it), the
 * switch expression that produces a value directly, and how several
 * values can share one result.
 *
 * Run it with 3, with 6, and with 9, and watch the three blocks agree.
 */
public class Main {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter the number of the day (0-6): ");
        int dayNumber = myScanner.nextInt();
        System.out.println();

        // ---------- switch statement ----------
        // Three ways to write the same decision. The if/else chain is first (switched off),
        // then the traditional switch with break after every case (switched off), then the
        // arrow form we use: one case, one arrow, no break needed, nothing falls through.
        System.out.println("========== switch statement ==========");
        String dayName;
        /*
        if (dayNumber == 0) {
            dayName = "Sunday";
        } else if (dayNumber == 1) {
            dayName = "Monday";
        } else if (dayNumber == 2) {
            dayName = "Tuesday";
        } else if (dayNumber == 3) {
            dayName = "Wednesday";
        } else if (dayNumber == 4) {
            dayName = "Thursday";
        } else if (dayNumber == 5) {
            dayName = "Friday";
        } else if (dayNumber == 6) {
            dayName = "Saturday";
        } else {
            dayName = "Invalid day";
        }
        */
        /*
        switch (dayNumber) {
            case 0:
                dayName = "Sunday";
                break;
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            default:
                dayName = "Invalid day";
        }
        */
        switch (dayNumber) {
            case 0 -> dayName = "Sunday";
            case 1 -> dayName = "Monday";
            case 2 -> dayName = "Tuesday";
            case 3 -> dayName = "Wednesday";
            case 4 -> dayName = "Thursday";
            case 5 -> dayName = "Friday";
            case 6 -> dayName = "Saturday";
            default -> dayName = "Invalid day";
        }
        System.out.println("The day is: " + dayName);
        System.out.println();

        // ---------- switch expression ----------
        // The switch can produce the value itself: each arrow gives the result, and the
        // whole switch is assigned to the variable. Note the semicolon after the closing brace.
        System.out.println("========== switch expression ==========");
        String description = switch (dayNumber) {         // the switch itself produces the value
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Invalid day";
        };
        System.out.println("The day is: " + description);
        System.out.println();

        // ---------- Several values, one result ----------
        // In the traditional form (switched off), a case without break falls through into the next
        // one, so 0 and 6 share "Weekend". Useful on purpose, a common bug by accident.
        // The arrow form lists the values with commas and nothing can fall through.
        System.out.println("========== Several values, one result ==========");
        /*
        String dayType = "";
        switch (dayNumber) {
            case 0:
            case 6:
                dayType = "Weekend";                  // 0 falls through to 6: no break between them
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                dayType = "Weekday";
                break;
            default:
                dayType = "Invalid day";
        }
        */
        String dayType = switch (dayNumber) {
            case 0, 6 -> "Weekend";
            case 1, 2, 3, 4, 5 -> "Weekday";
            default -> "Invalid day";
        };
        System.out.println(dayType);
        System.out.println();

        myScanner.close();                               // done reading: release the keyboard
    }
}
