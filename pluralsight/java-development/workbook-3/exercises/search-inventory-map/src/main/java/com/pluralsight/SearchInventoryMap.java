package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * SearchInventoryMap (Workbook 3a, Section 4-1 Using a HashMap, Exercise 2)
 *
 * The same store inventory as search-inventory, kept in a HashMap instead of an
 * ArrayList. The key is the product id, the value is the Product, so finding a
 * product is one call, inventory.get(id), with no loop over the whole list.
 *
 *   What item # are you interested in? 1234
 *   We carry Hammer and the price is $19.49
 *
 * The BONUS (keep searching while the user answers y) is kept switched off
 * inside main, right after the single search.
 * inventory.csv sits in the project folder, next to pom.xml.
 */
public class SearchInventoryMap {
    // the key is the product id, the value is a product object
    // static: shared by main and loadInventory without passing it around
    public static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void main(String[] args) {
        loadInventory();


        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();

        // get() returns the Product stored under that key, or null if the key is not there
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
        } else {
            System.out.printf("We carry %s and the price is $%.2f\n",
                    matchedProduct.getName(), matchedProduct.getPrice());
        }

        // BONUS: look up more than one product.
        // To try it, comment out the single search above (from Scanner to the end
        // of the if/else) and remove the comment markers below.
/*        Scanner scanner = new Scanner(System.in);
        boolean keepSearching = true;

        while (keepSearching) {
            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();

            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f\n",
                        matchedProduct.getName(), matchedProduct.getPrice());
            }

            System.out.print("Do you want to go again? (y/n): ");
            String answer = scanner.next();
            keepSearching = answer.equalsIgnoreCase("y");
        }*/
    }

    // Reads inventory.csv (id|name|price per line) and puts each product in the map
    private static void loadInventory() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
            while ((line = br.readLine()) != null) {
                // Split on | (escaped as \\| because split takes a pattern)
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                // put(key, value): the id is the key we will search with later
                inventory.put(id, new Product(id, name, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
