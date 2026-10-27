package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * Demo 04 - Date basics
 *
 * Java 8 gave us three classes in java.time for dates and times:
 *   - LocalDate      a date only          2026-10-17
 *   - LocalTime      a time only          14:05:09.123
 *   - LocalDateTime  both together        2026-10-17T14:05:09.123
 *
 * None of them carries a time zone: they hold the date and time of this
 * computer, as the clock on the wall shows it. "Local" means exactly that.
 *
 * Each class has now() for the current value and get...() methods for its
 * parts. The last two blocks go the other way round:
 *   - String to LocalDate: parse() turns text typed by a user into a date
 *   - LocalDateTime to String: format() prints a date the way you want
 * Both use a DateTimeFormatter, which describes the layout of the text with
 * pattern letters (MM month, dd day, yyyy year, HH hour, mm minute, ...).
 */
public class Main {
    public static void main(String[] args) {
        // ---------- LocalDate: a date only ----------
        // now() asks the computer clock. Printing a LocalDate gives ISO format: yyyy-MM-dd
        System.out.println("========== LocalDate ==========");
        LocalDate today = LocalDate.now();
        System.out.println("Today is: " + today);
        // The parts of the date. getDayOfWeek and getMonth return names (SATURDAY, OCTOBER),
        // the others return numbers.
        System.out.println("Day of Week: " + today.getDayOfWeek());
        System.out.println("Day of Month: " + today.getDayOfMonth());
        System.out.println("Day of Year: " + today.getDayOfYear());
        System.out.println("Month Name: " + today.getMonth());
        System.out.println("Month Value: " + today.getMonthValue());
        System.out.println("Year: " + today.getYear());
        System.out.println();

        // ---------- LocalTime: a time only ----------
        // Printed as HH:mm:ss plus the fraction of a second
        System.out.println("========== LocalTime ==========");
        LocalTime currentTime = LocalTime.now();
        System.out.println("The current time is: " + currentTime);
        System.out.println("Hour: " + currentTime.getHour());
        System.out.println("Minute: " + currentTime.getMinute());
        System.out.println("Second: " + currentTime.getSecond());
        System.out.println("Nanosecond: " + currentTime.getNano());     // billionths of a second
        System.out.println();

        // ---------- LocalDateTime: date and time together ----------
        // One object for a moment: a timestamp, an appointment, a log entry
        System.out.println("========== LocalDateTime ==========");
        LocalDateTime rightNow = LocalDateTime.now();
        System.out.println("Right now, it is: " + rightNow);       // the T separates the date from the time
        System.out.println();

        // ---------- String => LocalDate ----------
        // Text typed by a user is a String. parse() reads it and gives back a real LocalDate
        // you can compare, add days to, or take parts from.
        System.out.println("========== String to LocalDate ==========");
        // Alternative: text already in ISO format needs no formatter, LocalDate.parse(userInput) is enough
        // String userInput = "2026-10-17";                         // ISO format: LocalDate.parse(userInput) needs no formatter
        String userInput = "10/17/2026";

        // The formatter tells parse how the text is laid out: month first, then day, then year,
        // separated by slashes. Without it, parse expects ISO and throws on "10/17/2026".
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // tells parse how the text is laid out

        LocalDate newDate = LocalDate.parse(userInput, dateTimeFormatter);
        // The pattern was only for reading. A LocalDate has no format of its own,
        // so printing it always gives ISO.
        System.out.println(newDate);                                // always prints in ISO: 2026-10-17
        System.out.println();

        // ---------- LocalDateTime => String ----------
        // The other direction: format() takes a formatter and gives back text in that layout
        System.out.println("========== LocalDateTime to String ==========");
        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println("Today is: " + currentDate);

        // Pattern letters: E short day name (Sat), MMM short month name (Oct), dd day, yyyy year,
        // HH hour on the 24-hour clock, mm minutes, ss seconds.
        // Careful: MM is month, mm is minutes. Letters are case sensitive.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm:ss"); // E day name, MMM month name, HH 24-hour clock

        String formattedDate = currentDate.format(formatter);

        System.out.println("Today is: " + formattedDate);           // Sat, Oct 17, 2026 14:05:09
        System.out.println();
    }
}
