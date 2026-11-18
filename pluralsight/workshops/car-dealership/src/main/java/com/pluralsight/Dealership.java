package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The dealership: its contact details and the list of vehicles it sells.
 * <p>
 * One Dealership is the first line of inventory.csv (name|address|phone)
 * plus every Vehicle line that follows it.
 * <p>
 * This is the business layer. It owns the inventory and offers every search,
 * add and remove the application needs. It never prints and never reads the
 * keyboard: UserInterface asks a question, Dealership answers with a list,
 * and UserInterface decides how to show it. It never touches the file either,
 * that is DealershipFileManager's job.
 * <p>
 * How the class is organized:
 * • constructor builds an empty inventory, the file manager fills it
 * • getters and setters for name, address, phone (none for inventory, as the
 *   workshop asks: the list is reached through getAllVehicles)
 * • six getVehiclesBy... searches, one per menu option, all built the same way
 * • getAllVehicles, addVehicle, removeVehicle
 */
public class Dealership {
    private String name;
    private String address;
    private String phone;
    // ArrayList instead of an array: the inventory grows and shrinks as the
    // user adds and removes vehicles
    private ArrayList<Vehicle> inventory;

    // The constructor instantiates the list (the workshop insists on it): a
    // Dealership can take vehicles the moment it exists, with no null check
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    /* -----------------------------------------------------------------
       Getters and setters for the dealership details
       ----------------------------------------------------------------- */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /* -----------------------------------------------------------------
       Searches
       Every search follows one pattern: start a new empty list, walk the
       inventory, copy each vehicle that matches, return the list. A new
       list is returned (not the inventory itself) so the caller can do what
       it likes with the result without changing the dealership's stock.
       The return type is List, the interface, so the caller does not depend
       on ArrayList in particular.
       ----------------------------------------------------------------- */

    // Range searches (price, year, mileage) are inclusive on both ends:
    // a vehicle priced exactly at the minimum or the maximum is a match.
    // BigDecimal has no >= and <=: compareTo returns a negative number, 0 or
    // a positive number, so "at least min" is compareTo(min) >= 0
    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    // Text searches use equalsIgnoreCase: the user types "ford", the file
    // says "Ford", and that should still be a match
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    /* -----------------------------------------------------------------
       Whole inventory, add, remove
       ----------------------------------------------------------------- */

    // Returns the live list, not a copy: UserInterface lists it and the file
    // manager writes it, and both must see the current stock
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Takes the Vehicle object itself. UserInterface finds the object by VIN
    // first, then hands it over, so this class does not need to search again
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
}
