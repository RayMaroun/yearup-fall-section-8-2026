package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * NameFormatterTest (Workbook 4a, Section 4-2 Static Classes, Exercise 1)
 *
 * The exercise asks for "unit tests to test the methods with a few different
 * variations of names". One test per overload, with the names from the text
 * plus the minimum two-part name.
 */
class NameFormatterTest {

    @Test
    public void format_FirstAndLastName_LastCommaFirst() {
        // Arrange
        String expected = "Johnson, Mel";

        // Act
        String actual = NameFormatter.format("Mel", "Johnson");

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void format_AllFiveParts_StandardFormat() {
        // Arrange
        String expected = "Johnson, Dr. Mel B, PhD";

        // Act
        String actual = NameFormatter.format("Dr.", "Mel", "B", "Johnson", "PhD");

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void format_FivePartsWithEmptyOptionals_NoStraySeparators() {
        // Arrange
        String expected = "Johnson, Mel";

        // Act
        String actual = NameFormatter.format("", "Mel", "", "Johnson", "");

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void format_FullNameWithPrefixAndSuffix_StandardFormat() {
        // Arrange
        String expected = "Johnson, Dr. Mel B, PhD";

        // Act
        String actual = NameFormatter.format("Dr. Mel B Johnson, PhD");

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void format_FullNameWithMiddleAndSuffix_StandardFormat() {
        // Arrange
        String expected = "Johnson, Mel B, PhD";

        // Act
        String actual = NameFormatter.format("Mel B Johnson, PhD");

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void format_FullNameTwoWords_LastCommaFirst() {
        // Arrange
        String expected = "Johnson, Mel";

        // Act
        String actual = NameFormatter.format("Mel Johnson");

        // Assert
        assertEquals(expected, actual);
    }
}
