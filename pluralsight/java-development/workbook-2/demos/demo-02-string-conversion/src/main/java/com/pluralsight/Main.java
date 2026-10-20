package com.pluralsight;

import java.time.LocalDate;                     // a date (year, month, day) with no time of day
import java.time.format.DateTimeFormatter;      // describes how a date is written as text
import java.util.Scanner;                       // reads what the user types

/*
 * Demo 02 - String conversion
 *
 * This program walks through turning text into real values: a String into an
 * int, a String into a double, and a String into a LocalDate, first with the
 * default date format and then with a pattern you choose.
 *
 * Anything a user types, or anything read from a file, arrives as a String.
 * "111" looks like a number, but to Java it is three characters. You cannot do
 * math with it, and you cannot ask what day of the week "2002-10-17" is.
 * Converting (parsing) the text gives you a value of the right type.
 *
 * Each block prints a title line, then its results.
 * Run it, read the output next to the code, and change the values.
 * The last block waits for you to type a date: type it in the format shown,
 * for example 17/October/2002, and press Enter.
 *
 * Some lines are commented out on purpose. Each one contains a mistake.
 * Remove the // in front of one, run again, and read the error message:
 * that message is part of the lesson.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- String to int ----------
        // Integer.parseInt reads a String made of digits and gives back an int.
        // Before parsing, + on a String joins text. After parsing, + adds numbers.
        System.out.println("========== String to int ==========");
        String stringNumber = "111";
        System.out.println(stringNumber + 1);                           // 1111: + on a String joins text, it does not add
        int parsedNumber = Integer.parseInt(stringNumber);              // "111" (text) becomes 111 (a number)
        int result = parsedNumber + 1;
        System.out.println(result);                                     // 112: after parseInt it is a real number
        // int broken = Integer.parseInt("one hundred");                // error: NumberFormatException, only digits can be parsed
        System.out.println();

        // ---------- String to double ----------
        // Double.parseDouble does the same for numbers with a decimal point.
        // printf formats the output: %.2f prints a number with exactly 2 decimals,
        // and %n ends the line. The values after the text fill the % slots in order.
        System.out.println("========== String to double ==========");
        String priceText = "4.99";
        double price = Double.parseDouble(priceText);                   // parseDouble keeps the decimal part: 4.99
        double totalForThree = price * 3;                               // real math now: 14.97
        System.out.printf("3 boxes at $%.2f = $%.2f%n", price, totalForThree);
        System.out.println();

        // ---------- String to LocalDate ----------
        // LocalDate.parse reads a date written as YYYY-MM-DD (year-month-day, with dashes).
        // That is the ISO format, the international standard, and the only one parse
        // understands without help. Once parsed, the date knows things text cannot,
        // like which day of the week it falls on.
        System.out.println("========== String to LocalDate ==========");
        String userInput = "2002-10-17";                                // YYYY-MM-DD, the only format parse() reads by default
        LocalDate birthDay = LocalDate.parse(userInput);
        System.out.println(birthDay);
        System.out.println(birthDay.getDayOfWeek());                    // proof it is a date now, not text: THURSDAY
        System.out.println();

        // ---------- Date from the user with a pattern ----------
        // Users rarely type YYYY-MM-DD. A DateTimeFormatter describes the format you expect,
        // and parse uses it to read the text. In the pattern:
        //   d    = day of the month, 1 or 2 digits (17)
        //   MMMM = full month name (October)
        //   yyyy = 4-digit year (2002)
        //   /    = a plain character that must appear exactly as written
        // So the pattern d/MMMM/yyyy reads input like 17/October/2002.
        // If the input does not match the pattern, parse throws a DateTimeParseException.
        System.out.println("========== Date from the user with a pattern ==========");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the date that you want (d/MMMM/yyyy)");
        String dateInputFromUser = scanner.nextLine();                  // whatever was typed, as a String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MMMM/yyyy");
        // LocalDate userDate = LocalDate.parse(dateInputFromUser);     // error: DateTimeParseException, input is not YYYY-MM-DD
        LocalDate userDate = LocalDate.parse(dateInputFromUser, formatter); // the formatter tells parse how to read the text
        System.out.println(userDate);                                   // always printed back as YYYY-MM-DD
        System.out.println();

        scanner.close();                                                // done reading input: close the Scanner
    }
}
