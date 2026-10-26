package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Demo 02 - Reading files, part 2: a csv file, one object per line
 *
 * A csv file is a text file where every line holds several values separated
 * by the same character. In cities.csv that character is a pipe:
 *
 *   name|country|population
 *   Boston|United States|650000
 *
 * Reading such a line gives you ONE String. To get the three values out you
 * split it. This program shows split on one line first, then uses it on
 * every line of the file to build a City object per line.
 *
 * Two things to remember:
 *   1. split takes a pattern, and in a pattern | means "or". To split on a real
 *      pipe you write "\\|". Splitting on a comma is just ",".
 *   2. Everything that comes out of a file is text. "650000" is a String until
 *      you convert it with Integer.parseInt (or Double.parseDouble).
 */
public class Main2 {
    public static void main(String[] args) {
        // ---------- split: one line of text into its parts ----------
        // split cuts the String at every separator and returns a String[] of the pieces.
        System.out.println("========== split: one line into its parts ==========");
        String line = "Tokyo|Japan|13960000";
        // Try it: with a bare | the pattern means "or nothing", so the line is split
        // between every single character and you get far more than 3 parts.
        // String[] parts = line.split("|");                            // wrong first: a bare | means "or" in a pattern, so it splits between every character
        String[] parts = line.split("\\|");                             // \\| means a real pipe character
        System.out.println("Parts: " + parts.length);
        System.out.println(parts[0]);                                   // Tokyo
        System.out.println(parts[1]);                                   // Japan
        System.out.println(parts[2]);                                   // "13960000": still text, not a number yet
        int population = Integer.parseInt(parts[2]);                    // now a number
        System.out.println(population + 100000);                        // 14060000: arithmetic works because it is an int
        System.out.println();

        // ---------- Read a csv file: skip the header, each line becomes an object ----------
        // Same reading loop as Main, plus three steps per line:
        // split the line, convert each piece to its type, build the object.
        System.out.println("========== Read a csv file: each line becomes an object ==========");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("cities.csv"));

            // The first line of the file is the column names, not a city.
            // Read it once before the loop so the loop never sees it
            // (parseInt("population") would throw NumberFormatException).
            bufferedReader.readLine();                                  // first line is the header: read it and drop it

            String csvLine;
            while ((csvLine = bufferedReader.readLine()) != null) {
                String[] tokens = csvLine.split("\\|");
                String name = tokens[0];
                String country = tokens[1];
                int cityPopulation = Integer.parseInt(tokens[2]);       // every token is text: convert the number

                // One object per line, built from the three values
                City city = new City(name, country, cityPopulation);

                // %,d prints the number with thousands separators: 13,960,000
                System.out.printf("%s, %s: %,d people%n",
                        city.getName(), city.getCountry(), city.getPopulation());
            }

            bufferedReader.close();

        } catch (Exception ex) {
            System.out.println("Something went wrong!");
        }
        System.out.println();
    }
}
