package com.pluralsight;

// Exercise: Method Practice, file 2. One method prints the menu; main calls it twice to show it is reusable.
public class Menu {
    public static void main(String[] args) {
        displayMenu();   // first call
        displayMenu();   // second call (reusability)
    }

    // One method, written once, called as many times as needed
    public static void displayMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Coffee - $3.99");
        System.out.println("2. Tea - $2.99");
        System.out.println("3. Cookie - $1.99");
    }
}
