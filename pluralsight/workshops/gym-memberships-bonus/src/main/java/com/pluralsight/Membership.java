package com.pluralsight;

import java.math.BigDecimal;   // BigDecimal lives in java.math, so it needs an import

/**
 * Membership (Inheritance homework, Gym Memberships)
 *
 * The parent class: what every gym membership has in common. A member
 * name, the number of months of the contract, and the base rate, which
 * is the monthly rate the price of every kind of membership starts from.
 *
 * The class is abstract. "A membership" on its own is only an idea: at
 * the front desk a person signs up for a basic, a premium or a family
 * membership, never for "a membership". The word abstract says so:
 *   - "new Membership(...)" does not compile
 *   - the class still has variables, a constructor, getters and setters,
 *     and the child classes inherit them
 *
 * It holds three kinds of methods:
 *   - getMonthlyPrice() is abstract: every kind has its own price rule,
 *     so there is no body here and every child class MUST write one
 *   - getSignUpFee() is concrete and can be overridden: most kinds pay
 *     the same fee, one kind has its own rule
 *   - getContractTotal() is concrete and nobody overrides it: the formula
 *     is the same for every kind, only the numbers that go in differ
 */
public abstract class Membership {
    // Private: the child classes read these through the getters
    private String memberName;
    private int months;

    // Private like the other two: the child classes read it through
    // getBaseRate() when they work out their monthly price
    private BigDecimal baseRate;

    // An abstract class can have a constructor. Nobody can call it with
    // "new Membership(...)", but the child classes call it with super(...)
    public Membership(String memberName, int months, BigDecimal baseRate) {
        this.memberName = memberName;
        this.months = months;
        this.baseRate = baseRate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    // An abstract method: only the signature and a semicolon, no { } body.
    // It is a promise that every membership can tell its monthly price,
    // and it leaves the calculation to the child classes
    public abstract BigDecimal getMonthlyPrice();

    // The general rule: joining the gym costs 49.00, paid once.
    // A child class with a different rule overrides this method
    public BigDecimal getSignUpFee() {
        return new BigDecimal("49.00");
    }

    // What the whole contract costs: the monthly price for every month,
    // plus the sign-up fee.
    // This method is written once, here, and it still gives the right
    // answer for every kind: getMonthlyPrice() and getSignUpFee() run the
    // version of the object's REAL class.
    // BigDecimal has no * or + operator: multiply() and add() do the work.
    // multiply() needs another BigDecimal, so the int months is converted
    // with BigDecimal.valueOf()
    public BigDecimal getContractTotal() {
        BigDecimal total = getMonthlyPrice().multiply(BigDecimal.valueOf(months));
        // add() returns a NEW BigDecimal, so the result is assigned back
        total = total.add(getSignUpFee());
        return total;
    }

    // toString comes from Object, the parent of every class, so this is
    // an override too. The three child classes override it again with
    // their own line
    @Override
    public String toString() {
        return "Membership | " + memberName + " | " + months + " months";
    }
}
