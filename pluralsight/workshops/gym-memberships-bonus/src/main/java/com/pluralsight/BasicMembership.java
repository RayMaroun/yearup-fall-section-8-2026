package com.pluralsight;

import java.math.BigDecimal;

/**
 * BasicMembership (Inheritance homework, Gym Memberships)
 *
 * A child class of Membership: a BasicMembership IS a Membership. It adds
 * one thing of its own: whether the member may come in during the quiet
 * hours only (off-peak).
 *
 * Monthly price:
 *   the base rate
 *   5.00 less when the membership is off-peak only
 *
 * Example: base rate 30.00, off-peak only: 30.00 - 5.00 = 25.00
 *
 * The sign-up fee is not mentioned in this class: the inherited
 * getSignUpFee() from Membership (49.00) is the right one.
 */
public class BasicMembership extends Membership {
    private boolean offPeakOnly;

    public BasicMembership(String memberName, int months, BigDecimal baseRate, boolean offPeakOnly) {
        // First statement: the three values every membership has go up to
        // the Membership constructor. The fourth belongs to this class
        super(memberName, months, baseRate);
        this.offPeakOnly = offPeakOnly;
    }

    // The getter of a boolean starts with "is" instead of "get"
    public boolean isOffPeakOnly() {
        return offPeakOnly;
    }

    public void setOffPeakOnly(boolean offPeakOnly) {
        this.offPeakOnly = offPeakOnly;
    }

    // @Override tells the compiler "this method replaces one from the
    // parent". Membership declares getMonthlyPrice() as abstract, so
    // without this method the class would not compile:
    // BasicMembership is not abstract and does not override abstract method getMonthlyPrice() in Membership
    @Override
    public BigDecimal getMonthlyPrice() {
        // baseRate is private in Membership, so it is read with getBaseRate().
        // The 5.00 is built from a String so it is exactly 5.00
        if (offPeakOnly) {
            return getBaseRate().subtract(new BigDecimal("5.00"));
        }
        return getBaseRate();
    }

    // memberName and months are private in Membership, so they are read
    // through the inherited getters. offPeakOnly is this class's own
    // variable, so it is used directly
    @Override
    public String toString() {
        return "Basic | " + getMemberName() + " | " + getMonths() + " months | off-peak only: " + offPeakOnly;
    }
}
