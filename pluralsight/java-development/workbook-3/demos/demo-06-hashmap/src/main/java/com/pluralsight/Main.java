package com.pluralsight;

import java.util.HashMap;

/*
 * Demo 06 - HashMap
 *
 * A list finds things by position: kids.get(0). A map finds things by KEY:
 * products.get(1234), capitals.get("TX"). Every entry is a pair, key and
 * value, and the key is unique: put the same key twice and the second value
 * replaces the first.
 *
 * The map is declared with two types in the angle brackets, the key type
 * first and the value type second: HashMap<Integer, Product>,
 * HashMap<String, String>.
 *
 * The methods used here:
 *   put(key, value)   store a pair
 *   get(key)          the value for that key, or null when the key is not there
 *   remove(key)       take the pair out
 *   values()          every value, for a for-each loop
 *   keySet()          every key, for a for-each loop (then get(key) for the value)
 *
 * One more thing: a map keeps NO order. Printing it shows the entries in
 * whatever order Java stored them, not the order you put them in.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- put: a map of products by id ----------
        System.out.println("========== put: products by id ==========");
        HashMap<Integer, Product> idsAndProducts = new HashMap<>(); // key type first, value type second

        // Two ways to put: the key read from the object, or typed as a literal
        Product newProduct = new Product(1, "Potato", 123);
        idsAndProducts.put(newProduct.getProductID(), newProduct);   // the key comes from the object itself
        idsAndProducts.put(2, new Product(2, "Tomato", 456));       // or a literal key

        // Printing a map shows {key=value, key=value}; the values print through toString
        System.out.println(idsAndProducts);
        System.out.println();

        // ---------- values: every product ----------
        System.out.println("========== values ==========");
        // values() hands back the values only; the loop variable is a Product
        for (Product product : idsAndProducts.values()) {
            System.out.println(product);
        }
        System.out.println();

        // ---------- keySet: every key, then get by key ----------
        System.out.println("========== keySet ==========");
        // keySet() hands back the keys; get(key) fetches the value that goes with each one
        for (Integer key : idsAndProducts.keySet()) {
            System.out.println(key + ": " + idsAndProducts.get(key));
        }
        System.out.println();

        // ---------- put and get: states and capitals ----------
        System.out.println("========== put and get: states and capitals ==========");
        // Both the key and the value are Strings here
        HashMap<String, String> statesAndCapitals = new HashMap<>();

        statesAndCapitals.put("CT", "Hartford");
        statesAndCapitals.put("CA", "Sacramento");
        statesAndCapitals.put("WA", "Olympia");
        statesAndCapitals.put("TX", "Austin");
        statesAndCapitals.put("FL", "Tallahassee");

        System.out.println(statesAndCapitals);                      // no fixed order: a map is not a list

        // One lookup, one step: no loop, no index, just the key
        System.out.println("The capital of Texas: " + statesAndCapitals.get("TX")); // get by key, no index

        // remove takes the whole pair out, key and value
        statesAndCapitals.remove("WA");

        System.out.println(statesAndCapitals);
        System.out.println();

        // ---------- values ----------
        System.out.println("========== values: capitals ==========");
        for (String value : statesAndCapitals.values()) {
            System.out.println(value);
        }
        System.out.println();

        // ---------- keySet ----------
        System.out.println("========== keySet: states ==========");
        // The map, then only its keys in brackets, then each key with its value
        System.out.println(statesAndCapitals);
        System.out.println(statesAndCapitals.keySet());

        for (String key : statesAndCapitals.keySet()) {
            System.out.println(key + ": " + statesAndCapitals.get(key));
        }
        System.out.println();

        // ---------- Failed lookup ----------
        System.out.println("========== Failed lookup ==========");
        // A list throws an exception for a bad index. A map does not: a missing key gives null.
        System.out.print("The capital of Oklahoma is: ");
        System.out.println(statesAndCapitals.get("OK"));            // a key that is not there gives null, no exception

        // So the program checks for null before using the value. This is the
        // shape of the Search Inventory Map exercise.
        System.out.print("The capital of Oklahoma is: ");
        String value = statesAndCapitals.get("OK");
        if (value != null) {                                        // check before using the value
            System.out.println(value);
        } else {
            System.out.println("OK is not in the states map");
        }
        System.out.println();
    }
}
