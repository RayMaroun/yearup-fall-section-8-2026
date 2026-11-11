package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EmployeeTest (Workbook 4a, Section 3-2 Creating Unit Tests, Exercise 1)
 *
 * Tests the punchIn(time) and punchOut(time) pair: the start time is stored,
 * and punching out adds the right number of hours (9.0 to 17.5 = 8.5).
 *
 * assertEquals(expected, actual): expected first, so a failure message
 * reads "expected 8.5 but was ..." the right way round.
 */
class EmployeeTest {

    @Test
    public void punchIn_StartTimeNotSet_StartTimeSetToGivenTime() {
        // Arrange
        Employee employee = new Employee(1, "John Doe", "IT", 20.0);
        double startTime = 9.0;
        double expectedStartTime = 9.0;

        // Act
        employee.punchIn(startTime);

        // Assert
        double actualStartTime = employee.getStartTime();
        assertEquals(expectedStartTime, actualStartTime);
    }

    @Test
    public void punchOut_HoursNotSet_HoursWorkedSetCorrectly() {
        // Arrange
        Employee employee = new Employee(1, "John Doe", "IT", 20.0);
        double endTime = 17.5;
        employee.punchIn(9.0);
        double expectedHoursWorked = 8.5;

        // Act
        employee.punchOut(endTime);

        // Assert
        double actualHoursWorked = employee.getHoursWorked();
        assertEquals(expectedHoursWorked, actualHoursWorked);
    }


}
