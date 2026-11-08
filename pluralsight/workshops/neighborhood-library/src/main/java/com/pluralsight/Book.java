package com.pluralsight;

/**
 * Represents a single book in the library.
 * A book can be checked out to exactly one person at a time.
 * <p>
 * This is the "model" class of the workshop: it only holds the data for one
 * book and the two actions that change it (checkOut and checkIn). It never
 * reads from the keyboard or prints anything; that is the job of
 * NeighborhoodLibrary.
 */
public class Book {

    /* --------------------------------------------------------------------------
       Fields
       All fields are private, so other classes can only read them through the
       getters below and can only change them through checkOut / checkIn.
       -------------------------------------------------------------------------- */

    private int id;           // numeric id (chosen by the library), must be unique
    private String isbn;         // international standard book number
    private String title;        // book title
    private boolean checkedOut;   // true if currently borrowed (the workbook's isCheckedOut)
    private String checkedOutTo; // name of the borrower (empty when in library)

    /* --------------------------------------------------------------------------
       Constructor
       -------------------------------------------------------------------------- */

    /**
     * Creates a new book that starts on the shelf (not checked out).
     * Only id, isbn and title come in as parameters: every new book starts
     * in the library, so checkedOut and checkedOutTo always get the same
     * starting values.
     *
     * @param id    numeric id assigned by the library
     * @param isbn  ISBN of the book
     * @param title title and author
     */
    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.checkedOut = false;   // a new book is on the shelf
        this.checkedOutTo = "";    // empty string, not null, so printing it is always safe
    }

    /* --------------------------------------------------------------------------
       Getters
       Read-only access to the fields. For a boolean the Java convention is
       "isSomething()" instead of "getSomething()".
       -------------------------------------------------------------------------- */

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    /* --------------------------------------------------------------------------
       Public actions
       Instead of separate setters, the two fields that change are always
       updated together by these methods, so they can never disagree
       (for example "checked out" but with no borrower name).
       -------------------------------------------------------------------------- */

    /**
     * Checks the book out to the given person.
     * Sets checkedOutTo to the name and checkedOut to true.
     *
     * @param name borrower’s name
     */
    public void checkOut(String name) {
        this.checkedOut = true;
        this.checkedOutTo = name;
    }

    /**
     * Returns the book to the library.
     * Clears the borrower name ("") and sets checkedOut back to false.
     */
    public void checkIn() {
        this.checkedOut = false;
        this.checkedOutTo = "";
    }
}
