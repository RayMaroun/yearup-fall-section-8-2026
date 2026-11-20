package com.pluralsight;

/*
 * Cellist is a child class of Musician. Because a Cellist is-a Musician,
 * it fits in a Duet<Musician>.
 */
public class Cellist extends Musician {
    public Cellist(String name, String instrument) {
        super(name, instrument);
    }

    // The Cellist's own version of perform(). This one runs when a Duet
    // asks a Cellist to perform.
    @Override
    public void perform() {
        System.out.println("I am a Cellist and I am performing!");
    }
}
