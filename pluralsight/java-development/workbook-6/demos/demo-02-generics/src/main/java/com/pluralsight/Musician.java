package com.pluralsight;

/*
 * Musician is the parent class of Cellist and Violinist. It is the bound
 * in Duet<T extends Musician>: a Duet accepts a Musician or any of its
 * child classes, and nothing else.
 */
public class Musician {
    private String name;
    private String instrument;

    public Musician(String name, String instrument) {
        this.name = name;
        this.instrument = instrument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    // The method Duet relies on. Every Musician has it, so every child
    // class has it too, either inherited as it is or overridden.
    public void perform() {
        System.out.println("I am a musician and I am performing!");
    }
}
