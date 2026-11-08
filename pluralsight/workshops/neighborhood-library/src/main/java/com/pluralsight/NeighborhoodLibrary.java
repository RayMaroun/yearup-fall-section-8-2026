package com.pluralsight;

import java.util.Scanner;

/**
 * A small, console–based library application.
 * <p>
 * Learners can:
 * <ul>
 *   <li>view books that are available to borrow</li>
 *   <li>view books that are already checked out</li>
 *   <li>check out a book or return it</li>
 * </ul>
 * The program keeps running until the user chooses “Exit”.
 * <p>
 * How the program is organised:
 * <ul>
 *   <li>main builds the inventory (an array of Book objects) and runs the Home Screen loop</li>
 *   <li>displayAvailableBooks is the "Show Available Books" screen (check out)</li>
 *   <li>displayCheckedOutBooks is the "Show Checked Out Books" screen (check in)</li>
 *   <li>findBookById is a helper both screens use to look a book up by its id</li>
 * </ul>
 * The inventory and the Scanner are created once in main and passed to each
 * screen as parameters, so every screen works on the same books.
 */
public class NeighborhoodLibrary {

    // Menu options described by constants so the numbers have clear meaning
    private static final int MENU_SHOW_AVAILABLE = 1;
    private static final int MENU_SHOW_CHECKEDOUT = 2;
    private static final int MENU_EXIT = 3;

