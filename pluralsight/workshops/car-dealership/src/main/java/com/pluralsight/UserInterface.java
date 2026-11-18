package com.pluralsight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * User interface layer: everything the user sees and types goes through here.
 * <p>
 * This class owns the menu loop, reads the keyboard, calls the Dealership
 * for searches, adds and removes, and prints the results. It holds no
 * business logic of its own: it never filters a list itself and never reads
 * or writes the file directly. The file work is delegated to
 * DealershipFileManager, the searching to Dealership.
 * <p>
 * How the class is organized:
 * • display            loads the dealership (init), then runs the menu loop
 * • processGetBy...    one method per search option: ask, search, show
 * • processGetAllVehiclesRequest  lists the whole inventory
 * • processAddVehicleRequest      asks for every field, adds, saves the file
 * • processRemoveVehicleRequest   asks for a VIN, removes, saves the file
 * • init               private, builds the dealership from the file
 * • displayVehicles    private helper, prints any list of vehicles
 * • readInt, readBigDecimal private helpers, ask again on a typo instead of crashing
 * <p>
 * The dealership is created here and not in the Program class, as the
 * workshop asks: a later version could let the user pick among several
 * dealerships, and that choice belongs to the user interface.
 */
public class UserInterface {

    private Dealership dealership;
    // One Scanner for the whole program, created once in the constructor and
    // shared by every process method; two Scanners on System.in would fight
    // over the same input
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        // Load the dealership first: every menu option needs it
        init();
        boolean quit = false;
        while (!quit) {
            System.out.println("---------- Menu ----------");
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");

            System.out.print("Enter your choice: ");
            // The choice is read as a String, not an int, so a typo like "abc"
            // lands in the default case instead of crashing the program
            String choice = scanner.nextLine();

            // The switch only dispatches: each case hands the work to one
            // process method, so this loop stays short and the menu order is
            // easy to read against the workshop's list of options
            switch (choice) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "99":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /* -----------------------------------------------------------------
       Searches
       Each one follows the same three steps: ask for the criteria, call the
       matching Dealership search, hand the result to displayVehicles.
       ----------------------------------------------------------------- */

    public void processGetByPriceRequest() {
        BigDecimal min = readBigDecimal("Enter minimum price: ");
        BigDecimal max = readBigDecimal("Enter maximum price: ");
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        int min = readInt("Enter minimum year: ");
        int max = readInt("Enter maximum year: ");
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        int min = readInt("Enter minimum mileage: ");
        int max = readInt("Enter maximum mileage: ");
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    // Coded first in the workshop (Phase 3): it is the quickest way to prove
    // the file was loaded into the dealership
    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    /* -----------------------------------------------------------------
       Add and remove: both change the inventory, so both end by saving the
       file. The workshop requires the file to be rewritten after every add
       or remove, otherwise the change is lost when the program quits.
       ----------------------------------------------------------------- */

    public void processAddVehicleRequest() {
        int vin = readInt("Enter vehicle vin: ");

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        int year = readInt("Enter vehicle year: ");

        BigDecimal price = readBigDecimal("Enter vehicle price: ");

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        int mileage = readInt("Enter vehicle mileage: ");

        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.nextLine();

        // The questions are asked in a natural order for the user; the
        // constructor call puts the values back in the file's column order
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
        // Save at once: the in-memory list and the file must agree
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        int vin = readInt("Enter the VIN of the vehicle you wish to remove: ");

        // Dealership.removeVehicle takes a Vehicle object, so find the one
        // with this VIN first. break right after the remove: changing a list
        // while a for-each loop walks it is not allowed
        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        // Nothing changed, so nothing to save: return before the file is rewritten
        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    /* -----------------------------------------------------------------
       Private helpers
       ----------------------------------------------------------------- */

    // Builds the dealership from the file. Private: only display calls it,
    // and only once, before the menu loop starts
    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        dealership = manager.getDealership();
    }

    // One place to print a list of vehicles. Every search and the "get all"
    // option call it, so the layout is the same on every screen and a change
    // to it is made once
    /* -----------------------------------------------------------------
       Reading numbers safely
       scanner.nextInt() and nextBigDecimal() throw InputMismatchException on a
       typo like "abc" and leave the Enter key in the buffer. Reading the
       whole line as text and converting it avoids both: a bad entry is
       caught and the question is asked again. Same idea as the capstone.
       ----------------------------------------------------------------- */

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    // A price is read as a BigDecimal, built from the text the user typed,
    // so 41999.50 stays 41999.50
    private BigDecimal readBigDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {                                   // a search with no match prints a line, not silence
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }

}
