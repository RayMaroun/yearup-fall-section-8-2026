package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Console storefront demo.
 * <p>
 * Features demonstrated
 * • Reading data from a pipe-delimited file (SKU|Product Name|Price|Department)
 * • Storing inventory and a shopping cart in ArrayLists
 * • Simple user-input loop with basic validation
 * • Calculating totals and giving change at checkout
 * <p>
 * Deliberately kept simple: there is no “remove item” feature.
 * <p>
 * BONUS VERSION. Two things are added to the core solution, each marked BONUS:
 * • The cart and the receipt show each product once, with a quantity (HashMap)
 * • The receipt is also written to a file in the Receipts folder, named with
 *   the date and time of the sale (FileWriter + DateTimeFormatter)
 * <p>
 * How the program is organised:
 * • main loads the inventory once, then runs the Home Screen loop
 * • loadInventory reads products.csv into the inventory list
 * • displayProducts is the "Show Products" screen (add to cart)
 * • displayCart is the "Show Cart" screen (total, then check out or go back)
 * • checkOut takes the payment, gives change and empties the cart
 * • countItems (BONUS) turns the cart into a map of product id to quantity
 * • printLines (BONUS) prints the receipt lines to the screen or to a file
 * • saveReceipt (BONUS) writes the receipt file into the Receipts folder
 * • findProductById is the helper that looks a product up by its id
 * The two lists and the Scanner are created once in main and passed to each
 * screen as parameters, so every screen works on the same inventory and cart.
 */
public class Store {

    public static void main(String[] args) {

        /* -----------------------------------------------------------------
           1.  Prepare inventory and cart
           ----------------------------------------------------------------- */
        // ArrayList instead of an array: we do not know in advance how many
        // products the file holds, and the cart grows as the user shops
        ArrayList<Product> inventory = new ArrayList<>();
        ArrayList<Product> cart = new ArrayList<>();

        // products.csv sits at the project root, which is the working directory
        // when the program runs from IntelliJ
        loadInventory("products.csv", inventory);

        /* -----------------------------------------------------------------
           2.  Main menu loop
           ----------------------------------------------------------------- */
        // One Scanner for the whole program, shared with every screen
        Scanner scanner = new Scanner(System.in);
        int choice = -1;                   // any value other than 3 keeps the loop running
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            /* Accept only 1, 2, or 3.  Any text input is rejected. */
            // hasNextInt() looks at the input without consuming it, so a typo
            // like "abc" does not crash the program with an exception
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();        // discard the bad token
                continue;                  // restart the loop
            }
            choice = scanner.nextInt();
            scanner.nextLine();            // remove the newline that nextInt leaves

            // Arrow-style switch (Java 14+): each case runs one statement, no break needed
            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(inventory, cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();                   // always close the Scanner when finished
    }

    /**
     * Reads a file whose lines look like   SKU|Product Name|Price|Department
     * and fills the inventory list with Product objects.
     * The first line of the file is a header row (column names), not a product.
     */
    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Read the header line once and throw it away, so the loop below
            // only sees product lines ("Price" would not parse as a double)
            reader.readLine();

