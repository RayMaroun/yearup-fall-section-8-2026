package com.pluralsight;

/**
 * Employee (Workbook 4a, Section 2-1 Creating Classes, Exercise 1)
 *
 * Stores payroll information about one employee: id, name, department,
 * pay rate and hours worked.
 *
 * The three derived getters have no backing variable, they calculate
 * from hoursWorked and payRate:
 *   getRegularHours()  the first 40 hours
 *   getOvertimeHours() anything above 40
 *   getTotalPay()      regular at the pay rate, overtime at 1.5 x the rate
 */
public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private double payRate;
    private double hoursWorked;

    public Employee(int employeeId, String name, String department, double payRate, double hoursWorked) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
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

    // Derived getter: up to 40 hours at the normal rate, the rest at time and a half
    public double getTotalPay() {
        if (hoursWorked <= 40) {
            return hoursWorked * payRate;
        } else {
            return (40 * payRate) + ((hoursWorked - 40) * (payRate * 1.5));
        }
    }

    // Alternative kept switched off: the same total built from the two
    // getters below, so the 40-hour rule lives in one place only
/*    public double getTotalPay() {
        return (getRegularHours() * payRate) + (getOvertimeHours() * payRate * 1.5);
    }*/


    // Derived getter: the hours paid at the normal rate (never more than 40)
    public double getRegularHours() {
        if (hoursWorked <= 40) {
            return hoursWorked;
        } else {
            return 40;
        }
    }

    // Derived getter: the hours above 40 (0 if there are none)
    public double getOvertimeHours() {
        if (hoursWorked > 40) {
            return hoursWorked - 40;
        } else {
            return 0;
        }
    }
}
