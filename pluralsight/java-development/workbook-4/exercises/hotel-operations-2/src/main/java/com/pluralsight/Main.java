package com.pluralsight;

/**
 * Main (Workbook 4a, Section 2-2 Creating Classes, Exercise 2)
 *
 * Walks one room through check in, check out and cleaning, printing the
 * two flags after each step, then one employee through a punchIn/punchOut
 * day (8.5 hours) and a punchTimeCard day (9 hours): 17.5 hours total,
 * all regular, 262.5 at 15.00 an hour.
 */
public class Main {
    public static void main(String[] args) {
        // Create a new room with two beds and a price of $100 per night
        Room room = new Room(2, 100.00);

        // Check in a guest and mark the room as dirty
        room.checkIn();
        System.out.println("Room is occupied: " + room.isOccupied());
        System.out.println("Room is dirty: " + room.isDirty());

        // Guest checks out and room is cleaned
        room.checkOut();
        System.out.println("Room is occupied: " + room.isOccupied());
        room.cleanRoom();
        System.out.println("Room is dirty: " + room.isDirty());

        // Create a new employee and punch in
        Employee employee = new Employee(1, "John Doe", "Housekeeping", 15.00);
        employee.punchIn(9.0);

        // Employee works for 8.5 hours and punches out
        employee.punchOut(17.5);
        System.out.println("Hours worked: " + employee.getHoursWorked());
        System.out.println("Total pay: " + employee.getTotalPay());
        System.out.println("Regular hours: " + employee.getRegularHours());
        System.out.println("Overtime hours: " + employee.getOvertimeHours());

        // Employee punches time card again (BONUS): first call is the punch in,
        // second call is the punch out
        employee.punchTimeCard(9.0);
        employee.punchTimeCard(18.0);
        System.out.println("Hours worked: " + employee.getHoursWorked());
        System.out.println("Total pay: " + employee.getTotalPay());
        System.out.println("Regular hours: " + employee.getRegularHours());
        System.out.println("Overtime hours: " + employee.getOvertimeHours());
    }
}
