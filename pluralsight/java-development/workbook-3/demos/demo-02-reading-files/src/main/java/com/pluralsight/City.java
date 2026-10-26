package com.pluralsight;

/*
 * City: one line of cities.csv as an object.
 *
 * The fields match the columns of the file, in the same order. The
 * constructor takes all three values at once, because a City is always
 * built from a complete line. Getters only: nothing changes after reading.
 */
public class City {
    private String name;
    private String country;
    private int population;

    public City(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
