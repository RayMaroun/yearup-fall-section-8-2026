package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * One financial record: deposit (positive) or payment (negative).
 * <p>
 * Immutable – field values are assigned in the constructor
 * and never change afterwards.
 */
public class Transaction {

    /* ------------------------------------------------------------------
       Formatters (declared once and reused)
       ------------------------------------------------------------------ */
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    /* ------------------------------------------------------------------
       Data fields
       ------------------------------------------------------------------ */
    private final LocalDate date;        // calendar date of the transaction
    private final LocalTime time;        // time of day
    private final String description; // user-supplied description
    private final String vendor;      // where the money came from / went to
    private final double amount;      // positive for deposit, negative for payment

    /* ------------------------------------------------------------------
       Constructor
       ------------------------------------------------------------------ */
    public Transaction(LocalDate date,
                       LocalTime time,
                       String description,
                       String vendor,
                       double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    /* ------------------------------------------------------------------
       Getters (read-only access)
       ------------------------------------------------------------------ */
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    /* ------------------------------------------------------------------
       String helpers
       ------------------------------------------------------------------ */

    /**
     * Produces the exact pipe-delimited format stored in the data file.
     * Example: 2025-05-10|14:35:22|Coffee|Starbucks|-4.25
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f",
                date.format(DATE_FMT),
                time.format(TIME_FMT),
                description,
                vendor,
                amount);
    }
}