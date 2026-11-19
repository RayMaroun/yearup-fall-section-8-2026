package com.pluralsight.finance;

import java.math.BigDecimal;

/**
 * FixedAsset (Workbook 6a, Section 2-1 Implementing Interfaces, Exercise 1)
 *
 * The parent of the things you own whose value does not change from day
 * to day: Jewelry, Gold and House. It holds what they have in common, a
 * name. It does NOT store a value: the value of each asset is worked out
 * from its own details every time getValue() is called.
 *
 * The class is abstract: "a fixed asset" on its own is only an idea, so
 * "new FixedAsset(...)" does not compile. It exists only to be extended.
 *
 * It implements Valuable, so every child class is a Valuable too, without
 * writing "implements Valuable" again.
 */
public abstract class FixedAsset implements Valuable {
    private String name;

    // Nobody can call this constructor with "new FixedAsset(...)", but the
    // child classes call it with super(...)
    public FixedAsset(String name) {
        this.name = name;
    }

    // The method promised by Valuable. FixedAsset gives it no body: there
    // is no general way to work out the value of a fixed asset, so every
    // child class MUST supply its own version
    public abstract BigDecimal getValue();
}
