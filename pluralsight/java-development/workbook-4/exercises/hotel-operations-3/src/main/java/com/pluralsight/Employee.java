package com.pluralsight;

import java.time.LocalDateTime;   // the current date and time, used by the no-argument overloads

/**
 * Employee (Workbook 4a, Section 2-3 Overloading Methods, Exercise 3)
 *
 * The Section 2-2 Employee plus two overloads. Overloading means two
 * methods with the same name and a different parameter list; Java picks
 * one by the arguments you pass:
 *   punchIn(double time)  / punchOut(double time)   the caller gives the time
 *   punchIn()             / punchOut()              the computer clock gives it
 *
 * The clock overloads read LocalDateTime.now(), take the hour and minute,
 * and turn them into the same "hours on a 24-hour clock" number the
 * double versions use (10:30 becomes 10.5), so both pairs share the
 * same startTime and hoursWorked.
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

    // Signature punchIn_double: the caller supplies the time
    public void punchIn(double time) {
        startTime = time;
    }

    public void punchOut(double time) {
        double duration = time - startTime;
        hoursWorked += duration;
        startTime = 0;
    }

    // Signature punchIn (no parameters): the time comes from the clock.
    // minute / 60.0 (not / 60) so 30 minutes becomes 0.5, not 0
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

    // BONUS from Section 2-2, kept
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
