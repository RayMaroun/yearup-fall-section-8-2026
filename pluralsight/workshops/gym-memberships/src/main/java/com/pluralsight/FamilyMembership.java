package com.pluralsight;

import java.math.BigDecimal;

/**
 * FamilyMembership (Inheritance homework, Gym Memberships)
 *
 * A child class of Membership: a FamilyMembership IS a Membership. One
 * person signs the contract (the member name) and brings a number of
 * extra family members on the same membership.
 *
 * Monthly price:
 *   the base rate plus 12.50 for every extra member
 *   then 10% off the whole monthly price when there are 3 extra members
 *   or more
 *
 * Example: base rate 30.00, 3 extra members:
 *   30.00 + 12.50 x 3 = 67.50, minus 10%: 67.50 x 0.90 = 60.75
 *
 * The sign-up fee is not mentioned in this class: the inherited
 * getSignUpFee() from Membership (49.00) is the right one.
 */
public class FamilyMembership extends Membership {
    private int extraMembers;

    public FamilyMembership(String memberName, int months, BigDecimal baseRate, int extraMembers) {
        // First statement: the three values every membership has go up to
        // the Membership constructor. The fourth belongs to this class
        super(memberName, months, baseRate);
        this.extraMembers = extraMembers;
    }

    public int getExtraMembers() {
        return extraMembers;
    }

    public void setExtraMembers(int extraMembers) {
        this.extraMembers = extraMembers;
    }

    @Override
    public BigDecimal getMonthlyPrice() {
        // 12.50 for every extra member. multiply() needs a BigDecimal, so
        // the int extraMembers is converted with BigDecimal.valueOf()
        BigDecimal extras = new BigDecimal("12.50").multiply(BigDecimal.valueOf(extraMembers));
        BigDecimal price = getBaseRate().add(extras);

        // 10% off means the family pays 90% of the price.
        // multiply() returns a NEW BigDecimal, so it is assigned back
        if (extraMembers >= 3) {
            price = price.multiply(new BigDecimal("0.90"));
        }
        return price;
    }

    @Override
    public String toString() {
        return "Family | " + getMemberName() + " | " + getMonths() + " months | extra members: " + extraMembers;
    }
}
