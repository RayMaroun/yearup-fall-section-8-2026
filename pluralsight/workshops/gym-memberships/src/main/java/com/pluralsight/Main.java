package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Main (Inheritance homework, Gym Memberships)
 *
 * Builds six memberships of three kinds, puts them in ONE list, prints
 * what each one costs, adds up the totals, and prints the notes for the
 * front desk, and the class name of every object.
 *
 * The list is declared as a list of Membership, but the objects in it
 * are BasicMembership, PremiumMembership and FamilyMembership objects.
 * When the loop calls membership.getMonthlyPrice(), Java looks at the
 * REAL type of the object and runs that class's version of the method.
 * That is polymorphism: one call, many forms.
 *
 * Expected amounts:
 *   Alice Johnson      30.00 - 5.00 = 25.00         x 6  + 49.00 = 199.00
 *   Bob Smith      27.50                        x 2  + 49.00 = 104.00
 *   Carol White     30.00 + 18.50 = 48.50        x 12 +  0.00 = 582.00
 *   Frank Miller     30.00 + 18.50 = 48.50        x 3  + 49.00 = 194.50
 *   Grace Lee  (30.00 + 37.50) x 0.90 = 60.75  x 12 + 49.00 = 778.00
 *   Emma Wilson     30.00 + 12.50 = 42.50        x 6  + 49.00 = 304.00
 *   Monthly income 252.75, all contracts 2161.50
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Gym Memberships ===");

        // Trying to create a plain Membership. Remove the // to see the error:
        // Membership is abstract; cannot be instantiated
        //Membership plain = new Membership("Henry Clark", 3, new BigDecimal("30.00"));

        // Money amounts are BigDecimal objects built from a String, so the
        // amount is stored exactly as it is written
        BasicMembership basic1 = new BasicMembership("Alice Johnson", 6, new BigDecimal("30.00"), true);
        BasicMembership basic2 = new BasicMembership("Bob Smith", 2, new BigDecimal("27.50"), false);
        PremiumMembership premium1 = new PremiumMembership("Carol White", 12, new BigDecimal("30.00"), 2);
        PremiumMembership premium2 = new PremiumMembership("Frank Miller", 3, new BigDecimal("30.00"), 0);
        FamilyMembership family1 = new FamilyMembership("Grace Lee", 12, new BigDecimal("30.00"), 3);
        FamilyMembership family2 = new FamilyMembership("Emma Wilson", 6, new BigDecimal("30.00"), 1);

        // Membership is abstract, but it is still a type: a list of the
        // parent type can hold any child object
        ArrayList<Membership> memberships = new ArrayList<>();
        memberships.add(basic1);
        memberships.add(basic2);
        memberships.add(premium1);
        memberships.add(premium2);
        memberships.add(family1);
        memberships.add(family2);

        // The two totals start at zero. BigDecimal.ZERO is a ready-made 0
        BigDecimal monthlyIncome = BigDecimal.ZERO;
        BigDecimal allContracts = BigDecimal.ZERO;

        for (Membership membership : memberships) {
            // Printing an object calls its toString() automatically, and
            // Java runs the version of the object's real class
            System.out.println(membership);

            // The line with the three amounts is printed by a method of
            // this class. Its parameter is of type Membership, so any kind
            // of membership can be passed to it. No instanceof is needed:
            // the three methods exist in Membership, and Java picks the
            // right version on its own
            printCosts(membership);

            // add() returns a NEW BigDecimal, so the result is assigned back
            monthlyIncome = monthlyIncome.add(membership.getMonthlyPrice());
            allContracts = allContracts.add(membership.getContractTotal());
        }

        System.out.println("--- Totals ---");
        System.out.println("Memberships: " + memberships.size());
        System.out.printf("Monthly income: $%.2f%n", monthlyIncome);
        System.out.printf("All contracts: $%.2f%n", allContracts);

        System.out.println("--- Front desk notes ---");
        for (Membership membership : memberships) {
            // The variable "membership" is of type Membership, so only
            // Membership's methods can be called on it: isOffPeakOnly(),
            // useGuestPass() or getExtraMembers() would not compile.
            // instanceof asks "is this object really a BasicMembership?".
            // If it is, the downcast (BasicMembership) gives a variable of
            // the child type for the same object, and the child's own
            // methods become available
            if (membership instanceof BasicMembership) {
                BasicMembership basic = (BasicMembership) membership;
                if (basic.isOffPeakOnly()) {
                    System.out.println(basic.getMemberName() + ": entry between 10:00 and 16:00 only");
                }
            } else if (membership instanceof PremiumMembership) {
                PremiumMembership premium = (PremiumMembership) membership;
                // The method prints its own line, and takes one pass away
                premium.useGuestPass();
            } else if (membership instanceof FamilyMembership) {
                FamilyMembership family = (FamilyMembership) membership;
                // One key card for the member, plus one for every extra member
                System.out.println(family.getMemberName() + ": print " + (family.getExtraMembers() + 1) + " key cards");
            }
        }

        System.out.println("--- Kinds ---");
        for (Membership membership : memberships) {
            // getClass() gives the real class of the object, and
            // getSimpleName() gives its name without the package
            System.out.println(membership.getMemberName() + ": " + membership.getClass().getSimpleName());
        }
    }

    // Parent type as a parameter: this method is written once and works
    // for every kind of membership, because each of them is-a Membership.
    // %.2f prints the amount with two decimal places, %n ends the line
    public static void printCosts(Membership membership) {
        System.out.printf("  Monthly price: $%.2f, sign-up fee: $%.2f, contract total: $%.2f%n",
                membership.getMonthlyPrice(), membership.getSignUpFee(), membership.getContractTotal());
    }
}
