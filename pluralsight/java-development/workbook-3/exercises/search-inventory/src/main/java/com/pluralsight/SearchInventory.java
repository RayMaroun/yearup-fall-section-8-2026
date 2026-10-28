package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * SearchInventory (Workbook 3a, Section 3-2 Using an ArrayList, Exercise 1)
 *
 * Keeps the store's products in an ArrayList, a list that grows as products
 * are added (an array has a fixed size).
 *
 * All three BONUS parts are done here:
 *   - the products are loaded from inventory.csv (id|name|price per line)
 *     instead of being typed in the code
 *   - the products are sorted by name before they are shown
 *   - a menu loop: list, look up by id, find by price range, add, quit
 *
 * The first version (five products added in the code) is kept switched off
 * inside getInventory(), below the file reading.
 * inventory.csv sits in the project folder, next to pom.xml.
 */
public class SearchInventory {
    public static void main(String[] args) {
        ArrayList<Product> inventory = getInventory();

        // BONUS: sort by name.
        // Comparator.comparing(Product::getName) means "compare two products by their name"
        Collections.sort(inventory, Comparator.comparing(Product::getName));

        // Print the inventory
        listAllProducts(inventory);

        // BONUS: menu driven user interface
        Scanner input = new Scanner(System.in);
        int command = 0;

        // Keep showing the menu until the user picks 5
        while (command != 5) {
            System.out.println("What do you want to do?");
            System.out.println("1- List all products");
            System.out.println("2- Lookup a product by its id");
            System.out.println("3- Find all products within a price range");
            System.out.println("4- Add a new product");
            System.out.println("5- Quit the application");

            System.out.print("Enter command: ");
            command = input.nextInt();

            // One method per menu option keeps main short and readable
            switch (command) {
                case 1:
                    listAllProducts(inventory);
                    break;
                case 2:
                    lookupProductById(inventory);
                    break;
                case 3:
                    findProductsByPriceRange(inventory);
                    break;
                case 4:
                    addNewProduct(inventory);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    // Builds the list and hands it back to main
    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<>();

        // BONUS: read the products from inventory.csv
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
            // One product per line, until readLine() returns null at the end of the file
            while ((line = br.readLine()) != null) {
                // Split on | (escaped as \\| because split takes a pattern)
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                inventory.add(new Product(id, name, price));
            }
            br.close();
        } catch (Exception e) {
            // printStackTrace shows the full error, useful while building the program
            e.printStackTrace();
        }

        // Add products to the inventory: the first version, before the file bonus.
        // To try it, remove the comment markers (and comment out the file reading above).
        /*inventory.add(new Product(1, "Shirt", 19.99));
        inventory.add(new Product(2, "Pants", 29.99));
        inventory.add(new Product(3, "Shoes", 49.99));
        inventory.add(new Product(4, "Hat", 14.99));
        inventory.add(new Product(5, "Jacket", 79.99));*/

        return inventory;
    }

    // Menu 1: println(product) calls the toString() method of Product
    private static void listAllProducts(ArrayList<Product> inventory) {
        for (Product product : inventory) {
            System.out.println(product);
        }
    }

    // Menu 2: walk the list until the id matches
    private static void lookupProductById(ArrayList<Product> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int id = input.nextInt();

        for (Product product : inventory) {
            if (product.getId() == id) {
                System.out.println(product);
                // return leaves the method as soon as the product is found
                return;
            }
        }
        // We only get here if the loop finished without a match
        System.out.println("Product not found.");
    }

    // Menu 3: show every product whose price is between min and max (both included)
    private static void findProductsByPriceRange(ArrayList<Product> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double minPrice = input.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = input.nextDouble();

        for (Product product : inventory) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                System.out.println(product);
            }
        }
    }

    // Menu 4: the list grows by one; no size to manage, unlike an array
    private static void addNewProduct(ArrayList<Product> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int id = input.nextInt();
        // nextInt leaves the Enter in the buffer; this nextLine() eats it
        // so the name is read correctly on the next line
        input.nextLine();
        System.out.print("Enter product name: ");
        String name = input.nextLine();
        System.out.print("Enter product price: ");
        double price = input.nextDouble();
        inventory.add(new Product(id, name, price));
    }
}
