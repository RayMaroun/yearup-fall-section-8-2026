package com.pluralsight;

import java.time.LocalDateTime;

/**
 * Employee (Workbook 4a, Section 3-2 Creating Unit Tests, Exercise 1)
 *
 * The Employee from hotel-operations-3 with one addition: getStartTime().
 * A test can only check what it can read, and startTime was private with
 * no getter, so the punchIn test needed a way to look at it.
 *
 * Only punchIn(double) and punchOut(double) are tested: the no-argument
 * overloads read the real clock, so a test could not know the expected value.
 */
public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private double payRate;
    private double hoursWorked;
    private double startTime;

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

    // Added for the tests: lets EmployeeTest see the stored start time
    public double getStartTime() {
        return startTime;
    }

    public void punchIn(double time) {
        startTime = time;
    }

    public void punchOut(double time) {
        double duration = time - startTime;
        hoursWorked += duration;
        startTime = 0;
    }

    // Clock-based overloads (Section 2-3), not covered by the tests
    public void punchIn() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        double time = hour + (minute / 60.0);
        startTime = time;
    }

    public void punchOut() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        double time = hour + (minute / 60.0);
        double duration = time - startTime;
        hoursWorked += duration;
        startTime = 0;
    }

    public void punchTimeCard(double time) {
        if (startTime == 0) {
            startTime = time;
        } else {
            double duration = time - startTime;
            hoursWorked += duration;
            startTime = 0;
        }
    }

    public double getTotalPay() {
        if (hoursWorked <= 40) {
            return hoursWorked * payRate;
        } else {
            return (40 * payRate) + ((hoursWorked - 40) * (payRate * 1.5));
        }
    }

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
