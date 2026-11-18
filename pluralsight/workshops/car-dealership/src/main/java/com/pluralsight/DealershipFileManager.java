package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Persistence layer: the only class that knows the inventory file exists.
 * <p>
 * File format (inventory.csv, pipe-delimited, at the project root):
 * line 1        name|address|phone                                 (the dealership)
 * line 2 and on vin|year|make|model|vehicleType|color|odometer|price (one vehicle each)
 * <p>
 * Example:
 * D & B Used Cars|111 Old Benbrook Rd|817-555-5555
 * 10112|1993|Ford|Explorer|SUV|Red|525123|995.00
 * <p>
 * Two methods, two directions:
 * • getDealership  reads the file and builds a Dealership full of Vehicles
 * • saveDealership writes a Dealership and its vehicles back, same format
 * <p>
 * Keeping the file code here means Dealership and UserInterface never parse a
 * line or build one. If the file format changes, this is the only class to edit.
 */
public class DealershipFileManager {

    public Dealership getDealership() {
        // null until the first line is read: there is no Dealership object yet
        // because its name, address and phone come from the file
        Dealership dealership = null;
        // The vehicles are collected here and added to the dealership at the
        // end, once we are sure the dealership line was read
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // try-with-resources: the reader is closed for us, even on an exception.
        // The file sits at the project root, which is the working directory
        // when the program runs from IntelliJ
        try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            // The first line is the dealership, every other line a vehicle:
            // the counter tells the two apart
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                // split on the pipe; "\\|" because | alone means "or" in a regex
                String[] fields = line.split("\\|");
                if (lineNumber == 0) { // dealership info
                    String name = fields[0];
                    String address = fields[1];
                    String phone = fields[2];
                    dealership = new Dealership(name, address, phone);
                } else { // vehicle info
                    // Every field arrives as text; numbers are parsed into the
                    // type the Vehicle constructor expects, in column order
                    int vin = Integer.parseInt(fields[0]);
                    int year = Integer.parseInt(fields[1]);
                    String make = fields[2];
                    String model = fields[3];
                    String vehicleType = fields[4];
                    String color = fields[5];
                    int odometer = Integer.parseInt(fields[6]);
                    // new BigDecimal(text) keeps the price exactly as written in the file
                    BigDecimal price = new BigDecimal(fields[7]);
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    vehicles.add(vehicle);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Only now put the vehicles into the dealership: if the file was
        // missing or empty, dealership is still null and there is nothing to fill
        if (dealership != null) {
            for (Vehicle vehicle : vehicles) {
                dealership.addVehicle(vehicle);
            }
        }

        return dealership;
    }

    // Rewrites the whole file from the Dealership object: the in-memory list
    // is the truth, the file is a copy of it. FileWriter without the append
    // flag starts from an empty file, so removed vehicles disappear from disk
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv"))) {
            // Write dealership information
            // Same column order as getDealership reads, so the file round-trips
            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bw.newLine();

            // Write vehicle inventory
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel()
                        + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer()
                        + "|" + String.format("%.2f", vehicle.getPrice()));  // 995.00, as in the handed-out file
                bw.newLine();
            }

            System.out.println("Dealership saved successfully to inventory.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