    /**
     * Entry point: creates the books, then shows the Home Screen over and
     * over until the user picks Exit.
     */
    public static void main(String[] args) {

        /* -------------------------- create the inventory --------------------------- */
        // An array has a fixed size, which is fine here: the library has a known
        // set of books. Each book gets a unique id (1 to 20) so the user can pick
        // a book by typing its id.
        Book[] inventory = {
                new Book(1, "9780671023379", "The Alchemist by Paulo Coelho"),
                new Book(2, "9780061120084", "To Kill a Mockingbird by Harper Lee"),
                new Book(3, "9780143132172", "The 7 Habits of Highly Effective People by Stephen Covey"),
                new Book(4, "9781984801906", "Atomic Habits by James Clear"),
                new Book(5, "9780062562851", "The Four Agreements by Don Miguel Ruiz"),
                new Book(6, "9780451524935", "1984 by George Orwell"),
                new Book(7, "9780743273565", "The Great Gatsby by F. Scott Fitzgerald"),
                new Book(8, "9780316769488", "The Catcher in the Rye by J.D. Salinger"),
                new Book(9, "9780141439518", "Pride and Prejudice by Jane Austen"),
                new Book(10, "9780547928227", "The Hobbit by J.R.R. Tolkien"),
                new Book(11, "9780590353427", "Harry Potter and the Sorcerer's Stone by J.K. Rowling"),
                new Book(12, "9780060850524", "Brave New World by Aldous Huxley"),
                new Book(13, "9781451673319", "Fahrenheit 451 by Ray Bradbury"),
                new Book(14, "9780385490818", "The Handmaid's Tale by Margaret Atwood"),
                new Book(15, "9780439023481", "The Hunger Games by Suzanne Collins"),
                new Book(16, "9780140449136", "Crime and Punishment by Fyodor Dostoevsky"),
                new Book(17, "9780441172719", "Dune by Frank Herbert"),
                new Book(18, "9780618640157", "The Lord of the Rings by J.R.R. Tolkien"),
                new Book(19, "9780399590504", "Educated by Tara Westover"),
                new Book(20, "9780525559474", "The Midnight Library by Matt Haig")
        };

        // One Scanner for the whole program, shared with every screen
        Scanner scanner = new Scanner(System.in);

        int choice = -1;                                 // keeps the loop running
        while (choice != MENU_EXIT) {
            /* ----------------------------- home screen ----------------------------- */
            System.out.println("\nHome Screen");
            System.out.println("------------------");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            /* ---------- make sure the user types a number, not letters ---------- */
            // hasNextInt() looks at the input without consuming it, so a typo
            // like "abc" does not crash the program with an exception
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();                      // discard the bad input
                continue;                               // restart menu loop
            }

            choice = scanner.nextInt();
            scanner.nextLine();                         // remove the leftover newline

            // Arrow-style switch (Java 14+): each case runs one statement, no break needed
            switch (choice) {
                case MENU_SHOW_AVAILABLE -> displayAvailableBooks(inventory, scanner);
                case MENU_SHOW_CHECKEDOUT -> displayCheckedOutBooks(inventory, scanner);
                case MENU_EXIT -> System.out.println("Exiting the application. Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();                                // always close Scanner when finished
    }

    /**
     * Finds a book by its id.
     * Walks the array from start to end and returns the first match. Because
     * every id is unique there is at most one match.
     *
     * @param inventory the array of books
     * @param id        the id to look for
     * @return the matching {@code Book} or {@code null} if not found
     */
    private static Book findBookById(Book[] inventory, int id) {
        for (Book b : inventory) {
            if (b.getId() == id) {
                return b;                               // found it, stop looking
            }
        }
        return null;                                    // looked at every book, no match
    }

    /**
     * Lists all books that are not checked out and lets the user borrow one.
     * Screen: "Show Available Books" (Id, ISBN and Title), then check out or go back.
     *
     * @param inventory the array of books
     * @param scanner   shared scanner for user input
     */
    private static void displayAvailableBooks(Book[] inventory, Scanner scanner) {
        System.out.println("\nAvailable Books");
        System.out.println("------------------");

        // show each available book, one per line
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.printf("Id: %d  ISBN: %s  Title: %s%n",
                        book.getId(), book.getIsbn(), book.getTitle());
            }
        }
        System.out.println("------------------");
        // 0 is never a book id, so it works as the "go back to the home screen" option
        System.out.print("Enter the Id of the book to check out (0 to cancel): ");

        if (!scanner.hasNextInt()) {                    // validate numeric id
            System.out.println("Please enter a number.");
            scanner.nextLine();
            return;                                     // return to main menu
        }
        int bookId = scanner.nextInt();
        scanner.nextLine();                             // remove the leftover newline before reading the name

        if (bookId == 0) {                              // user changed their mind
            return;
        }

        // Guard clauses: each problem prints a message and returns early,
        // so the happy path at the bottom stays flat and easy to read
        Book selectedBook = findBookById(inventory, bookId);
        if (selectedBook == null) {                     // id does not exist
            System.out.println("Invalid book Id!");
            return;
        }
        if (selectedBook.isCheckedOut()) {              // double-check status
            System.out.println("Sorry, that book is already checked out!");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        selectedBook.checkOut(name);                    // mark book as borrowed
        System.out.println("Thank you, " + name +
                "! You checked out \"" + selectedBook.getTitle() + "\".");
    }

    /**
     * Lists all books that are currently checked out and lets the user return one.
     * Screen: "Show Checked Out Books" (Id, ISBN, Title and borrower), then
     * C to check in a book or X to go back to the home screen.
     *
     * @param inventory the array of books
     * @param scanner   shared scanner for user input
     */
    private static void displayCheckedOutBooks(Book[] inventory, Scanner scanner) {
        System.out.println("\nChecked Out Books");
        System.out.println("------------------");

        // same loop as the available list, with the condition flipped
        for (Book book : inventory) {
            if (book.isCheckedOut()) {
                System.out.printf("Id: %d  ISBN: %s  Title: %s  →  %s%n",
                        book.getId(), book.getIsbn(), book.getTitle(),
                        book.getCheckedOutTo());
            }
        }
        System.out.println("------------------");
        System.out.print("Press C to check in a book, or X to return: ");
        String input = scanner.nextLine().trim();       // trim() ignores stray spaces

        // If user wants to check a book in
        if (input.equalsIgnoreCase("C")) {              // accepts "c" or "C"
            System.out.print("Enter the Id of the book to check in: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                return;
            }
            int id = scanner.nextInt();
            scanner.nextLine();

            Book book = findBookById(inventory, id);
            if (book == null) {
                System.out.println("Invalid book Id!");
            } else if (!book.isCheckedOut()) {
                System.out.println("That book is not checked out!");
            } else {
                book.checkIn();                         // mark book as returned
                System.out.println("Thank you for returning \"" + book.getTitle() + "\".");
            }
        } else if (!input.equalsIgnoreCase("X")) {      // any input except X is invalid
            System.out.println("Invalid input!");
        }
        // returning from this method takes the user back to the home screen
    }
}
