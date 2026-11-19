package com.pluralsight.finance;

import java.math.BigDecimal;   // BigDecimal lives in java.math, so it needs an import

/**
 * Valuable (Workbook 6a, Section 2-1 Implementing Interfaces, Exercise 1)
 *
 * An interface: a contract. It says WHAT a class must be able to do and
 * leaves the HOW to the class. Anything that is Valuable can tell what it
 * is worth.
 *
 * Fixed assets (Jewelry, Gold, House) and liquid accounts (BankAccount,
 * CreditCard) have nothing else in common, so one parent class for all of
 * them would not make sense. An interface connects them anyway: every
 * class that writes "implements Valuable" promises to have a getValue()
 * method, and can then be used wherever a Valuable is expected.
 *
 * The value is money, so it is a BigDecimal and not a double: a BigDecimal
 * stores the decimal digits exactly.
 */
public interface Valuable {
    // Only the signature and a semicolon, no { } body. A method in an
    // interface is public and abstract on its own, so neither word has to
    // be written
    BigDecimal getValue();
}
