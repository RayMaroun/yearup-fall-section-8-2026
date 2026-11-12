package com.pluralsight;

/**
 * NameFormatter (Workbook 4a, Section 4-2 Static Classes, Exercise 1)
 *
 * A utility (helper) class: only static methods, never an object.
 * You call NameFormatter.format(...) on the class itself, the way you
 * call Math.round(...). The private constructor makes "new NameFormatter()"
 * a compile error, which is how you say "there is nothing to create here".
 *
 * Every overload produces the one standard format:
 *   LastName, Prefix FirstName MiddleName, Suffix
 *   Johnson, Dr. Mel B, PhD
 * Only first and last name are required; the optional parts are left out
 * (together with their separators) when they are empty.
 *
 * The three overloads share the work: the five-argument format() builds
 * the string, the other two turn their input into five parts and call it.
 */
public class NameFormatter {

    // Private constructor to prevent instantiation of this class
    private NameFormatter() {
    }

    // The easiest one: no prefix, middle name or suffix, so pass empty strings
    public static String format(String firstName, String lastName) {
        return format("", firstName, "", lastName, "");
    }

    // The one that does the formatting. StringBuilder appends piece by piece
    // instead of creating a new String on every +
    public static String format(String prefix, String firstName, String middleName, String lastName, String suffix) {
        StringBuilder sb = new StringBuilder();

        sb.append(lastName).append(", ");

        if (!prefix.isEmpty()) {
            sb.append(prefix).append(" ");
        }

        sb.append(firstName);

        if (!middleName.isEmpty()) {
            sb.append(" ").append(middleName);
        }
        if (!suffix.isEmpty()) {
            sb.append(", ").append(suffix);
        }
        return sb.toString();
    }

    // Takes "Prefix FirstName MiddleName LastName, Suffix" (the order the text
    // promises) and splits it into the five parts
    public static String format(String fullName) {
        // The suffix is the only part after a comma
        String[] parts = fullName.split(", ");
        String mainPart = parts[0];
        String suffix = parts.length > 1 ? parts[1] : "";


        String[] nameParts = mainPart.split(" ");

        String prefix = "", firstName = "", middleName = "", lastName = "";

        // How many words are left tells us which optional parts are present:
        // 4 = prefix first middle last, 3 = first middle last, 2 = first last
        switch (nameParts.length) {
            case 4:
                prefix = nameParts[0];
                firstName = nameParts[1];
                middleName = nameParts[2];
                lastName = nameParts[3];
                break;
            case 3:
                firstName = nameParts[0];
                middleName = nameParts[1];
                lastName = nameParts[2];
                break;
            case 2:
                firstName = nameParts[0];
                lastName = nameParts[1];
                break;
        }

        return format(prefix, firstName, middleName, lastName, suffix);
    }
}
