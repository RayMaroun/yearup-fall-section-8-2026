package com.pluralsight;

/**
 * StaticClasses (Workbook 4a, Section 4-2 Static Classes, Exercise 1)
 *
 * Tries each format() overload. Note there is no "new NameFormatter()":
 * the methods are static, so they are called on the class name.
 *
 * Expected output:
 *   Doe, Mr. John Robert, Jr.
 *   Johnson, Mel
 *   Johnson, Mel B, PhD
 *   Johnson, Dr. Mel B, PhD
 *   Garcia, Maria
 */
public class StaticClasses {
    public static void main(String[] args) {
        
        // Test the format method with separate name components
        String formattedName1 = NameFormatter.format("Mr.", "John", "Robert", "Doe", "Jr.");
        System.out.println(formattedName1); // Should print "Doe, Mr. John Robert, Jr."

        // The three example names from the text, through the one-String overload
        System.out.println(NameFormatter.format("Mel Johnson"));
        System.out.println(NameFormatter.format("Mel B Johnson, PhD"));
        System.out.println(NameFormatter.format("Dr. Mel B Johnson, PhD"));

        // Test the format method with minimum name components
        String formattedName3 = NameFormatter.format("Maria", "Garcia");
        System.out.println(formattedName3); // Should print "Garcia, Maria"

    }
}