            String line;
            // readLine() returns null at the end of the file, which ends the loop
            while ((line = reader.readLine()) != null) {
                // "|" means OR in a regular expression, so it is escaped as \\|
                String[] fields = line.split("\\|");       // split on the pipe symbol
                Product product = new Product(
                        fields[0],                         // id (SKU)
                        fields[1],                         // name
                        Double.parseDouble(fields[2]),     // price (text to number)
                        fields[3]                          // department
                );
                inventory.add(product);
            }
            reader.close();                                // release the file
            System.out.println("Loaded " + inventory.size() + " products.");
        } catch (IOException e) {
            // For example: products.csv is missing or not in the project root
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    /**
     * Lists every product and lets the user add one to the cart.
     * If the user types X the method simply returns to the main menu.
     */
    public static void displayProducts(ArrayList<Product> inventory,
                                       ArrayList<Product> cart,
                                       Scanner scanner) {

        System.out.println("\nProducts");
        System.out.println("--------");
        // %.2f prints the price with exactly two decimals, like money
        for (Product p : inventory) {
            System.out.printf("%s  %s  $%.2f%n", p.getId(), p.getName(), p.getPrice());
        }

        System.out.print("Enter product ID to add to cart (or X to return): ");
        String id = scanner.nextLine().trim();     // trim() drops stray spaces around the id

        if (id.equalsIgnoreCase("X")) return;      // user chose to go back

        Product product = findProductById(id, inventory);
        // null is the helper's way of saying "no product has that id"
        if (product == null) {
            System.out.println("That ID does not exist.");
        } else {
            // The cart holds the same Product object as the inventory, no copy is made
            cart.add(product);
            System.out.println("Added \"" + product.getName() + "\" to your cart.");
        }
    }

    /**
     * Shows everything in the cart, the running total,
     * and offers the option to check out.
     * BONUS: each product is listed once with its quantity, so a cart with three
     * copies of the same game shows one line "x3", not three lines.
     */
    public static void displayCart(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {

        System.out.println("\nYour Cart");
        System.out.println("---------");

        if (cart.isEmpty()) {
            System.out.println("(empty)");
            return;                         // nothing more to do
        }

        // The total is recomputed every time the cart is shown, so it is always
        // right, whatever was added since the last visit
        double totalAmount = 0.0;
        for (Product p : cart) {
            totalAmount += p.getPrice();
        }

        // BONUS: one line per product with the quantity, instead of one line per copy
        HashMap<String, Integer> quantities = countItems(cart);
        printLines(quantities, inventory, null);      // null = print to the screen only
        System.out.printf("Total: $%.2f%n", totalAmount);

        System.out.print("C = Check Out,  X = Return to menu: ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("C")) {
            // The total is passed along so checkOut does not have to add it up again
            checkOut(inventory, cart, totalAmount, scanner);
        }   // any other input returns to menu
    }

    /**
     * Finalises the purchase.
     * Steps:
     * 1. Ask if the user really wants to pay.
     * 2. Take payment amount.
     * 3. Calculate change.
     * 4. Print a receipt and clear the cart.
     * BONUS: the receipt also goes to a file in the Receipts folder.
     */
    public static void checkOut(ArrayList<Product> inventory,
                                ArrayList<Product> cart,
                                double totalAmount,
                                Scanner scanner) {

        System.out.printf("Total amount owed: $%.2f%n", totalAmount);
        System.out.print("Proceed with purchase? (Y/N): ");
        // Anything other than Y cancels, so a typo never charges the customer
        if (!scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            System.out.println("Checkout cancelled.");
            return;
        }

        System.out.print("Enter payment amount: $");
        double payment = scanner.nextDouble();
        scanner.nextLine();                        // clear newline

        // The customer pays cash, so the payment must cover the whole total
        if (payment < totalAmount) {
            System.out.println("Insufficient payment. Transaction cancelled.");
            return;
        }

        double change = payment - totalAmount;

        // The receipt: every item that was bought (BONUS: once each, with the quantity),
        // the total, what was paid and the change given
        HashMap<String, Integer> quantities = countItems(cart);
        System.out.println("\nSales receipt");
        System.out.println("-------------");
        printLines(quantities, inventory, null);
        System.out.printf("Total:   $%.2f%n", totalAmount);
        System.out.printf("Paid:    $%.2f%n", payment);
        System.out.printf("Change:  $%.2f%n", change);

        // BONUS: the same receipt, written to Receipts/yyyyMMddHHmm.txt
        saveReceipt(quantities, inventory, totalAmount, payment, change);

        cart.clear();                              // the sale is done, start a fresh cart
        System.out.println("Thank you for your purchase!");
    }

    /* =====================================================================
       BONUS helpers
       ===================================================================== */

    /**
     * BONUS. Counts how many times each product is in the cart.
     * The key is the product id, the value is the quantity.
     * A HashMap is the right tool: one entry per distinct id, found by the id itself.
     */
    public static HashMap<String, Integer> countItems(ArrayList<Product> cart) {
        HashMap<String, Integer> quantities = new HashMap<>();
        for (Product p : cart) {
            String id = p.getId();
            // getOrDefault: the current count, or 0 the first time this id shows up
            int soFar = quantities.getOrDefault(id, 0);
            quantities.put(id, soFar + 1);
        }
        return quantities;
    }

    /**
     * BONUS. Prints one line per product: name, quantity and the line total.
     * The lines go to the screen. If a writer is given, they go to the file too,
     * so the receipt on screen and the receipt in the file are the same text.
     */
    public static void printLines(HashMap<String, Integer> quantities,
                                  ArrayList<Product> inventory,
                                  BufferedWriter writer) {
        // keySet() gives every product id in the map, one at a time
        for (String id : quantities.keySet()) {
            Product p = findProductById(id, inventory);   // the map only holds the id
            int qty = quantities.get(id);
            String line = String.format("%-28s x%-3d $%8.2f", p.getName(), qty, p.getPrice() * qty);
            if (writer == null) {
                System.out.println(line);
            } else {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);        // handled by saveReceipt
                }
            }
        }
    }

    /**
     * BONUS. Writes the receipt into the Receipts folder.
     * The file name is the date and time of the sale, for example 202611051432.txt,
     * so every sale gets its own file and the files sort in order.
     */
    public static void saveReceipt(HashMap<String, Integer> quantities,
                                   ArrayList<Product> inventory,
                                   double totalAmount, double payment, double change) {

        // The folder is created the first time; mkdirs does nothing when it exists
        File folder = new File("Receipts");
        folder.mkdirs();

        // yyyyMMddHHmm: year, month, day, hour, minute, as digits only
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        File file = new File(folder, stamp + ".txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Sales receipt");
            writer.newLine();
            writer.write("-------------");
            writer.newLine();
            printLines(quantities, inventory, writer);
            writer.write(String.format("Total:   $%.2f", totalAmount));
            writer.newLine();
            writer.write(String.format("Paid:    $%.2f", payment));
            writer.newLine();
            writer.write(String.format("Change:  $%.2f", change));
            writer.newLine();
            writer.close();                            // close, or the file stays empty
            System.out.println("Receipt saved as " + file.getPath());
        } catch (IOException | RuntimeException e) {
            // The sale still went through; only the file failed
            System.out.println("Could not save the receipt: " + e.getMessage());
        }
    }

    /**
     * Searches a list for the given ID.
     * Returns the Product if found, or null if not found.
     */
    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product p : inventory) {
            // equalsIgnoreCase: "av1051" finds AV1051 too
            if (p.getId().equalsIgnoreCase(id)) {
                return p;                          // found it, stop looking
            }
        }
        return null;                               // looked at every product, no match
    }
}
