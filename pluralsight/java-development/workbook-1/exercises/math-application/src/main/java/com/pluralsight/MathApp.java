package com.pluralsight;

// Exercise: Math Application. Eight questions answered with the Math class, each with a clear message.
public class MathApp {
    public static void main(String[] args) {

        // Find and display the largest of two variables named job1Salary and job2Salary using Math.max().
        // Set the variables to any value you want.
        double bobSalary = 55000.0;
        double garySalary = 65000.0;
        double highestSalary = Math.max(bobSalary, garySalary);
        System.out.println("The highest salary is " + highestSalary);

        // Find and display the smallest of two variables named carPrice and truckPrice.
        // Set the variables to any value you want.
        double carPrice = 20000.0;
        double truckPrice = 18000.0;
        double minPrice = Math.min(carPrice, truckPrice);
        System.out.println("The smallest price is " + minPrice);

        // Find and display the area of a circle whose radius is 7.25
        // Area = pi * radius squared. Math.PI is the constant, Math.pow(radius, 2) is radius squared.
        double radius = 7.25;
        double area = Math.PI * Math.pow(radius, 2);
        System.out.println("The area of the circle is " + area);

        // Find and display the square root of a variable after it is set to 5.0
        double squareValue = 5.0;
        double squareRoot = Math.sqrt(squareValue);
        System.out.println("The square root of " + squareValue + " is " + squareRoot);

        // Find and display the distance between the points (5, 10) and (85, 50)
        // Distance formula: square root of (x2 - x1)^2 + (y2 - y1)^2
        int x1 = 5, y1 = 10, x2 = 85, y2 = 50;
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("The distance between the points is " + distance);

        // Find and display the absolute (positive) value of a variable after it is set to -3.8
        double negativeValue = -3.8;
        double absoluteValue = Math.abs(negativeValue);
        System.out.println("The absolute value of " + negativeValue + " is " + absoluteValue);

        // Find and display a random number between 0 and 1
        // Math.random() returns a double from 0.0 (included) up to 1.0 (not included); it changes every run
        double randomNum = Math.random();
        System.out.println("The random number is " + randomNum);

        // Calculate how many minutes are in 24 days, use a variable for each value you calculate with. BONUS: How many milliseconds?
        // Base values
        int days = 24;
        int hoursPerDay = 24;
        int minutesPerHour = 60;
        int secondsPerMinute = 60;
        int millisecondsPerSecond = 1000;

        // One step at a time, each in its own variable
        int hours = days * hoursPerDay;
        int minutes = hours * minutesPerHour;

        // BONUS: milliseconds. 2,073,600,000 only just fits in an int (max 2,147,483,647).
        // Math.multiplyExact multiplies like *, but if the result does not fit it throws an
        // ArithmeticException instead of silently wrapping into a wrong number. Try days = 25.
        int seconds = Math.multiplyExact(minutes, secondsPerMinute);
        long milliseconds = Math.multiplyExact(seconds, millisecondsPerSecond);

        // Print results
        System.out.println("Minutes in " + days + " days: " + minutes);
        System.out.println("Milliseconds in " + days + " days: " + milliseconds);
    }

}
