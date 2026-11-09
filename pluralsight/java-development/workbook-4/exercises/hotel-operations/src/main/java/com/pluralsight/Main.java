package com.pluralsight;

/**
 * Main (Workbook 4a, Section 2-1 Creating Classes, Exercise 1)
 *
 * The exercise says to use main() to create objects and test the logic
 * in the classes: one Employee, one Reservation, one Room, and print
 * every getter so the derived values can be checked by hand.
 *
 * John Doe: 45 hours at 25.00 -> 40 regular + 5 overtime
 *   total = 40 x 25 + 5 x 37.5 = 1187.5
 * King room, 3 nights, weekend: price per night 139 x 1.1 = 152.9, total 152.9 x 3 = 458.7
 *   (printed as 458.70000000000005, the double rounding error that Section 4-1 BigDecimal is about)
 */
public class Main {
    public static void main(String[] args) {
        // Test Employee class
        Employee employee = new Employee(1, "John Doe", "IT", 25.00, 45.00);
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Employee Department: " + employee.getDepartment());
        System.out.println("Employee Pay Rate: " + employee.getPayRate());
        System.out.println("Employee Hours Worked: " + employee.getHoursWorked());
        System.out.println("Employee Total Pay: " + employee.getTotalPay());
        System.out.println("Employee Regular Hours: " + employee.getRegularHours());
        System.out.println("Employee Overtime Hours: " + employee.getOvertimeHours());
        System.out.println("=======================================================================");

        // Test Reservation class
        Reservation reservation = new Reservation("king", 3, true);
        System.out.println("Room Type: " + reservation.getRoomType());
        System.out.println("Price per Night: " + reservation.getPrice());
        System.out.println("Number of Nights: " + reservation.getNumberOfNights());
        System.out.println("Is Weekend: " + reservation.isWeekend());
        System.out.println("Reservation Total: " + reservation.getReservationTotal());
        System.out.println("=======================================================================");

        // Test Room class
        Room room = new Room(2, 99.99);
        System.out.println("Number of Beds: " + room.getNumberOfBeds());
        System.out.println("Price: " + room.getPrice());
        System.out.println("Is Occupied: " + room.isOccupied());
        System.out.println("Is Dirty: " + room.isDirty());
        System.out.println("Is Available: " + room.isAvailable());
    }
}
