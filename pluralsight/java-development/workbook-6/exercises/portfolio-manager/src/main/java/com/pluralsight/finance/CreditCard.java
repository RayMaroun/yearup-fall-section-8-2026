package com.pluralsight.finance;

import java.math.BigDecimal;

/**
 * CreditCard (Workbook 6a, Section 2-1 Implementing Interfaces, Exercise 1)
 *
 * A liquid account like BankAccount, but it holds debt: the balance is
 * what is owed to the bank. It implements Valuable, and its value is
 * negative, so it lowers the net worth.
 */
public class CreditCard implements Valuable{
    private String name;            // the owner of the card
    private String accountNumber;
    private BigDecimal balance;     // the amount owed

    public CreditCard(String name, String accountNumber, BigDecimal balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Buying something with the card: the amount owed goes up
    public void charge(BigDecimal amount) {
        balance = balance.add(amount);
    }

    // Paying the card off: the amount owed goes down
    public void pay(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    // negate() flips the sign: a balance of 500.00 gives a value of -500.00
    @Override
    public BigDecimal getValue() {
        return balance.negate(); // Negative balance represents debt on credit card
    }
}
