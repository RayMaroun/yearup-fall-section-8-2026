package com.pluralsight.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Portfolio (Workbook 6a, Section 2-2 Working with Interfaces, Exercise 2)
 *
 * Everything one person owns and owes, in one list.
 *
 * The list is a List<Valuable>: the interface is used as the type. That
 * is why a Jewelry, a House, a BankAccount and a CreditCard can all go
 * into the same list, although they do not share a parent class. The
 * Portfolio does not need to know which class each item is. It only
 * needs the one thing they all promise: getValue().
 */
public class Portfolio {
    private String name;
    private String owner;
    private List<Valuable> assets;

    public Portfolio(String name, String owner) {
        this.name = name;
        this.owner = owner;
        // The list starts empty, items come in through add()
        this.assets = new ArrayList<>();
    }

    // The parameter is a Valuable, so any object whose class implements
    // Valuable is accepted
    public void add(Valuable asset) {
        assets.add(asset);
    }

    // The net value: everything added together. A credit card returns a
    // negative value, so adding it lowers the total
    public BigDecimal getValue() {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (Valuable valuable : assets) {
            // Java runs the getValue() of the real class of each object.
            // add() returns a NEW BigDecimal, so the result has to be
            // assigned back to totalValue
            totalValue = totalValue.add(valuable.getValue());
        }

/*        for(int i =0; i < assets.size(); i++){
            totalValue = totalValue.add(assets.get(i).getValue());
        }*/
        return totalValue;
    }

    // Returns the item with the highest value, or null when the portfolio
    // is empty. The caller has to check for null before using the result
    public Valuable getMostValuable() {
        if (assets.isEmpty()) {
            return null;
        }

        // Start with the first item as the best one seen so far
        Valuable mostValuable = assets.get(0);

        for (Valuable asset : assets) {
            // BigDecimal has no > operator. a.compareTo(b) returns a
            // positive number when a is bigger than b, 0 when they are
            // equal and a negative number when a is smaller
            if (asset.getValue().compareTo(mostValuable.getValue()) > 0) {
                mostValuable = asset;
            }
        }

        return mostValuable;
    }

    // The same search the other way round: keep the lowest value seen so
    // far. With a credit card in the list, the lowest value is negative
    public Valuable getLeastValuable() {
        if (assets.isEmpty()) {
            return null;
        }

        Valuable leastValuable = assets.get(0);

        for (Valuable asset : assets) {
            // compareTo(...) < 0 means "this asset is worth less than the lowest so far"
            if (asset.getValue().compareTo(leastValuable.getValue()) < 0) {
                leastValuable = asset;
            }
        }

        return leastValuable;
    }

}
