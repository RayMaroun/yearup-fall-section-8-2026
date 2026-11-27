package com.pluralsight;

import java.math.BigDecimal;

/**
 * PremiumMembership (Inheritance homework, Gym Memberships)
 *
 * A child class of Membership: a PremiumMembership IS a Membership. It
 * adds a number of guest passes, and a method only this kind has:
 * useGuestPass().
 *
 * Monthly price:
 *   the base rate plus 18.50 for the classes and the sauna
 *
 * Sign-up fee (this class has its own rule):
 *   0.00 when the contract is 12 months or longer
 *   49.00 otherwise
 *
 * Example: base rate 30.00, 12 months: 48.50 a month, no sign-up fee,
 * contract total 48.50 x 12 = 582.00
 */
public class PremiumMembership extends Membership {
    private int guestPasses;

    public PremiumMembership(String memberName, int months, BigDecimal baseRate, int guestPasses) {
        // First statement: the three values every membership has go up to
        // the Membership constructor. The fourth belongs to this class
        super(memberName, months, baseRate);
        this.guestPasses = guestPasses;
    }

    public int getGuestPasses() {
        return guestPasses;
    }

    public void setGuestPasses(int guestPasses) {
        this.guestPasses = guestPasses;
    }

    // The premium price rule. baseRate is private in Membership, so it
    // is read with getBaseRate()
    @Override
    public BigDecimal getMonthlyPrice() {
        return getBaseRate().add(new BigDecimal("18.50"));
    }

    // A second override: Membership already has a getSignUpFee() with a
    // body, and this class replaces it with its own rule.
    // months is private in Membership, so it is read with the getter
    @Override
    public BigDecimal getSignUpFee() {
        if (getMonths() >= 12) {
            return new BigDecimal("0.00");
        }
        return new BigDecimal("49.00");
    }

    // Only a premium membership has this method. Through a variable of
    // type Membership it cannot be called: the caller needs instanceof
    // and a downcast first (see Main).
    // It changes the object: every pass that is used is gone
    public void useGuestPass() {
        if (guestPasses > 0) {
            guestPasses--;
            System.out.println(getMemberName() + " used a guest pass. Passes left: " + guestPasses);
        } else {
            System.out.println(getMemberName() + " has no guest passes left.");
        }
    }

    @Override
    public String toString() {
        return "Premium | " + getMemberName() + " | " + getMonths() + " months | guest passes: " + guestPasses;
    }
}
