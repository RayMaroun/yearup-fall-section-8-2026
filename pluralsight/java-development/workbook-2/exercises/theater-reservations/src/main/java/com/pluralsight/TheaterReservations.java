package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * TheaterReservations (Workbook 2, Exercise 4)
 *
 * Asks for the customer's full name, the date of the show and the number of tickets,
 * then prints a confirmation in this format:
 *   # ticket(s) reserved for (date) under (LastName, FirstName)
 * When only one ticket is reserved, the word is "ticket" (no "s").
 *
 * Two ideas practiced here:
 *   - cutting a String in two with indexOf + substring (first name / last name)
 *   - turning text like "05/13/2023" into a real LocalDate with DateTimeFormatter
 */
public class TheaterReservations {

    public static void main(String[] args) {
        // A Scanner reads what the user types in the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for their full name
        System.out.print("Please enter your name: ");
        String fullName = scanner.nextLine(); // Raymond Maroun

        // Split the full name at the first space
        // Everything before the space is the first name, everything after it is the last name
        int indexOfSpace = fullName.indexOf(" ");
        String firstName = fullName.substring(0, indexOfSpace); // Raymond
        String lastName = fullName.substring(indexOfSpace + 1); // Maroun (+1 skips the space)

        // Prompt the user for the date of the show
        System.out.print("What date will you be coming (MM/dd/yyyy): ");
        String dateStr = scanner.nextLine();

        // Parse the date using DateTimeFormatter
        // The pattern tells Java how to read the text: MM = month, dd = day, yyyy = year
        // (capital MM is month; lowercase mm would mean minutes)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        // Printing a LocalDate gives the ISO format yyyy-MM-dd, e.g. 2023-05-13

        // Prompt the user for the number of tickets
        System.out.print("How many tickets would you like? ");
        int numTickets = scanner.nextInt();

        // Display the confirmation message
        // Ternary operator: condition ? valueIfTrue : valueIfFalse
        // 1 ticket -> " ticket", anything else -> " tickets"
        String ticketString = numTickets == 1 ? " ticket" : " tickets";
        // The same choice written as an if / else instead of the ternary:
        /*String ticketString = "";
        if(numTickets == 1){
            ticketString = " ticket";
        } else {
            ticketString = " tickets";
        }*/
        // Join the pieces with + to build the full sentence
        String confirmationMessage = numTickets + ticketString + " reserved for " + date + " under " + lastName + ", " + firstName;
        System.out.println(confirmationMessage);

        // Close the Scanner when we are done reading input
        scanner.close();
    }

    /*
     * Alternative solution: the same program with two different techniques.
     *   - name.split(" ") cuts the name into an array instead of indexOf/substring
     *     (nameParts[0] is the first name, nameParts[1] the last name)
     *   - String.format builds the message with %s placeholders, filled in order
     * .trim() removes extra spaces the user may type before or after the name.
     * To try it, comment out the main above and remove the comment markers below.
     */
/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: "); 
        String name = scanner.nextLine().trim();
        // Prompt user for date
        System.out.print("What date will you be coming (MM/dd/yyyy): ");
        String dateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        System.out.print("How many tickets would you like? ");
        int numTickets = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline character

        String[] nameParts = name.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts[1];


        String ticketStr = numTickets == 1 ? "1 ticket" : numTickets + " tickets";
        String confirmation = String.format("%s reserved for %s under %s, %s",
                ticketStr, date, lastName, firstName);
        System.out.println(confirmation);
    }*/
}
