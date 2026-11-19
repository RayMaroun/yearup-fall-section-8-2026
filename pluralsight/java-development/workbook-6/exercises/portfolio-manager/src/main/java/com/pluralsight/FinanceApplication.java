package com.pluralsight;

import com.pluralsight.finance.BankAccount;
import com.pluralsight.finance.Valuable;

import java.math.BigDecimal;

/**
 * FinanceApplication (Workbook 6a, Section 2-1 Implementing Interfaces, Exercise 1)
 *
 * Creates two bank accounts and tries to deposit money into both. The two
 * objects are the same kind of object, but the two VARIABLES have
 * different types, and that changes what can be called.
 *
 * Questions from the exercise:
 *
 * Did the deposit work for both accounts? Why or why not?
 *   Only for account1. "account2.deposit(...)" does not compile: the
 *   compiler looks at the type of the variable, Valuable, and Valuable has
 *   no deposit() method.
 *
 * What methods are available for account1?
 *   The variable is a BankAccount, so everything a BankAccount has:
 *   deposit(), withdraw() and getValue().
 *
 * What methods are available for account2?
 *   The variable is a Valuable, so only what the interface lists:
 *   getValue().
 *
 * Why?
 *   The type of the variable decides which methods can be called. The
 *   object behind account2 is still a full BankAccount, with its deposit()
 *   method, but through a Valuable variable only the Valuable part of it
 *   is visible.
 */
public class FinanceApplication {
    public static void main(String[] args) {
        // Money amounts are BigDecimal objects built from a String, so the
        // amount is stored exactly as it is written
        BankAccount account1 = new BankAccount("Pam", "123", new BigDecimal("12500"));
        // A variable of the interface type can hold any object whose class
        // implements that interface
        Valuable account2 = new BankAccount("Gary", "456", new BigDecimal("1500"));

        // try to deposit money into both accounts
        account1.deposit(new BigDecimal("100"));
        // Remove the // to see the error:
        // Cannot resolve method 'deposit' in 'Valuable'
        //account2.deposit(new BigDecimal("100"));

        // getValue() is in the interface, so it can be called on both.
        // %.2f prints the amount with two decimal places, %n ends the line
        System.out.printf("Account 1 value: $%.2f%n", account1.getValue());
        System.out.printf("Account 2 value: $%.2f%n", account2.getValue());
    }
}
