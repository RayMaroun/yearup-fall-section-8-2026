package com.pluralsight;

import java.math.BigDecimal;

/**
 * CorporateMembership (Inheritance homework, Gym Memberships, Bonus 1)
 *
 * A fourth child class of Membership: a membership that comes with a job.
 * The employer pays a share of the base rate, and the member pays the
 * rest.
 *
 * Monthly price:
 *   the base rate minus the employer's share of it
 *   (the share is a fraction: 0.40 means the employer pays 40%)
 *
 * Sign-up fee:
 *   always 0.00, the employer has a deal with the gym
 *
 * Example: base rate 30.00, employer share 0.40:
 *   30.00 - 30.00 x 0.40 = 30.00 - 12.00 = 18.00
 *
 * Nothing in the first loop of Main changed when this class was added:
 * the loop works on Membership, and a CorporateMembership IS a Membership.
 */
public class CorporateMembership extends Membership {
    private BigDecimal employerShare;

    public CorporateMembership(String memberName, int months, BigDecimal baseRate, BigDecimal employerShare) {
        // First statement: the three values every membership has go up to
        // the Membership constructor. The fourth belongs to this class
        super(memberName, months, baseRate);
        this.employerShare = employerShare;
    }

    public BigDecimal getEmployerShare() {
        return employerShare;
    }

    public void setEmployerShare(BigDecimal employerShare) {
        this.employerShare = employerShare;
    }

    @Override
    public BigDecimal getMonthlyPrice() {
        // What the employer pays, then what is left for the member
        BigDecimal employerPart = getBaseRate().multiply(employerShare);
        return getBaseRate().subtract(employerPart);
    }

    @Override
    public BigDecimal getSignUpFee() {
        return new BigDecimal("0.00");
    }

    @Override
    public String toString() {
        return "Corporate | " + getMemberName() + " | " + getMonths() + " months | employer share: " + employerShare;
    }
}
