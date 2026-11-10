package com.pluralsight;

/**
 * Employee (Workbook 4a, Section 2-2 Creating Classes, Exercise 2)
 *
 * The Section 2-1 Employee, with hoursWorked no longer passed to the
 * constructor: it starts at 0 and grows every time the employee
 * punches in and out.
 *
 *   punchIn(time)   remembers the start time
 *   punchOut(time)  adds (time - start) to hoursWorked
 *   punchTimeCard(time)  BONUS: one method that does both, it decides
 *                   by whether a start time is already stored
 *
 * Times are hours on a 24-hour clock: 9.0 is 9:00 am, 17.5 is 5:30 pm.
 * The text says punchIn(int time) "to keep the math simple"; this version
 * takes a double so half hours work too. An int argument still compiles
 * (9 is widened to 9.0), so calling punchIn(9) is fine.
 */
public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private double payRate;
    private double hoursWorked;
    private double startTime;   // 0 means "not punched in"

    public Employee(int employeeId, String name, String department, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.payRate = payRate;
        this.hoursWorked = 0;
        this.startTime = 0;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    // Track when the shift started
    public void punchIn(double time) {
        startTime = time;
    }

    // Shift over: add its length to the total and clear the start time
    public void punchOut(double time) {
        double duration = time - startTime;
        hoursWorked += duration;
        startTime = 0;
    }

    // BONUS: one method for both. No start time stored -> this is a punch in;
    // a start time stored -> this is a punch out
    public void punchTimeCard(double time) {
        if (startTime == 0) {
            startTime = time;
        } else {
            double duration = time - startTime;
            hoursWorked += duration;
            startTime = 0;
        }
    }

    // Derived getters, unchanged from Section 2-1
    public double getTotalPay() {
        if (hoursWorked <= 40) {
            return hoursWorked * payRate;
        } else {
            return (40 * payRate) + ((hoursWorked - 40) * (payRate * 1.5));
        }
    }

    /*    public double getTotalPay() {
        return (getRegularHours() * payRate) + (getOvertimeHours() * payRate * 1.5);
    }*/

    public double getRegularHours() {
        if (hoursWorked <= 40) {
            return hoursWorked;
        } else {
            return 40;
        }
    }

    public double getOvertimeHours() {
        if (hoursWorked > 40) {
            return hoursWorked - 40;
        } else {
            return 0;
        }
    }
}
