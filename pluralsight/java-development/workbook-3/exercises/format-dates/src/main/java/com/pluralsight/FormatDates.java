package com.pluralsight;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * FormatDates (Workbook 3a, Section 2-2 Date Basics, Exercise 2)
 *
 * Gets the current date and time once and prints it in these formats:
 *   04/22/2026
 *   2026-04-22
 *   Wednesday, April 22, 2026
 *   Wednesday, Apr 22, 2026 10:02        (in GMT)
 *   CHALLENGE: 5:02 on 22-Apr-2026       (in your local time zone)
 *
 * Pattern letters used: MM month number, MMM short month name, MMMM full month
 * name, dd / d day, yyyy year, EEEE full day name, HH hour 0-23, h hour 1-12,
 * mm minutes, a AM/PM. Text in single quotes ('on') is printed as is.
 * Capital MM is month, lowercase mm is minutes: easy to mix up.
 *
 * A second way to get the GMT time (ZonedDateTime instead of SimpleDateFormat)
 * is kept switched off right after the GMT line.
 */
public class FormatDates {
    public static void main(String[] args) {

        // Get the current date and time
        // LocalDateTime has no time zone: it is the date and time of this computer
        LocalDateTime now = LocalDateTime.now();

        // Format the date in the first format (MM/dd/yyyy)
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate1 = now.format(formatter1);
        System.out.println(formattedDate1);

        // Format the date in the second format (yyyy-MM-dd)
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate2 = now.format(formatter2);
        System.out.println(formattedDate2);

        // Format the date in the third format (EEEE, MMMM d, yyyy)
        // EEEE adds the full day name in front: Wednesday, April 22, 2026
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        String formattedDate3 = now.format(formatter3);
        System.out.println(formattedDate3);

        // Format the date in the fourth format (EEEE, MMM d, yyyy HH:mm in GMT)
        // LocalDateTime cannot change time zone, so this one uses the older Date class:
        // a Date is a moment in time, and the formatter decides which zone to show it in
        Date date = new Date();
        SimpleDateFormat requiredFormat = new SimpleDateFormat("EEEE, MMM d, yyyy HH:mm 'in GMT'");
        requiredFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String gmtTime = requiredFormat.format(date);
        System.out.println(gmtTime);

        // Another Way: ZonedDateTime is the Java 8 date type that carries a time zone.
        // To try it, remove the comment markers (and comment out the four lines above).
/*      ZonedDateTime nowGmt = ZonedDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm", Locale.ENGLISH);
        String formatted = nowGmt.format(formatter);
        System.out.println(formatted + " in GMT");*/

        // CHALLENGE: the fifth format (h:mm a 'on' dd-MMM-yyyy in local time zone)
        // now is already local time, so no time zone work is needed here
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("h:mm a 'on' dd-MMM-yyyy 'in local time zone'");
        String formattedDate5 = now.format(formatter5);
        System.out.println(formattedDate5);

    }
}
